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
    
    <h2 class="panel-title">  Editovat předmět</h2>
    
  
   
    
    
</div>
        <div class="panel-body">
            <p class="alert-info"><strong>Údaje označené symbolem <span class="glyphicon glyphicon-asterisk"></span> jsou povinné.</strong></p>
            
            <f:form   class="form-horizontal" commandName="form" action="adminEditSubject.htm" method="POST">
                    
                    <f:errors path="*" element="div" cssClass="alert alert-info" />    
                        <div class="form-group">
                                                  
                            <f:label path="name" class="col-lg-2 control-label">Název předmětu<span class="glyphicon glyphicon-asterisk"></span>:</f:label>
                       <div class="col-lg-5">
                            <f:input path="name" id="name" cssClass="form-control"/>
                        <f:errors path="name" element="div" cssClass="alert alert-info" />
                       </div>
                    </div>
                    <div class="form-group">
                                                  
                            <f:label path="validFrom" class="col-lg-2 control-label">Platný od:</f:label>
                       <div class="col-lg-5">
                            <f:input path="validFrom" id="validFrom" cssClass="form-control"/>
                        <f:errors path="validFrom" element="div" cssClass="alert alert-info" />
                       </div>
                       <p class="text-info"><strong> Datum zadejte ve formátu dd.mm.rrrr</strong></p>
                    </div>
                     
                     <div class="form-group">
                                                  
                            <f:label path="expiration" class="col-lg-2 control-label">Platný do:</f:label>
                       <div class="col-lg-5">
                            <f:input path="expiration" id="expiration" cssClass="form-control"/>
                        <f:errors path="expiration" element="div" cssClass="alert alert-info" />
                       </div>
                       <p class="text-info"><strong> Datum zadejte ve formátu dd.mm.rrrr</strong></p>
                    </div>
                        
                        <f:hidden path="id"/> 
                        <button class="btn btn-primary" type="submit">
                Editovat předmět
            </button>
            <a class="btn btn-danger" href="<c:url value="/admin/infoSubject.htm?id=${form.id}"/>"> Zrušit</a>
           <button class="btn btn-default" type="reset">Zrušit změny</button>
                        
                        
                </f:form>
            
        </div>
    </div>
    
        
    

</div>
    <div class="row">


</div>
<hr>





<%@include file="../../footer.jspf" %>