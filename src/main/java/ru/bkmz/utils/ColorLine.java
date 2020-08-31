package ru.bkmz.utils;

public class ColorLine {
    private String color;
    public static final ColorLine ANSI_RESET = new ColorLine( "\u001B[0m");
    public static final ColorLine ANSI_BLACK = new ColorLine( "\u001B[30m");
    public static final ColorLine ANSI_RED = new ColorLine( "\u001B[31m");
    public static final ColorLine ANSI_GREEN = new ColorLine( "\u001B[32m");
    public static final ColorLine ANSI_YELLOW = new ColorLine( "\u001B[33m");
    public static final ColorLine ANSI_BLUE = new ColorLine( "\u001B[34m");
    public static final ColorLine ANSI_PURPLE = new ColorLine( "\u001B[35m");
    public static final ColorLine ANSI_CYAN = new ColorLine( "\u001B[36m");
    public static final ColorLine ANSI_WHITE = new ColorLine( "\u001B[37m");

    public ColorLine(String s) {
        color = s;
    }

    public ColorLine(ColorLine colorLine) {
        color = colorLine.color;
    }

    public  String colorLine(String s){
        return color + s + ANSI_RESET.color;
    }
}
