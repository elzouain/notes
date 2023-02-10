package com.simon.notes.controller.common;

public class ConsoleUtils {
    
    public static void clear(){
        try{
            String operatingSystem = System.getProperty("os.name"); //Check the current operating system
            ProcessBuilder pb;
            if(operatingSystem.contains("Windows")){        
                pb = new ProcessBuilder("cmd", "/c", "clear");
            } else {
                pb = new ProcessBuilder("clear");
            }
            pb.inheritIO().start().waitFor();
        }catch(Exception e){
            System.out.println(e);
        }
    }
}