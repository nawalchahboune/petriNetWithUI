package org.pneditor.petrinet.models.nawal.src.Metier;

import org.pneditor.petrinet.models.nawal.src.Exceptions.ExistingArc;
/**
 * Classe abstraite représentant un arc dans un réseau de Petri.
 * Un arc connecte une place et une transition dans un réseau de Petri.
 * Cette classe contient les informations de base pour un arc, telles que la place source
 * et la transition cible.
 */

public abstract class Arc {
	
	 /** La place associée à cet arc. */
	private Place place;
	/** La transition associée à cet arc. */
	private Transition transition;

	

    /**
     * Constructeur de la classe Arc. Crée un arc qui relie une place et une transition spécifiées.
     * 
     * @param place La place à connecter à la transition.
     * @param transition La transition à connecter à la place.
     */

	
	public Arc(Place place, Transition transition) {
		
		this.place = place;
		this.transition = transition;
	}
	
	 /**
     * Retourne la place associée à cet arc.
     * 
     * @return La place associée à l'arc.
     */
	
	public Place getPlace() {
		return place;
	}
	
	 /**
     * Modifie la place associée à cet arc.
     * 
     * @param place La nouvelle place à associer à l'arc.
     */
	public void setPlace(Place place) {
		this.place = place;
	}
	
    /**
     * Retourne la transition associée à cet arc.
     * 
     * @return La transition associée à l'arc.
     */
	
	public Transition getTransition() {
		return transition;
	}
	
    /**
     * Modifie la transition associée à cet arc.
     * 
     * @param transition La nouvelle transition à associer à l'arc.
     */
	
	public void setTransition(Transition transition) {
		this.transition = transition;
	}
	
}
