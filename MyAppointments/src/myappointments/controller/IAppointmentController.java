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
public interface IAppointmentController extends IController {

    void cancel();

    Appointment getAppointment();

    void save() throws Exception;

}
