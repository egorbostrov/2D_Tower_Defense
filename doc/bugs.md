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


#### <ins>Defender legal og illegal placement</ins>
#### Beskrivelse:
- Selv om en defender lyser grønn (legal position), kunne vi ikke plassere den.
- Defender kunne også lyse rød (illegal position), men vi kunne plassere den.
- Dette gjorde at spilleren kunne ikke plassere defender på en legal position, og kunne plassere defender på en illegal position.

### <ins>Unable to start new game without closing the app</ins>
#### Beskrivelse:
-Fikk ikke byttet kart eller starte nytt spill.
-Bytting av scene tilbake til playScene fra meny eller gameOver beholdt fortsatt gamle Enemy- og TowerController.