 Document   : personInfo
    Created on : 24.4.2013, 0:14:56
    Author     : Marie Hotkova
--%>

<%@page pageEncoding="UTF-8"%>
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
    
    <h2 class="panel-title">  Karta předmětu </h2>
  
   
    
    
</div>
        <div class="panel-body">
            <div class="row">
            <div class="col-lg-8">
                <a href="<s:url value="/admin/subjects.htm?page=${pagination.page}" />" class="btn btn-primary"><span class="glyphicon glyphicon-arrow-left"></span> <strong> Zpět na seznam tříd</strong></a>
                
                </div>
            <div class="col-lg-4">
                <div class="btn-group">
                <a href="<c:url value="editSubject.htm?id=${subjects.id}"/>" class=" btn btn-success "> <span class="glyphicon glyphicon-edit"></span><strong> Editovat záznam</strong></a>
                <a href="<c:url value="removeSubject.htm?id=${subjects.id}&page=${pagination.page}"/>" class=" btn btn-danger "> <span class="glyphicon glyphicon-remove"></span><strong> Smazat záznam</strong></a>
                </div>
                        
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
             <div class="row">
         
                 <table class="table table-bordered table-striped table-hover">
                     <thead></thead>
                     <tfoot></tfoot>
                     <tbody>
                         <tr>
                             <th>Jméno přemětu:</th><td>${subjects.name}</td>
                         </tr>
                         <tr>
                             <th>Platný od:</th><td>${subjects.dateValidFrom}</td>
                         </tr>
                         <tr>
                             <th>Platný do:</th><td>${subjects.dateExpiration}</td>
                         </tr>
                         <tr>
                             <th>Vyučován: </th><td>${subjects.subjects.size()}</td>
                         </tr>
                         
                     </tbody>
                         
                 </table >
             </div>
                         
                      <div class="row">
    
                         <table class="table table-bordered table-striped table-hover">
                             <thead><th>Třída</th><th>Učitel</th><th>Odebrat třídě </th></thead>
                     <tfoot></tfoot>
                     <tbody>
                         <c:forEach items="${classes}" var="clazz">
                             <tr>
                                 <td>${clazz.id_class.nameNumber}.${clazz.id_class.name}</td><td>${clazz.id_teacher.degree} ${clazz.id_teacher.name} ${clazz.id_teacher.surname}</td>
                                 <td>
                                     <a href="<c:url value="removeS.htm?id=${clazz.id}&subject=${subjects.id}"/>" class="btn btn-danger"><span class="glyphicon glyphicon-remove"></span> Odebrat</a>
                                 </td>
                             </tr>
                         </c:forEach>
                         
                     </tbody>
                         
                 </table > 
        
        <br/>  
    </div>      
                         