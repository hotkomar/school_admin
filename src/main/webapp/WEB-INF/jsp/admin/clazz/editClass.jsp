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
    
    <h2 class="panel-title">  Upravit třídu </h2>
    
  
   
    
    
</div>
        <div class="panel-body">
            <p class="alert-info"><strong>Údaje označené symbolem <span class="glyphicon glyphicon-asterisk"></span> jsou povinné.</strong></p>
            
            <f:form   class="form-horizontal" commandName="form" action="adminEditClass.htm" method="POST">
                     <f:errors path="*" element="div" cssClass="alert alert-info" />
                        
                        <div class="form-group">
                                                  
                            <f:label path="name" class="col-lg-2 control-label">Písmeno označující třídu <span class="glyphicon glyphicon-asterisk"></span>:</f:label>
                       <div class="col-lg-5">
                            <f:input path="name" id="name" cssClass="form-control"/>
                        <f:errors path="name" element="div" cssClass="alert alert-info" />
                       </div>
                    </div>
                    <div class="form-group">
                                                  
                            <f:label path="numberOfYears" class="col-lg-2 control-label">Počet ročníků<span class="glyphicon glyphicon-asterisk"></span>:</f:label>
                       <div class="col-lg-5">
                            <f:input path="numberOfYears" id="numberOfYears" cssClass="form-control"/>
                        <f:errors path="numberOfYears" element="div" cssClass="alert alert-info" />
                       </div>
                    </div>
                    
                     <div class="form-group">
                                                  
                            <f:label path="numberName" class="col-lg-2 control-label">Aktuální ročník<span class="glyphicon glyphicon-asterisk"></span>:</f:label>
                       <div class="col-lg-5">
                            <f:input path="numberName" id="numberName" cssClass="form-control"/>
                        <f:errors path="numberName" element="div" cssClass="alert alert-info" />
                       </div>
                    </div>  
                       <div class="form-group">
                     <f:label path="classHead" class="col-lg-2 control-label">Třídní učitel<span class="glyphicon glyphicon-asterisk"></span>:</f:label>
                       <div class="col-lg-5">
                       <f:select path="classHead" items="${listClassHead}" id="classHead" cssClass="form-control"/>
                        <f:errors path="classHead" element="div" cssClass="alert alert-info" />
                       </div>
                    </div>  
                        <f:hidden path="id"/>
                        <div class="btn-group">
                        <button class="btn btn-primary" type="submit">
                Upravit třídu
            </button>
            <a class="btn btn-danger" href="<c:url value="/admin/classes.htm"/>"> Zrušit</a>
           <button class="btn btn-default" type="reset">Obnovit</button>
                        
                        </div>          
                </f:form>
            
        </div>
    </div>
    
        
    

</div>
    <div class="row">


</div>
<hr>





<%@include file="../../footer.jspf" %>