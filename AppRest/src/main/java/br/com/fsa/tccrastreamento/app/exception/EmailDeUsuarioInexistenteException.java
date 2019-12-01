package br.com.fsa.tccrastreamento.app.exception;

public class EmailDeUsuarioInexistenteException extends RuntimeException {
	
	public EmailDeUsuarioInexistenteException () 
	{
		super();
	}
	
	public EmailDeUsuarioInexistenteException(String msg)
	{
		super(msg);
	}

}
