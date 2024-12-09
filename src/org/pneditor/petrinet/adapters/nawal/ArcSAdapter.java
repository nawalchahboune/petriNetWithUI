package org.pneditor.petrinet.adapters.nawal;

import org.pneditor.petrinet.AbstractArc;
import org.pneditor.petrinet.AbstractNode;
import org.pneditor.petrinet.ResetArcMultiplicityException;
import org.pneditor.petrinet.models.nawal.src.Exceptions.NegativeWeight;
import org.pneditor.petrinet.models.nawal.src.Metier.Arc;
import org.pneditor.petrinet.models.nawal.src.Metier.ArcSortantNormal;
import org.pneditor.petrinet.models.nawal.src.Metier.ArcVideur;
import org.pneditor.petrinet.models.nawal.src.Metier.ArcZero;

/**
 * Adapter class that adapts an outgoing {@link Arc} to the {@link AbstractArc} interface.
 * This allows the integration of the Petri net model with the graphical interface.
 */
public class ArcSAdapter extends AbstractArc {
    
    private Arc arc; // The adapted Arc instance

    /**
     * Constructs an adapter for the given Arc.
     *
     * @param arc The Arc object to be adapted.
     */
    public ArcSAdapter(Arc arc) {
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
        PlaceAdapter pA = new PlaceAdapter(this.arc.getPlace());
        return pA;
    }

    /**
     * Retrieves the destination node of the arc, adapted to an {@link AbstractNode}.
     *
     * @return The destination node as an AbstractNode.
     */
    @Override
    public AbstractNode getDestination() {
        TransitionAdapter tA = new TransitionAdapter(arc.getTransition());
        return tA;
    }

    /**
     * Indicates whether the arc is a reset arc.
     *
     * @return {@code true} if the arc is an instance of {@link ArcVideur}, {@code false} otherwise.
     */
    @Override
    public boolean isReset() {
        return arc instanceof ArcVideur;
    }

    /**
     * Indicates whether the arc is a regular arc.
     *
     * @return {@code true} if the arc is an instance of {@link ArcSortantNormal}, {@code false} otherwise.
     */
    @Override
    public boolean isRegular() {
        return arc instanceof ArcSortantNormal;
    }

    /**
     * Indicates whether the arc is an inhibitory arc.
     *
     * @return {@code true} if the arc is an instance of {@link ArcZero}, {@code false} otherwise.
     */
    @Override
    public boolean isInhibitory() {
        return arc instanceof ArcZero;
    }

    /**
     * Retrieves the multiplicity (weight) of the arc.
     *
     * @return The multiplicity of the arc if it is an instance of {@link ArcSortantNormal}.
     * @throws ResetArcMultiplicityException If the arc is not applicable for multiplicity.
     */
    @Override
    public int getMultiplicity() throws ResetArcMultiplicityException {
        int multiplicity = 0;
        if (this.arc instanceof ArcSortantNormal) {
            try {
                multiplicity = ((ArcSortantNormal) arc).getPoids();
            } catch (Exception e) {
                e.printStackTrace(); // Handle exception
            }
        }
        return multiplicity;
    }

    /**
     * Sets the multiplicity (weight) of the arc.
     *
     * @param multiplicity The new multiplicity to set.
     * @throws ResetArcMultiplicityException If the arc is not applicable for multiplicity.
     */
    @Override
    public void setMultiplicity(int multiplicity) throws ResetArcMultiplicityException {
        if (this.arc instanceof ArcSortantNormal) {
            try {
                ((ArcSortantNormal) arc).setPoids(multiplicity);
            } catch (NegativeWeight e) {
                e.printStackTrace(); // Handle invalid weight value
            }
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
     * @return {@code true}, as the source node is a place in this implementation.
     */
    @Override
    public boolean isSourceAPlace() {
        return true;
    }
}
