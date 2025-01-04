import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {
    public static void main (String[] args){
        Scanner scanner=new Scanner(System.in);
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalTime alarmTime=null ;
        String FilePath="C:\\Users\\HP\\Desktop\\pdg.wav"; //audio file of your choice 

        //while loop to keep asking for input until valid value is provided
        while(alarmTime==null){
            try{ 
            System.out.print("Enter Alarm time to set (HH:MM:SS): ");    
        String inputTime=scanner.nextLine();
        //to store input in form of time
        alarmTime =LocalTime.parse(inputTime,formatter);
        System.out.println("alarm set for : "+alarmTime);
         }
         catch(DateTimeParseException e){
            System.out.println("invalid format! try again");

         }

       
        }
        AlarmClock alarmClock=new AlarmClock(alarmTime,FilePath,scanner);
        //since alarmClock is running on seperate thread so we will need to create new thread
        Thread alarmThread=new Thread(alarmClock);
        alarmThread.start();

           

    }
    
}
