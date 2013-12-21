<%-- 
    Document   : addTest
    Created on : 9.11.2013, 22:45:50
    Author     : Maru
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@include file="../header.jspf" %>
<%@include file="../headerMenu.jspf" %>
<%@include file="leftMenu.jspf" %>
<!--%@include file="/dist/js/jquery.js" %-->


<div class="container">
<div class="row">
    <div class=" panel panel-default">
<div class="panel-heading">
    
    <h2 class="panel-title">  Profil</h2>
  
   
    
    
</div>
        <div class="panel-body">
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
             <p class="alert-info"><strong>Údaje označené symbolem <span class="glyphicon glyphicon-asterisk"></span> jsou povinné.</strong></p>
          
                <f:form   class="form-horizontal" commandName="form" action="editKontak.htm" method="POST">
                    <h4 class="text-info">1. Zákonný zástupce</h4>
                        <h5 class="text-info">${form.motherName} ${form.motherSurname}</h5>
                   <div class="form-group">
                            
                        <f:label path="motherPhone" class="col-lg-2 control-label">Telefonní číslo <span class="glyphicon glyphicon-asterisk"></span>:</f:label>
                        <div class="col-lg-5">
                        <f:input path="motherPhone" id="motherPhone" cssClass="form-control"/>
                        <f:errors path="motherPhone" element="div" cssClass="alert alert-info" />
                        </div>
                    </div>
                        <div class="form-group">
                            
                        <f:label path="motherMail" class="col-lg-2 control-label">E-mail:<span class="glyphicon glyphicon-asterisk"></span></f:label>
                        <div class="col-lg-5">
                        <f:input path="motherMail" id="motherMail" cssClass="form-control"/>
                        <f:errors path="motherMail" element="div" cssClass="alert alert-info" />
                        </div>
                        <p class="text-info"><strong> Na tento e-mail budou zaslány přihlašovací údaje.</strong></p>
                    </div>
                        <h4 class="text-info">2. Zákonný zástupce</h4>
                        <h5 class="text-info">${form.fatherName} ${form.fatherSurname}</h5>
                        <div class="form-group">
                            
                        <f:label path="fatherPhone" class="col-lg-2 control-label">Telefonní číslo :</f:label>
                        <div class="col-lg-5">
                        <f:input path="fatherPhone" id="motherPhone" cssClass="form-control"/>
                        <f:errors path="fatherPhone" element="div" cssClass="alert alert-info" />
                        </div>
                    </div>
                        <div class="form-group">
                            
                        <f:label path="fatherMail" class="col-lg-2 control-label">E-mail:</f:label>
                        <div class="col-lg-5">
                        <f:input path="fatherMail" id="motherMail" cssClass="form-control"/>
                        <f:errors path="fatherMail" element="div" cssClass="alert alert-info" />
                        </div>
                        <f:hidden path="IdParent" />
                        
                        
                    </div>
                        
                            <div class="col-lg-5">
                                <div class="btn-group">
                            <button type="submit" class="btn btn-primary">Změnit</button>
                        <a href="<c:url value="/parent/profilInfo.htm"/>" class="btn btn-danger"> Zpět na profil</a>
                        <button type="reset" class="btn btn-default">Obnovit</button>
                                </div>
                            </div>
                        
                        
                        
                </f:form>
           
            
         
               
                 
                
                    
        </div>
    </div>
    
        
    

</div> 
        
    


    <div class="row">


</div>
<hr>
<%@include file="../footer.jspf" %>