package nl.saxion.oop.exam.exercise2.model;

public class BathRoom extends Room{
    private boolean hasToilet;
    private boolean hasShower;

    public BathRoom(int sizeM2, boolean hasToilet, boolean hasShower) {
        super(sizeM2);
        this.hasToilet = hasToilet;
        this.hasShower = hasShower;
        if (hasToilet){
            if (hasShower){
                this.price = 900*sizeM2 + 1000 + 2000;
            } else {
                this.price = 900*sizeM2 + 1000;
            }
        } else if (hasShower){
            this.price = 900*sizeM2 + 2000;
        } else {
            this.price = 900*sizeM2;
        }
    }

    @Override
    public String toString(){
        return "- Bathroom (toilet: " + isHasToilet() + ", shower:  " + isHasToilet() +") --- " + getSizeM2() + "m2, price: €" + getPrice();
    }

    public boolean isHasToilet() {
        return hasToilet;
    }

    public boolean isHasShower() {
        return hasShower;
    }
}
