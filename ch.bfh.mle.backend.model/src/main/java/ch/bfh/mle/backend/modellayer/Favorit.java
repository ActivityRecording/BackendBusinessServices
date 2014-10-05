/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bfh.mle.backend.modellayer;

import com.sun.jndi.toolkit.dir.SearchFilter;
import java.io.Serializable;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * Repraesentiert eine Liste von Tarmedleistungen, die dem Anwender in der Liste seiner Favoriten angezeigt werden.
 * @author Stefan Walle
 */
@Entity
@Access(AccessType.FIELD)
public class Favorit implements Serializable {

        /**
     * Serial-ID
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * Technische Datenbank-ID.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    private Leistungserbringer leistungserbringer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Leistungserbringer getLeistungserbringer() {
        return leistungserbringer;
    }

    public void setLeistungserbringer(Leistungserbringer leistungserbringer) {
        this.leistungserbringer = leistungserbringer;
    }

    
}
