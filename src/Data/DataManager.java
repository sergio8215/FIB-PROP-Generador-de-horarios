package src.Data;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


/**
 *
 * @author Sergio
 */
public class DataManager {

    // Constructor
    public DataManager( ){
    }

    /**
     * Reading from JSON file
     * @param fileName
     * @throws java.io.IOException
     */
    public Vector <Vector< String>> ImportClassrooms(String fileName) throws IOException {

        JSONParser parser = new JSONParser();
        Vector <Vector< String>> classrooms = new Vector <Vector< String>>();
        try {

            Object obj = parser.parse(new FileReader("./files/" + fileName ));
            JSONObject rootJSON = (JSONObject) obj;

            // loop array to find values of classrooms
            JSONArray classroomList = (JSONArray) rootJSON.get("Classrooms List");
            Iterator<JSONObject> iterator = classroomList.iterator();

            int i = 0;

            while (iterator.hasNext()) {

                JSONObject classroomJSON = (JSONObject) iterator.next();

                classrooms.add(new Vector<String>());
                classrooms.elementAt(i).add((String)classroomJSON.get("Classroom"));
                classrooms.elementAt(i).add((String)classroomJSON.get("Quantity"));
                classrooms.elementAt(i).add((String)classroomJSON.get("Type"));
                classrooms.elementAt(i).add((String)classroomJSON.get("Audiovisual"));
                classrooms.elementAt(i).add((String)classroomJSON.get("Num_computers"));

                System.out.println("Classroom: "+   classrooms.elementAt(i).get(0));
                System.out.println("Quantity: "+    classrooms.elementAt(i).get(1));
                System.out.println("Type: "+        classrooms.elementAt(i).get(2));
                System.out.println("Audiovisual: "+ classrooms.elementAt(i).get(3));
                System.out.println("Num Computers: "+classrooms.elementAt(i).get(4)+"\n");
                i++;
            }

        } catch (FileNotFoundException | ParseException ex) {
            classrooms = null;
            Logger.getLogger(DataManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return classrooms;
    }

    /**
     * Reading from JSON file
     * @param fileName
     * @throws java.io.IOException
     */
    public Vector <Vector< String>> ImportSubjects(String fileName) throws IOException {

        JSONParser parser = new JSONParser();
        Vector <Vector< String>> subjects = new Vector <Vector< String>>();

        try {
            Object obj = parser.parse(new FileReader("./files/" + fileName ));
            JSONObject rootJSON = (JSONObject) obj;

            String  subject;
            Long    weekHours;
            Long    numStudents;
            Long    level;
            Long    maxCapacity;

            // loop array to find values of Subjects
            JSONArray subjectsList = (JSONArray) rootJSON.get("Subjects List");
            Iterator<JSONObject> iterator2 = subjectsList.iterator();

            int i=0;

            while (iterator2.hasNext()) {

                JSONObject subjectJSON = (JSONObject) iterator2.next();

                subjects.add(new Vector<String>());
                subjects.elementAt(i).add((String)subjectJSON.get("Subject"));
                subjects.elementAt(i).add((String)subjectJSON.get("Num_students"));
                subjects.elementAt(i).add((String)subjectJSON.get("Level"));
                subjects.elementAt(i).add((String)subjectJSON.get("Theory_hours"));
                subjects.elementAt(i).add((String)subjectJSON.get("Laboratory_hours"));
                subjects.elementAt(i).add((String)subjectJSON.get("Problems_hours"));
                subjects.elementAt(i).add((String)subjectJSON.get("Number_of_groups"));
                subjects.elementAt(i).add((String)subjectJSON.get("Number_of_subgroups"));
                subjects.elementAt(i).add((String)subjectJSON.get("Shift"));

                System.out.println("Subject: "+         subjects.elementAt(i).get(0));
                System.out.println("Num_students: "+    subjects.elementAt(i).get(1));
                System.out.println("Level: "+           subjects.elementAt(i).get(2));
                System.out.println("Theory_hours: "+    subjects.elementAt(i).get(3));
                System.out.println("Laboratory_hours: "+subjects.elementAt(i).get(4));
                System.out.println("Problems_hours: "+  subjects.elementAt(i).get(5));
                System.out.println("Number_of_groups: "+subjects.elementAt(i).get(6));
                System.out.println("Number_of_subgroups: "+subjects.elementAt(i).get(7));
                System.out.println("Shift: "+           subjects.elementAt(i).get(8)+"\n");
                i++;
            }

        } catch (FileNotFoundException | ParseException ex) {
            subjects = null;
            Logger.getLogger(DataManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return subjects;
    }

    public void loadSchedule(  ) {

    }

    public void saveSchedule(  ) {

    }
}