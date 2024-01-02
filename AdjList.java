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
 * AdjListOutputFile name is passed through the command line as args[1]
 * Output to AdjListOutputFile with the format:
 * 1. c lines, each starting with a different course ID, then 
 *    listing all of that course's prerequisites (space separated)
 */
public class AdjList {
    public static void main(String[] args) {

        // if ( args.length < 2 ) {
        //     StdOut.println("Execute: java -cp bin prereqchecker.AdjList <adjacency list INput file> <adjacency list OUTput file>");
        //     return;
        // }
        StdIn.setFile(args[0]);
        StdOut.setFile(args[1]);

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

        for(String courseId : classesAndPreReqs.keySet()){
            StdOut.print(courseId + " ");

            for(int i = 0; i < classesAndPreReqs.get(courseId).size(); i++){
                StdOut.print(classesAndPreReqs.get(courseId).get(i) + " ");
            }
            StdOut.println();
        }








	// WRITE YOUR CODE HERE
    }
}
