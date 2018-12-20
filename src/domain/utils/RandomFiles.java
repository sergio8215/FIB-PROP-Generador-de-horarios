package src.domain.utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;


public class RandomFiles {

    private String name;

    public RandomFiles(String name, int size, int i){
        this.name = name;
        if (i==2){
            subjects(size);
        }else{
            classrooms(size);
        }
    }

    public void subjects(int size) {
        JSONObject obj = new JSONObject();
        JSONArray subjects = new JSONArray();
        String[] Shift = new String[3];
        Shift[0]="MORNING";
        Shift[1]="AFTERNOON";
        Shift[2]="BOTH";

        for (int i=0; i<size; i++){
            JSONObject subject =  new JSONObject();
            subject.put("Subject",     RandomStrings(3));
            subject.put("Num_students", Integer.toString((int)(Math.random() * 150 + 10)));
            subject.put("Level",        Integer.toString((int)(Math.random() * 8 + 1)));
            subject.put("Theory_hours",  Integer.toString((int)(Math.random() * 10 + 1)));
            subject.put("Laboratory_hours",Integer.toString((int)(Math.random() * 10 + 0)));
            subject.put("Problems_hours",Integer.toString((int)(Math.random() * 10 + 0)));
            subject.put("Number_of_groups",Integer.toString((int)(Math.random() * 10 + 0)));
            subject.put("Number_of_subgroups",Integer.toString((int)(Math.random() * 10 + 0)));
            subject.put("Shift",           Shift[(int)(Math.random() * 3 + 0)]);
            subjects.add(subject);
        }
        obj.put("Subjects List" ,subjects);

        // try-with-resources statement based on post comment below :)
        try (FileWriter file = new FileWriter("./data/import/"+name)) {
            file.write(obj.toJSONString());
            System.out.println("Successfully Copied JSON Object to File...");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void classrooms(int size) {
        JSONObject obj = new JSONObject();
        JSONArray classrooms = new JSONArray();
        String[] type = new String[2];
        type[0]="THEORY";
        type[1]="LABORATORY";
        String[] aud = new String[2];
        aud[0]="true";
        aud[1]="false";


        for (int i=0; i<size; i++){
            JSONObject classroom =  new JSONObject();
            String tmp =RandomStrings(1);
            tmp += Integer.toString((int)(Math.random() * 300 + 100));
            classroom.put("Classroom",  tmp);
            classroom.put("Quantity",   Integer.toString((int)(Math.random() * 150 + 10)));
            classroom.put("Type",       type[(int)(Math.random() * 2 + 0)]);
            classroom.put("Audiovisual", aud[(int)(Math.random() * 2 + 0)]);
            classroom.put("Num_computers",Integer.toString((int)(Math.random() * 80 + 0)));

            classrooms.add(classroom);
        }
        obj.put("Classrooms List" ,classrooms);

        // try-with-resources statement based on post comment below :)
        try (FileWriter file = new FileWriter("./data/import/"+name)) {
            file.write(obj.toJSONString());
            System.out.println("Successfully Copied JSON Object to File...");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String RandomStrings(int length){
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = length;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        String generatedString = buffer.toString();

        return generatedString.toUpperCase();
    }

    public static void main(String[] args){
        System.out.println("Create: 1-Classrooms or 2-Subjects: ");
        Scanner sc = new Scanner(System.in);
        int i = sc.nextInt();
        if ( i == 2 ){
            System.out.println("file name: ");
            String name = sc.next();
            System.out.println("size: ");
            int size = sc.nextInt();
            RandomFiles createFile = new RandomFiles(name+".json", size,2);
        }else{
            System.out.println("file name: ");
            String name = sc.next();
            System.out.println("size: ");
            int size = sc.nextInt();
            RandomFiles createFile = new RandomFiles(name+".json", size,1);
        }
    }
}



