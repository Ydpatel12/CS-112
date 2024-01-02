package prereqchecker;
import java.util.*;


public class Hash {
    private HashMap<String, ArrayList<String>> coursesAndPreReqs;
    private HashMap<String, Boolean> marked;
    //private HashSet<String> DIP = new HashSet<>();

    public Hash(){
        coursesAndPreReqs = new HashMap<>();
        marked = new HashMap<>();
    }

    public HashMap<String, ArrayList<String>> filledHashMap(String course1, ArrayList<String> preReqs){
        coursesAndPreReqs.put(course1, preReqs);
        marked.put(course1, false);
        return coursesAndPreReqs;
    } 

    public HashSet<String> allDIP(String courseID){
        HashSet<String> dip = new HashSet<>();
        for(int i = 0; i < coursesAndPreReqs.get(courseID).size(); i++){
            dip.add(coursesAndPreReqs.get(courseID).get(i));
            DFS(coursesAndPreReqs.get(courseID).get(i), dip);
        }
        return dip;
    }


    public void DFS(String courseID, HashSet<String> preReqs){
        for(String x : marked.keySet()){
            marked.put(courseID, false);
        }
        for(int i = 0; i < coursesAndPreReqs.get(courseID).size(); i++){
            if(marked.get(courseID) != true){
                preReqs.add(coursesAndPreReqs.get(courseID).get(i));
                DFS(coursesAndPreReqs.get(courseID).get(i), preReqs);
            }
        }
        marked.put(courseID, true);
    }


    public HashSet<String> eligibleP1(ArrayList<String> classes){
        HashSet<String> allCourses = new HashSet<>();
        for(int i = 0; i < classes.size(); i++){
            allCourses.add(classes.get(i));
            allCourses.addAll(allDIP(classes.get(i)));
        }
        return allCourses;
    }


    public String eligibleP2(String CourseID, HashSet<String> PreReqs){
        HashSet<String> course = allDIP(CourseID);
        if(PreReqs.containsAll(course) && (!PreReqs.contains(CourseID))){
            return CourseID;
        }
        else{
            return null;
        }

    }


    public HashSet<String> NTT(String target, ArrayList<String> coursesTaken){
        HashSet<String> dipClasses = new HashSet<>();
        HashSet<String> dipTarget = new HashSet<>();
        for(int i = 0; i < coursesTaken.size(); i++){
            HashSet<String> h = allDIP(coursesTaken.get(i));
            dipClasses.add(coursesTaken.get(i));
            dipClasses.addAll(h);
        }
        dipTarget = allDIP(target);
        dipTarget.removeAll(dipClasses);

        return dipTarget;
    }

    public void schedule(String target, ArrayList<String> coursesAlreadyTaken){
        HashSet<String> needToTakeCourses = NTT(target, coursesAlreadyTaken);
        HashMap<Integer, HashSet<String>> keyOfSem = new HashMap<>();
        int semCounter = 1;
        HashSet<String> allPreReqsforGivenCourse= new HashSet<>();
        HashSet<String> allCoursesYouCanTakeInASemester = new HashSet<>();
        
        while(!needToTakeCourses.isEmpty()){
            for(String x : needToTakeCourses){
                StdOut.println(allDIP(x));
                break;
            }
        }
    }

    // public void schedule2(String target, ArrayList<String> coursesAlreadyTaken){
    //     HashSet<String> coursesCompleted = new HashSet<>();
    //     HashSet<String> coursesStillNeeded = new HashSet<>();
    //     HashSet<String> coursesInTotal = allDIP(target);
    //     HashMap<Integer, HashSet<String>> semClasses = new HashMap<>();
        
    //     int semesterCounter = 1;

    //     for(String x : coursesAlreadyTaken){
    //         coursesCompleted.addAll(allDIP(x));
    //         coursesCompleted.add(x);
    //     }

    //     coursesInTotal.removeAll(coursesCompleted);
    //     coursesStillNeeded = coursesInTotal;

    //     StdOut.println(coursesAlreadyTaken + "   taken");
    //     StdOut.println(coursesCompleted + "  completed");
    //     StdOut.println(coursesStillNeeded + " still Needed for   " + target);
    //     StdOut.println(semClasses);

    //     for(String x : coursesStillNeeded){
    //         StdOut.println(allDIP(x));
    //         if(allDIP(x).containsAll(coursesCompleted)){
    //             StdOut.println("leggo");
    //         }
    //     }



    //     semesterCounter++;
    // }
    public void schedule3(String target, ArrayList<String> coursesAlreadyTaken){
        HashSet<String> coursesCompleted = new HashSet<>();
        for(String x : coursesAlreadyTaken){
            coursesC.addAll(allDIP(x));
            everythingCompleted.add(x);
        }
        StdOut.println(everythingCompleted);
    }




}
