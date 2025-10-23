package com.mycompany;

/**
 * Абстрактный класс, представляющий базовую сущность "Пища".
 * Содержит общие характеристики для всех видов пицц.
 */
public abstract class Food {
    private final double price;
    private final double weight;
    private final double diameter;
    private final double calories;

    /**
     * Конструктор для создания объекта пищи.
     *
     * @param price     цена
     * @param weight    вес
     * @param diameter  диаметр
     * @param calories  калорийность
     */
    public Food(double price, double weight, double diameter, double calories) {
        this.price = price;
        this.weight = weight;
        this.diameter = diameter;
        this.calories = calories;
    }

    public double getPrice() {
        return price;
    }

    public double getWeight() {
        return weight;
    }

    public double getDiameter() {
        return diameter;
    }

    public double getCalories() {
        return calories;
    }

    /**
     * Абстрактный метод для получения описания продукта.
     * @return строковое описание продукта.
     */
    public abstract String getDescription();
}