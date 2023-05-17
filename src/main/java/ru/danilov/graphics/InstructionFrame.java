package ru.danilov.graphics;

import javax.swing.*;
import java.awt.*;

public class InstructionFrame extends JDialog {

    public InstructionFrame() {

        int width = 350;
        int height = 330;

        Toolkit kit = Toolkit.getDefaultToolkit();
        final Dimension screenSize = kit.getScreenSize();
        //setUndecorated(true);
        setSize(width, height);
        setResizable(false);
        setLocation(screenSize.width / 2 - width / 2, screenSize.height / 2 - height / 2);

        JPanel panel = new JPanel();
        Label titleLabel = new Label("Инструкция:");

        JTextArea textArea = new JTextArea("Игра пошаговая выигрывает тот, кто приехал на финиш,\n" +
                "раньше другого, хотя бы на один ход.\n" +
                "Максимальная скорость 5 клеток за ход.\n" +
                "При нажатии кнопки boost произойдет ускорение\n" +
                "на одну клетку.\n" +
                "При нажатии кнопки brake произойдет торможение\n" +
                "на одну клетку.\n" +
                "Минимальная скорость 1 клетка за ход.\n" +
                "Если не выбрать ни boost ни brake скорость\n" +
                "останется прежней.\n" +
                "Выбирать направление можно только то,\n" +
                "которое было выбрано в прошлый раз\n" +
                "или два других соседних направления.\n" +
                "Если врезаться в стену то скорость скидывается\nдо 1 клетки за ход.");

        panel.add(titleLabel);
        panel.add(textArea);

        add(panel);

    }
}
