## Testliste – Rumeventyr Exception Rescue Mission

> Status: Færdig

---

### Implementeret og testet (nuværende status)

1. Program starter korrekt og spørger om kaptajn + skibsnavn  
2. STATUS vises med korrekte startværdier  
3. Menuinput håndterer ugyldigt input uden crash (`NumberFormatException` håndteres i `ConsoleIO`)

---

### Event 1 – Rumstorm

- Begge valg virker
- Fuel og integrity ændres korrekt
- Tilfældig skade via random (`Math.random`)
- Shield påvirker skade
- Loglinje oprettes
- `checkCriticalStatus` kaldes efter event

---

### Event 2 – Handel og Shield

- Trade validerer input korrekt
- `IllegalArgumentException` ved 0 eller negativ handel
- `InvalidTradeException` ved for mange reservedele
- Shield level 1 kan købes (kræver nok reservedele)
- Logging af trade og shield
- Fejl håndteres uden crash (brugeren får besked og spillet fortsætter)

---

### Event 3 – Motor (Engine)

- `try/catch/finally` bruges, og `finally` printer altid: "Forsøger at genstarte..."
- Genstart har ca. 40% chance for succes
- Ved fejl: integrity falder med 15
- 2 fejl i træk giver `CriticalStatusException`
- Repair kit kan kun bruges én gang, giver +20 integrity og logges
- Hvis spilleren prøver at bruge repair kit igen, håndteres det pænt (ingen exception)
- Logging af motorforsøg og repair kit

---

### Game flow

- `checkCriticalStatus` kaldes efter hvert event
- GAME OVER håndteres via checked exception (`CriticalStatusException`)
- EVENT LOG printes altid i `finally` i `Game`
- Spillet kan genstartes via menu (Spil igen / Afslut)
- Ingen uncaught exceptions under test

---

### Ekstra testscenarier (verificeret)

- Ugyldigt menuinput flere gange i træk uden crash
- Handel med negative værdier giver pæn fejlbesked
- Handel med for mange reservedele kaster `InvalidTradeException`
- Motor kan både lykkes og fejle
- Game Over ved fuel < 10 eller integrity < 20
- Sejr når alle events gennemføres
