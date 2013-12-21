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
    <div class="col-lg-10">
        <p></p>
    </div>
    
</div>
    <div class="row">
        <p></p>     
        <c:choose>
            <c:when test="${subjects.size()>0}">
                             <table class="table table-bordered table-striped table-hover">
                                 <thead><tr style="background-color: steelblue;color: white"><th>Jméno předmětu</th><th>Vyučující</th></tr></thead>
                     <tfoot></tfoot>
                     <tbody>
                         <c:forEach items="${subjects}" var="subject">
                     <tr>
                         <td>${subject.id_subject.name}</td>
                     <td>${subject.id_teacher.degree} ${subject.id_teacher.name} ${subject.id_teacher.surname}</td>
                     
                     
                     </tr>
                         </c:forEach>
                     </tbody>
                             </table>
            </c:when>
            <c:otherwise>
                <p class="text text-warning">Nejste třídním učitelem nebo Vaše třída nemá předměty.</p>
            </c:otherwise>
        </c:choose>
                             </div>
    
    
        
    

</div>
        
    


        
    <div class="row">


</div>
<hr>





<%@include file="../../footer.jspf" %>