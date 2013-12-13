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
    
    <h2 class="panel-title">Klasifikace - ${testResult.test.name} - ${testResult.student.name} ${testResult.student.surname} </h2>
  
   
    
    
</div>
        <div class="panel-body">
            
             <div class="row">
            <div class="col-lg-8">
                <a href="<c:url value="/teacher/classification/studentInfo.htm?idSubject=${testResult.test.id_subject.id}&idStudent=${testResult.student.id}&next=0&previous=0"/>"
                   class="btn btn-primary"><span class="glyphicon glyphicon-arrow-left"></span> Zpět na seznam známek
                
                </a>
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
                    <table class="table table-bordered table-hover table-striped">
                        <thead></thead>
                        <tfoot></tfoot>
                        <tbody>
                            <tr>
                                <th>Jméno testu:</th> <td>${testResult.test.name}</td>
                            </tr>
                            <tr>
                                <th>Známka:</th> <td>${testResult.mark}</td>
                            </tr>
                            <tr>
                                <th>Web test:</th> <td>
                                    <c:choose>
                                        <c:when test="${testResult.webTest}">
                                            <span class="glyphicon glyphicon-ok-sign"></span>
                                        </c:when>
                                        <c:otherwise>
                                             <span class="glyphicon glyphicon-remove-circle"></span>
                                        </c:otherwise>
                                        </c:choose>
                                    </td>
                            </tr>
                            
                        </tbody>
                    </table>
                    <div class="col-lg-4">
                    <f:form class="form-horizontal" commandName="form" action="changeMark.htm" method="POST">
                        <div class="form-group">
                            <f:label path="markDate" >Datum klasifikace: </f:label>
                            <f:input path="markDate" class="form-control"/>
                        </div>
                        <div class="form-group">
                            
                                
                                    <f:label path="classified"> Absence </f:label>
                                    <f:radiobutton path="classified" value="2" class="form-contol"/>
                            
                        </div>
                        <div class="form-group">
                            
                                
                                    <f:label path="classified"> Neklasifikováno </f:label>
                                    <f:radiobutton path="classified" value="1" class="form-contol"/>
                            
                        </div>
                                    <div class="form-group ">
    <div class="input-group ">
        <span class="input-group-addon ">
            <f:label path="classified" >Klasifikováno </f:label>
            <f:radiobutton path="classified" value="0"/>
            
        </span>
        <f:select path="mark" items="${form.map}"  class="form-control "/>
        <f:hidden path="id"/>
      
    </div>
                                    </div>
        <br/>
        
       <button class="btn btn-success" type="submit"><span class="glyphicon glyphicon-pencil"></span><strong> Změnit známku</strong></button>
            </f:form>
       
        
                    </div>
                </div>
            </div>
        
    


    <div class="row">


</div>
<hr>
<%@include file="../../footer.jspf" %>