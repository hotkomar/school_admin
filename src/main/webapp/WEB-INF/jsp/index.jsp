<%-- 
    Document   : index
    Created on : 19.4.2013, 18:06:22
    Author     : Marie Hoťková
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="f" uri="http://www.springframework.org/tags/form" %>

       
        <%@include file="header.jspf"  %>
        <table class="table">
            <tr>
                <td>karel</td><td>pepa</td>
            </tr>
            <tr>
                <td>ěščřžýáíéúůóŇ</td><td>pepa</td>
            </tr>
        </table>
        <p class="btn">${prvek.name}</p>
        
        <f:form class="form-horizontal" commandName="form" action="index.htm" method="POST">
            <div class="control-group">
                            <h4 class="text-info">přidat roly</h4>
                        <f:label path="name">jméno*:</f:label>
                        <f:input path="name" id="name"/>
                        <f:errors path="name" element="div" cssClass="alert alert-info"/>
                    </div>
            <button class="btn btn-primary" type="submit" id="but" >vytvořit</button>
        </f:form>
        <%@include file="footer.jspf" %>