import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class q3{
    public static int hourInput;
    public static int minInput;
    public static int secondsInput;

    public boolean checkValidity(String arrival_time){

        String arrival_time_without_space = arrival_time.replaceAll("\\s", "");

        String[] arrivalSeperate = arrival_time_without_space.split(":");

        int arrivalHours=0;
        int arrivalMinutes=0;
        int arrivalSeconds=0;

        try{
            arrivalHours = Integer.parseInt(arrivalSeperate[0]);
            arrivalMinutes = Integer.parseInt(arrivalSeperate[1]);
            arrivalSeconds = Integer.parseInt(arrivalSeperate[2]);            
        }catch (Exception e){
            return false;
        }

        if((arrivalHours<=23) && (arrivalMinutes<=59) && (arrivalSeconds<=59)){
            return true;
        }else{
            return false;
        }
    }

    public static ArrayList<String> parseValidStops_TimesFile(File stops_timesFile)throws IOException{
        try{
            BufferedReader reader = new BufferedReader(new FileReader(stops_timesFile));
            String string;
            int count =0;
            while((string = reader.readLine())!= null){
                String[] line = string.split(",");
                if(count!=0){
                    int trip_id = -1;
                    int stop_id = -1;
                    int stop_sequence = -1;
                    int stop_headsign = -1;
                    int pickup_type = -1;
                    int drop_off_type = -1;
                    float shape_dist_traveled = (float)-1;

                    String arrival_time = line[1];
                    String departure_time = line[2];

                    if(!line[0].equals("")){
                        trip_id = Integer.parseInt(line[0]);
                    }
                    if(!line[3].equals("")){
                        stop_id = Integer.parseInt(line[3]);
                    }
                    if(!line[4].equals("")){
                        stop_sequence = Integer.parseInt(line[4]);
                    }
                    if(!line[5].equals("")){
                        stop_headsign = Integer.parseInt(line[5]);
                    }
                    if(!line[6].equals("")){
                        pickup_type = Integer.parseInt(line[6]);
                    }
                    if(!line[7].equals("")){
                        drop_off_type = Integer.parseInt(line[7]);
                    }
                    if((line.length==9)&&(!line[8].equals(""))){
                        shape_dist_traveled = Float.parseFloat(line[8]);
                    }
                }
                count++;
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public static void main(String[] args)throws IOException{
        int hourInput = 15;
        int minInput = 15;
        int secondsInput = 15;
        if((hourInput >=23 ) || (minInput >= 59) || (secondsInput >= 59)){
            System.out.println("Invalid Input Times!" + "\n");
            return;
        }

        File stops_timesFile = new File("inputs\\stops_times.txt");
        parseValidStops_TimesFile(stops_timesFile);
    }

    
}  