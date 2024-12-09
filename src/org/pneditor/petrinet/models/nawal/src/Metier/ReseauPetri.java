package org.pneditor.petrinet.models.nawal.src.Metier;
import java.util.ArrayList; 
import java.util.Iterator;
import java.util.List;

import org.pneditor.petrinet.models.nawal.src.Exceptions.*;


/**
 * Représente un réseau de Petri avec des places, des transitions et des arcs.
 * Permet la gestion des jetons, des arcs et des transitions dans le cadre de l'exécution d'un réseau de Petri.
 * Permet l'activation du réseau de Petri créé.
 */

public class ReseauPetri implements IReseauPetri {
	private ArrayList<Arc> arcs;
	private ArrayList<Place> places;
	private ArrayList<Transition> transitions;
	
	/**
     * Constructeur par défaut. Initialise un réseau de Petri vide.
     */
	
	public ReseauPetri() {
		this.arcs = new ArrayList<Arc>();
		this.places = new ArrayList<Place>();
		this.transitions = new ArrayList<Transition>();
	}

	 /**
     * Récupère la liste des arcs du réseau.
     * 
     * @return La liste des arcs.
     */
	public ArrayList<Arc> getArcs(){
		return this.arcs;
	}
	
	/**
     * Récupère la liste des places du réseau.
     * 
     * @return La liste des places.
     */
	
	public ArrayList<Place> getPlaces(){
		return this.places;
	}
	
	/**
     * Récupère la liste des transitions du réseau.
     * 
     * @return La liste des transitions.
     */
	public ArrayList<Transition> getTransitions(){
		return this.transitions;
	}
	
    /**
     * Ajoute un arc au réseau de Petri.
     * 
     * @param arc L'arc à ajouter.
     * @throws NullTArcException Si l'arc est nul.
     * @throws ExistingArc Si l'arc existe déjà dans le réseau.
     */
	

	@Override
	public void ajouter_Arc(Arc arc) throws NullTArcException, ExistingArc{
		
		if(arc!=null) {
			
			 if(this.arcs.contains(arc)) {
				throw new ExistingArc();
			}
			
			this.arcs.add(arc);
		}
		else { throw new NullTArcException();
		
		}
	}
	
    /**
     * Supprime un arc du réseau de Petri.
     * 
     * @param arc L'arc à supprimer.
     */

	@Override
	public void supprimer_Arc(Arc arc) {
		if(this.arcs.contains(arc)) {
			if(arc instanceof ArcSortant) {
				arc.getTransition().remove_from_arc_entrant((ArcSortant)arc);
			}
			if(arc instanceof ArcEntrant) {
				arc.getTransition().remove_from_arc_Sortant((ArcEntrant)arc);
			}
			this.arcs.remove(this.arcs.indexOf(arc));
		}
	}

    /**
     * Ajoute une place au réseau de Petri.
     * 
     * @param place La place à ajouter.
     * @throws NullPlaceException Si la place est nulle.
     * @throws ExistingPlace Si la place existe déjà dans le réseau.
     */
	
	@Override
	public void ajouter_Place(Place place) throws NullPlaceException , ExistingPlace {
		if(place!=null) {
			if(!places.contains(place)) {
				this.places.add(place);
			}else {
				throw new ExistingPlace();
			}
			//si j'ajoute une placce à mon réseau !!  qts => est ce 
		}
		else {
			throw new NullPlaceException();
		}
		
		
	}
	
    /**
     * Supprime une place du réseau de Petri.
     * 
     * @param place La place à supprimer.
     */

	@Override
	public void supprimer_Place(Place place) {
		if(this.places.contains(place)) {
			this.places.remove(this.places.indexOf(place));
		}
		
	}
	
    /**
     * Ajoute une transition au réseau de Petri.
     * 
     * @param transition La transition à ajouter.
     * @throws NullTransitionException Si la transition est nulle.
     * @throws ExistingTransition Si la transition existe déjà dans le réseau.
     */

	@Override
	public void ajouter_Tarnsition(Transition transition) throws NullTransitionException , ExistingTransition {
		if(transition!=null) {
			if(!transitions.contains(transition)) {
				this.transitions.add(transition);
			}else {
				throw new ExistingTransition();
			}
		}
		else {
			throw new NullTransitionException();
		}
		
		
	}
	
    /**
     * Supprime une transition du réseau de Petri.
     * 
     * @param transition La transition à supprimer.
     */

	@Override
	public void supprimer_Tarnsition(Transition transition) {
		if(this.transitions.contains(transition)) {
			this.transitions.remove(this.transitions.indexOf(transition));
			
		}
		
	}
	
	 /**
     * Exécute une transition dans le réseau de Petri, en vérifiant si elle peut être tirée.
     * 
     * @param transition La transition à exécuter.
     * @throws NullTransitionException Si la transition est nulle.
     * @throws NegativeToken Si une place a un nombre de jetons insuffisant pour exécuter la transition.
     */

	@Override
    public void fire(Transition transition) throws NullTransitionException, NegativeToken  {
		//type ArcSortant réfère à un arc sortant de la place associé; 
		//donc un arc entrant de la transition et vis vers ca
		transition.fire();
		
	}
	/**
	 * Vérifie si au moins une transition est tirable dans une liste de transitions.
	 *
	 * @param transitions La liste des transitions à vérifier.
	 * @return true si au moins une transition est tirable, sinon  false.
	 *
	 */
	public boolean continuerATirer(List<Transition> transitions) {
	    // Parcourt chaque transition de la liste
	    for (Transition transition : transitions) {
	        // Vérifie si la transition est tirable
	        if (transition.isTirable()) {
	            return true; // Une transition tirable a été trouvée
	        }
	    }
	    // Si aucune transition tirable n'a été trouvée, retourne false
	    return false;
	}

	
    /**
     * Exécute toutes les transitions du réseau de Petri qui sont tirables.
     * La fonction continuerATrier permet de vérifier s'il y a au moins une transition tirable
     */

	@Override
	public void fireAll(){
	
			boolean continuer= this.continuerATirer(transitions);
			while(continuer) {
				for (Transition transition : this.transitions) {
					
					try {
						this.fire(transition);
					} catch (NullTransitionException e) {
						
						e.getMessage();
					} catch (NegativeToken e) {
						
						e.getMessage();
					}
					}
				continuer= this.continuerATirer(transitions);
				}
			
	
		
		
	}
	
    /**
     * Fournit une représentation sous forme de chaîne de caractères du réseau de Petri.
     * 
     * @return La chaîne de caractères représentant le réseau de Petri.
     */

	@Override
	public String toString() {
		String s="";
		for (Transition transition : transitions) {
			//System.out.println(transitions.size());
			//System.out.println(transition.toString() );
			s+=transition.toString() +"\n";
			ArrayList<ArcSortant> arcE= transition.getArcsEntrants();
			ArrayList<ArcEntrant> arcS= transition.getArcsSortants();
			ArrayList<Place> places = new ArrayList<Place>() ;
			for (ArcSortant a : arcE) {
				places.add(a.getPlace());
				
			}
			for (ArcEntrant a : arcS) {
				places.add(a.getPlace());
				
			}
			for (Place place : places) {
				s+=place.toString()+"\n";
				
			}
			for (ArcSortant a : arcE) {
				s+=a.toString() +"\n";
				
			}
			for (ArcEntrant a : arcS) {
				s+=a.toString() + "\n";
			}
			
			
		}
		return s;
	}

    /**
     * Ajoute des jetons à une place spécifique du réseau de Petri.
     * 
     * @param place La place à laquelle ajouter des jetons.
     * @param jetons Le nombre de jetons à ajouter.
     * @throws NegativeToken Si le nombre de jetons devient négatif.
     * @throws NullPlaceException Si la place est nulle.
     */
	
	@Override
	public void ajouterJetons(Place place, int jetons) throws NegativeToken , NullPlaceException {
		//
		//on doit s'assurer que le reseau le contient bien  : place !!
		// ainsi que pour transi
		if(place!=null) {
			place.setJetons(jetons+place.getJetons());

		}
		else {
			throw new NullPlaceException();
		}
	}
	
    /**
     * Retire des jetons d'une place spécifique du réseau de Petri.
     * 
     * @param place La place à laquelle retirer des jetons.
     * @param jetons Le nombre de jetons à retirer.
     * @throws ExceedExistingToken Si le nombre de jetons à retirer est supérieur au nombre de jetons existants.
     * @throws NullPlaceException Si la place est nulle.
     * @throws NegativeToken Si le nombre de jetons devient négatif après le retrait.
     */
	
	@Override
	public void enleverJetons(Place place , int jetons )  throws ExceedExistingToken , NullPlaceException, NegativeToken{ 
		
		if(place!=null) {
			if(place.getJetons()-jetons<0) {
				throw new ExceedExistingToken();
			}
			else {
				place.setJetons(place.getJetons()-jetons);
			}
			
		}
		else {
			throw new NullPlaceException();
		}
	}
	
	/**
     * Change le poids d'un arc dans le réseau de Petri.
     */

	@Override
	public void changerPoids(Arc arc, int poids ) throws NullTArcException, NegativeWeight  {
		
		if(arc!= null) {
			if(arc instanceof ArcEntrant  ) {
				((ArcEntrant) arc).setPoids(poids);
				
			}
			if( arc instanceof ArcSortantNormal ) {
			((ArcSortantNormal) arc).setPoids(poids);
			}
		}else {
			throw new NullTArcException();
		}
	}

}
