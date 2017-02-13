package com.marcobrador.tfm.cel.db.datageneration;

import com.marcobrador.tfm.cel.db.model.Item;
import com.marcobrador.tfm.cel.db.model.Party;
import com.marcobrador.tfm.cel.db.model.PartyBasicGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that represents a TV show producer.
 */
class Producer extends Party {

    private List<Series> mSeries;

    private Producer(Builder builder) {
        super(builder);
        mSeries = new ArrayList<Series>();
    }

    public List<Series> getSeries() {
        return mSeries;
    }

    public void addSeries(Series series) {
        mSeries.add(series);
    }

    public static final class Builder extends Party.Builder {

        public Builder(String id, PartyBasicGroup partyBasicGroup) {
            super(id, partyBasicGroup);
        }

        public Producer build() {
            return new Producer(this);
        }
    }
}
