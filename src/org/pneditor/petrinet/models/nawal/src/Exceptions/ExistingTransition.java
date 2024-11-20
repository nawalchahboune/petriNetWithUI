package org.pneditor.petrinet.models.nawal.src.Exceptions;

public class ExistingTransition extends Exception {
	public ExistingTransition() {

		super("transition deja existe!");
	}
}
