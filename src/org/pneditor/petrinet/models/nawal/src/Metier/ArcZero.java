package org.pneditor.petrinet.models.nawal.src.Metier;

import org.pneditor.petrinet.models.nawal.src.Exceptions.*;
/**
 * Classe représentant un arc sortant de type "arc zéro" dans un réseau de Petri.
 * Un arc zéro est un arc qui ne peut être tiré que si la place associée contient zéro jeton.
 * Lorsqu'il est tiré, il ne modifie pas le nombre de jetons de la place, contrairement aux autres arcs sortants.
 */

public class ArcZero extends ArcSortant {
	// Indique si l'arc est activé (peut être tiré)
	private boolean active;
	
    /**
     * Constructeur de la classe ArcZero.
     * Initialise un arc zéro entre une place et une transition données.
     * L'arc est ajouté aux listes d'arcs associés à la place et à la transition.
     * 
     * @param place La place associée à cet arc zéro.
     * @param transition La transition associée à cet arc zéro.
     * @throws NullPlaceException Si la place est nulle.
     * @throws NullTransitionException Si la transition est nulle.
     * @throws ExistingArc Si l'arc existe déjà dans les listes de la place ou de la transition.
     */
	
	public ArcZero(Place place, Transition transition) throws NullPlaceException , NullTransitionException, ExistingArc{
		// TODO Auto-generated constructor stub
		super(place, transition);
		// Initialisation de l'état de l'arc à inactif (pas tirable)
		this.active =false;
		// Ajout de l'arc aux listes des arcs associés à la transition et à la place
		transition.add_to_arc_entrant(this);
		place.add_to_arc_sortant(this);
	}
	
	  /**
     * Méthode qui met à jour les jetons dans la place associée à l'arc zéro.
     * Cette méthode ne fait rien pour l'arc zéro, car cet arc ne modifie pas les jetons de la place.
     */
	@Override
	public void update_jeton_place() {
		 // L'arc zéro ne modifie pas les jetons dans la place, donc la méthode est vide.
	}
	
	 /**
     * Vérifie si l'arc zéro peut être tiré.
     * L'arc zéro est tirable si la place associée contient exactement zéro jeton.
     * 
     * @return true si la place contient zéro jeton et l'arc peut être tiré, false sinon.
     */
	@Override
	public boolean arcIsFireable() {
		if (this.getPlace().getJetons() == 0) {
			this.setActive(true);
			return true;}
		this.setActive(false);
		return false;
	}
	
	 /**
     * Définit l'état de l'arc (actif ou inactif).
     * 
     * @param active L'état de l'arc (true pour actif, false pour inactif).
     */
	
	public void setActive(boolean active) {
		this.active = active;
	}
	
    /**
     * Obtient l'état de l'arc (actif ou inactif).
     * 
     * @return true si l'arc est actif , false sinon.
     */
	
	public boolean isActive() {
		return active;
	}
	
    /**
     * Compare cet arc zéro à un autre objet pour déterminer s'ils sont égaux.
     * Deux arcs zéro sont égaux s'ils ont la même place et la même transition associées.
     * 
     * @param obj L'objet à comparer.
     * @return true si l'objet est égal à cet arc zéro, false sinon.
     */

	
	@Override
	public boolean equals(Object obj) {
	    if (this == obj) return true; // Vérification de référence
	    if (obj == null || getClass() != obj.getClass()) return false; // Vérification du type

	    ArcZero other = (ArcZero) obj;

	    // Comparaison des attributs poids, place et transition
	    return 
	           (this.getPlace() != null && this.getPlace().equals(other.getPlace())) &&
	           (this.getTransition() != null && this.getTransition().equals(other.getTransition()));
	}

}
