

import jbotsim.Node;
import jbotsim.Topology;
import jbotsim.ui.JViewer;
import jbotsimx.dot.Import;

public class MainClass {
    public static void main(String[] args) {
//    	  Topology tp = new Topology(false); // do not start automatically
//          tp.setDefaultNodeModel(MyNode.class);
//          //Import.importGraph(tp, "/home/acasteig/test.dot", 2);
//          jbotsimx.topology.TopologyGenerator.generateRing(tp,10);
//          tp.shuffleNodeIds();
//          Helper.setParentRelations(tp);
//          JViewer jv = new JViewer(tp);
//          jv.getJTopology().addLinkPainter(new MyPainter());
//          tp.start(); // pour initialiser les noeuds via onStart()
//         // if(((MyNode)tp.getNodes().get(0)).terminated)
//              tp.pause(); // pour permettre le step by step
//            
//          boolean end=false;
//          while(!end) {
//              end=true;
//              MyNode n = ((MyNode) tp.getNodes().get(0));
//              end &= (n.terminated2);
//          }
//
//          if(end) {
//              System.out.println("Nb colors :" + Helper.nbColors(tp) + ", checkResult: " + (Helper.checkResult(tp)?"\u001B[32m" : "\u001B[31m") + Helper.checkResult(tp));
//          }
        
        /**************/
    	
    	  Topology tp = new Topology(false); // do not start automatically
          tp.setDefaultNodeModel(MyNode2.class);
          //Import.importGraph(tp, "/home/acasteig/test.dot", 2);
          jbotsimx.topology.TopologyGenerator.generateGrid(tp, 10);
          tp.shuffleNodeIds();
          Helper.setSize(tp.getNodes().size());
          JViewer jv = new JViewer(tp);
       
          tp.start(); // pour initialiser les noeuds via onStart()
         // if(((MyNode)tp.getNodes().get(0)).terminated)
              tp.pause(); // pour permettre le step by step
            
          boolean end=false;
          while(!end) {
              end=true;
              for(Node n : tp.getNodes())
            	  end &= ((MyNode2)n).terminated;
          }

          if(end) {
              System.out.println("Nb colors :" + Helper.nbColors(tp) + ", checkResult: " + (Helper.checkResult(tp)?"\u001B[32m" : "\u001B[31m") + Helper.checkResult(tp));
          }
    }
}
