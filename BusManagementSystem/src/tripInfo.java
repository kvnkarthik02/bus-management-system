public class tripInfo {
    int trip_id;
    String arrival_time;
    String departure_time;
    int stop_id;
    int stop_sequence;
    int stop_headsign;
    int pickup_type;
    int drop_off_type;
    float shape_dist_traveled;

    tripInfo(int trip_id,String arrival_time, String departure_time,int stop_id,int stop_sequence,int stop_headsign,int pickup_type,int drop_off_type,float shape_dist_traveled){
        this.trip_id=trip_id;
        this.stop_id = stop_id;
        this.stop_sequence = stop_sequence;
        this.stop_headsign = stop_headsign;
        this.pickup_type = pickup_type;
        this.drop_off_type = drop_off_type;
        this.shape_dist_traveled = shape_dist_traveled;
        this.arrival_time = arrival_time;
        this.departure_time = departure_time;
    }

    public void printInfo(){
        System.out.println("Trip_ID: " + trip_id +" Arrival_Time: "+ arrival_time + " Departure_Time: " + departure_time + " Stop_ID: " + stop_id + " Stop_Sequence: " + stop_sequence + " Stop_Headsign: " + stop_headsign + " Pickup_Type: " + pickup_type + " Drop_Off_Type: "+drop_off_type + " Shape_Dist_Traveled: "+ shape_dist_traveled);
    }
}
