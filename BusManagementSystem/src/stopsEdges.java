import javax.print.StreamPrintService;

public class stopsEdges {
    public int stop_id;
    public double cost;

    public stopsEdges(int stop_id, double cost){
        this.stop_id = stop_id;
        this.cost = cost;
    }

    public void printInfo(){
        System.out.println("Stop_ID: "+stop_id+" Cost: "+cost);
    }
}
