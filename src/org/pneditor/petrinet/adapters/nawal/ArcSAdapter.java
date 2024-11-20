package org.pneditor.petrinet.adapters.nawal;

import org.pneditor.petrinet.AbstractArc;
import org.pneditor.petrinet.AbstractNode;
import org.pneditor.petrinet.ResetArcMultiplicityException;
import org.pneditor.petrinet.models.nawal.src.Metier.Arc;

public class ArcSAdapter extends AbstractArc {
	
	private Arc arc;
	
	public ArcSAdapter(Arc arc) {
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
		return false;
	}

	@Override
	public boolean isInhibitory() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getMultiplicity() throws ResetArcMultiplicityException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setMultiplicity(int multiplicity) throws ResetArcMultiplicityException {
		// TODO Auto-generated method stub
		
	}

	public Arc getArc() {
		return arc;
	}
	

}
