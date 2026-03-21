package com.broneering.nutikasrestoran.controller;

import com.broneering.nutikasrestoran.object.RestaurantTable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/api/tables")
public class TableController {

    //ajutiselt näidisandemetega laudade hoidmiseks
    private List<RestaurantTable> tables = new ArrayList<>();

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
                table.getFeatures().add("aknaga");
            }
            tables.add(table);
        }
    }

    @GetMapping
    public List<RestaurantTable> getAllTables() {
        return tables;
    }
}
