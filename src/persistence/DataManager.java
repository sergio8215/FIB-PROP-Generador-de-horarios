package src.persistence;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
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
import src.domain.controllers.CtrlDomain;
import src.domain.utils.inout;

/**
 * DataManager manage all files functions and methods
 * @author Sergio Mazzariol
 */
public class DataManager {

    // Constructor
    public DataManager( ){
    }

    /**
     * Reading from JSON file
     * @param file name to import
     * @return An Array with all imported classrooms, one per position and all attributes per each classrooms
     * @throws java.io.IOException error on read
     */
    public Vector <Vector< String>> importClassrooms(String file) throws IOException {

        JSONParser parser = new JSONParser();
        Vector <Vector< String>> classrooms = new Vector <>();
        try {
            Object obj = parser.parse(new FileReader(file));
            JSONObject rootJSON = (JSONObject) obj;

            // loop array to find values of classrooms
            JSONArray classroomList = (JSONArray) rootJSON.get("Classrooms List");
            if (classroomList.size() != 0){
                Iterator<JSONObject> iterator = classroomList.iterator();

                int i = 0;

                while (iterator.hasNext()) {

                    JSONObject classroomJSON = iterator.next();

                    classrooms.add(new Vector<>());
                    classrooms.elementAt(i).add((String)classroomJSON.get("Classroom"));
                    classrooms.elementAt(i).add((String)classroomJSON.get("Quantity"));
                    classrooms.elementAt(i).add((String)classroomJSON.get("Type"));
                    classrooms.elementAt(i).add((String)classroomJSON.get("Audiovisual"));
                    classrooms.elementAt(i).add((String)classroomJSON.get("Num_computers"));

                    i++;
                }
            }else{
                return null;
            }


        } catch (FileNotFoundException | ParseException ex) {
            classrooms = null;
            Logger.getLogger(DataManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return classrooms;
    }

    /**
     * Reading from JSON file
     * @param file name to import
     * @return An Array with all imported subjects, one per position and all attributes per each subjects
     * @throws java.io.IOException error on file read
     */
    public Vector <Vector< String>> importSubjects(String file) throws IOException {

        JSONParser parser = new JSONParser();
        Vector <Vector< String>> subjects = new Vector <>();

        try {
            Object obj = parser.parse(new FileReader(file));
            JSONObject rootJSON = (JSONObject) obj;

            // loop array to find values of Subjects
            JSONArray subjectsList = (JSONArray) rootJSON.get("Subjects List");
            if (subjectsList.size() != 0) {
                Iterator<JSONObject> iterator2 = subjectsList.iterator();

                int i = 0;

                while (iterator2.hasNext()) {

                    JSONObject subjectJSON = iterator2.next();

                    subjects.add(new Vector<>());
                    subjects.elementAt(i).add((String) subjectJSON.get("Subject"));
                    subjects.elementAt(i).add((String) subjectJSON.get("Num_students"));
                    subjects.elementAt(i).add((String) subjectJSON.get("Level"));
                    subjects.elementAt(i).add((String) subjectJSON.get("Theory_hours"));
                    subjects.elementAt(i).add((String) subjectJSON.get("Laboratory_hours"));
                    subjects.elementAt(i).add((String) subjectJSON.get("Problems_hours"));
                    subjects.elementAt(i).add((String) subjectJSON.get("Number_of_groups"));
                    subjects.elementAt(i).add((String) subjectJSON.get("Number_of_subgroups"));
                    subjects.elementAt(i).add((String) subjectJSON.get("Shift"));

                    i++;
                }
            }else{
                return null;
            }
        } catch (FileNotFoundException | ParseException ex) {
            subjects = null;
            Logger.getLogger(DataManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return subjects;
    }

    /**
     * loadSchedule load a schedule from a file
     * @param fileName Name of the file to import
     * @return a List of string that contains all schedule values
     * @throws IOException exception on file error
     */
    public List<String> loadSchedule( String fileName ) throws IOException {

        Path file = Paths.get( fileName );
        List<String> stringSchedule = Files.readAllLines(file, Charset.forName("UTF-8"));
        return stringSchedule;
    }

    /**
     * saveSchedule allow us to save the schedule on a file
     * @param fileNamePath name of the file to save
     * @param schedule schedule to save on the file
     * @throws Exception exception on file error
     */
    public void saveSchedule( String fileNamePath, List<String> schedule ) throws Exception {

        Path file = Paths.get( fileNamePath );
        Files.write(file, schedule, Charset.forName("UTF-8"));
        //Files.write(file, lines, Charset.forName("UTF-8"), StandardOpenOption.APPEND);
    }

    /**
     * saveSubjects Save subjects set on a JSON file
     * @param fileNamePath name of the file to save
     * @param subjectSet set of subject to save
     */
    public void saveSubjects(String fileNamePath, Vector< Vector< String>> subjectSet){
        JSONObject obj = new JSONObject();
        JSONArray subjects = new JSONArray();

        for (Vector<String> s : subjectSet){
            JSONObject subject =  new JSONObject();
            subject.put("Subject:",     s.get(0));
            subject.put("Num_students:", s.get(1));
            subject.put("Level:",        s.get(2));
            subject.put("Theory_hours",  s.get(3));
            subject.put("Laboratory_hours:",s.get(4));
            subject.put("Problems_hours:",s.get(5));
            subject.put("Number_of_groups:",s.get(6));
            subject.put("Number_of_subgroups:",s.get(7));
            subject.put("Shift:",           s.get(8));
            subjects.add(subject);
        }
        obj.put("Subjects List" ,subjects);

        // try-with-resources statement based on post comment below :)
        try (FileWriter file = new FileWriter(fileNamePath)) {
            file.write(obj.toJSONString());
            System.out.println("Successfully Copied JSON Object to File...");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * saveClassroom Save classroom set on a JSON file
     * @param fileNamePath name of the file to save
     * @param classroomSet set of classroom to save
     */
    public void saveClassrooms(String fileNamePath, Vector< Vector <String>> classroomSet){
        JSONObject obj = new JSONObject();
        JSONArray classrooms = new JSONArray();

        for (Vector<String> c : classroomSet){
            JSONObject classroom =  new JSONObject();
            classroom.put("Classroom",  c.get(0));
            classroom.put("Quantity",   c.get(1));
            classroom.put("Type",       c.get(2));
            classroom.put("Audiovisual",c.get(3));
            classroom.put("Num_computers",c.get(4));
            classrooms.add(classroom);
        }
        obj.put("Classrooms List" ,classrooms);

        // try-with-resources statement based on post comment below :)
        try (FileWriter file = new FileWriter(fileNamePath)) {
            file.write(obj.toJSONString());
            System.out.println("Successfully Copied JSON Object to File...");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

