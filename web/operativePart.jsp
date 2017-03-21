<%-- 
    Document   : operativePart
    Created on : 19-mar-2017, 18:00:20
    Author     : david
--%>

<%@page import="com.marcobrador.tfm.cel.db.model.Party"%>
<%@page import="com.marcobrador.tfm.cel.db.model.Contract"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--
        Author: W3layouts
        Author URL: http://w3layouts.com
        License: Creative Commons Attribution 3.0 Unported
        License URL: http://creativecommons.org/licenses/by/3.0/
-->
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Genomic CEL contract creation</title>
        <!-- Meta-Tags -->
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <meta name="keywords" content="Hotel Booking Enquiry Form Widget Responsive, Login Form Web Template, Flat Pricing Tables, Flat Drop-Downs, Sign-Up Web Templates, Flat Web Templates, Login Sign-up Responsive Web Template, Smartphone Compatible Web Template, Free Web Designs for Nokia, Samsung, LG, Sony Ericsson, Motorola Web Design" />
        <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
        <!-- //Meta-Tags -->
        <!-- Custom-Style-Sheet -->
        <link rel="stylesheet" href="css/style.css" type="text/css" media="all">
        <link rel="stylesheet" href="css/jquery-ui.css" type="text/css" media="all">
        <!-- //Custom-Style-Sheet -->
        <!-- Fonts -->
        <link rel="stylesheet" href="//fonts.googleapis.com/css?family=Open+Sans:400,300,600,700,800"  type="text/css" media="all">
        <link href='//fonts.googleapis.com/css?family=Varela+Round' rel='stylesheet' type='text/css'>
        <!-- //Fonts -->
        <!-- Default-JavaScript -->
        <script type="text/javascript" src="js/jquery-2.1.4.min.js"></script>
    </head>
    <!-- //Head -->
    <!-- Body -->
    <body>
        <h1>CONTRACT FORM</h1>
        <div class="containerw3layouts-agileits">
            <div class="w3layoutscontactagileits">
                <h2 id="FormTittle">Operative Part</h2>
                <div id="wrapper">
                    <form Id="OperativePart" name="OperativePart" action="DeonticStructuredClause" method="post">
                        <div id="login" class="animate w3layouts agileits form">
                            <div class="ferry ferry-from">
                                <label>OperativePart Type</label>
                                <select name="OperativePartType">
                                    <option value="Obligation">Obligation</option>
                                    <option value="Permission">Permission</option>
                                    <option value="Prohibition">Prohibition</option>
                                    <!--<option value="Statement">Statement</option> -->
                                </select>
                            </div>
                            <div class="ferry ferry-from">
                                <label>OperativePart Id</label>
                                <input type="text" name="OperativePartId" placeholder="Id" required="">
                            </div>
                            <div class="ferry ferry-from">
                                <label>Textual Part</label>
                                <textarea id="message" name="TextualPart" placeholder="Textual contract version" title="Enter the analogical-textual Part if exists"></textarea>
                            </div>
                            <!-- <h3> Precondition </h3> <br>
                            <div class="ferry ferry-from">
                                <label>Precondition Id</label>
                                <input type="text" name="PreConditionId" placeholder="Id">
                            </div>
                            <div class="ferry ferry-from">
                                <label>Action Status</label>
                                <input type="text" name="PreConditionActionStarted" placeholder="Action started">
                                <input type="text" name="PreConditionActionDone" placeholder="Action Done">
                            </div>
                            <div class="ferry ferry-from">
                                <label>Delay</label>
                                <input type="text" name="PreConditionDelay" placeholder="Delay" >
                            </div>
                            <div class="ferry ferry-from">
                                <label>Validity</label>
                                <input class="date agileits w3layouts" name="PreConditionValidity" id="datepicker2" type="text" value="Date" onfocus="this.value = '';" onblur="if (this.value == '') {
                                            this.value = '';
                                        }">
                            </div> -->
                            <h3> Subject </h3> <br>
                            <div class="ferry ferry-from">
                                <label>Party Reference</label>
                                <select name="PartyRef">
                                    <%
                                        Contract.Builder cb = (Contract.Builder) session.getAttribute("Contract");
                                        for (Party p : cb.build().getParties()) {
                                            out.println("<option value=\"" + p.getId() + "\">" + p.getId() + "</option>");
                                        }
                                    %>
                                </select>
                            </div>
                            <!-- <h3> Act </h3> <br>
                            <div class="ferry ferry-from">
                                <label>Type</label>
                                <select name="type">
                                    <option value="Donation">Donation</option>
                                    <option value="Forensic">Forensic</option>
                                    <option value="GenethicAnalysis">GenethicAnalysis</option>
                                    <option value="PaternityTest">PaternityTest</option>
                                    <option value="ResearchProject">ResearchProject</option>
                                    <option value="View">View</option>
                                </select>
                            </div> -->
                            <h3> Action </h3><br>
                            <div class="ferry ferry-from">
                                <label>Action Identifier</label>
                                <input type="text" name="ActionId" placeholder="Id" >
                            </div>
                            <div class="ferry ferry-from">
                                <label>Type</label>
                                <select name="ActionType">
                                    <option value="Donation">Donation</option>
                                    <option value="Forensic">Forensic</option>
                                    <option value="GenethicAnalysis">GenethicAnalysis</option>
                                    <option value="PaternityTest">PaternityTest</option>
                                    <option value="ResearchProject">ResearchProject</option>
                                    <option value="View">View</option>
                                </select>
                            </div>
                            <h3> Object </h3><br>
                            <div class="ferry ferry-from">
                                <label>Type</label>
                                <select id="ObjectType" name="ObjectType" onchange="onObjectTypeChange()">
                                    <option value="Item">Item</option>
                                    <option value="Event">Event</option>
                                    <option value="Subject">Subject</option>
                                    <!-- <option value="Service">Service</option> -->
                                </select>
                            </div>
                            <div id="variableObjectDiv" class="ferry ferry-from">
                                <div id ="VariableContent">
                                    <label>Item Identifier</label>
                                    <input type="text" name="ItemIdentifier" placeholder="IdRef">
                                    <label>Item Type</label>
                                    <select name="ItemType">
                                        <option value="Alignment">Alignment</option>
                                        <option value="AnonymizedGenomaData">AnonymizedGenomaData</option>
                                        <option value="CompleteGenome">CompleteGenome</option>
                                        <option value="NonAlignedReads">NonAlignedReads</option>
                                        <option value="OutcomeGeneticAnalysis">OutcomeGeneticAnalysis</option>
                                        <option value="RegionsOfVariant">RegionsOfVariant</option>
                                        <option value="Sequence">Sequence</option>
                                    </select>
                                </div>
                            </div>
                            <h3> Resultant Object </h3><br>
                            <div class="ferry ferry-from">
                                <label>Item Identifier</label>
                                <input type="text" name="ResultantObjectIdentifier" placeholder="IdRef">
                                <label>Item Type</label>
                                <select name="ResultantObject">
                                    <option value="Alignment">Alignment</option>
                                    <option value="AnonymizedGenomaData">AnonymizedGenomaData</option>
                                    <option value="CompleteGenome">CompleteGenome</option>
                                    <option value="NonAlignedReads">NonAlignedReads</option>
                                    <option value="OutcomeGeneticAnalysis">OutcomeGeneticAnalysis</option>
                                    <option value="RegionsOfVariant">RegionsOfVariant</option>
                                    <option value="Sequence">Sequence</option>
                                </select>
                            </div>
                            <!--<h3> PostCondition </h3> <br>
                            <div class="ferry ferry-from">
                                <label>PostCondition Id</label>
                                <input type="text" name="PostConditionId" placeholder="Id">
                            </div> 
                            <div class="ferry ferry-from">
                                <label>Action Status</label>
                                <input type="text" name="PostConditionActionStarted" placeholder="Action started">
                                <input type="text" name="PostConditionActionDone" placeholder="Action Done">
                            </div>
                            <div class="ferry ferry-from">
                                <label>Delay</label>
                                <input type="text" name="PostConditionDelay" placeholder="Delay" >
                            </div>
                            <div class="ferry ferry-from">
                                <label>Validity</label>
                                <input class="date agileits w3layouts" name="PostConditionValidity" id="datepicker3" type="text" value="Date" onfocus="this.value = '';" onblur="if (this.value == '') {
                                            this.value = '';
                                        }">
                            </div>-->
                            <div class="wthreesubmitaits">
                                <input type="button" id="AddAnotherButton" value="Add another Operative Part" name="AddAnother" onClick ="addanother()"/>
                                <input type="submit" id="SubmitButton" name="submit" value="Continue to Pre/Post Conditions">
                            </div>
                        </div>
                        <input type="hidden" id="NextAction" name="NextAction" value="Finish"> 
                    </form>
                </div>
            </div>
        </div>
        <div class="w3lsfooteragileits">
            <p>  Design by <a href="http://github.com/5n10m" target="=_blank">5n10m</a></p>
        </div>
        <!-- Necessary-JavaScript-Files-&-Links -->
        <!-- Date-Picker-JavaScript -->
        <script src="js/jquery-ui.js"></script>
        <script>
                                    $(function () {
                                        $("#datepicker,#datepicker1,#datepicker2").datepicker();
                                    });
                                    $(function () {
                                        $("#datepicker,#datepicker1,#datepicker3").datepicker();
                                    });
        </script>
        <script type="text/javascript" >
            function addanother() {
                document.getElementById("NextAction").value = "AddAnother";
                document.getElementById('SubmitButton').click();
            }
            function onObjectTypeChange() {
            switch (document.getElementById("ObjectType").value) {
            case "Item":
                    var element = document.getElementById("VariableContent");
                    if (element != null) {
            element.outerHTML = "";
                    delete element;
            }
            //document.getElementById('VariableContent').removedNode();
            var div = document.createElement('div');
                    div.id = 'VariableContent';
                    div.innerHTML = '<label>Item Identifier</label>\
                                        <input type="text" name="ItemIdentifier" placeholder="IdRef" required="">\
                                        <label>Item Type</label>\
                                        <select name="ItemType">\
                                            <option value="Alignment">Alignment</option>\
                                            <option value="AnonymizedGenomaData">AnonymizedGenomaData</option>\
                                            <option value="CompleteGenome">CompleteGenome</option>\
                                            <option value="NonAlignedReads">NonAlignedReads</option>\
                                            <option value="OutcomeGeneticAnalysis">OutcomeGeneticAnalysis</option>\
                                            <option value="RegionsOfVariant">RegionsOfVariant</option>\
                                            <option value="Sequence">Sequence</option>\
                                        </select>';
                    document.getElementById('variableObjectDiv').appendChild(div);
                    break;
                    case "Event":
                    var element = document.getElementById("VariableContent");
                    if (element != null) {
            element.outerHTML = "";
                    delete element;
            }
            var div = document.createElement('div');
                    div.id = 'VariableContent';
                    div.innerHTML = '<label>Event Identifier</label>\
                                        <input type="text" name="EventIdentifier" placeholder="IdRef" required="">\
                                        <label>Event Type</label>\
                                        <select name="EventType">\
                                            <option value="MoreExperiments">MoreExperiments</option>\
                                        </select>';
                    document.getElementById('variableObjectDiv').appendChild(div);
                    break;
                    case "Subject":
                    var element = document.getElementById("VariableContent");
                    if (element != null) {
            element.outerHTML = "";
                    delete element;
            }
            var div = document.createElement('div');
                    div.id = 'VariableContent';
                    div.innerHTML = '<label>Subject Identifier</label>'+
                                        '<select name="PartyRef">'+
            <%
                                        for (Party p : cb.build().getParties()) {
                                            out.println("'<option value=\"" + p.getId() + "\">" + p.getId() + "</option>' +");
                                        }
            %>
                                '</select>';
                            document.getElementById('variableObjectDiv').appendChild(div);
                            break;
                                case "Service":
                                var element = document.getElementById("VariableContent");
                                if (element != null) {
                    element.outerHTML = "";
                    delete element;
                            }
                            var div = document.createElement('div');
                                div.id = 'VariableContent';
                                div.innerHTML = '<label>PORDEFINIR < /label>\
                            <input type="text" name="EventIdentifier" placeholder="PORDEFINIR" required="">';
                                document.getElementById('variableObjectDiv').appendChild(div);
                                break;
                                }
                                }
                                </script>
        <!-- //Date-Picker-JavaScript -->
        <!-- //Necessary-JavaScript-Files-&-Links -->
    </body>
    <!-- //Body -->
</html>
