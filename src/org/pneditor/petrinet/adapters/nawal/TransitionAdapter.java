package org.pneditor.petrinet.adapters.nawal;

import org.pneditor.petrinet.AbstractTransition;
import org.pneditor.petrinet.models.nawal.src.Metier.Arc;
import org.pneditor.petrinet.models.nawal.src.Metier.ArcSortant;
import org.pneditor.petrinet.models.nawal.src.Metier.Transition;

public class TransitionAdapter extends AbstractTransition {
	private Transition transition;

	public TransitionAdapter(String label) {
		super(label);
	}
	

	


	public TransitionAdapter(String label, Transition transition) {
		super(label);
		this.transition = transition;
	}
	public TransitionAdapter( Transition transition) {
		super("");
		this.transition = transition;
	}

	public Transition getTransition() {
		return transition;
	}
	public boolean isTirable() {
		//System.out.println("à un moment donnée j'aurai besoin de la tirabilité de la transition ! : "+ this.transition.isTirable());
		for (ArcSortant arc : this.getTransition().getArcsEntrants()){
			
		//System.out.println("la tirabilité des arcs entrants :"+arc.arcIsFireable());
			
		}
		
		return this.transition.isTirable();
	}
	
	

	

}
