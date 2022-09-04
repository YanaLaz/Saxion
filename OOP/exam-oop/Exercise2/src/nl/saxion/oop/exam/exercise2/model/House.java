package nl.saxion.oop.exam.exercise2.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class House {
    private String address;
    private String zipcode;
    private String city;
    private int price;
    private ArrayList<Room> roomList;
    private int sizeM2;


    public House(String address, String zipcode, String city, int price) {
        this.address = address;
        this.zipcode = zipcode;
        this.city = city;
        this.price = price;
        this.roomList = new ArrayList<>();
        this.sizeM2 = 0;
    }

    public void setNewRoom(Room room) {
        roomList.add(room);
    }


    @Override
    public String toString(){
        String res = "House at + " + getAddress() + getZipcode() + getCity() +" with get "+ getSizeM2() + " m2 and total price: €" + getPrice() + " has rooms:";

        Collections.sort(roomList);
        for (Room r:roomList){
            res += "\n"+r;
        }
        return res;
    }

    public void setNewPriceSize(){
        int totalPrice = this.price;
        int newSize = this.sizeM2;
        for (Room r:roomList){
            totalPrice += r.getPrice();
            newSize += r.sizeM2;
        }
        this.price = totalPrice;
        this.sizeM2 = newSize;
    }

    public int getSizeM2() {
        return sizeM2;
    }

    public String getAddress() {
        return address;
    }

    public String getZipcode() {
        return zipcode;
    }

    public String getCity() {
        return city;
    }

    public int getPrice() {
        return price;
    }

    public boolean checkBathAndBed(int bathNum, int bedNum) {
        int nmBath = 0;
        int nmBed = 0;
        for (Room r : roomList) {
            if (r instanceof BathRoom) {
                nmBath++;
            } else if (r instanceof BedRoom) {
                nmBed++;
            }
        }
        if (nmBath >= bathNum && nmBed >= bedNum) {
            return true;
        } else {
            return false;
        }
    }
}
