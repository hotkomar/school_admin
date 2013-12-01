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
                             
                             <table class="table table-bordered table-striped table-hover">
                                 <thead><th>Login</th><th>Jméno</th><th>Příjmení</th><th>info</th></thead>
                     <tfoot></tfoot>
                     <tbody>
                         <c:forEach items="${students}" var="students1">
                             
                     <tr>
                         <td>${students1.login}</td>
                     <td>${students1.name}</td>
                     <td>${students1.surname}</td>
                     <td style="text-align: center"><a href="<s:url value="headClassS.htm?id=${students1.id}"/>" class="btn btn-info"><span class="glyphicon glyphicon-info-sign"></span></a></td>
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