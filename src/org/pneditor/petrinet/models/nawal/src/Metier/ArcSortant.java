package org.pneditor.petrinet.models.nawal.src.Metier;

import org.pneditor.petrinet.models.nawal.src.Exceptions.*;

/**
 * Classe abstraite représentant un arc sortant dans un réseau de Petri.
 * Un arc sortant relie une place à une transition.
 * Les arcs sortants ont pour origine une place et extrémité une transition.
 * Cette classe est abstraite car certaines fonctionnalités spécifiques aux arcs sortants,
 * comme la mise à jour des jetons et la vérification de la possibilité de tirer l'arc,
 * dépendent des types spécifiques d'arc sortant.
 */
public abstract class ArcSortant extends Arc{
    /**
     * Constructeur de la classe ArcSortant.
     * Initialise un arc sortant qui relie une place à une transition.
     * 
     * @param place La place associée à cet arc.
     * @param transition La transition associée à cet arc.
     * @throws NullPlaceException Si la place est nulle.
     * @throws NullTransitionException Si la transition est nulle.
     * Ce constructeur a pour but d'être utilisé dans les classes filles et non d'initialiser un élément
     * de cette classe ce qui n'aurait pas de sens
     */

	public ArcSortant(Place place, Transition transition) throws NullPlaceException , NullTransitionException{
		super(place, transition);
		// Vérification des paramètres d'entrée
		if(place==null) {
			throw new NullPlaceException();
		}
		if(transition==null) {
			throw new NullTransitionException();
		}
	}
	 /**
     * Méthode abstraite qui doit être implémentée dans les sous-classes pour
     * mettre à jour le nombre de jetons dans la place associée à cet arc sortant.
     * 
     * @throws NegativeToken Si le nombre de jetons devient négatif après la mise à jour.
     */
	public abstract void update_jeton_place() throws NegativeToken;
    /**
     * Méthode abstraite qui doit être implémentée dans les sous-classes pour vérifier
     * si l'arc est tirable, c'est-à-dire si la transition associée peut être exécutée.
     * 
     * @return true si l'arc est tirable, false sinon.
     */
	public abstract boolean arcIsFireable();
    
}
