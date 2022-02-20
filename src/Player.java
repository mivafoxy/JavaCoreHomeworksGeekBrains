import Actions.ActionState;
import Actions.PlayerAction;
import Obstacles.Obstacle;

public class Player {
    private final String name;
    private final PlayerAction[] playerActions;

    public Player(String name, PlayerAction[] playerActions) {
        this.name = name;
        this.playerActions = playerActions;
    }

    public String getName() {
        return name;
    }

    public void tryToPassObstacle(Obstacle obstacle) {
        System.out.println(getName() + " пытается преодолеть " + obstacle.getObstacleName());

        for (PlayerAction action : playerActions) {
            try { // Из-за высокого уровня абстракции (работы с объектами через интерфейс PlayerAction) мы не знаем где
                  // какое препятствие будет обработано, что может вызвать исключение ClassCastException. Таким образом,
                  // несколько проверок instance of в реализациях интерфейса PlayerAction превращаются в одну, вынесенную
                  // на уровень выше.
                action.pass(obstacle);
                System.out.println(name + " смог обработать препятствие " + obstacle.getObstacleName());
                System.out.println();
                return;
            } catch (ClassCastException castException) {
                System.out.println(name + " подходит не с той стороны к препятствию " + obstacle.getObstacleName());
                System.out.println();
            }
        }
    }

    public boolean isWinner() {
        for (PlayerAction action : playerActions) {
            if (action.getActionState() == ActionState.NONE
                || action.getActionState() == ActionState.DEFEAT) {
                return false;
            }
        }

        return true;


    }
}


/*
1. Напишите метод, на вход которого подаётся двумерный строковый массив размером 4х4.
 При подаче массива другого размера необходимо бросить исключение MyArraySizeException.
2. Далее метод должен пройтись по всем элементам массива, преобразовать в int и просуммировать.
Если в каком-то элементе массива преобразование не удалось (например, в ячейке лежит символ или текст вместо числа),
 должно быть брошено исключение MyArrayDataException с детализацией,
 в какой именно ячейке лежат неверные данные.
3. В методе main() вызвать полученный метод, обработать возможные исключения
MyArraySizeException и MyArrayDataException и вывести результат расчета.
 */