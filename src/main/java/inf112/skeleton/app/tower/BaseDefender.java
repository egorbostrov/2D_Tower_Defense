package inf112.skeleton.app.tower;

public abstract class BaseDefender {
    public enum DefenderType {
        GUNNER, SNIPER
    }

    public int getSpeedPrice() {
        return 0;
    }

    public int getRangePrice() {
        return 0;
    }

    public int getDefenderCost() {
        return 0;
    }
}
