# Planten water geven
Om kamerplanten in huis te laten overleven is het belangrijk om ze zo nu en dan water te geven.
Je gaat bij deze opdracht een systeem bouwen waarmee je voor elke kamerplant in je huis kunt bijhouden wanneer je ze water moet geven.
Daarnaast is er een optie om te registreren dat een plant water heeft gehad.

Het systeem bestaat uit de volgende functionaliteiten:
1. Het toevoegen van een nieuwe kamerplant aan het systeem.
2. Een overzicht tonen van alle kamerplanten in het systeem.
3. Registreren dat een plant net water heeft gekregen.
4. Het systeem naar de volgende dag laten gaan (hiermee simuleren we dat een dag afgelopen is en we naar de volgende dag gaan).

Wij hebben vrijwel alle gebruikersinteractie voor je geïmplementeerd in de `Application` klasse. Hiervoor zijn `//TODO` blokken
opgenomen op de locaties waar jij nog code moet toevoegen. Ook hebben wij de publieke interface van de `PlantManager` 
al gegeven. Het is niet nodig dat je de parameters of het return type aanpast van deze methoden. Wel moet je, indien je gebruik maakt van je eigen Exceptions, deze toevoegen aan de methode en natuurlijk een eigen invulling geven aan de methoden.
Verder mag je de klasse uitbreiden met hulpmethoden, waar jij dit nodig acht.

Het is belangrijk dat er foutcontrole plaats vindt bij deze applicatie. Maak daarbij gebruik van een eigen geïmplementeerde Exception. Mocht dit niet lukken, gebruik dan de bestaande Exceptions die je geleerd hebt tijdens dit vak. Zorg ervoor dat iedere Exception op een nette manier wordt afgehandeld en gebruikersvriendelijk wordt geprint.

**Uiteraard maak je voor deze opdracht gebruik van de geleerde technieken binnen de module.**

## Planten opslaan
Van iedere plant moeten we opslaan wat het type van de plant is, hoeveel dagen de plant al geen water heeft gehad en om de hoeveel dagen de plant water nodig heeft.
Daarnaast krijgt iedere plant die aangemaakt wordt automatisch een uniek id toegewezen, zodat we de plant eenvoudig kunnen terugvinden. Zodra een plant toegevoegd is kan zijn id en zijn type niet meer aangepast worden.
Ga ervan uit dat de plant net water heeft gehad op het moment dat je de plant toevoegt aan het systeem.

We hebben bij deze opdracht geen `LocalDate` nodig. Het volstaat om met integers bij te houden hoeveel dagen er verstreken zijn en om de hoeveel dagen een plant water nodig heeft.

Zorg voor goede encapsulatie van de klasse en implementeer een `toString()`-methode die de informatie van de plant teruggeeft en over hoeveel dagen de plant weer water nodig heeft.
Zorg zelf voor aanvullende publieke methoden, zodat de plant water gegeven kan worden, etc.

## Menuoptie 1 - Het toevoegen van een plant
Het systeem vraagt je:
- om het type van de kamerplant in te voeren
- om de hoeveel dagen de plant water nodig heeft (dit noemen we het interval. Interval=5 betekent, elke 5 dagen heeft deze plant water nodig).

Implementeer de methode `addPlant(String type, int interval)`.

**Foutcontrole:**
- Zorg ervoor dat type niet leeg kan zijn.
- Interval moet een positief getal zijn (minstens 1).

Als de meegegeven parameters correct zijn dan zal er een plant worden opgeslagen in de `PlantManager`. Iedere plant krijgt **automatisch een uniek id toegewezen**, zodat we deze weer terug kunnen vinden in het systeem.

```text
Please enter a plant type: Snake plant
Please enter after how many days the plant should get water: 5

Added plant of type Snake plant with id 1
```
(Let op dat je de laatste zin nog moet toevoegen. Hoe een eventuele gebruikersvriendelijke foutmelding eruitziet, mag je zelf bepalen.)

## Menuoptie 2 - Het tonen van alle planten in het systeem
Bij deze menuoptie zal het systeem een overzicht geven van alle planten in het systeem. Bij de planten die vandaag water nodig hebben wordt dit duidelijk vermeld!
De uitvoer moet er als volgt uit zien:

```text
All plants in the system:
- Plant Snake plant (id: 1) needs water in 2 days.
- Plant Peace Lily (id: 2) needs water in 11 days.
- Plant Parlor Palm (id: 3) needs water in 4 days.
- Plant Monstera (id: 4) needs water TODAY!.
```

## Menuoptie 3 - Planten water geven
Bij deze menuoptie zal het systeem je vragen om het id van een plant in te vullen. Vervolgens zal deze plant water gegeven worden.
Implementeer hiervoor de methode: `public void waterPlant(int id)`.

Je zult een eigen Exception moeten gooien wanneer:
- Het id van de plant niet in het systeem voorkomt.
- De plant nog geen water nodig heeft.

```text
Please enter the id of the plant you want to water: 4
Plant with id 4 has been watered.
```

(Je mag zelf bepalen wat er geprint wordt als er een fout optreedt, zolang het maar gebruikersvriendelijk getoond wordt).

## Menuoptie 4 - Naar de volgende dag
Bij deze menuoptie gaan we simuleren dat we naar de volgende dag gaan.
We kunnen alleen naar de volgende dag gaan als alle planten die vandaag water nodig hebben, ook water gehad hebben.
Mocht dit niet het geval zijn, dan wordt er een eigen Exception gegooid en blijven we bij de huidige dag.

Indien alle planten water hebben gehad, dan gaat het systeem naar de volgende dag.

```text
All plants have been watered, we will proceed to the next day.
```