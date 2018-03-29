package pl.marek.pretius;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;

public class CheckFolder {

    private static int numberEditFiles = 1;

    private void saveCount(File home, String info){
        for(File file : home.listFiles()) {
            if (file.toString().substring(file.toString().length() - 9, file.toString().length()).equals("count.txt")) {
                try {
                    FileWriter fileWriter = new FileWriter(file.toString(),true);
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    bufferedWriter.write(info);
                    bufferedWriter.newLine();
                    bufferedWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void transfer(File home, String from, String to){
        try {
            Files.move(Paths.get(from), Paths.get(to), StandardCopyOption.REPLACE_EXISTING);
            String info = "Numer przeniesionego pliku: " + numberEditFiles + " Przeniesiono z: " + from + " do " + to;
            numberEditFiles++;
            saveCount(home,info);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void check(File home, File dev, File test){
        while(true){
            for(File file : home.listFiles()){
                if(!file.toString().substring(file.toString().length()-9,file.toString().length()).equals("count.txt")){
                    String string = file.toString();
                    if(string.endsWith(".xml")){
                        String from = file.toString();
                        String to = from.replace("HOME","DEV");
                        transfer(home,from,to);
                    } else{
                        if(string.endsWith(".jar")){
                            boolean flag;
                            LocalDateTime localDateTime = LocalDateTime.now();
                            int minute = localDateTime.getMinute();
                            flag = minute % 2 == 0;
                            if(flag){
                                String from = file.toString();
                                String to = from.replace("HOME","DEV");
                                transfer(home,from,to);
                            }
                            else{
                                String from = file.toString();
                                String to = from.replace("HOME","TEST");
                                transfer(home,from,to);
                            }
                        }
                    }
                }
            }
        }
    }
}
