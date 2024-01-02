package prereqchecker;

import java.util.*;

/**
 * Steps to implement this class main method:
 * 
 * Step 1:
 * AdjListInputFile name is passed through the command line as args[0]
 * Read from AdjListInputFile with the format:
 * 1. a (int): number of courses in the graph
 * 2. a lines, each with 1 course ID
 * 3. b (int): number of edges in the graph
 * 4. b lines, each with a source ID
 * 
 * Step 2:
 * NeedToTakeInputFile name is passed through the command line as args[1]
 * Read from NeedToTakeInputFile with the format:
 * 1. One line, containing a course ID
 * 2. c (int): Number of courses
 * 3. c lines, each with one course ID
 * 
 * Step 3:
 * NeedToTakeOutputFile name is passed through the command line as args[2]
 * Output to NeedToTakeOutputFile with the format:
 * 1. Some number of lines, each with one course ID
 */
public class NeedToTake {
    public static void main(String[] args) {

        if ( args.length < 3 ) {
            StdOut.println("Execute: java NeedToTake <adjacency list INput file> <need to take INput file> <need to take OUTput file>");
            return;
        }
        StdIn.setFile(args[0]);

        Hash hash = new Hash();
        HashMap<String, ArrayList<String>> classesAndPreReqs = new HashMap<>();

        int numberOfCourses = StdIn.readInt();
        int count1 = 0;

        while(count1 < numberOfCourses){
            String classes = StdIn.readString();
            ArrayList<String> empty = new ArrayList<>();
            classesAndPreReqs = hash.filledHashMap(classes, empty);
            count1++;
        }

        int numberOfPreReqs = StdIn.readInt();
        int count2 = 0;

        while(count2 < numberOfPreReqs){
            String classes = StdIn.readString();
            String preReq = StdIn.readString();
            classesAndPreReqs.get(classes).add(preReq);
            count2++;
        }
        StdIn.setFile(args[1]);
        StdOut.setFile(args[2]);


        ArrayList<String> CTK = new ArrayList<>();
        String target = StdIn.readString();

        int count3 = 0;
        int classesToTake = StdIn.readInt();
        while(count3 < classesToTake){
            CTK.add(StdIn.readString());
            count3++;
        }
        for(String x : hash.NTT(target, CTK)){
            StdOut.println(x);
        }

	// WRITE YOUR CODE HERE
    }
}
