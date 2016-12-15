/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package myappointments.model;

import myappointments.model.domain.Appointment;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import myappointments.model.DB;
import myappointments.util.DateUtils;

/**
 *
 * @author leonardo
 */
public class AgendaDAO extends AbstractAgendaDAO  {

    private static AgendaDAO agendaDAO = new AgendaDAO();
    
    private AgendaDAO() {
        super();
    }    
    
    public static AgendaDAO getInstance() {
        return agendaDAO ;
    }
    
    public void addAppointment(Appointment appointment) throws Exception {
        if (appointment.getDate().compareTo
                (DateUtils.getCurrentDate()) < 0)
            throw new IllegalArgumentException
                    ("Date field cannot be prior to current date and time!") ;        
        
        String update = 
                "INSERT INTO APPOINTMENT (TITLE, NOTE, TIME) " +
                "VALUES (?, ?, ?)" ;
        
        PreparedStatement stmt = DB.getConnection().prepareStatement(update) ;
        stmt.setString(1, appointment.getTitle()) ;
        stmt.setString(2, appointment.getNote()) ;
        stmt.setTimestamp(3, new Timestamp(appointment.getDate().getTime())) ;
        stmt.executeUpdate();        
        stmt.close() ;                       
        
        setChanged() ;
        notifyObservers(new DAOCommand(DAOCommand.Type.INSERT, appointment));
    }

    public void saveAppointment
            (Appointment oldAppointment, Appointment newAppointment) throws Exception {
        
        if (oldAppointment.getDate().compareTo
                (DateUtils.getCurrentDate()) < 0 ||
            newAppointment.getDate().compareTo
                (DateUtils.getCurrentDate()) < 0)
            throw new IllegalArgumentException
                    ("Cannot save appointment whose time has pasted!") ;                
        
        String update = 
                "UPDATE APPOINTMENT SET " +
                "TITLE = ?, " +
                "NOTE = ?, " +
                "TIME = ? " +
                "WHERE TIME = ?" ;
        
        PreparedStatement stmt = DB.getConnection().prepareStatement(update) ;
        stmt.setString(1, newAppointment.getTitle());
        stmt.setString(2, newAppointment.getNote());        
        stmt.setTimestamp(3, new Timestamp(newAppointment.getDate().getTime())) ;
        stmt.setTimestamp(4, new Timestamp(oldAppointment.getDate().getTime())) ;        
        stmt.execute(); 
        stmt.close();
        
        setChanged();
        Appointment[] pair = {oldAppointment, newAppointment} ;                
        notifyObservers(new DAOCommand(DAOCommand.Type.UPDATE, pair));
    }
    
    public Appointment getAppointment(Date date) throws Exception {
        String query = 
                "SELECT TITLE, NOTE, TIME " +
                "FROM APPOINTMENT " +
                "WHERE TIME = ?"  ;
        
        PreparedStatement stmt = DB.getConnection().prepareStatement(query) ;
        stmt.setTimestamp(1, new Timestamp(date.getTime())) ;
        
        Appointment app = null ;
        
        ResultSet result = stmt.executeQuery() ;
        if (result.next()) {
            app = new Appointment() ;
            app.setTitle(result.getString(1));
            app.setNote(result.getString(2));
            app.setDate(new Date(result.getTimestamp(3).getTime()));            
        }
        
        stmt.close(); 
        
        return app ;                
    }
    
    public void removeAppointment(Date date) throws Exception {       
        Appointment appointment = getAppointment(date) ;
        
        if (appointment.getDate().compareTo
                (DateUtils.getCurrentDate()) < 0)
            throw new IllegalArgumentException
                    ("Cannot remove a past appointment!") ;        
        
        
        String udpate = 
                "DELETE FROM APPOINTMENT WHERE TIME = ?" ;
        PreparedStatement stmt = DB.getConnection().prepareStatement(udpate) ;
        stmt.setTimestamp(1, new Timestamp(date.getTime())) ;
        stmt.executeUpdate(); 
        stmt.close() ;
        
        setChanged() ;
        notifyObservers(new DAOCommand(DAOCommand.Type.REMOVE, appointment));
    }

    @Override
    public List<Appointment> getAppointments(int day, int month, int year) throws Exception {
        
        String query = 
                "SELECT TITLE, NOTE, TIME " +
                "FROM APPOINTMENT WHERE " +
                "DAYOFMONTH(TIME) = ? AND " +
                "MONTH(TIME) = ? AND " +
                "YEAR(TIME) = ? " +
                "ORDER BY TIME";
        
        PreparedStatement stmt = DB.getConnection().prepareStatement(query);
        stmt.setInt(1, day);
        stmt.setInt(2, month);
        stmt.setInt(3, year);

        List<Appointment> appointments = new LinkedList<Appointment>();
        ResultSet result = stmt.executeQuery();        
        
        while (result.next()) {
            Appointment app = new Appointment() ;
            app.setTitle(result.getString(1)) ;
            app.setNote(result.getString(2));
            
            Timestamp time = result.getTimestamp(3) ;
            app.setDate(new Date(time.getTime())) ;
            
            appointments.add(app) ;
        }

        stmt.close() ;        
        return appointments;
    }
}
 