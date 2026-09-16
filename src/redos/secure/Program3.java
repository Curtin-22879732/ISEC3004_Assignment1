package redos.secure;

import java.io.IOException;
import java.util.logging.*;
import java.util.regex.*;

public class Program3 {
	private static Logger logger = Logger.getLogger(Program3.class.getName());
	
	public static void main(String[] args) {
		FileHandler handler = null;
		try {
			handler = new FileHandler("log/redos.log", true);
			handler.setFormatter(new SimpleFormatter());
			logger.addHandler(handler);
			
			if (args.length != 1)
				System.out.println("Usage: Program3 <email>");
			else {
				/* Mitigation Technique
				 * using possessive quantifier to avoid backtracking
				 * 1. starts with an alphanumeric character
				 * 2. follows by any groups of: an optional dot + some alphanumeric characters (anti-backtracking)
				 * 3. ends with @curtin.edu.au
				*/
				String regex = "^[a-zA-Z0-9](\\.?[a-zA-Z0-9]+)*+@curtin\\.edu\\.au$";
				Pattern pattern = Pattern.compile(regex);
				Matcher matcher = pattern.matcher(args[0]);
				logger.info("Matching " + args[0].length() + " characters");
				
				long start = System.nanoTime();
				boolean result = matcher.matches();
				long end = System.nanoTime();
				long time = (end - start) / 1000000;
				
				System.out.println("Result: " + result);
				System.out.println("Time: " + time + "ms");
				logger.info("Computed " + result + " in " + time + "ms");;
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
