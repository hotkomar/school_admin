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
    
    <h2 class="panel-title">  Seznam učitelů </h2>
  
   
    
    
</div>
        <div class="panel-body">
            <div class="row">
            <div class="col-lg-10">
                <f:form class="form-horizontal" commandName="form" action="teachersFulltext.htm" method="POST">
                <div class="form-group">
                                                  
                            <f:label path="fullText" class="col-lg-2 control-label">Vyheldat:</f:label>
                       <div class="col-lg-5">
                            <f:input path="fullText" id="fullText" cssClass="form-control"/>
                        <f:errors path="fullText" element="div" cssClass="alert alert-info" />
                       </div>
                       <button class="btn btn-primary" type="submit">Hledej</button>
                    </div>
                      
            </f:form>
            </div>
            <div class="col-lg-2">
                <a href="<c:url value="addTeacher.htm"/>" class="btn btn-primary "> <span  class=" glyphicon glyphicon-plus"></span> Přidat učitele</a>
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
            <p></p>
            <c:choose>
                <c:when test="${listOfTeachers.size()>0}">
            <table class="table table-bordered table-striped table-hover">
                <thead>
                <th>Přihlašovací jméno</th> <th>Jméno</th><th>Příjmení</th><th>Třídní učiel</th><th>Pedagogický konzultant</th><th>Administrátor</th><th>Info</th><th>Editovat</th><th>Smazat</th><th>Změnit heslo</th>
                            
            </thead>
            <tfoot>
            </tfoot>
            <tbody>
                <c:forEach items="${listOfTeachers}" var="teachers">
                    <tr>
                        <td><a href="<c:url  value="infoTeacher.htm?id=${teachers.id}"/>" style="color: black">${teachers.login}</a></td>
                            <td>${teachers.name}</td><td>${teachers.surname}</td><td>${teachers.id_class.nameNumber}.${teachers.id_class.name}</td>
                            <td style="text-align: center"><c:if test="${teachers.education_consultant}"><span class="glyphicon glyphicon-ok"></span></c:if></td>
                            <td style="text-align: center">
                            <c:forEach items="${teachers.idType}" var="id">
                                
                                        <c:choose>
                                            <c:when test="${id.name=='ROLE_ADMIN'}">
                                                <span class="glyphicon glyphicon-ok"></span>
                                            </c:when>
                                            
                                        </c:choose>
                            </c:forEach>
                            </td>
                            <td style="text-align: center" ><a class="btn btn-info" href="<c:url  value="infoTeacher.htm?id=${teachers.id}&page=${pageForm.actualPage}"/>"><span class="glyphicon glyphicon-info-sign"></span></a></td>
                            <td style="text-align: center"><a class="btn btn-success"href="<c:url value="editTeacher.htm?id=${teachers.id}&page=${pageForm.actualPage}"/>"><span class="glyphicon glyphicon-edit"></span></a></td>
                            <td style="text-align: center"><a class="btn btn-danger"href="<c:url value="removeTeacher.htm?id=${teachers.id}&page=${pageForm.actualPage}"/>"><span class="glyphicon glyphicon-remove"></span></a></td>
                            <td style="text-align: center"><a class="btn btn-default" style="background: black; color: white"  href="<c:url value="passTeacher.htm?id=${teachers.id}&page=${pageForm.actualPage}"/>"><span class="glyphicon glyphicon-wrench"></span></a></td>
                        </tr>
                </c:forEach>
            </tbody>
</table>
           <c:if test="${pageForm.actualPage>=1}">
            <c:if test="${pageForm.actualPage>1}">
            <a href="<s:url value="/admin/teachers.htm?page=1"/>" style="color: black"> <span class="glyphicon glyphicon-fast-backward"> </span></a>
            
            <a href="<s:url value="/admin/teachers.htm?page=${pageForm.actualPage-1}"/>"style="color: black"><span class="glyphicon glyphicon-backward"></span></a>
            </c:if>
            strana ${pageForm.actualPage} z ${pageForm.countOfPage}
            <c:if test="${pageForm.actualPage<(pageForm.countOfPage)}">
                <a href="<c:url value="/admin/teachers.htm?page=${pageForm.actualPage+1}"/>" style="color: black"/> <span class="glyphicon glyphicon-forward"> </span></a>
            
            
        <a href="<c:url value="/admin/teachers.htm?page=${pageForm.countOfPage}"/>" style="color:black">
            <span class="glyphicon glyphicon-fast-forward"></span></a>
            </c:if>
        </c:if>
                </c:when>
                <c:otherwise>
                    <p class="alert alert-warning"><strong>Nemáte žádné učitele.</strong></p>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
    
        
    

</div>
    <div class="row">


</div>
<hr>





<%@include file="../../footer.jspf" %>