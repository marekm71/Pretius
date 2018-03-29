package pl.marek.pretius;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

    private static File home;
    private static File dev;
    private static File test;

    public static File CreateFolder(String path)
    {
        File file = new File(path);
        boolean success = (file).mkdirs();
        if (!success) {
            System.out.println("Folder nie utworzony bo juz istnieje");
        }
        else {
            System.out.println("Folder utworzony");
        }
        return file;
    }

    private static void init(){
        try {
            home = CreateFolder("katalogi/HOME");
            dev = CreateFolder("katalogi/DEV");
            test = CreateFolder("katalogi/TEST");
            String filePath = "katalogi/HOME/count.txt";
            FileWriter fileWriter = null;
            fileWriter = new FileWriter(filePath);
        }
            catch (IOException e) {
            e.printStackTrace();

        }
    }

    public static void main(String[] args) {
        init();
        CheckFolder checkFolder = new CheckFolder();
        checkFolder.check(home,dev,test);
    }
}
