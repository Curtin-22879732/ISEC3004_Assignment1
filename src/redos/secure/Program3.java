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
				System.out.println("Usage: Program <email>");
			else {
				/*	Mitigation Technique
				 *	using possessive quantifier to avoid backtracking
				*/
				String regex = "^[a-zA-Z0-9](\\.?[a-zA-Z0-9]+)*+@curtin\\.edu\\.au$";
				Pattern pattern = Pattern.compile(regex);
				Matcher matcher = pattern.matcher(args[0]);
				
				long start = System.nanoTime();
				boolean result = matcher.matches();
				long end = System.nanoTime();
				long time = (end - start) / 1000000;
				
				String out = String.format("Result: %b; Time: %dms", result, time);
				System.out.println(out);
				logger.info(out);
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
