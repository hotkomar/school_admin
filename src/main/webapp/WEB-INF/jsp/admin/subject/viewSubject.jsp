<%-- 
    Document   : personInfo
    Created on : 24.4.2013, 0:14:56
    Author     : maru
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>

<%@include file="../../header.jspf" %>
<%@include file="../../headerMenu.jspf" %>
<%@include file="../leftMenu.jspf" %>
<div class="container">
<div class="row">
    <div class=" panel panel-default">
<div class="panel-heading">
    
    <h2 class="panel-title">  Seznam předmětů </h2>
  
   
    
    
</div>
        <div class="panel-body">
            <div class="row">
            <div class="col-lg-10"></div>
            <div class="col-lg-2">
                <a href="<c:url value="addStudent.htm"/>" class="btn btn-primary "> <span  class=" glyphicon glyphicon-plus"></span> Přidat studenta</a>
            </div>
            </div>
            <p></p>
            <table class="table table-bordered table-striped table-hover">
                        <tr>
                            <th>Přihlašovací jméno</th> <th>Jméno</th><th>Příjmení</th><th>Datum narození</th><th>Třída</th><th>Info</th><th>Editovat</th><th>Smazat</th><th>Změnit heslo</th>
                            
                        </tr>
                        <tr>
                            <td>login</td><td>Karel</td><td>Novák</td><td>1.1.1990</td><td>1.A</td>
                            <td style="text-align: center" ><a class="btn btn-info" href="#" ><span class="glyphicon glyphicon-info-sign"></span></a></td>
                            <td style="text-align: center"><a class="btn btn-success"href="#"><span class="glyphicon glyphicon-edit"></span></a></td>
                            <td style="text-align: center"><a class="btn btn-danger"href="#"><span class="glyphicon glyphicon-remove"></span></a></td>
                            <td style="text-align: center"><a class="btn btn-default" style="background: black; color: white"  href="#"><span class="glyphicon glyphicon-wrench"></span></a></td>
                        </tr>
</table>
        </div>
    </div>
    
        
    

</div>
    <div class="row">


</div>
<hr>





<%@include file="../../footer.jspf" %>