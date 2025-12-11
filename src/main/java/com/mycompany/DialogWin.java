package com.mycompany;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DialogWin extends JDialog {
    private JComboBox<String> typeComboBox;
    private JTextField priceField;
    private JTextField weightField;
    private JTextField diameterField;
    private JTextField caloriesField;
    
    private boolean confirmed = false;
    private Food food = null;
    
    public DialogWin(JFrame parent, Food existingFood, int index) {
        super(parent, existingFood == null ? "Добавить пиццу" : "Редактировать пиццу", true);
        setSize(400, 300);
        setLocationRelativeTo(parent);
        
        initUI(existingFood);
    }
    
    private void initUI(Food existingFood) {
        JPanel mainPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Тип пиццы
        mainPanel.add(new JLabel("Тип пиццы:"));
        typeComboBox = new JComboBox<>(new String[]{"Пепперони", "Сырная", "Мясная"});
        mainPanel.add(typeComboBox);
        
        // Цена
        mainPanel.add(new JLabel("Цена (руб):"));
        priceField = new JTextField();
        mainPanel.add(priceField);
        
        // Вес
        mainPanel.add(new JLabel("Вес (г):"));
        weightField = new JTextField();
        mainPanel.add(weightField);
        
        // Диаметр
        mainPanel.add(new JLabel("Диаметр (см):"));
        diameterField = new JTextField();
        mainPanel.add(diameterField);
        
        // Калорийность
        mainPanel.add(new JLabel("Калорийность (ккал):"));
        caloriesField = new JTextField();
        mainPanel.add(caloriesField);
        
        // Заполняем поля если редактируем существующую пиццу
        if (existingFood != null) {
            if (existingFood instanceof Peperoni) {
                typeComboBox.setSelectedIndex(0);
            } else if (existingFood instanceof Cheese) {
                typeComboBox.setSelectedIndex(1);
            } else if (existingFood instanceof Meat) {
                typeComboBox.setSelectedIndex(2);
            }
            
            priceField.setText(String.valueOf(existingFood.getPrice()));
            weightField.setText(String.valueOf(existingFood.getWeight()));
            diameterField.setText(String.valueOf(existingFood.getDiameter()));
            caloriesField.setText(String.valueOf(existingFood.getCalories()));
        }
        
        // Кнопки
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton okButton = new JButton("OK");
        JButton cancelButton = new JButton("Отмена");
        
        okButton.addActionListener(e -> {
            if (validateInput()) {
                createFood();
                confirmed = true;
                dispose();
            }
        });
        
        cancelButton.addActionListener(e -> {
            confirmed = false;
            dispose();
        });
        
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);
        
        // Добавляем все на диалог
        setLayout(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        
        // Делаем OK кнопкой по умолчанию
        getRootPane().setDefaultButton(okButton);
    }
    
    private boolean validateInput() {
        try {
            double price = Double.parseDouble(priceField.getText());
            double weight = Double.parseDouble(weightField.getText());
            double diameter = Double.parseDouble(diameterField.getText());
            double calories = Double.parseDouble(caloriesField.getText());
            
            if (price <= 0 || weight <= 0 || diameter <= 0 || calories <= 0) {
                JOptionPane.showMessageDialog(this, 
                    "Все значения должны быть положительными числами!", 
                    "Ошибка", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            
            return true;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, 
                "Пожалуйста, введите корректные числовые значения!", 
                "Ошибка", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    private void createFood() {
        try {
            double price = Double.parseDouble(priceField.getText());
            double weight = Double.parseDouble(weightField.getText());
            double diameter = Double.parseDouble(diameterField.getText());
            double calories = Double.parseDouble(caloriesField.getText());
            
            int selectedType = typeComboBox.getSelectedIndex();
            
            switch (selectedType) {
                case 0:
                    food = new Peperoni(price, weight, diameter, calories);
                    break;
                case 1:
                    food = new Cheese(price, weight, diameter, calories);
                    break;
                case 2:
                    food = new Meat(price, weight, diameter, calories);
                    break;
            }
        } catch (NumberFormatException e) {
            // Не должно произойти, так как валидация уже выполнена
            food = null;
        }
    }
    
    public boolean isConfirmed() {
        return confirmed;
    }
    
    public Food getFood() {
        return food;
    }
}