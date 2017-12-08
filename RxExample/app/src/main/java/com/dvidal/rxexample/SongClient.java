package com.dvidal.rxexample;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by diegovidal on 13/11/2017.
 */

public class SongClient {

    public List<String> getSongs(){

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return getSongsFromServer();
    }

    private List<String> getSongsFromServer(){

        List<String> songs = new ArrayList<>();

        songs.add("Dont Look Back in Anger");
        songs.add("The Masterplan");
        songs.add("Supersonic");
        songs.add("The Death of You and Me");
        songs.add("Wonderwall");

        return songs;
    }
}
