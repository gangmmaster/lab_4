package com.mycompany;

/**
 * Класс, представляющий пиццу "Пепперони".
 */
public class Peperoni extends Food {

    /**
     * Конструктор пиццы Пепперони.
     *
     * @param price     цена
     * @param weight    вес
     * @param diameter  диаметр
     * @param calories  калорийность
     */
    public Peperoni(double price, double weight, double diameter, double calories) {
        super(price, weight, diameter, calories);
    }

    /**
     * Возвращает описание пиццы.
     *
     * @return строку "Пицца Пепперони".
     */
    @Override
    public String getDescription() {
        return "Пицца Пепперони";
    }
}