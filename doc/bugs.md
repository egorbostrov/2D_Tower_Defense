# Bugs som vi har fikset (ikke sortert)

### <ins>Defender Range</ins>
#### Beskrivelse:
- Defenders radius ble ikke overholdt.
- Defenders angrep ble ikke begrenset til angrepsradiusen, dette gjorde at den kunne <br>
- angripe fiender som var utenfor angrepsradiusen.

### <ins>Enemy Movement</ins>
#### Beskrivelse:
- Fiender beveget seg vertikalt i en tiles bredde, og horisontalt i en tiles høyde.
- Dette gjorde at fiender beveget seg feil etter `directionsList` som ble gitt.



### <ins>Hitbox på Enemies</ins>
#### Beskrivelse:
- Hitboxen til fiender satt fast på en plass, og fulgte ikke fienden.
- Dette gjorde at defenders angrep ikke traff fienden, men hitboxen der de "spawnet".


### <ins>Defenders plassering på brettet</ins>
#### Beskrivelse:
- Defenders ble plassert med en offset fra musen.
- Dette gjorde at defenders ble plassert feil på brettet.


### <ins>Option meny</ins>
#### Beskrivelse:
- Fonten i option menyen ble generert feil og viste ikke riktig font.
- Dette gjorde at teksten i menyen ble ikke lesbar.


### <ins>Spawndelay</ins>
#### Beskrivelse:
- Spawndelay baserte seg ikke på wave.
- Dette gjorde at fiender ble spawnet for fort, og enemies ble spawnet oppå hverandre.


### <ins>Shot</ins>
#### Beskrivelse:
- Skuddene fra defenders ble ikke "rendered" på skjermen.
- Dette gjorde at skuddene ikke ble synlig for spilleren, men skadet ennå fienden.


### <ins>Last enemy spawn</ins>
#### Beskrivelse:
- Siste fiende ble ikke spawnet.
- Dette gjorde at "wave" aldri ble ferdig, og spillet gikk ikke videre.


### <ins>WavePattern</ins>
#### Beskrivelse:
- WavePattern ble ikke generert riktig og enemies følgte ikke spawnPattern.
- Dette gjorde at enemies ble spawnet feil.


### <ins>Bytte Map</ins>
#### Beskrivelse:
- Når spilleren byttet map, ble ikke "wave" restartet, enemies og defenders var ennå på brettet.
- Dette gjorde at spilleren kunne plassere tårn på en map, og bytte map for å spille videre.


### <ins>Restart game</ins>
#### Beskrivelse:
- Når spilleren restartet spillet, ble liv og penger evig.
- Dette gjorde at spilleren kunne spille videre uten å miste liv eller bruke penger.