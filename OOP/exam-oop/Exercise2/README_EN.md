# Real estate agency
A real estate agency needs a system to determine the value of each property.
This value is calculated based on different aspects and characteristics of the house, like how much living space there is, and how luxurious the bathroom is,
etc. 

The real estate agency keeps a list of all houses in their portfolio. Each house is stored with an address, zipcode, city and
a base price.

Also, the number of rooms and their sizes (surface in m<sup>2</sup>) are stored. In total, the system knows three different
room types: Living room, Bedroom abd Bathroom. Other room types are ignored for this assignment, but could be added
in the near future, so keep this in mind while designing the system.

To calculate the resale value of a house, the base price is used. To this number, the value of each known room is added
to get the full resale price. The added value depends on the type of room and their characteristics. You may however assume
that the surface (in m<sup>2</sup>) is important for all different types of rooms.

Next we'll explain how the value of each room is calculated.

## Living room
For a living room, the number of windows should be stored. Each window adds a certain value to the price of the surface, so more
windows means the value per m<sup>2</sup> goes up.

In this case, the base price of a living room is 1000 euros per m<sup>2</sup>, each window increases this value with 250
euros per m<sup>2</sup>.

Suppose a living room has 3 windows, then the price per m<sup>2</sup> is 1750 euro. If then the surface of the living
room is 20 m<sup>2</sup>, the total added value for this living room is 20 * 1750 euro = 35.000 euro.

## Bedroom
For each bedroom is stored if the room fits a double bed.

The price per m<sup>2</sup> depends on whether a double bed fits in the room, giving a lower value if it does not
fit. You can use the table below to see what price you should use:

| Double bed?                     | Price per m<sup>2</sup> |
|---------------------------------|:-----------------------:|
| Double bed does NOT fit in room |          € 800          |
| Double bed fits in room         |          € 975          |

**Example**
Suppose you have a bedroom of 15m<sup>2</sup> where NO double bed can be put in. The
value would be calculated as €800 (because no double bed) * 15 = €12000.

## Bathroom
For a bathroom, we store if one of the following facilities is present: toilet and/or shower.

Each bathroom has a default value of € 900 per m<sup>2</sup>.

In addition, an amount will be added to the total room price if the following facilities are present:

| Present? | Added value |
|----------|:-----------:|
| Toilet   |   € 1000    |
| Shower   |   € 2000    |

**Example**
Suppose you have a bathroom of 10m<sup>2</sup> with a shower and toilet, the total value would be €900 * 10 + €1000 + €2000 = €12000.

## The application
The houses managed by the real estate agency are stored in a CSV file `houses.csv`. The code to read this CSV is already partially provided to help you out.
A rule of a house will always be read out first. Then the rooms in that house will follow, until you find a new house rule. You may assume that there are no errors in the CSV file.

The application needs search functionality, where you indicate how many bathrooms and how many bedrooms you MINIMALLY need (e.g. a house with at least 2 bathrooms and at least 3 bedrooms).
Then the application will print an overview of the houses found.

Make sure the houses are printed in **<u>exactly</u>** the same way as in the example.

It is important here that:
- The total price of each house is shown (base price + sum of all room prices).
- The total area of each house is shown.
- That a room overview is shown for each house:
    - Per room the details of the room and the price of the room.
    - The rooms should be sorted in order of size (showing the smallest rooms first). If rooms have the same surface area, then sort them by price per room (lowest value first).

Make good use of the knowledge of inheritance and polymorphism, as discussed in the classes. In doing so, take into account
the extensibility of your design; what if a new room type is going to be added? Do we need to change the entire application? So also make sure you write as 
little "duplicate"/comparable code as possible.

We have provided two `Application` classes. You may choose whether you want to use the variant with or the variant without the SaxionApp.

## Result
If the application is created correctly, it will give the following output if you search for houses with at least one bedroom and at least one bathroom:

```text
Find houses with at least 1 bathroom and at least of 1 bedrooms:
House at Enschedesestraat 22, 7521AB Enschede with 25 m2 and total price: €129800.00 has rooms: 
- Bathroom (toilet: true, shower: true) --- 5 m2, price: €7500.00
- Bedroom (double bed: false) --- 6 m2, price: €4800.00
- Living room (number of windows: 1) --- 14 m2, price: €17500.00

House at Hengelosestraat 91, 7204ST Hengelo with 60 m2 and total price: €200050.00 has rooms: 
- Bathroom (toilet: true, shower: false) --- 2 m2, price: €2800.00
- Bathroom (toilet: true, shower: true) --- 6 m2, price: €8400.00
- Bedroom (double bed: false) --- 7 m2, price: €5600.00
- Bedroom (double bed: true) --- 20 m2, price: €19500.00
- Living room (number of windows: 3) --- 25 m2, price: €43750.00

House at Adelaarstraat 6a, 3606DD Deventer with 183 m2 and total price: €402850.00 has rooms: 
- Bathroom (toilet: true, shower: false) --- 3 m2, price: €3700.00
- Bedroom (double bed: false) --- 12 m2, price: €9600.00
- Bedroom (double bed: true) --- 12 m2, price: €11700.00
- Bathroom (toilet: true, shower: true) --- 12 m2, price: €13800.00
- Bedroom (double bed: false) --- 22 m2, price: €17600.00
- Bedroom (double bed: true) --- 22 m2, price: €21450.00
- Living room (number of windows: 3) --- 100 m2, price: €175000.00
```