package com.mycompany;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    private Refact repository = new Refact();
    private DefaultListModel<String> listModel = new DefaultListModel<>();
    private JList<String> pizzaList = new JList<>(listModel);

    public MainFrame() {
        super("Управление пиццами");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        
        initUI();
        loadSampleData();
        updateList();
    }

    private void initUI() {
        // Создаем панели
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Левая панель со списком
        JPanel listPanel = new JPanel(new BorderLayout());
        listPanel.setBorder(BorderFactory.createTitledBorder("Список пицц"));
        listPanel.add(new JScrollPane(pizzaList), BorderLayout.CENTER);
        
        // Правая панель с кнопками
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(6, 1, 5, 5));
        
        JButton addButton = new JButton("Добавить пиццу");
        JButton editButton = new JButton("Редактировать");
        JButton deleteButton = new JButton("Удалить");
        JButton viewButton = new JButton("Просмотреть детали");
        JButton clearButton = new JButton("Очистить все");
        JButton exitButton = new JButton("Выход");
        
        // Добавляем обработчики событий
        addButton.addActionListener(e -> showAddDialog());
        editButton.addActionListener(e -> showEditDialog());
        deleteButton.addActionListener(e -> deleteSelected());
        viewButton.addActionListener(e -> showDetails());
        clearButton.addActionListener(e -> clearAll());
        exitButton.addActionListener(e -> System.exit(0));
        
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(viewButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(exitButton);
        
        // Добавляем панели на главное окно
        mainPanel.add(listPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.EAST);
        
        add(mainPanel);
    }

    private void loadSampleData() {
        // Добавляем примеры пицц
        repository.add(new Peperoni(12.5, 450, 30, 280));
        repository.add(new Cheese(10.5, 420, 30, 320));
        repository.add(new Meat(15.5, 500, 30, 350));
    }

    private void updateList() {
        listModel.clear();
        for (int i = 0; i < repository.size(); i++) {
            Food food = repository.get(i);
            listModel.addElement((i + 1) + ". " + food.getDescription() + 
                " - " + food.getPrice() + " руб");
        }
    }

    private void showAddDialog() {
        DialogWin dialog = new DialogWin(this, null, -1);
        dialog.setVisible(true);
        
        if (dialog.isConfirmed()) {
            Food newFood = dialog.getFood();
            if (newFood != null) {
                repository.add(newFood);
                updateList();
                JOptionPane.showMessageDialog(this, "Пицца добавлена!", 
                    "Успех", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private void showEditDialog() {
        int selectedIndex = pizzaList.getSelectedIndex();
        if (selectedIndex == -1) {
            JOptionPane.showMessageDialog(this, "Выберите пиццу для редактирования", 
                "Ошибка", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        DialogWin dialog = new DialogWin(this, repository.get(selectedIndex), selectedIndex);
        dialog.setVisible(true);
        
        if (dialog.isConfirmed()) {
            Food updatedFood = dialog.getFood();
            if (updatedFood != null) {
                repository.update(selectedIndex, updatedFood);
                updateList();
                JOptionPane.showMessageDialog(this, "Пицца обновлена!", 
                    "Успех", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private void deleteSelected() {
        int selectedIndex = pizzaList.getSelectedIndex();
        if (selectedIndex == -1) {
            JOptionPane.showMessageDialog(this, "Выберите пиццу для удаления", 
                "Ошибка", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int response = JOptionPane.showConfirmDialog(this, 
            "Вы уверены, что хотите удалить выбранную пиццу?", 
            "Подтверждение удаления", 
            JOptionPane.YES_NO_OPTION);
        
        if (response == JOptionPane.YES_OPTION) {
            repository.remove(selectedIndex);
            updateList();
            JOptionPane.showMessageDialog(this, "Пицца удалена!", 
                "Успех", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void showDetails() {
        int selectedIndex = pizzaList.getSelectedIndex();
        if (selectedIndex == -1) {
            JOptionPane.showMessageDialog(this, "Выберите пиццу для просмотра", 
                "Ошибка", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        Food food = repository.get(selectedIndex);
        String details = String.format(
            "Тип: %s\n" +
            "Цена: %.2f руб\n" +
            "Вес: %.1f г\n" +
            "Диаметр: %.1f см\n" +
            "Калорийность: %.1f ккал",
            food.getDescription(),
            food.getPrice(),
            food.getWeight(),
            food.getDiameter(),
            food.getCalories()
        );
        
        JOptionPane.showMessageDialog(this, details, 
            "Детали пиццы", JOptionPane.INFORMATION_MESSAGE);
    }

    private void clearAll() {
        if (repository.size() == 0) {
            JOptionPane.showMessageDialog(this, "Список уже пуст", 
                "Информация", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        int response = JOptionPane.showConfirmDialog(this, 
            "Вы уверены, что хотите удалить все пиццы?", 
            "Подтверждение", 
            JOptionPane.YES_NO_OPTION);
        
        if (response == JOptionPane.YES_OPTION) {
            repository.clear();
            updateList();
            JOptionPane.showMessageDialog(this, "Все пиццы удалены!", 
                "Успех", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void main(String[] args) {
        // Устанавливаем Look and Feel для лучшего внешнего вида
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}