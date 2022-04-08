import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.spi.ToolProvider;

public class q1 {
    public static Stops stops;
    public static tripTimes tripTimes;
    int from_stop;
    int to_stop;

    q1(int from_stop, int to_stop) throws IOException{
        this.from_stop = from_stop;
        this.to_stop = to_stop;

        File stop_times_File = new File("inputs\\stop_times.txt");
        File stops_File = new File("inputs\\stops.txt");
        File transfers_File = new File("inputs\\transfers.txt");

        q1GraphSetup(stop_times_File, stops_File, transfers_File);

        if(from_stop == to_stop){
            System.out.println("From and To Locations are both the same stops!");
            return;
        }

        ArrayList<Integer> temp = stops.getJourneyPath(from_stop, to_stop);
        double cost = stops.pathCost;

        if (cost == Double.POSITIVE_INFINITY) {
            System.out.println("Route Doesn't Exist between these routes");
        } else {
            System.out.println("Cost to travel between these stops: " + cost);
            ArrayList<stopsInfo> stopsInBetween = stops.getStopsInBetween(temp);
            for (int i = 0; i < temp.size(); i++) {
                System.out.println(temp.get(i));
                if (i != temp.size() - 1) {
                    System.out.println(" -> ");
                }
            }
            System.out.println();
        }

    }

    public static void q1GraphSetup(File stop_timesFile, File stopsFile, File transfersFile) throws IOException {
        System.out.println("--------------------------------------------------------------------");
        System.out.println("Graph Setup");
        System.out.println("--------------------------------------------------------------------");

        stops = new Stops(stopsFile, transfersFile);
        tripTimes = new tripTimes(stop_timesFile);

        for (int i = 1; i < tripTimes.validInfo.size(); i++) {
            tripInfo trip = tripTimes.validInfo.get(i - 1);
            tripInfo trip2 = tripTimes.validInfo.get(i);
            stops.newStop(trip.stop_id, trip2.stop_id, 1);
        }

        System.out.println("Graph Setup Complete.");
    }

    // public static void main(String[] args) throws Exception {
    //     ASCIIArtGenerator artGen = new ASCIIArtGenerator();
    //     System.out.println();
    //     artGen.printTextArt("Bus Management System", ASCIIArtGenerator.ART_SIZE_MEDIUM);
    //     System.out.println();


    //     File stop_times_File = new File("inputs\\stop_times.txt");
    //     File stops_File = new File("inputs\\stops.txt");
    //     File transfers_File = new File("inputs\\transfers.txt");

    //     q1GraphSetup(stop_times_File, stops_File, transfers_File);



    //     if(from_stop == to_stop){
    //         System.out.println("From and To Locations are both the same stops!");
    //         return;
    //     }

    //     ArrayList<Integer> temp = stops.getJourneyPath(from_stop, to_stop);
    //     double cost = stops.pathCost;

    //     if (cost == Double.POSITIVE_INFINITY) {
    //         System.out.println("Route Doesn't Exist between these routes");
    //     } else {
    //         System.out.println("Cost to travel between these stops: " + cost);
    //         ArrayList<stopsInfo> stopsInBetween = stops.getStopsInBetween(temp);
    //         for (int i = 0; i < temp.size(); i++) {
    //             System.out.println(temp.get(i));
    //             if (i != temp.size() - 1) {
    //                 System.out.println(" -> ");
    //             }
    //         }
    //         System.out.println();
    //     }
    // }
}