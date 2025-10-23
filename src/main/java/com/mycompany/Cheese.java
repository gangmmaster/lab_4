package com.mycompany;

/**
 * Класс, представляющий сырную пиццу.
 */
public class Cheese extends Food {

    /**
     * Конструктор сырной пиццы.
     *
     * @param price     цена
     * @param weight    вес
     * @param diameter  диаметр
     * @param calories  калорийность
     */
    public Cheese(double price, double weight, double diameter, double calories) {
        super(price, weight, diameter, calories);
    }

    /**
     * Возвращает описание пиццы.
     *
     * @return строку "Сырная пицца".
     */
    @Override
    public String getDescription() {
        return "Сырная пицца";
    }
}