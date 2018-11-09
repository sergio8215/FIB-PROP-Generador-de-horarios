/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

import Data.DataManager;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Sergio
 */
public class Main {
    
    public static void main (String[] args) throws Exception {
        DataManager dt = new DataManager();
        
        dt.ImportData("file1.txt");
        
        
    }
}
