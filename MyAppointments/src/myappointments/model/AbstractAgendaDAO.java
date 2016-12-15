/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


package myappointments.model;

import myappointments.model.domain.Appointment;
import java.util.Date;
import java.util.List;
import java.util.Observable;

/**
 *
 * @author leonardo
 */
public abstract class AbstractAgendaDAO extends Observable { 
    public AbstractAgendaDAO() {
        super() ;
    }
    
    abstract public void addAppointment(Appointment appointment) 
            throws Exception ; 
    abstract public void removeAppointment(Date date) 
            throws Exception;      
    abstract public Appointment getAppointment(Date date) 
            throws Exception ;
    abstract public List<Appointment> getAppointments(int day, int month, int year) 
            throws Exception;                    
    abstract public void saveAppointment(Appointment oldAppointment, Appointment newAppointment) 
            throws Exception ;
}
