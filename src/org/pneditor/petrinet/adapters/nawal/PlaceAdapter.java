package org.pneditor.petrinet.adapters.nawal;

import org.pneditor.petrinet.AbstractPlace;
import org.pneditor.petrinet.models.nawal.src.Exceptions.NegativeToken;
import org.pneditor.petrinet.models.nawal.src.Metier.Place;

/**
 * Adapter class for the Place entity in the Petri network.
 * This class bridges the abstract representation of a place with the concrete implementation.
 */
public class PlaceAdapter extends AbstractPlace {
    private Place place;

    /**
     * Constructor to create a PlaceAdapter with a label.
     *
     * @param label the label for the place.
     */
    public PlaceAdapter(String label) {
        super(label);
    }

    /**
     * Constructor to create a PlaceAdapter with a label and an existing Place instance.
     *
     * @param label the label for the place.
     * @param place the underlying Place instance.
     */
    public PlaceAdapter(String label, Place place) {
        super(label);
        this.place = place;
    }

    /**
     * Constructor to create a PlaceAdapter with an existing Place instance.
     *
     * @param place the underlying Place instance.
     */
    public PlaceAdapter(Place place) {
        super("");
        this.place = place;
    }

    /**
     * Adds a token to the place.
     * Increments the number of tokens stored in the underlying Place instance.
     */
    @Override
    public void addToken() {
        try {
            this.place.setJetons(this.place.getJetons() + 1);
        } catch (NegativeToken e) {
            e.printStackTrace();
        }
    }

    /**
     * Removes a token from the place.
     * Decrements the number of tokens stored in the underlying Place instance.
     */
    @Override
    public void removeToken() {
        try {
            this.place.setJetons(this.place.getJetons() - 1);
        } catch (NegativeToken e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets the number of tokens in the place.
     *
     * @return the current number of tokens.
     */
    @Override
    public int getTokens() {
        return this.place.getJetons();
    }

    /**
     * Sets the number of tokens in the place.
     *
     * @param tokens the number of tokens to set.
     */
    @Override
    public void setTokens(int tokens) {
        try {
            this.place.setJetons(tokens);
        } catch (NegativeToken e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves the underlying Place instance.
     *
     * @return the associated Place instance.
     */
    public Place getPlace() {
        return place;
    }
}
