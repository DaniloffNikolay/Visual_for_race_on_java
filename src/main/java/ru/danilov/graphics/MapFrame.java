package ru.danilov.graphics;

import engine.Game;
import ru.danilov.graphics.panels.MapPanel;

import javax.swing.*;
import java.awt.*;

public class MapFrame extends JDialog {
    private Game game;

    private MapPanel mapPanel;

    public MapFrame(Game game) {
        this.game = game;

        Toolkit kit = Toolkit.getDefaultToolkit();
        final Dimension screenSize = kit.getScreenSize();

        setSize(screenSize.width, screenSize.height - 50);
        setResizable(true);
        setLocation(0, 0);

        setLayout(new BorderLayout());

        mapPanel = new MapPanel(game.getField(), game, null);
        mapPanel.setPaintAllMap(true);

        JScrollPane scrollPane = new JScrollPane(mapPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);



//        scrollPane.add(mapPanel);

        add(scrollPane, BorderLayout.CENTER);
    }
}
