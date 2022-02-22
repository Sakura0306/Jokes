package com.company;

import java.util.*;

import static com.company.Main.jokes;

public class JokeHelper {

    public static int scanInt(){
        Scanner scan = new Scanner(System.in);
        return scan.nextInt();
    }

    public static String scanString(){
        Scanner scan = new Scanner(System.in);
        return scan.nextLine();
    }

    public static void downloadJokes() {
        System.out.println("How many jokes do you want to download?");
        int size = JokeHelper.scanInt();
        JokeConnector connector = new JokeConnector();
        for (int i = 0; i < size; i++) {
            try {
                jokes.add(connector.getJoke("Any"));
            } catch (Exception e) {
                System.out.println("Sorry, we have a problem");
                e.printStackTrace();
            }
        }
    }


    public static void sortJokeListByLength() {
        Comparator<Joke> jokeComparator = Comparator.comparing(Joke::getJokeLength);

        Collections.sort(jokes, jokeComparator);
        System.out.println("Shortest joke is: ");
        System.out.println(jokes.get(0));
    }

    public static void religiousJokesPrint(){
        List<Joke> religiousJokes = getReligiousJokes();
        if (religiousJokes.size() != 0) {
            System.out.println("Do you want to see downloaded religious jokes? yes / no");
            String doReligious = scanString();
            if (doReligious.equalsIgnoreCase("yes")) {
                System.out.println("Downloaded religious jokes: ");
                for(Joke j: religiousJokes)
                    System.out.println(j);
            }
        }
    }

    public static void printTwoPartJokes() {
        List<Joke> twoPartJokes = getTwoPartJokes();
        System.out.println("Do you want to see how many two part jokes were downloaded? yes / no");
        String twoPart = scanString();
        if (twoPart.equalsIgnoreCase("yes")) {
            int twoPartJokesCounter = countTwoPartJokes();
            System.out.println("You downloaded " + twoPartJokesCounter + "two part jokes");
            for(Joke j: twoPartJokes)
                System.out.println(j);
        }
    }

    public static void continueJokes() {
        System.out.println("Do you want to delete downloaded jokes? yes / no");
        String delete = scanString();
        if (delete.equalsIgnoreCase("yes")) {
            jokes.clear();
        }
        System.out.println("Do you want to download more jokes? yes / no");
        String downloadMore = scanString();
        if (!downloadMore.equalsIgnoreCase("yes")) {
            System.exit(0);
        }
    }

    private static int countTwoPartJokes() {
        int twoPartJokesCounter = 0;
        for (var joke : jokes) {
            if (joke.setup != null) {
                twoPartJokesCounter++;
            }
        }
        return twoPartJokesCounter;
    }


    private static List<Joke> getReligiousJokes() {
        List<Joke> religiousJokes = new ArrayList<>();
        for (var joke : jokes) {
            if (joke.religious) {
                religiousJokes.add(joke);
            }
        }
        return religiousJokes;
    }

    private static List<Joke> getTwoPartJokes() {
        List<Joke> twoPartJokes = new ArrayList<>();
        for (var joke : jokes) {
            if (joke.setup != null) {
                twoPartJokes.add(joke);
            }
        }
        return twoPartJokes;
    }

}
