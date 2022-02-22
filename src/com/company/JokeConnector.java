package com.company;

import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

class JokeConnector {
    public static final String API_URL = "https://v2.jokeapi.dev/joke/";

    public String getJokeData(String JokeCategory) throws IOException {

        URL url = new URL(API_URL + JokeCategory + "?format=json");

        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        return content.toString();
    }

    public Joke getJoke(String JokeCategory) throws IOException {
        String jokeData = getJokeData(JokeCategory);
//        JSONTokener token = new JSONTokener(jokeData);
        JSONObject jokeObject = new JSONObject(jokeData);

        Joke joke = new Joke();
        joke.category = jokeObject.getString("category");
        joke.type = jokeObject.getString("type");
        joke.joke = jokeObject.has("joke") ? jokeObject.getString("joke") : null;
        joke.setup = jokeObject.has("setup") ? jokeObject.getString("setup") : null;
        joke.delivery = jokeObject.has("delivery") ? jokeObject.getString("delivery") : null;
        joke.id = jokeObject.getInt("id");
        joke.safe = jokeObject.getBoolean("safe");
        joke.language = jokeObject.getString("lang");
        joke.nsfw = jokeObject.getJSONObject("flags").getBoolean("nsfw");
        joke.religious = jokeObject.getJSONObject("flags").getBoolean("religious");
        joke.political = jokeObject.getJSONObject("flags").getBoolean("political");
        joke.racist = jokeObject.getJSONObject("flags").getBoolean("racist");
        joke.sexist = jokeObject.getJSONObject("flags").getBoolean("sexist");
        joke.explicit = jokeObject.getJSONObject("flags").getBoolean("explicit");

        return joke;

    }
}