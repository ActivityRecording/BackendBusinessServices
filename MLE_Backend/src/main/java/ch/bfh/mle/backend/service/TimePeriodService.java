/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bfh.mle.backend.service;

import ch.bfh.mle.backend.model.TimePeriod;
import javax.ejb.Stateless;
import javax.inject.Named;

/**
 *
 * @author Stefan Walle
 */
@Stateless
@Named
public class TimePeriodService extends GenericService<TimePeriod>{
    
    public TimePeriodService(){
        super(TimePeriod.class);
    }
           
    
}
