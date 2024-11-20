package org.pneditor.petrinet.models.nawal.src.Metier;

import org.pneditor.petrinet.models.nawal.src.Exceptions.*;

/**
 * Classe représentant un arc sortant normal dans un réseau de Petri.
 * Un arc sortant normal est un arc sortant qui n'est ni videur ni zero.
 */
public class ArcSortantNormal extends ArcSortant {
	
	// Le poids de l'arc (nombre de jetons à retirer)
	private int poids;
	
	
	 /**
     * Constructeur de la classe ArcSortantNormal.
     * Initialise un arc sortant normal entre une place et une transition, avec un poids spécifique.
     * 
     * @param poids Le poids de l'arc (nombre de jetons à retirer).
     * @param place La place associée à cet arc sortant.
     * @param transition La transition associée à cet arc sortant.
     * @throws NullPlaceException Si la place est nulle.
     * @throws NegativeWeight Si le poids est négatif.
     * @throws NullTransitionException Si la transition est nulle.
     * @throws ExistingArc Si l'arc existe déjà dans les listes de la place ou de la transition.
     */

	public ArcSortantNormal(int poids,Place place, Transition transition) throws NullPlaceException, NegativeWeight, NullTransitionException, ExistingArc {
		// TODO Auto-generated constructor stub
		super(place, transition);
		
		
		//Vérification de la validité du poids
		if(poids<0) {
			throw new NegativeWeight();
		}
		
		// Initialisation du poids de l'arc
		this.poids=poids;
		//Ajout de l'arc à la liste des arcs entrants de la transition associée et des arcs sortants de la place associée
		transition.add_to_arc_entrant(this);
		place.add_to_arc_sortant(this);
	}

	
	
	 /**
     * Modifie le poids de l'arc.
     * 
     * @param poids Le nouveau poids de l'arc.
     * @throws NegativeWeight Si le poids est négatif.
     */
	public void setPoids(int poids) throws NegativeWeight {
		if(poids<0) {
			throw new NegativeWeight();
		}
		else {

			this.poids = poids;
		}
	}
	 /**
     * Récupère le poids de l'arc.
     * 
     * @return Le poids de l'arc.
     */
	public int getPoids() {
		return this.poids;
	}
	
	 /**
     * Met à jour le nombre de jetons dans la place associée à l'arc.
     * Lorsqu'une transition est tirée, les jetons sont transférés de la place vers la transition
     * selon le poids de l'arc sortant.
     * 
     * @throws NegativeToken Si le nombre de jetons dans la place devient négatif après la mise à jour.
     */
	@Override
	public void update_jeton_place() throws NegativeToken{
		int jetons = this.getPlace().getJetons();
		  // Vérification que la place possède suffisamment de jetons pour effectuer la mise à jour
		if(jetons-this.poids>=0) {
			this.getPlace().setJetons(jetons - this.poids);
		}else {
			throw new NegativeToken();
		}
			
		
	}
	
	 /**
     * Vérifie si l'arc peut être tiré.
     * Un arc sortant est tirable si la place possède un nombre suffisant de jetons pour correspondre au poids de l'arc.
     * 
     * @return true si l'arc est tirable, false sinon.
     */
	@Override
	public boolean arcIsFireable() {
		 // L'arc est tirable si le nombre de jetons dans la place est supérieur ou égal au poids de l'arc
		if (this.poids <= this.getPlace().getJetons()) {
			return true;
		}
		return false;
	}
	
	 /**
     * Retourne une chaîne de caractères représentant l'arc.
     * La représentation inclut le poids de l'arc, ainsi que le nombre de jetons dans la place associée.
     * 
     * @return La représentation sous forme de chaîne de l'arc.
     */

	public String toString() {
		//arc simple poids 1 (place avec 4 jetons vers transition)
		
		return "arc simple poids "+ this.getPoids() +
				" ( place avec "+this.getPlace().getJetons()+" jetons vers transition" ;
	}
	
	 /**
     * Compare cet arc à un autre objet pour déterminer s'ils sont égaux.
     * Deux arcs sont égaux s'ils ont le même poids, la même place et la même transition.
     * 
     * @param obj L'objet à comparer.
     * @return true si l'objet est égal à cet arc, false sinon.
     */

	@Override
	public boolean equals(Object obj) {
	    if (this == obj) return true; 
	    if (obj == null || getClass() != obj.getClass()) return false;

	    ArcSortantNormal other = (ArcSortantNormal) obj;

	    return this.poids == other.poids &&
	           (this.getPlace() != null && this.getPlace().equals(other.getPlace())) &&
	           (this.getTransition() != null && this.getTransition().equals(other.getTransition()));

	}

}
