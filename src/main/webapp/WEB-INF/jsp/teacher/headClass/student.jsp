Document   : personInfo
    Created on : 24.4.2013, 0:14:56
    Author     : Marie Hotkova
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@include file="view.jspf" %>
<div class="row">
            <div class="col-lg-3">
                <!--obrázek-->
               <c:choose>
                    <c:when test="${students.photo != null}">
                        <img src="<s:url value="/downloadFile.htm?id=${students.photo.id}" />" alt="profilové foto" width="300px"  class="img-thumbnail">
                        
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
                    
                    
                    
                </tbody>
                </table>
                
            </div>
                <div class="col-lg-6">
                    <table class="table table-bordered table-striped table-hover">
                    <thead>
                        <tr>
                            <th colspan="2" style="background-color: steelblue;color: white">2. zákonný zástupce</th>
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
        
    


        
    <div class="row">


</div>
<hr>





<%@include file="../../footer.jspf" %>