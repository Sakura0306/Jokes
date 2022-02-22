package com.company;

import java.util.*;

public class Main {

    public static List<Joke> jokes = new ArrayList<>();

    public static void main(String[] args) {
        while (true) {
            JokeHelper.downloadJokes();
            System.out.println("You now have " + jokes.size() + " jokes at your list");

            JokeHelper.sortJokeListByLength();
            JokeHelper.religiousJokesPrint();
            JokeHelper.printTwoPartJokes();
            JokeHelper.continueJokes();
        }
    }
}