/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.hotkomar.service.checkAndMake;

import cz.cvut.hotkomar.form.student.ViewAnswerForm;
import cz.cvut.hotkomar.form.student.ViewQuestionForm;
import cz.cvut.hotkomar.form.student.ViewTestForm;
import cz.cvut.hotkomar.model.entity.Answer;
import cz.cvut.hotkomar.model.entity.Test;
import cz.cvut.hotkomar.model.entity.TestResult;
import cz.cvut.hotkomar.service.manager.AnswerMan;
import cz.cvut.hotkomar.service.manager.TestMan;
import java.util.ArrayList;
import java.util.Calendar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Maru
 */
@Service
public class TestResultCheck {
    
    
    private AnswerMan answerMan;
    private TestMan testMan;
   

    
    @Autowired
    public void setTestMan(TestMan testMan) {
        this.testMan = testMan;
    }
    
    
@Autowired
    public void setAnswerMan(AnswerMan answerMan) {
        this.answerMan = answerMan;
    }
   

    
    public TestResult checkTest ( ViewTestForm form, TestResult testResult)
    {
         double sumPoints =0.0;
         double count =0.0;
        System.out.println("test");
        for(ViewQuestionForm q : form.getQuestions())
        {
            //přičtu body do celkového počtu bodů
            count+=q.getPoints();
           // boolean correctAnswer =true;
            ArrayList<Boolean> correctAnswer = new ArrayList<Boolean>();
            System.out.println(q.getId()+" otázka "+q.getQuestion()+" počet bodů"+q.getPoints());
            System.out.println("délka je "+correctAnswer.size());
            for(ViewAnswerForm a: q.getAnswers())
            {
                Answer findById = answerMan.findById(a.getId());
                if(findById==null)
                {
                    
                    return null;
                }
                if(a.getCorrect()!=findById.getCorrect())
                {
                    correctAnswer.add(Boolean.FALSE);
                }
                
            }
            System.out.println("correcrt "+correctAnswer.size());
            if(correctAnswer.size()==0)
            {
                sumPoints+=q.getPoints();
            }
        }
        System.out.println("celkový počet bodů je "+sumPoints+" z "+count);
        testResult = chooseMark(form.getIdTest(), sumPoints, count,testResult);
        return testResult;
        
    }
    private TestResult chooseMark(Long testId, double points,double count, TestResult test)
    {
        double onePerecent = count/100.0;
        double countPercent = points/onePerecent;
       short mark= Short.MIN_VALUE;
        Test findById = testMan.findById(testId);
        if(findById==null)
        {
            return null;
        }
        
        
        
        test.setPercent(countPercent);
        System.out.println("5 je "+findById.getFive()+" %");
        System.out.println("4 je "+findById.getFour()+" %");
        System.out.println("3 je "+findById.getThree()+" %");
        System.out.println("2 je "+findById.getTwo()+" %");
        if(countPercent<findById.getFive())
        {
            
            mark=5;
        }
        else
        {
            if(countPercent<=findById.getFour()){
            
                
                mark=4;
            }
            else{
                if(countPercent<=findById.getThree())
                {
                    mark=3;
                }
                else{
                    if(countPercent<=findById.getTwo())
                    {
                        mark=2;
                    }
                    else{
                        mark=1;
                    }
                }
            }
        }
        System.out.println("známka je"+mark);
        test.setMark(mark);
        return test;
    }
    
   
}
