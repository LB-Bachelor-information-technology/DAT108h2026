# Oblig 5 - Database og API
**Gruppe:** DAT108 Oblig5 gr 67  
**Deltagere:**  
Lars Birger Bergmål 583411  
Markus Fosse Høvring 194918  

---

## 1. Introduksjon og Arkitektur
Denne rapporten dekker implementasjonen av Oblig 5 i DAT108, der en tidligere Spring Boot-applikasjon (fra Oblig 4) har blitt utvidet med:
- Persistens via Spring Data JPA mot en Azure-hostet PostgreSQL-database (`dat108_oblig5_h26`).
- Et sikret REST API for henting og oppretting av deltagere ved hjelp av felles API-nøkkel (`X-API-Key`).
- Bruk av Data Transfer Objects (DTOer) for inn- og utdata med validering.

---

## 2. Enhetstester (JUnit og Mockito)
Følgende enhetstester er implementert for å sikre at forretningslogikken fungerer som forventet:
1. `PassordServiceTest` (tester passordhashing og verifisering).
2. `DeltagerDbServiceTest` (tester sortering av deltagere ved hjelp av Mockito).

![Skjermbilde av vellykket kjøring av JUnit-tester](<../assets/junit-tests.png>)
*Figur 1: Kjøring av enhetstester som viser at alle tester grønne.*

---

## 3. Manuell Testing av REST API (.http-fil)
I henhold til oppgavespesifikasjonen er alle endepunktene testet manuelt med tilhørende HTTP-statuskoder og feilscenarier.

### 3.1 GET /api/deltagere (Hente alle)
- **Test 1: Gyldig API-nøkkel (200 OK)**
  ![Skjermbilde av Test 1](<../assets/test-1.png>)
  *Figur 2: Henter ut alle deltagere sortert på fullt navn med gyldig API-nøkkel.*

- **Test 2: Manglende API-nøkkel (401 Unauthorized)**
  ![Skjermbilde av Test 2](<../assets/test-2.png>)
  *Figur 3: Avviser forespørsel som mangler API-nøkkel.*

- **Test 3: Ugyldig API-nøkkel (401 Unauthorized)**
  ![Skjermbilde av Test 3](<../assets/test-3.png>)
  *Figur 4: Avviser forespørsel med feil API-nøkkel.*

---

### 3.2 GET /api/deltagere/{mobil} (Hente en)
- **Test 4: Eksisterende deltager og gyldig nøkkel (200 OK)**
  ![Skjermbilde av Test 4](<../assets/test-4.png>)
  *Figur 5: Returnerer spesifikk deltager som JSON.*

- **Test 5: Ugyldig/manglende API-nøkkel (401 Unauthorized)**
  ![Skjermbilde av Test 5](<../assets/test-5.png>)
  *Figur 6: Avviser forespørsel ved ugyldig eller manglende API-nøkkel.*

- **Test 6: Deltager finnes ikke (404 Not Found)**
  ![Skjermbilde av Test 6](<../assets/test-6.png>)
  *Figur 7: Returnerer 404 når mobilnummeret ikke finnes i databasen.*

---

### 3.3 POST /api/deltagere (Opprette ny)
- **Test 7: Vellykket opprettelse (201 Created)**
  ![Skjermbilde av Test 7](<../assets/test-7.png>)
  *Figur 8: Lagrer ny deltager og returnerer bekreftelse.*

- **Test 8: Ugyldig/manglende API-nøkkel (401 Unauthorized)**
  ![Skjermbilde av Test 8](<../assets/test-8.png>)
  *Figur 9: Avviser registrering ved manglende eller feil API-nøkkel.*

- **Test 9: Ugyldig input / valideringsfeil (400 Bad Request)**
  ![Skjermbilde av Test 9](<../assets/test-9.png>)
  *Figur 10: Avviser påmelding fordi inputdata bryter valideringsreglene (f.eks. for kort mobilnummer/passord).*

- **Test 10: Eksisterende deltager / duplikat (409 Conflict)**
  ![Skjermbilde av Test 10](<../assets/test-10.png>)
  *Figur 11: Stopper registrering og gir 409 Conflict dersom mobilnummeret allerede er registrert.*