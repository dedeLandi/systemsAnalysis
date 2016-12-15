/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package myappointments.controller;

import myappointments.view.AbstractAgendaView;

/**
 *
 * @author leonardo
 */
public interface IAgendaController extends IController {

    void addAppointment();

    void editAppointment();

    void endExecution();

    AbstractAgendaView getView();

    void loadAppointments() throws Exception;

    void removeAppointment() throws Exception;

}
