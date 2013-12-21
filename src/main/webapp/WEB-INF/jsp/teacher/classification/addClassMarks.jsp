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
    
    <h2 class="panel-title">Přidat novu známku </h2>
  
   
    
    
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
   
                    <f:form class="form-horizontal" commandName="form" action="addMarks.htm" method="POST">
                        <f:errors path="*" element="div" cssClass="alert alert-info" />
                        <div class="form-group">
                            <f:label path="name" class="col-lg-2 control-label">Název známky<span class="glyphicon glyphicon-asterisk"></span>:</f:label>
                            <div class="col-lg-5">
                            <f:input path="name" id="nameOfMark" cssClass="form-control"/>
                            <f:errors path="name" element="div" cssClass="alert alert-info"/>
                            </div>
                        </div>
                             <div class="form-group">
                            <f:label path="date" class="col-lg-2 control-label">Název známky<span class="glyphicon glyphicon-asterisk"></span>:</f:label>
                            <div class="col-lg-5">
                            <f:input path="date" id="nameOfMark" cssClass="form-control"/>
                            <f:errors path="date" element="div" cssClass="alert alert-info"/>
                            </div>
                            <f:hidden path="idTeacher" />
                            <f:hidden path="idSubject" />
                            <f:hidden path="idClass"  />
                        </div>
                        
                            <c:forEach items="${form.listOfStudents}" var="item1" varStatus="iter">
                                <div class="row"
                                     
                                     <c:if test="${(iter.index%2)==0}">
                                       style="font-weight: bold;  background-color: whitesmoke;"
                                     </c:if>
                                      <c:if test="${(iter.index%2)!=0}">
                                       style="font-weight: bold;"
                                     </c:if> 
                                         
                                    >
                                    <div class="col-lg-4">
                                        ${item1.name} ${item1.surname} ( ${item1.login})
                                        <f:hidden path="listOfStudents[${iter.index}].name"/>
                                        <f:hidden path="listOfStudents[${iter.index}].surname"/>
                                        <f:hidden path="listOfStudents[${iter.index}].login" />
                                    </div>
                                      <div class="col-lg-6">
                                          <div class="row">
                                              <div class="col-lg-4">
                                          <div class="radio">
                                              <f:label path="listOfStudents[${iter.index}].classified" style="font-weight: bold;">Absence
                                                  <f:radiobutton path="listOfStudents[${iter.index}].classified" value="2"/>
                                              </f:label>
                                              
                                          </div> 
                                              </div>
                                              <div class="col-lg-4">
                                          <div class="radio">
                                              <f:label path="listOfStudents[${iter.index}].classified" style="font-weight:bold;">Neklasifikováno
                                                  <f:radiobutton path="listOfStudents[${iter.index}].classified" value="1"/>
                                              </f:label>
                                          </div>
                                              </div>
                                              <div class="col-lg-4">
                                          <div class="radio">
                                              <f:label path="listOfStudents[${iter.index}].classified" style="font-weight: bold;">Klasifikováno
                                                  <f:radiobutton path="listOfStudents[${iter.index}].classified" value="0"/>
                                              </f:label>
                                          </div>
                                              </div>
                                      </div>
                                      </div>
                                        <div class="col-lg-1">
                                            <f:select path="listOfStudents[${iter.index}].mark"  items="${form.map}" class="form-control " />
                                        </div>
                                </div>   
                                            <f:hidden path="listOfStudents[${iter.index}].id"/>
                                            
                                            <f:hidden path="listOfStudents[${iter.index}].mark"/>
                                            
                            </c:forEach>
                        <div class="btn-group">
                        <button type="submit" class="btn btn-primary">Přidat známky</button> 
                        <a href="<c:url value="/teacher/classification/studentView.htm?idSubject=${form.idSubject}&idClass=${form.idClass}"/>" class="btn btn-danger"/>Zrušit</a>
                        <button type="reset" class="btn btn-default">Smazat</button>
                        </div>
            </f:form>
       
        
                    </div>
                </div>
            </div>
        
    


    <div class="row">


</div>
<hr>
<%@include file="../../footer.jspf" %>