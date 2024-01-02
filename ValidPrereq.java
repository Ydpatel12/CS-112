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
 * ValidPreReqInputFile name is passed through the command line as args[1]
 * Read from ValidPreReqInputFile with the format:
 * 1. 1 line containing the proposed advanced course
 * 2. 1 line containing the proposed prereq to the advanced course
 * 
 * Step 3:
 * ValidPreReqOutputFile name is passed through the command line as args[2]
 * Output to ValidPreReqOutputFile with the format:
 * 1. 1 line, containing either the word "YES" or "NO"
 */
public class ValidPrereq {
    public static void main(String[] args) {

        if ( args.length < 3 ) {
            StdOut.println("Execute: java -cp bin prereqchecker.ValidPrereq <adjacency list INput file> <valid prereq INput file> <valid prereq OUTput file>");
            return;
        }
        StdIn.setFile(args[0]);
        
        Hash hash = new Hash();
        HashMap<String, ArrayList<String>> classesAndPreReqs = new HashMap<>();
        HashSet<String> preReqs = new HashSet<>();

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

        String course1 = StdIn.readString();
        String course2 = StdIn.readString();
        HashSet<String> allDIP = hash.allDIP(course2);
            if (allDIP.contains(course1)){
                StdOut.println("NO");
            }
            else{
                StdOut.println("YES");
            }
        

        // WRITE YOUR CODE HERE
    }
}
