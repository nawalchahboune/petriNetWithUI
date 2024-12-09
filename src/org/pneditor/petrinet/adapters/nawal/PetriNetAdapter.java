package org.pneditor.petrinet.adapters.nawal;

import java.util.ArrayList;
import java.util.Iterator;

import org.pneditor.petrinet.AbstractArc;
import org.pneditor.petrinet.AbstractNode;
import org.pneditor.petrinet.AbstractPlace;
import org.pneditor.petrinet.AbstractTransition;
import org.pneditor.petrinet.PetriNetInterface;
import org.pneditor.petrinet.ResetArcMultiplicityException;
import org.pneditor.petrinet.UnimplementedCaseException;
import org.pneditor.petrinet.adapters.imta.ArcAdapter;
import org.pneditor.petrinet.models.nawal.src.Exceptions.ExistingArc;
import org.pneditor.petrinet.models.nawal.src.Exceptions.ExistingPlace;
import org.pneditor.petrinet.models.nawal.src.Exceptions.ExistingTransition;
import org.pneditor.petrinet.models.nawal.src.Exceptions.NegativeToken;
import org.pneditor.petrinet.models.nawal.src.Exceptions.NegativeWeight;
import org.pneditor.petrinet.models.nawal.src.Exceptions.NullPlaceException;
import org.pneditor.petrinet.models.nawal.src.Exceptions.NullTArcException;
import org.pneditor.petrinet.models.nawal.src.Exceptions.NullTransitionException;
import org.pneditor.petrinet.models.nawal.src.Metier.Arc;
import org.pneditor.petrinet.models.nawal.src.Metier.ArcEntrant;
import org.pneditor.petrinet.models.nawal.src.Metier.ArcSortant;
import org.pneditor.petrinet.models.nawal.src.Metier.ArcSortantNormal;
import org.pneditor.petrinet.models.nawal.src.Metier.ArcVideur;
import org.pneditor.petrinet.models.nawal.src.Metier.ArcZero;
import org.pneditor.petrinet.models.nawal.src.Metier.Place;
import org.pneditor.petrinet.models.nawal.src.Metier.ReseauPetri;
import org.pneditor.petrinet.models.nawal.src.Metier.Transition;

public class PetriNetAdapter extends PetriNetInterface {
	private ReseauPetri network = new ReseauPetri();
	
	

	@Override
	public AbstractPlace addPlace() {

		//System.out.println("debut simulation");
		Place place= new Place();
		PlaceAdapter placeAdapter= new PlaceAdapter(place);
		try {
			network.ajouter_Place(place);
		} catch (NullPlaceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExistingPlace e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return placeAdapter;
	}

	@Override
	public AbstractTransition addTransition() {

		Transition transition = new Transition();
		TransitionAdapter transitionAdapter = new TransitionAdapter(transition);
		
		try {
			network.ajouter_Tarnsition(transition);
		} catch (NullTransitionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExistingTransition e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return transitionAdapter;
	}

	@Override
	public AbstractArc addRegularArc(AbstractNode source, AbstractNode destination) throws UnimplementedCaseException {
		//System.out.println("initializing an arc");
		ArcEAdapter arcEAdapter;
		ArcSAdapter arcsAdapter;
		AbstractArc aA=null;
		Arc arc= null;
		if(source instanceof AbstractPlace) {
			try {
				 arc= new ArcSortantNormal(1, ((PlaceAdapter)source).getPlace(), ((TransitionAdapter)destination).getTransition());
				 arcsAdapter = new ArcSAdapter(arc);
				 aA=arcsAdapter;
				 try {
					this.network.ajouter_Arc(arc);
				} catch (NullTArcException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (NullPlaceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NegativeWeight e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NullTransitionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExistingArc e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(source instanceof AbstractTransition) {
			try {
				 arc= new ArcEntrant(1, ((PlaceAdapter)destination).getPlace(), ((TransitionAdapter)source).getTransition());
				
				 arcEAdapter = new ArcEAdapter(arc);
				 aA=arcEAdapter;
				 try {
					this.network.ajouter_Arc(arc);
				} catch (NullTArcException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (NullPlaceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NegativeWeight e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NullTransitionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExistingArc e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		//System.out.println("adding arc");
		
		
		return aA ;
	}

	@Override
	public AbstractArc addInhibitoryArc(AbstractPlace place, AbstractTransition transition) {
	ArcSAdapter arcsAdapter;
	AbstractArc aA=null;
	Arc arc= null;
			 try {
				arc= new ArcZero( ((PlaceAdapter)place).getPlace(), ((TransitionAdapter)transition).getTransition());
				try {
					this.network.ajouter_Arc(arc);
				} catch (NullTArcException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (NullPlaceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NullTransitionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExistingArc e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 arcsAdapter = new ArcSAdapter(arc);
			 aA=arcsAdapter;
		
	return aA ;
	}

	@Override
	public AbstractArc addResetArc(AbstractPlace place, AbstractTransition transition) {
	ArcSAdapter arcsAdapter;
	AbstractArc aA=null;
	Arc arc= null;
			 try {
				arc= new ArcVideur( ((PlaceAdapter)place).getPlace(), ((TransitionAdapter)transition).getTransition());
				try {
					this.network.ajouter_Arc(arc);
				} catch (NullTArcException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 } catch (NullPlaceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NullTransitionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExistingArc e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 arcsAdapter = new ArcSAdapter(arc);
			 aA=arcsAdapter;

		
	return aA ;
	}

	@Override
	public void removePlace(AbstractPlace place) {
		//System.out.println("removing place");
		network.supprimer_Place(((PlaceAdapter)place).getPlace());
		}

	@Override
	public void removeTransition(AbstractTransition transition) {
		//System.out.println("removing transition");
		network.supprimer_Tarnsition(((TransitionAdapter)transition).getTransition());
		
		
	}

	@Override
	public void removeArc(AbstractArc arc) {
		
		
		if(arc.getSource() instanceof AbstractPlace) {
			network.supprimer_Arc(((ArcSAdapter)arc).getArc());
			//System.out.println(network.getArcs().size());
			//System.out.println("removing : "+ "regular ? " + ((ArcSAdapter)arc).isRegular() + " / reset ? "+ ((ArcSAdapter)arc).isReset()+ " / inhibitor ? "+ ((ArcSAdapter)arc).isInhibitory());
			//System.out.println(network.getArcs().size());
		}
		if(arc.getSource() instanceof AbstractTransition){
			//System.out.println(network.getArcs().size());
			network.supprimer_Arc(((ArcEAdapter)arc).getArc());
			//System.out.println("removing : "+ (ArcEAdapter)arc);
			//System.out.println(network.getArcs().size());

		}
		
		
	}

	@Override
	public boolean isEnabled(AbstractTransition transition) throws ResetArcMultiplicityException {
		return ((TransitionAdapter)transition).isTirable();
	}

	@Override
	public void fire(AbstractTransition transition) throws ResetArcMultiplicityException {
try
      {
	//System.out.println(this.network.getPlaces());
	Transition t = (((TransitionAdapter)transition).getTransition());
	ArrayList<ArcEntrant > arcsS= t.getArcsSortants();
	ArrayList<ArcSortant > arcsE= t.getArcsEntrants();
	//System.out.println("pour les arcs entrants à la transition ");
	for (ArcSortant arcEntrant : arcsE) {
		//System.out.println(arcEntrant.getPlace().getJetons());
	}
	//System.out.println("pour les arcs sortants de la transition ");
	for (ArcEntrant arcSortant : arcsS) {
		//System.out.println(arcSortant.getPlace().getJetons());
	}
	
	this.network.fire(((TransitionAdapter)transition).getTransition());
	for (ArcSortant arcEntrant : arcsE) {
		//System.out.println(arcEntrant.getPlace().getJetons());
	}
	arcsS= t.getArcsSortants();
	arcsE= t.getArcsEntrants();
	//System.out.println("pour les arcs sortants de la transition ");
	for (ArcEntrant arcSortant : arcsS) {
		//System.out.println(arcSortant.getPlace().getJetons());
	}
			//System.out.println(this.network.getPlaces());
		} catch (NullTransitionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NegativeToken e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
