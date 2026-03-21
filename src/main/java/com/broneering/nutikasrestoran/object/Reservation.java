package com.broneering.nutikasrestoran.object;

import java.time.LocalDateTime;

public class Reservation {
    public long tableId;
    public LocalDateTime startTime;
    public LocalDateTime endTime;

    //Siin pean arvestama, et laud on kinni, kui:
    // - uus broneering algab ENNE olemasoleva lõppu
    // JA
    // uus broneering lõppeb PÄRAST olemasoleva algust


    public Reservation(long tableId, LocalDateTime startTime, LocalDateTime endTime) {
        this.tableId = tableId;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public long getTableId() {
        return tableId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }
}
