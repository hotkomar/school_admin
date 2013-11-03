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
    
    <h2 class="panel-title">  Přidat nového studenta </h2>
    
  
   
    
    
</div>
        <div class="panel-body">
            <p class="alert-info"><strong>Údaje označené symbolem <span class="glyphicon glyphicon-asterisk"></span> jsou povinné.</strong></p>
            
            <f:form  class="form-horizontal" commandName="form" action="adminNewStudent.htm" method="POST">
                    
                        <h4 class="text-info">Přihlašovací údaje</h4>
                        <div class="form-group">
                                                  
                            <f:label path="login" class="col-lg-2 control-label">Přihlašovací jméno <span class="glyphicon glyphicon-asterisk"></span>:</f:label>
                       <div class="col-lg-5">
                            <f:input path="login" id="login" cssClass="form-control"/>
                        <f:errors path="login" element="div" cssClass="alert alert-info" />
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
                       
                            
                        
                            <h1 class="text-danger">PŘIDAT TŘÍDU</h1>
                        
                   
                        <h4 class="text-info">Osobní údaje</h4>
                     <div class="form-group">
                            
                        <f:label path="name" class="col-lg-2 control-label">Jméno<span class="glyphicon glyphicon-asterisk"></span>:</f:label>
                        <div class="col-lg-5">
                        <f:input path="name" id="name" cssClass="form-control"/>
                        <f:errors path="name" element="div" cssClass="alert alert-info" />
                        </div>
                    </div> 
                     <div class="form-group">
                            
                        <f:label path="surname" class="col-lg-2 control-label">Přijmení<span class="glyphicon glyphicon-asterisk"></span>:</f:label>
                        <div class="col-lg-5">
                        <f:input path="surname" id="surname" cssClass="form-control"/>
                        <f:errors path="surname" element="div" cssClass="alert alert-info" />
                        </div>
                    </div> 
                        <div class="form-group">
                            
                        <f:label path="dateOfBorn" class="col-lg-2 control-label">Datum narození<span class="glyphicon glyphicon-asterisk"></span>:</f:label>
                        <div class="col-lg-5">
                        <f:input path="dateOfBorn" id="dateOfBorn" cssClass="form-control"/>
                        <f:errors path="dateOfBorn" element="div" cssClass="alert alert-info" />
                        </div>
                    </div>
                   <div class="form-group">
                            
                        <f:label path="identificationNumber" class="col-lg-2 control-label">Rodné číslo<span class="glyphicon glyphicon-asterisk"></span>:</f:label>
                        <div class="col-lg-5">
                        <f:input path="identificationNumber" id="identificationNumber" cssClass="form-control"/>
                        <f:errors path="identificationNumber" element="div" cssClass="alert alert-info" />
                        </div>
                    </div> 
                         <div class="form-group">
                            
                        <f:label path="placeOfBorn" class="col-lg-2 control-label">Místo narození<span class="glyphicon glyphicon-asterisk"></span>:</f:label>
                        <div class="col-lg-5">
                        <f:input path="placeOfBorn" id="placeOfBorn" cssClass="form-control"/>
                        <f:errors path="placeOfBorn" element="div" cssClass="alert alert-info" />
                        </div>
                         </div>
                        <h4 class="text-info">Zákonní zástupci</h4>
                        <h5 class="text-info">1. Zákonný zástupce</h5>
                        <div class="form-group">
                            
                        <f:label path="motherName" class="col-lg-2 control-label">Jméno:</f:label>
                        <div class="col-lg-5">
                        <f:input path="motherName" id="motherName" cssClass="form-control"/>
                        <f:errors path="motherName" element="div" cssClass="alert alert-info" />
                        </div>
                    </div>
                        <div class="form-group">
                            
                        <f:label path="motherSurname" class="col-lg-2 control-label">Příjmení:</f:label>
                        <div class="col-lg-5">
                        <f:input path="motherSurname" id="motherSurname" cssClass="form-control"/>
                        <f:errors path="motherSurname" element="div" cssClass="alert alert-info" />
                        </div>
                    </div>   
                    <div class="form-group">
                            
                        <f:label path="motherPhone" class="col-lg-2 control-label">Telefonní číslo:</f:label>
                        <div class="col-lg-5">
                        <f:input path="motherPhone" id="motherPhone" cssClass="form-control"/>
                        <f:errors path="motherPhone" element="div" cssClass="alert alert-info" />
                        </div>
                    </div>
                        <div class="form-group">
                            
                        <f:label path="motherMail" class="col-lg-2 control-label">E-mail:</f:label>
                        <div class="col-lg-5">
                        <f:input path="motherMail" id="motherMail" cssClass="form-control"/>
                        <f:errors path="motherMail" element="div" cssClass="alert alert-info" />
                        </div>
                    </div>
                        <h5 class="text-info">2. zákonný zástupce</h5>
                       <div class="form-group">
                            
                        <f:label path="fatherName" class="col-lg-2 control-label">Jméno:</f:label>
                        <div class="col-lg-5">
                        <f:input path="fatherName" id="fatherName" cssClass="form-control"/>
                        <f:errors path="fatherName" element="div" cssClass="alert alert-info" />
                        </div>
                    </div>
                        <div class="form-group">
                            
                        <f:label path="fatherSurname" class="col-lg-2 control-label">Příjmení:</f:label>
                        <div class="col-lg-5">
                        <f:input path="fatherSurname" id="fatherSurname" cssClass="form-control"/>
                        <f:errors path="fatherSurname" element="div" cssClass="alert alert-info" />
                        </div>
                    </div>   
                    <div class="form-group">
                            
                        <f:label path="fatherPhone" class="col-lg-2 control-label">Telefonní číslo:</f:label>
                        <div class="col-lg-5">
                        <f:input path="fatherPhone" id="fatherPhone" cssClass="form-control"/>
                        <f:errors path="fatherPhone" element="div" cssClass="alert alert-info" />
                        </div>
                    </div>
                        <div class="form-group">
                            
                        <f:label path="fatherMail" class="col-lg-2 control-label">E-mail:</f:label>
                        <div class="col-lg-5">
                        <f:input path="fatherMail" id="fatherMail" cssClass="form-control"/>
                        <f:errors path="fatherMail" element="div" cssClass="alert alert-info" />
                        </div>
                    </div>
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
                    
                        
                           
                        <button class="btn btn-primary" type="submit">
                Přidat studenta
            </button>
            <a class="btn btn-danger" href="<c:url value="/admin/students.htm"/>"> Zrušit</a>
            <a class="btn btn-danger">Vymazat formulář</a>
                        
                        
                </f:form>
            
        </div>
    </div>
    
        
    

</div>
    <div class="row">


</div>
<hr>





<%@include file="../../footer.jspf" %>