package Scheduler;

import InfoNeeded.CourseActivity;
import InfoNeeded.Section;
import org.chocosolver.solver.Model;
import org.chocosolver.solver.Solution;
import org.chocosolver.solver.constraints.Constraint;
import org.chocosolver.solver.variables.IntVar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Scheduler {
    private ArrayList<CourseActivity> courseActivities;
    private HashMap<String, ArrayList<Integer>> map;
    private int size;

    public Scheduler(ArrayList<CourseActivity> courseActivities){
        map = new HashMap<>();
        this.courseActivities = courseActivities;
        size = courseActivities.size();
    }

    // input: nothing, output: a list of sections chosen representing a solution
    // values of each variable represent sections (each is an index for Sections field in CourseActivities)
    public ArrayList<ArrayList<Section>> generateSchedule(){
        Model model = new Model("Scheduler");

        //generate a variable for each CourseActivity
        IntVar[] caVars = new IntVar[size];
        for (int i = 0; i<size; i++){
            caVars[i] = model.intVar("CA_"+i,0,courseActivities.get(i).getSections().size()-1);

            // build a hashmap of <Course Title, list of course activities belongs to it>
            map.putIfAbsent(courseActivities.get(i).getTitle(), new ArrayList<Integer>());
            map.get(courseActivities.get(i).getTitle()).add(i);
        }

        // add the constraint between each pair of variable
        for (int i = 0; i<size-1; i++){
            for (int j = i+1;j<size; j++){
                Constraint c = new Constraint("NoTimeConflict", new NoTimeConflict(caVars[i],caVars[j],courseActivities));
                model.post(c);
            }
        }

        // get a list of CourseActivities (index) organized by Course
        ArrayList<ArrayList<Integer>> caByCourse = new ArrayList<>(map.values());
        for (ArrayList<Integer> i: caByCourse){
            for (int j=0; j<i.size(); j++){
                for (int k = j+1;k<size; k++){
                    //TODO: add constraint for each course
                    Constraint c = new Constraint("SameTerm", new SameTerm(caVars[j],caVars[k],courseActivities));
                    model.post(c);
                }
            }
        }

        ArrayList<ArrayList<Section>> timetables = new ArrayList<>();
        List<Solution> solutions = model.getSolver().findAllSolutions();
        long solutionCount = model.getSolver().getSolutionCount();
        if (solutionCount != 0){
            System.out.println(solutionCount + " Solutions Found");
            for (Solution s : solutions) {
                ArrayList<Section> timetable = new ArrayList<>();
                for (int i = 0; i<caVars.length; i++){
                    timetable.add(courseActivities.get(i).getSections().get(s.getIntVal(caVars[i])));
                    //System.out.println(s.getIntVal(caVars[i]));
                    //System.out.println(courseActivities.get(i).getSections().get(s.getIntVal(caVars[i])));
                }

                timetables.add(timetable);
            }
        } else {
            System.out.println("No Possible Schedule exists");
        }

        return timetables;
    }

    public ArrayList<CourseActivity> getCourseActivities() {
        return courseActivities;
    }

    public void setCourseActivities(ArrayList<CourseActivity> courseActivities) {
        this.courseActivities = courseActivities;
    }

    public int getSize() {
        return size;
    }
}
