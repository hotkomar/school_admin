<%-- 
    Document   : login
    Created on : 21.4.2013, 18:41:30
    Author     : maru
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@include file="header.jspf" %>

<div class="container">
   
   
    
<form class="form-signin" action="<s:url value="j_spring_security_check" />" method="POST">
    <h1 class="form-signin-heading">  <a href="<s:url value="/maru.htm"/>">School admin</a></h1>
    <c:if test="${param.error != true}">
    <h2 classs="form-signin-heading" >Prosím, přihlašte se.</h2>
    </c:if>
    <c:if test="${param.error == true}">
        <div class="text text-danger"> <h3>Chyba přihlášení. Špatné jméno nebo heslo.</h3></div>
    </c:if>
    <c:if test="${param.pass == true}">
        <div class="text text-danger"> <h3>E-mail byl odeslán.</h3></div>
    </c:if>
    <!--label for="j_username" class="">Přihlašovací jméno</label-->
   
    <input  type="text" id="j_username" name="j_username" class="form-control" placeholder="Přihlašovací jméné"/>
    
    <!--label for="j_password" class="col-lg-3 control-label">Heslo</label-->
    
    <input  type="password" id="j_password" name="j_password" class="form-control" placeholder="Heslo"/>
     
    <button class="btn btn-large btn-primary btn-block" type="submit" id="but">Přihlásit</button>
    <p style="margin-top: 10px">
        <a href="<c:url value="/help.htm"/>">zapomenuté heslo</a></p>
</form>
    
<!--a class="btn btn-large btn-info"href="s:url value="/student/personInfo.htm"/>">přihlásit jako student</a>
<a class="btn btn-large btn-danger" href="s:url value="/teacher/personInfo.htm"/>">přihlásit jako učitel </a>
<a class="btn btn-large btn-success" href="s:url value="/parent/personInfo.htm"/>">přihlásit jako rodič </a> <br /> <br />
<a class="btn btn-large btn-warning" href="s:url value="/admin/students.htm?page=1"/>">přihlásit jako administrátor </a-->
</div>  

<%@include file="footer.jspf" %>
    
    
    
    

