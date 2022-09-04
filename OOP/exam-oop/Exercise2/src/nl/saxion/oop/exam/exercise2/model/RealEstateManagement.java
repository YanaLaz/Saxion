package nl.saxion.oop.exam.exercise2.model;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class RealEstateManagement {
    public RealEstateManagement(String fileName) {
        ArrayList<House> houseList = new ArrayList<>();
        ArrayList<LivingRoom> lrList = new ArrayList<>();
        int lineNo = 0;
        try {
            Scanner scanner = new Scanner(new File(fileName));
            int houseNo = -1;
            while (scanner.hasNextLine()) {
                ++lineNo;
                String[] fields = scanner.nextLine().split(",");
                switch (fields[0]) {
                    case "HOUSE": {
                        houseNo++;
                        String address = fields[1];
                        String zipCode = fields[2];
                        String city = fields[3];
                        int basePrice = Integer.parseInt(fields[4]);

                        // TODO: Create new house
                        House house1 = new House(address, zipCode, city, basePrice);
                        houseList.add(house1);

                        break;
                    }
                    case "LIVINGROOM": {
                        int sizeM2 = Integer.parseInt(fields[1]);
                        int numberOfWindows = Integer.parseInt(fields[2]);

                        // TODO: Add room
                        LivingRoom lr = new LivingRoom(sizeM2,numberOfWindows);
                        houseList.get(houseNo).setNewRoom(lr);
                        lrList.add(lr);

                        break;
                    }
                    case "BEDROOM": {
                        int sizeM2 = Integer.parseInt(fields[1]);
                        boolean fitsDoubleBed = Boolean.parseBoolean(fields[2]);

                        // TODO: Add room
                        BedRoom br = new BedRoom(sizeM2, fitsDoubleBed);
                        houseList.get(houseNo).setNewRoom(br);

                        break;
                    }
                    case "BATHROOM": {
                        int sizeM2 = Integer.parseInt(fields[1]);
                        boolean hasToilet = Boolean.parseBoolean(fields[2]);
                        boolean hasShower = Boolean.parseBoolean(fields[3]);

                        // TODO: Add room
                        BathRoom bathr = new BathRoom(sizeM2, hasToilet, hasShower);
                        houseList.get(houseNo).setNewRoom(bathr);

                        break;
                    }
                    //bathr
                }
            }
        } catch (Exception e) {
            System.err.println("CSV read error in line " + lineNo + ": " + e.getMessage());
            System.exit(-1);
        }
//        for (int i = 0; i < houseList.size(); i++) {
//            System.out.println();
//        }

        int minBath = 1;
        int minBed = 1;

        System.out.println("Find houses with at least " + minBath+" bathroom and at least of " + minBed +" bedrooms:");
        for (House h:houseList){
            h.setNewPriceSize();
            if (h.checkBathAndBed(minBath, minBed)){
                System.out.println(h);
                System.out.println();
            }
        }

    }

}
