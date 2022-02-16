package Obstacles;

public abstract class Obstacle {
    private int distance;
    public Obstacle(int distance) {
        this.distance = distance;
    }

    public int getDistance() {
        return distance;
    }

    public String getObstacleName() {
        return this.getClass().getSimpleName();
    }
}
