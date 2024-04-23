package inf112.skeleton.app.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import inf112.skeleton.app.level.Level;

import java.util.ArrayList;
import java.util.List;

public class WaveController {
    private FileHandle filehandle;
    private final int selectedWave;
    EnemyController enemyController;
    float spawnDelay;
    WaveEnemyFactory enemyFactory;

    List<String> wavePatterns;
    int waveIndex;
    float zombieIndex;

    public WaveController(EnemyController enemyController, int selectedWave) {
        this.enemyController = enemyController;
        this.selectedWave = selectedWave;

        selectFileHandle();
        this.wavePatterns = readWavePatternsFromFile(filehandle.readString());

        this.spawnDelay = 5;
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
        waveIndex = waveIndex % wavePatterns.size();//Loop to first wave if we ran out of waves, using modulo

        String wavePattern = wavePatterns.get(waveIndex);
        this.enemyFactory = new WaveEnemyFactory(wavePattern);

        this.zombieIndex = 1f;
        spawnDelay *= 0.75f;

        for(int i = 0; i < wavePattern.length(); i++) {
            enemyController.newZombie(enemyFactory.getNext(level, zombieIndex * spawnDelay));
            zombieIndex++;
        }
        waveIndex++;
        zombieIndex = 0;
    }
}
