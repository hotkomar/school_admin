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
    
    <h2 class="panel-title">  Přidat nový předmět </h2>
    
  
   
    
    
</div>
        <div class="panel-body">
            <p class="alert-info"><strong>Údaje označené symbolem <span class="glyphicon glyphicon-asterisk"></span> jsou povinné.</strong></p>
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
            <f:form   class="form-horizontal" commandName="form" action="adminAddSubjectToClass.htm" method="POST">
                    
                        
                        <div class="form-group">
                                                  
                            <f:label path="idSubject" class="col-lg-2 control-label">Předmět <span class="glyphicon glyphicon-asterisk"></span>:</f:label>
                       <div class="col-lg-5">
                       <f:select path="idSubject" items="${subjects}" id="subject" cssClass="form-control"/>
                        <f:errors path="idSubject" element="div" cssClass="alert alert-info" />
                       </div>
                    </div>
                    <div class="form-group">
                                                  
                            <f:label path="idTeacher" class="col-lg-2 control-label">Počet ročníků<span class="glyphicon glyphicon-asterisk"></span>:</f:label>
                       <div class="col-lg-5">
                       <f:select  path="idTeacher" items="${teachers}" id="teacher" cssClass="form-control" />
                        <f:errors path="idTeacher" element="div" cssClass="alert alert-info" />
                       </div>
                    </div>
                    
                        <f:hidden path="idClass" />
                        
                        <button class="btn btn-primary" type="submit">
                Přidat předmět třídě
            </button>
            <a class="btn btn-danger" href="<c:url value="/admin/infoClassS.htm?id=${form.idClass}"/>"> Zrušit</a>
           <button class="btn btn-default" type="reset">Vymazat formulář</button>
                        
                        
                </f:form>
            
        </div>
    </div>
    
        
    

</div>
    <div class="row">


</div>
<hr>





<%@include file="../../footer.jspf" %>