package Servlets;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author david
 */
import com.marcobrador.tfm.cel.db.model.*;
import com.marcobrador.tfm.cel.db.model.DeonticStructuredClause;
import org.xembly.Directives;
import org.xembly.Xembler;
public class ConditionalCreator {

    public static String WriteContract(Contract c) throws Exception {
        Directives Core = new Directives().add("cel-core:Contract");
        Core.attr("xmlns:cel-core","urn:mpeg:mpeg21:cel:core:2015");
        Core.attr("xmlns:cel-ipre","urn:mpeg:mpeg21:cel:ipre:2015");
        Core.attr("xmlns:dc","http://purl.org/dc/elements/1.1/");
        Core.attr("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");
        Core.attr("xmlns:dii","urn:mpeg:mpeg21:2002:01-DII-NS");
        Core.attr("xsi:schemaLocation","urn:mpeg:mpeg21:cel:core:2015 cel-core.xsd urn:mpeg:mpeg21:cel:ipre:2015 cel-ipre.xsd");
        Core.attr("xmlns:rel-r", "urn:mpeg:mpeg21:2003:01-REL-R-NS");
        
        
        Core.attr("contractId", c.getContractId());
        Core.push();
        if(c.getTextVersion() != null){
            Core.add("cel-core:TextVersion");
                Core.set(c.getTextVersion());
            Core.pop();
        }
        //ADD ENCRYPTED
        //ADD METADATA
        //CONTRACTS RELATED   
        //PARTYS
        for(Party p : c.getParties()){
            Core.push();
            Core.add("cel-core:Party");
                Core.attr("Id", p.getId());
                PartyBasicGroup pbg = p.getPartyBasicGroup();
                if(pbg.getType().equals("Organization")){
                    Core.push();
                    Core.add("cel-core:Organization");
                        Core.push();
                        Core.add("cel-core:Name");
                            Core.set(pbg.getName());
                        Core.pop();
                        Core.push();
                        Core.add("dc:identifier");
                            Core.set(pbg.getIdentifier());
                        Core.pop();
                        Core.push();
                        Core.add("dc:description");
                            Core.set(pbg.getDescription());
                        Core.pop();
                        //ADD SIGNATORY
                    Core.pop();
                }
                else if(pbg.getType().equals("Person")){
                    Core.push();
                    Core.add("cel-core:Person");
                        Core.push();
                        Core.add("cel-core:Name");
                            Core.set(pbg.getName());
                        Core.pop();
                        Core.push();
                        Core.add("dc:identifier");
                            Core.set(pbg.getIdentifier());
                        Core.pop();
                        Core.push();
                        Core.add("dc:description");
                            Core.set(pbg.getDescription());
                        Core.pop();
                        //ADD SIGNATORY
                    Core.pop();
                }
                Core.push();
                if (p.getAddress() != null){
                    Core.add("dc:Address");
                        Core.set(p.getAddress());
                    Core.pop();
                }
            Core.pop();
        }
        //BODY
        
        Core.push();
        Core.add("cel-core:Body");
        for (Body b : c.getBody()){
        OperativePart op = b.getOperativePart();
            Core.push();
            Core.add("cel-core:OperativePart");
            for (DeonticStructuredClause d : op.getClauses()){
                Core.push();
                Core.add("cel-core:" + d.getClass().getSimpleName());
                    Core.attr("id",d.getId());
                    /*SUBJECT*/
                    Core.push();
                    Core.add("cel-core:Subject");
                        Core.attr("PartyRef",d.getSubject().toString().replace("Subject: ", ""));
                    Core.pop();
                    /*ACT*/
                    Core.push();
                    Core.add("cel-core:Act");
                        Core.push();
                        Core.add("cel-gen:" + d.getAct().getAction().toString());
                        Core.pop();
                    Core.pop();
                    /*OBJECT*/
                    Core.push();
                    Core.add("cel-core:Object");
                    DeonticStructuredClause.CelObject o = d.getCelObject();
                    if(o.getItem() != null){
                    //switch(o.getClass().getSimpleName()){
                        //case "Item":
                        Item i = o.getItem();
                        Core.add("cel-core:Item");
                            Core.attr("name",i.getName());
                            Core.add("dii:Identifier");
                                Core.set(i.getRelatedIdentifier());
                            Core.pop();
                        Core.pop();
                        //break;
                       // case "Event":
                    } else if(o.getEvent() != null){
                        Event e = o.getEvent();
                        Core.add("cel-core:Item");
                            Core.attr("name",e.getName());
                            Core.add("dii:Identifier");
                                Core.set(e.getRelatedIdentifier());
                            Core.pop();
                        Core.pop();
                        //break;
                       // case "Subject":
                    } else if(o.getIssuer() != null){
                        DeonticStructuredClause.Issuer is = o.getIssuer(); /*AQUI QUIZAS HAY QUE REMODELAR UN POCO LA COSA*/
                        Core.push();
                        Core.add("cel-core:Subject");
                            Core.attr("PartyRef",is.getPartyRef().replace("Issuer: ", ""));
                        Core.pop();
                        //break;
                    }
                    /*RESLUTANT OBJECT*/
                    
                    /*ISSUER creo que no tengo de eso*/ 
                    
                    Core.pop();
                Core.pop();
            }
            Core.pop();
        Core.pop();    
        }
        System.out.println(new Xembler(Core).xml());
        return new Xembler(Core).xml();
    }
}
