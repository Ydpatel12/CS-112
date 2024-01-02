package transit;

import java.util.ArrayList;

/**
 * This class contains methods which perform various operations on a layered linked
 * list to simulate transit
 * 
 * @author Ishaan Ivaturi
 * @author Prince Rawal
 */
public class Transit {
	private TNode trainZero; // a reference to the zero node in the train layer

	/* 
	 * Default constructor used by the driver and Autolab. 
	 * DO NOT use in your code.
	 * DO NOT remove from this file
	 */ 
	public Transit() { trainZero = null; }

	/* 
	 * Default constructor used by the driver and Autolab. 
	 * DO NOT use in your code.
	 * DO NOT remove from this file
	 */
	public Transit(TNode tz) { trainZero = tz; }
	
	/*
	 * Getter method for trainZero
	 *
	 * DO NOT remove from this file.
	 */
	public TNode getTrainZero () {
		return trainZero;
	}

	/**
	 * Makes a layered linked list representing the given arrays of train stations, bus
	 * stops, and walking locations. Each layer begins with a location of 0, even though
	 * the arrays don't contain the value 0. Store the zero node in the train layer in
	 * the instance variable trainZero.
	 * 
	 * @param trainStations Int array listing all the train stations
	 * @param busStops Int array listing all the bus stops
	 * @param locations Int array listing all the walking locations (always increments by 1)
	 */
	public void makeList(int[] trainStations, int[] busStops, int[] locations) {

	    // UPDATE THIS METHOD
		int walk = 0;
		int bus = 0;
		int train = 0;

		TNode firstloc = new TNode(0);
		TNode firstBus = new TNode(0, null, firstloc);
		trainZero = new TNode(0, null, firstBus);

		TNode walkLocation;
		TNode busLocation;
		TNode trainLocation; 

		TNode prevTrain = trainZero;


		//WALKING
		for(int loci = 0, busi = 0, traini = 0; loci < locations.length; loci++){
			walk = locations[loci];
			walkLocation = new TNode(walk);

			bus = busStops[busi];
			busLocation = new TNode(bus);

			train = trainStations[traini];
			trainLocation = new TNode(train);
			
			if(firstloc != null){
				firstloc.setNext(walkLocation);
				firstloc = walkLocation;
			}

			if(walk == bus){
				busLocation = new TNode(bus, null, walkLocation);
				if (firstBus != null){
					firstBus.setNext(busLocation);
					firstBus = busLocation;
				 }
				 if(busi < busStops.length-1){
				 busi++;
				 }

				 if(bus == train){
					trainLocation = new TNode(train, null, busLocation);
					if (prevTrain != null){
						prevTrain.setNext(trainLocation);
						prevTrain = trainLocation;
					 }
					 if(traini < trainStations.length-1){
					 traini++;
					 }


				}
			}
		}
	}
	
	/**
	 * Modifies the layered list to remove the given train station but NOT its associated
	 * bus stop or walking location. Do nothing if the train station doesn't exist
	 * 
	 * @param station The location of the train station to remove
	 */
	public void removeTrainStation(int station) {
	    // UPDATE THIS METHOD
		TNode ptr = trainZero.getNext();
		TNode prev = trainZero;
		while(ptr != null){
				if (ptr.getLocation()==station){
						prev.setNext(ptr.getNext());
				}
				prev = ptr;
				ptr = ptr.getNext();
		}
	}

	/**
	 * Modifies the layered list to add a new bus stop at the specified location. Do nothing
	 * if there is no corresponding walking location.
	 * 
	 * @param busStop The location of the bus stop to add
	 */
	public void addBusStop(int busStop) {

	    // UPDATE THIS METHOD
		TNode ptr = trainZero.getDown().getNext();
		TNode prev = trainZero.getDown();
		TNode walkptr = trainZero.getDown().getDown().getNext();
		TNode walkprev = trainZero.getDown().getDown();

		while(ptr != null && ptr.getLocation() != busStop){
				if (ptr.getLocation() < busStop){
					prev = ptr;
					ptr = ptr.getNext();				
				}
				else if(ptr.getLocation() > busStop){
					ptr = prev;
					TNode add = new TNode(busStop, ptr.getNext(), null);
					prev.setNext(add);

					while(walkptr != add && walkptr != null){
						walkprev = walkptr;
						walkptr = walkptr.getNext();
						
						if(walkprev.getLocation() == prev.getNext().getLocation()){
							prev.getNext().setDown(walkprev);
						}


					}
					//break;
				}

			}
	}
	
	/**
	 * Determines the optimal path to get to a given destination in the walking layer, and 
	 * collects all the nodes which are visited in this path into an arraylist. 
	 * 
	 * @param destination An int representing the destination
	 * @return
	 */
	public ArrayList<TNode> To(TNode start, int end){
		ArrayList<TNode> create = new ArrayList<>();

		TNode currLoc = start;

		for(; currLoc!=null && currLoc.getLocation()<=end; currLoc=currLoc.getNext()){
		create.add(currLoc);
		}

		return create;
		}
	public ArrayList<TNode> bestPath(int destination) {
		ArrayList<TNode> path = new ArrayList<>();

		ArrayList<TNode> train=To(trainZero, destination);

		ArrayList<TNode> bus=To(train.get(train.size()-1).getDown(), destination);
		
		ArrayList<TNode> walk=To(bus.get(bus.size()-1).getDown(), destination);


		path.addAll(train);
		path.addAll(bus);
		path.addAll(walk);
		return path;	    // UPDATE THIS METHOD
	}

	/**
	 * Returns a deep copy of the given layered list, which contains exactly the same
	 * locations and connections, but every node is a NEW node.
	 * 
	 * @return A reference to the train zero node of a deep copy
	 */
	public TNode last(TNode head, TNode i){
		TNode newTNode = head;

		while(head.getNext() != null)
			head = head.getNext();

		head.setNext(i);
		return newTNode;
	}

	public TNode find(TNode head, int i){
		while(head != null){
			if(head.getLocation() == i)
				return head;
			head = head.getNext();
		}
		return null;
	}

	public TNode duplicate() {

		TNode currHead = trainZero;

		if(trainZero.getDown().getDown().getDown() == null){
			
			if(trainZero == null){
				return null;
			}

			TNode newHead = new TNode(0, null, new TNode(0, null, new TNode(0, null, null)));
			trainZero = trainZero.getDown().getDown().getNext();
			
			while(trainZero != null){

				int loc = trainZero.getLocation();
				newHead.getDown().setDown(last(newHead.getDown().getDown(), new TNode(loc, null, null)));
				trainZero = trainZero.getNext();

			}

			trainZero = currHead.getDown().getNext();

			while(trainZero != null){
				
				int loc = trainZero.getLocation();
				newHead.setDown(last(newHead.getDown(), new TNode(loc, null, find(newHead.getDown().getDown(), loc))));
				trainZero = trainZero.getNext();

			}

			trainZero = currHead.getNext();
			
			while(trainZero != null){
				int loc = trainZero.getLocation();
				newHead = last(newHead, new TNode(loc, null, find(newHead.getDown(), loc)));
				trainZero = trainZero.getNext();
			}

			trainZero = currHead;
			return newHead;
		}

		TNode newHead = new TNode(0, null, new TNode(0, null, new TNode(0, null, null)));
		trainZero = trainZero.getDown().getDown().getDown().getNext();

		while(trainZero != null){

			int loc = trainZero.getLocation();
			newHead.getDown().getDown().setDown(last(newHead.getDown().getDown().getDown(), new TNode(loc, null, null)));
			trainZero = trainZero.getNext();

		}

		trainZero = currHead;
		return newHead;
	}

	/**
	 * Modifies the given layered list to add a scooter layer in between the bus and
	 * walking layer.
	 * 
	 * @param scooterStops An int array representing where the scooter stops are located
	 */
	public void addScooter(int[] scooterStops) {

		int scooter = 0;

		TNode busZero = trainZero.getDown();
		TNode walkZero = busZero.getDown();
		TNode scooterZero = new TNode(0);
		scooterZero.setDown(walkZero);
		busZero.setDown(scooterZero);

		TNode walkCur = walkZero;
		TNode scooterLocation = null;

		TNode ptr = scooterZero;
		TNode prevScooter = scooterZero;

		for(int i = 0; i < scooterStops.length; i++){
			scooter = scooterStops[i];
			scooterLocation = new TNode(scooter);

			while(walkCur.getLocation() != scooterLocation.getLocation()){
				walkCur = walkCur.getNext();
			}
			if(walkCur.getLocation() == scooterLocation.getLocation()){
				scooterLocation.setDown(walkCur);
			}
				if(prevScooter != null){
					prevScooter.setNext(scooterLocation);
					prevScooter = scooterLocation;
				}
			}
			
			while(scooterZero.getNext() != null){
				if(busZero.getLocation() == ptr.getLocation()){
					busZero.setDown(ptr);
					busZero = busZero.getNext();
					if(busZero == null){
						break;
					}
				}
				if(busZero.getLocation() > ptr.getLocation()){
					ptr = ptr.getNext();
				}
			}
	    // UPDATE THIS METHOD
	}

	/**
	 * Used by the driver to display the layered linked list. 
	 * DO NOT edit.
	 */
	public void printList() {
		// Traverse the starts of the layers, then the layers within
		for (TNode vertPtr = trainZero; vertPtr != null; vertPtr = vertPtr.getDown()) {
			for (TNode horizPtr = vertPtr; horizPtr != null; horizPtr = horizPtr.getNext()) {
				// Output the location, then prepare for the arrow to the next
				StdOut.print(horizPtr.getLocation());
				if (horizPtr.getNext() == null) break;
				
				// Spacing is determined by the numbers in the walking layer
				for (int i = horizPtr.getLocation()+1; i < horizPtr.getNext().getLocation(); i++) {
					StdOut.print("--");
					int numLen = String.valueOf(i).length();
					for (int j = 0; j < numLen; j++) StdOut.print("-");
				}
				StdOut.print("->");
			}

			// Prepare for vertical lines
			if (vertPtr.getDown() == null) break;
			StdOut.println();
			
			TNode downPtr = vertPtr.getDown();
			// Reset horizPtr, and output a | under each number
			for (TNode horizPtr = vertPtr; horizPtr != null; horizPtr = horizPtr.getNext()) {
				while (downPtr.getLocation() < horizPtr.getLocation()) downPtr = downPtr.getNext();
				if (downPtr.getLocation() == horizPtr.getLocation() && horizPtr.getDown() == downPtr) StdOut.print("|");
				else StdOut.print(" ");
				int numLen = String.valueOf(horizPtr.getLocation()).length();
				for (int j = 0; j < numLen-1; j++) StdOut.print(" ");
				
				if (horizPtr.getNext() == null) break;
				
				for (int i = horizPtr.getLocation()+1; i <= horizPtr.getNext().getLocation(); i++) {
					StdOut.print("  ");

					if (i != horizPtr.getNext().getLocation()) {
						numLen = String.valueOf(i).length();
						for (int j = 0; j < numLen; j++) StdOut.print(" ");
					}
				}
			}
			StdOut.println();
		}
		StdOut.println();
	}
	
	/**
	 * Used by the driver to display best path. 
	 * DO NOT edit.
	 */
	public void printBestPath(int destination) {
		ArrayList<TNode> path = bestPath(destination);
		for (TNode vertPtr = trainZero; vertPtr != null; vertPtr = vertPtr.getDown()) {
			for (TNode horizPtr = vertPtr; horizPtr != null; horizPtr = horizPtr.getNext()) {
				// ONLY print the number if this node is in the path, otherwise spaces
				if (path.contains(horizPtr)) StdOut.print(horizPtr.getLocation());
				else {
					int numLen = String.valueOf(horizPtr.getLocation()).length();
					for (int i = 0; i < numLen; i++) StdOut.print(" ");
				}
				if (horizPtr.getNext() == null) break;
				
				// ONLY print the edge if both ends are in the path, otherwise spaces
				String separator = (path.contains(horizPtr) && path.contains(horizPtr.getNext())) ? ">" : " ";
				for (int i = horizPtr.getLocation()+1; i < horizPtr.getNext().getLocation(); i++) {
					StdOut.print(separator + separator);
					
					int numLen = String.valueOf(i).length();
					for (int j = 0; j < numLen; j++) StdOut.print(separator);
				}

				StdOut.print(separator + separator);
			}
			
			if (vertPtr.getDown() == null) break;
			StdOut.println();

			for (TNode horizPtr = vertPtr; horizPtr != null; horizPtr = horizPtr.getNext()) {
				// ONLY print the vertical edge if both ends are in the path, otherwise space
				StdOut.print((path.contains(horizPtr) && path.contains(horizPtr.getDown())) ? "V" : " ");
				int numLen = String.valueOf(horizPtr.getLocation()).length();
				for (int j = 0; j < numLen-1; j++) StdOut.print(" ");
				
				if (horizPtr.getNext() == null) break;
				
				for (int i = horizPtr.getLocation()+1; i <= horizPtr.getNext().getLocation(); i++) {
					StdOut.print("  ");

					if (i != horizPtr.getNext().getLocation()) {
						numLen = String.valueOf(i).length();
						for (int j = 0; j < numLen; j++) StdOut.print(" ");
					}
				}
			}
			StdOut.println();
		}
		StdOut.println();
	}
}
