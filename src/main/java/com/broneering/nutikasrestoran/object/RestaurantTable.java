package com.broneering.nutikasrestoran.object;

import java.util.ArrayList;
import java.util.List;

public class RestaurantTable {

    public long id;
    public int size;
    public boolean occupied;
    public List<String> features;

    public RestaurantTable(long id, int size, boolean occupied) {
        this.id = id;
        this.size = size;
        this.occupied = occupied;
        //Kogun laua omadused listi, sest nii on paindlikum
        features = new ArrayList<>();
    }

    //Getterid ja setterid
    public long getId() {
        return id;
    }

    public int getSize() {
        return size;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public List<String> getFeatures() {
        return features;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public void setFeatures(List<String> features) {
        this.features = features;
    }
}
