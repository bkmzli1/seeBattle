package ru.bkmz.game.ship.coord;

import ru.bkmz.utils.Direction;
import ru.bkmz.utils.ExceptionOutOfBounds;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Coordinates {
    Set<Coordinate> coordinates;
    Set<Coordinate> coordinatesAttack;

    public Coordinates(int x, int y, int size, Direction direction, Coordinate maxSize) throws ExceptionOutOfBounds {
        this.coordinates = new HashSet<>();
        this.coordinatesAttack = new HashSet<>();
        if (direction.equals(Direction.Down)) {
            if (y + size > maxSize.getY()) throw new ExceptionOutOfBounds("Выход за пределы y");
            for (int i = y; i < y + size; i++) {
                coordinates.add(new Coordinate(x, i));
            }
        } else if (direction.equals(Direction.Up)) {
            if (y - size < 0) throw new ExceptionOutOfBounds("Выход за пределы y");
            for (int i = y; i > y - size; i--) {
                coordinates.add(new Coordinate(x, i));
            }
        } else if (direction == Direction.Right) {
            if (x + size > maxSize.getX()) throw new ExceptionOutOfBounds("Выход за пределы x");
            for (int i = x; i < x + size; i++) {
                coordinates.add(new Coordinate(i, y));
            }
        } else if (direction == Direction.Left) {
            if (x - size < 0) throw new ExceptionOutOfBounds("Выход за пределы x");
            for (int i = x; i > x - size; i--) {
                coordinates.add(new Coordinate(i, y));
            }
        }


    }

    public Set<Coordinate> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Set<Coordinate> coordinates) {
        this.coordinates = coordinates;
    }

    public Set<Coordinate> getCoordinatesAttack() {
        return coordinatesAttack;
    }

    public void setCoordinatesAttack(Set<Coordinate> coordinatesAttack) {
        this.coordinatesAttack = coordinatesAttack;
    }
}
