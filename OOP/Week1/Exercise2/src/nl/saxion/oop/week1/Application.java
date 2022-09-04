package nl.saxion.oop.week1;

import nl.saxion.app.CsvReader;
import nl.saxion.app.SaxionApp;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class Application implements Runnable {
    public static void main(String[] args) {
        SaxionApp.start(new Application(), 1200, 600);
    }

    public void run() {
        // TODO: Load videos from csv file.

        ArrayList<movieList> movieArrayList = new ArrayList<movieList>();

        CsvReader reader = new CsvReader("/Users/yanalazareva/Documents/Saxion/OOP/Week1/Exercise2/youtubevideos.csv");
        reader.setSeparator(';');
        reader.skipRow();
        while (reader.loadRow()) {
            movieList temp = new movieList(
                    reader.getString(1),
                    reader.getString(0),
                    reader.getInt(3)
            );
            movieArrayList.add(temp);
        }

        int menuInput = -1;

        do {
            SaxionApp.clear();
            SaxionApp.printLine("***************************************");
            SaxionApp.printLine("* 1) Show watchlist ");
            SaxionApp.printLine("* 2) Watch video");
            SaxionApp.printLine("* 0) Exit program");
            SaxionApp.printLine("***************************************");

            SaxionApp.print("Please make a selection from the menu: ");
            menuInput = SaxionApp.readInt();

            SaxionApp.printLine();



            if (menuInput == 1) {
                int counter = 1;
                for (movieList temp :
                        movieArrayList) {
                    SaxionApp.print(counter + ") " + temp.toString());
                    temp.printWatchedStr();
                    counter++;
                }
                SaxionApp.pause();

            } else if (menuInput == 2) {
                int counter = 1;
                for (movieList temp :
                        movieArrayList) {
                    SaxionApp.print(counter + ") " + temp.toString());
                    temp.printWatchedStr();
                    counter++;
                }
                int selectedVideo;
                do {
                    SaxionApp.print("Please select a video to watch: ");
                    selectedVideo = SaxionApp.readInt();
                } while (selectedVideo < 1 || selectedVideo > movieArrayList.size());
                movieArrayList.get(selectedVideo - 1).watch();
                SaxionApp.pause();
            }
             else {
                SaxionApp.printLine("Goodbye!");
            }

        } while (menuInput != 0);
    }}