import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.rmi.ConnectIOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Stops {
    public static HashMap<Integer, ArrayList<stopsEdges>> adjacency;
    public static HashMap<Integer, stopsInfo> validDetails;
    public double pathCost;

    Stops(File stops, File transfers) throws IOException {
        adjacency = new HashMap<>();
        validDetails = new HashMap<>();
        parseStopsFile(stops);
        parseTransfersFile(transfers);
    }

    public ArrayList<stopsInfo> getStopsInBetween(ArrayList<Integer> temp) {
        ArrayList<stopsInfo> stopsInBetween = new ArrayList<>();
        System.out.println("Stops in Between: ");
        for (int i = 0; i < temp.size(); i++) {
            stopsInfo info = validDetails.get(i);
            stopsInBetween.add(info);
        }
        return stopsInBetween;
    }

    public void parseStopsFile(File stopsFile) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(stopsFile));
        int count = 0;
        String string;
        try {
            while ((string = reader.readLine()) != null) {
                String[] line = string.split(",");
                if (count != 0) {

                    int stop_id = (line[0].equals("") || line[0].equals(" ")) ? -1 : Integer.parseInt(line[0]);
                    int stop_code = (line[1].equals("") || line[1].equals(" ")) ? -1 : Integer.parseInt(line[1]);
                    String stop_name = (line[2].equals("") || line[2].equals(" ")) ? "" : line[2];
                    String stop_desc = (line[3].equals("") || line[3].equals(" ")) ? "" : line[3];
                    double stop_lat = (line[4].equals("") || line[4].equals(" ")) ? (double)-1 : Double.parseDouble(line[4]);
                    double stop_lon = (line[5].equals("") || line[5].equals(" ")) ? (double)-1 : Double.parseDouble(line[5]);
                    String zone_id = (line[6].equals("") || line[6].equals(" ")) ? "" : line[6];
                    String stop_url = (line[7].equals("") || line[7].equals(" ")) ? "" : line[7];
                    int location_type = (line[8].equals("") || line[8].equals(" ")) ? -1 : Integer.parseInt(line[8]);
                    String parent_station;
                    if(line.length==9){
                        parent_station = "";
                    }else{
                        parent_station = line[9];
                    }
                    stopsInfo stop = new stopsInfo(stop_id, stop_code, stop_name, stop_desc, stop_lat, stop_lon,
                            zone_id, stop_url, location_type, parent_station);
                    adjacency.put(stop.stop_id, new ArrayList<>());
                    validDetails.put(stop.stop_id, stop);
                }
                count++;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("StopsFile Completed");
        reader.close();
    }

    public void parseTransfersFile(File transfersFile) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("inputs\\transfers.txt"));
        int count = 0;
        String string;
        try {
            while ((string = reader.readLine()) != null) {
                String[] line = string.split(",");
                if (count != 0) {
                    int from_stop = (line[0].equals("") || line[0].equals(" ")) ? -1 : Integer.parseInt(line[0]);
                    int to_stop = (line[1].equals("") || line[1].equals(" ")) ? -1 : Integer.parseInt(line[1]);
                    int transferType = (line[2].equals("") || line[2].equals(" ")) ? -1 : Integer.parseInt(line[2]);

                    if (transferType == 0) {
                        adjacency.computeIfAbsent(from_stop, k -> new ArrayList<>());
                        adjacency.computeIfAbsent(to_stop, k -> new ArrayList<>());
                        adjacency.get(from_stop).add(new stopsEdges(to_stop, (double)2));
                    } else if (transferType == 2) {
                        double min_transfer_time = Double.parseDouble(line[3]);
                        double cost = min_transfer_time / 100;
                        newStop(from_stop, to_stop, cost);
                    } else {
                        System.out.println("Invalid Transfer Type");
                    }
                }
                count++;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("TransfersFile Reading Completed");
        reader.close();
    }

    public void newStop(int from_stop, int to_stop, double cost) {
        adjacency.computeIfAbsent(from_stop, k -> new ArrayList<>());
        adjacency.computeIfAbsent(to_stop, k -> new ArrayList<>());
        adjacency.get(from_stop).add(new stopsEdges(to_stop, cost));
    }

    public ArrayList<Integer> getJourneyPath(int fromStop, int toStop) {
        if (adjacency.keySet().contains(fromStop) && adjacency.keySet().contains(toStop)) {
            if (fromStop == toStop) {
                System.out.println("Input stops are the same.");
                return null;
            }
            HashMap<Integer, Double> dist = new HashMap<>(adjacency.size());
            HashMap<Integer, Integer> temp = new HashMap<>(adjacency.size());
            HashSet<Integer> visited = new HashSet<>(adjacency.size());

            for (int i : adjacency.keySet()) {
                dist.put(i, Double.POSITIVE_INFINITY);
                temp.put(i, Integer.MAX_VALUE);
                visited.add(i);
            }

            dist.put(fromStop, (double) 0);
            while (!visited.isEmpty()) {
                int current = Integer.MAX_VALUE;
                double min = Double.POSITIVE_INFINITY;
                for (int i : visited) {
                    double newDist = dist.get(i);
                    if (newDist < min) {
                        min = newDist;
                        current = i;
                    }
                }
                if (current == Integer.MAX_VALUE) {
                    break;
                }
                visited.remove(current);
                if (current == toStop) {
                    break;
                }

                ArrayList<stopsEdges> adjacencyTemp = adjacency.get(current);

                if (adjacencyTemp != null) {
                    for (stopsEdges edge : adjacencyTemp) {
                        if (edge.cost != Double.POSITIVE_INFINITY && dist.get(current) != null) {
                            double edgeDist = dist.get(current) + edge.cost;
                            if (dist.get(edge.stop_id) > edgeDist) {
                                dist.put(edge.stop_id, edgeDist);
                                temp.put(edge.stop_id, current);
                            }
                        }
                    }
                }
            }
            ArrayList<Integer> path = new ArrayList<>();
            int stop = toStop;
            if (temp.get(stop) != null) {
                if (temp.get(stop) != Integer.MAX_VALUE || stop == fromStop) {
                    while (stop != Integer.MAX_VALUE) {
                        path.add(0, stop);
                        stop = temp.get(stop);
                    }
                }
            }
            if (dist.get(toStop) != null) {
                pathCost = dist.get(toStop);
            }
            return path;
        } else {
            System.out.println("Invalid input stops.");
        }
        pathCost = -1;
        return null;
    }

}
