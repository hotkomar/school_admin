<%-- 
    Document   : formItems
    Created on : 9.11.2013, 23:25:12
    Author     : Maru
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<c:if test="${saved == true}">
    <script>
        window.location.href = "tests.htm";
    </script>
</c:if>
<c:choose >
    
    <c:when test="${listSubject.size()>0}">
<f:form  class="form-horizontal" action="addTest.htm" method="POST" commandName="form" id="testAddForm">
    
    <h4 class="text-info">Hlavička testu</h4>
    <p class="alert alert-info">Všechny položky jsou povinné</p>
    
    
    <div class="row">
        <f:errors path="*" cssClass="alert alert-danger" element="div"/>
    </div>
    <div class="row">
        <div class="col-lg-6">
                       <div class="input-group">
                           <span class="input-group-addon">Jméno testu</span>
                            <f:input path="name" id="login" cssClass="form-control"/>

                            
                        
                       </div>
                            <f:errors path="name" element="div" cssClass="alert alert-info" />
                        <br/>
                        <!--div class="input-group">
                           <span class="input-group-addon">Přístupové heslo</span>
                           <!--f:password path="password" id="password" cssClass="form-control"/>

                            
                        
                       </div>
                            <!--f:errors path="password" element="div" cssClass="alert alert-info" />
                        <br/>
                        <div class="input-group">
                           <span class="input-group-addon">Opakujte heslo testu</span>
                           <!--f:password path="password2" id="login" cssClass="form-control"/>

                            
                        
                       </div>
                            <!--f:errors path="password2" element="div" cssClass="alert alert-info" /-->
                        <br/>
                        <div class="input-group">
                             
                            <span class="input-group-addon">Předmět</span>
                            <f:select path="id_subject" id="subject" items="${listSubject}" cssClass="form-control"/>
                        <f:errors path="id_subject" element="div" cssClass="alert alert-info" />
                            
                        </div>
                    
                        
        </div>
    </div>
                       <div class="container">
                        <div class="row">                            
                        <h5 class="text-info">Zadejte v procentech horní hranici pro známku.</h5>
                        </div>
                           <div class="col-lg-2">
                        
                        <div class="row">
                             <div class="input-group">
                                 <span class="input-group-addon">1 do:</span>
                                 <input class="form-control" value="100" disabled="true" /> 
                            <span class="input-group-addon">%</span>
                            
                            </div>       
                            </div>
                        <div class="row">
                            <div class="input-group">
                               <span class="input-group-addon">2 do:</span>
                            <f:input path="two" id="login" cssClass="form-control"/> 
                            <span class="input-group-addon">%</span>
                            
                            </div>
                            <f:errors path="two" element="div" cssClass="alert alert-info" />
                         </div>
                        <div class="row"> 
                      
                           <div class="input-group">
                               <span class="input-group-addon">3 do:</span>
                            <f:input path="three" id="three" cssClass="form-control"/> 
                            <span class="input-group-addon">%</span>
                            
                            </div>
                            <f:errors path="three" element="div" cssClass="alert alert-info" />
                            </div>
                        <div class="row">
                      
                           <div class="input-group">
                               <span class="input-group-addon">4 do:</span>  
                            <f:input path="four" id="four" cssClass="form-control"/> 
                            <span class="input-group-addon">%</span>
                            
                           </div>
                             <f:errors path="four" element="div" cssClass="alert alert-info" />      
                      
                          </div>
                        <div class="row">
                            
                           <div class="input-group">
                             <span class="input-group-addon">5 do:</span>  
                            <f:input path="five" id="five" cssClass="form-control "/> 
                            <span class="input-group-addon">%</span>
                             
                           </div>
                            <f:errors path="five" element="div" cssClass="alert alert-info" />
                            </div>
                        </div>
                           </div>
                           
                           <div class="row">
                               <div class="col-lg-12">
                          <h4 class="text-info">Zadání testu</h4> 
                          <p class="text text-info">Správné odpovědi zaškrtněte.</p>
                          <div id="qestions" class="alert " style="background-color: whitesmoke">
                         <c:forEach items="${form.questions}" var="q" varStatus="qstat">
                             <div id="q${qstat.index}">
                                 <div class="row">      
                                 <div class="col-lg-8">
                         <div class="form-group">         
                    <f:label  path="questions[${qstat.index}].points" class="col-lg-2 control-label">Počet bodů:</f:label>
                   
                    <div class="col-lg-2">
                    <f:input path="questions[${qstat.index}].points" cssClass="form-control"></f:input>
                    
                    </div>
                    
                    </div>
                    <f:errors path="questions[${qstat.index}].points" cssClass="alert alert-info" element="div"></f:errors>
                    </div>
                    <div class="col-lg-4">
                        <div class="btn-group">
                        <button type="button" id="delQ${qstat.index}"  onclick="delQuestion(${qstat.index});" class="btn btn-danger"><span class="glyphicon glyphicon-remove"></span><strong>Smazat otázku</strong></button>
                              <button type="button" id="addA${qstat.index}"  onclick="addAnswerToQuestion(${qstat.index});"  class="btn btn-primary"><span class="glyphicon glyphicon-plus"></span><strong>Přidat odpověď</strong></button>
                              </div>
                    </div>
                    </div>
                                 <div class="row">
                                    
                                 <div class="form-group">
                             <f:label path="questions[${qstat.index}].question" class="col-lg-2 control-label">Text otázky:</f:label>
                             
                              <div class="col-lg-8">
                              <f:textarea path="questions[${qstat.index}].question" cssClass="form-control" rows="4" cols="20"/>
                    
                                 </div>
                              
                                 </div>
                              <f:errors path="questions[${qstat.index}].question" element="div" cssClass="alert alert-info"></f:errors>
                                     </div>
                                 
                             
                    
                    <div id="answers" style= "padding-left: 50px;padding-right: 50px;" >
                    <c:forEach items="${q.answers}" var="a" varStatus="astat">
                        <div id="q${qstat.index}a${astat.index}" class="container alert " style="background-color:white;">
                            <div class="row">
                                <div class="col-lg-8"></div>
                                <div class="col-lg-4">
                                    <button type="button" id="delAq${qstat.index}a${astat.index}"  onclick="delAnswerInQuestion(${qstat.index},${astat.index});" class="btn btn-danger"><span class="glyphicon glyphicon-remove"></span><strong>Smazat odpověď</strong></button>
                                </div>
                            </div>
                             <div class="row">
                                 <div class="col-lg-6">
                                 <div class="input-group">
                                     
                        <f:label path="questions[${qstat.index}].answers[${astat.index}].answer" class="col-lg-2 control-label">Text odpovědi:</f:label>
                         
                        <span class="input-group-addon">
                       
                            <f:checkbox path="questions[${qstat.index}].answers[${astat.index}].correct" cssClass="control-form"/>
                        </span>
                                                
                            <f:textarea path="questions[${qstat.index}].answers[${astat.index}].answer" cssClass="form-control" rows="4" cols="20" />
                            
                         
                                 </div>
                       
                        
                                 </div>
                            <div class="col-lg-6"></div>
                                 
                        
                        
                        </div>
                            <div class="row">
                                
                                <f:errors path="questions[${qstat.index}].answers[${astat.index}].answer" element="div" cssClass="alert alert-info"></f:errors> 
                                
                            </div>
                            </div>
                           
                    </c:forEach>  
                        </div>
                              
                             </div>
                         </c:forEach>
                             
                         <button type="button" id="addQ"  onclick="addQuestion();" class="btn btn-primary"><span class="glyphicon glyphicon-plus"></span><strong>Přidat otázku</strong></button>
                         </div>
                          
                             
                             <br/><br/>
                             <div class="btn-group">
                             <button type="button" onclick="saveTest();" id="postTest"  class="btn btn-primary"><strong>Vytvořit test</strong></button>
                             <a href="<c:url value="/teacher/tests.htm"/>" id="removeTest" class="btn btn-danger"><strong>Zrušit test</strong></a>
                             <a href="<c:url value="addTestClear.htm"/>" class="btn btn-default"><strong>Smazat formulář</strong></a>
                             </div>
                             
                             
                            </div>
                            
                            </div>
</f:form>
    </c:when>
    <c:otherwise>
        <p class="alert alert-warning">Nemůžete vytvořit test.</p>
    </c:otherwise>
</c:choose>
