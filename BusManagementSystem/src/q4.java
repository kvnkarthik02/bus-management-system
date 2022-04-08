import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.xml.transform.SourceLocator; 

public class q4{
    public static void main(String[] args)throws Exception{
        ASCIIArtGenerator artGen = new ASCIIArtGenerator();
        System.out.println();
        artGen.printTextArt("Bus Management System", ASCIIArtGenerator.ART_SIZE_MEDIUM);
        System.out.println();
        

        boolean quit = false;

        Scanner scan= new Scanner(System.in);
        
        System.out.println("Please Wait...");
        while(!quit){
            System.out.println("------------------------------------------------------");
            System.out.println("Welcome to Vancouver's Translink Bus Management System!");
            System.out.println("------------------------------------------------------");
            System.out.println();

            System.out.println("1) Find the shortest path between any two bus stops.");
            System.out.println("2) Search for Bus Stop.");
            System.out.println("3) Search for Bus Trips with Arrival Time");
            System.out.println("4) Quit Program.");
            System.out.println("Choose your option! Enter 1/2/3/4: ");
            int partOption = scan.nextInt();

            switch(partOption){
                case 1: 
                    scan= new Scanner(System.in);
                    System.out.println("Enter the Stop ID of your beginning location: ");
                    int from_stop = scan.nextInt();
                    System.out.println("Enter the Stop ID of your final location: ");
                    int to_stop = scan.nextInt();
                    q1 q1 = new q1(from_stop, to_stop);
                    System.out.println("Thank you for using our services. ");
                    break;

                case 2:
                    scan= new Scanner(System.in);
                    System.out.println("What bus stop are you looking for? : ");
                    String stopSearch = scan.nextLine();
                    q2 q2 = new q2();
                    q2.q2init(stopSearch);
                    break;
                case 3:
                    System.out.println("Enter Input Time in the format of hh/mm/ss");
                    scan = new Scanner(System.in);
                    System.out.println("Enter your hour input: ");
                    String hourInput = scan.nextLine();
                    System.out.println("Enter your minute input: ");
                    String minInput = scan.nextLine();
                    System.out.println("Enter your seconds input: ");
                    String secInput = scan.nextLine();
                    q3 q3 = new q3(hourInput, minInput, secInput);


                    break;
                case 4: 
                    System.out.println("Thank you for using our services.");
                    quit=true;
                    break;
                default:
                    System.out.println("Invalid Input! Try Again.");
                    System.out.println();
                    break;
           }
        }
        System.out.println();
        artGen.printTextArt("GOODBYE!", ASCIIArtGenerator.ART_SIZE_MEDIUM);
        System.out.println();

    }
}