package app;

import java.io.*;

/**
 * 
 */

public class FilePlay {

	/**
	 * Copies contents from one file to another.
	 * This method reads a file line by line, processes each line,
	 * and writes the result to an output file. Each line is expected
	 * to have data separated by pipes ('|'), which is then formatted
	 * and written to the output file.
	 *
	 * @param inputFilename  The name of the input file to read from.
	 * @param outputFilename The name of the output file to write to.
	 * @throws FileNotFoundException if the input file does not exist.
	 * @throws IOException if an I/O error occurs during reading or writing.
	 */
    private static void copyFile(String inputFilename, String outputFilename)throws FileNotFoundException, IOException {
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
        } finally {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
        }
    }

    /**
     * The main method of the FilePlay class.
     * It calls the copyFile method to demonstrate file copying.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {

    	try {
            FilePlay.copyFile("InUsers.txt", "OutUsers.txt");
            System.out.println("File was copied successfully!");
        } catch (FileNotFoundException e) {
        	// catch file not found error
        	e.printStackTrace();
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
        	// catch I/O errors
        	e.printStackTrace();
            System.out.println("I/O error: " + e.getMessage());
        }
    }
}
