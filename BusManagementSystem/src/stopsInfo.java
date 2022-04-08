import java.util.zip.ZipEntry;

public class stopsInfo {
    int stop_id;
    int stop_code;
    String stop_name;
    String stop_desc;
    double stop_lat;
    double stop_lon;
    String zone_id;
    String stop_url;
    int location_type;
    String parent_station;

    stopsInfo(int stop_id, int stop_code, String stop_name, String stop_desc, double stop_lat, double stop_lon, String zone_id, String stop_url, int location_type, String parent_station){
        this.stop_id = stop_id;
        this.stop_code = stop_code;
        this.stop_name = stop_name;
        this.stop_desc = stop_desc;
        this.stop_lat = stop_lat;
        this.stop_lon = stop_lat;
        this.zone_id = zone_id;
        this.stop_url = stop_url;
        this.location_type = location_type;
        this.parent_station = parent_station;
    }

    public void printInfo(){
        System.out.println("Stop_ID: " + stop_id + ", Stop_Code: " + stop_code + ", Stop_Name: " + stop_name + ", Stop_Desc: "
        + stop_desc + ", Stop_Lat: " + stop_lat + ", Stop_Lon: " + stop_lon + ", Zone_ID: " + zone_id + ", Stop_URL: "
        + stop_url + ", Location_Type: " + location_type + ", Parent_Station: " + parent_station);
    }
}
