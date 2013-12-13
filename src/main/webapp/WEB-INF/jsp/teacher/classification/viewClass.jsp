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
    
    <h2 class="panel-title">Klasifikace <c:if test="${classList.size()!=0}">- ${classList[0].id_subject.name}</c:if> </h2>
  
   
    
    
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
                    
                    <c:choose>
                        <c:when test="${classList.size()>0}">
                            <div class="col-lg-2"> 
                    <table class="table table-bordered table-hover">
                        <thead><tr style="background-color: steelblue; color: white">
                                <th>Číslo</th><th>Třída</th><th>Info</th>
                            </tr></thead>
                        <tfoot></tfoot>
                        <tbody>
                            <c:forEach items="${classList}" var="item" varStatus="iter">
                                <tr>
                                    <td>${iter.index+1}</td>
                                    <td>${item.id_class.nameNumber}.${item.id_class.name}</td>
                                    <td>
                                        <a href="<c:url value="studentView.htm?idSubject=${item.id_subject.id}&idClass=${item.id_class.id}"/>" class="btn btn-info"><span class="glyphicon glyphicon-info-sign"></span></a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    
                    </table>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <p class="alert alert-warning">Třída nemá žádné studenty.</p>
                        </c:otherwise>
                    </c:choose>
                           
                </div>
            </div>
        
    


    <div class="row">


</div>
<hr>
<%@include file="../../footer.jspf" %>