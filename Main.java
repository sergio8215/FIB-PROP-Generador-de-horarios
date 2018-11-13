
import src.Data.DataManager;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        DataManager dManager = new DataManager();

        dManager.ImportClassrooms("file1.txt");

        dManager.ImportSubjects("file1.txt");
    }
}