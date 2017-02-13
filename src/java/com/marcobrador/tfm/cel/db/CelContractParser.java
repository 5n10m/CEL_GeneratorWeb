package com.marcobrador.tfm.cel.db;

import com.marcobrador.tfm.cel.db.model.Contract;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

/**
 * Class that wrapps functionality for parsing XML CEL contracts.
 */
public class CelContractParser {

    private static CelContractParser mInstance;

    private JAXBContext mJaxbContext;
    private Unmarshaller mUnmarshaller;
    private Marshaller mMarshaller;

    private CelContractParser() throws JAXBException {
        // Hide default constructor
        mJaxbContext = JAXBContext.newInstance(Contract.class);
        mMarshaller = mJaxbContext.createMarshaller();
        mMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        mUnmarshaller = mJaxbContext.createUnmarshaller();
    }

    /**
     * @return The singleton instance of the CEL Contract parser.
     *
     * @throws JAXBException If the instance does not exists and could not be created.
     */
    public static CelContractParser getInstance() throws JAXBException {
        if (mInstance == null) {
            mInstance = new CelContractParser();
        }
        return mInstance;
    }

    /**
     * Parses an XML file containing a CEL contract and returns an instance of the {@link Contract} class representing it.
     *
     * @param file The {@link File} object containing the XML file.
     *
     * @return A {@link Contract} object representing the given XML.
     *
     * @throws JAXBException If an error occurs while unmarshalling the XML.
     */
    public Contract unmarshallContract(File file) throws JAXBException {
        return (Contract) mUnmarshaller.unmarshal(file);
    }

    /**
     * Creates an XML file representing the given contract.
     *
     * @param contract The contract to be marshalled.
     * @param out The file to which the contract shall be written.
     *
     * @throws JAXBException If an error occurs while unmarshalling the XML.
     */
    public void marshallContract(Contract contract, File out) throws JAXBException {
        mMarshaller.marshal(contract, out);
    }
}
