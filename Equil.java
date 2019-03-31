import java.util.Scanner;
/**
 * Write a description of class Equil here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Equil
{
    public static void main(String[] args ){
        Scanner in = new Scanner(System.in);
        System.out.println("For an Equation of A + B -> AB");
        System.out.println("Give the Kc for the equation: ");
        double kc = in.nextDouble();
        System.out.println("Give starting concentration for reactant A: ");
        double A = in.nextDouble();
        A = Math.round(A *100.0) / 100.0;
        String polynomial = kc+"x^2" + "-" +(kc*2*A+1)+"x+"+kc*A*A;
        //System.out.println(polynomial);
        double b = kc*2*A+1;
        b *= -1;
        double c = kc*A*A;
        //System.out.println(kc);
        //System.out.println(b);
        //System.out.println(c);
        double sol = Math.round(((-1*b) - Math.sqrt(b*b-4*kc*c)) / (2*kc)*100.0)/100.0;
        System.out.println("For the Equation of A + B -> AB");
        System.out.println("          ICE TABLE  ");
        System.out.println("        ----------------");
        System.out.println("        I: " +A+ "   " +A+ "   0"); 
        System.out.println("        C: -x    -x    +x");
        A -= sol;
        A = Math.round(A*100.0)/100.0;
        System.out.println("        E: "+A+" "+A+" "+sol);
    }
}
