<%-- 
    Document   : prePostConditions
    Created on : 20-mar-2017, 1:01:43
    Author     : david
--%>
<%@page import="com.marcobrador.tfm.cel.db.model.DeonticStructuredClause"%>
<%@page import="com.marcobrador.tfm.cel.db.model.Body"%>
<%@page import="com.marcobrador.tfm.cel.db.model.Contract"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <!-- Head -->
    <head>
        <title>Genomic CEL contract creation</title>
        <!-- Meta-Tags -->
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
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
                    <form Id="PrePostConditions" name="PrePostConditions" action="PrePostConditions" method="post">
                        <div id="login" class="animate w3layouts agileits form">
                            <h3> Target Clause </h3> <br>
                            <div class="ferry ferry-from">
                                <label>Precondition Id</label>
                                <select name="PreConditionPartyRef">
                                    <%
                                        Contract.Builder cb = (Contract.Builder) session.getAttribute("Contract");
                                        for (Body b : cb.build().getBody()) {
                                            for (DeonticStructuredClause d : b.getOperativePart().getClauses()) {
                                                out.println("<option value=\"" + d.getId().toString() + "\">" + d.getId().toString() + "</option>");
                                            }
                                        }
                                    %>
                                </select>
                            </div>
                            <h3> Precondition </h3> <br>
                            <div class="ferry ferry-from">
                                <label>Precondition Id</label>
                                <select name="PreConditionPartyRef">
                                    <%
                                        cb = (Contract.Builder) session.getAttribute("Contract");
                                        for (Body b : cb.build().getBody()) {
                                            for (DeonticStructuredClause d : b.getOperativePart().getClauses()) {
                                                out.println("<option value=\"" + d.getId().toString() + "\">" + d.getId().toString() + "</option>");
                                            }
                                        }
                                    %>
                                </select>
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
                            </div>
                            <h3> PostCondition </h3> <br>
                            <div class="ferry ferry-from">
                                <label>PostCondition Id</label>
                                <select name="PostConditionPartyRef">
                                    <%
                                        cb = (Contract.Builder) session.getAttribute("Contract");
                                        for (Body b : cb.build().getBody()) {
                                            for (DeonticStructuredClause d : b.getOperativePart().getClauses()) {
                                                out.println("<option value=\"" + d.getId().toString() + "\">" + d.getId().toString() + "</option>");
                                            }
                                        }
                                    %>
                                </select>
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
                            </div>
                            <div class="wthreesubmitaits">
                                <input type="button" id="AddAnotherButton" value="Add another Operative Part" name="AddAnother" onClick ="addanother()"/>
                                <input type="submit" id="SubmitButton" name="submit" value="End and Generate Contract">
                            </div>
                        </div>
                        <input type="hidden" id="NextAction" name="NextAction" value="Finish"> 
                    </form>
                </div>
            </div>
        </div>
        <div class="w3lsfooteragileits">
            <p>  Design by <a href="http://github.com/5n10m" target="=_blank">5n10m</a></p>
        </div><script type="text/javascript" >
            function addanother() {
                document.getElementById("NextAction").value = "AddAnother";
                document.getElementById('SubmitButton').click();
            }
        </script>
    </body>
</html>
