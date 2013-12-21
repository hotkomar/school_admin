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
    
    <h2 class="panel-title">Klasifikace </h2>
  
   
    
    
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
           
            <div class="container">
                <div class="row">
                    <c:choose>
                        <c:when test="${subjects.size()>0}">
                    <div class="col-lg-6">
                    <table class="table table-bordered table-hover table-striped">
                        <thead>
                            <tr style="background-color: steelblue; color: white;">
                                <th>Předmět</th><th>Info</th>
                            </tr> 
                        </thead>
                        <tfoot>
                            
                        </tfoot>
                        <tbody>
                            <c:forEach  items="${subjects}" var="item">
                                <tr>
                                    <td>${item.id_subject.name}</td><td>
                                        <a href="<c:url value="/student/classification/view.htm?idSubject=${item.id}"/>" class="btn btn-info"><span class="glyphicon glyphicon-info-sign"></span></a>
                                    </td>
                                </tr>
                                
                            </c:forEach>
                        </tbody>
                    </table>
                    </div>
                        </c:when>
                        <c:otherwise>
                            <div class="col-lg-12">
                              <p class="alert alert-warning"> Student nemá žádné známky.
                            </p>  
                            </div>
                        </c:otherwise>
                    </c:choose>
                </div>
                </div>
                   
                </div>
            </div>
        
    


    <div class="row">


</div>
<hr>
<%@include file="../../footer.jspf" %>