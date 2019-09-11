package com.boristax;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class App extends JFrame {
    JFrame thisFrame;
    public static void main(String[] args) throws AWTException {
        new App();
    }
    private static final long serialVersionUID = 1L;
    public App() {
        super("DialogWindows");
        thisFrame=this;
        // Выход из программы при закрытии
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // Кнопки для создания диалоговых окон
        JButton button1 = new JButton("ОК");
        
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                thisFrame.dispose();
            }
        });

        // Создание панели содержимого с размещением кнопок
        JPanel contents = new JPanel();
        contents.add(button1);
        setContentPane(contents);
        // Определение размера и открытие окна
        setSize(100, 100);
        setLocation(1000,800);
        setVisible(true);
    }
    /** Функция создания диалогового окна.
     * @param title - заголовок окна
     * @param modal - флаг модальности
     */

}