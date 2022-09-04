# Water your plants
For houseplants to survive in your home, it is important to water them regularly. It is however quite common to forget this,
so in this assignment, you are going to build a system that allows you to keep track of each plant in your house needs and provide
a reminder whenever they need to be watered.

The system consists of the following functionalities:
1. Adding a new houseplant to the system.
2. Showing an overview of all houseplants in the system.
3. Registering that a plant has just been watered.
4. Make the system go to the next day (here we simulate that a day is finished and that we proceed to the next day).

We have implemented almost all user interaction for you in the `Application` class. For this purpose, `//TODO` blocks
have been provided at all locations where code needs to be added. We have also provided the public interface of the `PlantManager`.
It is not necessary to change the parameters or the return type of these methods. However, if you use your own 
Exceptions, you have to add them to the method signature and of course give your own implementation of the given methods.
Furthermore, you may add helper methods to the class, where you think this is necessary.

It is important that the application can handle errors properly, so use your own implemented Exception. If you are unable,
to create one, use pre-existing exceptions that you used during this course. Make sure that every Exception is properly caught
and leads to user-friendly response (by printing an error for example).

**Obviously it is implied that you should use the techniques learned in this course!**

## Adding plants to the system
For each plant, we need to store the type of plant, the number of days the plant has not been watered and the number of days the plant can go without water. 
In addition, every plant that is created is automatically assigned a unique id, so that we can easily find the plant again. All attributes must be retrievable from 
outside the class. Once a plant is created, its type and id cannot change anymore. Assume that the plant has just been watered when you add it to the system.

Note that you do not need instances of `LocalDate` for this task. It is sufficient to just use integers to keep track of how 
many days have passed since the plant was last watered and how many days the plant can go without water.

Make sure the class is encapsulated properly and implement a `toString()` method that returns the information of the 
plant. Take care of additional public methods, so that the plant can be watered, etc.

## Menu option 1 - Adding a plant
When you want to add a plant, the system will prompt you
- to enter the type of the houseplant
- to enter how many days the plant can go without water (we call this the interval. Interval=5 means, every 5 days this plant needs water).

Implement the method `addPlant(String type, int interval)`.

**Error checking:**
- Make sure that type cannot be empty.
- Interval must be a positive number (at least 1).

If the given parameters are correct, a plant will be saved in the `PlantManager`. Each plant is **automatically assigned a unique id**, 
so that we can easily find it inside the system.

```text
Please enter a plant type: Snake plant
Please enter after how many days the plant should get water: 5

Added plant of type Snake plant with id 1
```
(Please note that you have to add the last sentence above to your code. What a user-friendly error message looks like is up to you.)

## Menu option 2 - Show all plants in the system
With this menu option, the system will give an overview of all plants in the system. The plants that need watering today will be clearly marked!
The output should look like this:

```text
All plants in the system:
- Plant Snake plant (id: 1) needs water in 2 days.
- Plant Peace Lily (id: 2) needs water in 11 days.
- Plant Parlor Palm (id: 3) needs water in 4 days.
- Plant Monstera (id: 4) needs water TODAY!
```

## Menu option 3 - Watering the plants
With this menu option, the system will ask you to fill in the id of a plant. Then, this plant will be watered.
Implement the method: `public void waterPlant(int id)`.

You will have to throw your own Exception when:
- The id of the plant is not in the system.
- The plant does not need water yet.

```text
Please enter the id of the plant you want to water: 4
Plant with id 4 has been watered.
```

(You may decide what is printed when an error occurs, as long as it is displayed in a user-friendly manner).

## Menu option 4 - Proceed to the next day
With this menu option, we simulate going to the next day.
We can only proceed to the next day if all plants that need water (this day) have been watered.
If this is not the case, an Exception is thrown and the user is returned to the previous menu.

If all plants have had water, the system moves to the next day.

```text
All plants have been watered, we can proceed to the next day.
```