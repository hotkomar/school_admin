<%-- 
    Document   : classResult
    Created on : 27.11.2013, 15:51:18
    Author     : Maru
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="testInfo.jspf" %>
<div class="row">
    <div class="col-lg-6">
        
        <a href="<c:url value="classResult.htm?classID=${clazzID}&test=${tests.id}"/>" class="btn btn-primary"><span class="glyphicon glyphicon-arrow-left"></span><strong> Zpět na seznamt studentů</strong></a>
    </div>  
    <div class="col-lg-6">
        
    </div>
    
</div>
    <br/>
     <c:choose>
         <c:when test="${results.size()>0}">
            <div class="row">
    <div class="col-lg-9">
       <c:if test="${form!=null}">
        <div class="col-lg-4">
            <f:form class="form-horizontal" commandName="form" action="#" method="POST">
    <div class="input-group">
        <f:select path="mark" items="${form.map}"  class="form-control"/>
        <f:hidden path="id"/>
      <span class="input-group-btn">
          <button class="btn btn-success" type="submit"><span class="glyphicon glyphicon-pencil"></span><strong> Změnit známku</strong></button>
      </span>
    </div>
            </f:form>
  </div>

<div class="col-lg-8">
        
        <a href="<c:url value="newResult.htm?student=${results[0].idStudent}&test=${tests.id}&idClass=${clazzID}"/>" class="btn btn-warning"><span class="glyphicon glyphicon-repeat"></span><strong> Opakovat test</strong></a>
        
        </div>
        </c:if>
    </div>
    <div class="col-lg-3">
        
    </div>
</div>
   
        
       
    
<div class="row col-lg-6">
    <br/>
   <p class="text text-info">* Aktuálně platná známka je modře vyznačena a nejde smazat.</p>
    <table class="table table-bordered">
        <thead><tr><th>Číslo</th><th>Datum</th><th>Známka</th><th>Procenta</th><th>Smazat</th></tr></thead>
        <tfoot></tfoot>
        <tbody>
    <c:forEach items="${results}"  var="item" varStatus="iterator">
        <c:choose>
            <c:when test="${item.actualMark}">
            
                <c:choose>
                    <c:when test="${item.mark!=null}">
       <tr      style="background-color: steelblue;color: white"                                              >
            <td >
               
                
                ${iterator.index+1} </td><td>${item.testResultDate}</td><td>${item.mark}</td><td>${item.percent}%</td>
            <td>
                <span  class="btn btn-default"><span class="glyphicon glyphicon-remove"></span></span>
            </td>
         
            
        </tr>
                    </c:when >
                    <c:otherwise>
                       <tr      style="background-color: steelblue;color: white"                                              >
            <td >
               
                
                ${iterator.index+1} </td><td>${item.testResultDate}</td><td colspan="2" class="text text-danger">Student řádně neodevzdal test</td>
            <td>
                <span  class="btn btn-default"><span class="glyphicon glyphicon-remove"></span></span>
            </td>
         
            
        </tr>
                    </c:otherwise>
                </c:choose>
        
            </c:when>
            <c:otherwise>
                <c:choose>
                    <c:when test="${item.mark!=null}">
                <tr >
            <td >
               
                
                ${iterator.index+1} </td><td>${item.testResultDate}</td><td>${item.mark}</td><td>${item.percent}%</td>
            <td>
                <a href="<c:url value="removeMark.htm?result=${item.id}&test=${tests.id}&idClass=${clazzID}"/>" class="btn btn-danger"><span class="glyphicon glyphicon-remove"></span></a>
            </td>
         
            
        </tr>
                    </c:when>
                    <c:otherwise>
                        <tr  >
            <td >
               
                
                ${iterator.index+1} </td><td>${item.testResultDate}</td><td colspan="2" class="text text-danger">Student řádně neodevzdal test</td>
            <td>
                 <a href="<c:url value="removeMark.htm?result=${item.id}&test=${tests.id}&idClass=${clazzID}"/>" class="btn btn-danger"><span class="glyphicon glyphicon-remove"></span></a>
            </td>
         
            
        </tr>
                    </c:otherwise>
                </c:choose>
            </c:otherwise>
        </c:choose>
    </c:forEach>
        </tbody>
    </table>
    
       </c:when>
        <c:otherwise>
            <p class="alert alert-warning">Student test nevypracoval.</p>
        </c:otherwise>
            
        </c:choose>
    
    </div>
    


        
    


        
    <div class="row">


</div>
<hr>





<%@include file="../../footer.jspf" %>