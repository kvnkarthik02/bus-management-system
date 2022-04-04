import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class q2 {
    public static ArrayList<String> stopNames = new ArrayList<String>();
    public static TST tst = new TST();

    public static String manipulateStopName(String stop_name) {
        String flag = stop_name.substring(0, 2).strip();
        if (flag.equals("WB") || flag.equals("NB") || flag.equals("SB") || flag.equals("EB")) {
            String temp = stop_name.substring(3);
            String temp2 = stop_name.substring(0, 2);
            String manipStopName = temp.concat(" ").concat(temp2);
            return manipulateStopName(manipStopName);
        }
        return stop_name;
    }

    public static void main(String[] args) throws IOException {

        File stopsFile = new File("inputs\\stops.txt");
        BufferedReader reader = new BufferedReader(new FileReader(stopsFile));
        String string;

        while ((string = reader.readLine()) != null) {
            String[] stopsFileHeaders = string.split(",");
            if (!stopsFileHeaders[2].equals("stop_name")) {
                String manipStopName = manipulateStopName(stopsFileHeaders[2]);
                stopNames.add(manipStopName);
            }
        }
        reader.close();

        for (String stopName : stopNames) {
            tst.insert(stopName);
        }

        String[] search = tst.search("HASTINGS");
        for (String x : search)
            System.out.println(x);
        System.out.println("Length " + search.length);

    }
}
