/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Config;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author Agust
 */
public interface AppConfig {
    
    
    static final String BASE = "src/data";
    
    static final String BINARY_FILE_PATH = "personas.dat";
    
    static final String CSV_FILE_PATH = "personas.csv";

    
    public static Path getRutaCSV(){
        return Paths.get(BASE, CSV_FILE_PATH);
    }
    
    public static Path getRutaBin(){
        return Paths.get(BASE, BINARY_FILE_PATH);
    }
    
    public static String getRutaCSVString(){
        return getRutaCSV().toString();
    }
    
    public static String getRutaBinString(){
        return getRutaBin().toString();
    }  
    
}
