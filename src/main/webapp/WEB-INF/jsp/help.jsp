<%-- 
    Document   : addTest
    Created on : 9.11.2013, 22:45:50
    Author     : Maru
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@include file="header.jspf" %>


<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!--%@include file="/dist/js/jquery.js" %-->


<div class="container">
<div class="row">
    <div class=" panel panel-default">
<div class="panel-heading">
    
    <h2 class="panel-title"> Informace</h2>
  
   
    
    
</div>
        <div class="panel-body">
            
             
            <h4 class="text text-info">Zákonný zástupce</h4>   
            <p>
                Zákonný zástupce si může nechat zaslat zapomenuté heslo pomocí e-mailu.          
                            </p>
            <h4 class="text text-info">Učitel</h4>   
            <p>
                Učitel si může nechat zaslat zapomenuté heslo pomocí e-mailu, pokud má vyplněný e-mail ve svém profilu nebo požádat administrátora o změnu hesla.
                               </p>
        
    <h4 class="text text-info">Student</h4>   
            <p>
               Student si může nechat zaslat zapomenuté heslo pomocí e-mailu, pokud má vyplněný e-mail ve svém profilu nebo požádat administrátora o změnu hesla.
                               </p>

                               <f:form class="form-signin" action="newPass.htm" commandName="form" method="POST">
                                   <c:if test="${param.error == true}">
                                       <h4 classs="form-signin-heading" >Zadejte přihlašovací jméno.</h4>
                                    </c:if>
                                    <c:if test="${param.error != true}">
                                    <div class="text text-danger"> <h3>Uživatel neexistuje nebo nevyplnil svou e-mailovou adresu.</h3></div>
                                    </c:if>
                                   <f:input  path="login" class="form-control" placeholder="Přihlašovací jméné"/>
                                   <f:input  path="mail" class="form-control" placeholder="e-mail"/>
                                   <f:errors path="mail" element="div"/>
                                   <p style="margin-top: 10px">
                                   <button type="submit" class="btn btn-primary">Odeslat</button></p>
                               </f:form>
    <div class="row">


</div>
<hr>
<%@include file="footer.jspf" %>