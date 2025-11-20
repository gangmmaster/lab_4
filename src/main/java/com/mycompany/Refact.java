package com.mycompany;

import java.util.ArrayList;
import java.util.List;

/**
 * Репозиторий для управления коллекцией объектов Food
 * Содержит коллекцию экземпляров иерархии и методы добавления, удаления и изменения
 */
public class Refact {
    private List<Food> foodItems = new ArrayList<>();
    
    /**
     * Добавление элемента в коллекцию
     * @param food добавляемый объект Food
     */
    public void add(Food food) {
        if (food != null) {
            foodItems.add(food);
        }
    }
    
    /**
     * Удаление элемента по индексу
     * @param index индекс удаляемого элемента
     * @return true если удаление успешно
     */
    public boolean remove(int index) {
        if (index >= 0 && index < foodItems.size()) {
            foodItems.remove(index);
            return true;
        }
        return false;
    }
    
    /**
     * Изменение элемента по индексу
     * @param index индекс изменяемого элемента
     * @param newFood новый объект Food
     * @return true если изменение успешно
     */
    public boolean update(int index, Food newFood) {
        if (index >= 0 && index < foodItems.size() && newFood != null) {
            foodItems.set(index, newFood);
            return true;
        }
        return false;
    }
    
    /**
     * Получение элемента по индексу
     * @param index индекс элемента
     * @return элемент или null если не найден
     */
    public Food get(int index) {
        if (index >= 0 && index < foodItems.size()) {
            return foodItems.get(index);
        }
        return null;
    }
    
    /**
     * Получение всех элементов коллекции
     * @return список всех элементов
     */
    public List<Food> getAll() {
        return new ArrayList<>(foodItems);
    }
    
    /**
     * Получение размера коллекции
     * @return количество элементов
     */
    public int size() {
        return foodItems.size();
    }
    
    /**
     * Очистка всей коллекции
     */
    public void clear() {
        foodItems.clear();
    }
}