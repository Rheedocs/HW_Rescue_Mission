## Testliste – Rumeventyr Exception Rescue Mission

> Status: Færdig

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

7. Event 3 - Engine

* try / catch / finally flow hvor finally altid printer (Forsøger at genstarte ved fail eller Motoren er nu igang igen ved success)
* Motor restart chance (success/fail) med math.random
* CriticalStatusException ved to engine failures i træk
* Repair kit kan kun bruges én gang. Muligheden for at vælge repair-kit blive fjernet efter repair kit er brugt.
* Logging af motorforsøg og repair kit success/fail

---

### Planlagte tests (når events er færdige)

* Ugyldigt menuinput flere gange i træk uden crash
* Handel med negative værdier giver pæn fejlbesked
* Handel med for mange reservedele kaster InvalidTradeException
* Motor kan både lykkes og fejle
* Game Over ved fuel < 10 eller integrity < 20
* Sejr når alle events gennemføres
