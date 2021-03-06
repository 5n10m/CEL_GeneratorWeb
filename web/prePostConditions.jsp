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
                                <select name="TargetRef">
                                    <option value=""></option>
                                    <%
                                        Contract cb = (Contract) session.getAttribute("Contract");
                                        for (Body b : cb.getBody()) {
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
                                    <option value=""></option>
                                    <%
                                        cb = (Contract) session.getAttribute("Contract");
                                        for (Body b : cb.getBody()) {
                                            for (DeonticStructuredClause d : b.getOperativePart().getClauses()) {
                                                out.println("<option value=\"" + d.getId().toString() + "\">" + d.getId().toString() + "</option>");
                                            }
                                        }
                                    %>
                                </select>
                            </div>
                            <div class="ferry ferry-from">
                                <label>Action Status</label>
                                <select name="PreConditionActionStatus">
                                    <option value="ActionStarted">Action Started</option>
                                    <option value="ActionDone">Action Done</option>
                                </select>
                            </div>
                            <div class="ferry ferry-from">
                                <label>Delay</label>
                                <input type="text" name="PreConditionDelay" placeholder="Delay on xs:duration format" >
                            </div>
                            <div class="ferry ferry-from">
                                <label>Validity</label>
                                <input type="text" name="PreConditionValidity" placeholder="Validity on xs:duration format" >
                            </div>
                            <h3> PostCondition </h3> <br>
                            <div class="ferry ferry-from">
                                <label>PostCondition Id</label>
                                <select name="PostConditionPartyRef">
                                    <option value=""></option>
                                    <%
                                        cb = (Contract) session.getAttribute("Contract");
                                        for (Body b : cb.getBody()) {
                                            for (DeonticStructuredClause d : b.getOperativePart().getClauses()) {
                                                out.println("<option value=\"" + d.getId().toString() + "\">" + d.getId().toString() + "</option>");
                                            }
                                        }
                                    %>
                                </select>
                            </div> 
                            <div class="ferry ferry-from">
                                <label>Action Status</label>
                                <select name="PostConditionActionStatus">
                                    <option value="ActionStarted">Action Started</option>
                                    <option value="ActionDone">Action Done</option>
                                </select>
                            </div>
                            <div class="ferry ferry-from">
                                <label>Delay</label>
                                <input type="text" name="PostConditionDelay" placeholder="Delay on xs:duration format" >
                            </div>
                            <div class="ferry ferry-from">
                                <label>Validity</label>
                                
                                <input type="text" name="PostConditionValidity" placeholder="Validity on xs:duration format" >
                            </div>
                            <div class="wthreesubmitaits">
                                <input type="button" id="AddAnotherButton" value="Add another Pre/Post Cindition" name="AddAnother" onClick ="addanother()"/>
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
        </div>
        <script src="js/jquery-ui.js"></script>
        <script>
            $(function () {
                $("#datepicker,#datepicker1,#datepicker2").datepicker();
            });
        </script>
        <script type="text/javascript" >
            function addanother() {
                document.getElementById("NextAction").value = "AddAnother";
                document.getElementById('SubmitButton').click();
            }
        </script>
    </body>
</html>
