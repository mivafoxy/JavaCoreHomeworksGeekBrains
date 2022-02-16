import Obstacles.Obstacle;

/*
3. Добавить класс Course (полоса препятствий), в котором будут находиться:
массив препятствий;
метод, который будет просить команду пройти всю полосу.
 */
public class Course {
    /*
    массив препятствий;
     */
    private Obstacle[] obstacles;

    public Course(Obstacle[] obstacles) {
        this.obstacles = obstacles;
    }

    /*
    метод, который будет просить команду пройти всю полосу.
    Каждое препятствие поочередно передается команде, та уже решает, кто будет проходить препятствие.
     */
    public void doIt(Team team) {
        for (Obstacle obstacle : obstacles) {
            System.out.println("Просим преодолеть: " + obstacle.getObstacleName());
            team.overcomeObstacle(obstacle);
        }
    }
}