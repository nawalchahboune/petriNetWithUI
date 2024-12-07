package org.pneditor.petrinet.adapters.nawal;

import org.pneditor.petrinet.AbstractArc;
import org.pneditor.petrinet.AbstractNode;
import org.pneditor.petrinet.ResetArcMultiplicityException;
import org.pneditor.petrinet.models.nawal.src.Exceptions.NegativeWeight;
import org.pneditor.petrinet.models.nawal.src.Metier.Arc;
import org.pneditor.petrinet.models.nawal.src.Metier.ArcEntrant;

public class ArcEAdapter extends AbstractArc {
	
	private Arc arc;
	
	public ArcEAdapter(Arc arc) {
		super();
		this.arc = arc;
	}

	@Override
	public AbstractNode getSource() {
		TransitionAdapter tA= new TransitionAdapter(arc.getTransition());
		return tA;
	}

	@Override
	public AbstractNode getDestination() {
		PlaceAdapter pA= new PlaceAdapter(arc.getPlace());
		return pA;
	}

	@Override
	public boolean isReset() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isRegular() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isInhibitory() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getMultiplicity() throws ResetArcMultiplicityException {
		int Multiplicity =((ArcEntrant)arc).getPoids();
		return Multiplicity;
	}

	@Override
	public void setMultiplicity(int multiplicity) throws ResetArcMultiplicityException {
		try {
			((ArcEntrant)arc).setPoids(multiplicity);
		} catch (NegativeWeight e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public Arc getArc() {
		return arc;
	}

	@Override
	public boolean isSourceAPlace() {
		return false;
	}
	

}
