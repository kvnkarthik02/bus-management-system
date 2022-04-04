import java.lang.reflect.Array;
import java.util.ArrayList;
import org.w3c.dom.Node;


public class TST {
    private node root;
    private ArrayList<String> al;

    public TST(){
        root = null;
    }

    public void insert(String stopName){
        root = insert(root, stopName.toCharArray(), 0);
    }

    public node insert(node root, char[] stopName, int count){
        if(root == null){
            root = new node(stopName[count]);
        }

        if(stopName[count] < root.c){
            root.left = insert(root.left, stopName, count);
        }else if(stopName[count]> root.c){
            root.right = insert(root.right, stopName, count);
        }else{
            if(count+1 < stopName.length){
                root.mid = insert(root.mid, stopName, count+1);
            }
            else{
                root.bool = true;
            }
        }

        return root;
    }


    public void delete(String stopName){
        delete(root, stopName.toCharArray(), 0);
    }

    public void delete(node root, char[] stopName, int count){
        if(root == null){
            return;
        }
        if(stopName[count]<root.c){
            delete(root.left, stopName, count);
        }else if(stopName[count]>root.c){
            delete(root.right, stopName, count);
        }else{
            if((root.bool==true) && (count == stopName.length -1)){
                root.bool = false;
            }else if(count +1 >stopName.length){
                delete(root.mid, stopName, count);
            }
        }
    }
     

    public String[] search(String stopName){
        try{
            if(stopName == null){
                return new String[0];
            }
        }catch (Exception e){
            System.out.println("Invalid! Searching for Null.");
        }

        StringBuilder builder = new StringBuilder();
        node prefix = searchPrefix(root, stopName.toCharArray(), 0);
        find(prefix, "", builder, stopName);
        if(builder.length()<1){
            System.out.println("No String Found!");
            return new String[0];
        }
        return builder.toString().split("\n");
    }

    public String toString(){
        al = new ArrayList<String>();
        travelTree(root, "");
        return "\n TST: " + al;
    }


    private void travelTree(node root, String string) {
        if(root != null){
            travelTree(root.left, string);

            string+= root.c;
            if(root.bool){
                al.add(string);
            }
            travelTree(root.mid, string);
            string = string.substring(0, string.length()-1);
            travelTree(root.right, string);
        }
    }

    public node searchPrefix(node root, char[] stopNames, int count){

        if(root == null){
            root = new node(stopNames[count]);
        }
        if(stopNames[count] < root.c){
            return searchPrefix(root.left, stopNames, count);
        }else if(stopNames[count]> root.c){
            return searchPrefix(root.right, stopNames, count);
        }else{
            if(count < stopNames.length - 1){
                return root;
            }
            else{
                return searchPrefix(root.mid, stopNames, count+1);
            }
        }

    }

    public void find (node node, String string, StringBuilder builder, String stopName){
        if(node!= null){
            find(node.left,string, builder, stopName);
            string+=node.c;
            if(node.bool==true){
                if(stopName.length()==1){
                    if(stopName.equals(string.substring(0,1))){
                        builder.append(stopName + string.substring(1) + "\n");
                    }
                }else{
                    builder.append(stopName + string.substring(1) + "\n");
                }
            }
            find(node.mid, string, builder, stopName);
            string = string.substring(0, string.length() - 1);
            find(node.right, string, builder, stopName);
        }
    }

}
