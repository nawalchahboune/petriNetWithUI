package org.pneditor.petrinet.adapters.nawal;

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
import org.pneditor.petrinet.models.nawal.src.Exceptions.NegativeWeight;
import org.pneditor.petrinet.models.nawal.src.Exceptions.NullPlaceException;
import org.pneditor.petrinet.models.nawal.src.Exceptions.NullTransitionException;
import org.pneditor.petrinet.models.nawal.src.Metier.Arc;
import org.pneditor.petrinet.models.nawal.src.Metier.ArcEntrant;
import org.pneditor.petrinet.models.nawal.src.Metier.ArcSortant;
import org.pneditor.petrinet.models.nawal.src.Metier.ArcSortantNormal;
import org.pneditor.petrinet.models.nawal.src.Metier.Place;
import org.pneditor.petrinet.models.nawal.src.Metier.Reseau_Petri;
import org.pneditor.petrinet.models.nawal.src.Metier.Transition;

public class PetriNetAdapter extends PetriNetInterface {
	private Reseau_Petri network = new Reseau_Petri();
	
	

	@Override
	public AbstractPlace addPlace() {

		System.out.println("debut simulation");
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
		System.out.println("initializing an arc");
		ArcEAdapter arcEAdapter;
		ArcSAdapter arcsAdapter;
		AbstractArc aA=null;
		Arc arc= null;
		if(source instanceof AbstractPlace) {
			try {
				 arc= new ArcSortantNormal(0, ((PlaceAdapter)source).getPlace(), ((TransitionAdapter)destination).getTransition());
				 arcsAdapter = new ArcSAdapter(arc);
				 aA=arcsAdapter;
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
		}else {
			try {
				 arc= new ArcEntrant(0, ((PlaceAdapter)destination).getPlace(), ((TransitionAdapter)source).getTransition());
				 arcEAdapter = new ArcEAdapter(arc);
				 aA=arcEAdapter;
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
		System.out.println("adding arc");
		
		
		return aA ;
	}

	@Override
	public AbstractArc addInhibitoryArc(AbstractPlace place, AbstractTransition transition)
			throws UnimplementedCaseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractArc addResetArc(AbstractPlace place, AbstractTransition transition)
			throws UnimplementedCaseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removePlace(AbstractPlace place) {
		System.out.println("removing place");
		network.supprimer_Place(((PlaceAdapter)place).getPlace());
		
	}

	@Override
	public void removeTransition(AbstractTransition transition) {
		System.out.println("removing transition");
		network.supprimer_Tarnsition(((TransitionAdapter)transition).getTransition());
		
	}

	@Override
	public void removeArc(AbstractArc arc) {
		
		if(arc.getSource() instanceof AbstractPlace) {
			network.supprimer_Arc(((ArcSAdapter)arc).getArc());
		}
		if(arc.getSource() instanceof AbstractTransition){
			network.supprimer_Arc(((ArcEAdapter)arc).getArc());
		}
		
	}

	@Override
	public boolean isEnabled(AbstractTransition transition) throws ResetArcMultiplicityException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void fire(AbstractTransition transition) throws ResetArcMultiplicityException {
		// TODO Auto-generated method stub
		
	}
	

}
