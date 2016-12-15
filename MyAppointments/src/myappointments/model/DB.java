/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package myappointments.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author leonardo
 */
final public class DB {
    
    private static final String DB_LOCATION = System.getProperty("user.home") ;
    
    private static final String DB_NAME = "agendadb" ;
    private static final String DB_USER = "sa" ;
    private static final String DB_PASSWD = "" ;
    private static Connection connection ;
        
    static {    
        boolean dbExits = true ;
        
        if (loadDriver()) {
            try { connectToDB() ; }
            catch (SQLException sqle) {                
                dbExits = false ;
            }
            
            if (! dbExits) {           
                try { connectToNewDB() ; createSchema(); }
                catch (SQLException sqle) {
                sqle.printStackTrace(); 
                }            
            }            
       }        
    }

    private static boolean loadDriver() {
        try { Class.forName("org.hsqldb.jdbcDriver"); } 
        catch(Exception e) { e.printStackTrace(); return false ; }                        
        return true ;
    }
    
    
    private static void connectToDB() throws SQLException {                                
        connection =  DriverManager.getConnection("jdbc:hsqldb:file:"
                       + DB_LOCATION + "/" + DB_NAME + ";ifexists=true",
                       DB_USER,   
                       DB_PASSWD); 
    }
    
    private static void connectToNewDB() throws SQLException {               
        connection =  DriverManager.getConnection("jdbc:hsqldb:file:"
                       + DB_LOCATION + "/" + DB_NAME ,
                       DB_USER,   
                       DB_PASSWD);
    }
    
    private static void createSchema() throws SQLException {
        Statement statement = connection.createStatement() ;
        statement.executeUpdate(
            "CREATE TABLE APPOINTMENT (" +
                "TITLE VARCHAR(10) NOT NULL," +
                "NOTE VARCHAR(100) NOT NULL," +
                "TIME TIMESTAMP NOT NULL," +
                "CONSTRAINT PK_APPOINTMENT PRIMARY KEY(TIME)" +
             ")") ;
        statement.close();         
    }                
    
    public static void flush() throws SQLException {
        Statement stmt = connection.createStatement() ;
        stmt.execute("CHECKPOINT") ;
        stmt.close() ;
    }
    
    public static Connection getConnection() {
        return connection ;
    }    
    
    public static void shutdown() throws SQLException {
        Statement stmt = connection.createStatement() ;
        stmt.execute("SHUTDOWN") ;
        stmt.close() ;
    }    
} 
