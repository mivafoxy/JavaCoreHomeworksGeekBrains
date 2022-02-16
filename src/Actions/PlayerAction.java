package Actions;

import Obstacles.Obstacle;

/*
Действие игрока. Паттерн "стратегия". Объект действия агрегируется (замена наследованию) объектом "Игрок". Т.е.
в игроке хранится массив из действий, которые он может совершать.
 */
public interface PlayerAction {
    void pass(Obstacle obstacle);
    ActionState getActionState();
}
