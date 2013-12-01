/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

//document.onload=new function (){
//    loadForm();
//};

            function loadForm() {
                callForm();
            }
            ;

            function callForm() {
                $.get("addTestLoad.htm", function(data) {
                    $("#form").empty();   
                    
                    $("#form").append(data);
                });
            }
            ;

            function addQuestion() {
                $.post("addTest.htm", $("#testAddForm").serialize());
                $(document).ajaxStop(function() {
                    $.get("addQuestion.htm", function(data) {
                        $("#form").empty();
                        $("#form").append(data);
                    });
                    $(this).unbind('ajaxStop');
                });
            }
            ;

            function delQuestion(idx) {
                //alert("deleting "+idx)
                $(document).ajaxStop(function() {
                    $.post("addTest.htm", $("#testAddForm").serialize());
                    $(this).unbind('ajaxStop');
                });
                
                $.get("addQuestionDelete.htm?idx=" + idx, function(data) {
                    $("#form").empty();
                    $("#form").append(data);
                });

                
            }
            ;

            function addAnswerToQuestion(idxQ) {
                $.post("addTest.htm", $("#testAddForm").serialize());
                $(document).ajaxStop(function() {
                    $.get("addAnswer.htm?idxQ=" + idxQ, function(data) {
                        $("#form").empty();
                        $("#form").append(data);
                    });
                    $(this).unbind('ajaxStop');
                });
            }
            ;
            
            function delAnswerInQuestion(idxQ,idxA){
                $(document).ajaxStop(function() {
                    $.post("addTest.htm", $("#testAddForm").serialize());
                    $(this).unbind('ajaxStop');
                });
                
                $.get("addAnswerDelete.htm?idxQ="+idxQ+"&idxA="+idxA, function(data) {
                    $("#form").empty();
                    $("#form").append(data);
                });
               
            };
            
            function saveTest()
            {
                $.post("addTest.htm", $("#testAddForm").serialize());
                $(document).ajaxStop(function() {
                    $.get("saveTest.htm", function(data) {
//                        alert(data);
                        $("#form").empty();
                        $("#form").append(data);
                    });
                    $(this).unbind('ajaxStop');
                });
            };
            /**/
            $(document).ready(loadForm);
        