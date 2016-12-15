/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package myappointments.controller;

import java.util.Date;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import myappointments.model.AgendaDAO;
import myappointments.view.AbstractAgendaView;
import myappointments.model.AbstractAgendaDAO;
import myappointments.model.domain.Appointment;
import myappointments.model.DAOCommand;
import myappointments.model.DB;
import myappointments.util.DateUtils;
import myappointments.view.AgendaView;

/**
 *
 * @author leonardo
 */
public class AgendaController implements IAgendaController {
    
    public AgendaController() {        
        this.agendaView = new AgendaView(this) ;                
        this.agendaDAO = AgendaDAO.getInstance() ;        
        this.agendaDAO.addObserver(new Observer() {
            public void update(Observable o, Object arg) {
                try {
                    DAOCommand cmd = (DAOCommand) arg ;
                    updateAppointmentListing(cmd) ;
                } catch(Exception e) {
                    e.printStackTrace() ;
                    // TODO.
                }
            }
        });
    }

    public void start() {
         try {
            loadAppointments() ;
        } catch(Exception e) {                
            agendaView.displayErrorOnLoadingAppointments(e) ;
        }        
        
        agendaView.display(); 
    }    
        
    private void updateAppointmentListing(DAOCommand cmd) throws Exception {

        List<Appointment> apps = null ;        
        
        switch(cmd.getType()) {
            case INSERT:
                Appointment app = (Appointment) cmd.getValue() ;
                apps = agendaDAO.getAppointments
                            (DateUtils.getCurrentDay(), 
                            DateUtils.getCurrentMonth(), 
                            DateUtils.getCurrentYear()) ;

                if (! DateUtils.isToday(app.getDate()))
                    return ;                
                
                agendaView.insertAppointRow
                            (apps.indexOf(app), 
                            DateUtils.toString(app.getDate(), DateUtils.HOUR_FMT),
                            app.getTitle()) ;                        
                break ;
            
            case UPDATE:
                Appointment newApp = ((Appointment[]) cmd.getValue())[1] ;
                
                agendaView.removeAppointmentRow
                            (agendaView.getSelectedAppointmentRowIndex());                
                
                if (! DateUtils.isToday(newApp.getDate()))
                    return ;
                                
                apps = agendaDAO.getAppointments
                            (DateUtils.getCurrentDay(), 
                            DateUtils.getCurrentMonth(), 
                            DateUtils.getCurrentYear()) ;                
                
                
                agendaView.insertAppointRow
                    (apps.indexOf(newApp), 
                    DateUtils.toString(newApp.getDate(), DateUtils.HOUR_FMT), newApp.getTitle());                    
                
                break   ;
        }                        
    }    
    
    public void loadAppointments() throws Exception {
        List<Appointment> apps = agendaDAO.getAppointments
                (DateUtils.getCurrentDay(), DateUtils.getCurrentMonth(), DateUtils.getCurrentYear()) ;
        
        int i = 0 ;
        for(Appointment app : apps) {
            agendaView.insertAppointRow
                    (i, DateUtils.toString(app.getDate(), 
                    DateUtils.HOUR_FMT), app.getTitle());
            i++ ;
        }
    }
    
    public void addAppointment() {                
        new AddAppointmentController().start();                
    }  
    
    public void editAppointment() {
        try {
            int row = agendaView.getSelectedAppointmentRowIndex() ;

            if (row == -1) 
                agendaView.displayNoSelectedAppointment() ;        

            else {
                Appointment app = agendaDAO.getAppointment(getAppointmentRowAsDate(row)) ;
                new EditAppointmentController(app).start() ;
            }
        } catch(Exception e) {
            e.printStackTrace() ;
        }
    }
    
    private Date getAppointmentRowAsDate(int row) {
        String[] appHour = agendaView.getAppointmentRow(row)[0].split(":") ;
        return DateUtils.newDate
            (DateUtils.getCurrentDay(),
             DateUtils.getCurrentMonth(),
             DateUtils.getCurrentYear(),
             Integer.parseInt(appHour[0]), Integer.parseInt(appHour[1])) ;        
    }
    
    public void removeAppointment() throws Exception {   
        int row = agendaView.getSelectedAppointmentRowIndex() ;

        if (row == -1) 
            agendaView.displayNoSelectedAppointment() ;

        else if (agendaView.confirmRemoval())  {
            agendaDAO.removeAppointment(getAppointmentRowAsDate(row));  
            agendaView.removeAppointmentRow(row) ;
        }
    }
    
    public AbstractAgendaView getView() {
        return agendaView ;
    }    

    public void endExecution() {
        
        System.out.println("Shutting down...") ;
        
        try { DB.shutdown(); } 
        catch(Exception e) {
            e.printStackTrace() ;
        }
    }

    public static void main(String[] args) {
        new AgendaController().start() ;
    }
    
    private AbstractAgendaView agendaView ;
    private AbstractAgendaDAO agendaDAO ;  
}
