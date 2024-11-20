package org.pneditor.petrinet.models.nawal.src.Metier;

import org.pneditor.petrinet.models.nawal.src.Exceptions.*;

/**
 * Classe représentant un arc videur dans un réseau de Petri.
 * Un arc videur est un type d'arc sortant qui permet de vider une place de ses jetons lorsque la transition associée est tirée.
 * Cet arc est activé lorsque la place contient au moins un jeton.
 */

public class ArcVideur extends ArcSortant{
	private boolean active;
    /**
     * Constructeur de la classe ArcVideur.
     * Initialise un arc videur entre une place et une transition.
     * L'arc est ajouté aux listes d'arcs associés à la place et à la transition.
     * 
     * @param place La place associée à cet arc videur.
     * @param transition La transition associée à cet arc videur.
     * @throws NullPlaceException Si la place est nulle.
     * @throws NullTransitionException Si la transition est nulle.
     * @throws ExistingArc Si l'arc existe déjà dans les listes de la place ou de la transition.
     */
	public ArcVideur(Place place, Transition transition)  throws NullPlaceException , NullTransitionException, ExistingArc{

		super(place, transition);
		// Ajout de l'arc aux listes des arcs associés à la transition et à la place
		transition.add_to_arc_entrant(this);
		place.add_to_arc_sortant(this);
	}
	
    /**
     * Met à jour le nombre de jetons dans la place associée à cet arc videur.
     * Si l'arc est actif (c'est-à-dire si la place a au moins un jeton), tous les jetons de la place sont supprimés.
     * 
     * @throws NegativeToken Si une tentative de mise à jour des jetons conduit à un nombre négatif de jetons.
     */
	@Override
	public void update_jeton_place() throws NegativeToken {
		if(active) {
			// Vide la place en mettant son nombre de jetons à 0
			this.getPlace().setJetons(0);
		}
	}
	
    /**
     * Vérifie si l'arc peut être tiré.
     * Un arc videur est tirable si la place contient au moins un jeton.
     * Si l'arc est tirable, il est activé et pourra vider la place de ses jetons.
     * 
     * @return true si l'arc est tirable (place contient au moins un jeton), false sinon.
     */
	@Override
	public boolean arcIsFireable() {
		if (this.getPlace().getJetons() >=1) {
			this.active=true;
			return true;}
		else {
			this.active=false;
			return false;
		}
	}

    /**
     * Compare cet arc videur à un autre objet pour déterminer s'ils sont égaux.
     * Deux arcs videurs sont égaux s'ils ont la même place et la même transition associées.
     * 
     * @param obj L'objet à comparer.
     * @return true si l'objet est égal à cet arc videur, false sinon.
     */
	
	@Override
	public boolean equals(Object obj) {
	    if (this == obj) return true; // Vérification de référence
	    if (obj == null || getClass() != obj.getClass()) return false; // Vérification du type

	    ArcVideur other = (ArcVideur) obj;

	    // Comparaison des attributs poids, place et transition
	    return  
	           (this.getPlace() != null && this.getPlace().equals(other.getPlace())) &&
	           (this.getTransition() != null && this.getTransition().equals(other.getTransition()));
	}


}
