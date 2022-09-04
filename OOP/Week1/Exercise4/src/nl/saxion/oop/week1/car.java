package nl.saxion.oop.week1;

import nl.saxion.app.SaxionApp;

public class car {
    private String brand, licensePlate;
    private int tank_size, tankPosition, fuelConsumption, mileage, currentTankPosition;

    public car(String brand, String licensePlate, int tank_size, int tankPosition, int fuelConsumption, int mileage) {
        this.brand = brand;
        this.licensePlate = licensePlate;
        this.tank_size = tank_size;
        this.tankPosition = tankPosition;
        this.fuelConsumption = fuelConsumption;
        this.currentTankPosition = tankPosition;
        this.mileage = mileage;
    }


    public void drive(int km){
        if (getFuelConsumption() * getCurrentTankPosition() < km){
            SaxionApp.printLine("The car cant drive that far!");
        } else {
            setMileage(getMileage()+km);
            setCurrentTankPosition(getCurrentTankPosition() - km / getFuelConsumption());
            SaxionApp.printLine("Driving " + km + " km. Mileage is now " + getMileage() + " km \nand there is " + getCurrentTankPosition() + " litres of fuel left");
        }
    }

    public void fuel(int litres){
        if ((litres >= getTank_size()) || (litres+getCurrentTankPosition() >= getTank_size())){
            SaxionApp.printLine("You cannot overfill the tank!");
        } else {
            setCurrentTankPosition(getCurrentTankPosition()+litres);
            SaxionApp.printLine("Now tank position is " + getCurrentTankPosition());
        }
    }

    @Override public String toString() {
        return getBrand() + " ( " + getLicensePlate() + ") " + getCurrentTankPosition() + " litres (" + (double)(getCurrentTankPosition() / (double)getTank_size()) * 100 + "%) " + "Mileage: " + getMileage() + " km.";
    }


    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public void setTank_size(int tank_size) {
        this.tank_size = tank_size;
    }

    public void setTankPosition(int tankPosition) {
        this.tankPosition = tankPosition;
    }

    public void setFuelConsumption(int fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public void setCurrentTankPosition(int currentTankPosition) {
        this.currentTankPosition = currentTankPosition;
    }

    public String getBrand() {
        return brand;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public int getTank_size() {
        return tank_size;
    }

    public int getTankPosition() {
        return tankPosition;
    }

    public int getFuelConsumption() {
        return fuelConsumption;
    }

    public int getMileage() {
        return mileage;
    }

    public int getCurrentTankPosition() {
        return currentTankPosition;
    }
}
