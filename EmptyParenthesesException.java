package mvhacks;

public class EmptyParenthesesException extends Exception {
	public EmptyParenthesesException() {
		super("Parentheses cannot be empty. ");
	}
}
