package org.pneditor.petrinet.adapters.nawal;

import org.pneditor.petrinet.AbstractArc;
import org.pneditor.petrinet.AbstractNode;
import org.pneditor.petrinet.ResetArcMultiplicityException;
import org.pneditor.petrinet.models.nawal.src.Exceptions.NegativeWeight;
import org.pneditor.petrinet.models.nawal.src.Metier.Arc;
import org.pneditor.petrinet.models.nawal.src.Metier.ArcSortantNormal;
import org.pneditor.petrinet.models.nawal.src.Metier.ArcVideur;
import org.pneditor.petrinet.models.nawal.src.Metier.ArcZero;

public class ArcSAdapter extends AbstractArc {
	
	private Arc arc;
	
	public ArcSAdapter(Arc arc) {
		super();
		this.arc = arc;
	}

	@Override
	public AbstractNode getSource() {

		PlaceAdapter pA= new PlaceAdapter(this.arc.getPlace());
		return pA;
	}

	@Override
	public AbstractNode getDestination() {
		TransitionAdapter tA= new TransitionAdapter(arc.getTransition());
		return tA;
	}

	@Override
	public boolean isReset() {
		if(arc instanceof ArcVideur) {

			//System.out.println("reset : true");
			return true;
		}
		//System.out.println("reset : false");
		return false ;
	}

	@Override
	public boolean isRegular() {
		
		if(arc instanceof ArcSortantNormal) {
			//System.out.println("regular : true");
			return true; 
		}
		//System.out.println("regular : false");
		return false;
	}

	@Override
	public boolean isInhibitory() {
		if(arc instanceof ArcZero) {

			//System.out.println("inhibitory : true ");
			return true;
		}

		//System.out.println("inhibitory :false ");
		return false;
	}

	@Override
	public int getMultiplicity() throws ResetArcMultiplicityException {
		int multiplicity=0;
		if(this.arc instanceof ArcSortantNormal) {
			try {
				multiplicity= ((ArcSortantNormal)arc).getPoids();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return multiplicity;
	}

	@Override
	public void setMultiplicity(int multiplicity) throws ResetArcMultiplicityException {
		if(this.arc instanceof ArcSortantNormal) {
			try {
				((ArcSortantNormal)arc).setPoids(multiplicity);
			} catch (NegativeWeight e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}

	public Arc getArc() {
		return arc;
	}

	@Override
	public boolean isSourceAPlace() {
		return true;
	}
	

}
