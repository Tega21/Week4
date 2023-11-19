package app;

import java.io.*;

public class FilePlay {
	
	/**
	 * Copies the contents of one file to another using FileReader and FileWriter.
	 * This method reads and writes data character by character.
	 *
	 * @param inputFilename  The name of the input file to be read.
	 * @param outputFilename The name of the output file to be written to.
	 * @return An integer indicating the status of the operation: 0 for success,
	 *         -1 for FileNotFoundException, -2 for IOException.
	 */

    private static int copyFile(String inputFilename, String outputFilename) {
        FileReader in = null;
        FileWriter out = null;

        try {
            in = new FileReader(inputFilename);
            out = new FileWriter(outputFilename);

            int c;
            while ((c = in.read()) != -1) {
                out.write(c);
            }
        } catch (FileNotFoundException e) {
            return -1;
        } catch (IOException e) {
            return -2;
        } finally {
            try {
                if (in != null) {
                    out.close();
                }
                if (in != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    /**
     * The main method for the FilePlay application. This method calls the copyFile method
     * to demonstrate basic file I/O operations and error handling.
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
