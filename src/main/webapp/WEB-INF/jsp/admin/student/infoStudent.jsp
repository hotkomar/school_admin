 Document   : personInfo
    Created on : 24.4.2013, 0:14:56
    Author     : Marie Hotkova
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
    
    <h2 class="panel-title">  Karta studenta </h2>
  
   
    
    
</div>
        <div class="panel-body">
             <div class="row">
            <div class="col-lg-7">
              <a href="<s:url value="/admin/students.htm?page=${pagination.page}" />" class="btn btn-primary"><span class="glyphicon glyphicon-arrow-left"></span> <strong> Zpět na seznam studentů</strong></a>
            </div>
            <div class="col-lg-5">
                <div class="btn-group">   
                <a href="<c:url value="editStudent.htm?id=${students.id}"/>" class=" btn btn-success "> <span class="glyphicon glyphicon-edit"></span><strong> Editovat záznam</strong></a>
           
                <a href="<c:url value="/admin/removeStudent.htm?id=${students.id}&page=${pagination.page}"/>" class=" btn btn-danger "> <span class="glyphicon glyphicon-remove"></span><strong> Smazat záznam</strong></a>
           <a href="<c:url value="studentPass.htm?id=${students.id}"/>" class=" btn btn-default " style="color: white; background: black"> <span class="glyphicon glyphicon-wrench"></span><strong> Změnit heslo</strong></a>
                </div>
                </div>
            </div>
            <p>
                
            </p>
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
            <div class="col-lg-3">
                <!--obrázek-->
               <c:choose>
                    <c:when test="${students.photo != null}">
                        <img src="<s:url value="/downloadFile.htm?id=${students.photo.id}" />" alt="profilové foto" width="300px" height="425px"  class="img-thumbnail" style="margin-bottom:  10px">
                        <a href="<s:url value="removePhotoS.htm?id=${students.id}"/>" class="btn btn-danger" ><span class="glyphicon glyphicon-remove"></span>Smazat Fotografii</a>
                        <p></p>
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
                        <th>Login:</th><td>${students.login}</td>
                    </tr>
                    <tr>
                        <th>Jméno:</th><td>${students.name}</td>
                    </tr>
                    <tr>
                        <th>Příjmení:</th><td>${students.surname}</td>
                    </tr>
                    <tr>
                        <th>Datum narození:</th><td>${students.born}</td>
                    </tr>
                    <tr>
                        <th>Rodné číslo:</th><td>${students.identificationNumber}</td>
                    </tr>
                    <tr>
                        <th>Místo narození:</th><td>${students.placeOfBorn}</td>
                    </tr>
                    
                    
                    
                    
                </tbody>
                </table>
                </div>
            </div>
                     <div class="row">
            <div class="col-lg-3">
               <table class="table table-bordered table-striped table-hover">
                    <thead>
                        <tr style="background-color: steelblue;color: white">
                            <th>Třída</th>
                        </tr>
                       
                    
                </thead>
                <tfoot>
                    
                </tfoot>
                <tbody>
                    <tr>
                        <th ><a href="<c:url value="/admin/infoClass.htm?id=${students.id_class.id}"/>" style="color: black"> ${students.id_class.nameNumber}.${students.id_class.name}</a></th>
                    </tr>
                    
                    
                    
                </tbody>
                </table>
                
            </div>
                <div class="col-lg-9">
                    <table class="table table-bordered table-striped table-hover">
                    <thead>
                        <tr style="background-color: steelblue;color: white">
                            <th colspan="2">Kontaktní údaje</th>
                        </tr>
                    
                </thead>
                <tfoot>
                    
                </tfoot>
                <tbody>
                    <tr>
                        <th>e-mail:</th><td>${students.mail}</td>
                    </tr>
                    <tr>
                        <th>Telefoní číslo:</th><td>${students.phoneNumber}</td>
                    </tr>
                    <tr>
                        <th>Mobil:</th><td>${students.mobilePhone}</td>
                    </tr>
                    <tr>
                        <th>Fax:</th><td>${students.fax}</td>
                    </tr>
                    
                    
                    
                </tbody>
                </table>
                </div>
            </div> 
                 <div class="row">
            <div class="col-lg-6">
               <table class="table table-bordered table-striped table-hover">
                    <thead>
                        <tr style="background-color: steelblue;color: white">
                            <th colspan="2">1. zákonný zástupce</th>
                        </tr>
                       
                    
                </thead>
                <tfoot>
                    
                </tfoot>
                <tbody>
                    <tr>
                        <th>Jméno:</th> <td>${students.motherName}</td>
                    </tr>
                    <tr>
                        <th>Příjmení:</th> <td>${students.motherSurname}</td>
                    </tr>
                    <tr>
                        <th>Telefon:</th> <td>${students.motherPhone}</td>
                    </tr>
                    <tr>
                        <th>Fax:</th> <td>${students.motherMail}</td>
                    </tr>
                    <tr>
                        <th>Login:</th><td>${studentParent.login}</td>
                    </tr>
                    
                    
                </tbody>
                </table>
                
            </div>
                <div class="col-lg-6">
                    <table class="table table-bordered table-striped table-hover">
                    <thead>
                        <tr style="background-color: steelblue;color: white">
                            <th colspan="2">2. zákonný zástupce</th>
                        </tr>
                    
                </thead>
                <tfoot>
                    
                </tfoot>
                <tbody>
                    <tr>
                        <th>Jméno:</th> <td>${students.fatherName}</td>
                    </tr>
                    <tr>
                        <th>Příjmení:</th> <td>${students.fatherSurname}</td>
                    </tr>
                    <tr>
                        <th>Telefon:</th> <td>${students.fatherPhone}</td>
                    </tr>
                    <tr>
                        <th>Fax:</th> <td>${students.fatherMail}</td>
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