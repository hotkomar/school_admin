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
                $.get("editTestLoad.htm", function(data) {
                    $("#form").empty();   
                    
                    $("#form").append(data);
                });
            }
            ;

            function addQuestion() {
                $.post("editTest.htm", $("#testEditForm").serialize());
                $(document).ajaxStop(function() {
                    $.get("editQuestion.htm", function(data) {
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
                    $.post("editTest.htm", $("#testEditForm").serialize());
                    $(this).unbind('ajaxStop');
                });
                
                $.get("editQuestionDelete.htm?idx=" + idx, function(data) {
                    $("#form").empty();
                    $("#form").append(data);
                });

                
            }
            ;

            function addAnswerToQuestion(idxQ) {
                $.post("editTest.htm", $("#testEditForm").serialize());
                $(document).ajaxStop(function() {
                    $.get("editAnswer.htm?idxQ=" + idxQ, function(data) {
                        $("#form").empty();
                        $("#form").append(data);
                    });
                    $(this).unbind('ajaxStop');
                });
            }
            ;
            
            function delAnswerInQuestion(idxQ,idxA){
                $(document).ajaxStop(function() {
                    $.post("editTest.htm", $("#testEditForm").serialize());
                    $(this).unbind('ajaxStop');
                });
                
                $.get("editAnswerDelete.htm?idxQ="+idxQ+"&idxA="+idxA, function(data) {
                    $("#form").empty();
                    $("#form").append(data);
                });
               
            };
            
            function saveTest()
            {
                $.post("editTest.htm", $("#testEditForm").serialize());
                $(document).ajaxStop(function() {
                    $.get("saveEditTest.htm", function(data) {
//                        alert(data);
                        $("#form").empty();
                        $("#form").append(data);
                    });
                    $(this).unbind('ajaxStop');
                });
            };
            /**/
            $(document).ready(loadForm);
        