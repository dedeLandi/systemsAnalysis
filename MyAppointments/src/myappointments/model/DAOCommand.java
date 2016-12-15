/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package myappointments.model;

/**
 *
 * @author leonardo
 */
public class DAOCommand {
    
    public static enum Type {INSERT, UPDATE, REMOVE}
        
    public DAOCommand(Type command, Object value) {
        this.command = command ;
        this.value = value ;
    }
               
    public Type getType() {
        return command ;
    }    
    
    public Object getValue() {
        return value ;
    }
    
    private Type command ;
    private Object value ;
}

