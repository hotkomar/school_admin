Document   : personInfo
    Created on : 24.4.2013, 0:14:56
    Author     : Marie Hotkova
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@include file="infoClass.jspf" %>
<div class="row">
    <div class="col-lg-10">
        <p></p>
    </div>
    <div class="col-lg-2">
    <a href="<c:url value="addSubjectToClass.htm?id=${classes.id}"/>" class="btn btn-primary"><span class="glyphicon glyphicon-plus"></span>Přidat záznam</a>
    </div>
</div>
    <div class="row">
        <p></p>     
                             <table class="table table-bordered table-striped table-hover">
                                 <thead><th>Jméno předmětu</th><th>Vyučující</th><th>Odebrat záznam</th></thead>
                     <tfoot></tfoot>
                     <tbody>
                         <c:forEach items="${subjects}" var="subject">
                     <tr>
                         <td>${subject.id_subject.name}</td>
                     <td>${subject.id_teacher.degree} ${subject.id_teacher.name} ${subject.id_teacher.surname}</td>
                     <td>
                         <a href="<s:url value="removeSubjectC.htm?id=${subject.id}&class=${classes.id}"/>" class="btn btn-danger"><span class="glyphicon  glyphicon-remove"></span>Odebrat</a>
                     </td>
                     
                     </tr>
                         </c:forEach>
                     </tbody>
                             </table>
                             </div>
    
    
        
    

</div>
        
    


        
    <div class="row">


</div>
<hr>





<%@include file="../../footer.jspf" %>