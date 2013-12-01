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

<!--%@include file="/dist/js/jquery.js" %-->
<%@include file="../leftMenu.jspf" %>
<%@include file="passMenu.jspf" %>

<div class="container">
<div class="row">
    <div class=" panel panel-default">
<div class="panel-heading">
    
    <h2 class="panel-title">  Zadat nové/změnit heslo testu</h2>
  
   
    
    
</div>
        <div class="panel-body">
            
             <div class="row">
            <div class="col-lg-8">
                
                </div>
            <div class="col-lg-4">
                
                        
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
            
           <f:form  class="form-horizontal" commandName="form" action="changeTestPass.htm" method="POST">
                    
                        
                        <f:errors path="*" element="div" cssClass="alert alert-info" />
                        <div class="form-group">
                            
                            
                            <f:label path="actualPass" class="col-lg-3 control-label">Vaše heslo<strong><span class="glyphicon glyphicon-asterisk"></span></strong>:</f:label>
                        <div class="col-lg-5">
                        <f:password path="actualPass" id="actualPass" cssClass="form-control"/>
                        <f:errors path="actualPass" element="div" cssClass="alert alert-info" />
                        </div>
                    </div>
                         
                        <div class="form-group">
                           
                            <f:label path="newPass" class="col-lg-3 control-label">Nové heslo testu<strong><span class="glyphicon glyphicon-asterisk"></span></strong>:</f:label>
                        <div class="col-lg-5">
                        <f:password path="newPass" id="mobilePhone" cssClass="form-control"/>
                        <f:errors path="newPass" element="div" cssClass="alert alert-info" />
                        </div>
                        </div>
                        
                        <div class="form-group">
                            
                        <f:label path="newPass2" class="col-lg-3 control-label">Opakujte nové heslo testu  <strong><span class="glyphicon glyphicon-asterisk"></span></strong>:</f:label>
                        <div class="col-lg-5">
                        <f:password path="newPass2" id="newPass2" cssClass="form-control"/>
                        <f:errors path="newPass2" element="div" cssClass="alert alert-info" />
                        </div>
                    </div>
                        
                        <f:hidden path="id"/>
                           
                        <button class="btn btn-primary" type="submit">
               Změnit heslo
            </button>
            <a class="btn btn-danger" href="<c:url value="tests.htm?id=${form.id}"/>">Zrušit</a>
            <button class="btn btn-default" type="reset">Vymazat formulář</button>
                        
                        
                </f:form>
                    
        </div>
    </div>
    
        
    

</div> 
        
    


    <div class="row">


</div>
<hr>
<%@include file="../../footer.jspf" %>