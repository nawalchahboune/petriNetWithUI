package org.pneditor.petrinet.models.nawal.src.Metier;

import java.util.ArrayList;


import org.pneditor.petrinet.models.nawal.src.Exceptions.*;

/**
 * Représente une transition dans un réseau de Petri.
 * Une transition est une entité qui peut être activée (tirée) si tous les arcs entrants sont satisfaits.
 * Une transition peut avoir plusieurs arcs entrants et sortants.
 */
public class Transition {
	
	// Indicateur de si la transition peut être tirée
	private boolean tirable;
	// Liste des arcs entrants de la transition
	private ArrayList<ArcSortant> arcsEntrants;
	// Liste des arcs sortants de la transition
	private ArrayList<ArcEntrant> arcsSortants;
	
	 /**
     * Constructeur par défaut pour une transition.
     * Initialise la transition avec un état "non tirable" et des listes vides pour les arcs.
     */
	public Transition() {
		this.tirable = false;
		this.arcsEntrants = new ArrayList<ArcSortant>();
		this.arcsSortants = new ArrayList<ArcEntrant>();
		
	}
	

	
    /**
     * Définit l'état de la transition, c'est-à-dire si elle peut être tirée ou non.
     * 
     * @param tirable Indique si la transition est tirable.
     */
	
	public void setTirable(boolean tirable) {
	
		this.tirable=tirable;
	}
	
    /**
     * Vérifie si la transition peut être tirée.
     * Une transition est tirable si tous les arcs entrants dans cette transition sont "fireable".
     * 
     * @return true si la transition peut être tirée, false sinon.
     */
	public boolean isTirable() {
		tirable =true;
		for (ArcSortant arcEntrant : arcsEntrants) {
			  // Si l'un des arcs entrants n'est pas tirable, la transition ne l'est pas
			if(!arcEntrant.arcIsFireable()) {
				tirable =false;
				break;
			}
		}
		return tirable;
	}
	
    /**
     * Obtient la liste des arcs sortants de la transition.
     * 
     * @return La liste des arcs sortants.
     */
	public  ArrayList<ArcEntrant> getArcsSortants() {
		return   arcsSortants;
		
	}
	
    /**
     * Obtient la liste des arcs entrants de la transition.
     * 
     * @return La liste des arcs entrants.
     */
	public  ArrayList<ArcSortant> getArcsEntrants() {
		
		return arcsEntrants  ;
	}
	
	 /**
     * Ajoute un arc sortant à la transition.
     * Si l'arc est déjà présent, une exception `ExistingArc` est levée.
     * 
     * @param arcSortant L'arc sortant à ajouter.
     * @throws ExistingArc Si l'arc sortant existe déjà dans la liste.
     */
	public void add_to_arc_sortant(ArcEntrant arcSortant) throws ExistingArc{
		if(this.arcsSortants.contains(arcSortant)) {
			throw new ExistingArc();
		}
		else {
			this.arcsSortants.add(arcSortant);
		}
		
	}
	 /**
     * Ajoute un arc entrant à la transition.
     * Si l'arc est déjà présent, une exception `ExistingArc` est levée.
     * 
     * @param arc L'arc entrant à ajouter.
     * @throws ExistingArc Si l'arc entrant existe déjà dans la liste.
     */
	public void add_to_arc_entrant(ArcSortant arc) throws ExistingArc {
		if(this.arcsEntrants.contains(arc)) {
			throw new ExistingArc();
		}
		else {
			this.arcsEntrants.add(arc);
		}
		
	}

    /**
     * Retire un arc sortant de la transition.
     * 
     * @param arcSortant L'arc sortant à retirer.
     */
	public void remove_from_arc_Sortant(ArcEntrant arcSortant) {
		this.arcsSortants.remove(arcSortant);
		
	}
	
	   /**
     * Retire un arc entrant de la transition.
     * 
     * @param arcEntrant L'arc entrant à retirer.
     */
	public void remove_from_arc_entrant(ArcSortant arcEntrant) {
		this.arcsEntrants.remove(arcEntrant);
	}
	

    /**
     * Retourne une chaîne de caractères représentant l'état de la transition.
     * La représentation inclut le nombre d'arcs entrants et sortants, et le type d'arcs (normal, videur, zéro).
     * Si la transition est isolée (pas d'arcs entrants ou sortants), cette information est aussi incluse.
     * 
     * @return La représentation sous forme de chaîne de caractères.
     */
	@Override
	public String toString() {
	    int Enormal = 0;
	    int Evideur = 0;
	    int Ezero = 0;
	    String s = "transition, ";

	    if (arcsEntrants.isEmpty() && arcsSortants.isEmpty()) {
	        s += "Isolée";
	    } else {
	        if (!arcsSortants.isEmpty()) {
	            s += this.getArcsSortants().size() + " arc(s) sortant(s) ";
	        } else {
	            s += "0 arc(s) sortant(s) ";
	        }

	        if (!arcsEntrants.isEmpty()) {
	            s += "// arc(s) entrant(s) ==> ";
	            for (ArcSortant arcEntrant : this.getArcsEntrants()) {
	                if (arcEntrant instanceof ArcSortantNormal) {
	                    Enormal++;
	                } else if (arcEntrant instanceof ArcVideur) {
	                    Evideur++;
	                } else if (arcEntrant instanceof ArcZero) {
	                    Ezero++;
	                }
	            }
	            s += Enormal + " arc(s) entrant(s) normal / "
	                 + Evideur + " arc(s) entrant(s) videur / "
	                 + Ezero + " arc(s) entrant(s) zero .";
	        }
	    }

	    return s;
	}
	

}
