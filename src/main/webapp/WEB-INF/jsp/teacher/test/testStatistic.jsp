<%-- 
    Document   : classResult
    Created on : 27.11.2013, 15:51:18
    Author     : Maru
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="testInfo.jspf" %>

<div class="row">
    <h3><span class="label label-default" style="background-color: steelblue">Procentuální zastoupení jednotlivých známek</span></h3>
</div>
<div class="row">
    <p class="text text-info"><strong>Celkový počet známek: ${markCount}</strong></p>
    <c:if test="${markCount}>0">
    <div class="row">
        <div class="col-lg-1"><span class="label" style="color: black;"> Výborně 1</span></div>
        <div class="col-lg-10">
   <div class="progress ">
       
  <div class="progress-bar progress-bar-success col-lg-10" role="progressbar" aria-valuenow="${ListMarks[0]}" aria-valuemin="0" aria-valuemax="100" style="width:${ListMarks[0]}%;background-color: darkgreen">
    
  </div>
</div>
        </div>
   <div class="col-lg-1"><span class="label" style="color: black;"> ${ListMarks[0]} %</span></div>
    </div>
    <div class="row">
        <div class="col-lg-1"><span class="label" style="color: black;">Chvalitebně 2</span></div>
        <div class="col-lg-10">
   <div class="progress ">
       
  <div class="progress-bar  col-lg-10" role="progressbar" aria-valuenow="${ListMarks[1]}" aria-valuemin="0" aria-valuemax="100" style="width: ${ListMarks[1]}%; background-color: forestgreen">
    
  </div>
</div>
        </div>
   <div class="col-lg-1"><span class="label" style="color: black;"> ${ListMarks[1]} %</span></div>
    </div>
 <div class="row">
     <div class="col-lg-1" ><span class="label" style="color: black;">Dobře 3</span></div>
        <div class="col-lg-10">
   <div class="progress ">
       
  <div class="progress-bar progress-bar-info col-lg-10" role="progressbar" aria-valuenow="${ListMarks[2]}" aria-valuemin="0" aria-valuemax="100" style="width: ${ListMarks[2]}%;background-color: limegreen">
    
  </div>
</div>
        </div>
   <div class="col-lg-1"><span class="label" style="color: black;"> ${ListMarks[2]} %</span></div>
    </div>
    <div class="row">
        <div class="col-lg-1"><span class="label" style="color: black;">Dostatečně 4</span></div>
        <div class="col-lg-10">
   <div class="progress ">
       
  <div class="progress-bar progress-bar-warning col-lg-10" role="progressbar" aria-valuenow="${ListMarks[3]}" aria-valuemin="0" aria-valuemax="100" style="width:${ListMarks[3]}%;background-color: lightgreen">
    
  </div>
</div>
        </div>
   <div class="col-lg-1"><span class="label" style="color: black;"> ${ListMarks[3]} %</span></div>
    </div>
    <div class="row">
        <div class="col-lg-1"><span class="label" style="color: black;">Nedostatečně 5</span></div>
        <div class="col-lg-10">
   <div class="progress ">
       
  <div class="progress-bar progress-bar-danger col-lg-10" role="progressbar" aria-valuenow="${ListMarks[4]}" aria-valuemin="0" aria-valuemax="100" style="width: ${ListMarks[4]}%">
    
  </div>
</div>
        </div>
    
   <div class="col-lg-1"><span class="label" style="color: black;"> ${ListMarks[4]} %</span></div>
    </div>
    </c:if>
</div>

     
<div class="row">
    <h3><span class="label label-default" style="background-color: steelblue">Průměrná známka v jednotlivých třídách</span></h3>
</div>
<div class="row">
    <p class="text text-info"><strong>Celkový počet tříd: ${clazzAVG.size()}</strong></p>
    <c:forEach items="${clazzAVG}"  var="item" varStatus="iter">
       
    <div class="row">
        <div class="col-lg-1"><span class="label" style="color: black;">${item ['key'].nameNumber}.${item ['key'].name}</span></div>
        <div class="col-lg-10">
   <div class="progress ">
       
  <div class="progress-bar  col-lg-10" role="progressbar" aria-valuenow="${item ['value']}" aria-valuemin="0" aria-valuemax="100" style="width:${item ['value']*20}%; 
       <c:if test="${iter.index%2==0}">background-color:darkgreen</c:if>">
    
  </div>
</div>
        </div>
   <div class="col-lg-1"><span class="label" style="color: black;"> ${item ['value']} </span></div>
    </div>
    </c:forEach>

    </div>
 

   

</div>   
    <div class="row">


</div>
<hr>





<%@include file="../../footer.jspf" %>