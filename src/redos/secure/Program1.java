package redos.secure;

import java.io.IOException;
import java.util.logging.*;
import java.util.regex.*;

public class Program1 {
	private static Logger logger = Logger.getLogger(Program1.class.getName());
	
	public static void main(String[] args) {
		FileHandler handler = null;
		try {
			handler = new FileHandler("log/redos.log", true);
			handler.setFormatter(new SimpleFormatter());
			logger.addHandler(handler);
			
			if (args.length != 1)
				System.out.println("Usage: Program1 <email>");
			else {
				/* Mitigation Technique
				 * still using nested quantifier, but no longer overlapping as each group must start with a dot
				 * 1. starts with some alphanumeric characters
				 * 2. follows by any groups of: a dot + some alphanumeric characters
				 * 3. ends with @curtin.edu.au
				*/
				String regex = "^[a-zA-Z0-9]+(\\.[a-zA-Z0-9]+)*@curtin\\.edu\\.au$";
				Pattern pattern = Pattern.compile(regex);
				Matcher matcher = pattern.matcher(args[0]);
				logger.info("Matching " + args[0].length() + " characters");
				
				long start = System.nanoTime();
				boolean result = matcher.matches();
				long end = System.nanoTime();
				long time = (end - start) / 1000000;
				
				System.out.println("Result: " + result);
				System.out.println("Time: " + time + "ms");
				logger.info("Computed " + result + " in " + time + "ms");
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
