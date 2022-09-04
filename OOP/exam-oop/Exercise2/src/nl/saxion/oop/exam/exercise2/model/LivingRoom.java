package nl.saxion.oop.exam.exercise2.model;

public class LivingRoom extends Room{
    private int numberOfWindows;


    public LivingRoom(int sizeM2, int numberOfWindows) {
        super(sizeM2);
        this.numberOfWindows = numberOfWindows;
        this.price = sizeM2*(1000+(numberOfWindows*250));
    }

    @Override
    public String toString(){
        return "- Living room (number of windows: " + getNumberOfWindows() + ") --- " + getSizeM2() + "m2, price: €" + getPrice();
    }

    public int getNumberOfWindows() {
        return numberOfWindows;
    }
}
