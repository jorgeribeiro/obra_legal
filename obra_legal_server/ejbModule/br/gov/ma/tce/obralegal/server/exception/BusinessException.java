package br.gov.ma.tce.obralegal.server.exception;

public class BusinessException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public BusinessException() {

	}

	public BusinessException(String mensagem) {
		super(mensagem);
	}

	public BusinessException(String mensagem, Throwable cause) {
		super(mensagem, cause);
	}

}
