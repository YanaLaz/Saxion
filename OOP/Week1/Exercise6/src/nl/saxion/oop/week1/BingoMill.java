package nl.saxion.oop.week1;

import nl.saxion.app.SaxionApp;

import java.util.*;

public class BingoMill {
    private ArrayList<Integer> integerArrayList = new ArrayList<Integer>();
    public BingoMill() {
        reset();
    }

    //private int[] array = [1,3,2,4,5,75];
    List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,64,65,66,67,68,69,70,71,72,73,74,75);

    //Util.shuffle(array);


    private static Random random;


    public int getNumberOfBallsRemaining(){
        return list.size();
    }

    public int draw(){
        Random rand = new Random();
        int randomElement = list.get(rand.nextInt(list.size()));
        //array = ArrayUtils.removeElement(array, array[rnd]);

        return randomElement;
    }

    public void reset() {
        for (int i = 1; i <= 75; i++) {
            integerArrayList.add(i);
        }
        this.shuffle();
    }

    private void shuffle() {
        for (int i = 0; i < 200; i++) {
            int index1 = SaxionApp.getRandomValueBetween(0, 75);
            int index2;
            do {
                index2 = SaxionApp.getRandomValueBetween(0, 75);
            } while (index1 == index2);
            Collections.swap(integerArrayList, index1, index2);
        }
    }
}
