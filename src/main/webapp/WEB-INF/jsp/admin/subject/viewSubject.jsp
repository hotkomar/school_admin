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
    
    <h2 class="panel-title">  Seznam předmětů </h2>
  
   
    
    
</div>
        <div class="panel-body">
            <div class="row">
            <div class="col-lg-10">
                <f:form class="form-horizontal" commandName="form" action="subjectFullText.htm" method="POST">
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
                <a href="<c:url value="addSubject.htm"/>" class="btn btn-primary "> <span  class=" glyphicon glyphicon-plus"></span> Přidat předmět</a>
            </div>
            </div>
            <p></p>
            <c:choose>
                <c:when test="${subjects.size()>0}">
            <table class="table table-bordered table-striped table-hover">
                <thead>
                            <th>Jméno předmětu</th> <th>Platnost od</th><th>Platnost do</th><th>Vyučován</th><th>Info</th><th>Editovat</th><th>Smazat</th>
                            
            </thead>
            <tfoot>
            </tfoot>
            <tbody>
                <c:forEach items="${subjects}" var="sub">
                        <tr>
                            <td>${sub.name}</td><td>${sub.dateValidFrom}</td><td>${sub.dateExpiration}</td><td>${sub.subjects.size()}</td>
                            <td style="text-align: center" ><a class="btn btn-info" href="<s:url value="infoSubject.htm?id=${sub.id}"/>" ><span class="glyphicon glyphicon-info-sign"></span></a></td>
                            <td style="text-align: center"><a class="btn btn-success"href="<s:url value="editSubject.htm?id=${sub.id}"/>"><span class="glyphicon glyphicon-edit"></span></a></td>
                            <td style="text-align: center"><a class="btn btn-danger" href="<s:url value="removeSubject.htm?id=${sub.id}&page=${pageForm.actualPage}"/>"><span class="glyphicon glyphicon-remove"></span></a></td>
                            
                        </tr>
                </c:forEach>
            </tbody>
</table>
            <c:if test="${pageForm.actualPage>=1}">
            <c:if test="${pageForm.actualPage>1}">
            <a href="<s:url value="/admin/subjects.htm?page=1"/>" style="color: black"> <span class="glyphicon glyphicon-fast-backward"> </span></a>
            
            <a href="<s:url value="/admin/subjects.htm?page=${pageForm.actualPage-1}"/>"style="color: black"><span class="glyphicon glyphicon-backward"></span></a>
            </c:if>
            strana ${pageForm.actualPage} z ${pageForm.countOfPage}
            <c:if test="${pageForm.actualPage<(pageForm.countOfPage)}">
                <a href="<c:url value="/admin/subjects.htm?page=${pageForm.actualPage+1}"/>" style="color: black"/> <span class="glyphicon glyphicon-forward"> </span></a>
            
            
        <a href="<c:url value="/admin/subjects.htm?page=${pageForm.countOfPage}"/>" style="color:black">
            <span class="glyphicon glyphicon-fast-forward"></span></a>
            </c:if>
        </c:if>
                </c:when>
                <c:otherwise>
                    <p class="alert alert-warning">Nemáte žádný předmět.</p>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
    
        
    

</div>
    <div class="row">


</div>
<hr>





<%@include file="../../footer.jspf" %>