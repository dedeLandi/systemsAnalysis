/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package myappointments.view;

/**
 *
 * @author leonardo
 */
public abstract class View {
    
    public View() { this(null) ; }
    
    public View(View  parent) {
        this.parent = parent ;
    }    
    
    public View getParent() {
        return parent ;
    }
    
    public void setParent(View parent) {
        this.parent = parent ;
    }
    
    abstract public void display() ;
    
    private View parent ;
}
