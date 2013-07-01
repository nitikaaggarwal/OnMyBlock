import java.util.HashMap;
import java.io.IOException;

public class Driver
{

	public static void main (String[] args)
	{
	
		// create empty HashMap
		HashMap<String,Object> h  = new HashMap<String,Object> ();

		// if file name is supplied as command line
		// parameter
		if(args.length != 0)
		{
			try
			{
				// parse the file
				h = Parser.parseFile (args[0]);
			}
			catch(IOException e)
			{
				System.err.println ("Bummer!! Failed to read file: " + args[0]);
				System.exit (-1);
			}

			// print all key-value pairs
			System.out.println (h);
		}
		// otherwise, print error message
		else
		{
			System.out.println("usage: java Driver <configFileName>");
			System.exit (0);
		}

	}

}
