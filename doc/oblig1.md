# Rapport – innlevering 1

# A0
### Gruppenavn: Obama Gaming (Gruppe 5)
### Team & Roller
- **Prosjektleder:** Egor Bostrov
  - *Ansvar:* Delegere arbeid, fikser møter, kommunisere med gruppeleder.
- **Systemarkitekt:** Trym Fonn Jakobsen
  - *Ansvar:* Bestemme struktur, kodeintegrering mellom klasser, grensesnitt, kvalitetskontroll av kode, dokumentasjon.
- **Utvikler:** Alle
  - *Ansvar:* Arbeide med Model-View-Controller (MVC), Main.
- **Design og UX:** Kevinas Pliuskus
  - *Ansvar:* Lyd, bilde og grafikk.
- **Testansvarlig:** William Manley
  - *Ansvar:* Fikse nødvendige tester og hjelpe andre med koding.
- **Santas Hjelper:** Tarjei Fløtre
  - *Ansvar:* Hjelperen i gruppen, hjelper de som trenger.

### Kompetanse
#### Generell Informasjon
- **Egor, Tarjei, William, Trym:**
  - **Studerer:** Datasikkerhet
  - **Ferdigheter:** Samlet kompetanse i Java, deler samme klasse.
  - **Fullførte Relevante Kurs:** INF100 (Python), INF101 (Java), INF102 (Java), INF142 (Datanett-Python), INF113 (OS / C programmeringsspråk).

- **Kevin:**
  - **Studerer:** Datateknologi
  - **Ferdigheter:** Sammenlignbar kompetanse med de andre.
  - **Fullførte Relevante Kurs:** INF100 (Python), INF101 (Java), INF102 (Java), INF113 (OS / C programmeringsspråk), INF115 (Databaser).

#### Spesifikke Styrker og Svakheter
**Egor**
- **Styrker:**  
  Har kontroll over størstedelen av "MVC".  
  God på å lese og forstå hvordan kode er strukturert og fungerer.
- **Svakheter:**  
  Sliter med å implementere tester og forstå hvordan en test skal teste spesifikke ting i koden.  
  Finner det vanskelig å komme i gang med projekter.

**William*
- **Styrker:**  
  Føler seg mest komfortabel med både modellen og kontrolleren i "MVC", liker back-end koding.  
  Liker å teste koden.
- **Svakheter:**  
  Liker ikke front-end utvikling.

**Tarjei**
- **Styrker:**  
  Har mest kontroll på "controllere" delen og hvordan de kommuniserer med resten av koden i en MVC-oppsett.
- **Svakheter:**  
  Sliter med kreativitet i å komme opp med ulike ideer for å løse komplekse problemer.

**Trym**
- **Styrker:**  
  Finner det spennende å tenke utenfor boksen når det kommer til bruk av forskjellige datastrukturer, noe som åpner for muligheter for optimalisering i prosjektet.  
  Nyter å teste sikkerheten i applikasjoner, noe som kan være nyttig når vi tester spillet for feil.
- **Svakheter**  
  Finner det vanskelig å starte på store kodeprosjekter.  
  Har en tendens til å søke inspirasjon fra kilder når han står fast, i stedet for å la problemet bearbeides over tid.

**Kevin**
- **Styrker**  
  En stor fan av design, UI, og generelt front-end aspektet av programmer og systemer. Føler at han har et øye for slike ting og klarer godt å formidle programmets tema til brukeren på en fin måte som har "flyt".
- **Svakheter**  
  Kan slite litt i starten av et prosjekt, men når ideen for en viss løsning har slått til, er alt bra. Sliter litt med å skape tester.


# A1
### Prosjektideer
### Ide 1: Tower Defence (med kartskaper hvis mulig)
OBS! Vi har bestemt å lage Tower Defence
- Inspirert av Balloons tower defence, men kan involvere hvilket som helst tema, ikke nødvendigvis "Balloons".
- Forskjellige kart og design for "aper" og "ballonger".
- Ulike "aper" som utfører forskjellige handlinger (basic angriper, flammekaster for skade over tid, iskaster for slowness).
- Inkluderer liv som påvirkes hvis ballongene når enden.
- Flere nivåer med 1, 2, eller 3 veier hvorfra ballongene kan komme.
- En kartskaper som lar spillerne lage sine egne kart.

### Ide 2: En Platformer

- Inspirert av Mario, Super Meat Boy, osv.
- En plattformspiller med hinderløyper, "power-ups", og fiender som kan skade deg (ta liv).
- Forskjellige nivåer som en spiller må fullføre for å gå videre til neste.



# A2 - Konsept
## Spillkonsept: Tower Defense med Kartskaper

### Hoved spillelementer:
  - **Spillerkontrollert Tårnplassering:**  
  Brukere kan strategisk plassere tårn langs en sti for å forsvare seg mot bølger av fiender.

  - **2D Rutenett Kart:**  
  Spillverdenen består av et todimensjonalt rutenett hvor spillere kan bygge og tilpasse sitt forsvar.

  - **Kartskaper:**  
  Spillere har tilgang til et verktøy for å lage kart, som lar dem designe og dele sine egne 2D rutenett kart for bruk i spillet.

  - **Fiender:**  
  Forskjellige typer fiender beveger seg langs stien, hver med unike egenskaper. Målet er å stoppe dem fra å nå enden av stien.

  - **Poeng og Oppgraderinger:**  
  Poeng tjenes ved å beseire fiender, som kan brukes til å kjøpe tårnoppgraderinger eller spesielle evner.

  - **Utfordringer:**  
  Spillets utfordringer inkluderer å håndtere ressurser, velge de riktige tårntypene for forskjellige fiender, og tilpasse seg til stadig vanskeligere bølger.

### Aspekter som vurderes:
  - **Tårnoppgraderinger:**  
  Spillere kan oppgradere forskjellige tårn, som for eksempel å forbedre skadeutgang, for å forbedre deres defensive evner mot fiendens bølger.

  - **Hastighetsmodus:**  
  Vurder å implementere en hastighetsmodus som lar spillet simulere bølger i 2x hastighet, noe som gjør spillopplevelsen raskere og mer dynamisk for spillere som søker en raskere spillprogresjon.

### Inspirasjon:
Dette spillet henter inspirasjon fra klassiske tower defense-spill og inkorporerer en kreativ vri med kartskaperfunksjonen, som oppmuntrer spillerkreativitet og forlenger spillets replay-verdi. Konseptet har som mål å blande strategisk dybde med brukergenerert innhold, og tilbyr et friskt perspektiv på tower defense-sjangeren.


# A3 - Velg og tilpass en prosess for teamet
Vi bruker en Kanban board (GitLab Issue Board) og metoden vi bruker er "XP".

  **Møter og hyppighet av dem**  
  - Møter skjer *hver-uke* der vi går gjennom hva vi har gjort, hva som må gjøres og problemer vi har.
  - Møter skjer hver *Mandag kl: 1000 på Høytek rom: 209M1* (Hvis ledig)
  - Møter kan bli tilkallet av hvem som helst med å spørre projektleder.

  **Kommunikasjon mellom møter**
  - Kommunikasjonen skjer på discord, om det er noe viktig som trenger svar ASAP så kan vi "tagge" hverandre.

  **Arbeidsfordeling**
  - Rollene har blitt fordelt.
  - Vi jobber samlet minst en dag i uken (Fredag)
  - Om vi jobber hver for oss, så sjekker vi først "Issue Board" sånn at vi ikke jobber på samme ting.

  **Oppfølging av arbeid**
  - På møtet snakker vi om hvordan det går, hva vi sliter med og hva vi trenger hjelp til.

  **Deling og oppbevaring av felles dokumenter, diagram og kodebase**
  - Alle dokumenter er lagt til på discord serveren våres og lagt til projektet på GitLab.
  - Når vi er ferdig med å jobbe, så må vi alltid huske å "commit" arbeidet våres, uanset hvor mye er lite vi har gjort.

## A3 Del 2
### Applikasjonens Mål
  Applikasjonen skal være et enkelt, men engasjerende "Tower Defence" hvor spilleren plasserer "tårn" for å forsvare seg mot bølger av "angripere". Målet er å overleve så mange bølger som mulig ved plassering av "tårn", samtidig som spilleren tenker på ressurser som liv og poeng. Spillet skal tilby en intuitiv og engasjerende brukeropplevelse og en gradvis økning i vanskelighetsgraden.

### MVP  
1. Vise et spillbrett  
2. Mulig å plassere en "beskytter" (i koden)  
3. Mulig å generere "angripere" (i koden)  
4. Liv, poeng og bølger vises  
5. Spiller kan dø (mister liv)  
6. Game Start Screen  
7. Game Over Screen  
8. Quick Reset av spillet  
9. Pause Screen  
10. "Beskytter" skyter/ skader "angriperen"  

### Brukerhistorier 
  ***Som Spiller:***  
  Som spiller trenger jeg å skille mellom "forsvarstårn" og "angripere", samt forstå rekkevidden til "tårnere", slik at jeg kan planlegge min straregi mer effektivt. Jeg trenger også å enkelt kunne identifisere og velge oppgraderingsalternativer for mine tårn, inkludert forskjellige attributter som rekkevidde, skade, og spesialeffekter, for å tilpasse mitt forsvar basert på økende vanskelighetsgraden. Dette er spesielt viktig for å sikre at spillet er interessant og spennende for ulike spillere, inkludert de som kanskje har synsutfordringer eller spiller på enheter med mindre skjermer. Å ha intuitive ikoner og tydelige beskrivelse.  

  ***Som Utvikler:***  
  Som en utvikler trenger jeg å implementere en intuitiv og tilgjengelig UI design som klart skiller spillbare elementer (som forsvarstårn og angripere) fra ikke-spillbare bakgrunnselementer. Dette krever at jeg bruker tydelige farger, symboler og muligens tekst-lementer som kan hjelpe spilleren, inkludert de som er fargeblinde. I tillegg til dette, må jeg utvikle et klart og forståelig system for å vise tårnenes oppgraderingsmuligheter, hvor hver oppgradering har distinkte  indikatorer for sine attributter (for eksempel rekkevidde, skade, spesialeffekter). Dette vil sikre at spillere kan gjøre raske og mer informerte valg når det gjelder å tilpasse og styrke deres forsvar, noe som er avgjørende for å forbedre spillernes engasjement og spenning med spillopplevelsen. Implementering av tilgjengelige UI-komponenter som støtter ulike typer brukere er essensielt, uavhengig av deres fysiske forutsetninger eller skjermstørrelse.



