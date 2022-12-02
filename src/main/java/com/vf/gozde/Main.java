package com.vf.gozde;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
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
    System.out.println("*************************************************************************");
    Scanner scanner = new Scanner(System.in);
    URI folderPath = Main.class.getClassLoader().getResource("files").toURI();
    File appFolder = new File(folderPath);
    int option;
    while (true) {
      System.out.println("*************************************************************************");
      System.out.println(
          "Option 1: Please press 1 to return the current file names in ascending order");
      System.out.println("Option 2: Please press 2 to return the details of the user interface.");
      System.out.println("Option 3: Please press 3 to close the application.");
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
          System.out.println("Wrong option!");
          break;
      }
    }

  }

  private static List<File> getAllFilesFromResource(URI folderPath) throws IOException {
    List<File> allFiles = Files.walk(Paths.get(folderPath))
        .filter(Files::isRegularFile)
        .map(x -> x.toFile())
        .collect(Collectors.toList());
    //sort list in asc order
    Collections.sort(allFiles);

    return allFiles;
  }

  private static void case2Options(Scanner scanner, File appFolder) throws IOException {
    boolean case2Options = true;
    while (case2Options) {
      System.out.println("*************************************************************************");
      System.out.println("1. Add a file to the application");
      System.out.println("2. Delete a file from the application");
      System.out.println("3. Search a specified file from the application");
      System.out.println("4. Go back to the previous menu");

      int option2 = scanner.nextInt();
      switch (option2) {
        case 1:
          addFile(scanner, appFolder);
          break;
        case 2:
          deleteFile(scanner, appFolder);
          break;
        case 3:
          searchFile(scanner, appFolder);
          break;
        case 4:
          case2Options = false;
          break;
        default:
          System.out.println("Wrong option!");
          break;
      }
    }
  }

  private static void addFile(Scanner scanner, File appFolder) throws IOException {
    System.out.println("Please enter a new file name:");
    String fileName = scanner.next();
    File folder1 = new File(appFolder, fileName);
    boolean created = folder1.createNewFile();

    if (!created) {
      System.out.println("The file already exists");
    } else {
      System.out.println("New file is created successfully");
    }
  }


  private static void deleteFile(Scanner scanner, File appFolder) {
    System.out.println("Please enter a file name to delete:");
    String name1 = scanner.next();
    boolean folder2 = new File(appFolder, name1).exists();
    if (folder2) {
      File folder3 = new File(appFolder, name1);
      folder3.delete();
      System.out.println("File is deleted successfully");
    } else {
      System.out.println("File not exist");
    }
  }

  private static void searchFile(Scanner scanner, File appFolder) {
    System.out.println("Please enter a keyword to search in files:");
    String search = scanner.next();

    File arr1[] = appFolder.listFiles();
    boolean found = false;
    for (int a = 0; a < arr1.length; a++) {
      if (arr1[a].getName().startsWith(search)) {
        System.out.println("File found: " + arr1[a]);
        found = true;
        break;
      }
    }
    if (!found) {
      System.out.println("File not found");
    }
  }

}