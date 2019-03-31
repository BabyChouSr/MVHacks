package mvhacks;

import java.util.ArrayList;
import java.util.Scanner;

public class ExpressionChecker {
	private static final char NULL = (char)0;
	
	private static char[] legalChars = {'+', '-', '*', '/', '^', '!', '.'};
	
	static String expression;
	static ArrayList<Character> vars = new ArrayList<Character>(0);
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		System.out.println("Enter an expression with one or two variables (use x, y, and z):");
		expression = in.nextLine();
		
		//remove spaces from equation
		String temp = "";
		if(expression.charAt(0) == '.')
			temp += '0';
		for(int i = 0; i < expression.length(); i++) {
			if(i > 0) {
				if(expression.charAt(i) == '.' && !(expression.charAt(i-1) >= 48 && expression.charAt(i-1) <= 57))
					temp += '0'; // add 0 before a leading decimal point
			}
			if(expression.charAt(i) != ' ')
				temp += expression.charAt(i);
		}
		expression = temp;
		
		//basically everything after this comment is checking legality
		if( isaSymbol(expression.charAt(0)) || 
				(isaSymbol(expression.charAt(expression.length()-1)) && expression.charAt(expression.length()-1) != '!') ) {
			System.out.println("Syntax error. Terminating program.");
			System.exit(1);
		}
			
		for(int i = 0; i < expression.length(); i++) {
			
			if(i > 0) {
				if( isaSymbol(expression.charAt(i)) && 
						isaSymbol(expression.charAt(i-1)) && expression.charAt(i-1) != '!') {
					System.out.println("Syntax error. Terminating program.");
					System.exit(1);
				}
			}
			
			if( isaLetter(expression.charAt(i)) || expression.charAt(i) == 'i' || expression.charAt(i) == 'e') {
				if(i+1 < expression.length()) {
					if(expression.charAt(i+1) >= 48 && expression.charAt(i+1) <= 57 || 
							expression.charAt(i+1) == '.') { // if a letter is followed by a number or decimal pointa
						System.out.println("Syntax error. Terminating program.");
						System.exit(1);
					}
				}
			}
			if( isaLetter(expression.charAt(i)) && !vars.contains(expression.charAt(i)))
				vars.add(expression.charAt(i)); //sets the variable to the first letter it sees that is not e or i
			
			if( isIllegal(expression.charAt(i))) {
				//if there is a new variable exit the program
				System.out.println("An illegal symbol was included. Terminating program.");
				System.exit(1);
			}
			if(i+1 < expression.length()) {
				if( expression.charAt(i) == '!') {
					if(!isaSymbol(expression.charAt(i+1)) || expression.charAt(i+1) == '.') {
						System.out.println("Syntax error. Terminating program.");
						System.exit(1); //factorial must be followed by a symbol that is not a decimal point
					}
				}
				if( expression.charAt(i) == '.' && !(expression.charAt(i+1) >= 48 && expression.charAt(i+1) <= 57) ) {
					System.out.println("Decimal point must be followed by a digit. Terminating program.");
					System.exit(1);
				}
			}
		}//code identifies the variable and determines if the equation is legal
		
		try {
			checkParentheses(expression);
		}
		catch (EndParenthesisException e) {
			System.out.println(e.getMessage() + "Terminating program.");
			System.exit(1);
		}
		catch (EmptyParenthesesException e) {
			System.out.println(e.getMessage() + "Terminating program.");
			System.exit(1);
		}
		catch (SyntaxError e) {
			System.out.println(e.getMessage() + "Terminating program.");
			System.exit(1);
		}
		
		for(int i = 0; i < vars.size(); i++) {
			Character a = vars.get(i);
			if( !(Equal(a, 'x') || Equal(a, 'y') || Equal(a, 'z')) ) {
				System.out.println("Variables other than x, y, and z are used. Terminating program.");
				System.exit(1);
			}
		}
		
		//if it has passed all of the above tests
		System.out.println("Expression is legal");
		System.out.println(vars);
		System.exit(0);
	}
	
	public static boolean isaLetter(char a) {
		if( a >= 97 && a <= 122 && !(a=='e' || a=='i') ) //exceptions for e and i because they are constants
			return true;
		else if( a >= 65 && a <= 90 )
			return true;
		else
			return false;
	}
	
	public static boolean isaSymbol(char a) { //returns true if a is in the legalChars array
		for(int i = 0; i < legalChars.length; i++) {
			if(a == legalChars[i])
				return true;
		}
		return false;
	}
	public static boolean isIllegal(char a) {
		//returns false if character is legal
		
		if( isaSymbol(a) )
			return false;
		
		if( a == '(' || a == ')')
			return false;
		
		if( isaLetter(a) || a == 'e' || a == 'i') //if it's a letter
			return false;
		
		if(a >= 48 && a <= 57) // if it's a number
			return false;
		
		return true;
	}
		
	public static void checkParentheses(String e) throws EndParenthesisException, EmptyParenthesesException, SyntaxError {
		int p = 0;
		
		for(int i = 0; i < e.length(); i++) {
			if(e.charAt(i) == '(')
				p++;
			else if(e.charAt(i) == ')') {
				p--;
				if(i > 0) {
					if(e.charAt(i-1) == '(')
						throw new EmptyParenthesesException();
				}
			}
			else if(isaSymbol(e.charAt(i)) && e.charAt(i-1) == '(')
				throw new SyntaxError();
			else if(isaSymbol(e.charAt(i)) && e.charAt(i) != '!' && e.charAt(i+1) == ')')
				throw new SyntaxError();
			if(p < 0)
				throw new EndParenthesisException();
		}
		if(p != 0)
			throw new EndParenthesisException();
	}
	
	static boolean Equal(Character a, char b) {
		if(a.equals(new Character(b))) {
			return true;
		}
		return false;
	}
}
