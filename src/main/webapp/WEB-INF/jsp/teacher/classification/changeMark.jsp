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
    
    <h2 class="panel-title">Klasifikace - ${testResult.test.name} - ${testResult.student.name} ${testResult.student.surname} </h2>
  
   
    
    
</div>
        <div class="panel-body">
            
             <div class="row">
                 
            <div class="col-lg-8">
                
                </div>
            <div class="col-lg-4">
                
                        
            </div>
            </div>
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
            <p>
                
            </p>
            <div class="container">
                <div class="row">
                 <p class="alert-info"><strong>Údaje označené symbolem <span class="glyphicon glyphicon-asterisk"></span> jsou povinné.</strong></p>
                    
                    <f:form class="form-horizontal" commandName="form" action="changeMark.htm" method="POST">
                       
                        <div class="form-group">
                            
                        <f:label path="markDate" class="col-lg-2 control-label">Datum klasifikace<span class="glyphicon glyphicon-asterisk"></span>:</f:label>
                        <div class="col-lg-2">
                        <f:input path="markDate" id="markDate" cssClass="form-control"/>
                        <f:errors path="*" element="div" cssClass="alert alert-info" />
                        </div>
                       
                         
                        <p class="text-info"><strong> Datum zadejte ve formátu dd.mm.rrrr</strong></p>
                        
                    </div>
                        <div class="radio">
                            
                                
                                    <f:label path="classified" > Absence 
                                    
                                    <f:radiobutton path="classified" value="2" />
                                    </f:label>
                                    
                        </div>       
                            
                                    <div class="radio">
                        
                            
                                
                                    <f:label path="classified" > Neklasifikováno 
                                   
                                    <f:radiobutton path="classified" value="1" />
                           </f:label>
                                    </div>    
                            
                        
                                    
                                    <div class="row">       
                                        <div class="col-lg-2">
                                            <div class="radio">
            <f:label path="classified"  >Klasifikováno:
            
            <f:radiobutton path="classified" value="0" class="form-cont"/>
             </f:label>
                                            </div>
                                        </div>
                                            <div class="col-lg-1">
        <f:select path="mark" items="${form.map}"  class="form-control "/>
        <f:hidden path="id"/>
                                            </div>
                                    </div>
    
        <br/>
        <div class="btn-group"> 
       <button class="btn btn-primary" type="submit"><strong> Změnit známku</strong></button>
        <a href="<c:url value="/teacher/classification/studentInfo.htm?idSubject=${testResult.test.id_subject.id}&idStudent=${testResult.student.id}&next=0&previous=0"/>"
           class="btn btn-danger"><strong> Zrušit </strong>       </a>
        </div>
            </f:form>
       
        
                    </div>
                </div>
            </div>
        
    


    <div class="row">


</div>
<hr>
<%@include file="../../footer.jspf" %>