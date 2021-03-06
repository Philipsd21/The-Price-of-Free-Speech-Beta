package Java;
import java.io.*;
import java.nio.charset.*;
import java.nio.file.*;
import java.util.*;

public class RunPython {

    private static String[] toRun = new String[]{"getTwitterID.py","tweetCounter.py","SMVI.py"};
    private static String[] Output = new String[toRun.length];

    public static void main(String[] args) {
        System.out.println("This method has no contents");
        Run(1);
        System.out.println(Output[1]);
    }

    public static void Run(int index) {
        Path p = Paths.get("PathTest.java");
        p = p.toAbsolutePath();
        String absPath = p.toString().substring(0,p.toString().lastIndexOf("/"))+"/Python/";
        String fileName = absPath + toRun[index];
        System.out.println("Running \"" + fileName + "\"");
        InputStream output = null;
        String text = "";
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("python", fileName);
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();
            output = (process.getInputStream());
        } catch (IOException e) {
            try{
                ProcessBuilder processBuilder = new ProcessBuilder("python3", fileName);
                processBuilder.redirectErrorStream(true);
                Process process = processBuilder.start();
                output = (process.getInputStream());
            }catch(IOException ee){
                System.out.println("Error when running python files" + "\n");
            }
        }
        try (Scanner scanner = new Scanner(output, StandardCharsets.UTF_8.name())) {
            text = scanner.useDelimiter("\\A").next();
            Output[index] = text;
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static String getOutput(int index){
        return Output[index];
    }
}
