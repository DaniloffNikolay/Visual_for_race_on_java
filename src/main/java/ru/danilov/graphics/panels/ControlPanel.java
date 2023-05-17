package ru.danilov.graphics.panels;

import engine.Game;
import engine.Player;
import engine.Step;
import ru.danilov.graphics.GameFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlPanel extends JPanel {

    private GameFrame gameFrame;
    private final Game game;

    private Player player;
    private byte direction;
    private boolean isBoost;
    private boolean isBrake;
    private JButton buttonDirectionPressed;

    private JButton topLeft;
    private JButton top;
    private JButton topRight;
    private JButton left;
    private JButton right;
    private JButton downLeft;
    private JButton down;
    private JButton downRight;

    private JButton brake;
    private JButton boost;
    private JButton buttonMovePressed;

    private static Color PASSIVE_COLOR = new Color(0xbcbcbc);
    private static Color ACTIVE_COLOR = new Color(0x379748);

    public ControlPanel(GameFrame gameFrame, Game game) {
        this.gameFrame = gameFrame;
        this.game =game;
        setBackground(new Color(0x544F4F));

        initialization();
    }

    private void initialization() {
        player = game.nextPlayer();

        topLeft = new JButton("top-left");
        topLeft.addActionListener(getActionListenerForDirection((byte) 1, topLeft));

        top = new JButton("top");
        top.addActionListener(getActionListenerForDirection((byte) 2, top));

        topRight = new JButton("top-right");
        topRight.addActionListener(getActionListenerForDirection((byte) 3, topRight));

        left = new JButton("left");
        left.addActionListener(getActionListenerForDirection((byte) 4, left));

        right = new JButton("right");
        right.addActionListener(getActionListenerForDirection((byte) 5, right));

        downLeft = new JButton("down-left");
        downLeft.addActionListener(getActionListenerForDirection((byte) 6, downLeft));

        down = new JButton("down");
        down.addActionListener(getActionListenerForDirection((byte) 7, down));

        downRight = new JButton("down-right");
        downRight.addActionListener(getActionListenerForDirection((byte) 8, downRight));


        JButton center = new JButton();
        center.setEnabled(false);
        center.setBackground(PASSIVE_COLOR);

        JButton run = new JButton("run");
        run.addActionListener(a -> {
            if (direction != 0) {
                Step step = new Step(player, direction, isBoost, isBrake);
                gameFrame.action(step);
                player = game.nextPlayer();
                setActiveButton(player.getDirection());
            } else {
                System.out.println("нужно выбрать направление");
            }
        });
        brake = new JButton("BRAKE");
        brake.addActionListener(getActionListenerForMove(brake, 1));
        boost = new JButton("BOOST");
        boost.addActionListener(getActionListenerForMove(boost, 2));

        GridLayout layout = new GridLayout(4, 2, 3, 3);
        setLayout(layout);

        add(topLeft);
        add(top);
        add(topRight);

        add(left);
        add(center);
        add(right);

        add(downLeft);
        add(down);
        add(downRight);

        add(run);
        add(brake);
        add(boost);

        setActiveButton(player.getDirection());
    }

    private void setButtonDirectionPressed(JButton button) {
        if (buttonDirectionPressed != null) {
            buttonDirectionPressed.setBackground(PASSIVE_COLOR);
            buttonDirectionPressed = button;
            buttonDirectionPressed.setBackground(ACTIVE_COLOR);
        } else {
            buttonDirectionPressed = button;
            buttonDirectionPressed.setBackground(ACTIVE_COLOR);
        }
    }

    private void setDirection(byte newDirection) {
        direction = newDirection;
    }

    private ActionListener getActionListenerForDirection(byte newDirection, JButton button) {
        button.setBackground(PASSIVE_COLOR);
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setDirection(newDirection);
                setButtonDirectionPressed(button);
            }
        };
    }

    private ActionListener getActionListenerForMove(JButton button, int i) {
        button.setBackground(PASSIVE_COLOR);
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (buttonMovePressed == null) {
                    buttonMovePressed = button;
                    buttonMovePressed.setBackground(ACTIVE_COLOR);
                    setMove(i);
                } else {
                    if (buttonMovePressed == button) {
                        buttonMovePressed = null;
                        button.setBackground(PASSIVE_COLOR);
                        setMove(0);
                    } else {
                        buttonMovePressed.setBackground(PASSIVE_COLOR);
                        buttonMovePressed = button;
                        buttonMovePressed.setBackground(ACTIVE_COLOR);
                        setMove(i);
                    }
                }
            }
        };
    }

    private void setMove(int i) {
        switch (i) {
            case 0 -> {
                isBoost = false;
                isBrake = false;
            }
            case 1 -> {
                isBoost = false;
                isBrake = true;
            }
            case 2 -> {
                isBoost = true;
                isBrake = false;
            }
        }
    }

    private void setActiveButton(Player.Direction directions) {
        topLeft.setEnabled(directions.isTopLeft());
        top.setEnabled(directions.isTop());
        topRight.setEnabled(directions.isTopRight());
        left.setEnabled(directions.isLeft());
        right.setEnabled(directions.isRight());
        downLeft.setEnabled(directions.isDownLeft());
        down.setEnabled(directions.isDown());
        downRight.setEnabled(directions.isDownRight());
    }
}
