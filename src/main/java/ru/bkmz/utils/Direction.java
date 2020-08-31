package ru.bkmz.utils;

public class Direction {
    String direction;
    public final static Direction Up = new Direction("Up");
    public final static Direction Down = new Direction("Down");
    public final static Direction Right = new Direction("Right");
    public final static Direction Left = new Direction("Left");

    public Direction(String direction) {
        this.direction = direction;
    }

    public static Direction getId(int nextInt) {
        switch (nextInt) {
            case 1:
                return Direction.Down;

            case 2:
                return Direction.Up;

            case 3:
                return Direction.Right;

            case 4:
                return Direction.Left;
        }
        return null;
    }
}
