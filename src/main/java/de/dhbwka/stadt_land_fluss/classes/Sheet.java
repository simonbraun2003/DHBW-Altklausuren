package de.dhbwka.stadt_land_fluss.classes;

import javax.swing.*;
import java.awt.*;

public class Sheet extends JFrame {

    private Player player;
    private Game game;

    public Sheet(Player player, Game game) {
        this.player = player;
        this.game = game;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
