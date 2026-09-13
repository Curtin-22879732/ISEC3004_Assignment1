package redos.vulnerable;

import java.io.IOException;
import java.util.logging.*;
import java.util.regex.*;

public class Program {
	private static Logger logger = Logger.getLogger(Program.class.getName());
	
	public static void main(String[] args) throws IOException {
		if (args.length != 1)
			System.out.println("Usage: ./Program <email>");
		else {
			FileHandler handler = new FileHandler("log/redos.log", true);
			handler.setFormatter(new SimpleFormatter());
			logger.addHandler(handler);
			
			// regex explanation
			// ^[a-zA-Z0-9] -> starts with a alphanumeric character
			// \\.?[a-zA-Z0-9]+ -> followed by an optional dot and >= 1 alphanumerc characters
			// (...+)* -> repeated any number of times (nested quantifier!!!)
			// @curtin.edu.au$ -> ends with @curtin.edu.au
			// e.g. a@curtin.edu.au, a.b@curtin.edu.au, ab.cd@curtin.edu.au
			String regex = "^[a-zA-Z0-9](\\.?[a-zA-Z0-9]+)*@curtin.edu.au$";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(args[0]);
			
			long start = System.nanoTime();
			boolean result = matcher.matches();
			long end = System.nanoTime();
			long time = (end - start) / 1000000;
			
			String out = String.format("Result: %b; Time: %dms\n", result, time);
			System.out.println(out);
			logger.info(out);
		}
	}
}
