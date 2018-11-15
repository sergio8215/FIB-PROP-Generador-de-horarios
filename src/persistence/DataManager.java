
package src.persistence;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import src.domain.classes.*;
import src.domain.utils.inout;


import static java.nio.file.Files.readAllLines;

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
    public Vector <Vector< String>> importClassrooms(String fileName) throws IOException {

        JSONParser parser = new JSONParser();
        Vector <Vector< String>> classrooms = new Vector <Vector< String>>();
        try {

            Object obj = parser.parse(new FileReader("./data/import/" + fileName ));
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
    public Vector <Vector< String>> importSubjects(String fileName) throws IOException {

        JSONParser parser = new JSONParser();
        Vector <Vector< String>> subjects = new Vector <Vector< String>>();

        try {
            Object obj = parser.parse(new FileReader("./data/import/" + fileName ));
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

    public Schedule loadSchedule( int fileNum ) throws IOException {

        ArrayList<String> fileList = listScheduleFiles();
        Path file = Paths.get( fileList.get(fileNum));

        List<String> stringSchedule = Files.readAllLines(file, Charset.forName("UTF-8"));
        String classroomFile = stringSchedule.get(0);
        String subjectFile = stringSchedule.get(1);
        HashMap<String, ArrayList<MUS> > timetable = new HashMap<>();
        String[] separated;
        MUS myMUS;
        ArrayList<MUS> arrayMUS = new ArrayList<>();

        // We read the first class
        separated = stringSchedule.get(2).split("\\*");
        String subjectName = ClassClass.fromStr(new Vector(Arrays.asList(separated))).getSubject().getName();

        for( int i = 2; i < stringSchedule.size(); i+=3 ){

            // We read the class
            separated = stringSchedule.get(i).split("\\*");
            ClassClass classClass = ClassClass.fromStr(new Vector(Arrays.asList(separated)));

            // We read the classroom
            separated = stringSchedule.get(i+1).split("\\*");
            Classroom classroom = Classroom.fromStr(new Vector(Arrays.asList(separated)));

            // We read the session
            separated = stringSchedule.get(i+2).split("\\*");
            Session session = new Session(new Vector(Arrays.asList(separated)));
            myMUS = new MUS( classClass, classroom, session );

            // If the class before it's same subject we add it to the array list
            if ( subjectName.equalsIgnoreCase(classClass.getSubject().getName()) ){

                arrayMUS.add(myMUS);

            }else{  // if not we add it to the hashmap
                timetable.put(subjectName, arrayMUS );
                arrayMUS = new ArrayList<>();
                arrayMUS.add(myMUS);
                subjectName = classClass.getSubject().getName();
            }
        }
        Schedule schedule = new Schedule(classroomFile, subjectFile, timetable);
        return schedule;
    }

    public ArrayList<String> listScheduleFiles() {
        inout i = new inout();

        ArrayList<String> myFiles = new ArrayList<String>();
        Path dir = Paths.get("./data/load/");
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {

            for (Path entry : stream) {
                myFiles.add(entry.toString());
            }


        } catch (IOException e) {
            e.printStackTrace();
            myFiles = null;
        } catch (Exception e) {
            e.printStackTrace();
            myFiles = null;
        }
        return myFiles;
    }

        public void saveSchedule( String fileName, Schedule schedule ) throws Exception {

        Path file = Paths.get("./data/load/"+fileName);

        List<String> lines = new ArrayList<>();

        lines.add(schedule.getClassroomFile());
        lines.add(schedule.getSubjectFile());

        // I get an array list of MUS's
        for ( MUS mus: schedule.unset() ) {

            // For each mus I pass all his attributes to string

            Vector<Vector<String>> musAttributes;
            musAttributes = mus.toStr();
            String result;

            // for each attribute of the MUS
            for (Vector<String> eachAttribute : musAttributes) {

                result = "";

                for (String objectAttribute : eachAttribute) {
                    result = result + objectAttribute + "*";
                }
                lines.add(result);

            }
        }

        Files.write(file, lines, Charset.forName("UTF-8"));
        //Files.write(file, lines, Charset.forName("UTF-8"), StandardOpenOption.APPEND);
    }
}

