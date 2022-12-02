package com.vf.gozde;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

  private final static String APP_NAME = "Virtual Key For Repositories";
  private final static String DEV_NAME = "Gozde Aldikacti";

  public static void main(String[] args) throws IOException, URISyntaxException {

    System.out.println("WELCOME");
    System.out.println("Application Name: " + APP_NAME);
    System.out.println("Developer: " + DEV_NAME);
    System.out.println(
        "*************************************************************************");
    Scanner scanner = new Scanner(System.in);
    URI folderPath = Main.class.getClassLoader().getResource("files").toURI();
    File appFolder = new File(folderPath);
    int option;
    while (true) {
      System.out.println(
          "Option 1: Please press 1 to return the current file names in ascending order");
      System.out.println("Option 2: Please press 2 to return the details of the user interface.");
      System.out.println("Option 3: Please press 3 to close the application.");

      //System.out.print("Enter your name ? ");
      //String username = scanner.next();
      option = scanner.nextInt();

      switch (option) {
        case 1:
          System.out.println(
              "File names are: " + getAllFilesFromResource(folderPath).stream().map(File::getName)
                  .collect(Collectors.toList()));
          break;
        case 2:
          case2Options(scanner, appFolder);
          break;
        case 3:
          System.out.println("Closing the application...");
          System.exit(0);
        default:
          System.out.println("Wrong option");
          break;
      }
    }

  }

  private static void writeResourceFileNames(String path) throws IOException {
    File folder = new File(path);
    File[] listOfFiles = folder.listFiles();

    for (int i = 0; i < listOfFiles.length; i++) {
      if (listOfFiles[i].isFile()) {
        System.out.println("File " + listOfFiles[i].getName());
      } else if (listOfFiles[i].isDirectory()) {
        System.out.println("Directory " + listOfFiles[i].getName());
      }
    }
  }

  private static List<File> getAllFilesFromResource(URI folderPath) throws IOException {

    //ClassLoader classLoader = Main.class.getClassLoader();
    //URL resource = classLoader.getResource(folder);

    List<File> collect = Files.walk(Paths.get(folderPath))
        .filter(Files::isRegularFile)
        .map(x -> x.toFile())
        .collect(Collectors.toList());
    //todo do asc order

    return collect;
  }

  private static void case2Options(Scanner scanner, File appFolder) {
    boolean case2Options = true;
    while (case2Options) {
      System.out.println("1. Add a file to the application");
      System.out.println("2. Delete a file from the application");
      System.out.println("3. Search a specified file from the application");
      System.out.println("4. Go back to the previous menu");

      int option2 = scanner.nextInt();
      switch (option2) {
        case 1:
          System.out.println("Please enter a new file name:");
          String fileName = scanner.next();
          if (new File(appFolder, fileName).exists()) {
            System.out.println("The file already exists");
          } else {
            File folder1 = new File(appFolder, fileName);
            folder1.mkdir();
            if (new File(appFolder, fileName).exists()) {
              System.out.println("New file is added successfully");
            }
          }
          break;

        case 2:
          System.out.println("Please enter a file name to delete:");
          String name1 = scanner.next();
          boolean folder2 = new File(appFolder, name1).exists();
          System.out.println(folder2);
          if (folder2 == true) {
            File folder3 = new File(appFolder, name1);
            folder3.delete();
            System.out.println("File is deleted successfully");
          } else {
            System.out.println("File not exist");
          }
          break;

        case 3:
          System.out.println("Please enter a keyword to search in files:");
          String search = scanner.next();

          File arr1[] = appFolder.listFiles();
          for (int a = 0; a < arr1.length; a++) {
            if (arr1[a].getName().startsWith(search)) {
              System.out.println(arr1[a]);
            } else {
              System.out.println("File not found");
            }
          }
          break;

        case 4:
          case2Options = false;
          break;

        default:
          System.out.println("Wrong option");

      }
    }
    
  }

}