/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package myappointments.util;

/**
 *
 * @author leonardo
 */
public class StringUtils {
    public static boolean isEmpty(String s) {
        if (s == null || s.trim().length() == 0)
            return true ;
        
        return false ;
    }    
}
