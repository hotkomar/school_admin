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
    
    <h2 class="panel-title"> Informace</h2>
  
   
    
    
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
            <div class="container">
               
                <div class="row">
                    <f:form class="form-horizontal" commandName="form" action="editSchool.htm" method="POST">
                        <div class="form-group">
                                                  
                            <f:label path="schoolName" class="col-lg-2 control-label">Název:</f:label>
                       <div class="col-lg-5">
                       <f:input  path="schoolName" id="id_class" cssClass="form-control"/>
                        <f:errors path="schoolName" element="div" cssClass="alert alert-info" />
                       </div>
                    </div>
                       <div class="form-group">
                                                  
                            <f:label path="streetName" class="col-lg-2 control-label">Ulice:</f:label>
                       <div class="col-lg-5">
                       <f:input path="streetName" id="id_class" cssClass="form-control"/>
                        <f:errors path="streetName" element="div" cssClass="alert alert-info" />
                       </div>
                    </div>
                        <div class="form-group">
                                                  
                            <f:label path="city" class="col-lg-2 control-label">Město:</f:label>
                       <div class="col-lg-5">
                       <f:input path="city" id="id_class" cssClass="form-control"/>
                        <f:errors path="city" element="div" cssClass="alert alert-info" />
                       </div>
                    </div>
                        <div class="form-group">
                                                  
                            <f:label path="psc" class="col-lg-2 control-label">PSČ:</f:label>
                       <div class="col-lg-5">
                       <f:input path="psc" id="id_class" cssClass="form-control"/>
                        <f:errors path="psc" element="div" cssClass="alert alert-info" />
                       </div>
                    </div>
                        <div class="form-group">
                                                  
                            <f:label path="mail" class="col-lg-2 control-label">E-mail:</f:label>
                       <div class="col-lg-5">
                       <f:input path="mail" id="id_class" cssClass="form-control"/>
                        <f:errors path="mail" element="div" cssClass="alert alert-info" />
                       </div>
                    </div>
                        
                        <div class="form-group">
                                                  
                            <f:label path="phoneNumber" class="col-lg-2 control-label">Telefonní číslo:</f:label>
                       <div class="col-lg-5">
                       <f:input path="phoneNumber" id="id_class" cssClass="form-control"/>
                        <f:errors path="phoneNumber" element="div" cssClass="alert alert-info" />
                       </div>
                    </div>
                    <div class="form-group">
                                                  
                            <f:label path="mobilePhone" class="col-lg-2 control-label">Mobilní číslo:</f:label>
                       <div class="col-lg-5">
                       <f:input path="mobilePhone" id="id_class" cssClass="form-control"/>
                        <f:errors path="mobilePhone" element="div" cssClass="alert alert-info" />
                       </div>
                       </div>
                       <div class="form-group">
                                                  
                            <f:label path="fax" class="col-lg-2 control-label">Fax :</f:label>
                       <div class="col-lg-5">
                       <f:input path="fax" id="id_class" cssClass="form-control"/>
                        <f:errors path="fax" element="div" cssClass="alert alert-info" />
                        <f:hidden path="id"/>
                       </div>
                       </div>
                       <button type="submit" class="btn btn-primary">Změnit</button>
                       <a href="<c:url value="/school.htm"/>" class="btn btn-danger">Zpět na profil školy</a>
                       <button type="reset" class="btn btn-default">Zrušit změny</button>
                       
                    </f:form>
                </div>
            </div>
        
    


    <div class="row">


</div>
<hr>
<%@include file="../footer.jspf" %>