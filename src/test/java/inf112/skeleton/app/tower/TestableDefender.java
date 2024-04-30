package inf112.skeleton.app.tower;

import inf112.skeleton.app.entity.Enemy;
import java.util.List;

public class TestableDefender extends BaseDefender {
    public TestableDefender(float xCord, float yCord, List<Enemy> enemies) {
        super(xCord, yCord, enemies);
    }

    @Override
    public void projectileFire() {
        // Implement this method as needed for testing, or leave it minimal.
    }

    @Override
    public void rappidFire() {
        // Implement this method as needed for testing, or leave it minimal.
    }
}
