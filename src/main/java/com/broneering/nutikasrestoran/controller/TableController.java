package com.broneering.nutikasrestoran.controller;

import com.broneering.nutikasrestoran.object.Reservation;
import com.broneering.nutikasrestoran.object.RestaurantTable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tables")
public class TableController {

    //ajutiselt näidisandemetega laudade hoidmiseks
    private List<RestaurantTable> tables = new ArrayList<>();

    private List<Reservation> allReservations = new ArrayList<>();

    public TableController() {
        Random random = new Random();

        for (int i = 0; i <= 10; i++) {
            //Määran suvalise suuruse 2, 4, 6 ja 8 vahel
            int size = (random.nextInt(4) + 1)*2;
            //Juhuslikult kas broneeritud või mitte
            boolean occupied = random.nextBoolean();
            //Loon uue laua objekti
            RestaurantTable table = new RestaurantTable(i, size, occupied);

            //Lisan talle juhuslikult omadusi ning lõpuks listi
            if (random.nextBoolean()) {
                table.getFeatures().add("Akna all");
            }

            if (occupied) {
                LocalDateTime dateTime = LocalDateTime.now();
                LocalDateTime startTime = dateTime.minusHours(1);
                LocalDateTime endTime = dateTime.plusHours(1);
                //Lisan broneeringu nimekirja
                allReservations.add(new Reservation(i, startTime, endTime));


            }
            tables.add(table);
        }
    }

    @GetMapping
    // @RequestParam - extact query parameters, form parameters, files from a web request
    // and bind them to method parameters in a controller
    // You can specify if the param is required and set default values if needed
    public List<RestaurantTable> getTables(
            @RequestParam(required = false) Integer size,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startTime) {
        if (size == null) {
            return tables; //Näitan kõike
        }

        LocalDateTime start = (startTime == null) ? LocalDateTime.now() : startTime;
        LocalDateTime end = start.plusHours(2); // 2h külastust

        //.stream() takes data structure and gives a sequence of elements you can
        // transform, filter, sort, combine
        return tables.stream()
                //Kas lauas on piisavalt kohti
                .filter(table -> table.getSize() >= size)
                //Kas laud on vaba
                .filter(table -> !table.occupied)
                //Kas laud on vaba ajavahemikus?
                .filter(table -> isTableAvailable(table.getId(), start, end))
                //Lisame sorteerimise sobilikumast lauast, vähem sobilikumani
                .sorted(Comparator.comparingInt(RestaurantTable::getSize))
                //Leiame sobivad lauad
                .collect(Collectors.toList());

    }

    public boolean isTableAvailable(Long tableId, LocalDateTime checkStart, LocalDateTime checkEnd) {
        for (Reservation res: allReservations) {
            //Kontrollin ainult konkreetse laua broneeringuid
            if (res.getTableId() == tableId) {
                //Kattuvus
                //(Uus algus < vana lõpp) JA (uus lõpp > vana algus)
                if (checkStart.isBefore(res.getEndTime()) && checkEnd.isAfter(res.getStartTime())) {
                    return false; //Laud kinni
                }
            }
        }
        return true; //Kattuvusi polnud, laud sobib broneeringuks
    }
}
