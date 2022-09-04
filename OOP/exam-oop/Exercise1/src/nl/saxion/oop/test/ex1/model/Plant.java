package nl.saxion.oop.test.ex1.model;

public class Plant {
    private String type;
    private int nmOfNotWatered;
    private int interval;
    private int id;

    private static int availableId = 1;

    public Plant(String type, int interval) {
        this.type = type;
        this.nmOfNotWatered = 0;
        this.interval = interval;
        this.id = availableId;
        availableId++;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString(){
        if (getInterval()-getNmOfNotWatered() == 0){
            return "- Plant " + getType() + " (id: "+ getId() +") needs water TODAY!";
        } else {
            return "- Plant " + getType() + " (id: " + getId() + ") needs water in " + ( getInterval() - getNmOfNotWatered() ) + " days.";
        }
    }

    public String getType() {
        return type;
    }

    public int getNmOfNotWatered() {
        return nmOfNotWatered;
    }

    public int getInterval() {
        return interval;
    }

    public void setNmOfNotWatered(int nmOfNotWatered) {
        this.nmOfNotWatered = nmOfNotWatered;
    }
}
