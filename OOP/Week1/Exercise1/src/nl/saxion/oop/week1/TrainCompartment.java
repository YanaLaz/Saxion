package nl.saxion.oop.week1;

import nl.saxion.app.SaxionApp;

public class TrainCompartment {
    private int numberOfSeats;
    private int compartmentClass;
    private int numberOfSeatsInUse;

    //constructor
    public TrainCompartment() {
        this.numberOfSeats = 50;
        this.compartmentClass = 2;
        this.numberOfSeatsInUse = 0;
    }

    public void enter() {
        if (numberOfSeatsInUse <= 50){
            numberOfSeatsInUse ++;
            SaxionApp.printLine("A passenger enter the train...");
            SaxionApp.printLine("There are now " + numberOfSeatsInUse + " in use");
        } else {
            SaxionApp.printLine("A passenger can't enter the train. Now compartment is full.");
        }
    }

    public void leave(){
        SaxionApp.printLine("A passenger leave the train...");
        if (numberOfSeatsInUse == 0){
            SaxionApp.printLine("There is no-one to leave.");
        } else {
            numberOfSeatsInUse --;
            SaxionApp.printLine("There are now " + numberOfSeatsInUse + " in use");
        }
    }

    //methods
    //setters
    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public void setCompartmentClass(int compartmentClass) {
        this.compartmentClass = compartmentClass;
    }

    public void setNumberOfSeatsInUse(int numberOfSeatsInUse) {
        this.numberOfSeatsInUse = numberOfSeatsInUse;
    }

    //getters
    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public int getCompartmentClass() {
        return compartmentClass;
    }

    public int getNumberOfSeatsInUse() {
        return numberOfSeatsInUse;
    }
}
