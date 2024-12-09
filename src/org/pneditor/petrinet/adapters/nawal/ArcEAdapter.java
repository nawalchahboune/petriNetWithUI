package org.pneditor.petrinet.adapters.nawal;

import org.pneditor.petrinet.AbstractArc;
import org.pneditor.petrinet.AbstractNode;
import org.pneditor.petrinet.ResetArcMultiplicityException;
import org.pneditor.petrinet.models.nawal.src.Exceptions.NegativeWeight;
import org.pneditor.petrinet.models.nawal.src.Metier.Arc;
import org.pneditor.petrinet.models.nawal.src.Metier.ArcEntrant;

/**
 * Adapter class that adapts an {@link Arc} to the {@link AbstractArc} interface.
 * This allows the integration of the underlying Petri net model with a graphical interface.
 */
public class ArcEAdapter extends AbstractArc {
    
    private Arc arc; // The adapted Arc instance

    /**
     * Constructs an adapter for the given Arc.
     *
     * @param arc The Arc object to be adapted.
     */
    public ArcEAdapter(Arc arc) {
        super();
        this.arc = arc;
    }

    /**
     * Retrieves the source node of the arc, adapted to an {@link AbstractNode}.
     *
     * @return The source node as an AbstractNode.
     */
    @Override
    public AbstractNode getSource() {
        TransitionAdapter tA = new TransitionAdapter(arc.getTransition());
        return tA;
    }

    /**
     * Retrieves the destination node of the arc, adapted to an {@link AbstractNode}.
     *
     * @return The destination node as an AbstractNode.
     */
    @Override
    public AbstractNode getDestination() {
        PlaceAdapter pA = new PlaceAdapter(arc.getPlace());
        return pA;
    }

    /**
     * Indicates whether the arc is a reset arc.
     *
     * @return {@code false}, as this arc is not a reset arc.
     */
    @Override
    public boolean isReset() {
        return false; // Not a reset arc
    }

    /**
     * Indicates whether the arc is a regular arc.
     *
     * @return {@code true}, as this arc is a regular arc.
     */
    @Override
    public boolean isRegular() {
        return true; // Regular arc
    }

    /**
     * Indicates whether the arc is an inhibitory arc.
     *
     * @return {@code false}, as this arc is not inhibitory.
     */
    @Override
    public boolean isInhibitory() {
        return false; // Not an inhibitory arc
    }

    /**
     * Retrieves the multiplicity (weight) of the arc.
     *
     * @return The multiplicity of the arc.
     * @throws ResetArcMultiplicityException If the arc is a reset arc, which it isn't in this case.
     */
    @Override
    public int getMultiplicity() throws ResetArcMultiplicityException {
        int multiplicity = ((ArcEntrant) arc).getPoids();
        return multiplicity;
    }

    /**
     * Sets the multiplicity (weight) of the arc.
     *
     * @param multiplicity The new multiplicity to set.
     * @throws ResetArcMultiplicityException If the arc is a reset arc, which it isn't in this case.
     */
    @Override
    public void setMultiplicity(int multiplicity) throws ResetArcMultiplicityException {
        try {
            ((ArcEntrant) arc).setPoids(multiplicity);
        } catch (NegativeWeight e) {
            e.printStackTrace(); // Handle invalid multiplicity value
        }
    }

    /**
     * Retrieves the underlying adapted {@link Arc} instance.
     *
     * @return The adapted Arc object.
     */
    public Arc getArc() {
        return arc;
    }

    /**
     * Indicates whether the source node of the arc is a place.
     *
     * @return {@code false}, as the source node is a transition in this implementation.
     */
    @Override
    public boolean isSourceAPlace() {
        return false; // Source is a transition, not a place
    }
}
