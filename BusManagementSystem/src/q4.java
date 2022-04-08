import java.io.File;
import java.io.IOException;
import java.util.*; 

public class q4{
    public static void main(String[] args)throws Exception{
        ASCIIArtGenerator artGen = new ASCIIArtGenerator();
        System.out.println();
        artGen.printTextArt("Bus Management System", ASCIIArtGenerator.ART_SIZE_MEDIUM);
        System.out.println();
        

        boolean quit = false;

        Scanner scan= new Scanner(System.in);
        System.out.println("------------------------------------------------------");
        System.out.println("Welcome to Vancouver's Translink Bus Management System!");
        System.out.println("------------------------------------------------------");
        System.out.println();

        System.out.println("1) Find the shortest path between any two bus stops.");
        System.out.println("2) Search for Bus Stop with Stop ID.");
        System.out.println("3) Search for Bus Trips with Arrival Time");
        System.out.println("4) Quit Program.");
        System.out.println("Choose your option! Enter 1/2/3: ");
        int partOption = scan.nextInt();
        System.out.println("Please Wait...");
        while(!quit){
            switch(partOption){
                case 1: 
                    
                    System.out.println("Enter the Stop ID of your beginning location: ");
                    int from_stop = scan.nextInt();
                    System.out.println("Enter the Stop ID of your final location: ");
                    int to_stop = scan.nextInt();
                    q1 q1 = new q1(from_stop, to_stop);
                    break;
                default:
                    System.out.println("Invalid Input!");
                    break;
           }
        }
    }
}