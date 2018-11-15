package src.domain.classes;

import src.persistence.DataManager;

public class MainDataManager {

    public static void main(String[] args) throws Exception {
        DataManager dManager = new DataManager();
        Schedule schedule = null;
        
        dManager.saveSchedule("test.txt", schedule);
    }

}
