package path.secure;

import java.io.*;
import java.nio.file.*;
import java.util.logging.*;

public class Program3 {
	private static final String BASE_PATH = "data/public/";
	private static Logger logger = Logger.getLogger(Program3.class.getName());
	
	public static void main(String[] args) {
		FileHandler handler = null;
		try {
			handler = new FileHandler("log/path.log", true);
			handler.setFormatter(new SimpleFormatter());
			logger.addHandler(handler);
			
			if (args.length != 1)
				System.out.println("Usage: Program <path>");
			else {
				/*	Mitigation Technique
				 *	using secure library
				*/
				String path = args[0];
				File file = new File(BASE_PATH, path);
				logger.info("Accessing " + file.getAbsolutePath());
				
				if (!file.toPath().toAbsolutePath().normalize().startsWith(Paths.get(BASE_PATH).toAbsolutePath().normalize())) {
					System.out.println("Path is illegal");
					logger.warning("Detected path traversal");
				}
				else {
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
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			if (handler != null) handler.close();
		}
	}
}
