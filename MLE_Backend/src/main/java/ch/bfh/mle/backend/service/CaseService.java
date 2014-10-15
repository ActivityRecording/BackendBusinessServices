/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bfh.mle.backend.service;

import ch.bfh.mle.backend.model.Behandlungsfall;
import javax.ejb.Stateless;
import javax.inject.Named;

/**
 *
 * @author haueb1@students.bfh.ch
 */

@Stateless
@Named
public class CaseService extends GenericDaoService<Behandlungsfall>{
    
    public CaseService(){
        super(Behandlungsfall.class);
    }
}
