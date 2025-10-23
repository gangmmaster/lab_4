package com.mycompany;

/**
 * Класс, представляющий мясную пиццу.
 */
public class Meat extends Food {

    /**
     * Конструктор мясной пиццы.
     *
     * @param price     цена
     * @param weight    вес
     * @param diameter  диаметр
     * @param calories  калорийность
     */
    public Meat(double price, double weight, double diameter, double calories) {
        super(price, weight, diameter, calories);
    }

    /**
     * Возвращает описание пиццы.
     *
     * @return строку "Мясная пицца".
     */
    @Override
    public String getDescription() {
        return "Мясная пицца";
    }
}