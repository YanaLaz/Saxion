package nl.saxion.oop.exam.exercise2.model;

public class BedRoom extends Room{
    private boolean fitsDoubleBed;

    public BedRoom(int sizeM2, boolean fitsDoubleBed) {
        super(sizeM2);
        this.fitsDoubleBed = fitsDoubleBed;
        if (this.fitsDoubleBed){
            this.price = 975*sizeM2;
        } else {
            this.price = 800*sizeM2;
        }

    }

    @Override
    public String toString(){
        return "- Bedroom (double bed: " + isFitsDoubleBed() + ") --- " + getSizeM2() + "m2, price: €" + getPrice();
    }

    public boolean isFitsDoubleBed() {
        return fitsDoubleBed;
    }
}
