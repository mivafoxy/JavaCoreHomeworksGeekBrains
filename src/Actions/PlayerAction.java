package Actions;

import Obstacles.Obstacle;

/*
Действие игрока. Паттерн "стратегия". Объект действия агрегируется (замена наследованию)
объектом "Игрок". Т.е. в игроке хранится массив из действий, которые он может совершать.
 */
public interface PlayerAction<T extends Obstacle> { // Теперь интерфейс может унаследоваться таким образом, чтобы
    void pass(T obstacle);                          // наследник уточнял, какой тип препятствия он может преодолеть
    ActionState getActionState();                   // При этом сигнатура метода для кода, использующего объекты типа
}                                                   // PlayerAction никак не изменяется.
