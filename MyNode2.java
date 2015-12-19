import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import jbotsim.Message;
import jbotsim.Node;


public class MyNode2 extends Node{
	HashMap<Integer, Integer> neigh;
	boolean terminated;
	
	@Override
	public void onStart() {
		
		neigh = new HashMap<>();
		sendMyColor();
	}
	
	private void sendMyColor() {
		IdColor m = new IdColor(getID(), getIntColor());
        for (Node node : getNeighbors())
        	send(node, m);
    }
	
	@Override
	public void onMessage(Message message) {
		IdColor ic = ((IdColor) message.getContent());
		if(neigh.containsKey(ic.getId())){
			if(neigh.get( ic.getId()) != ic.getColor() ){
				neigh.remove(ic.getId());
				neigh.put(ic.getId(), ic.getColor());
			}
		}
		else
			neigh.put(ic.getId(), ic.getColor());
	}
	
	@Override
	public void onClock() {
		if(!terminated){
			boolean modifiable = true;
			ArrayList<Integer> colorNeigh = new ArrayList<Integer>();
			for(Entry<Integer, Integer> ic : neigh.entrySet()){
				if(ic.getKey() > getID()){
					if(ic.getValue() == -1)
						modifiable = false;
					else
						colorNeigh.add(ic.getValue());
				}
				else{
					if(ic.getValue()!= -1);
						colorNeigh.add(ic.getValue());
				}
					
			}
			
			if(modifiable){
				setIntColor(Helper.firstFree(colorNeigh));
				terminated = true;
			}
			sendMyColor();	
			
		}		
	}
}
