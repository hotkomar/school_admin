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
    
    <h2 class="panel-title">  Změnit heslo </h2>
    
  
   
    
    
</div>
        <div class="panel-body">
            <p class="alert-info"><strong>Údaje označené symbolem <span class="glyphicon glyphicon-asterisk"></span> jsou povinné.</strong></p>
            
            <f:form enctype="multipart/form-data"  class="form-horizontal" commandName="form" action="changePasswordStudent.htm" method="POST">
                <f:errors path="*" element="div" cssClass="alert alert-info"/>
                        
                        <div class="form-group">
                                                  
                            <f:label path="adminPass" class="col-lg-2 control-label">Vaše heslo<span class="glyphicon glyphicon-asterisk"></span>:</f:label>
                       <div class="col-lg-5">
                       <f:password path="adminPass" id="login" cssClass="form-control"/>
                        <f:errors path="adminPass" element="div" cssClass="alert alert-info" />
                       </div>
                    </div>
                    <div class="form-group">
                            
                        <f:label path="password" class="col-lg-2 control-label">Heslo<span class="glyphicon glyphicon-asterisk"></span>:</f:label>
                        <div class="col-lg-5">
                        <f:password path="password" id="password" cssClass="form-control"/>
                        <f:errors path="password" element="div" cssClass="alert alert-info" />
                        </div>
                    </div>
                        
                        <div class="form-group">
                            
                        <f:label path="password2" class="col-lg-2 control-label">Ověření hesla<span class="glyphicon glyphicon-asterisk"></span>:</f:label>
                        <div class="col-lg-5">
                        <f:password path="password2" id="password2" cssClass="form-control"/>
                        <f:errors path="password2" element="div" cssClass="alert alert-info" />
                        </div>
                    </div>
                       
                        <f:hidden path="id" />    
                        
                            
                    
                        
                        <div class="btn-group">           
                        <button class="btn btn-primary" type="submit">
                Změnit heslo
            </button>
            <a class="btn btn-danger" href="<c:url value="/admin/infoStudent.htm?id=${form.id}"/>"> Zrušit</a>
            <a class="btn btn-default">Vymazat formulář</a>
                        </div>           
                        
                </f:form>
            
        </div>
    </div>
    
        
    

</div>
    <div class="row">


</div>
<hr>





<%@include file="../../footer.jspf" %>