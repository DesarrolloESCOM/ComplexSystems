package mx.ipn.escom.complexsystems.engine.history

/**
 * Created by alberto on 17/10/15.
 */
@groovy.transform.Canonical
class AutomataNode {
    Integer decimalState
    String binaryState
    boolean isFinal
    Integer hits
    List neighborhood
    Integer contains // reference to previous node
    Integer calculated // 0 not calculated,1 in progress, 2 calculated
}
