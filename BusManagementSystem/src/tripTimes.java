import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOError;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;

public class tripTimes {
public ArrayList<tripInfo> validInfo;

    public tripTimes(File stop_timesFile) throws IOException {
        parseStop_TimesFile(stop_timesFile);
    }

    public void parseStop_TimesFile(File stop_timesFile) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("inputs\stop_times.txt"));
        try{
            String string;
            int count =0;
            while((string = reader.readLine())!=null){
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
                        shape_dist_traveled = Integer.parseInt(line[8]);
                    }
                    
                    if(checkValidity(arrival_time, departure_time)){
                        validInfo.add(new tripInfo(trip_id, arrival_time, departure_time, stop_id, stop_sequence, stop_headsign, pickup_type, drop_off_type, shape_dist_traveled));

                    }
                }
                count++;
            }
        }catch (Exception e){
            System.out.println(e);
        }
        System.out.println("StopTimes File Reading Completed");
    }

    public boolean checkValidity(String arrival_time, String departure_time){
        boolean valid = false;

        String arrival_time_without_space = arrival_time.replaceAll("\\s", "");
        String departure_time_without_space = departure_time.replaceAll("\\s", "");

        String[] arrivalSeperate = arrival_time_without_space.split(":");
        String[] departureSeperate = departure_time_without_space.split(":");

        int arrivalHours=0;
        int arrivalMinutes=0;
        int arrivalSeconds=0;
        int departureHours=0;
        int departureMinutes=0;
        int departureSeconds=0;

        try{
            arrivalHours = Integer.parseInt(arrivalSeperate[0]);
            arrivalMinutes = Integer.parseInt(arrivalSeperate[1]);
            arrivalSeconds = Integer.parseInt(arrivalSeperate[2]);
    
            departureHours = Integer.parseInt(arrivalSeperate[0]);
            departureMinutes = Integer.parseInt(arrivalSeperate[1]);
            departureSeconds = Integer.parseInt(arrivalSeperate[2]);
            
        }catch (Exception e){
            valid = false;
            return valid;
        }

        if((arrivalHours<=23) && (departureHours<=23) && (arrivalMinutes<=59) && (departureMinutes<=59) && (arrivalSeconds<=59) && (departureSeconds<=59)){
            valid = true;
        }

        return valid;
    }


}
