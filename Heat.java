import java.util.Scanner;
/**
 * Write a description of class Heat here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Heat
{    // instance variables - replace the example below with your own
    public static void main(String[]args){
        System.out.print("\f");
        Scanner in = new Scanner(System.in);
        System.out.println("What is the mass of your solution?");
        double mass = in.nextDouble();
        System.out.println("What is the starting temperature?");
        double t1 = in.nextDouble();
        System.out.println("What is the ending temperature?");
        double t2 = in.nextDouble();     
        System.out.println("What is the specific heat capacity of the solution?");
        double heat = in.nextDouble();
        double enthalpy = Math.round(mass * (t2-t1) * heat*100.0)/100.0;
        System.out.println("The Enthalpy Change is: "+enthalpy+"J/mol");
    }
}
