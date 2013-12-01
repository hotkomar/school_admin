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
    
    <h2 class="panel-title"> Spustit test</h2>
  
   
    
    
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
                <div class="col-lg-8">
       <f:form  class="form-horizontal" commandName="form" action="openTest.htm" method="POST">
    <div class="input-group">
       
      <span class="input-group-addon text-info">
          <strong> Přístupové heslo k testu:</strong>
      </span>
      <f:password path="password" class="form-control"  style="text-align: center; font-weight: bolder"  />
      <f:hidden path="id" />
      
     
      <span class="input-group-btn">
          <button  class="btn btn-primary" type="submit"><strong>Spustit test</strong></button>
      </span>
    </div>  
       </f:form>
        </div>
            </div>
        
    


    <div class="row">


</div>
<hr>
<%@include file="../../footer.jspf" %>