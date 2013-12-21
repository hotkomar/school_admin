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
    
    <h2 class="panel-title">  Profil</h2>
  
   
    
    
</div>
        <div class="panel-body">
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
            <div class="col-lg-8">
                
                </div>
            <div class="col-lg-4">
                <div class="btn-group">
                <a href="<c:url value="editTeacher.htm"/>" class=" btn btn-success "> <span class="glyphicon glyphicon-edit"></span><strong> Editovat profil</strong></a>
                
                <a href="<c:url value="changePass.htm"/>" class=" btn btn-default " style="color: white; background: black"> <span class="glyphicon glyphicon-wrench"></span><strong> Změnit heslo</strong></a>
                </div>       
            </div>
            </div>
                
            <p>
                
            </p>
            <div class="row">
            <div class="col-lg-3">
                <!--obrázek-->
               <c:choose>
                    <c:when test="${teachers.photo != null}">
                        <img src="<s:url value="/downloadFile.htm?id=${teachers.photo.id}" />" alt="profilové foto" width="300px"  class="img-thumbnail">
                        
                    </c:when>
                    <c:otherwise>
                        <img src="<s:url value="/img/unknown.jpg" />" alt="profilové foto" width="300px"  class="img-thumbnail">
                        </c:otherwise>
                </c:choose>
                        
                        
            </div>
                <div class="col-lg-9">
                    <table class="table table-bordered table-striped table-hover">
                    <thead>
                        <tr style="background-color: steelblue;color: white">
                            <th colspan="2">Osobní údaje</th>
                        </tr>
                    
                </thead>
                <tfoot>
                    
                </tfoot>
                <tbody>
                    <tr>
                        <th>Login:</th><td>${teachers.login}</td>
                    </tr>
                    <tr>
                        <th>Titul:</th><td>${teachers.degree}</td>
                    </tr>
                    <tr>
                        <th>Jméno:</th><td>${teachers.name}</td>
                    </tr>
                    <tr>
                        <th>Příjmení:</th><td>${teachers.surname}</td>
                    </tr>
                    
                    
                    
                    
                    
                </tbody>
                </table>
                </div>
            </div>
                     <!--div class="row">
                         <div class="col-lg-3"></div>
                         <div class="col-lg-9">
            
                
            </div>
                     </div-->
                    <div class="row">
                        <div class="col-lg-3">
                               <table class="table table-bordered table-striped table-hover">
                    <thead>
                     
                            <th colspan="2" style="background-color: steelblue;color: white">Pozice</th>
                      
                    
                </thead>
                <tfoot>
                    
                </tfoot>
                <tbody>
                    <tr>
                        <th>Třídní učitel:</th>
                        <td><a href="<c:url value="/teacher/headClass.htm"/>" style="color:black"> ${teachers.id_class.nameNumber}.${teachers.id_class.name}</a></td>
                    </tr>
                    <tr>
                        <th>Pedagogický poradce:</th>
                        <td><c:if test="${teachers.education_consultant}">
                                <span class="glyphicon glyphicon-ok"></span>
                            </c:if></td>
                    </tr>
                    <tr>
                        <th>Administrátor:</th>
                        <td>
                            <c:forEach items="${teachers.idType}" var="id">
                                
                                        <c:choose>
                                            <c:when test="${id.name=='ROLE_ADMIN'}">
                                                <span class="glyphicon glyphicon-ok"></span>
                                            </c:when>
                                            
                                        </c:choose>
                            </c:forEach>
                            
                        </td>
                    </tr>
                    
                    
                </tbody>
                </table>
                        </div>
                <div class="col-lg-9">
                    <table class="table table-bordered table-striped table-hover">
                    <thead>
                        <tr>
                            <th colspan="2" style="background-color: steelblue;color: white">Kontaktní údaje</th>
                        </tr>
                    
                </thead>
                <tfoot>
                    
                </tfoot>
                <tbody>
                    <tr>
                        <th>e-mail:</th><td>${teachers.mail}</td>
                    </tr>
                    <tr>
                        <th>Telefoní číslo:</th><td>${teachers.phoneNumber}</td>
                    </tr>
                    <tr>
                        <th>Mobil:</th><td>${teachers.mobilePhone}</td>
                    </tr>
                    <tr>
                        <th>Fax:</th><td>${teachers.fax}</td>
                    </tr>
                    
                    
                    
                </tbody>
                </table>
                </div>
            </div> 
                
                    
        </div>
    </div>
    
        
    

</div> 
        
    


    <div class="row">


</div>
<hr>
<%@include file="../../footer.jspf" %>