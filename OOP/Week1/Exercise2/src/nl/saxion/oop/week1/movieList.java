package nl.saxion.oop.week1;

import nl.saxion.app.SaxionApp;

import java.awt.*;

public class movieList {
    private String name;
    private String id;
    private Boolean type;
    private int views;

    public movieList(String name, String id, int views) {
        this.name = name;
        this.id = id;
        this.views = views;
        this.type = false;
    }


    public void watch() {
        type = true;
        views ++;
        SaxionApp.printLine("You have watched: ");
        SaxionApp.printLine(toString(), Color.green);
    }

    @Override public String toString() {
        return name + " — " + id + "(" + views + " views)";
    }

    public void printWatchedStr() {
        SaxionApp.printLine(type ? "[WATCHED]" : "[NOT WATCHED]", type ? Color.green : Color.red);
    }

}
