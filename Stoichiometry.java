import java.util.Scanner;
/**
 * Write a description of class Stochiometry here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Stoichiometry
{
    public static void main (String[] args){
        System.out.print("\f");
        Scanner in = new Scanner(System.in);
        System.out.println("Plug in an equation in the format A + B -> AB and this calculator will give the Limiting Reactant and # of mol of product formed");
        boolean still = false;
        String[] reactants = new String[1000];
        double[] molarMass = new double[1000];
        int[] ratio = new int[1000];
        double[] mol = new double[1000];
        int i = 0;
        while (!still) {
            System.out.println("What is the Reactant? (Type 'Quit' when Done): ");
            String str1 = in.next();
            str1=str1.toUpperCase();
            if(str1.equals("QUIT")) { break;}
            else {reactants[i]=str1;}
            //System.out.println("What is the Molar Mass of that Reactant? ");
            //double str2 = in.nextDouble();
            //molarMass[i] = str2;
            System.out.println("What is the coefficient of the Reactant(6CO2 is 6)? ");
            int str3 = in.nextInt();
            ratio[i] = str3;
            System.out.println("How many moles of this reactant do you have?");
            double str4 = in.nextDouble();
            mol[i] = str4;
            i++;
        }
        int limit = Integer.MAX_VALUE;
        double min = Integer.MAX_VALUE;
        for(int j = 0; j < reactants.length;j++){           
            if(mol[j]/ratio[j] < min){
                limit = j;
                min = mol[j]/ratio[j];
            }
        }
        String[] products = new String[1000];
        int[]ratio2 = new int[1000];
        double[]mass2 = new double[1000];
        Boolean done = false;
        int k = 0;
        while(!done){
            System.out.println("What is the Product? (Type ' Quit' when Done)");
            String prod = in.next();
            prod = prod.toUpperCase();
            if(prod.equals("QUIT")){break;}
            else {products[k] = prod;}
            System.out.println("What is the Coefficient of the Product?");
            int rat= in.nextInt();
            ratio2[k] = rat;
            System.out.println("What is the molar mass of the product?");
            double prodMass = in.nextDouble();
            mass2[k] = prodMass;
            k++;
        }
        double sum = 0;
        double sumMass = 0;
        System.out.println("Limiting Reactant: "+reactants[limit]);     
        for(int l = 0; l < products.length; l++){
            if(products[l] == null) {break;}
            double prodFormed = mol[limit]/ratio[limit] * ratio2[l];
            System.out.println("Amount of "+products[l]+ " formed " + prodFormed+ "mol\nMass of "+products[l]+ " formed: "+prodFormed *mass2[l]+"g");
            sum += prodFormed;
            sumMass += (prodFormed * mass2[l]);
        } 
        System.out.println("Total Amount of Product Formed: "+sum+"mol");     
        System.out.println("Total Mass of Product Formed: "+sumMass+ "g");
    }
}
