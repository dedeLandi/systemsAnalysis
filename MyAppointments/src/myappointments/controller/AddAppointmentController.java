/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package myappointments.controller;

import myappointments.model.domain.Appointment;

/**
 *
 * @author leonardo
 */
public class AddAppointmentController extends AppointmentController {
    
    public AddAppointmentController() {
        super() ;
    }

    @Override
    public void start() {
        appointmentView.display(); 
    }

    
    
    @Override
    public void save() throws Exception {
        Appointment app = getAppointment() ;                
        getAgendaDAO().addAppointment(app);
        appointmentView.displaySavingConfirmation() ;
        appointmentView.close(); 
    }        
}
