package com.marcobrador.tfm.cel.db.datageneration;

import com.marcobrador.tfm.cel.db.model.Party;
import com.marcobrador.tfm.cel.db.model.PartyBasicGroup;

/**
 * Class that represents a broadcaster.
 */
class Broadcaster extends Party {

    enum BroadcasterType {
        TV_CHANNEL,
        PREMIUM_TV_CHANNEL,
        ONLINE_ONLY
    }

    private BroadcasterType mType;

    private Broadcaster(Builder builder) {
        super(builder);
        mType = builder.mBroadcasterType;
    }

    public BroadcasterType getType() {
        return mType;
    }

    public static final class Builder extends Party.Builder {

        private BroadcasterType mBroadcasterType;

        public Builder(String id, PartyBasicGroup partyBasicGroup, BroadcasterType type) {
            super(id, partyBasicGroup);
            mBroadcasterType = type;
        }

        public Broadcaster build() {
            return new Broadcaster(this);
        }
    }
}
