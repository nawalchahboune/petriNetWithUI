package org.pneditor.petrinet.adapters.nawal;

import org.pneditor.petrinet.AbstractPlace;
import org.pneditor.petrinet.models.nawal.src.Exceptions.NegativeToken;
import org.pneditor.petrinet.models.nawal.src.Metier.Place;

public class PlaceAdapter extends AbstractPlace {
	private Place place;
	
	public PlaceAdapter(String label) {
		super(label);
		// TODO Auto-generated constructor stub
	}
	

	public PlaceAdapter(String label, Place place) {
		super(label);
		this.place = place;
	}
	public PlaceAdapter( Place place) {
		super("");
		this.place = place;
	}
	


	@Override
	public void addToken() {
		try {
			this.place.setJetons(this.place.getJetons()+1);
		} catch (NegativeToken e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void removeToken() {
		try {
			this.place.setJetons(this.place.getJetons()-1);
		} catch (NegativeToken e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Override
	public int getTokens() {
		return this.place.getJetons();
	}

	@Override
	public void setTokens(int tokens) {
		
		
	}


	public Place getPlace() {
		return place;
	}
	

}
