<%-- 
    Document   : login
    Created on : 21.4.2013, 18:41:30
    Author     : maru
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="header.jspf" %>

<div class="container">
    <div class="span5 offset3">
        <h1>  <a href="<s:url value="/index.htm"/>">School admin</a></h1>
<form class="form-actions" action="<s:url value="j_spring_security_check" />" method="POST">
       
    <h2 >Prosím, přihlašte se.</h2>
    <c:if test="${param.error == true}">
        <div class="alert alert-error">Chyba přihlášení. Špatné jméno nebo heslo.</div>
    </c:if>
    <div class="control-group">
    <label for="j_username">Přihlašovací jméno</label>
    <input  type="text" id="j_username" name="j_username" />
    </div>
     <div class="control-group">
    <label for="j_password">Heslo</label>
    <input  type="password" id="j_password" name="j_password" />
     </div>

    <button class="btn btn-large btn-primary" type="submit" id="but">Přihlásit</button>
    
    
</form>

<a class="btn btn-large btn-info"href="<s:url value="/student/personInfo.htm"/>">přihlásit jako student</a>
<a class="btn btn-large btn-danger" href="<s:url value="/teacher/personInfo.htm"/>">přihlásit jako učitel </a>
<a class="btn btn-large btn-success" href="<s:url value="/parent/classification.htm"/>">přihlásit jako rodič </a> <br /> <br />
<a class="btn btn-large btn-warning" href="<s:url value="/admin/students.htm"/>">přihlásit jako administrátor </a>
        
</div>
<%@include file="footer.jspf" %>
    
    
    
    

