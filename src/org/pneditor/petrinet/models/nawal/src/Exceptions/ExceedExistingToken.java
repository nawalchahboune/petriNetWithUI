package org.pneditor.petrinet.models.nawal.src.Exceptions;

public class ExceedExistingToken extends Exception {
	public ExceedExistingToken() {
		super("you try to remove more than what is inside : place");
	}

}
