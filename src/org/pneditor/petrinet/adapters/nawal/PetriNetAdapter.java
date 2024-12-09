package org.pneditor.petrinet.adapters.nawal;
import java.util.ArrayList;
import java.util.Iterator;

import org.pneditor.petrinet.AbstractArc;
import org.pneditor.petrinet.AbstractNode;
import org.pneditor.petrinet.AbstractPlace;
import org.pneditor.petrinet.AbstractTransition;
import org.pneditor.petrinet.PetriNetInterface;
import org.pneditor.petrinet.ResetArcMultiplicityException;
import org.pneditor.petrinet.UnimplementedCaseException;
import org.pneditor.petrinet.adapters.imta.ArcAdapter;
import org.pneditor.petrinet.models.nawal.src.Exceptions.ExistingArc;
import org.pneditor.petrinet.models.nawal.src.Exceptions.ExistingPlace;
import org.pneditor.petrinet.models.nawal.src.Exceptions.ExistingTransition;
import org.pneditor.petrinet.models.nawal.src.Exceptions.NegativeToken;
import org.pneditor.petrinet.models.nawal.src.Exceptions.NegativeWeight;
import org.pneditor.petrinet.models.nawal.src.Exceptions.NullPlaceException;
import org.pneditor.petrinet.models.nawal.src.Exceptions.NullTArcException;
import org.pneditor.petrinet.models.nawal.src.Exceptions.NullTransitionException;
import org.pneditor.petrinet.models.nawal.src.Metier.Arc;
import org.pneditor.petrinet.models.nawal.src.Metier.ArcEntrant;
import org.pneditor.petrinet.models.nawal.src.Metier.ArcSortant;
import org.pneditor.petrinet.models.nawal.src.Metier.ArcSortantNormal;
import org.pneditor.petrinet.models.nawal.src.Metier.ArcVideur;
import org.pneditor.petrinet.models.nawal.src.Metier.ArcZero;
import org.pneditor.petrinet.models.nawal.src.Metier.Place;
import org.pneditor.petrinet.models.nawal.src.Metier.ReseauPetri;
import org.pneditor.petrinet.models.nawal.src.Metier.Transition;

/**
 * Adapter class to bridge the ReseauPetri implementation with the PetriNetInterface.
 */
public class PetriNetAdapter extends PetriNetInterface {
    private ReseauPetri network = new ReseauPetri();
    
    /**
     * Adds a new place to the Petri network.
     *
     * @return an adapter wrapping the newly created place.
     */
    @Override
    public AbstractPlace addPlace() {
        Place place = new Place();
        PlaceAdapter placeAdapter = new PlaceAdapter(place);
        try {
            network.ajouter_Place(place);
        } catch (NullPlaceException | ExistingPlace e) {
            e.printStackTrace();
        }
        return placeAdapter;
    }

    /**
     * Adds a new transition to the Petri network.
     *
     * @return an adapter wrapping the newly created transition.
     */
    @Override
    public AbstractTransition addTransition() {
        Transition transition = new Transition();
        TransitionAdapter transitionAdapter = new TransitionAdapter(transition);
        try {
            network.ajouter_Tarnsition(transition);
        } catch (NullTransitionException | ExistingTransition e) {
            e.printStackTrace();
        }
        return transitionAdapter;
    }

    /**
     * Adds a regular arc to the Petri network.
     *
     * @param source      the source node (place or transition).
     * @param destination the destination node (transition or place).
     * @return an adapter wrapping the newly created arc.
     * @throws UnimplementedCaseException if an invalid node combination is provided.
     */
    @Override
    public AbstractArc addRegularArc(AbstractNode source, AbstractNode destination) throws UnimplementedCaseException {
        ArcEAdapter arcEAdapter;
        ArcSAdapter arcsAdapter;
        AbstractArc aA = null;
        Arc arc = null;
        if (source instanceof AbstractPlace) {
            try {
                arc = new ArcSortantNormal(1, ((PlaceAdapter) source).getPlace(), ((TransitionAdapter) destination).getTransition());
                arcsAdapter = new ArcSAdapter(arc);
                aA = arcsAdapter;
                network.ajouter_Arc(arc);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (source instanceof AbstractTransition) {
            try {
                arc = new ArcEntrant(1, ((PlaceAdapter) destination).getPlace(), ((TransitionAdapter) source).getTransition());
                arcEAdapter = new ArcEAdapter(arc);
                aA = arcEAdapter;
                network.ajouter_Arc(arc);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return aA;
    }

    /**
     * Adds an inhibitory arc to the Petri network.
     *
     * @param place      the source place.
     * @param transition the destination transition.
     * @return an adapter wrapping the newly created arc.
     */
    @Override
    public AbstractArc addInhibitoryArc(AbstractPlace place, AbstractTransition transition) {
        ArcSAdapter arcsAdapter;
        AbstractArc aA = null;
        Arc arc = null;
        try {
            arc = new ArcZero(((PlaceAdapter) place).getPlace(), ((TransitionAdapter) transition).getTransition());
            network.ajouter_Arc(arc);
            arcsAdapter = new ArcSAdapter(arc);
            aA = arcsAdapter;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return aA;
    }

    /**
     * Adds a reset arc to the Petri network.
     *
     * @param place      the source place.
     * @param transition the destination transition.
     * @return an adapter wrapping the newly created arc.
     */
    @Override
    public AbstractArc addResetArc(AbstractPlace place, AbstractTransition transition) {
        ArcSAdapter arcsAdapter;
        AbstractArc aA = null;
        Arc arc = null;
        try {
            arc = new ArcVideur(((PlaceAdapter) place).getPlace(), ((TransitionAdapter) transition).getTransition());
            network.ajouter_Arc(arc);
            arcsAdapter = new ArcSAdapter(arc);
            aA = arcsAdapter;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return aA;
    }

    /**
     * Removes a place from the Petri network.
     *
     * @param place the place to remove.
     */
    @Override
    public void removePlace(AbstractPlace place) {
        network.supprimer_Place(((PlaceAdapter) place).getPlace());
    }

    /**
     * Removes a transition from the Petri network.
     *
     * @param transition the transition to remove.
     */
    @Override
    public void removeTransition(AbstractTransition transition) {
        network.supprimer_Tarnsition(((TransitionAdapter) transition).getTransition());
    }

    /**
     * Removes an arc from the Petri network.
     *
     * @param arc the arc to remove.
     */
    @Override
    public void removeArc(AbstractArc arc) {
        if (arc.getSource() instanceof AbstractPlace) {
            network.supprimer_Arc(((ArcSAdapter) arc).getArc());
        } else if (arc.getSource() instanceof AbstractTransition) {
            network.supprimer_Arc(((ArcEAdapter) arc).getArc());
        }
    }

    /**
     * Checks if a transition is enabled (fireable) in the Petri network.
     *
     * @param transition the transition to check.
     * @return true if the transition is enabled; false otherwise.
     * @throws ResetArcMultiplicityException if an invalid multiplicity is encountered.
     */
    @Override
    public boolean isEnabled(AbstractTransition transition) throws ResetArcMultiplicityException {
        return ((TransitionAdapter) transition).isTirable();
    }

    /**
     * Fires a transition, modifying the state of the Petri network.
     *
     * @param transition the transition to fire.
     * @throws ResetArcMultiplicityException if an invalid multiplicity is encountered.
     */
    @Override
    public void fire(AbstractTransition transition) throws ResetArcMultiplicityException {
        try {
            network.fire(((TransitionAdapter) transition).getTransition());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
