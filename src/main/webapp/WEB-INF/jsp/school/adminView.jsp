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
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
                    <div class="col-lg-10"></div>
                    <div class="col-lg-2">
                        <sec:authorize ifAnyGranted="ROLE_ADMIN">
                        <a href="<c:url value="/admin/editSchool.htm"/>" class="btn btn-success"><span class="glyphicon glyphicon-edit"></span>Editovat profil školy</a>
                        </sec:authorize>
                      
                        <p></p>
                    </div>
                    
                </div>
                <div class="row">
                    <table class="table table-bordered table-hover table-striped">
                        <thead></thead>
                        <tfoot></tfoot>
                        <tbody>
                            <tr>
                                <th>Jméno školy:</th><td>${schoolInfo.schoolName}</td>
                            </tr>
                            <tr>
                                <th>Ulice</th><td>${schoolInfo.streetName}</td>
                            </tr>
                            <tr>
                                <th>Město:</th><td>${schoolInfo.city}</td>
                            </tr>
                            <tr>
                                <th>PSČ:</th><td>${schoolInfo.psc}</td>
                            </tr>
                            <tr>
                                <th>E-mail:</th><td>${schoolInfo.mail}</td>
                            </tr>
                            
                            <tr>
                                <th>Telefonní číslo:</th><td>${schoolInfo.phoneNumber}</td>
                            </tr>
                            <tr>
                                <th>Mobilní číslo:</th><td>${schoolInfo.mobilePhone}</td>
                            </tr>
                            <tr>
                                <th>Fax:</th><td>${schoolInfo.fax}</td>
                            </tr>
                        </tbody>
                        
                    </table>
                </div>
            </div>
        
    


    <div class="row">


</div>
<hr>
<%@include file="../footer.jspf" %>