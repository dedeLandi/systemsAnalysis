/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package myappointments.controller;

import myappointments.model.AbstractAgendaDAO;
import myappointments.model.AgendaDAO;
import myappointments.model.domain.Appointment;
import myappointments.util.DateUtils;
import myappointments.view.AbstractAppointmentView;
import myappointments.view.AppointmentView;

/**
     *
     * @author leonardo
 */
public abstract class AppointmentController implements IAppointmentController {
    
    public AppointmentController() {
        this.appointmentView = new AppointmentView(this) ;        
        this.agendaDAO = AgendaDAO.getInstance() ;
    }

    abstract public void start() ;


    public Appointment getAppointment() {
        Appointment app = null ;
        
        try {
            app = new Appointment() ;
            app.setTitle(getView().getTitleField()) ;
            app.setNote(getView().getNoteField()) ;

            String dateStr = 
                    getView().getDateField() + " " + 
                    getView().getHoursField() + ":" + 
                    getView().getMinutesField() ;

            app.setDate(DateUtils.fromString(dateStr, DateUtils.LONG_DATE_FMT)) ;        
        } catch(Exception e) {
            e.printStackTrace(); 
        }
        return app ;
    }

    public AbstractAppointmentView getView() {
        return appointmentView ;
    }            
    
    public void cancel() {
        appointmentView.close() ;
    }
    
    protected AbstractAgendaDAO getAgendaDAO() {
        return agendaDAO ;
    }
    
    protected AbstractAppointmentView appointmentView ;        
    protected AbstractAgendaDAO agendaDAO ;   
}
