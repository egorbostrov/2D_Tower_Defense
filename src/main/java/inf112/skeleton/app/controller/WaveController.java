package inf112.skeleton.app.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import inf112.skeleton.app.level.Level;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WaveController {
    private FileHandle filehandle;
    private final int selectedWave;
    private final EnemyController enemyController;
    float speedMultiplier;
    float healthMultiplier;
    private float spawnDelay;
    private EnemyFactory enemyFactory;
//    WaveEnemyFactory enemyFactory;

    List<String> wavePatterns;
    int waveIndex;
    float zombieIndex;
    private final boolean randomMode;
    private final Random random;
    private boolean doubleSpeed;

    public WaveController(EnemyController enemyController, int selectedWave, boolean randomMode) {
        this.enemyController = enemyController;
        this.selectedWave = selectedWave;
        this.randomMode = randomMode;
        this.random = new Random();

        selectFileHandle();
        this.wavePatterns = readWavePatternsFromFile(filehandle.readString());

        this.speedMultiplier = 1;
        this.healthMultiplier = 1;
        this.spawnDelay = 5;

        if(randomMode) {
            this.enemyFactory = new RandomEnemyFactory();
        }
        else {
            this.enemyFactory = new PatternedEnemyFactory(wavePatterns.get(waveIndex));
        }

    }

    private void selectFileHandle() {
        switch(selectedWave) {
            case 1 -> filehandle = Gdx.files.internal("maps/WavePattern1.txt");
            case 2 -> filehandle = Gdx.files.internal("maps/WavePattern2.txt");
            default -> throw new IllegalArgumentException("Found no wave for the value:  " + selectedWave);
        }
    }

    private List<String> readWavePatternsFromFile(String fileContent) {
        String[] lines = fileContent.split("\n");
        List<String> cleanLines = new ArrayList<>();

        for(String line : lines) {
            cleanLines.add(line.trim());
        }
        return cleanLines;
    }

    public void newWave(Level level) {
        //Increase the zombie speed, health and decrease the delay inbetween their spawns.
        speedMultiplier *= 1.05f;
        healthMultiplier *= 1.05f;
        spawnDelay *= 0.75f;

        if (randomMode) {
            generateRandomWave(level);
        }
        else {
            generateFixedWave(level);
        }
    }



    private void generateFixedWave(Level level) {
        waveIndex = waveIndex % wavePatterns.size();//Loop to first wave if we ran out of waves, using modulo

        String wavePattern = wavePatterns.get(waveIndex);
        this.enemyFactory = new PatternedEnemyFactory(wavePattern);

        this.zombieIndex = 1f;

        for(int i = 0; i < wavePattern.length(); i++) {
            if(level.isDoubleSpeedActive()) {
                enemyController.newZombie(enemyFactory.getNext(level, (speedMultiplier * 2), healthMultiplier, (zombieIndex * spawnDelay)));
            }
            else {
                enemyController.newZombie(enemyFactory.getNext(level, speedMultiplier, healthMultiplier, (zombieIndex * spawnDelay)));
            }
            zombieIndex++;
        }
        waveIndex++;
        zombieIndex = 0;
    }

    private void generateRandomWave(Level level) {
        int numZombies = 5 + waveIndex + random.nextInt(5);//Length is always minimum 5 + wave number; but get random amount on top of this.
        this.zombieIndex = 1f;

        for(int i = 0; i < numZombies; i++) {
            if(level.isDoubleSpeedActive()) {//FIX
                enemyController.newZombie(enemyFactory.getNext(level, speedMultiplier * 2, healthMultiplier, (zombieIndex * spawnDelay)));
            }
            else {
                enemyController.newZombie(enemyFactory.getNext(level, speedMultiplier, healthMultiplier, (zombieIndex * spawnDelay)));
            }
            zombieIndex++;
        }
        waveIndex++;
        zombieIndex = 0;
    }

    public void zombiesDoubleSpeedEnable() {
        this.doubleSpeed = true;
    }
    public void zombiesDoubleSpeedDisable() {
        this.doubleSpeed = false;
    }
}
