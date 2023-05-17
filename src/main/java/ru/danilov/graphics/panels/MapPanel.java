package ru.danilov.graphics.panels;

import engine.Game;
import engine.Player;
import engine.field.Cell;
import engine.field.Field;
import ru.danilov.graphics.GameFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class MapPanel extends JPanel {

    private final Field field;
    private final Game game;
    private GameFrame gameFrame;

    private boolean paintAllMap;

    public MapPanel(Field field, Game game, GameFrame gameFrame) {
        this.field = field;
        this.game = game;
        this.gameFrame = gameFrame;
        paintAllMap = false;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;

        if (game.isDraw() || game.getWinner() != null) {
            g2.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 24));
            g2.setPaint(new Color(0x33913C));
            if (game.isDraw()) {
                g2.drawString("НИЧЬЯ", 30, 30);
            } else {
                g2.drawString("Победил: " + game.getWinner() + " за " + game.getWinner().getCountStep() + " шагов", 30, 30);
            }
        } else if (paintAllMap) {
            int size = 15;
            int half = 8;

            Cell[][] map = game.getField().getFullMap();

            for (int i = 0; i < map.length; i++) {
                for (int y = 0; y < map[i].length; y++) {
                    Rectangle2D rectangle = new Rectangle2D.Double(y * size, i * size, size, size);
                    Cell cell = map[i][y];
                    if (cell.isActive()) {
                        g2.setColor(Color.WHITE);
                        if (cell.isPlayerOneHere()) {
                            g2.setColor(Color.BLUE);
                        } else if (cell.isPlayerTwoHere()) {
                            g2.setColor(Color.GREEN);
                        }
                    } else {
                        g2.setColor(new Color(0x1fb639));
                    }

                    g2.fill(rectangle);

                    rectangle = new Rectangle2D.Double((y * size) + half, i * size, 1, size);
                    g2.setColor(new Color(0xbcbcbc));
                    g2.fill(rectangle);

                    rectangle = new Rectangle2D.Double(y * size, (i * size) + half, size, 1);
                    g2.fill(rectangle);
                }
            }
        } else {
            int size = 15;
            int half = 8;

            Player player = game.whoIsNext();
            Cell[][] map = player.getMap();

            for (int i = 0; i < map.length; i++) {
                for (int y = 0; y < map[i].length; y++) {
                    Rectangle2D rectangle = new Rectangle2D.Double(y * size, i * size, size, size);
                    Cell cell = map[i][y];
                    if (cell.isActive()) {
                        g2.setColor(Color.WHITE);
                        if (cell.isPlayerOneHere()) {
                            g2.setColor(Color.BLUE);
                        } else if (cell.isPlayerTwoHere()) {
                            g2.setColor(Color.GREEN);
                        }
                    } else {
                        g2.setColor(new Color(0x1fb639));
                    }

                    g2.fill(rectangle);

                    rectangle = new Rectangle2D.Double((y * size) + half, i * size, 1, size);
                    g2.setColor(new Color(0xbcbcbc));
                    g2.fill(rectangle);

                    rectangle = new Rectangle2D.Double(y * size, (i * size) + half, size, 1);
                    g2.fill(rectangle);
                }
            }
        }
    }

    public void setPaintAllMap(boolean paintAllMap) {
        this.paintAllMap = paintAllMap;
    }
}
