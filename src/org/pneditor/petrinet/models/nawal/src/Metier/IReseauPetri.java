package org.pneditor.petrinet.models.nawal.src.Metier;

import java.util.List;

import org.pneditor.petrinet.models.nawal.src.Exceptions.*;

/**
 * Interface représentant un réseau de Petri.
 * Elle définit les opérations possibles sur un réseau de Petri, telles que l'ajout, la suppression
 * et la manipulation des places, des transitions et des arcs, ainsi que la gestion des jetons et des poids.
 */

public interface IReseauPetri {
	
    /**
     * Ajoute un arc au réseau de Petri.
     * 
     * @param arc L'arc à ajouter.
     * @throws NullTArcException Si l'arc est nul.
     * @throws ExistingArc Si l'arc existe déjà dans le réseau.
     */
	
	public void ajouter_Arc(Arc arc) throws NullTArcException, ExistingArc;
    /**
     * Supprime un arc du réseau de Petri.
     * 
     * @param arc L'arc à supprimer.
     */
	public void supprimer_Arc(Arc arc);
    /**
     * Ajoute une place au réseau de Petri.
     * 
     * @param place La place à ajouter.
     * @throws NullPlaceException Si la place est nulle.
     * @throws ExistingPlace Si la place existe déjà dans le réseau.
     */
	public void ajouter_Place(Place place) throws NullPlaceException , ExistingPlace;
	 /**
     * Supprime une place du réseau de Petri.
     * 
     * @param place La place à supprimer.
     */
	public void supprimer_Place(Place place);
	  /**
     * Ajoute une transition au réseau de Petri.
     * 
     * @param transition La transition à ajouter.
     * @throws NullTransitionException Si la transition est nulle.
     * @throws ExistingTransition Si la transition existe déjà dans le réseau.
     */
	public void ajouter_Tarnsition(Transition transition) throws NullTransitionException , ExistingTransition;
    /**
     * Supprime une transition du réseau de Petri.
     * 
     * @param transition La transition à supprimer.
     */
	public void supprimer_Tarnsition(Transition transition);
    /**
     * Ajoute des jetons à une place spécifique dans le réseau de Petri.
     * 
     * @param place La place à laquelle ajouter des jetons.
     * @param jetons Le nombre de jetons à ajouter.
     * @throws NegativeToken Si le nombre de jetons devient négatif.
     * @throws NullPlaceException Si la place est nulle.
     */
	public void ajouterJetons(Place place , int jetons) throws NegativeToken , NullPlaceException ;

    /**
     * Retire des jetons d'une place spécifique dans le réseau de Petri.
     * 
     * @param place La place à laquelle retirer des jetons.
     * @param jetons Le nombre de jetons à retirer.
     * @throws NegativeToken Si le nombre de jetons devient négatif après le retrait.
     * @throws ExceedExistingToken Si le nombre de jetons à retirer dépasse le nombre de jetons existants.
     * @throws NullPlaceException Si la place est nulle.
     */
	public void enleverJetons(Place place , int jetons) throws NegativeToken,ExceedExistingToken, NullPlaceException ;
    /**
     * Change le poids d'un arc dans le réseau de Petri.
     * 
     * @param arc L'arc dont le poids doit être changé.
     * @param poids Le nouveau poids à attribuer à l'arc.
     * @throws NullTArcException Si l'arc est nul.
     * @throws NegativeWeight Si le poids est négatif.
     */
	public void changerPoids(Arc arc , int poids) throws NullTArcException, NegativeWeight;
	  /**
     * Exécute une transition dans le réseau de Petri, en vérifiant si elle peut être tirée.
     * 
     * @param transition La transition à exécuter.
     * @throws NullTransitionException Si la transition est nulle.
     * @throws NegativeToken Si une place a un nombre de jetons insuffisant pour exécuter la transition.
     */
	public void fire(Transition transition) throws NullTransitionException, NegativeToken;
	  /**
     * Exécute toutes les transitions qui sont tirables dans le réseau de Petri jusqu'à l'absence d'une transition tirable.
     */
	public void fireAll();
	/**
	 * Vérifie si au moins une transition est tirable dans une liste de transitions.
	 *
	 * @param transitions La liste des transitions à vérifier.
	 * @return true si au moins une transition est tirable, sinon  false.
	 *
	 */
	public boolean continuerATirer(List<Transition> transitions) ;

}
