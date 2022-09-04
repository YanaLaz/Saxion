package nl.saxion.oop.test.ex1.model;

import java.util.ArrayList;

public class PlantAll {
    public ArrayList<Plant> plantList;

    public PlantAll() {
        this.plantList = new ArrayList<>();
    }

    public String showAll(){
        String res = "All plants in the system:" + "\n";
        System.out.println(plantList.size());
        for (Plant p:plantList){

            System.out.println(p);
            res += p + "\n";
        }
        return res;

    }

    public void addPlantToList(Plant p){
        plantList.add(p);
    }
}
