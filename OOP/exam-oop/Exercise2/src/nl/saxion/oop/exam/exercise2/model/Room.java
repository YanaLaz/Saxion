package nl.saxion.oop.exam.exercise2.model;

public abstract class Room implements Comparable<Room>{
    protected int sizeM2;
    protected int price;

    public Room(int sizeM2) {
        this.sizeM2 = sizeM2;
    }

    @Override
    public int compareTo(Room o) {
        int ssize = Integer.compare(sizeM2, o.sizeM2);

        if (ssize == 0){
            return Integer.compare(price, o.price);
        } else return ssize;
    }

    public int getSizeM2() {
        return sizeM2;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString(){
        return "";
    }
}
