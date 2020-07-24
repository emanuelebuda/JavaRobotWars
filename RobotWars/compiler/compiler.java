import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.channels.FileChannel;
import java.util.Scanner;

public class compiler {

  private static void printLines(String name, InputStream ins) throws Exception {
    String line = null;
    BufferedReader in = new BufferedReader(
        new InputStreamReader(ins));
    while ((line = in.readLine()) != null) {
        System.out.println(line);
    }
  }

  private static void runProcess(String command) throws Exception {
    Process pro = Runtime.getRuntime().exec(command);
    printLines(command, pro.getInputStream());
    printLines(command, pro.getErrorStream());
    pro.waitFor();
    //System.out.println(command + " exitValue() " + pro.exitValue());
  }

  public static boolean moveFile(String sourcePath, String targetPath) {
    File fileToMove = new File(sourcePath);
    return fileToMove.renameTo(new File(targetPath));
  }
  
  private static void copyFile(File sourceFile, File destFile) throws IOException {
        if (!sourceFile.exists()) {
            return;
        }
        if (!destFile.exists()) {
            destFile.createNewFile();
        }
        FileChannel source = null;
        FileChannel destination = null;
        source = new FileInputStream(sourceFile).getChannel();
        destination = new FileOutputStream(destFile).getChannel();
        if (destination != null && source != null) {
            destination.transferFrom(source, 0, source.size());
        }
        if (source != null) {
            source.close();
        }
        if (destination != null) {
            destination.close();
        }

    }
  
  public static void replaceSelected(String newLine1, String newLine2, String n) throws IOException {
    //Instantiating the File class
      String filePath = System.getProperty("user.dir") + "/Public/main.java";
      //Instantiating the Scanner class to read the file
      Scanner sc = new Scanner(new File(filePath));
      //instantiating the StringBuffer class
      StringBuffer buffer = new StringBuffer();
      //Reading lines of the file and appending them to StringBuffer
      while (sc.hasNextLine()) {
         buffer.append(sc.nextLine()+System.lineSeparator());
      }
      String fileContents = buffer.toString();

      //System.out.println("Contents of the file: " + fileContents);
      //closing the Scanner object
      sc.close();
      String oldLine1 = "//player" + n;
      String oldLine2 = "//import" + n;

      //Replacing the old line with new line
      fileContents = fileContents.replaceAll(oldLine1, newLine1);
      fileContents = fileContents.replaceAll(oldLine2, newLine2);
      //instantiating the FileWriter class
      FileWriter writer = new FileWriter(filePath);
      System.out.println("");
      //System.out.println("new data: " + fileContents);
      writer.append(fileContents);
      writer.flush();
}
  
  public static void importRobot(int n) throws IOException {
      String file;
      Scanner in = new Scanner(System.in);
      System.out.println("Import new robot? (y/n)");
      if(in.next().equals("y")) {
          System.out.println("Insert file name of the bot you want to import? (e.g. vertical)");
          System.out.println("It must be in the main folder");
          String nomeRobot = in.next();
          file = System.getProperty("user.dir") + "/" + nomeRobot + ".java";
          System.out.println(file);
          if(moveFile(file, System.getProperty("user.dir") + "/Robots/" + nomeRobot + ".java")) {
              System.out.println("Robot imported!");
              replaceSelected("arena.addRobot(new " + nomeRobot + "(ThreadLocalRandom.current().nextInt(0, SIZE-1),ThreadLocalRandom.current().nextInt(0, SIZE-1),\\\""+n+"\\\",arena));", "import Robots." + nomeRobot + ";", Integer.toString(n));
          }else{
              System.out.println("Could not import robot");
          } 
      }
  }
  
  public static void addRobot(int n) throws IOException {
      String file;
      Scanner in = new Scanner(System.in);
      System.out.println("Inserisci bot, nome file? (default loaded: vertical, horizontal)");
      String nomeRobot = in.next();
      file = System.getProperty("user.dir") + "/Robots/" + nomeRobot + ".java";
      if (new File(file).exists()) {
          replaceSelected("arena.addRobot(new " + nomeRobot + "(ThreadLocalRandom.current().nextInt(0, SIZE-1),ThreadLocalRandom.current().nextInt(0, SIZE-1),\\\""+n+"\\\",arena));", " ", Integer.toString(n));
      } else {
          System.out.println("Not found");
      }
  }

  public static void main(String[] args) {
    try {
        Scanner in = new Scanner(System.in);
        
        //purify the main loader
        File del = new File(System.getProperty("user.dir") + "/Public/main.java");
        //System.out.println(del.delete());
        copyFile(new File(System.getProperty("user.dir") + "/utils/mainBCK.java"), new File(System.getProperty("user.dir") + "/Public/main.java"));
        
        for(int i = 3; i < 6; i++) {
            System.out.println("Actual robots = " + (i-1));
            System.out.println("Do you want to add another robot to the game? (y/n)");
            
            if(in.next().equals("y")) {
                System.out.println("Is the robot already imported? (y/n)");         
                if(in.next().equals("n")) {
                    importRobot(i);
                }else {
                    addRobot(i);
                }
            }else {
                i = 5;
            }
            
        }
        
        System.out.println("Starting . . .");

        String compile = "java Public/main";
        System.out.println("Field size? (standard is 30)");
        compile += " " + in.next();
        System.out.println("How many turns? (standard is 500)");
        compile += " " + in.next();
        runProcess("javac Public/main.java");
        System.out.println("\nCompiling . . .\n");
        runProcess(compile);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}