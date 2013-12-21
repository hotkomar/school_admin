<%-- 
    Document   : testResults
    Created on : 26.11.2013, 22:21:37
    Author     : Maru
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="testInfo.jspf" %>
<div class="row">
    <div class="col-lg-6">
    <h4 class="text text-info">Třídy ve kterých vyučujete.</h4>
<c:choose>
 
    <c:when  test="${result.size()==0}">
    <p class="alert alert-warning">Test nebyl ještě vypracován žádným studentem.</p>
    </c:when>
    <c:otherwise>
        <div class="row col-lg-8">
            <!--h4 class="text text-info">Třídy ve kterých vyučujete.</h4-->
            <br/>
        <table class="table table-bordered table-striped table-hover ">
            
            <thead><tr style="background-color: steelblue;color: white">
            <th>Třída</th><th>info</th><th>Smazat výsledky testu</th></tr>
                
            </thead>
            <tfoot></tfoot>
            <tbody>
                
        <c:forEach items="${result}" var="item">
            <tr>
                <td>${item.id_class.nameNumber}.${item.id_class.name} </td>
                <td style="text-align: center"><a href="<c:url value="classResult.htm?classID=${item.id_class.id}&test=${tests.id}"/>" class="btn btn-info"><span class="glyphicon glyphicon-info-sign"></span></a></td>
                <td style="text-align: center"><a href="<c:url value="removeClassRresult.htm?idClass=${item.id_class.id}&test=${tests.id}"/>" class="btn btn-danger"><span class="glyphicon glyphicon-remove"></span></a></td>
            </tr>
         
        </c:forEach>
            </tbody>
        </table>
            
        </div>
    </c:otherwise>
</c:choose>
    </div>
    <div class="col-lg-6">
            <h4 class="text text-info">Třídy ve kterých jste vyučoval.</h4>
<c:choose>
 
    <c:when  test="${classVisible.size()==0}">
    <p class="alert alert-warning">Test nebyl vypracován žádným studentem.</p>
    </c:when>
    <c:otherwise>
        <div class="row col-lg-8">
            <!--h4 class="text text-info">Třídy ve kterých vyučujete.</h4-->
            <br/>
        <table class="table table-bordered table-striped table-hover ">
            
            <thead><tr style="background-color: steelblue;color: white">
            <th>Třída</th><th>info</th><th>Smazat výsledky testu</th></tr>
                
            </thead>
            <tfoot></tfoot>
            <tbody>
                
        <c:forEach items="${classVisible}" var="item">
            <tr>
                <td>${item.nameNumber}.${item.name} ${item.visible}</td>
                <td style="text-align: center"><a href="<c:url value="classResult.htm?classID=${item.id}&test=${tests.id}"/>" class="btn btn-info"><span class="glyphicon glyphicon-info-sign"></span></a></td>
                <td style="text-align: center"><a href="<c:url value="removeClassRresult.htm?idClass=${item.id}&test=${tests.id}"/>" class="btn btn-danger"><span class="glyphicon glyphicon-remove"></span></a></td>
            </tr>
         
        </c:forEach>
            </tbody>
        </table>
            
        </div>
    </c:otherwise>
</c:choose>
    </div>
</div>

 </div>
        
    


        
    <div class="row">


</div>
<hr>





<%@include file="../../footer.jspf" %>