/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


package myappointments.view;


/**
 *
 * @author leonardo
 */
public abstract class AbstractAppointmentView extends View {

    public AbstractAppointmentView() {
        super() ;
    }
    
    public AbstractAppointmentView(View parent) {
        super(parent) ;
    }
      
    abstract public void setTitleField(String title) ;
    abstract public void setNoteField(String note) ;
    abstract public void setDateField(String date) ;
    abstract public void setMinutesField(String minutes) ;
    abstract public void setHoursField(String hours) ;
    
    abstract public String getTitleField() ;
    abstract public String getNoteField() ;
    abstract public String getDateField() ;
    abstract public String getMinutesField() ;
    abstract public String getHoursField() ;
    
    abstract public void display() ;
    abstract public void displaySavingConfirmation() ;
    abstract public void close() ;
}
