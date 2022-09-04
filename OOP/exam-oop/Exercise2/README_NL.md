# Makelaarskantoor
Een makelaarskantoor heeft een systeem nodig om per woning te kunnen bepalen wat de waarde van de woning is.
De berekening daarvoor is complex, omdat de prijs van een woning afhankelijk is van verschillende factoren.

Het makelaarskantoor houdt een lijst bij van alle huizen in hun bestand. Van ieder huis wordt het adres, de postcode, de woonplaats en een basisprijs opgeslagen.

Daarnaast wordt van iedere woning een overzicht bijgehouden van de kamers in de woning en de grootte (oppervlakte in m<sup>2</sup>) van deze kamers.
In het systeem wordt gebruik gemaakt van drie verschillende kamertypes: Woonkamer, Slaapkamer en Badkamer. Andere types kamers zijn niet relevant voor deze opdracht, maar zouden in de toekomst wel eenvoudig toegevoegd moeten kunnen worden.

Om de totale verkoopwaarde van een huis te kunnen bepalen, hebben we de basiswaarde van het huis nodig. Daar tellen we bij op de waarde van iedere kamer, om tot een totale prijs te komen.
Per type kamer is er een andere manier om de kamerwaarde te berekenen. Wat iedere berekening gemeenschappelijk heeft, is dat in ieder geval de oppervlakte van de kamer (het aantal m<sup>2</sup>) gebruikt wordt.

Per kamer type zal er nu uitgelegd worden wat er opgeslagen moet worden en hoe de prijs van de kamer berekend wordt:

## Woonkamer
Bij woonkamers wordt opgeslagen hoeveel ramen er in de woonkamer aanwezig zijn.

De vierkantemeterprijs van een woonkamer wordt mede bepaald door het aantal ramen. De minimale prijs per m<sup>2</sup> is 1000 euro.
Per raam wordt er 250 euro bij de vierkantemeterprijs opgeteld.

Stel dat een woonkamer 3 ramen heeft, dan is de prijs per m<sup>2</sup> dus: 1750 euro.
Als de oppervlakte van de kamer 20 m<sup>2</sup> is, dan is de totale prijs voor de kamer: 20 * €1750 = €35.000.

## Slaapkamer
Bij slaapkamers wordt er bijgehouden of er een tweepersoonsbed in de kamer past.

De vierkantemeterprijs is afhankelijk van of er een tweepersoonsbed in de slaapkamer past of niet:

| Voorziening                           | Prijs per m<sup>2</sup> |
|---------------------------------------|:-----------------------:|
| Tweepersoonsbed past NIET in de kamer |          € 800          |
| Tweepersoonsbed past in de kamer      |          € 975          |

**Voorbeeld**
Je hebt een slaapkamer van 15m<sup>2</sup> waar GEEN tweepersoonsbed in past. Dan is de waarde: €800 * 15 = €12000.

## Badkamer
Bij een badkamer wordt er bijgehouden welke van de volgende voorzieningen er in de kamer aanwezig zijn: toilet en/of douche.

Een badkamer heeft een vaste prijs per m<sup>2</sup> van € 900. 

Daarnaast wordt er een bedrag bij de totale kamerprijs opgeteld als de volgende voorzieningen aanwezig zijn:

| Voorziening | Bedrag |
|-------------|:------:|
| Toilet      | € 1000 |
| Douche      | € 2000 |

**Voorbeeld**
Stel je hebt een badkamer van 10m<sup>2</sup> met een toilet en een douche, dan is de waarde: €900 * 10 + €1000 + €2000 = €12000.

## De applicatie
De woningen die het makelaarskantoor beheert zijn opgeslagen in het CSV-bestand `houses.csv`. De code om deze CSV uit te lezen is deels gegeven.
Er zal altijd eerst een regel van een huis worden uitgelezen. Daarna volgen de kamers in dat huis, tot dat je een nieuwe huisregel tegenkomt. Je mag er vanuit gaan dat er geen fouten zitten in het CSV-bestand.

De applicatie heeft zoekfunctionaliteit nodig, waarbij je aangeeft hoeveel badkamers en hoeveel slaapkamers je MINIMAAL nodig hebt (bijvoorbeeld een huis met minimaal 2 badkamers en minimaal 3 slaapkamers).
Vervolgens zal de applicatie een overzicht printen van de gevonden huizen.

Zorg ervoor dat de huizen op **<u>exact</u>** dezelfde manier worden geprint als in het voorbeeld.

Het is hierbij belangrijk dat:
- De totale prijs van ieder huis getoond wordt (basisprijs + som van alle kamerprijzen).
- De totale oppervlakte van ieder huis getoond wordt.
- Dat er per huis een kameroverzicht wordt getoond:
  - Per kamer de details van de kamer en de prijs van de kamer.
  - De kamers moeten hierbij gesorteerd worden in volgorde van oppervlakte (kleinste eerst). Als kamers hetzelfde oppervlakte hebben, sorteer deze dan vervolgens op prijs per kamer (laagste eerst).

Maak goed gebruik van de kennis van inheritance en polymorfisme, zoals in de lessen is besproken. Houd daarbij rekening met 
de uitbreidbaarheid van je ontwerp; wat als er nog een nieuw kamertype gaat worden toegevoegd? Moet dan de hele 
applicatie worden aangepast? Zorg dus ook dat je zo min mogelijk "dubbele"/vergelijkbare code schrijft.

We hebben twee `Application` klassen aangeleverd. Je mag zelf kiezen of je gebruik maakt van de variant met of de variant zonder de SaxionApp.

## Resultaat
Als de applicatie juist is gemaakt dan levert het de volgende uitvoer op als je zoekt op huizen met minimaal 1 slaapkamer en minimaal 1 badkamer:

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