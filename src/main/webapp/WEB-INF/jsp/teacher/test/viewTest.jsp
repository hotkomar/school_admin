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
    
    <h2 class="panel-title">  Seznam testů </h2>
  
   
    
    
</div>
        <div class="panel-body">
            <div class="row">
                <div class="col-lg-10">
                    
              
                <f:form class="form-horizontal" commandName="form" action="testFullText.htm" method="POST">
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
                    <c:choose>
                    <c:when test="${subjects}">
                    <a href="<c:url value="addTest.htm"/>" class="btn btn-primary"><span class="glyphicon glyphicon-plus"></span><strong>Nový test</strong></a>
                    
                    </c:when>
                    <c:otherwise>
                        <p class="text-danger">Nevyučujete, žádný předmět.</p>
                    </c:otherwise>
                    </c:choose>
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
            
            
            <div class="row">
                <br/>
                <c:choose >
                    <c:when test="${listTest.size()>0}">
                        <table class="table table-bordered table-striped table-hover">
                            <thead><tr style="background-color: steelblue;color: white;">
                        <th>Jméno</th><th>Předmět</th><th>Počet otázek</th><th>Zadané heslo</th><th>Info</th><th>Editovat</th><th>Smazat</th><th>Heslo</th>
                            </tr>
                        </thead>
                        <tfoot></tfoot>
                        <tbody>
                <c:forEach items="${listTest}" var="listT">
                    <tr>
                        <td>${listT.name}</td><td>${listT.id_subject.name}</td><td>${listT.questions.size()}</td>
                        <td style="text-align: center">
                            <c:if test="${listT.password!=null}">
                                <span class="glyphicon glyphicon-ok-circle">
                                   
                                    
                                </span>
                            </c:if>
                            <c:if test="${listT.password==null}">
                                <span class="glyphicon glyphicon-remove-circle">
                                   
                                    
                                </span>
                            </c:if>
                        </td>
                        <td style="text-align: center"><a href="<c:url value="infoTest.htm?test=${listT.id}"/>" class="btn btn-info"><span class="glyphicon glyphicon-info-sign"></span></a></td>
                        <td style="text-align: center"><a href="<c:url value="editTest.htm?id=${listT.id}"/>" class="btn btn-success"><span class="glyphicon glyphicon-edit"></span></a></td>
                        <td style="text-align: center"><a href="<c:url value="removeTest.htm?id=${listT.id}"/>" class="btn btn-danger"><span class="glyphicon glyphicon-remove"></span></a></td>
                        <td style="text-align: center"><a href="<c:url value="changeTestPass.htm?id=${listT.id}"/>" class="btn btn-default" style="background: black;color: white"><span class="glyphicon glyphicon-wrench"></span></a></td>
                    </tr>
                        
               
                </c:forEach>
                    </tbody>
                            
                    </table>
                        <c:if test="${pageForm.actualPage>=1}">
            <c:if test="${pageForm.actualPage>1}">
            <a href="<s:url value="/teacher/tests.htm?page=1"/>" style="color: black"> <span class="glyphicon glyphicon-fast-backward"> </span></a>
            
            <a href="<s:url value="/teacher/tests.htm?page=${pageForm.actualPage-1}"/>"style="color: black"><span class="glyphicon glyphicon-backward"></span></a>
            </c:if>
            strana ${pageForm.actualPage} z ${pageForm.countOfPage}
            <c:if test="${pageForm.actualPage<(pageForm.countOfPage)}">
                <a href="<c:url value="/teacher/tests.htm?page=${pageForm.actualPage+1}"/>" style="color: black"/> <span class="glyphicon glyphicon-forward"> </span></a>
            
            
        <a href="<c:url value="/teacher/tests.htm?page=${pageForm.countOfPage}"/>" style="color:black">
            <span class="glyphicon glyphicon-fast-forward"></span></a>
            </c:if>
        </c:if>
                    </c:when>
                <c:otherwise>
                    <p class="alert alert-warning">Nemáte žádný test.</p>
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