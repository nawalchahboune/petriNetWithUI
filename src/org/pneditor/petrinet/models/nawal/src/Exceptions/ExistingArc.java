package org.pneditor.petrinet.models.nawal.src.Exceptions;

public class ExistingArc extends Exception {
	public ExistingArc() {
		super("arc already exists");
	}

}
