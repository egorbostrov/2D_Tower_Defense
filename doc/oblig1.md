# Rapport – innlevering 1

 # A1 - Prosjektet
 ## Team & Roller

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
  - *Ansvar:* Poteten i gruppen, hjelper de som trenger.

## Prosjektideer

### Ide 1: Tower Defence (med kartskaper hvis mulig)

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

## Ferdighetsoversikt

### Generell Informasjon

- **Egor, Tarjei, William, Trym:**
  - **Studerer:** Datasikkerhet
  - **Ferdigheter:** Samlet kompetanse i Java, deler samme klasse.
  - **Fullførte Relevante Kurs:** INF100 (Python), INF101 (Java), INF102 (Java), INF142 (Datanett-Python), INF113 (OS / C programmeringsspråk).

- **Kevin:**
  - **Studerer:** Datateknologi
  - **Ferdigheter:** Sammenlignbar kompetanse med de andre.
  - **Fullførte Relevante Kurs:** INF100 (Python), INF101 (Java), INF102 (Java), INF113 (OS / C programmeringsspråk), INF115 (Databaser).

### Spesifikke Styrker og Svakheter

#### Egor
- **Styrker:**
  - Har kontroll over størstedelen av "MVC".
  - God på å lese og forstå hvordan kode er strukturert og fungerer.
- **Svakheter:**
  - Sliter med å implementere tester og forstå hvordan en test skal teste spesifikke ting i koden.
  - Finner det vanskelig å komme i gang med projekter.

#### William
- **Styrker:**
  - Føler seg mest komfortabel med både modellen og kontrolleren i "MVC", liker back-end koding.
  - Liker å teste koden.
- **Svakheter:**
  - Liker ikke front-end utvikling.

#### Tarjei
- **Styrker:**
  - Har mest kontroll på "controllere" delen og hvordan de kommuniserer med resten av koden i en MVC-oppsett.
- **Svakheter:**
  - Sliter med kreativitet i å komme opp med ulike ideer for å løse komplekse problemer.

#### Trym
- **Styrker:**
  - Finner det spennende å tenke utenfor boksen når det kommer til bruk av forskjellige datastrukturer, noe som åpner for muligheter for optimalisering i prosjektet.
  - Nyter å teste sikkerheten i applikasjoner, noe som kan være nyttig når vi tester spillet for feil.
- **Svakheter**
  - Finner det vanskelig å starte på store kodeprosjekter.
  - Har en tendens til å søke inspirasjon fra kilder når han står fast, i stedet for å la problemet bearbeides over tid.

#### Kevin
- **Styrker**
  - En stor fan av design, UI, og generelt front-end aspektet av programmer og systemer. Føler at han har et øye for slike ting og klarer godt å formidle programmets tema til brukeren på en fin måte som har "flyt".
- **Svakheter**
  - Kan slite litt i starten av et prosjekt, men når ideen for en viss løsning har slått til, er alt bra. Sliter litt med å skape tester.


# A2 - Konsept
## Spillkonsept: Tower Defense med Kartskaper

### Hoved spillelementer:
- **Spillerkontrollert Tårnplassering:** Brukere kan strategisk plassere tårn langs en sti for å forsvare seg mot bølger av fiender.
- **2D Rutenett Kart:** Spillverdenen består av et todimensjonalt rutenett hvor spillere kan bygge og tilpasse sitt forsvar.
- **Kartskaper:** Spillere har tilgang til et verktøy for å lage kart, som lar dem designe og dele sine egne 2D rutenett kart for bruk i spillet.
- **Fiender:** Forskjellige typer fiender beveger seg langs stien, hver med unike egenskaper. Målet er å stoppe dem fra å nå enden av stien.
- **Poeng og Oppgraderinger:** Poeng tjenes ved å beseire fiender, som kan brukes til å kjøpe tårnoppgraderinger eller spesielle evner.
- **Utfordringer:** Spillets utfordringer inkluderer å håndtere ressurser, velge de riktige tårntypene for forskjellige fiender, og tilpasse seg til stadig vanskeligere bølger.

### Aspekter som vurderes:
- **Tårnoppgraderinger:** Spillere kan oppgradere forskjellige tårn, som for eksempel å forbedre skadeutgang, for å forbedre deres defensive evner mot fiendens bølger.
- **Hastighetsmodus:** Vurder å implementere en hastighetsmodus som lar spillet simulere bølger i 2x hastighet, noe som gjør spillopplevelsen raskere og mer dynamisk for spillere som søker en raskere spillprogresjon.

### Inspirasjon:
Dette spillet henter inspirasjon fra klassiske tower defense-spill og inkorporerer en kreativ vri med kartskaperfunksjonen, som oppmuntrer spillerkreativitet og forlenger spillets replay-verdi. Konseptet har som mål å blande strategisk dybde med brukergenerert innhold, og tilbyr et friskt perspektiv på tower defense-sjangeren.


# A3 - Velg og tilpass en prosess for teamet
* 



