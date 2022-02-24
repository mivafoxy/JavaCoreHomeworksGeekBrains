/*
1. Написать метод, который меняет два элемента массива местами (массив может быть любого ссылочного типа);
2. Задача:
Даны классы abstractFruit.Fruit, abstractFruit.Apple extends abstractFruit.Fruit, abstractFruit.Orange extends abstractFruit.Fruit;
Класс fruits.Box, в который можно складывать фрукты.
Коробки условно сортируются по типу фрукта, поэтому в одну коробку нельзя сложить и яблоки, и апельсины;
Для хранения фруктов внутри коробки можно использовать ArrayList;
Сделать метод getWeight(),
который высчитывает вес коробки,
зная вес одного фрукта и их количество: вес яблока – 1.0f, апельсина – 1.5f (единицы измерения не важны);
Внутри класса fruits.Box сделать метод compare(),
который позволяет сравнить текущую коробку с той, которую подадут в compare() в качестве параметра.
true – если их массы равны, false в противоположном случае. Можно сравнивать коробки с яблоками и апельсинами;
Написать метод, который позволяет пересыпать фрукты из текущей коробки в другую.
Помним про сортировку фруктов: нельзя яблоки высыпать в коробку с апельсинами.
Соответственно, в текущей коробке фруктов не остается, а в другую перекидываются объекты, которые были в первой;
Не забываем про метод добавления фрукта в коробку.
 */

import fruits.Apple;
import fruits.Box;
import fruits.Orange;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        String[] arr = {"1", "2"};
        changeArrayElements(arr, 0, 1);
        System.out.println(Arrays.toString(arr));

        Box<Apple> appleBox = new Box<>(makeNewApples(10));
        Box<Orange> orangeBox = new Box<>(makeNewOranges(10));
        Box<Orange> secondOrangeBox = new Box<>(makeNewOranges(0));

        appleBox.compare(orangeBox);
        appleBox.compare(secondOrangeBox);
        orangeBox.transferTo(secondOrangeBox);
        appleBox.compare(orangeBox);
        appleBox.compare(secondOrangeBox);
    }

    public static ArrayList<Apple> makeNewApples(int count) {
        ArrayList<Apple> fruits = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            fruits.add(new Apple());
        }
        return fruits;
    }

    public static ArrayList<Orange> makeNewOranges(int count) {
        ArrayList<Orange> fruits = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            fruits.add(new Orange());
        }
        return fruits;
    }

    /*
    1. Написать метод, который меняет два элемента массива местами (массив может быть любого ссылочного типа);
     */
    public static <T>void changeArrayElements(T[] array, int firstIndex, int secondIndex) {
        if (firstIndex < 0 || array.length <= firstIndex || secondIndex < 0 || array.length <= secondIndex || array.length < 2) {
            System.out.println("Некорректные индексы");
            return;
        }

        // Если if-а не будет
        try {
            T arrayElement = array[firstIndex];
            array[firstIndex] = array[secondIndex];
            array[secondIndex] = arrayElement;
        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println("Некорректные индексы");
        }
    }
}
