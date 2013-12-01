<%-- 
    Document   : addTest
    Created on : 9.11.2013, 22:45:50
    Author     : Maru
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@include file="../../header.jspf" %>
<%@include file="../../headerMenu.jspf" %>
<%@include file="../leftMenu.jspf" %>
<!--%@include file="/dist/js/jquery.js" %-->


<div class="container">
<div class="row">
    <div class=" panel panel-default">
<div class="panel-heading">
    
    <h2 class="panel-title">  Profil</h2>
  
   
    
    
</div>
        <div class="panel-body">
            
             <div class="row">
            <div class="col-lg-8">
                
                </div>
            <div class="col-lg-4">
                <a href="<c:url value="editTeacher.htm"/>" class=" btn btn-success "> <span class="glyphicon glyphicon-edit"></span><strong> Editovat profil</strong></a>
                
                <a href="<c:url value="passTeacher.htm"/>" class=" btn btn-default " style="color: white; background: black"> <span class="glyphicon glyphicon-wrench"></span><strong> Změnit heslo</strong></a>
                        
            </div>
            </div>
                <div class="row">
                <c:if test="${message.positiveFull}">
    <div class="alert alert-success ">
        <a href="#" class="close" data-dismiss="alert">&times;</a>
        ${message.positiveMes}
    </div>
</c:if>
<c:if test="${message.negativeFull}">
    
    <div class="alert alert-danger alert-dismissable">
  <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
  ${message.negativeMes}
</div>
</c:if>
            </div>
            <p>
                
            </p>
            <div class="container">
                <div class="row">
                    <p class="alert-info"><strong>Údaje označené symbolem <span class="glyphicon glyphicon-asterisk"></span> jsou povinné.</strong></p>
            
            <f:form  class="form-horizontal" commandName="form" action="editTeacher.htm" method="POST">
                    
                        
                        <h4 class="text-info"> Kontaktní údaje</h4>
                        <div class="form-group">
                            
                            
                        <f:label path="mail" class="col-lg-2 control-label">E-mail:</f:label>
                        <div class="col-lg-5">
                        <f:input path="mail" id="mail" cssClass="form-control"/>
                        <f:errors path="mail" element="div" cssClass="alert alert-info" />
                        </div>
                    </div>
                        <div class="form-group">
                            
                        <f:label path="mobilePhone" class="col-lg-2 control-label">Mobilní telefon</f:label>
                        <div class="col-lg-5">
                        <f:input path="mobilePhone" id="mobilePhone" cssClass="form-control"/>
                        <f:errors path="MobilePhone" element="div" cssClass="alert alert-info" />
                        </div>
                        </div>
                        
                        <div class="form-group">
                            
                        <f:label path="phoneNumber" class="col-lg-2 control-label">Telefon:</f:label>
                        <div class="col-lg-5">
                        <f:input path="phoneNumber" id="phoneNumber" cssClass="form-control"/>
                        <f:errors path="phoneNumber" element="div" cssClass="alert alert-info" />
                        </div>
                    </div>
                        <div class="form-group">
                            
                        <f:label path="fax" class="col-lg-2 control-label">Fax:</f:label>
                        <div class="col-lg-5">
                        <f:input path="fax" id="fax" cssClass="form-control"/>
                        <f:errors path="fax" element="div" cssClass="alert alert-info" />
                        </div>
                    </div>
                        
                        <f:hidden path="id" />
                        
                           
                        <button class="btn btn-primary" type="submit">
               Editovat kontakt
            </button>
            <a class="btn btn-danger" href="<c:url value="/teacher/personInfo.htm"/>"> Zrušit</a>
            <button class="btn btn-default" type="reset">Zrušit změny</button>
                        
                        
                </f:form>
                </div>
            </div>
        
    


    <div class="row">


</div>
<hr>
<%@include file="../../footer.jspf" %>