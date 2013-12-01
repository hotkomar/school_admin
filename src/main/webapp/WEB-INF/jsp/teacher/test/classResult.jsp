<%-- 
    Document   : classResult
    Created on : 27.11.2013, 15:51:18
    Author     : Maru
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="testInfo.jspf" %>
<div class="row col-lg-6">

    <table class="table table-bordered table-striped table-hover ">
        <thead><tr><th>Login</th><th>Jméno</th><th>Příjmení</th><th>Zobrazit výsledek testu</th></tr></thead>
        <tfoot></tfoot>
        <tbody>
    <c:forEach items="${results}"  var="item">
        <tr>
            <td>${item.login}</td><td>${item.name}</td><td>${item.surname}</td>
            <td style="text-align: center;">
                <a href="<c:url value="studentResult.htm?student=${item.id}&test=${tests.id}&idClass=${clazzID}"/>" class="btn btn-info"><span class="glyphicon glyphicon-info-sign"></span></a>    
            </td>
            
        </tr>
    </c:forEach>
        </tbody>
    </table>
    
    
    
    
</div>

        
    


        
    <div class="row">


</div>
<hr>





<%@include file="../../footer.jspf" %>