package nl.saxion.oop.exam.exercise2;

import nl.saxion.app.SaxionApp;
import nl.saxion.oop.exam.exercise2.model.RealEstateManagement;

public class ApplicationWithSaxionApp implements Runnable {

    public static void main(String[] args){
        SaxionApp.start(new ApplicationWithSaxionApp(), 1024, 768);
    }

    @Override
    public void run() {
        RealEstateManagement administration = new RealEstateManagement("Exercise2/houses.csv");

        // TODO: Test the functionality of your system.
    }
}
