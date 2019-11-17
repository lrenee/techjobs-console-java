package org.launchcode.techjobs.console;

import java.util.*;

/**
 * Created by LaunchCode
 */
public class TechJobs {

    private static Scanner in = new Scanner(System.in);

    public static void main (String[] args) {

        // Initialize our field map with key/name pairs
        HashMap<String, String> columnChoices = new HashMap<>();
        columnChoices.put("core competency", "Skill");
        columnChoices.put("employer", "Employer");
        columnChoices.put("location", "Location");
        columnChoices.put("position type", "Position Type");
        columnChoices.put("all", "All");

        // Top-level menu options
        HashMap<String, String> actionChoices = new HashMap<>();
        actionChoices.put("search", "Search");
        actionChoices.put("list", "List");

        System.out.println("Welcome to LaunchCode's TechJobs App!");

        // Allow the user to search until they manually quit
        while (true) {

            String actionChoice = getUserSelection("View jobs by:", actionChoices);

            if (actionChoice.equals("list")) {

                String columnChoice = getUserSelection("List", columnChoices);

                if (columnChoice.equals("all")) {
                    printJobs(JobData.findAll());
                } else {

                    ArrayList<String> results = JobData.findAll(columnChoice);

                    System.out.println("\n*** All " + columnChoices.get(columnChoice) + " Values ***");

                    // Print list of skills, employers, etc
                    for (String item : results) {
                        System.out.println(item);
                    }
                }

            } else { // choice is "search"

                // How does the user want to search (e.g. by skill or employer)
                String searchField = getUserSelection("Search by:", columnChoices);

                // What is their search term?
                System.out.println("\nSearch term: ");
                String searchTerm = in.nextLine();

                if (searchField.equals("all")) {
                    printJobs(JobData.findByValue(searchTerm));
                } else {
                    printJobs(JobData.findByColumnAndValue(searchField, searchTerm));
                }
            }
        }
    }

    // ﻿Returns the key of the selected item from the choices Dictionary
    private static String getUserSelection(String menuHeader, HashMap<String, String> choices) {

        Integer choiceIdx;
        Boolean validChoice = false;
        String[] choiceKeys = new String[choices.size()];

        // Put the choices in an ordered structure so we can
        // associate an integer with each one
        Integer i = 0;
        for (String choiceKey : choices.keySet()) {
            choiceKeys[i] = choiceKey;
            i++;
        }

        do {

            System.out.println("\n" + menuHeader);

            // Print available choices
            for (Integer j = 0; j < choiceKeys.length; j++) {
                System.out.println("" + j + " - " + choices.get(choiceKeys[j]));
            }

            choiceIdx = in.nextInt();
            in.nextLine();

            // Validate user's input
            if (choiceIdx < 0 || choiceIdx >= choiceKeys.length) {
                System.out.println("Invalid choice. Try again.");
            } else {
                validChoice = true;
            }

        } while(!validChoice);

        return choiceKeys[choiceIdx];
    }

    // Print a list of jobs
    private static void printJobs(ArrayList<HashMap<String, String>> someJobs) {

        /* check to see if results are empty
        if so, print an appropriate message
         */
        if (someJobs.isEmpty()) {
            System.out.println("No jobs match selected criteria.");
        } else {
            System.out.println("*****");
            for (Map<String, String> posting : someJobs) {
                for (Map.Entry<String, String> field : posting.entrySet()) {
                    System.out.println(field.getKey() + ": " + field.getValue());
                }
                System.out.println("*****");
            };
        };

            /*for (int i=0; i < someJobs.size(); i++) {
                Iterator it = someJobs.get(i).entrySet().iterator();
                System.out.println("*****");
                while (it.hasNext()) {
                    Map.Entry jobDataReturned = (Map.Entry)it.next();
                    if (jobDataReturned.getKey() == "core competency") {
                        System.out.println(jobDataReturned.getKey() + ": " + jobDataReturned.getValue());
                        System.out.println("*****");
                    } else {
                        System.out.println(jobDataReturned.getKey() + ": " + jobDataReturned.getValue());
                    };
                    it.remove();
                }
            }/*
            /*for (int i = 0; i < someJobs.size(); i++) {
                HashMap<String, String> jobDataReturned = (HashMap<String, String>) someJobs.get(i);
                Set<String> key = jobDataReturned.keySet();
                Iterator it = key.iterator();
                while (it.hasNext()) {
                    String left = (String)it.next();
                    String right = (String) jobDataReturned.get(left);
                    System.out.println("*****");
                    System.out.println(left + ": " + right);
                    System.out.println("*****");
                    it.remove();
                };
            };*/

            /* if results are not empty, create a nested loop to loop over each HashMap until the end of the results*/

        /*System.out.println("*****");*/
    }
}
