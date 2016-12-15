/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package myappointments.controller;

import myappointments.model.domain.Appointment;
import myappointments.util.DateUtils;


/**
 *
 * @author leonardo
 */
public class EditAppointmentController extends AppointmentController  {
    
    public EditAppointmentController(Appointment oldAppointment) {
        super() ;
        this.oldAppointment = oldAppointment ;
    }

    @Override
    public void start() {
        getView().setTitleField(oldAppointment.getTitle());
        getView().setNoteField(oldAppointment.getNote());
        getView().setDateField
                (DateUtils.toString(oldAppointment.getDate(), 
                DateUtils.SHORT_DATE_FMT));
        
        int hour = DateUtils.getHour(oldAppointment.getDate()) ;
        if (hour < 10)
            getView().setHoursField("0" + hour);
        else
            getView().setHoursField("" + hour);
        
        int minutes = DateUtils.getMinutes(oldAppointment.getDate()) ;
        if (minutes < 10)
            getView().setMinutesField("0" + minutes);
        else
            getView().setMinutesField("" + minutes);                
        
        getView().display(); 
    }
    
    

    @Override
    public void save() throws Exception {        
       Appointment newAppointment = getAppointment() ; 
       getAgendaDAO().saveAppointment(oldAppointment, newAppointment);            
       getView().displaySavingConfirmation() ;
       getView().close();        
    }
    
    private Appointment oldAppointment ;

}
