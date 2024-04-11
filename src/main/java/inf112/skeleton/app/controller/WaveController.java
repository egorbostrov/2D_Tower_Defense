package inf112.skeleton.app.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.Interpolation;
import inf112.skeleton.app.level.Level;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WaveController {
    EnemyController enemyController;
    float spawnDelay;
    WaveEnemyFactory enemyFactory;

    List<String> wavePatterns;
    int waveIndex;
    float zombieIndex;

    public WaveController(EnemyController enemyController) {
        this.enemyController = enemyController;
        this.spawnDelay = 5;

        this.wavePatterns = readWavePatternsFromFile("WavePatterns.txt");

//        this.enemyFactory = new WaveEnemyFactory("S");
//        this.wavePattern = null;
    }

    private List<String> readWavePatternsFromFile(String fileName) {
        FileHandle fileHandle = Gdx.files.internal(fileName);
        String fileContent = fileHandle.readString();
        String[] lines = fileContent.split("\n");
        return new ArrayList<>(Arrays.asList(lines));
    }

    public void newWave(Level level) {
        System.out.println("Index:  " + waveIndex);
        System.out.println("size:   " + wavePatterns.size());
        /*if(waveIndex >= wavePatterns.size()) {
            waveIndex = 0; //Loop to first wave if we ran out of waves
        }*/
        waveIndex = waveIndex % wavePatterns.size();//Loop to first wave if we ran out of waves

        System.out.println("New index:  " + waveIndex);

        String wavePattern = wavePatterns.get(waveIndex);
        this.enemyFactory = new WaveEnemyFactory(wavePattern);

        this.zombieIndex = 1f;
        spawnDelay *= 0.75f;

        for(int i = 0; i < wavePattern.length() - 1; i++) {
            //System.out.println(wavePattern.length());
            enemyController.newZombie(enemyFactory.getNext(level, zombieIndex * spawnDelay));
            zombieIndex++;
        }
        waveIndex++;
        zombieIndex = 0;
    }

/*    public EnemyController getEnemyController() {
        return enemyController;
    }*/
}
