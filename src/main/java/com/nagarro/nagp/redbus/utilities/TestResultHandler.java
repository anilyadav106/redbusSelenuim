package com.nagarro.nagp.redbus.utilities;
import java.nio.file.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestResultHandler {

	/*
	 * method to archive previous results to "Archived test results" folder before
	 * start of test
	 */	
    public static void archivePreviousResults() {
    	
        Path currentTestResultsFolder = Paths.get("Current test results");
        Path archivedTestResultsFolder = Paths.get("Archived test results");

        try {
            // To create "Archived test results" folder if it does not exists already
            Files.createDirectories(archivedTestResultsFolder);

            // To move contents of "Current test results" to "Archived test results" on start of test
            Files.move(currentTestResultsFolder, archivedTestResultsFolder.resolve(getFormattedDateTime()),
                    StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // To store test results in "Current test results" folder
    public static void storeTestResults() {
        Path currentTestResultsFolder = Paths.get("Current test results");

        try {
            // To create "Current test results" folder if it does not exist already
            Files.createDirectories(currentTestResultsFolder);

             
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // The Helper method to get formatted current date-time stamp of the sub folder
    private static String getFormattedDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String DateTimeSubfolderName=dateFormat.format(new Date());
        return DateTimeSubfolderName;
    }
}