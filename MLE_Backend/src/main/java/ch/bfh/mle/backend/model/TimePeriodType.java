package ch.bfh.mle.backend.model;

/**
 * Repraesentiert die Zeitraumarten, die gemessen werden koennen.<br />
 * BEHANDLUNG - Zeitraeum in dem eine aerztliche Behandlung stattgefunden hat. <br />
 * PFLEGEUNTERBRUCH - Zeitraum, in dem der Patient nicht gepflegt wurde.
 * @author Stefan Walle
 */
public enum TimePeriodType {
    TREATMENT,
    CAREGAP
}
