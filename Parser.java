import java.util.Scanner;
import java.io.File;
import java.util.HashMap;
import java.io.IOException;
import java.lang.NumberFormatException;

public class Parser
{
	
	public static HashMap<String, Object> parseFile (String fileName)
		throws IOException
	{

		// open file for reading
		Scanner s = new Scanner(new File (fileName, "r"));
		// create empty HashMap to store key-value pairs
		HashMap<String,Object> h = new HashMap<String,Object> ();

		// loop through each line in file
		while(s.hasNextLine())
		{
			// read line, removing leading and trailing whitespaces
			String line = (s.nextLine ()).trim ();

			// if line is not a comment
			if (!line.isEmpty () && !line.startsWith("#"))
			{
				// split the line around "=" symbol
				String[] tokens = line.split("=");

				// must have length 2 to be valid
				if(tokens.length == 2)
				{
					// remove leading/trailing whitespaces
					// from key-value pair
					tokens[0] = tokens[0].trim ();
					tokens[1] = tokens[1].trim ();

					// if non-empty, add to HashMap
					if (!tokens[0].isEmpty () && !tokens[1].isEmpty ())
						h.put (tokens[0].trim (), typeConvert (tokens[1].trim ()));
				}
			}

		}
			
		return h;

	}

	private static Object typeConvert (String value)
	{

		// return true if "on", "yes", or "true"
		if (value.equalsIgnoreCase("on") || value.equalsIgnoreCase("true") || value.equalsIgnoreCase("yes"))
			return new Boolean (true);

		// return false if "off", "no", "false"
		if (value.equalsIgnoreCase("off") || value.equalsIgnoreCase("false") || value.equalsIgnoreCase("no"))
			return new Boolean (false);

		// check if it's an Integer
		try
		{
			return Integer.parseInt (value);
		}
		catch (NumberFormatException e0)
		{
			// if that fails, check if Double
			try
			{
				return Double.parseDouble (value);
			}
			catch (NumberFormatException e1)
			{
				// must not be a number, phew
				return value;
			}
		}
	}

}
