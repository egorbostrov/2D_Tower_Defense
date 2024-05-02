# Brukerhistorier (ikke sortert)

### <ins>Tårnendring ved klikk</ins>
#### Brukerhistorie:
- Som spiller ønsker jeg at tårnene skal endre oppførsel når jeg klikker på dem,<br>
og en oppgraderings meny kommer opp på skjermen, <br>
slik at jeg kan tilpasse forsvarstaktikken min under spillet.

#### Akseptansekriterier:
- Tårn endres visuelt når de blir klikket på.
- Tårn viser tilgjengelige oppgraderinger eller handlinger når de blir klikket på.
- Brukergrensesnittet for tårnoppgradering skal være intuitivt og enkelt å bruke.

#### Arbeidsoppgaver:
- Implementere funksjonalitet for å endre tårnvisuell ved klikk.
- Utvikle interaktivt grensesnitt for tårnvalg og oppgraderinger.
- Implementere funksjonalitet for å vise og gjemme tårnoppgraderinger ved klikk.
- Implementere "speed upgrade" knapp.
- Implementere "damage upgrade" knapp.
- Implementere "range upgrade" knapp.


### <ins>Tårn følger musepekeren</ins>
#### Brukerhistorie:
- Som spiller ønsker jeg at det tårnet jeg velger, følger musepekeren min,<br> 
slik at jeg kan plassere det presist der jeg ønsker.

#### Akseptansekriterier:
- Tårnet som er valgt skal følge musepekeren til det plasseres på et gyldig område.
- Ugyldige plasseringer skal ikke aksepteres, og spilleren får visuell feedback om dette.

#### Arbeidsoppgaver:
- Implementere logikk for at valgte tårn følger musepekeren.
- Utvikle visuell feedback for ulovlige og lovlige tårnplasseringer.


### <ins>Dobbel hastighet i spillet</ins>
#### Brukerhistorie:
- Som spiller ønsker jeg å kunne veksle mellom normal og dobbel hastighet i spillet,<br>
slik at jeg kan tilpasse spillets tempo etter mine behov.

#### Akseptansekriterier:
- Det skal være mulig å aktivere og deaktivere dobbel hastighet enkelt under spillet.
- Overgangen mellom hastighetene skal være sømløs.

#### Arbeidsoppgaver:
- Utvikle en knapp i brukergrensesnittet for å veksle mellom normal og dobbel hastighet.
- Sikre at implementeringen fungerer korrekt for begge hastigheter.


### <ins>"How To Play" scene</ins>
#### Brukerhistorie:
- Som ny spiller ønsker jeg en enkel og forståelig forklaring på hvordan spillet fungerer,<br>
inkludert tårn- og fiendetyper.

#### Akseptansekriterier:
- "How To Play"-scenen skal tilby detaljerte forklaringer på spillets mekanikker.
- Instruksjonene skal være klare, lette å forstå, og visuelt intuitivt.

#### Arbeidsoppgaver:
- Designe og implementere "How To Play"-scenen.
- Inkludere eksempler med forklaringer på ulike mekanikker.


### <ins>"Map Selection" scene</ins>
#### Brukerhistorie:
- Som spiller ønsker jeg å kunne velge mellom forskjellige kart før jeg starter et spill,<br>
slik at jeg kan variere spillopplevelsen og utfordringene jeg møter.

#### Akseptansekriterier:
- Det skal være et utvalg av kart tilgjengelig fra en "Map Selection"-scene.
- Spilleren skal kunne navigere mellom kartvalgene og tilbake til hovedmenyen på en lett måte.

#### Arbeidsoppgaver:
- Utvikle flere kart og legge dem til i spillets "Map Selection"-scene.
- Implementere en brukervennlig kartvalgsscene.
- Implementere "bilde-knapp" av kartene som spiller kan trykke på.


### <ins>"Game Over" scene med statistikk og menyvalg</ins>
#### Brukerhistorie:
- Som spiller ønsker jeg å se statistikk når spillet er ferdig og ha valg for å raskt starte et <br>
nytt spill eller gå tilbake til hovedmenyen eller "options".

#### Akseptansekriterier:
- Ved "Game Over", skal det vises en GameOver Scene med statistikk som:<br>
antall drepte zombier, total poengsum, og "wave".
- GameOver-scenen skal inneholde funksjonelle knapper for å starte et nytt spill <br>
eller returnere til hovedmenyen eller "options".

#### Arbeidsoppgaver:
- Utvikle en GameOver Scene som viser relevant spillstatistikk.
- Implementere knapper for rask reset, retur til hovedmenyen og "options".


### <ins>Bedre knappefunksjonalitet</ins>
#### Brukerhistorie:
- Som spiller ønsker jeg at knappene skal gi responsiv tilbakemelding og funksjonelle <br>
resultater når de aktiveres, for å forbedre brukeropplevelsen.

#### Akseptansekriterier:
- Knapper skal endre utseende når musen er over dem og når de klikkes.
- Hver knapp utfører en spesifikk handling.

#### Arbeidsoppgaver:
- Implementere visuelle og funksjonelle endringer på knapper.
- Sikre at hver knapp gir passende tilbakemelding og utfører en spesifikk handling.


### <ins>"Options" scene med innstillinger</ins>
#### Brukerhistorie:
- Som spiller ønsker jeg å endre forskjellige innstillinger i spillet til å forbedre min brukeriopplevelse.

#### Akseptansekriterier:
- Spillet skal inneholde en "Options"-scene med innstillinger for musikkvolum, <br>
- skjermmodus, og andre alternativer.
- Spillet skal tilby en enkel måte å endre innstillinger.
- Innstillingene skal endres umiddelbart uten å restarte spillet.

#### Arbeidsoppgaver:
- Implementere en "Options"-scene.
- Implementere innstillinger for musikkvolum, skjermmodus og andre alternativer.
- Sikre at overgangen mellom skjerminnstillinger er sømløs og brukervennlig.
- Implementere funksjonalitet for å lagre og laste inn innstillinger.

### <ins>Utvikle Bedre Spawner for Fiender</ins>
#### Brukerhistorie:
- Som spiller ønsker jeg at fiender skal "spawnes" på en mer variert og utfordrende <br>
måte i hver bølge, for å forbedre spillets dynamikk og utfordringsnivå.

#### Akseptansekriterier:
- Fiender som "spawnes" skal variere i type og mengde basert på hvilken bølge spilleren er på.

#### Arbeidsoppgaver:
- Implementere logikk for å øke antall og type fiender som spawnes med hver bølge.
- Utvikle en "random mode" for å generere fiender, sikre varierte og utfordrende fiendebølger.

### <ins>Spillerstatistikk i spillet</ins>
#### Brukerhistorie:
- Som spiller ønsker jeg å kunne se mine spillstatistikker som helse, bølgenummer, <br>
antall drepte fiender og tilgjengelige penger, for en bedre og lettere spilleropplevelse.

#### Akseptansekriterier:
- Spillet skal vise en top-bar med statistikk om spillerens helse, bølge, penger, og antall drepte fiender.
- Informasjonen skal være lett synlig og kontinuerlig oppdatert.

#### Arbeidsoppgaver:
- Designe og implementere en top-bar med nødvendige spillerstatistikker.
- Integrere "real-time-updates" av statistikkene basert på spillhendelser.


### <ins>Defenders</ins>
#### Brukerhistorie:
- Som spiller har jeg lyst å velge flere tårn og velge selv hvor de skal stå ut i fra en “menu” når jeg spiller.

#### Akseptansekriterier:
- Velge tårn fra en “menu” i “playscene”.
- Ha flere tårn å velge mellom.
- Tårn skal kunne plasseres hvor som helst på board (uten om path og innenfor "board").

#### Arbeidsoppgaver:
- Implementer en UI menu for tårnene.
- Implementer gunner defender (Vanlig range, damage, firerate).
- Implementer sniper defender (Stor range, mye damage, sein firerate).
- Implementer bomber defender (liten range, vanlig damage og speedrate, har "explosive radius").
- Implementere “click and drop” for å plassere tårnene selv.


### <ins>Enemies</ins>
#### Brukerhistorie:
- Som spiller vil jeg at enemies skal bli vanskeligere og vanskeligere å drepe for hver wave som kommer, <br>
og det kommer forskjellige typer enemies.

#### Akseptansekriterier:
- Ha en multiplikator for hver wave som gjør at flere enemies spawner.
- Ha en multiplikator for hver wave som gjør at enemies får mer “HP”.
- Ha en “randomizer” eller at det spawner flere typer enemies.


#### Arbeidsoppgaver:
- Implementere en multiplikator som baseres seg på hvilket wave du er på.
- Bruke multiplikatoren til å spawne mer enemies med mer “HP”.
- Implementere flere typer enemies.
- Implementere en bedre spawner som klarer å gi forskjellige typer enemies.

### <ins>"Main menu" design</ins>
#### Brukerhistorie:
- Som spiller syntes jeg at spillet ser mer spennende og vakkert ut med en fin meny.

#### Akseptansekriterier:
- Bedre design på “Main menu”, knapper skifter font/farge/bakgrunnsfarge når musen er over den og trykket.
- Når du trykker på en knapp så gjør den noe, skifter scene eller viser options…

#### Arbeidsoppgaver:
- Implementere bedre design på “Main menu”.
- Implementere bedre knapper med funksjoner og responsive feedback (skifter font/farge/bakgrunnsfarge).


### <ins>Musikk og lyd</ins>
#### Brukerhistorie:
- Som spiller ønsker jeg å ha musikk og lyd i spillet for å forbedre spillopplevelsen.

#### Akseptansekriterier:
- Spillet skal ha bakgrunnsmusikk og lydeffekter.

#### Arbeidsoppgaver:
- Finne og implementere passende bakgrunnsmusikk.
- Finne og implementere passende lydeffekter for handlinger i spillet.


### <ins>Bedre design på zombies og defenders</ins>
#### Brukerhistorie:
- Som spiller ønsker jeg at spillets objekter ser fine og tilpasset til spillet.

#### Akseptansekriterier:
- Bedre design på zombies, defenders og skydd.

#### Arbeidsoppgaver:
- Implementere bedre design på zombies.
- Implementere bedre design på defenders.
- Implementere bedre design på skydd.


### <ins>Flere typer zombies</ins>
#### Brukerhistorie:
- Som spiller ønsker jeg at det skal være flere typer zombies for å gjøre spillet mer utfordrende.

#### Akseptansekriterier:
- Flere typer zombies med forskjellige egenskaper.

#### Arbeidsoppgaver:
- Implementere "vanlig" zombie (vanlig liv og fart).
- Implementere "rask" zombie (vanlig/ lite live og rask fart).
- Implementere "tank" zombie (Mye live og treg fart).


### <ins>Penger fra zombies</ins>
#### Brukerhistorie:
- Som spiller ønsker jeg at det skal få penger fra zombies som blir drept, <br>
for å kunne kjøpe nye defenders og oppdateringer.

#### Akseptansekriterier:
- Få penger fra zombies som blir drept.

#### Arbeidsoppgaver:
- Implementere når en zombie blir drept for spilleren penger.
- Implementere forskjellige mengder penger fra forskjellige zombies.


### <ins>Penge "notifikasjon"</ins>
#### Brukerhistorie:
- Som spiller ønsker jeg at det skal komme en visuell notifikasjon når jeg får penger og mister penger.

#### Akseptansekriterier:
- Visuell notifikasjon når spilleren får penger.
- Visuell notifikasjon når spilleren mister penger

#### Arbeidsoppgaver:
- Implementere en "MoneyPopup" klasse.
- Bruker "MoneyPopup" klassen til å visuelt vise at spilleren har fått penger.
- Bruker "MoneyPopup" klassen til å visuelt vise at spilleren har mistet penger.


### <ins>Liv</ins>
#### Brukerhistorie:
- Som spiller ønsker jeg at jeg mister liv når en zombie kommer til enden av banen.

#### Akseptansekriterier:
- Spilleren mister liv når en zombie kommer til enden av banen.

#### Arbeidsoppgaver:
- Implementere at spilleren mister liv når en zombie kommer til enden av banen.
- Livene oppdateres i "top-bar".


### <ins>Kjøpe tårn</ins>
#### Brukerhistorie:
- Som spiller ønsker jeg å bruker penger til å kjøpe tårn for å forsvare meg mot zombier.

#### Akseptansekriterier:
- Spilleren kan kjøpe tårn for penger.

#### Arbeidsoppgaver:
- Implementere at spilleren kan kjøpe tårn for penger.


### <ins>Tårn er mer dynamisk</ins>
#### Brukerhistorie:
- Som spiller ønsker jeg at tårnene mine er mer dynamiske sånn at spillet blir mer gøy å spille.

#### Akseptansekriterier:
- Tårnene skal snu og flippe.

#### Arbeidsoppgaver:
- Implementere at tårnene snur seg etter zombier.
- Implementere at tårnene flipper når en zombie går forbi den.


### <ins>Bedre design på kartene</ins>
#### Brukerhistorie:
- Som spiller ønsker jeg at kartene er mer variert tekstur for å forbedre spillopplevelsen.

#### Akseptansekriterier:
- Bedre design på kartene.

#### Arbeidsoppgaver:
- Implementere bedre design på kartene ved å "randomize" alle tiles uten om "path".


### <ins>"Game" meny</ins>
#### Brukerhistorie:
- Som spiller ønsker jeg at det skal være en interaktiv meny i spillet for å kunne stoppe/ starte spillet, <br>
gå tilbake til hovedmenyen, gå til options, dobble/ vanlig hastighet.

#### Akseptansekriterier:
- Interaktiv meny i spillet.
- Knapper til forskjellige funksjoner.
- Designe nye textures for knappene.

#### Arbeidsoppgaver:
- Implementere en interaktiv meny i spillet.
- Implementere knapper for å stoppe/ starte spillet.
- Implementere knapper for å gå tilbake til hovedmenyen.
- Implementere knapper for å gå til options.
- Implementere knapper for å dobble/ vanlig hastighet.
- Designe nye textures for knappene.


### <ins>Liv til zombies</ins>
#### Brukerhistorie:
- Som spiller ønsker jeg at zombier har liv sånn at de ikke dør med en gang og en "HPbar".

#### Akseptansekriterier:
- Zombier har mer liv.
- Zombier har en "HPbar".

#### Arbeidsoppgaver:
- Implementere at zombier har mer liv.
- Implementere at zombier har en "HPbar".
- "HPbar" skal vises over zombien og skal forsvinne når zombien dør.
- "HPbar" skal være grønn (og ha en rød bakgrunn) og skal bli mindre når zombien mister liv.
