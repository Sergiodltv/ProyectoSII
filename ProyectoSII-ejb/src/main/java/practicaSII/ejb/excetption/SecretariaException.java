package practicaSII.ejb.excetption;

public class SecretariaException extends Exception{
    public SecretariaException () {};
	
	public SecretariaException (String message) {
		super(message);
	}
}
