

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


public class For_Eyeballing extends JFrame {
    public static void main(String[] args) throws IOException {
//        Section s1 = new Section("S4L", LECTURE, "Bowen", TERM_1, "", "Test 121");
//        LocalTime st1 = LocalTime.of(13,15);
//        LocalTime et1 = LocalTime.of(14,15);
//        ClassTime ct1 = new ClassTime(TUESDAY, st1, et1);
//        s1.addClassTime(ct1);
//        ClassTime ct1_2 = new ClassTime(THURSDAY, st1, et1);
//        s1.addClassTime(ct1_2);
//
//        Section s2 = new Section("L5A", LABORATORY, "Bowen", TERM_2, "", "Test 110");
//        LocalTime st2 = LocalTime.of(9,15);
//        LocalTime et2t1 = LocalTime.of(10,15);
//        ClassTime ct2 = new ClassTime(MONDAY, st2, et2t1);
//        s2.addClassTime(ct2);
//        ClassTime ct2_2 = new ClassTime(WEDNESDAY, st2, et2t1);
//        s2.addClassTime(ct2_2);
//        ClassTime ct2_3 = new ClassTime(FRIDAY, st2, et2t1);
//        s2.addClassTime(ct2_3);
//
//        ArrayList<Section> sections = new ArrayList<>();
//        sections.add(s2);
//        ArrayList<Section> sections2 = new ArrayList<>();
//        sections2.add(s1);
//        ArrayList<ArrayList<Section>> testing = new ArrayList<>();
//        testing.add(sections);
//        testing.add(sections2);
//
//        TimeTableWindow t = new TimeTableWindow();
//        t.initializeTable(testing);
//        t.displayTable();
//        t.initializeButton();

        HashMap<String, ArrayList<Integer>> a = new HashMap<>();
        a.putIfAbsent("a", new ArrayList<>());
        a.get("a").add(2);
        a.get("a").add(4);
        a.putIfAbsent("b", new ArrayList<>());
        a.get("b").add(3);
        ArrayList<ArrayList<Integer>> b =  new ArrayList<>(a.values());
        System.out.println(b);

    }

}

