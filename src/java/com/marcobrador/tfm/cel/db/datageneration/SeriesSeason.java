package com.marcobrador.tfm.cel.db.datageneration;

import com.marcobrador.tfm.cel.db.model.Item;
import com.marcobrador.tfm.cel.db.model.RelatedIdentifier;

/**
 * Class that represents one season of a given series.
 */
public class SeriesSeason extends Item {

    private Series mSeries;
    private int mSeasonNumber;

    private SeriesSeason(Builder builder) {
        super(builder);
        mSeries = builder.mSeries;
        mSeasonNumber = builder.mSeasonNumber;
    }

    public Series getSeries() {
        return mSeries;
    }

    public int getSeasonNumber() {
        return mSeasonNumber;
    }

    public static class Builder extends Item.Builder {
        private Series mSeries;
        private int mSeasonNumber;

        public Builder setSeries(Series series) {
            mSeries = series;
            return this;
        }

        public Builder setSeasonNumber(int seasonNumber) {
            mSeasonNumber = seasonNumber;
            return this;
        }

        public SeriesSeason build() {
            if (mSeries == null) {
                throw new IllegalArgumentException("series must not be null");
            }
            if (mSeasonNumber < 1 || mSeasonNumber > mSeries.getNumSeasons()) {
                throw new IllegalArgumentException("season number must be between " + 1 + " and " + mSeries.getNumSeasons());
            }
            super.name = mSeries.getName() + ", season " + mSeasonNumber;
            super.relatedIdentifier = new RelatedIdentifier(mSeries.getName().replace(" ", "_") + "_" + mSeasonNumber);
            return new SeriesSeason(this);
        }
    }
}
