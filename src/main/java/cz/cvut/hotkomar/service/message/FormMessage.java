/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.hotkomar.service.message;

import java.io.Serializable;
import java.util.Stack;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

/**
 *
 * @Marie Hoťková
 */
@Service
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class FormMessage implements Serializable {

    private Stack<String> positiveMes = new Stack<String>();
    private Stack<String> negativeMes = new Stack<String>();

    /**
     * return positive message (FormMessage) about action for user
     *
     * @return
     */
    public String getPositiveMes() {
        String message = "";
        while (!positiveMes.isEmpty()) {
            message += positiveMes.pop() + "<br />";
        }
        return message;
    }

    /**
     * return negative message (FormMessage) about action for user
     *
     * @param message
     */
    public void setPositiveMes(String message) {
        positiveMes.push(message);
    }

    /**
     * return true, when possitveMes is not empty
     *
     * @return
     */
    public boolean isPositiveFull() {
        return !positiveMes.isEmpty();
    }

    /**
     * return negative message (FormMessage) about action for user
     *
     * @return
     */
    public String getNegativeMes() {
        String message = "";
        while (!negativeMes.isEmpty()) {
            message += negativeMes.pop() + "<br />";
        }
        return message;
    }

    /**
     * add message to negativeMes
     *
     * @param message
     */
    public void setNegativeMes(String message) {
        negativeMes.push(message);
    }

    /**
     * return true, when negativeMes is not empty
     *
     * @return
     */
    public boolean isNegativeFull() {
        return !negativeMes.isEmpty();
    }
}
