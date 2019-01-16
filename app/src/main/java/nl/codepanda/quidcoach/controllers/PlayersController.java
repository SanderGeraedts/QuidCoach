package nl.codepanda.quidcoach.controllers;

import android.content.Context;

import com.android.volley.Cache;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import nl.codepanda.quidcoach.models.Player;

public class PlayersController {
    public final static String API_URL = "https://training-drills.herokuapp.com/players";
    public final static float BASE_LAT = 51.44653399903774f;
    public final static float BASE_LON = 5.438819646341946f;

    public final static float DIFF_LAT = 51.44707298917047f - 51.44653399903774f;
    public final static float DIFF_LON = 5.4383440000000345f - 5.438819646341946f;

    public static List<Player> getPlayers() {
        List<Player> players = new ArrayList<>();

        for (int i = 0; i < 14; i++) {
            Random random = new Random();

            float lat = BASE_LAT + random.nextFloat() * DIFF_LAT;
            float lon = BASE_LON + random.nextFloat() * DIFF_LON;

            String position;

            if(i < 6) {
                position = "Chaser";
            } else if (i < 8) {
                position = "Keeper";
            } else if (i < 12) {
                position = "Beater";
            } else {
                position = "Seeker";
            }

            Player player = new Player( String.valueOf(i), position, lat, lon);

            players.add(player);
        }

        return players;
    }
}
