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
            <p>
              
            </p>
            <div class="container">
                <div class="row">
                <div class="col-lg-10">
                   <c:forEach items="${listSemester}" var="item" varStatus="iter">
                        <a href="<c:url value="/student/classification/view.htm?idSubject=1&semester=${item}"/> " class="btn btn-primary" >   ${item}</a>
                        <c:if test="${((iter.index+1)%10)==0}">
                            <br/>
                        </c:if>
                        
                    </c:forEach> 
                    
                    
                </div>
                  
                </div>
                    <h4 class="text text-primary">1. pololetí</h4>
                    <c:choose>
                        <c:when test="${first.size()>0}">
                            <p>
                                <c:set var="salary" scope="session" value="${0}"/>
                                <c:set var="s2" scope="session" value="${0}"/>   
                                
                            </p>
                           
                            <table class="table table-hover table-bordered table-striped">
                                <thead>
                                    <tr style="background-color: steelblue;color: white">
                                        <th>Jméno testu </th><th>datum</th><th>Web test</th><th>známka</th>
                                    </tr>
                                </thead> 
                                
                                <tbody>
                                    
                                    <c:forEach items="${first}" var="item" varStatus="iter">
                                        <tr>
                                            <td>
                                               
                                              ${item.testName}
                                            </td>
                                            
                                            <td> ${item.dateMark} </td>
                                            <td>
                                                <c:choose>
                                                    
                                                    <c:when test="${item.webTest}">
                                                        <span class="glyphicon glyphicon-ok-circle"></span>
                                                </c:when>
                                                <c:otherwise>
                                                    <span class="glyphicon glyphicon-remove-circle"></span>
                                                </c:otherwise>
                                                </c:choose>
                                            </td>
                                            <td>
                                                
                                                 ${item.mark}
                                            </td>
                                            
                                            
                                        </tr>
                                    </c:forEach>
                                        <tfoot><tr>
                                             
                                                <th colspan="3">Průměrná známka: </th><th colspan="5"> ${firstAVG}</th>
                                    </tr></tfoot>
                                </tbody>
                            </table>
                        <br />
                        </c:when>
                        <c:otherwise>
                            <p class="alert alert-warning"> Student nemá žádné známky.
                            </p>
                        </c:otherwise>
                    </c:choose>
                   <h4 class="text text-primary">2. pololetí</h4>
                    <c:choose>
                        <c:when test="${second.size()>0}">
                            <p>
                                
                                
                            </p>
                           
                            <table class="table table-hover table-bordered table-striped">
                                <thead>
                                    <tr style="background-color: steelblue;color: white">
                                        <th>Jméno testu</th><th>datum</th><th>Web test</th><th>známka</th>
                                    </tr>
                                </thead> 
                                
                                <tbody>
                                    
                                    <c:forEach items="${second}" var="item" varStatus="iter">
                                        <tr>
                                            <td>
                                                ${item.testName}
                                            </td>
                                            
                                            <td> ${item.dateMark} </td>
                                            <td>
                                                <c:choose>
                                                    
                                                    <c:when test="${item.webTest}">
                                                        <span class="glyphicon glyphicon-ok-circle"></span>
                                                </c:when>
                                                <c:otherwise>
                                                    <span class="glyphicon glyphicon-remove-circle"></span>
                                                </c:otherwise>
                                                </c:choose>
                                            </td>
                                            <td>${item.mark}
                                                
                                            </td>
                                           
                                            
                                        </tr>
                                    </c:forEach>
                                        <tfoot><tr>
                                             
                                                <th colspan="3">Průměrná známka: </th><th>${secondAVG}</th>
                                    </tr></tfoot>
                                </tbody>
                            </table>
                        <br />
                        
                            
                           
                        </c:when>
                        <c:otherwise>
                            <p class="alert alert-warning"> Student nemá žádné známky.
                            </p>
                        </c:otherwise>
                    </c:choose>
                            
                </div>
            </div>
        
    


    <div class="row">


</div>
<hr>
<%@include file="../../footer.jspf" %>