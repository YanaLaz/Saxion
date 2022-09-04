package nl.saxion.oop.test.ex1;

import nl.saxion.oop.test.ex1.model.Plant;
import nl.saxion.oop.test.ex1.model.PlantException;
import nl.saxion.oop.test.ex1.model.PlantManager;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        PlantManager plantManager = new PlantManager();

        int choice;
        do {
            System.out.println("1. Add plant");
            System.out.println("2. Show overview");
            System.out.println("3. Water plant");
            System.out.println("4. Go to next day");
            System.out.println("0. Exit");
            System.out.println();
            choice = readInt("Please select an entry from the menu: ");
            System.out.println();

            if (choice == 1) {
                // Add a new plant
                String type = readString("Please enter a plant type: ");
                int interval = readInt("Please enter after how many days the plant should get water: ");

                // TODO: Add plant to the system. Don't forget to handle possible exceptions here.
                //PlantManager pm = new PlantManager();
                try {
                    int idd = plantManager.addPlant(type, interval);
                    System.out.println("Added plant with id " + idd);
                } catch (PlantException e) {
                    System.err.println(e);
                }


            } else if (choice == 2) {
                // Show overview of all plants in the system
                System.out.println(plantManager);

            } else if (choice == 3) {
                // Water plant
                int id = readInt("Please enter the id of the plant you want to water: ");

                // TODO: Water the specific plant. Please handle possible exceptions here
                try {
                    plantManager.waterPlant(id);
                } catch (PlantException e) {
                    System.err.println(e);
                }

            } else if (choice == 4) {
                // TODO: Go to the next day. Please handle possible exceptions here
                try {
                    plantManager.increaseDay();
                } catch (PlantException e) {
                    System.err.println(e);
                }

            } else if (choice == 0) {
                System.out.println("Goodbye");
            }

            System.out.println();
        } while (choice != 0);
    }

    /**
     * Helper method that prints out a message before returning the response (String).
     *
     * @return a String value as returned by the user.
     */
    private static String readString(String msg) {
        System.out.print(msg);
        return readString();
    }

    /**
     * Helper method that returns a String, read by Scanner.
     *
     * @return a String value as returned by the user.
     */
    private static String readString() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    /**
     * Helper method that prints out a message before returning the response (int).
     *
     * @return the parsed value (as integer) read from user.
     */
    private static int readInt(String msg) {
        System.out.print(msg);
        return readInt();
    }

    /**
     * Helper method that returns an integer, based on the readString() method.
     *
     * @return the parsed value (as integer) read from user.
     */
    private static int readInt() {
        while(true) {
            try {
                return Integer.parseInt(readString());
            } catch (NumberFormatException nfe) {
                System.err.print("That is not a valid value! Try again: ");
            }
        }
    }
}
