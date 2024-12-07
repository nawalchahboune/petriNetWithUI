package org.pneditor.petrinet.models.nawal.src.Metier;

import java.util.ArrayList;


import org.pneditor.petrinet.models.nawal.src.Exceptions.*;

/**
 * Représente une place dans un réseau de Petri.
 * Une place contient un certain nombre de jetons et a des arcs entrants et sortants qui la relient aux transitions.
 * Les places sont utilisées pour stocker des jetons qui peuvent être consommés ou produits lors de l'exécution des transitions.
 */
public class Place {
	// Le nombre de jetons dans la place
	private int jetons;
	// Liste des arcs sortants de la place
	private ArrayList<ArcSortant> arcsSortants;
	// Liste des arcs entrants vers la place
	private ArrayList<ArcEntrant> arcsEntrants;
	
    /**
     * Constructeur par défaut pour une place avec zéro jeton.
     * Initialise les listes d'arcs entrants et sortants.
     */
	public Place() {
	
		this.jetons=0;
		this.arcsSortants = new ArrayList<ArcSortant>();
		this.arcsEntrants = new ArrayList<ArcEntrant>();

	
	}
	
    /**
     * Constructeur pour une place avec un nombre de jetons donné.
     * 
     * @param jetons Le nombre initial de jetons dans la place.
     * @throws NegativeToken Si le nombre de jetons est négatif.
     */
	public Place(int jetons) throws NegativeToken{
		if(jetons>=0) {
			this.jetons = jetons;
			
			this.arcsEntrants = new ArrayList<ArcEntrant>();
			this.arcsSortants = new ArrayList<ArcSortant>();
			 
		}else {
			throw new NegativeToken();
		}
	}
	
    /**
     * Obtient le nombre de jetons dans la place.
     * 
     * @return Le nombre de jetons.
     */

	public int getJetons() {
		return jetons;
	}
	
    /**
     * Définit le nombre de jetons dans la place.
     * 
     * @param jetons Le nombre de jetons à définir.
     * @throws NegativeToken Si le nombre de jetons est négatif.
     */

	public void setJetons(int jetons) throws NegativeToken{
		if(jetons>=0) {

			this.jetons = jetons;
			//System.out.println("tokens i want to out : "+ jetons);
		}
		else {
			throw new NegativeToken();
		}
	}
	
    /**
     * Enlève un certain nombre de jetons de la place.
     * 
     * @param jetons Le nombre de jetons à enlever.
     * @throws ExceedExistingToken Si le nombre de jetons à enlever dépasse le nombre actuel de jetons.
     */

	public void enleverJetons(int jetons) throws ExceedExistingToken {
		if(this.getJetons()-jetons<0) {
			throw new ExceedExistingToken();
		}
		else { 
			this.jetons-= jetons;
		}
	}
    /**
     * Obtient la liste des arcs entrants vers cette place.
     * 
     * @return La liste des arcs entrants.
     */
	public  ArrayList<ArcEntrant> getArcsEntrants() {
		return  arcsEntrants ;
		
	}
	 /**
     * Obtient la liste des arcs sortants de cette place.
     * 
     * @return La liste des arcs sortants.
     */
	public  ArrayList<ArcSortant> getArcsSortants() {
		
		return  arcsSortants ;
	}
	
    /**
     * Ajoute un arc sortant à la liste des arcs sortants de la place.
     * 
     * @param arcSortant L'arc sortant à ajouter.
     */
	public void add_to_arc_sortant(  ArcSortant arcSortant) {
		this.arcsSortants.add(arcSortant);
		
	}
	
	 /**
     * Ajoute un arc entrant à la liste des arcs entrants de la place.
     * 
     * @param arc L'arc entrant à ajouter.
     */
	public void add_to_arc_entrant( ArcEntrant arc) {
		this.arcsEntrants.add(arc);
		
	}
	
	 /**
     * Retire un arc sortant de la liste des arcs sortants de la place.
     * 
     * @param arcSortant L'arc sortant à retirer.
     */
	public void remove_from_arc_Sortant(ArcSortant arcSortant) {
		this.arcsSortants.remove(arcSortant);
		
	}
	
	 /**
     * Retire un arc entrant de la liste des arcs entrants de la place.
     * 
     * @param arcEntrant L'arc entrant à retirer.
     */
	public void remove_from_arc_entrant( ArcEntrant arcEntrant) {
		this.arcsEntrants.remove(arcEntrant);
		
	}
	
    /**
     * Retourne une chaîne de caractères représentant l'état de la place.
     * La représentation inclut le nombre de jetons dans la place, ainsi que le nombre d'arcs sortants de chaque type.
     * 
     * @return La représentation sous forme de chaîne de caractères.
     */
	@Override
	public String toString() {
		int Snormal =0;
		int Svideur =0;
		int Szero=0;
		for (ArcSortant arcSortant : this.getArcsSortants()) {
			if( arcSortant instanceof ArcSortantNormal ) {
				Snormal++;
			}
			if( arcSortant instanceof ArcVideur ) {
				Svideur++;
			}
			if( arcSortant instanceof ArcZero ) {
				Szero++;
			}
			
		}
		
		return	"place avec "+ this.getJetons() +" :: arcs=> " 
		+ Snormal+ " arc(s) sortant(s) normal / " 
		+ Svideur +" arc(s) sortant(s) videur " 
		+ Szero + " arc(s) sortant(s) zero ///" 
		+ this.getArcsEntrants().size() +" arc(s) entrant(s)  ";
	}


}
