package org.pneditor.petrinet.models.nawal.src.Metier;

import org.pneditor.petrinet.models.nawal.src.Exceptions.*;


/**
 * Représente un arc entrant dans une place c'est à dire ayant comme origine une transition et comme extrémité une place.
 * Un arc entrant relie une transition à une place, et il possède un poids qui représente
 * la quantité de jetons qu'il transfère vers la place.
 */

public class ArcEntrant extends Arc {
	
	/** Le poids de l'arc entrant, représentant la quantité de jetons transférés. */
	private int poids;
	
    /**
     * Constructeur de l'arc entrant.
     * Initialise un arc entrant avec un poids, une place et une transition spécifiques.
     * 
     * @param poids Le poids de l'arc, représentant la quantité de jetons transférés.
     * @param place La place associée à cet arc.
     * @param transition La transition associée à cet arc.
     * @throws NullPlaceException Si la place est nulle.
     * @throws NegativeWeight Si le poids est négatif.
     * @throws NullTransitionException Si la transition est nulle.
     * @throws ExistingArc si il existe déjà un arc entrant entre la place et la transition.
     */
	
	public ArcEntrant(int poids ,Place place, Transition transition) throws NullPlaceException, NegativeWeight, NullTransitionException, ExistingArc {
		
		super(place, transition);
		
		// Initialisation du poids
		this.poids=poids;
		
		// Vérification des paramètres d'entrée
		if(place==null) {
			throw new NullPlaceException();
		}
		if(transition==null) {
			throw new NullTransitionException();
		}
		if(poids<0) {
			throw new NegativeWeight();
		}
		// Ajout de l'arc aux listes des arcs sortants de la transition et des arcs entrants de la place
		transition.add_to_arc_sortant(this);
		place.add_to_arc_entrant(this);
		
	}
	
    /**
     * Définit le poids de l'arc entrant.
     * 
     * @param poids Le nouveau poids à attribuer à l'arc.
     * @throws NegativeWeight Si le poids est négatif.
     */
	
	public void setPoids(int poids) throws NegativeWeight{
		if(poids<0) {
		throw new NegativeWeight();
	}
	else {
		
		// Initialisation du poids
		this.poids = poids ;
		
	}
	}
	
    /**
     * Retourne le poids de l'arc entrant.
     * 
     * @return Le poids de l'arc.
     */

	public int getPoids() {
		return this.poids;
	}

    /**
     * Met à jour le nombre de jetons dans la place associée à cet arc.
     * Le nombre de jetons de la place est incrémenté du poids de l'arc.
     * Si cette opération résulte en un nombre négatif de jetons, une exception est levée.
     * 
     * @throws NegativeToken Si le nombre de jetons devient négatif après la mise à jour.
     */

	public void update_jetons_place() throws NegativeToken {
		int a = this.getPlace().getJetons();
		if((a + this.poids>=0)) {
			this.getPlace().setJetons(a + this.poids);
		}else {
			throw new NegativeToken();
		}
		
	}
	
	 /**
     * Retourne une représentation textuelle de l'arc entrant.
     * 
     * @return Une chaîne de caractères représentant l'arc entrant.
     */

	
	@Override
	public String toString() {
		return "arc simple poids "+ this.getPoids() +
				"( transition vers place avec  "+this.getPlace().getJetons()+" jetons";
	}
	
	  /**
     * Vérifie si deux arcs entrants sont égaux.
     * Deux arcs entrants sont considérés égaux si leur poids, place et transition associés sont égaux.
     * 
     * @param obj L'objet à comparer à cet arc.
     * @return true si les arcs sont égaux, false sinon.
     */
	
	@Override
	public boolean equals(Object obj) {
	    if (this == obj) return true; 
	    if (obj == null || getClass() != obj.getClass()) return false;

	    ArcEntrant other = (ArcEntrant) obj;

	    return this.poids == other.poids &&
	           (this.getPlace() != null && this.getPlace().equals(other.getPlace())) &&
	           (this.getTransition() != null && this.getTransition().equals(other.getTransition()));

	}
}
