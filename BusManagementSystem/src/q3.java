import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

import javax.xml.transform.SourceLocator;


public class q3{
    public static int hourInput;
    public static int minInput;
    public static int secondsInput;
    public static ArrayList<tripInfo> validInfo;

    public static boolean checkValidity(String time){

        String time_without_space = time.replaceAll("\\s", "");

        String[] timeSeperate = time_without_space.split(":");

        int Hours=0;
        int Minutes=0;
        int Seconds=0;

        try{
            Hours = Integer.parseInt(timeSeperate[0]);
            Minutes = Integer.parseInt(timeSeperate[1]);
            Seconds = Integer.parseInt(timeSeperate[2]);            
        }catch (Exception e){
            return false;
        }

        if((Hours<=23) && (Minutes<=59) && (Seconds<=59)){
            return true;
        }else{
            return false;
        }
    }

    public static ArrayList<tripInfo> parseValidStops_TimesFile(File stops_timesFile, String hourInput2, String minInput2, String secondsInput2)throws IOException{
        ArrayList<tripInfo> validDetails = new ArrayList<tripInfo>();
        BufferedReader reader = new BufferedReader(new FileReader("inputs\\stop_times.txt"));
        try{
            String string;
            int count =0;
            while((string = reader.readLine()) != null){
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
                    
                    if(checkValidity(arrival_time) &&  checkValidity(departure_time)){
                        validDetails.add(new tripInfo(trip_id, arrival_time, departure_time, stop_id, stop_sequence, stop_headsign, pickup_type, drop_off_type, shape_dist_traveled));

                    }
                }
                count++;
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return validDetails;
    }

    public static String[][] sort(String[][] map){
        boolean bool = true;
        String[] temp;
        while(!bool){
            bool = true;
            for(int i=0;i<map.length -1;i++){
                int one = Integer.parseInt(map[i][0]);
                int two = Integer.parseInt(map[i+1][0]);
                if(one > two){
                    temp = map[i];
                    map[i] = map[i+1];
                    map[i+1] = temp;
                    bool = false;
                }
            }
        }
        return map;
    }

    public static ArrayList<tripInfo> searchArrayList(ArrayList<tripInfo> validInfo, String hourInput, String minInput, String secondsInput){
        ArrayList<tripInfo> listWithCorrectTime = new ArrayList<tripInfo>();
        for(int i=0;i<validInfo.size();i++){
            tripInfo temp = validInfo.get(i);
            String timeString = temp.arrival_time;
            timeString = timeString.replaceAll(" ", "");
            String[] timeStringArr = timeString.split(":");
            if(timeStringArr[0].equals(hourInput) && timeStringArr[1].equals(minInput) && timeStringArr[2].equals(secondsInput)){
                listWithCorrectTime.add(temp);
            }
        }        
        return listWithCorrectTime;
    }

    public static ArrayList<tripInfo> sort(ArrayList<tripInfo> validInfo){
        boolean bool = false;
        while(!bool){
            bool = true;
            for(int i=0;i<validInfo.size()-1;i++){
                int stopID1 = validInfo.get(i).stop_id;
                int stopID2 = validInfo.get(i+1).stop_id;
                if(stopID1>stopID2){
                    Collections.swap(validInfo, i, i+1);
                    bool = false;
                }
            }
        }
        return validInfo;
    }

    public static void main(String[] args)throws IOException{
        String hourInput = "17";
        String minInput = "18";
        String secondsInput = "19";
        String time = hourInput + ":" + minInput + ":" + secondsInput;
        System.out.println("Your Input Time: "+time);


        File stops_timesFile = new File("inputs\\stop_times.txt");
        validInfo = parseValidStops_TimesFile(stops_timesFile, hourInput, minInput, secondsInput);
        validInfo = searchArrayList(validInfo, hourInput, minInput, secondsInput);
        ArrayList<tripInfo> sortedResults = sort(validInfo);

        System.out.println("Search results are as follows: " + "\n");
        // System.out.println(sortedResults.size());

        for(int i=0;i<sortedResults.size();i++){
            System.out.println("--------------------------------------------");
            System.out.println(i+1);
            System.out.println("StopID: "+sortedResults.get(i).stop_id);
            System.out.println("Arrival Time: "+ sortedResults.get(i).arrival_time);
        }
        System.out.println("--------------------------------------------");

    }

    
}  


