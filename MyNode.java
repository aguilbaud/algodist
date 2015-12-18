
import jbotsim.Message;
import jbotsim.Node;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Les arêtes en gras sont dessinées des enfants vers les parents.
 * Attention : les messages envoyés au round r-1 sont reçus au round r
 * via onMessage. Notez que onMessage r s'exécute toujours avant onClock r.
 */
public class MyNode extends Node{
    boolean terminated = false;
    boolean terminated2 = false;
    public Node parent = this; // modifié lors de l'initialisation globale
    int L, L2;
    int y;
    int k_0 = 6;
    List<Integer> neighborColors = new ArrayList<>();

    @Override
    public void onStart() {
        setIntColor(getID());
        L = Helper.log2(getTopology().getNodes().size());
        if (parent == null)
            y = Helper.firstFree(Arrays.asList(getIntColor()));
        sendMyColor();
    }

    private void sendMyColor() {
        for (Node node : getNeighbors())
            if (node != parent)
                send(node, getIntColor());
    }

    private void sendMyColorToAll() {
        for (Node node : getNeighbors())
            send(node, getIntColor());
    }

    @Override
    public void onMessage(Message message) {
        if (! terminated)
            color6_onMessage(message);
        else
            reducePalette_onMessage(message);
    }

    @Override
    public void onClock() {
        if (! terminated) {
            color6_onClock();
        }
        else {
            if(k_0 > 2){//ReducePalette
                reducePalette_onClock();
                k_0--;
            }
            else
                terminated2 = true;
        }
    }

    //*****Algo function****//
    private void color6_onClock(){
        setIntColor(Helper.posdiff(getIntColor(), y));
        L2 = L;
        L = 1 + Helper.log2(L);
        if (L == L2)
            terminated = true;
        else
            sendMyColor();
    }

    private void color6_onMessage(Message message){
        if (message.getSender() == parent)
            y = (Integer) message.getContent();
    }

    private void reducePalette_onClock(){
        System.out.println("DEBUG: Id noeud " + getID() + " color:" + getIntColor() + ", reducePalette(" + k_0 + "," + (k_0-1) +")");
        sendMyColorToAll();
        if(getIntColor() == k_0 )
            setIntColor(Helper.firstFree(neighborColors));
        neighborColors.clear();
    }

    private void reducePalette_onMessage(Message message){
        //System.out.println("DEBUG: MESSAGE Id noeud " + getID() );
        neighborColors.add((Integer) message.getContent());
        System.out.println("DEBUG: MESSAGE Id noeud " + getID() + " content: " + ((Integer) message.getContent()));

    }
}
