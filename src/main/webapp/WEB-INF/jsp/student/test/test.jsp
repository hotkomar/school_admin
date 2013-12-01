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



<div class="container">
<div class="row">
    <div class=" panel panel-default">
<div class="panel-heading">
    
    <h2 class="panel-title">${form.name}</h2>
  
   
    
    
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
                
                <f:form class="form-horizontal" commandName="form" action="saveTest.htm" method="POST">
                    
                    <c:forEach items="${form.questions}" var="q" varStatus="qstat">
                             <div id="q${qstat.index}">
                                 <div class="row">      
                                     <h3>   <span class="label label-default " style="background-color: steelblue">Otázka ${qstat.index+1}) Počet bodů: ${q.points}</span></h3>
                    
                    </div>
                                 <div class="row " style= "padding-left: 50px;">
                                     
                                 <div class="col-lg-11 ">
                             
                                 <p class="">${q.question}</p>
                              
                              <f:hidden path="questions[${qstat.index}].question" />
                              <f:hidden path="questions[${qstat.index}].id"/>
                              <f:hidden path="questions[${qstat.index}].points"/>
                              
                                 
                              
                                 </div>
                              
                                     </div>
                                 
                             
                     <h4><span class="label label-default"  style="background-color: steelblue;">Odpovědi</span></h4>
                    <div id="answers" style= "padding-left: 50px;" >
                       
                    <c:forEach items="${q.answers}" var="a" varStatus="astat">
                        <div id="q${qstat.index}a${astat.index}" >
                            
                            <div class="row"  >
                                 <div class="col-lg-1" >
                               ${(astat.index)+1})
                                     <f:checkbox path="questions[${qstat.index}].answers[${astat.index}].correct" />
                                      <f:hidden path="questions[${qstat.index}].answers[${astat.index}].answer" />
                                      <f:hidden path="questions[${qstat.index}].answers[${astat.index}].id"/>
                                      
                                 </div>
                                 <div class="col-8">
                                     <p>${a.answer}</p>
                            
                                <hr/>

                                 </div>

                       </div>
                            
                            </div>
                           
                    </c:forEach>  
                        </div>
                              
                             </div-->
                         </c:forEach>
                         <f:hidden path="id"/>
                             <button type="submit" class="btn btn-primary">Odevzdat test</button>
                             <button type="reset" class="btn btn-danger">Vymazat odpvědi</button>
                </f:form>
            </div>
        
    


    <div class="row">


</div>
<hr>
<%@include file="../../footer.jspf" %>