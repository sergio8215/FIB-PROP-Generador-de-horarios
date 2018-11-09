/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Domain.SubjectsSet;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
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
    
    SubjectsSet subjects;
    
    
    // Constructor
    public DataManager(  ){
    }

    /**
     * Reading from JSON file
     * @param fileName
     * @throws java.io.IOException
     */
    public void ImportData(String fileName) throws IOException {

        JSONParser parser = new JSONParser();
        
        try {
            Object obj = parser.parse(new FileReader("./files/" + fileName ));
            JSONObject rootJSON = (JSONObject) obj;
           
            String  aula;
            Long    cantidad;
            String  tipo;
            Boolean audiovisual;
          
            // loop array to find values of classrooms
            JSONArray aulasList = (JSONArray) rootJSON.get("Aulas List");
            Iterator<JSONObject> iterator = aulasList.iterator();
            
            int i=0;
            
            while (iterator.hasNext()) {

                JSONObject aulas = (JSONObject) iterator.next();
                aula =          (String) aulas.get("Aula");
                cantidad =      (Long) aulas.get("Cantidad");
                tipo =          (String) aulas.get("Tipo");
                audiovisual =   (Boolean) aulas.get("Audiovisual");
                
                System.out.println("Aula: "+aula);
                System.out.println("Cantdidad: "+cantidad);
                System.out.println("Tipo: "+tipo);
                System.out.println("Audiovisual: "+audiovisual+"\n");
                i++;
            }
            
            String  subject;
            Long    weekHours;
            Long    numStudents;
            Long    level;
            Long    maxCapacity;

            // loop array to find values of Subjects
            JSONArray subjectsList = (JSONArray) rootJSON.get("Subjects List");
            Iterator<JSONObject> iterator2 = subjectsList.iterator();
            
            int j=0;
            
            while (iterator2.hasNext()) {

                JSONObject subjectVal = (JSONObject) iterator2.next();
                subject =       (String) subjectVal.get("Subject");
                weekHours =     (Long) subjectVal.get("Week_hours");
                numStudents =   (Long) subjectVal.get("Num_students");
                level =         (Long) subjectVal.get("Level");
                maxCapacity =   (Long) subjectVal.get("Max_capacity");
                
                System.out.println("Subject: "+subject);
                System.out.println("Week_hours: "+weekHours);
                System.out.println("Num_students: "+numStudents);
                System.out.println("Level: "+level);
                System.out.println("Max_capacity: "+maxCapacity+"\n");
                j++;
            }

            
        } catch (FileNotFoundException | ParseException ex) {
            Logger.getLogger(DataManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
