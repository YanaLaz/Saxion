package nl.saxion.oop.week1;

import nl.saxion.app.SaxionApp;

public class airplane {
    private boolean passengers, luggage, doors;

    public airplane() {
        this.passengers = false;
        this.luggage = false;
        this.doors = false;
    }

    public void doorsLock(){
        if (isDoors() == true){
            doors = false;
            SaxionApp.printLine("Doors are unlocked!");
        } else {
            doors = true;
            SaxionApp.printLine("Doors are locked!");
        }
    }

    public void loadPassengers(){
        if (isDoors() == true){
            setPassengers(true);
            SaxionApp.printLine("Passengers are loaded");
    } else { SaxionApp.printLine("Unable to load passengers. The doors are locked!");
        }
    }
    public void loadLuggage(){
        if (isDoors() == true){
            setLuggage(true);
            SaxionApp.printLine("Luggage are loaded!");}
        else {
            SaxionApp.printLine("Unable to load luggage. The doors are locked!");
        }
    }




    public void depart(){
        if (isDoors() == true) {
            if (isPassengers() == true){
                if (isLuggage() == true){
                    SaxionApp.printLine("The plane is ready to depart!");
                } else {
                    SaxionApp.printLine("Load luggage!");
                }
            } else {
                SaxionApp.printLine("Load passengers!");
            }
        } else {
            SaxionApp.printLine("Lock the doors!");
        }
    }

    public void setPassengers(boolean passengers) {
        this.passengers = passengers;
    }

    public void setLuggage(boolean luggage) {
        this.luggage = luggage;
    }



    public boolean isPassengers() {
        return passengers;
    }

    public boolean isLuggage() {
        return luggage;
    }

    public boolean isDoors() {
        return doors;
    }
}
