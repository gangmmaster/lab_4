package com.mycompany;

import java.util.ArrayList;
import java.util.List;

/**
 * Главный класс приложения для демонстрации работы иерархии классов пицц.
 */
public class App {
    public static void main(String[] args) {
        List<Food> pizzaMenu = new ArrayList<>();
        
        pizzaMenu.add(new Peperoni(12.5, 450.0, 30.0, 280.0));
        pizzaMenu.add(new Peperoni(14.0, 500.0, 35.0, 300.0));
        pizzaMenu.add(new Peperoni(11.0, 400.0, 28.0, 260.0));
        pizzaMenu.add(new Cheese(10.5, 420.0, 30.0, 320.0));
        pizzaMenu.add(new Cheese(13.0, 480.0, 35.0, 350.0));
        pizzaMenu.add(new Cheese(9.5, 380.0, 25.0, 310.0));
        pizzaMenu.add(new Meat(15.5, 500.0, 30.0, 350.0));
        pizzaMenu.add(new Meat(17.0, 550.0, 35.0, 380.0));
        pizzaMenu.add(new Meat(14.0, 470.0, 28.0, 340.0));
        
        double targetWeight = 450.0;
        long countByWeight = pizzaMenu.stream()
                .filter(pizza -> pizza.getWeight() > targetWeight)
                .count();
        System.out.println("Количество пицц тяжелее " + targetWeight + "г: " + countByWeight);
        
        double targetDiameter = 30.0;
        System.out.println("\nЦены пицц с диаметром больше " + targetDiameter + "см:");
        pizzaMenu.stream()
                .filter(pizza -> pizza.getDiameter() > targetDiameter)
                .forEach(pizza -> System.out.println(
                    pizza.getDescription() + ": " + pizza.getPrice() + " у.е."
                ));
        
        System.out.println("\n--- Всё меню ---");
        for (Food pizza : pizzaMenu) {
            System.out.printf("%s | Цена: %.2f | Вес: %.0fг | Диаметр: %.0fсм | Ккал: %.0f%n",
                    pizza.getDescription(),
                    pizza.getPrice(),
                    pizza.getWeight(),
                    pizza.getDiameter(),
                    pizza.getCalories());
        }
    }
}