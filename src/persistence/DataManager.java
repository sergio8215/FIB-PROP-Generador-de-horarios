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
import src.domain.utils.inout;

/**
 *
 * @author Sergio Mazzariol
 */
public class DataManager {

    // Constructor
    public DataManager( ){
    }

    /**
     * Reading from JSON file
     * @param file
     * @throws java.io.IOException
     */
    public Vector <Vector< String>> importClassrooms(String file) throws IOException {

        JSONParser parser = new JSONParser();
        Vector <Vector< String>> classrooms = new Vector <>();
        try {
            Object obj = parser.parse(new FileReader(file));
            JSONObject rootJSON = (JSONObject) obj;

            // loop array to find values of classrooms
            JSONArray classroomList = (JSONArray) rootJSON.get("Classrooms List");
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

        } catch (FileNotFoundException | ParseException ex) {
            classrooms = null;
            Logger.getLogger(DataManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return classrooms;
    }

    /**
     * Reading from JSON file
     * @param file
     * @throws java.io.IOException
     */
    public Vector <Vector< String>> importSubjects(String file) throws IOException {

        JSONParser parser = new JSONParser();
        Vector <Vector< String>> subjects = new Vector <>();

        try {
            Object obj = parser.parse(new FileReader(file));
            JSONObject rootJSON = (JSONObject) obj;

            // loop array to find values of Subjects
            JSONArray subjectsList = (JSONArray) rootJSON.get("Subjects List");
            Iterator<JSONObject> iterator2 = subjectsList.iterator();

            int i=0;

            while (iterator2.hasNext()) {

                JSONObject subjectJSON = iterator2.next();

                subjects.add(new Vector<>());
                subjects.elementAt(i).add((String)subjectJSON.get("Subject"));
                subjects.elementAt(i).add((String)subjectJSON.get("Num_students"));
                subjects.elementAt(i).add((String)subjectJSON.get("Level"));
                subjects.elementAt(i).add((String)subjectJSON.get("Theory_hours"));
                subjects.elementAt(i).add((String)subjectJSON.get("Laboratory_hours"));
                subjects.elementAt(i).add((String)subjectJSON.get("Problems_hours"));
                subjects.elementAt(i).add((String)subjectJSON.get("Number_of_groups"));
                subjects.elementAt(i).add((String)subjectJSON.get("Number_of_subgroups"));
                subjects.elementAt(i).add((String)subjectJSON.get("Shift"));

                i++;
            }



        } catch (FileNotFoundException | ParseException ex) {
            subjects = null;
            Logger.getLogger(DataManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return subjects;
    }

    public List<String> loadSchedule( String fileName ) throws IOException {

        Path file = Paths.get( fileName );

        List<String> stringSchedule = Files.readAllLines(file, Charset.forName("UTF-8"));

        return stringSchedule;
    }

    public void saveSchedule( String fileNamePath, List<String> schedule ) throws Exception {

        Path file = Paths.get( fileNamePath );

        Files.write(file, schedule, Charset.forName("UTF-8"));
        //Files.write(file, lines, Charset.forName("UTF-8"), StandardOpenOption.APPEND);
    }
}

