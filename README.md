## Testliste – Rumeventyr Exception Rescue Mission

> Status: Event 1 og Event 2 er implementeret og smoke-testet. Event 3 er under udvikling.

---

### Implementeret og testet (nuværende status)

1. Program starter korrekt og spørger om kaptajn + skibsnavn  
2. STATUS vises med korrekte startværdier  
3. Menuinput håndterer ugyldigt input uden crash (robust readInt)

4. Event 1 – Rumstorm

    * Begge valg virker
    * Fuel og integrity ændres korrekt
    * Tilfældig skade via Math.random
    * Shield påvirker skade
    * Loglinje oprettes
    * `checkCriticalStatus` kaldes efter event

5. Event 2 – Handel og Shield

    * Trade validerer input korrekt
    * IllegalArgumentException ved 0 eller negativ handel
    * InvalidTradeException ved for mange reservedele
    * Shield level 1 kan købes
    * Logging af trade og shield
    * Fejl håndteres uden crash og menu gentages

6. EVENT LOG printes altid til sidst (også ved fejl)

---

### Pending – Afventer implementering

#### Event 3 – Motor og Repair Kit (under udvikling)

* TODO: try / catch / finally flow
* TODO: Motor restart chance (success/fail)
* TODO: CriticalStatusException ved to fejl i træk
* TODO: Repair kit kan kun bruges én gang
* TODO: Logging af motorforsøg og repair kit

---

### Planlagte tests (når events er færdige)

* Ugyldigt menuinput flere gange i træk uden crash
* Handel med negative værdier giver pæn fejlbesked
* Handel med for mange reservedele kaster InvalidTradeException
* Motor kan både lykkes og fejle
* Game Over ved fuel < 10 eller integrity < 20
* Sejr når alle events gennemføres
