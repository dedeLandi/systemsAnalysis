/*
 * SimpleAgendaUI.java
 *
 * Created on 3 de Setembro de 2008, 13:47
 */

package myappointments.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import myappointments.controller.AgendaController;
import myappointments.controller.IAgendaController;
import myappointments.util.DateUtils;


public class AgendaView extends AbstractAgendaView {
    public AgendaView(AgendaController controller) {
        this.agendaForm = new AgendaForm() ;
        this.controller = controller ;
        initComponents();
    }
    
    private void initComponents() {                        

        agendaForm.addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                new Thread(new Runnable() {                    
                    public void run() {
                        controller.addAppointment() ;
                    }
                }).start() ;
            }
        });

        agendaForm.editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                new Thread(new Runnable() {
                    public void run() {
                        controller.editAppointment() ;
                    }
                }).start() ;                    
            }
        });

        agendaForm.removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {                        
                    new Thread(new Runnable() {
                        public void run() {
                            try {
                                controller.removeAppointment() ;
                            } catch(Exception e) {
                                displayErrorOnRemove(e) ;
                                e.printStackTrace() ;
                            }
                        }
                    }).start() ;                                                                    
                }
        });               

        agendaForm.currentDateLabel.setText
                ("Appointments on " + DateUtils.toString
                (DateUtils.getCurrentDate(), DateUtils.SHORT_DATE_FMT)) ;    

        agendaForm.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent arg0) {
                controller.endExecution() ;
            }
        }) ;            
    }

    @Override
    public void display() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                agendaForm.setVisible(true) ;
            }
        });
    }

    @Override
    public void insertAppointRow(final int index, String hour, String title) {            
        final Object[] row = {hour, title} ;            

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {     
                DefaultTableModel model = 
                        (DefaultTableModel) agendaForm.appointmentsTable.getModel() ;
                if (index == model.getRowCount())
                    model.addRow(row) ;
                else
                    model.insertRow(index, row);            
            }
        }) ;

    }

    public int getSelectedAppointmentRowIndex() {
        int vRow = agendaForm.appointmentsTable.getSelectedRow() ;

        if (vRow == -1)
            return -1 ;

        return agendaForm.appointmentsTable.convertRowIndexToModel(vRow) ;            
    }

    public boolean confirmRemoval() {                  
        confirmationResult = JOptionPane.NO_OPTION ;

        try {
            SwingUtilities.invokeAndWait(new Runnable() {                
                public void run() {
                    confirmationResult = JOptionPane.showConfirmDialog
                            (agendaForm, 
                            "Remove appointment?", 
                            "Alert", 
                            JOptionPane.YES_NO_OPTION);
                }
            }) ;

        } catch(final Exception e) {                                                         
            e.printStackTrace() ;
        }

        return (confirmationResult == JOptionPane.YES_OPTION) ? true : false ;            
    }

    public void removeAppointmentRow(final int row) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ((DefaultTableModel) agendaForm.appointmentsTable.getModel()).removeRow(row) ;
            }
        });
    }

    public String[] getAppointmentRow(int row) {
        String[] content = new String[2] ;

        content[0] = ((DefaultTableModel) agendaForm.appointmentsTable.getModel()).getValueAt(row, 0).toString() ;
        content[1] = ((DefaultTableModel) agendaForm.appointmentsTable.getModel()).getValueAt(row, 0).toString() ;            

        return content ;
    }

    public void displayErrorOnLoadingAppointments(final Exception e) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JOptionPane.showMessageDialog
                        (agendaForm, 
                        "Could not load agenda: " + e.getMessage(), 
                        "Exception", 
                        JOptionPane.ERROR_MESSAGE) ;
            }
        }) ; 
    }

    public void displayNoSelectedAppointment() {
       SwingUtilities.invokeLater(new Runnable() {
        public void run() {
            JOptionPane.showMessageDialog
                    (agendaForm, 
                    "Cannot remove non-selected appointment!") ;
            }
        }) ;            
    }

    public void displayErrorOnRemove(final Exception e) {
       SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JOptionPane.showMessageDialog
                        (agendaForm, 
                        "Error on removing apppointment: " + e.getMessage(), 
                        "Exception", 
                        JOptionPane.ERROR_MESSAGE) ;
            }
        }) ;             
    }

    private IAgendaController controller ;            
    private AgendaForm agendaForm ;
    private int confirmationResult ;
}
/**
 *
 * @author  leonardo
 */
class AgendaForm extends javax.swing.JFrame {              
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** Creates new form SimpleAgendaUI */
    public AgendaForm() {
        initComponents();
    }       

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        appointmentsTable = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        currentDateLabel = new javax.swing.JLabel();
        addButton = new javax.swing.JButton();
        editButton = new javax.swing.JButton();
        removeButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("myAppointments");

        appointmentsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Time", "Title"
            }
        ));
        appointmentsTable.setFillsViewportHeight(true);
        jScrollPane1.setViewportView(appointmentsTable);

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(currentDateLabel)
                .addContainerGap(396, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addComponent(currentDateLabel)
                .addContainerGap())
        );

        addButton.setText("Add");

        editButton.setText("Edit");

        removeButton.setText("Remove");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 412, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(188, Short.MAX_VALUE)
                .addComponent(addButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(editButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(removeButton)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(removeButton)
                    .addComponent(editButton)
                    .addComponent(addButton))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    javax.swing.JButton addButton;
    javax.swing.JTable appointmentsTable;
    javax.swing.JLabel currentDateLabel;
    javax.swing.JButton editButton;
    javax.swing.JPanel jPanel1;
    javax.swing.JScrollPane jScrollPane1;
    javax.swing.JButton removeButton;
    // End of variables declaration//GEN-END:variables
}


