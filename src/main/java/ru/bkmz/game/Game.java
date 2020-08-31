package ru.bkmz.game;

import ru.bkmz.game.ship.Ship;
import ru.bkmz.game.ship.coord.Coordinate;
import ru.bkmz.utils.ColorLine;
import ru.bkmz.utils.Direction;
import ru.bkmz.utils.ExceptionOutOfBounds;

import java.awt.*;
import java.io.IOException;
import java.util.*;

public class Game {
    Scanner scn;

    Player playerOne;
    Player playerTwo;

    String horizontal = "═", vertical = "║", DAR = "╔", DAL = "╗", UAR = "╚", UAL = "╝", nul = "█";
    String error = "";
    Coordinate coordinate;

    Map<String, Coordinate> coordinatesMap;

    boolean game = false;
    int dimensions;
    int fielddimensions;

    public Game(int dimensions) {
        this.dimensions = 20 + (dimensions * 2);
        this.coordinatesMap = new TreeMap<>();
        scn = new Scanner(System.in);
        coordinate = new Coordinate(this.dimensions / 2, this.dimensions / 2);
        init();
    }

    public void init() {
//        playerOne = new Player(question("Введите имя первого игрока: ", ColorLine.ANSI_RESET).next());
//        playerTwo = new Player(question("Введите имя второго игрока: ", ColorLine.ANSI_RESET).next());
        playerOne = new Player("1", coordinate);
        playerTwo = new Player("2", coordinate);


        start(playerOne);
    }

    private void start(Player player) {
        clearConsole(error);
        render(player);
        System.out.println("Выберите корабль: ");
        System.out.println("(1): четырехпалубный\t|\t стоит:" + player.shipFour.size() + "/1");
        System.out.println("(2): трехпалубных\t|\t стоит:" + player.shipTree.size() + "/2");
        System.out.println("(3): двухпалубных\t|\t стоит:" + player.shipTwo.size() + "/3");
        System.out.println("(4): однопалубных\t|\t стоит:" + player.shipOne.size() + "/4");
        System.out.println("(5): сменить игрока");
        System.out.println("(6): начать игру (будет ходить первым игрок который выбран)");
        System.out.print("Введите номер коробя: ");
        try {
            switch (scn.next()) {
                case "1":
                    addShip(player, player.shipFour, 1, 4);
                    break;
                case "2":
                    addShip(player, player.shipTree, 2, 3);
                    break;
                case "3":
                    addShip(player, player.shipTwo, 3, 2);
                    break;
                case "4":
                    addShip(player, player.shipOne, 4, 1);
                    break;
                case "5":
                    if (player == playerOne) {
                        player = playerTwo;
                    } else {
                        player = playerOne;
                    }
                    break;
                case "6":
                    if (playerOne.ready() & playerTwo.ready()) {
                        game = true;
                        play(player);
                    } else error = "Не все коробили расставлены";
                    break;
                default:
                    error = "что-то пошло не так";

            }
        } catch (Exception e) {
            e.printStackTrace();
            error = "что-то пошло не так";
        }
        start(player);
    }

    private void play(Player player) {
        clearConsole(error);

        render(player);
        System.out.println("Вы атакуете игрока " + player.name);
        boolean kill = false;
        try {
            kill = player.attack(coordinatesMap.get(question("Введите поле корабля: ", ColorLine.ANSI_RESET).next()));
        } catch (Exception e) {
            error = "выход за пределы поля";
        }
        if (kill) {
            error = "Попал!";
        } else {
            error = ColorLine.ANSI_BLUE + "Промах ;(";
        }
        clearConsole(error);
        render(player);
        pauseConsole("");
        if (player.allShipKill()) {
            clearConsole(error);
            render(player);
            System.out.println("Проиграл игрок: " + player.name);
            System.exit(0);
        }
        if (!kill)
            if (player == playerOne) {
                play(playerTwo);
            } else {
                play(playerOne);
            }

        else play(player);

    }

    private void addShip(Player player, Set<Ship> ships, int maxShip, int size) {
        if (ships.size() == maxShip) {
            error = "королей нет";
        } else {
            System.out.print("Ведите адрес ячейки(A/1):");
            String in = scn.next();
            System.out.println("Ведите направление:");
            System.out.println("(1) Вниз");
            System.out.println("(2) Вверх");
            System.out.println("(3) Право");
            System.out.println("(4) Влево");
            System.out.print("Введите номер:");
            Direction direction = Direction.getId(scn.nextInt());
            try {
                Ship ship = new Ship(Color.green, coordinatesMap.get(in), size, direction, this.coordinate);
                boolean intersection = false;
                for (Ship shipOld : player.allShip()) {
                    for (Coordinate coordinate : shipOld.getCoordinates()) {
                        for (Coordinate coordinateNew : ship.getCoordinates()) {
                            if (coordinate.getX() == coordinateNew.getX() & coordinate.getY() == coordinateNew.getY()) {
                                intersection = true;
                                break;
                            }
                        }
                    }
                }
                if (!intersection)
                    ships.add(ship);
                else error = "Корабли накладываются друг на друга";
            } catch (ExceptionOutOfBounds exceptionOutOfBounds) {
                exceptionOutOfBounds.printStackTrace();
            }
        }
    }

    private void render(Player player) {
        System.out.println("\t игрок:" + player.name);
        System.out.print("\t ");
        for (char i = 65; i < 65 + dimensions / 2; i++) {
            System.out.print(i);
            indent(1);
        }
        System.out.println();
        int number = 1;
        int realX = 0, realY = 0;
        System.out.print("\t");
        for (int y = 0; y <= dimensions; y++) {
            for (int x = 0; x <= dimensions; x++) {
                if (y == 0 & x == 0) {
                    System.out.print(DAR);
                } else if (y == dimensions & x == 0) {
                    System.out.print(UAR);
                } else if (y == 0 & x == dimensions) {
                    System.out.print(DAL);
                } else if (y == dimensions & x == dimensions) {
                    System.out.print(UAL);
                } else if (y == 0 & x != 0 & x < dimensions) {
                    System.out.print(horizontal);
                } else if (y % 2 == 0 & x > 0 & x < dimensions) {
                    System.out.print(horizontal);
                } else if (x % 2 == 0 & y > 0 & y < dimensions) {
                    System.out.print(vertical);
                } else if (x == 0 | x == dimensions) {
                    System.out.print(vertical);
                } else if (y == dimensions) {
                    System.out.print(horizontal);
                } else {
                    coordinatesMap.put((char) (65 + realX) + "/" + (number - 1), new Coordinate(realX, realY));
                    if (!game) {
                        if (player.thisIsShip(realX, realY)) {

                            System.out.print(new ColorLine(ColorLine.ANSI_GREEN).colorLine(nul));

                        } else System.out.print(nul);
                    } else {
                        if (player.thisIsShipHits(realX, realY)) {

                            System.out.print(new ColorLine(ColorLine.ANSI_RED).colorLine(nul));

                        } else {
                            boolean b = true;
                            for (Coordinate hit : player.hits) {
                                if (hit.getX() == realX & hit.getY() == realY) {
                                    System.out.print(new ColorLine(ColorLine.ANSI_BLUE).colorLine(nul));
                                    b = false;
                                    break;
                                }
                            }
                            if (b)
                                System.out.print(nul);

                        }


                    }
                    realX++;
                }


            }
            realX = 0;
            System.out.println();
            if (y % 2 == 0 & y < dimensions) {
                System.out.print((number++) + "\t");
                if (y != 0) {

                    realY++;
                }
            } else System.out.print("\t");

        }

    }

    private Scanner question(String s, ColorLine colorLine) {
        System.out.print(new ColorLine(colorLine).colorLine(s));
        return scn;
    }


    public final void clearConsole(String error) {
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        } catch (IOException | InterruptedException ex) {
        }
        System.out.println(new ColorLine(ColorLine.ANSI_RED).colorLine(error));
        this.error = "";
    }

    public final void pauseConsole(String error) {
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "pause").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("read -p");
        } catch (IOException | InterruptedException ex) {
        }
        System.out.println(new ColorLine(ColorLine.ANSI_RED).colorLine(error));
        this.error = "";
    }

    private void indent(int size) {
        for (int i = 0; i < size; i++) {
            System.out.print(" ");
        }
    }
}
