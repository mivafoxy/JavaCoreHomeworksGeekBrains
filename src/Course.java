import Obstacles.Obstacle;

public class Course {
    private Obstacle[] obstacles;

    public Course(Obstacle[] obstacles) {
        this.obstacles = obstacles;
    }

    public void doIt(Team team) {
        for (Obstacle obstacle : obstacles) {
            System.out.println("Просим преодолеть: " + obstacle.getObstacleName());
            team.overcomeObstacle(obstacle);
        }
    }
}