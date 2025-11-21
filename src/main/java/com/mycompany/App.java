package com.mycompany;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        List<Food> menu = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        
        // Начальные пиццы
        menu.add(new Peperoni(12.5, 450, 30, 280));
        menu.add(new Cheese(10.5, 420, 30, 320));
        menu.add(new Meat(15.5, 500, 30, 350));
        
        while (true) {
            // Таблица пицц
            System.out.println("\nСейчас в меню:");
            for (int i = 0; i < menu.size(); i++) {
                Food p = menu.get(i);
                System.out.println((i+1) + ". " + p.getDescription() + " | " + 
                    p.getPrice() + " руб | " + p.getWeight() + "г | " + 
                    p.getDiameter() + "см | " + p.getCalories() + "ккал");
            }
            
            // Меню действий
            System.out.println("\n1 - Добавить");
            System.out.println("2 - Изменить"); 
            System.out.println("3 - Удалить");
            System.out.println("4 - Выход");
            System.out.print("Выбери: ");
            
            int choice = scanner.nextInt();
            
            if (choice == 1) {
                // Добавить
                System.out.print("Тип (1-Пепперони, 2-Сырная, 3-Мясная): ");
                int type = scanner.nextInt();
                System.out.print("Цена: ");
                double price = scanner.nextDouble();
                System.out.print("Вес: ");
                double weight = scanner.nextDouble();
                System.out.print("Диаметр: ");
                double diameter = scanner.nextDouble();
                System.out.print("Ккал: ");
                double calories = scanner.nextDouble();
                
                if (type == 1) menu.add(new Peperoni(price, weight, diameter, calories));
                else if (type == 2) menu.add(new Cheese(price, weight, diameter, calories));
                else if (type == 3) menu.add(new Meat(price, weight, diameter, calories));
                
                System.out.println("Добавлено!");
                
            } else if (choice == 2) {
                // Изменить
                System.out.print("Какую пиццу меняем (номер): ");
                int num = scanner.nextInt();
                int index = num - 1;
                if (index >= 0 && index < menu.size()) {
                    System.out.print("Новая цена: ");
                    double price = scanner.nextDouble();
                    System.out.print("Новый вес: ");
                    double weight = scanner.nextDouble();
                    System.out.print("Новый диаметр: ");
                    double diameter = scanner.nextDouble();
                    System.out.print("Новые ккал: ");
                    double calories = scanner.nextDouble();
                    
                    Food old = menu.get(index);
                    if (old instanceof Peperoni) menu.set(index, new Peperoni(price, weight, diameter, calories));
                    else if (old instanceof Cheese) menu.set(index, new Cheese(price, weight, diameter, calories));
                    else if (old instanceof Meat) menu.set(index, new Meat(price, weight, diameter, calories));
                    
                    System.out.println("Изменено!");
                } else {
                    System.out.println("Нет такой пиццы!");
                }
                
            } else if (choice == 3) {
                // Удалить
                System.out.print("Какую удаляем (номер): ");
                int num = scanner.nextInt();
                int index = num - 1;
                if (index >= 0 && index < menu.size()) {
                    menu.remove(index);
                    System.out.println("Удалено!");
                } else {
                    System.out.println("Нет такой пиццы!");
                }
                
            } else if (choice == 4) {
                // Выход
                break;
            }
        }
        scanner.close();
    }
}