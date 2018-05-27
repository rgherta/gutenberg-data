package wordcounts;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Map;
import static wordcounts.ConsoleArguments.printUsage;

import com.google.devtools.common.options.OptionsParser;


public class Main {
	
	private static final int LIMIT = 15;

	public static void main(String[] args) throws URISyntaxException, IOException {
		
		//Parse console arguments
		OptionsParser parser = OptionsParser.newOptionsParser(ConsoleArguments.class); parser.parseAndExitUponError(args);
		ConsoleArguments options  =  parser.getOptions(ConsoleArguments.class);
		
		
		if (options.bookId.isEmpty()) {
			printUsage(parser);
			return;
		}

		
		URL myUrl = NetworkUtils.buildUri(options.bookId);
		System.out.println("Parsing: " + myUrl.toString());

		Map<String, Long> map = NetworkUtils.getWordsMap(myUrl);

		//Most Frequent Words
		Map<String, Long> sortedMap = ParseUtils.TopWords(map, LIMIT);
		System.out.println("Most Frequent:" + sortedMap);


	}



}
