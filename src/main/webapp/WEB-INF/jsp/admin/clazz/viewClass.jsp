<%-- 
    Document   : personInfo
    Created on : 24.4.2013, 0:14:56
    Author     : maru
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
    
    <h2 class="panel-title">  Seznam tříd </h2>
  
   
    
    
</div>
        <div class="panel-body">
            <div class="row">
            
                
                    <div class="col-lg-9">
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
           <div class="col-lg-3">
                <c:choose>
                <c:when test="${teacherWithoutClass!=null}">
                     
                <a href="<c:url value="addClass.htm"/>" class="btn btn-primary "> <span  class=" glyphicon glyphicon-plus"></span> Přidat třídu</a>
                </c:when>
                <c:otherwise>
                    
            
                <p class="text-danger"><strong>Nemůžete přidat třídu, nemáte volného učitele.</strong></p>

                </c:otherwise> 
                </c:choose>
                 <c:if test="${listOfClass.size()>0}">
                     <a href="<c:url value="up.htm?page=${pageForm.actualPage}"/>" class="btn btn-primary"><span class="glyphicon glyphicon-chevron-up"></span> posunout do dalšího ročníku</a>
                    </c:if>
            </div>
            </div>
            <p> </p>
            
            <c:choose>
               
                <c:when test="${listOfClass.size()>0}">
                   
            <table class="table table-bordered table-striped table-hover">
                <thead>
                            <th>Třída</th> <th>Počet let</th><th>Rok zakončení</th><th>Třídní učitel</th><th>počet žáků</th><th>Info</th><th>Editovat</th><th>Smazat</th>
                            
            </thead>
            <tfoot></tfoot>
            <tbody>
                <c:forEach items="${listOfClass}" var="classes">
                        <tr>
                            <td>${classes.nameNumber}.${classes.name}</td>
                            <td>${classes.numberOfYears}</td>
                            <td>${classes.year}</td>
                            <td><a href="<c:url value="infoTeacher.htm?id=${classes.id_teacher.id}"/>" style="color:black">${classes.id_teacher.name} ${classes.id_teacher.surname}</a></td>
                            <td>${classes.students.size()}</td>
                            <td style="text-align: center" ><a class="btn btn-info" href="<c:url value="infoClass.htm?id=${classes.id}"/>" ><span class="glyphicon glyphicon-info-sign"></span></a></td>
                            <td style="text-align: center"><a class="btn btn-success"href="editClass.htm?id=${classes.id}"><span class="glyphicon glyphicon-edit"></span></a></td>
                            <td style="text-align: center"><a class="btn btn-danger"href="<c:url value="removeClass.htm?id=${classes.id}&page=${pageForm.actualPage}"/>"><span class="glyphicon glyphicon-remove"></span></a></td>
                            
                        </tr>
                </c:forEach>
            </tbody>
</table>
           <c:if test="${pageForm.actualPage>=1}">
            <c:if test="${pageForm.actualPage>1}">
            <a href="<s:url value="/admin/classes.htm?page=1"/>" style="color: black"> <span class="glyphicon glyphicon-fast-backward"> </span></a>
            
            <a href="<s:url value="/admin/classes.htm?page=${pageForm.actualPage-1}"/>"style="color: black"><span class="glyphicon glyphicon-backward"></span></a>
            </c:if>
            strana ${pageForm.actualPage} z ${pageForm.countOfPage}
            <c:if test="${pageForm.actualPage<(pageForm.countOfPage)}">
                <a href="<c:url value="/admin/classes.htm?page=${pageForm.actualPage+1}"/>" style="color: black"/> <span class="glyphicon glyphicon-forward"> </span></a>
            
            
        <a href="<c:url value="/admin/classes.htm?page=${pageForm.countOfPage}"/>" style="color:black">
            <span class="glyphicon glyphicon-fast-forward"></span></a>
            </c:if>
        </c:if>
                </c:when>
                <c:otherwise> <p class="alert alert-warning"> Nemáte žádné třídy.</p>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
    
        
    

</div>
    <div class="row">


</div>
<hr>





<%@include file="../../footer.jspf" %>