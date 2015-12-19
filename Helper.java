import jbotsim.Node;
import jbotsim.Topology;

import java.util.*;

/**
 * Created by adrien on 15/12/15.
 */
public class Helper {
    private static int k_0;
    public static void setParentRelations(Topology tp){
        k_0 = tp.getNodes().size();

        List<Node> nodes = tp.getNodes();
        for(Node n : nodes) {
            ((MyNode) n.getNeighbors().get(0)).parent = n;
            ((MyNode)n).parent = n.getNeighbors().get(1);
        }
    }
    public static void setSize(int n){
    	k_0 = n;
    }
    
    public static int firstFree(List<Integer> neigh){
        List<Integer> ints = new ArrayList<Integer>();

        for (int i = 0; i < k_0; i++)
            ints.add(i);
       
        ints.removeAll(neigh);
      
       // System.out.println("DEBUG - Helper.firstFree: IN  " + neigh.toString() + ", OUT " + Collections.min(ints));
        return Collections.min(ints);


    }

    public static int posdiff(Integer x, Integer y){
        for(int i=0; i < Integer.SIZE; i++){
            int x_bit = (x >> i) & 1;
            int y_bit = (y >> i) & 1;
            if ( x_bit != y_bit )
                return 2*i + x_bit;

        }
        return -1;
    }

    public static int log2(Integer x){
        return Integer.SIZE - Integer.numberOfLeadingZeros(x) - 1;
    }

    public static int nbColors(Topology tp){
        List<Node> nodes = tp.getNodes();
        Set<Integer> colors = new HashSet<>();

        for(Node n : nodes){
            colors.add(n.getIntColor());
        }

        System.out.println("DEBUG: Helper.nbColors " + colors.toString());

        return colors.size();
    }

    public static boolean checkResult(Topology tp){
        boolean res = true;
        for(Node n : tp.getNodes()){
            int myColor = n.getIntColor();
            for(Node n2 : n.getNeighbors()) {
                res &= ( n2.getIntColor() != myColor);
            }
        }
        return res;
    }
}
