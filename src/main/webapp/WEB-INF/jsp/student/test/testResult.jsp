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


<div class="container">
<div class="row">
    <div class=" panel panel-default">
<div class="panel-heading">
    
    <h2 class="panel-title">Vyhodnocení testu</h2>
  
   
    
    
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
               
                
                <h3><span class="label label-success">Test byl vypracován na ${testResult.percent}%. Vaše známka je ${testResult.mark} </span></h3>
                    
                
                
                <h3 class="text text-info">Klasifikační tabulka</h3>
                <table class="table table-bordered table-striped table-hover">
                    <thead><th>Známka</th><th>od %</th><th>do %</th></thead>
                     <tfoot></tfoot>
                     <tbody>
                         <tr>
                             <td>1</td><td>${testResult.test.two+1}</td><td>100</td>
                         </tr>
                         <tr>
                             <td>2</td><td>${testResult.test.three+1}</td><td>${testResult.test.two}</td>
                         </tr>
                         <tr>
                             <td>3</td><td>${testResult.test.four+1}</td><td>${testResult.test.three}</td>
                         </tr>
                         <tr>
                             <td>4</td><td>${testResult.test.five+1}</td><td>${testResult.test.four}</td>
                         </tr>
                         <tr>
                             <td>5</td><td>0</td><td>${testResult.test.five}</td>
                         </tr>
                         
                     </tbody>
                         
                 </table >
                
                </div>
        
    


    <div class="row">


</div>
<hr>
<%@include file="../../footer.jspf" %>