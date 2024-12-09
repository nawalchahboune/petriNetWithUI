package org.pneditor.petrinet.adapters.nawal;

import org.pneditor.petrinet.AbstractTransition;
import org.pneditor.petrinet.models.nawal.src.Metier.ArcSortant;
import org.pneditor.petrinet.models.nawal.src.Metier.Transition;

/**
 * Adapter class for the Transition entity in the Petri network.
 * This class bridges the abstract representation of a transition with its concrete implementation.
 */
public class TransitionAdapter extends AbstractTransition {
    private Transition transition;

    /**
     * Constructor to create a TransitionAdapter with a label.
     *
     * @param label the label for the transition.
     */
    public TransitionAdapter(String label) {
        super(label);
    }

    /**
     * Constructor to create a TransitionAdapter with a label and an existing Transition instance.
     *
     * @param label      the label for the transition.
     * @param transition the underlying Transition instance.
     */
    public TransitionAdapter(String label, Transition transition) {
        super(label);
        this.transition = transition;
    }

    /**
     * Constructor to create a TransitionAdapter with an existing Transition instance.
     *
     * @param transition the underlying Transition instance.
     */
    public TransitionAdapter(Transition transition) {
        super("");
        this.transition = transition;
    }

    /**
     * Retrieves the underlying Transition instance.
     *
     * @return the associated Transition instance.
     */
    public Transition getTransition() {
        return transition;
    }

    /**
     * Checks if the transition is fireable (tirable).
     * Evaluates the fireability of the transition based on its incoming arcs.
     *
     * @return true if the transition is fireable; false otherwise.
     */
    public boolean isTirable() {
        for (ArcSortant arc : this.getTransition().getArcsEntrants()) {
            // Evaluate the fireability of each incoming arc (debugging logic can be uncommented).
        }
        return this.transition.isTirable();
    }
}
