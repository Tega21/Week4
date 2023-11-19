package app;

import java.io.*;

public class FilePlay {

	/**
	 * Enhanced version of file copying using BufferedReader and BufferedWriter with
	 * specific error handling. The method reads and processes lines from the input file
	 * and writes them to the output file. It returns specific error codes for different
	 * types of exceptions encountered during the operation.
	 *
	 * @param inputFilename  The name of the input file to be read.
	 * @param outputFilename The name of the output file to be written to.
	 * @return An integer indicating the status of the operation: 0 for success,
	 *         -1 for FileNotFoundException, -2 for IOException.
	 */
    private static int copyFile(String inputFilename, String outputFilename) {
        BufferedReader in = null;
        BufferedWriter out = null;

        try {
            in = new BufferedReader(new FileReader(inputFilename));
            out = new BufferedWriter(new FileWriter(outputFilename));

            String line;
            while ((line = in.readLine()) != null) {
                String[] tokens = line.split("\\|");
                out.write(String.format("Name is %s %s of age %s\n", tokens[0], tokens[1], tokens[2]));
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
            return -1;
        } catch (IOException e) {
            System.err.println("I/O error: " + e.getMessage());
            return -2;
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                System.err.println("Error closing file streams: " + e.getMessage());
            }
        }
        return 0;
    }

    /**
     * The main method for the FilePlay application. This version enhances error handling
     * by using specific return codes from the copyFile method and handling them with a
     * switch statement.
     *
     * @param args Command line arguments (not used in this application).
     */

    public static void main(String[] args) {

        int err = FilePlay.copyFile("InUsers.txt", "OutUsers.txt");

        switch (err){
            case 0:
                System.out.println("File was copied successfully!");
                break;
            case -1:
                System.out.println("File could not be opened.");
                break;
            case -2:
                System.out.println("File I/O error");
                break;
        }

    }
}
