package path.vulnerable;

import java.io.*;
import java.util.logging.*;

public class Program {
	private static final String BASE_PATH = "data/public/";
	private static Logger logger = Logger.getLogger(Program.class.getName());
	
	public static void main(String[] args) {
		FileHandler handler = null;
		try {
			handler = new FileHandler("log/path.log", true);
			handler.setFormatter(new SimpleFormatter());
			logger.addHandler(handler);
			
			if (args.length != 1)
				System.out.println("Usage: Program <path>");
			else {
				/* Vulnerability Explanation
				 * directly using unsanitised user input as the path
				*/
				String path = args[0];
				File file = new File(BASE_PATH, path);
				logger.info("Accessing " + file.getAbsolutePath());
				
				// read every line in the file
				try (BufferedReader reader = new BufferedReader(new FileReader(file))) {	
					String line = reader.readLine();
					while(line != null) {
						System.out.println(line);
						logger.info("Read " + line);
						line = reader.readLine();
					}
				}
				catch (FileNotFoundException e) {
					System.out.println("File is not accessible");
					logger.info("File not found");
				}
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			if (handler != null) handler.close();
		}
	}
}
