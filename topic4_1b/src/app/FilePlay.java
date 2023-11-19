package app;

import java.io.*;

public class FilePlay {

	/**
	 * Copies the contents of one file to another using BufferedReader and BufferedWriter.
	 * This method reads and writes data line by line, which is more efficient than
	 * character-by-character operations. It also processes each line, splitting the data
	 * using a pipe delimiter and formatting it before writing.
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
     * The main method for the FilePlay application. It demonstrates file copying using
     * BufferedReader and BufferedWriter for efficient line-by-line file I/O operations.
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
