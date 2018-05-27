package wordcounts;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import static wordcounts.ConsoleArguments.printUsage;

import static java.util.Collections.reverseOrder;

import com.google.devtools.common.options.OptionsParser;

import wordcounts.ParseUtils.TopWords;


public class Main {
	private static final int LIMIT = 15;

	public static void main(String[] args) throws URISyntaxException, IOException {
		//Parse console arguments
        OptionsParser parser = OptionsParser.newOptionsParser(ConsoleArguments.class); 
        parser.parseAndExitUponError(args);
        ConsoleArguments options  =  parser.getOptions(ConsoleArguments.class);
        
        if (options.bookId.isEmpty()) {
            printUsage(parser);
            return;
        }
		
        URL myUrl = NetworkUtils.buildUri(options.bookId);
        System.out.println("Parsing: " + myUrl.toString());
        
        HttpURLConnection urlConnection = (HttpURLConnection) myUrl.openConnection();
        urlConnection.setConnectTimeout(5000);
        urlConnection.setReadTimeout(10000);
        InputStream in = urlConnection.getInputStream();
        
        Map<String, Long> map = new HashMap<>();
        
        try(Scanner scanner = new Scanner(in, "UTF-8")) {
        	
        	String word = new String();
        	long count = 1;
        	
            while (scanner.hasNext()) {
            	word = scanner.next();
            	word = word.replaceAll("[^a-zA-Z0-9]", "");
            	word = word.toLowerCase();
            	
            	if(!map.containsKey(word)) {
            		count=1;
            		map.put(word, count);
            	} else {
            		count = map.get(word);
            		map.replace(word, count, count+1);
            		
            	}
            	 
            }
            
        }
        finally {
           urlConnection.disconnect();
        }

        
        
        //Most Frequent Words
        Map<String, Long> sortedMap = ParseUtils.TopWords(map, LIMIT);
        System.out.println("Most Frequent:" + sortedMap);
			
			
	}
	

	
	
	
	
	
	

}
