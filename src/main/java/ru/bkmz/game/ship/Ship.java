package ru.bkmz.game.ship;

import ru.bkmz.game.ship.coord.Coordinate;
import ru.bkmz.game.ship.coord.Coordinates;
import ru.bkmz.utils.Direction;
import ru.bkmz.utils.ExceptionOutOfBounds;

import java.awt.*;

public class Ship extends Coordinates {
    Color color;
    int size;
    boolean live;

    public Ship(Color color, int x, int y, int size, Direction direction, Coordinate maxSize) throws ExceptionOutOfBounds {
        super(x, y, size, direction, maxSize);
        this.color = color;
        this.size = size;
        this.live = true;

    }

    public Ship(Color color, Coordinate coordinate, int size, Direction direction, Coordinate maxSize) throws ExceptionOutOfBounds {
        super(coordinate.getX(), coordinate.getY(), size, direction, maxSize);
        this.color = color;
        this.size = size;
        this.live = true;
    }

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }
}
