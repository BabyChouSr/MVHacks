package mvhacks;

public class EndParenthesisException extends Exception {
	public EndParenthesisException() {
		super("Parentheses mismatch. ");
	}
}
