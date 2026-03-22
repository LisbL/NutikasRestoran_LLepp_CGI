package com.broneering.nutikasrestoran.object;

import java.util.ArrayList;
import java.util.List;

public class RestaurantTable {

    private long id;
    private int size;
    private boolean occupied;
    private List<String> features;
    //Asukoha jaoks koordinaadid
    private int x;
    private int y;
    private String zone;
    private int score;

    public RestaurantTable(long id, int size, boolean occupied,int x, int y) {
        this.id = id;
        this.size = size;
        this.occupied = occupied;
        //Kogun laua omadused listi, sest nii on paindlikum
        features = new ArrayList<>();
        this.x = x;
        this.y = y;
        if (x == 5) {
            zone = "Terrass";
        } else if (x == 1) {
            zone = "Privaatruum";
            features.add("Vaikne ala");
        } else{
            zone = "Sisesaal";
        }
    }


    //Getterid ja setterid
    public long getId() {
        return id;
    }

    public int getSize() {
        return size;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public List<String> getFeatures() {
        return features;
    }

    public String getZone() {
        return zone;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public void setFeatures(List<String> features) {
        this.features = features;
    }
}
