package ru.danilov;

import engine.Game;
import engine.Player;
import engine.field.Cell;
import engine.field.Field;
import ru.danilov.graphics.StartFrame;

import javax.swing.*;
import java.awt.*;

public class Main {

    public void startApplication(Game game) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                StartFrame frame = new StartFrame(game);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }

    public static void fullTest() {
        Main main = new Main();
        Player playerOne = new Player("Player one");
        Player playerTwo = new Player("Player two");
        Game game = new Game(Field.getInstance(10), playerOne, playerTwo);
        main.startApplication(game);
    }

    public static void main(String[] args) throws InterruptedException {
        fullTest();
        //mapGeneratorTest();
    }

    public static void mapGeneratorTest() {
        Main main = new Main();
        Player playerOne = new Player("Player one");
        Player playerTwo = new Player("Player two");
        Game game = new Game(Field.getInstance(10), playerOne, playerTwo);

        Cell[][] playerOneMap = playerOne.getMap();
        Cell[][] playerTwoMap = playerTwo.getMap();


        System.out.println("Map player one: ");
        System.out.println(getMap(playerOneMap));

        System.out.println();

        System.out.println("Map player two: ");
        System.out.println(getMap(playerTwoMap));
    }

    private static String getMap(Cell[][] playerMap) {
        System.out.println();
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < playerMap[0].length; i++) {
            for (int j = 0; j < playerMap.length; j++) {
                if (playerMap[j][i].isActive())
                    stringBuilder.append(" 1");
                else
                    stringBuilder.append(" 0");
            }
            stringBuilder.append("\n");
        }

        return stringBuilder.toString();
    }
}