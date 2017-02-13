package com.marcobrador.tfm.cel.db;

import com.marcobrador.tfm.cel.db.dao.ContractDao;
import com.marcobrador.tfm.cel.db.datageneration.RandomContractGenerator;
import com.marcobrador.tfm.cel.db.model.Contract;
import org.apache.commons.cli.*;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Main class for testing & debugging purposes.
 */
public class Main {

    private static final String OPT_ADD_CONTRACT = "a";
    private static final String OPT_LIST_CONTRACT_IDS = "l";
    private static final String OPT_PRINT_CONTRACT = "p";
    private static final String OPT_GENERATE_RANDOM_CONTRACTS = "g";
    private static final String ARG_PATH = "path";
    private static final String ARG_NUM = "num";
    private static final String ARG_CONTRACT_ID = "contractId";
    private static final String DESC_ADD_CONTRACT = String.format("Adds the contract provided in <%s> to the DB.", ARG_PATH);
    private static final String DESC_LIST_CONTRACT_IDS = "Lists the contract IDs of the contracts stored in the DB.";
    private static final String DESC_PRINT_CONTRACT = String.format("Prints the contract with the given <%s>.", ARG_CONTRACT_ID);
    private static final String DESC_GENERATE_RANDOM_CONTRACTS = String.format("Generates <%s> random contracts and persists them to the DB", ARG_NUM);

    private static Options options;
    static {
        // Create CL options
        options = new Options();
        Option addContractOption = Option.builder(OPT_ADD_CONTRACT)
                .desc(DESC_ADD_CONTRACT)
                .hasArg(true)
                .argName(ARG_PATH)
                .build();
        Option listContractIdsOption = Option.builder(OPT_LIST_CONTRACT_IDS)
                .desc(DESC_LIST_CONTRACT_IDS)
                .hasArg(false)
                .build();
        Option printContractOption = Option.builder(OPT_PRINT_CONTRACT)
                .desc(DESC_PRINT_CONTRACT)
                .hasArg(true)
                .argName(ARG_CONTRACT_ID)
                .build();
        Option generateRandomContractsOption = Option.builder(OPT_GENERATE_RANDOM_CONTRACTS)
                .desc(DESC_GENERATE_RANDOM_CONTRACTS)
                .hasArg(true)
                .argName(ARG_NUM)
                .build();
        options.addOption(addContractOption);
        options.addOption(listContractIdsOption);
        options.addOption(printContractOption);
        options.addOption(generateRandomContractsOption);
    }

    public static void main(String[] args) {
        // (Partially) Disable hibernate logger:
        Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);

        // Parse given args
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd;
        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            System.out.println("Failed to parse command line options: " + e.getMessage());
            e.printStackTrace();
            printHelp();
            return;
        }

        if (cmd.hasOption(OPT_ADD_CONTRACT)) {
            String path = cmd.getOptionValue(OPT_ADD_CONTRACT);
            addContract(path);
        } else if (cmd.hasOption(OPT_LIST_CONTRACT_IDS)) {
            listContracts();
        } else if (cmd.hasOption(OPT_PRINT_CONTRACT)) {
            String contractId = cmd.getOptionValue(OPT_PRINT_CONTRACT);
            printContract(contractId);
        } else if (cmd.hasOption(OPT_GENERATE_RANDOM_CONTRACTS)) {
            String numContractsString = cmd.getOptionValue(OPT_GENERATE_RANDOM_CONTRACTS, "1");
            int numContracts;
            try {
                numContracts = Integer.parseInt(numContractsString);
            } catch (NumberFormatException e) {
                System.err.println("ERROR: could not parse " + numContractsString + ". Should be an integer.");
                return;
            }
            Map<String, Contract> generatedContracts = new HashMap<String, Contract>();
            ContractDao contractDao = new ContractDao();
            for (int i = 0; i < numContracts; i++) {
                Contract contract = RandomContractGenerator.getInstance().generate();
                System.out.print(contract);
                String contractId = contractDao.save(contract);
                generatedContracts.put(contractId, contract);
            }
            for (String contractId : generatedContracts.keySet()) {
                Object retrievedContract = contractDao.getByKey(contractId);
                if (retrievedContract.equals(generatedContracts.get(contractId))) {
                    System.out.println("Contract persisted/retrieved successfully: " + contractId);
                } else {
                    System.out.println("Error persisting/retrieving contract " + contractId);
                    break;
                }
            }
        } else {
            printHelp();
            return;
        }
        // Make sure to close connection to underlying DB
        HibernateSessionWrapper.getInstance().shutdown();
    }

    /**
     * Parses a contract from the given path and adds it to the DB.
     *
     * @param path Path to the XML definition of the contract to be added.
     */
    private static void addContract(String path) {
        // Unmarshall contract from XML
        Contract contract;
        try {
            contract = CelContractParser.getInstance().unmarshallContract(new File(path));
            System.out.print(contract);
        } catch (Exception e) {
            System.out.println("Failed to unmarshall XML file: " + e.getMessage());
            e.printStackTrace();
            return;
        }

        // Write contract to DB
        ContractDao contractDao = new ContractDao();
        String id;
        try {
            id = contractDao.save(contract);
            System.out.println("Contract persisted to DB with ID: " + id);
        } catch (Exception e) {
            System.out.println("Failed to persist contract to DB: " + e.getMessage());
            e.printStackTrace();
            return;
        }

        // Retrieve contract from DB and check that it's equal to the one that we wanted to persist.
        Object retrieved = contractDao.getByKey(id);
        if (!contract.equals(retrieved)) {
            System.out.println("Contract retrieved from DB is not equal to the original one.");
            System.out.println("Original:");
            System.out.println(contract);
            System.out.println("Retrieved:");
            System.out.println(retrieved);
        }

        try {
            CelContractParser.getInstance().marshallContract((Contract) retrieved, new File(path + "_retrieved"));
        } catch (Exception e) {
            System.out.println("Failed to marshall XML file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Lists the contract IDs of the contracts stored in the DB.
     */
    private static void listContracts() {
        try {
            List<String> contractIds = new ContractDao().listContractIds();
            System.out.println("Contract IDs of all contracts stored in the DB:");
            for (String contractId : contractIds) {
                System.out.println("- " + contractId);
            }
        } catch (Exception e) {
            System.out.println("Failed to list contracts in DB: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Retrieves the specified contract from the DB and prints it.
     *
     * @param contractId The ID of the contract to be printed.l
     */
    private static void printContract(String contractId) {
        try {
            Object contract = new ContractDao().getByKey(contractId);
            System.out.println(contract);
        } catch (Exception e) {
            System.out.println("Failed to get contract <" + contractId + ">: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Prints usage help to stdout.
     */
    private static void printHelp() {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("Main [option]", options);
    }
}
