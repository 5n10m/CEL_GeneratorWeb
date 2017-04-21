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
import java.io.File;
import java.io.FileWriter;
import javax.servlet.ServletContext;
import com.marcobrador.tfm.cel.db.model.*;
import com.marcobrador.tfm.cel.db.model.DeonticStructuredClause;
import com.marcobrador.tfm.cel.db.model.PreCondition;
import org.xembly.Directives;
import org.xembly.Xembler;
public class ConditionalCreator {

    public static String WriteContract(Contract c, String path) throws Exception {
        Directives Core = new Directives().add("cel-core:Contract");
        Core.attr("xmlns:cel-core","urn:mpeg:mpeg21:cel:core:2015");
        Core.attr("xmlns:cel-gen","urn:mpeg:mpeg21:cel:gen:2017");
        Core.attr("xmlns:dc","http://purl.org/dc/elements/1.1/");
        Core.attr("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");
        Core.attr("xmlns:dii","urn:mpeg:mpeg21:2002:01-DII-NS");
        Core.attr("xsi:schemaLocation","urn:mpeg:mpeg21:cel:core:2015 cel-core_v2.xsd urn:mpeg:mpeg21:cel:gen:2017 cel-gen_v4.xsd");
        Core.attr("xmlns:rel-r", "urn:mpeg:mpeg21:2003:01-REL-R-NS");
        
        
        Core.attr("contractId", c.getContractId());
        if(c.getTextVersion() != null){
            Core.push();
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
            Core.add("cel-gen:PartyGen");
                Core.attr("id", p.getId());
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
                        if ( pbg.getDescription().length() > 0){
                            Core.push();
                            Core.add("dc:description");
                                Core.set(pbg.getDescription());
                            Core.pop();
                        }
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
                        if ( pbg.getDescription().length() > 0){
                            Core.push();
                            Core.add("dc:description");
                                Core.set(pbg.getDescription());
                            Core.pop();
                        }
                        //ADD SIGNATORY
                    Core.pop();
                }
                if (p.getAddress() != null){
                    Core.push();
                    Core.add("cel-core:Address");
                        Core.set(p.getAddress());
                    Core.pop();
                }
                //ESTO NOSE SI SERIA ASÍ
                if (p.getRol() != null){
                    Core.push();
                    Core.add("cel-gen:Rol");
                        Core.set(p.getRol());
                    Core.pop();
                }
            Core.pop();
        }
        //BODY
        Core.push();
        Core.add("cel-core:Body");
        for (Body b : c.getBody()){
            OperativePart op = b.getOperativePart();
        /*Textual part*/
            for (Statement s: op.getStatemens()){
                Core.push();
                Core.add("cel-core:TextualPart");
                    Core.push();
                    Core.add("cel-core:TextClause");
                        Core.attr("id", s.getId());
                        Core.push();
                        Core.add("cel-core:TextParagraph");
                            Core.attr("id", s.getId()+"i");
                            Core.set(s.getValue());
                        Core.pop();
                    Core.pop();
                Core.pop();
            }
        /*Operative Part*/
            Core.push();
            Core.add("cel-core:OperativePart");
            for (DeonticStructuredClause d : op.getClauses()){
                Core.push();
                Core.add("cel-core:" + d.getClass().getSimpleName());
                    Core.attr("id",d.getId());
                    /*PRECONDITIONS*/
                    if (d.getPreConditions() != null && !d.getPreConditions().isEmpty() ){
                        for (PreCondition preC: d.getPreConditions()){
                            Core.push();
                            Core.add("cel-core:PreCondition");
                                Core.attr("idref", preC.getIdref());
                                Core.attr("actionStatus", preC.getActionStatus());
                                if (preC.getWithDelay().length() > 0) Core.attr("withDelay", preC.getWithDelay());
                                if (preC.getValidity().length() > 0)Core.attr("validity", preC.getValidity());
                            Core.pop();
                        }
                    }
                    /*SUBJECT*/
                    if (d.getSubject().getPartyRef().length() > 0){
                        Core.push();
                        Core.add("cel-core:Subject");
                            Core.attr("partyRef",d.getSubject().getPartyRef());
                        Core.pop();
                    }
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
                        Core.push();
                        Core.add("cel-gen:ItemType");
                            Core.attr("name",i.getName());
                            Core.push();
                            Core.add("dii:Identifier");
                                Core.set(i.getRelatedIdentifier());
                            Core.pop();
                            if(i.getRegion() != null) {
                                Core.push();
                                Core.add("cel-gen:Region");                                    
                                    if(i.getRegion().getIdentifier() != null){
                                        Core.push();
                                        Core.add("Id");
                                            Core.set(i.getRegion().getIdentifier());
                                        Core.pop();
                                    }
                                    if(i.getRegion().getRegionClass().length()> 0){
                                        Core.push();
                                        Core.add("Class");
                                            Core.set(i.getRegion().getRegionClass());
                                        Core.pop();
                                    }
                                    if(i.getRegion().getStart() > 0){
                                        Core.push();
                                        Core.add("Start");
                                            Core.set(i.getRegion().getStart());
                                        Core.pop();
                                    }
                                    if(i.getRegion().getEnd() > 0){
                                        Core.push();
                                        Core.add("End");
                                            Core.set(i.getRegion().getEnd());
                                        Core.pop();
                                    }
                                Core.pop();
                            }
                        Core.pop();
                        //break;
                       // case "Event":
                    } else if(o.getEvent() != null){
                        Event e = o.getEvent();
                        Core.push();
                        Core.add("cel-core:Event");
                            Core.attr("name",e.getName());
                            Core.push();
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
                            Core.attr("partyRef",is.getPartyRef().replace("Issuer: ", ""));
                        Core.pop();
                        //break;
                    }
                    /*RESLUTANT OBJECT*/
                    
                    /*POSTCONDITION*/
                    if (d.getPostConditions() != null && !d.getPostConditions().isEmpty() ){
                        for (PostCondition postC: d.getPostConditions()){
                            Core.push();
                            Core.add("cel-core:PostCondition");
                                Core.attr("idref", postC.getIdref());
                                Core.attr("actionStatus", postC.getActionStatus());
                                if (postC.getWithDelay().length() > 0) Core.attr("withDelay", postC.getWithDelay());
                                if (postC.getValidity().length() > 0)Core.attr("validity", postC.getValidity());
                            Core.pop();
                        }
                    }
                    
                    /*ISSUER creo que no tengo de eso*/ 
                    
                    Core.pop();
                Core.pop();
            }
            Core.pop();   
        }        
        Core.pop(); 
        
        File outputFile = new File(path + "/TheFile.xml");
        FileWriter fout = new FileWriter(outputFile);
        fout.write(new Xembler(Core).xml());
        fout.close();
        System.out.println(outputFile.getAbsolutePath());
        System.out.print(new Xembler(Core).xml());
        
        return outputFile.getPath();
    }
}