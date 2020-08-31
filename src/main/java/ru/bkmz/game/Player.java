package ru.bkmz.game;

import ru.bkmz.game.ship.Ship;
import ru.bkmz.game.ship.coord.Coordinate;
import ru.bkmz.utils.ColorLine;
import ru.bkmz.utils.Direction;
import ru.bkmz.utils.ExceptionOutOfBounds;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class Player {
    Set<Ship> shipOne;
    Set<Ship> shipTwo;
    Set<Ship> shipTree;
    Set<Ship> shipFour;
    String name;
    Coordinate coordinate;
    Set<Coordinate> hits;

    public Player(String name, Coordinate coordinate) {
        this.name = name;
        this.coordinate = coordinate;
        shipOne = new HashSet<>();
        shipTwo = new HashSet<>();
        shipTree = new HashSet<>();
        shipFour = new HashSet<>();
        hits = new HashSet<>();
    }

    public Set<Ship> getShipOne() {
        return shipOne;
    }

    public void setShipOne(Set<Ship> shipOne) {
        this.shipOne = shipOne;
    }

    public Set<Ship> getShipTwo() {
        return shipTwo;
    }

    public void setShipTwo(Set<Ship> shipTwo) {
        this.shipTwo = shipTwo;
    }

    public Set<Ship> getShipTree() {
        return shipTree;
    }

    public void setShipTree(Set<Ship> shipTree) {
        this.shipTree = shipTree;
    }

    public Set<Ship> getShipFour() {
        return shipFour;
    }

    public void setShipFour(Set<Ship> shipFour) {
        this.shipFour = shipFour;
    }

    public Set<Ship> allShip() {
        Set<Ship> ships = new HashSet<>();
        ships.addAll(shipOne);
        ships.addAll(shipTwo);
        ships.addAll(shipTree);
        ships.addAll(shipFour);
        return ships;
    }
    public boolean allShipKill() {
        boolean live = true;

        for (Ship ship:allShip()){
            if (ship.isLive()){
                live = false;
                break;
            }
        }

        return live;
    }
    public boolean ready() {
        return 1 + 2 + 3 + 4 == shipOne.size() + shipTwo.size() + shipTree.size() + shipFour.size();
    }

    public void crateShip(Coordinate coordinate, int size, Direction direction) throws ExceptionOutOfBounds {
        shipFour.add(new Ship(Color.green, coordinate.getX(), coordinate.getY(), size, Direction.Down, this.coordinate));
    }

    public boolean thisIsShip(int x, int y) {
        for (Ship ship : allShip()) {
            for (Coordinate coordinate : ship.getCoordinates()) {

                if (coordinate.getX() == x & coordinate.getY() == y) return true;
            }
        }


        return false;
    }
    public boolean thisIsShipHits(int x, int y) {
        for (Ship ship : allShip()) {
            for (Coordinate coordinate : ship.getCoordinatesAttack()) {

                if (coordinate.getX() == x & coordinate.getY() == y) return true;
            }
        }


        return false;
    }
    public Set<Coordinate> getHits() {
        return hits;
    }

    public boolean attack(Coordinate coordinate) throws NullPointerException{
        for (Coordinate coordinateHits : hits) {
            if (coordinateHits.getX() == coordinate.getX() & coordinateHits.getY() == coordinate.getY()) {
                hits.add(coordinate);
                return false;
            }
        }

        hits.add(coordinate);
        for (Ship ship : allShip()) {
            if (ship.isLive()) {
                for (Coordinate coordinateShip : ship.getCoordinates()) {
                    if (coordinateShip.getX() == coordinate.getX() & coordinateShip.getY() == coordinate.getY()) {
                        ship.getCoordinatesAttack().add(coordinateShip);
                        ship.getCoordinates().remove(coordinateShip);
                        if (ship.getCoordinates().size() == 0){
                            ship.setLive(false);
                        }
                        return true;

                    }
                }
            }
        }
        return false;
    }
}

