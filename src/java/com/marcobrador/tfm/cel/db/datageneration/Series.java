package com.marcobrador.tfm.cel.db.datageneration;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that represents a TV series.
 */
public class Series {

    private String mName;
    private int mNumSeasons;

    public Series(String name, int numSeasons) {
        mName = name;
        mNumSeasons = numSeasons;
    }

    public String getName() {
        return mName;
    }

    public int getNumSeasons() {
        return mNumSeasons;
    }

    public List<SeriesSeason> getSeriesSeasons() {
        List<SeriesSeason> seasons = new ArrayList<SeriesSeason>();
        for (int i = 0; i < mNumSeasons; i++) {
            SeriesSeason season = new SeriesSeason.Builder()
                    .setSeries(this)
                    .setSeasonNumber(i + 1)
                    .build();
            seasons.add(season);
        }
        return seasons;
    }
}
