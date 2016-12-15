/*
 * SimpleAgenda2.java
 *
 * Created on 3 de Setembro de 2008, 13:24
 */

package myappointments.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.Runnable;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import myappointments.controller.AppointmentController;
import myappointments.controller.IAppointmentController;

public class AppointmentView extends AbstractAppointmentView {
    public AppointmentView(AppointmentController controller) {
        this.controller = controller ;
        this.appForm = new AppointmentForm() ;
        initComponents() ;
    }

    private void initComponents() {
        appForm.saveButton.addActionListener(new ActionListener() {            
            public void actionPerformed(ActionEvent evt) {
                new Thread(new Runnable() {
                    public void run() {
                        try { 
                            controller.save() ; 
                        } 
                        catch(Exception e) {
                            displayErrorOnSaving(e) ;
                            e.printStackTrace() ;
                        }
                   }
                }).start() ;
            }
        });

        appForm.cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                close() ;
            }
        });
    }

    @Override
    public String getDateField() {
        return appForm.dateField.getText() ;
    }

    @Override
    public String getHoursField() {
        return appForm.hourCombo.getSelectedItem().toString() ;
    }

    @Override
    public String getMinutesField() {
        return appForm.minutesCombo.getSelectedItem().toString() ;
    }

    @Override
    public String getNoteField() {
        return appForm.noteField.getText() ;
    }

    @Override
    public String getTitleField() {
        return appForm.titleField.getText() ;
    }

    @Override
    public void setDateField(final String date) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                appForm.dateField.setText(date);
            }
        }) ;                
    }

    @Override
    public void setHoursField(final String hours) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                appForm.hourCombo.setSelectedItem(hours);
            }
        }) ;        
    }

    @Override
    public void setMinutesField(final String minutes) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                appForm.minutesCombo.setSelectedItem(minutes);
            }
        }) ;        
    }

    @Override
    public void setNoteField(final String note) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                appForm.noteField.setText(note);
            }
        }) ;                
    }

    @Override
    public void setTitleField(final String title) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                appForm.titleField.setText(title);
            }
        }) ;                
    }

    public void display() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                appForm.setVisible(true);
            }
        });            
    }

    public void displaySavingConfirmation() {
        try { 
            SwingUtilities.invokeAndWait(new Runnable() {
                public void run() {
                    JOptionPane.showMessageDialog
                    (appForm, "Appointment successfully saved!") ;
                }
            }) ;
        }
        catch (Exception e) { e.printStackTrace() ; }
    }

    public void displayErrorOnSaving(final Exception e) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JOptionPane.showMessageDialog
                (appForm, 
                "Cannot save appointment: " + e.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE) ; 
                e.printStackTrace() ;
            }
        }) ;            
    }        

    public void close() {
        Runnable runnable = new Runnable() {
            public void run() {
                appForm.setVisible(false);
                appForm.dispose(); 
            }
        };

        if (SwingUtilities.isEventDispatchThread())
            runnable.run() ;
        else 
            SwingUtilities.invokeLater(runnable);

    }


    private IAppointmentController controller ;
    private AppointmentForm appForm ;
}

/**
 *
 * @author  leonardo
 */
class AppointmentForm extends javax.swing.JFrame {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** Creates new form SimpleAgenda2 */
    public AppointmentForm() {
        initComponents();
    }        

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        titleField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        noteField = new javax.swing.JTextPane();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        String[] hours = new String[24] ;

        for(int i = 0; i < 24; i++) {
            String s = Integer.toString(i) ;
            if (i < 10)
            hours[i] = '0' + s ;
            else
            hours[i] = s ;
        }

        hourCombo = new javax.swing.JComboBox(hours) ;
        jLabel11 = new javax.swing.JLabel();
        String[] minutes = new String[60] ;

        for(int i = 0 ; i < 60; i++) {
            String s = Integer.toString(i) ;
            if (i < 10)
            minutes[i] = '0' + s ;
            else
            minutes[i] = s ;
        }

        minutesCombo = new javax.swing.JComboBox(minutes) ;
        dateField = new javax.swing.JFormattedTextField();
        saveButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Appointment");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Appointment"));

        jLabel1.setText("Title (required):");

        jLabel2.setText("Note:");

        jLabel3.setText("Date (required):");

        jScrollPane1.setViewportView(noteField);

        jLabel5.setFont(new java.awt.Font("Dialog", 0, 10));
        jLabel5.setText("(Example: Piano class)");

        jLabel6.setFont(new java.awt.Font("Dialog", 0, 10));
        jLabel6.setText("(max: 10 characters)");

        jLabel7.setFont(new java.awt.Font("Dialog", 0, 10));
        jLabel7.setText("(max: 100 characters)");

        jLabel8.setFont(new java.awt.Font("Dialog", 0, 10));
        jLabel8.setText("(Example: Must bring 100$ to pay for the next 3 classes)");

        jLabel4.setText("Time (required):");

        jLabel10.setFont(new java.awt.Font("Dialog", 0, 10));
        jLabel10.setText("(hh:mm)");

        jLabel11.setFont(new java.awt.Font("Dialog", 0, 10));
        jLabel11.setText("(mm/dd/yyyy)");

        try {
            dateField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel6)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel7)
                                .addComponent(jLabel2))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(titleField, javax.swing.GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE)
                                .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jLabel11)
                    .addComponent(jLabel10)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(hourCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(minutesCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(dateField, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(titleField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5))
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(jLabel8)
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(dateField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(hourCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(minutesCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addGap(19, 19, 19))
        );

        saveButton.setText("Save");

        cancelButton.setText("Cancel");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(315, Short.MAX_VALUE)
                .addComponent(saveButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cancelButton)
                .addGap(22, 22, 22))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(saveButton))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
         
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    javax.swing.JButton cancelButton;
    javax.swing.JFormattedTextField dateField;
    javax.swing.JComboBox hourCombo;
    javax.swing.JLabel jLabel1;
    javax.swing.JLabel jLabel10;
    javax.swing.JLabel jLabel11;
    javax.swing.JLabel jLabel2;
    javax.swing.JLabel jLabel3;
    javax.swing.JLabel jLabel4;
    javax.swing.JLabel jLabel5;
    javax.swing.JLabel jLabel6;
    javax.swing.JLabel jLabel7;
    javax.swing.JLabel jLabel8;
    javax.swing.JPanel jPanel1;
    javax.swing.JScrollPane jScrollPane1;
    javax.swing.JComboBox minutesCombo;
    javax.swing.JTextPane noteField;
    javax.swing.JButton saveButton;
    javax.swing.JTextField titleField;
    // End of variables declaration//GEN-END:variables
}


