/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bfh.mle.backend.model;

/**
 * Repraesentiert die Zeitraumarten, die gemessen werden koennen.<br />
 * BEHANDLUNG - Zeitraeum in dem eine aerztliche Behandlung stattgefunden hat. <br />
 * PFLEGEUNTERBRUCH - Zeitraum, in dem der Patient nicht gepflegt wurde.
 * @author Stefan Walle
 */
public enum Zeitraumart {
    BEHANDLUNG,
    PFLEGEUNTERBRUCH
}
