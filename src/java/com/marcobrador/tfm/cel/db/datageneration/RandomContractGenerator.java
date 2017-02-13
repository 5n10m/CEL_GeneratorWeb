package com.marcobrador.tfm.cel.db.datageneration;

import com.marcobrador.tfm.cel.db.model.*;
import com.marcobrador.tfm.cel.db.model.actions.Distribute;
import com.marcobrador.tfm.cel.db.model.actions.Payment;
import com.marcobrador.tfm.cel.db.model.constraints.*;

import java.util.*;

/**
 * Class in charge of generating random contracts.
 */
public class RandomContractGenerator {

    private static final String[] COUNTRY_NAMES = {
            "UK",
            "US",
            "Spain",
            "France",
            "Italy",
            "Deutschland",
            "Russia",
            "Netherlands",
            "Belgium",
            "Norway"
    };
    private static final Integer CONTRACT_DURATIONS_IN_MONTHS[] = { 1, 2, 3, 6, 12, 18, 24 };
    private static final Integer INCOME_PERCENTAGES[] = { 5, 10, 15, 20 };

    private static RandomContractGenerator mInstance;

    private Random mRandom;
    private List<Producer> mProducers;
    private List<Broadcaster> mBroadcasters;

    private RandomContractGenerator() {
        mRandom = new Random();
        mProducers = generateProducersList();
        mBroadcasters = generateBroadcastersList();
    }

    public static RandomContractGenerator getInstance() {
        if (mInstance == null) {
            mInstance = new RandomContractGenerator();
        }
        return mInstance;
    }

    public Contract generate() {
        // Generate common elements
        Producer producer = getRandomItemFromList(mProducers);
        Broadcaster broadcaster = getRandomItemFromList(mBroadcasters);
        Series series = getRandomItemFromList((producer).getSeries());
        DeonticStructuredClause.CelObject seriesSeason
                = new DeonticStructuredClause.CelObject(getRandomItemFromList(series.getSeriesSeasons()));
        SpatialContext spatialContext = generateSpatialContext();
        TemporalContext temporalContext = generateTemporalContext();
        // Build operative part
        OperativePart.Builder operativePartBuilder = new OperativePart.Builder();
        IprePermission permission1
                = generatePermission1(producer, broadcaster, seriesSeason, spatialContext, temporalContext);
        operativePartBuilder.addClause(permission1);
        operativePartBuilder.addClause(generateObligation1(
                producer,
                broadcaster,
                false,
                1,
                permission1.getAct(),
                temporalContext,
                spatialContext,
                getAccessPolicy(broadcaster)));
        IprePermission permission2
                = generatePermission2(producer, broadcaster, seriesSeason, spatialContext, temporalContext);
        if (permission2 != null) {
            operativePartBuilder.addClause(permission2);
        }
        Body body = new Body(operativePartBuilder.build());
        int randomId = mRandom.nextInt();
        if (randomId < 0) {
            randomId = -1 * randomId;
        }
        String contractId = producer.getId() + "_" + broadcaster.getId() + "_" + series.getName() + "_" + randomId;
        Contract contract = new Contract.Builder(contractId.toLowerCase().replaceAll("\\s",""), body)
                .addParty(producer)
                .addParty(broadcaster)
                .setTextVersion("Not available")
                .build();
        return contract;
    }

    private List<Producer> generateProducersList() {
        List<Producer> result = new ArrayList<Producer>();
        PartyBasicGroup all3mediaPbg = new Organization.Builder("All 3 Media")
                .setIdentifier("all3media_id")
                .setDescription("A leading creator of content with an impeccable track record and unparalleled reputation around the world – we are committed to creating outstanding TV, film and digital content and to innovate in the ever-evolving broadcast landscape.")
                .build();
        Producer all3media = new Producer.Builder("all3media", all3mediaPbg).build();
        List<Series> all3mediaSeries = generateAll3mediaSeriesList();
        for (Series series : all3mediaSeries) {
            all3media.addSeries(series);
        }
        result.add(all3media);
        PartyBasicGroup banijayPbg = new Organization.Builder("Banijay Entertainment")
                .setIdentifier("banijay_entertainment_id")
                .setDescription("The Banijay Group is a content creation company for television and multimedia platforms. With leading production entities in more than a dozen territories, the Group is engaged in strategic partnerships with innovative creative talent around the world.")
                .build();
        Producer banijay = new Producer.Builder("banijay", banijayPbg).build();
        List<Series> banijaySeries = generateBanijaySeriesList();
        for (Series series : banijaySeries) {
            banijay.addSeries(series);
        }
        result.add(banijay);
        PartyBasicGroup endemolPbg = new Organization.Builder("Endemol Shine Group")
                .setIdentifier("endemol_group_id")
                .setDescription("Endemol Shine Group is a global production powerhouse creating world class content for all platforms.")
                .build();
        Producer endemol = new Producer.Builder("endemol", endemolPbg).build();
        List<Series> endemolSeries = generateEndemolSeriesList();
        for (Series series : endemolSeries) {
            endemol.addSeries(series);
        }
        result.add(endemol);
        return result;
    }

    private List<Series> generateAll3mediaSeriesList() {
        List<Series> series = new ArrayList<Series>();
        series.add(new Series("Fresh Meat", 4));
        series.add(new Series("Cash Cab", 1));
        series.add(new Series("The Village", 2));
        series.add(new Series("Derren Brown", 7));
        series.add(new Series("The Only Way Is Essex", 17));
        return series;
    }

    private List<Series> generateBanijaySeriesList() {
        List<Series> series = new ArrayList<Series>();
        series.add(new Series("The Missing", 2));
        series.add(new Series("Beyond The Darklands", 3));
        series.add(new Series("Dr. Steve-o", 7));
        series.add(new Series("Ski Patrol", 1));
        series.add(new Series("CPH Airport", 5));
        return series;
    }

    private List<Series> generateEndemolSeriesList() {
        List<Series> series = new ArrayList<Series>();
        series.add(new Series("Bron", 3));
        series.add(new Series("The Fall", 3));
        series.add(new Series("Humans", 2));
        series.add(new Series("Intersection", 1));
        series.add(new Series("Kingdom", 3));
        return series;
    }

    private List<Broadcaster> generateBroadcastersList() {
        List<Broadcaster> result = new ArrayList<Broadcaster>();
        PartyBasicGroup bbc = new Organization.Builder("BBC")
                .setIdentifier("bbc_id")
                .setDescription("The BBC is the world's leading public service broadcaster. Its mission is to enrich people's lives with programmes that inform, educate and entertain.")
                .build();
        result.add(new Broadcaster.Builder("bbc", bbc, Broadcaster.BroadcasterType.TV_CHANNEL).build());
        PartyBasicGroup rai = new Organization.Builder("Rai Italia")
                .setIdentifier("rai_id")
                .setDescription("RAI - Italian Radio and Television is the exclusive dealer of the Italian public broadcasting service; produces television-, radio-, satellite-, digital terrestrial- channels.")
                .build();
        result.add(new Broadcaster.Builder("rai", rai, Broadcaster.BroadcasterType.TV_CHANNEL).build());
        PartyBasicGroup tve = new Organization.Builder("TVE Internacional")
                .setIdentifier("tve_id")
                .setDescription("")
                .build();
        result.add(new Broadcaster.Builder("tve", tve, Broadcaster.BroadcasterType.TV_CHANNEL).build());
        PartyBasicGroup franceTv = new Organization.Builder("France Télévisions")
                .setIdentifier("france_tv_id")
                .setDescription("")
                .build();
        result.add(new Broadcaster.Builder("france_tv", franceTv, Broadcaster.BroadcasterType.TV_CHANNEL).build());
        PartyBasicGroup hbo = new Organization.Builder("HBO")
                .setIdentifier("hbo_id")
                .setDescription("")
                .build();
        result.add(new Broadcaster.Builder("hbo", hbo, Broadcaster.BroadcasterType.PREMIUM_TV_CHANNEL).build());
        PartyBasicGroup sky = new Organization.Builder("Sky Limited")
                .setIdentifier("sky_id")
                .setDescription("")
                .build();
        result.add(new Broadcaster.Builder("sky", sky, Broadcaster.BroadcasterType.PREMIUM_TV_CHANNEL).build());
        PartyBasicGroup netflix = new Organization.Builder("Netflix")
                .setIdentifier("netflix_id")
                .setDescription("")
                .build();
        result.add(new Broadcaster.Builder("netflix", netflix, Broadcaster.BroadcasterType.ONLINE_ONLY).build());
        return result;
    }

    private IprePermission generatePermission1(
            Producer producer,
            Broadcaster broadcaster,
            DeonticStructuredClause.CelObject object,
            SpatialContext spatialContext,
            TemporalContext temporalContext) {
        // Generate basic structure: IPRE permission with ID, subject, act and issuer.
        IprePermission.Builder iprePermissionBuilder = new IprePermission.Builder(
                "P01",
                new DeonticStructuredClause.Subject(broadcaster.getId()),
                new DeonticStructuredClause.Act("D01", new Distribute()));
        iprePermissionBuilder.setIssuer(new DeonticStructuredClause.Issuer(producer.getId()));
        // TODO: deal with exclusivity
        iprePermissionBuilder.setObject(object);
        // Generate constraints
        FactIntersection.Builder factIntersectionBuilder = new FactIntersection.Builder();
        if (spatialContext != null) {
            factIntersectionBuilder.addFact(spatialContext);
        }
        if (temporalContext != null) {
            factIntersectionBuilder.addFact(temporalContext);
        }
        factIntersectionBuilder.addFact(getMeans(broadcaster));
        factIntersectionBuilder.addFact(getAccessPolicy(broadcaster));
        Runs runs = generateRuns(broadcaster, temporalContext);
        if (runs != null) {
            factIntersectionBuilder.addFact(runs);
        }
        iprePermissionBuilder.setConstraint(new DeonticStructuredClause.Constraint(factIntersectionBuilder.build()));
        return iprePermissionBuilder.build();
    }

    private IprePermission generatePermission2(
            Producer producer,
            Broadcaster broadcaster,
            DeonticStructuredClause.CelObject object,
            SpatialContext spatialContext,
            TemporalContext temporalContext) {
        // Permission #2 appears only if broadcaster is a TV channel and with 50% probability
        if (broadcaster.getType() == Broadcaster.BroadcasterType.ONLINE_ONLY || mRandom.nextInt(10) < 5) {
            return null;
        }
        // Generate basic structure: IPRE permission with ID, subject, act and issuer.
        IprePermission.Builder iprePermissionBuilder = new IprePermission.Builder(
                "P02",
                new DeonticStructuredClause.Subject(broadcaster.getId()),
                new DeonticStructuredClause.Act("D02", new Distribute()));
        iprePermissionBuilder.setIssuer(new DeonticStructuredClause.Issuer(producer.getId()));
        // TODO: deal with exclusivity (should be the same as in permission #1)
        iprePermissionBuilder.setObject(object);
        // Generate constraints
        FactIntersection.Builder factIntersectionBuilder = new FactIntersection.Builder();
        if (spatialContext != null) {
            factIntersectionBuilder.addFact(spatialContext.clone());
        }
        if (temporalContext != null) {
            factIntersectionBuilder.addFact(getInternetTemporalContext(temporalContext.clone()));
        }
        factIntersectionBuilder.addFact(new Means(Means.Type.Internet));
        factIntersectionBuilder.addFact(getAccessPolicy(broadcaster));
        iprePermissionBuilder.setConstraint(new DeonticStructuredClause.Constraint(factIntersectionBuilder.build()));
        return iprePermissionBuilder.build();
    }

    private DeonticStructuredClause generateObligation1(
            Producer producer,
            Broadcaster broadcaster,
            boolean isPermission1Exclusive,
            int permission1NumSeasons,
            DeonticStructuredClause.Act permission1Act,
            TemporalContext permission1TemporalContext,
            SpatialContext permission1SpatialContext,
            AccessPolicy permission1AccessPolicy) {
        // Generate the payment
        Payment.Builder paymentBuilder = new Payment.Builder(new Payment.Beneficiary(producer.getId()))
                .setIncomeSource(new Payment.IncomeSource(permission1Act.getId()))
                .setCurrency("€");
        if (permission1AccessPolicy.getType() == AccessPolicy.Type.FreeOfCharge) {
            // If access policy is free of charge, payment is a fixed amount computed as follows:
            // amount = X * num_seasons * num_countries * contract_duration_in_months * exclusivity_factor
            // Where "X" and "exclusivity_factor" are constant values, and the result is computed in Million €
            double x = 1;
            double exclusivityFactor = 2;
            paymentBuilder.setAmount(
                    (int) (x * 1000000
                    * permission1NumSeasons
                    * (permission1SpatialContext != null ? permission1SpatialContext.getCountries().size() : 5)
                    * (permission1TemporalContext != null ? getDurationInMonths(permission1TemporalContext) : 36)
                    * (isPermission1Exclusive ? exclusivityFactor : 1)));
        } else {
            // If access policy is subscription, payment is an income percentage:
            paymentBuilder.setIncomePercentage(getRandomItemFromList(Arrays.asList(INCOME_PERCENTAGES)));
        }
        return new Obligation.Builder(
                "O01",
                new DeonticStructuredClause.Subject(broadcaster.getId()),
                new DeonticStructuredClause.Act("P01", paymentBuilder.build()))
                .build();
    }

    private AccessPolicy getAccessPolicy(Broadcaster broadcaster) {
        AccessPolicy.Type accessPolicyType;
        if (broadcaster.getType() == Broadcaster.BroadcasterType.TV_CHANNEL) {
            // For a normal TV, access is free
            accessPolicyType = AccessPolicy.Type.FreeOfCharge;
        } else {
            // For premium TV or Online, access is based on subscription
            accessPolicyType = AccessPolicy.Type.Subscription;
        }
        return new AccessPolicy(accessPolicyType);
    }

    private SpatialContext generateSpatialContext() {
        SpatialContext.Builder spatialContextBuilder = new SpatialContext.Builder();
        List<String> countryNames = Arrays.asList(COUNTRY_NAMES);
        int random = mRandom.nextInt(100);
        if (random < 5) {
            // With 5% probability there is no spatial restriction (worldwide contract)
            return null;
        } else if (random < 55) {
            // With 50% probability contract allows broadcaster to broadcast in 1 country
            spatialContextBuilder.addCountry(getRandomItemFromList(countryNames));
        } else if (random < 85) {
            // With 30% probability contract allows broadcaster to broadcast in 2 countries
            List<String> countries = getNRandomElementsFromList(countryNames, 2);
            for (String country : countries) {
                spatialContextBuilder.addCountry(country);
            }
        } else {
            // With 15% probability contract allows broadcaster to broadcast in 3 countries
            List<String> countries = getNRandomElementsFromList(countryNames, 3);
            for (String country : countries) {
                spatialContextBuilder.addCountry(country);
            }
        }
        return spatialContextBuilder.build();
    }

    private TemporalContext generateTemporalContext() {
        if (mRandom.nextInt(10) < 1) {
            // Temporal context is NOT present with 10% probability
            return null;
        } else {
            Calendar calendar = Calendar.getInstance();
            // Start date can range from 12 months before until 12 months after the present
            int monthsOffset = mRandom.nextInt(25) - 12;
            calendar.add(Calendar.MONTH, monthsOffset);
            Date startDate = calendar.getTime();
            // Duration of the contract can be any of the predefined ones
            int durationInMonths = getRandomItemFromList(Arrays.asList(CONTRACT_DURATIONS_IN_MONTHS));
            calendar.add(Calendar.MONTH, durationInMonths);
            Date endDate = calendar.getTime();
            return new TemporalContext(startDate, endDate);
        }
    }

    private TemporalContext getInternetTemporalContext(TemporalContext temporalContext) {
        int durationInMonths = getDurationInMonths(temporalContext);
        // Interned broadcasting should start with a certain delay with respect to the original broadcasting in TV
        // This delay should be of between 1/4 and 1/2 of the total duration of the contract
        double random = 0.25 * mRandom.nextDouble() + 0.25;
        int delayInMonths = (int) Math.floor(durationInMonths * random);
        Calendar internetBroadcastingStartDate = Calendar.getInstance();
        internetBroadcastingStartDate.setTime(temporalContext.getAfterDate());
        internetBroadcastingStartDate.add(Calendar.MONTH, delayInMonths);
        return new TemporalContext(
                internetBroadcastingStartDate.getTime(),
                temporalContext.getBeforeDate());
    }

    private Means getMeans(Broadcaster broadcaster) {
        Means means;
        if (broadcaster.getType() == Broadcaster.BroadcasterType.ONLINE_ONLY) {
            means = new Means(Means.Type.Internet);
        } else {
            means = new Means(Means.Type.BroadcastTechnology);
        }
        return means;
    }

    private Runs generateRuns(Broadcaster broadcaster, TemporalContext temporalContext) {
        if (broadcaster.getType() == Broadcaster.BroadcasterType.ONLINE_ONLY || temporalContext == null) {
            // If broadcaster is not a TV channel or there is no temporal context, number of runs is unlimited
            return null;
        }
        // For the rest of the cases, the number of runs is only limited with probability 50%
        if (mRandom.nextInt(10) < 5) {
            return null;
        }
        int durationInMonths = getDurationInMonths(temporalContext);
        // number of runs = (duration of contract in months) * random(0.2, 1)
        double random = 0.8 * mRandom.nextDouble() + 0.2;
        int numberOfRuns = (int) Math.floor(durationInMonths * random) + 1;
        return new Runs(numberOfRuns);
    }

    private int getDurationInMonths(TemporalContext temporalContext) {
        Calendar startDate = Calendar.getInstance();
        startDate.setTime(temporalContext.getAfterDate());
        Calendar endDate = Calendar.getInstance();
        endDate.setTime(temporalContext.getBeforeDate());
        int durationInMonths = endDate.get(Calendar.MONTH) - startDate.get(Calendar.MONTH);
        if (durationInMonths <= 0) {
            durationInMonths += 12;
        }
        return durationInMonths;
    }

    private <T> T getRandomItemFromList(List<T> list) {
        int randomIndex = mRandom.nextInt(list.size());
        return list.get(randomIndex);
    }

    private <T> List<T> getNRandomElementsFromList(List<T> list, int n) {
        List<T> elements = new ArrayList<T>();
        while (elements.size() < n) {
            T element = getRandomItemFromList(list);
            if (!elements.contains(element)) {
                elements.add(element);
            }
        }
        return elements;
    }
}
