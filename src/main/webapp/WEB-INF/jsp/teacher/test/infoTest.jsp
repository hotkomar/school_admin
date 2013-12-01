 Document   : personInfo
    Created on : 24.4.2013, 0:14:56
    Author     : Marie Hotkova
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="testInfo.jspf" %>
                         
                      <div class="row">
    
                          
                          <table class="table table-bordered table-hover">
                              <thead></thead>
                              <tfoot></tfoot>
                              <tbody>
                                  <c:forEach items="${tests.questions}" var="q">
                                      <tr style="background-color: steelblue;color:white">
                                      <th>Otázka:</th><td>${q.question}</td>
                                  </tr>
                                  <tr>
                                      <th>Počet bodů za otázku:</th><td>${q.points}</td>
                                  </tr>
                                  <c:forEach items="${q.answers}" var="a">
                                      <tr <c:if test="${a.correct}">
                                              class="success" style="font-weight: bolder"
                                              </c:if>>
                                          <td>Odpověď
                                              <c:if test="${a.correct}">
                                                  (správná)
                                              </c:if>
                                              
                                              :</td>
                                          <td>${a.answer}</td>
                                          
                                      </tr>
                                  </c:forEach>
                                  </c:forEach>
                              </tbody>
                          </table>     
        
        <br/>  
    </div>      
 </div>
        
    


        
    <div class="row">


</div>
<hr>





<%@include file="../../footer.jspf" %>                        