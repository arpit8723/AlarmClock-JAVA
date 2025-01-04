import java.io.File;
import java.io.IOException;
import java.time.LocalTime;
import java.util.Scanner;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class AlarmClock implements Runnable{

    private final LocalTime alarmTime;
    private final String FilePath;
    private final Scanner scanner;
//constructor
    AlarmClock(LocalTime alarmTime,String FilePath,Scanner scanner){
        this.alarmTime=alarmTime;
        this.FilePath=FilePath;
        this.scanner=scanner;


    }

    @Override
    public void run(){
       while(LocalTime.now().isBefore(alarmTime)){
        try{Thread.sleep(1000);
        LocalTime now=LocalTime.now();
        int hours=now.getHour();
        int min=now.getMinute();
        int sec=now.getSecond();
        System.out.printf("\r%02d:%02d:%02d",hours,min,sec); //\r to move cursor back to beginning everytime
       }
       catch(InterruptedException e){
        System.out.println("thread interrupted  ");

       }
    }
    System.out.println("*ALARM NOISES*");
    playSound(FilePath);
    }
    private void playSound(String FilePath){
        File audiofile=new File(FilePath);
        try(AudioInputStream audiostream= AudioSystem.getAudioInputStream(audiofile);){
            //now clip acts as a player to play the stream
            Clip clip= AudioSystem.getClip();
            clip.open(audiostream);
            clip.start();
            System.out.println("Press *ENTER* to Stop Alarm");
            scanner.nextLine();
            //now i need to off the music when i click enter so to do that i'll pass scanner as an object in here from main class
            scanner.close(); 
            
        
        }
        catch(UnsupportedAudioFileException e){
            System.out.println("file format unsupported");
        }
        catch(LineUnavailableException e){
            System.out.println("Audio unavailable");
        }
        catch(IOException e){
            System.out.println("error reading audio file");
        }
    }
    
}
