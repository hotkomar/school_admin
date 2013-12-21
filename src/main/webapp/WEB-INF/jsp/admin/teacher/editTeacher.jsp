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
    
    <h2 class="panel-title">  Editovat učitele </h2>
    
  
   
    
    
</div>
        <div class="panel-body">
            <p class="alert-info"><strong>Údaje označené symbolem <span class="glyphicon glyphicon-asterisk"></span> jsou povinné.</strong></p>
            
            <f:form enctype="multipart/form-data"  class="form-horizontal" commandName="form" action="adminEditTeacher.htm" method="POST">
                     <f:errors path="*" element="div" cssClass="alert alert-info" />
                        <h4 class="text-info">Fotografie</h4>
                        
                      <div class="form-group">
                            
                        <f:label path="file" class="col-lg-2 control-label">Fotografie:</f:label>
                        <div class="col-lg-5">
                            <f:input type="file" path="file" id="file" cssClass="form-control"/>
                        <f:errors path="file" element="div" cssClass="alert alert-info" />
                        </div>
                    </div>                    
                        
                        
                   
                        <h4 class="text-info">Osobní údaje</h4>
                        <div class="form-group">
                            
                        <f:label path="degree" class="col-lg-2 control-label">Titul:</f:label>
                        <div class="col-lg-5">
                        <f:input path="degree" id="degree" cssClass="form-control"/>
                        <f:errors path="degree" element="div" cssClass="alert alert-info" />
                        </div>
                         </div>
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
                         <p class="text-info"><strong> Datum zadejte ve formátu dd.mm.rrrr</strong></p>
                    </div>
                       
                   <div class="form-group">
                            
                        <f:label path="identificationNumber" class="col-lg-2 control-label">Rodné číslo<span class="glyphicon glyphicon-asterisk"></span>:</f:label>
                        <div class="col-lg-5">
                        <f:input path="identificationNumber" id="identificationNumber" cssClass="form-control"/>
                        <f:errors path="identificationNumber" element="div" cssClass="alert alert-info" />
                        </div>
                    </div> 
                         <div class="form-group">
                            
                        <f:label path="placeOfBorn" class="col-lg-2 control-label">Místo narození:</f:label>
                        <div class="col-lg-5">
                        <f:input path="placeOfBorn" id="placeOfBorn" cssClass="form-control"/>
                        <f:errors path="placeOfBorn" element="div" cssClass="alert alert-info" />
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
                        <h4 class="text-info">Zaměstnanec</h4>
                        <div class="form-group"> 
                        <f:label path="dateOfEmploy" class="col-lg-2 control-label">Datum nástupu:</f:label>
                        <div class="col-lg-5">
                        <f:input path="dateOfEmploy" id="date_of_employ" cssClass="form-control"/>
                        <f:errors path="dateOfEmploy" element="div" cssClass="alert alert-info" />
                        </div>
                        <p class="text-info"><strong> Datum zadejte ve formátu dd.mm.rrrr</strong></p>
                        </div>
                        
                        <div class="form-group">
                            
                        <f:label path="wagePerHour" class="col-lg-2 control-label">Hodinová mzda:</f:label>
                        <div class="col-lg-5">
                        <f:input path="wagePerHour" id="wagePerHour" cssClass="form-control"/>
                        <f:errors path="wagePerHour" element="div" cssClass="alert alert-info" />
                        </div>
                        </div>
                        <div class="form-group">
                            
                        <f:label path="education" class="col-lg-2 control-label">Vzdělání:</f:label>
                        <div class="col-lg-5">
                        <f:textarea path="education" id="education" value="1" cssClass="form-control"/>
                        <f:errors path="education" element="div" cssClass="alert alert-info" />
                        </div>
                        </div>
                        <div class="form-group">
                            
                        <f:label path="educationConsultant" class="col-lg-2 control-label">Pedagogický poradce :</f:label>
                        <div class="col-lg-5">
                        <f:checkbox path="educationConsultant" id="educationConsultant" value="${form.educationConsultant}" cssClass="form-control"/>
                        <f:errors path="educationConsultant" element="div" cssClass="alert alert-info" />
                        </div>
                         </div>
                        <div class="form-group">
                            
                        <f:label path="admin" class="col-lg-2 control-label">Administrátor:</f:label>
                        <div class="col-lg-5">
                        <f:checkbox path="admin" id="admin" value="${form.admin}" cssClass="form-control"/>
                        <f:errors path="admin" element="div" cssClass="alert alert-info" />
                        </div>
                         </div>
                        <f:hidden path="id" />
                        <f:hidden path="actualPage" />
                        <div class="btn-group">      
                        <button class="btn btn-primary" type="submit">
               Editovat učitele
            </button>
            <a class="btn btn-danger" href="<c:url value="/admin/infoTeacher.htm?id=${form.id}"/>"> Zrušit</a>
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