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
 * SchedulePlanInputFile name is passed through the command line as args[1]
 * Read from SchedulePlanInputFile with the format:
 * 1. One line containing a course ID
 * 2. c (int): number of courses
 * 3. c lines, each with one course ID
 * 
 * Step 3:
 * SchedulePlanOutputFile name is passed through the command line as args[2]
 * Output to SchedulePlanOutputFile with the format:
 * 1. One line containing an int c, the number of semesters required to take the course
 * 2. c lines, each with space separated course ID's
 */
public class SchedulePlan {
    public static void main(String[] args) {

        // if ( args.length < 3 ) {
        //     StdOut.println("Execute: java -cp bin prereqchecker.SchedulePlan <adjacency list INput file> <schedule plan INput file> <schedule plan OUTput file>");
        //     return;
        // }
        StdIn.setFile("adjlist.in");
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

        StdIn.setFile("scheduleplan.in");
        StdOut.setFile("scheduleplan.out");

        String target = StdIn.readString();

        int count3 = 0;
        int numTakenCourses = StdIn.readInt();
        ArrayList<String> allTakenCourses = new ArrayList<>();

        while(count3 < numTakenCourses){
           String takenCourses = StdIn.readString();
           allTakenCourses.add(takenCourses);
           count3++;
        }
        hash.schedule2(target, allTakenCourses);




	// WRITE YOUR CODE HERE

    }
}
