package br.com.fsa.tccrastreamento.app.exception;

public class UsuarioNaoEncontradoException extends Exception {
	
	public UsuarioNaoEncontradoException(String msg)
	{
		super(msg);
	}
	
	public UsuarioNaoEncontradoException() {
		super();
	}

}
