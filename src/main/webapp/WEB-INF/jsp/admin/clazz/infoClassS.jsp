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
                             
                             <table class="table table-bordered table-striped table-hover">
                                 <thead><th>Login</th><th>Jméno</th><th>Příjmení</th><th>Přesunout do jiné třídy</th><th>Smazat studenta</th></thead>
                     <tfoot></tfoot>
                     <tbody>
                         <c:forEach items="${students}" var="students1">
                             
                     <tr>
                         <td>${students1.login}</td>
                     <td>${students1.name}</td>
                     <td>${students1.surname}</td>
                     <!--td><a href="<!--c:url value="#"/>" class="btn btn-success"><span class="glyphicon glyphicon-edit"></span> Přesunout žáka</a></td-->
                     <td>
                         <f:form   class="form-horizontal" commandName="form" action="moveStudent.htm" method="POST">
                             <div class="form-group">
                     <f:label path="clazz" class="col-lg-2 control-label"></f:label>
                       <div class="col-lg-5">
                            <f:select path="clazz" items="${listclazz}"  id="clazz" cssClass="form-control"/>
                        <f:errors path="clazz" element="div" cssClass="alert alert-info" />
                        
                       </div>
                        <button class="btn btn-primary" type="submit">Přesunout studenta
            </button>
                    </div>   
                     
                        <input type="hidden" value="${students1.id}" name="idStudent"/>
                        <f:hidden path="idClazz"/>
                        
                            
                
                         </f:form>
                     </td>
                     <td><a href="<c:url value="removeStudentC.htm?id=${students1.id}&class=${classes.id}"/>" class="btn btn-danger"><span class="glyphicon glyphicon-remove"></span> Smazat žáka</a></td>
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