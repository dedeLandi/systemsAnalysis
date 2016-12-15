/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package myappointments.view;


/**
 *
 * @author leonardo
 */
public abstract class AbstractAgendaView extends View {

    public AbstractAgendaView() {
        super() ;
    }
    
    public AbstractAgendaView(View parent) {
        super(parent) ;
    }
    abstract public int getSelectedAppointmentRowIndex() ;
    abstract public boolean confirmRemoval() ;       
    abstract public void insertAppointRow(int index, String hour, String title) ;
    abstract public String[] getAppointmentRow(int row) ;
    abstract public void removeAppointmentRow(int row) ;
    abstract public void displayErrorOnLoadingAppointments(Exception e) ;   
    abstract public void displayNoSelectedAppointment() ;
}
