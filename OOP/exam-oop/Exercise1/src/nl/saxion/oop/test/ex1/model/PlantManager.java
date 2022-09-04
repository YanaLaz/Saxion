package nl.saxion.oop.test.ex1.model;

import java.util.ArrayList;

/**
 * Class for keeping track of all plants in a house.
 */
public class PlantManager {
    private static ArrayList<Plant> plantList;

    public PlantManager() {
        this.plantList = new ArrayList<>();
    }

    //PlantAll pall = new PlantAll();

    /**
     * Add a plant to the system.
     *
     * This method should throw a custom exception when: type is empty, location is empty or when the interval is < 1.
     *
     * @param type Name of the plants type.
     * @param interval After how many days does the plant need to be watered? (e.g. interval=5 means, every 5 days).
     * @return The unique id of the plant.
     */
    public int addPlant(String type, int interval) throws PlantException {
        if (type.isEmpty()){
            throw new PlantException("The type is empty!");
        } else if (interval < 1) {
            throw new PlantException("The interval < 1!");
        } else {
            Plant plant1 = new Plant(type, interval);
            plantList.add(plant1);
            System.out.println(plantList.size());
            return plant1.getId();
        }
    }

    /**
     * Give an overview of all plants in the system. Clearly indicate which plants need water today!
     * @return All plants in String format.
     */
    public String toString() {
        System.out.println(plantList.size());
        String res = "All plants in the system:" + "\n";
        for (Plant p:plantList){
            //System.out.println(p);
            res += p + "\n";
        }
        return res;
    }

    /**
     * Try to water a plant. If the plant was not found or the plant does not need water today, throw an exception.
     * @param id Unique id of the plant we want to water.
     */
    public void waterPlant(int id) throws PlantException {
        if (plantList.get(id-1).getInterval() - plantList.get(id-1).getNmOfNotWatered() != 0){
            throw new PlantException("The water day is not today!");
        } else if (plantList.get(id-1).getInterval() - plantList.get(id-1).getNmOfNotWatered() == 0) {
            plantList.get(id-1).setNmOfNotWatered(0);
            System.out.println("Plant with id "+ id +" has been watered.");
        } else{
            throw new PlantException("Plant not found");
        }
    }

    /**
     * Go to the next day in the system.
     * Should throw an exception when there are plants that needed water today, but haven't been watered yet.
     */
    public void increaseDay() throws PlantException {
        for (Plant p:plantList){
            if (p.getInterval() - p.getNmOfNotWatered() == 0){
                throw new PlantException("Tha plant with id "+ p.getId() + "shoud be watered today!");
            }
        }

        for (Plant p:plantList){
            p.setNmOfNotWatered(p.getNmOfNotWatered()+1);
        }
        System.out.println("All plants have been watered, we can proceed to the next day.");
    }
}
