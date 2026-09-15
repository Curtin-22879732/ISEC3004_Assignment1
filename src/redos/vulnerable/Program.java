package redos.vulnerable;

import java.io.IOException;
import java.util.logging.*;
import java.util.regex.*;

public class Program {
	private static Logger logger = Logger.getLogger(Program.class.getName());
	
	public static void main(String[] args) {
		FileHandler handler = null;
		try {
			handler = new FileHandler("log/redos.log", true);
			handler.setFormatter(new SimpleFormatter());
			logger.addHandler(handler);
			
			if (args.length != 1)
				System.out.println("Usage: Program <email>");
			else {
				/*	Vulnerability Explanation
				 *	^[a-zA-Z0-9] -> starts with a alphanumeric character
				 *	(\.?[a-zA-Z0-9]+)* -> follows by any number of groups of: an optional dot and at least one alphanumeric characters
				 *		using overlapping, nested quantifier, which leads to an exponential number of backtracking combinations
				 *	@curtin\.edu\.au$ -> ends with '@curtin.edu.au'
				 *	e.g. a@curtin.edu.au, ab.cd@curtin.edu.au, ab.cd.ef@curtin.edu.au
				*/
				String regex = "^[a-zA-Z0-9](\\.?[a-zA-Z0-9]+)*@curtin\\.edu\\.au$";
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
