import Actions.JumpAction;
import Actions.PlayerAction;
import Actions.RunAction;
import Actions.SwimAction;
import Obstacles.Obstacle;
import Obstacles.Pool;
import Obstacles.Racetrack;
import Obstacles.Wall;

import java.util.Random;

 /*
1. Разобраться с имеющимся кодом.


В итоге должно получиться похожее:
public static void main(String[] args) {
Course c = new Course(...); // Создаем полосу препятствий
Team team = new Team(...); // Создаем команду
c.doIt(team); // Просим команду пройти полосу
team.showResults(); // Показываем результаты
}
  */

public class Main {
    private static final int POOL_DISTANCE = 100;
    private static final int RACETRACK_DISTANCE = 100;
    private static final int WALL_DISTANCE = 5;

    public static void main(String[] args) {
        Course course = new Course(makeObstacles());
        Player[] players = makePlayers();
        Team team = new Team("GeekBrains", players);
        course.doIt(team);
        team.printWinners();
    }

    public static Obstacle[] makeObstacles() {
        return new Obstacle[]{
            new Pool(POOL_DISTANCE),
            new Racetrack(RACETRACK_DISTANCE),
            new Wall(WALL_DISTANCE)
        };
    }

    /*
    Создание игроков вынесено в отдельный метод, а не осталось в конструкторе класса Course, код необходимо декомпозировать,
    и конструктор оставлять МАКСИМАЛЬНО простым.
     */
    public static Player[] makePlayers() {
        return new Player[] {
            new Player("Петя", new PlayerAction[] { getSwimAction(), getJumpAction() }),
            new Player("Ваня", new PlayerAction[] { getRunAction() }),
            new Player("Аня", new PlayerAction[] { getRunAction(), getJumpAction(), getSwimAction() }),
            new Player("Лена", new PlayerAction[] { getSwimAction() }),
        };
    }

    /*
    Ниже создаются действия, которые могут выполнять игроки. Возможности этих игроков рандомизируются.
     */

    public static PlayerAction getSwimAction() {
        Random random = new Random();
        SwimAction swimAction = new SwimAction(random.nextInt(POOL_DISTANCE + 50));
        return swimAction;
    }

    public static PlayerAction getJumpAction() {
        Random random = new Random();
        JumpAction jumpAction = new JumpAction(random.nextInt(WALL_DISTANCE + 2));
        return jumpAction;
    }

    public static PlayerAction getRunAction() {
        Random random = new Random();
        RunAction runAction = new RunAction(random.nextInt(RACETRACK_DISTANCE + 50));
        return runAction;
    }
}
