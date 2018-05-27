package wordcounts;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class NetworkUtils {
  
    private static final String scheme = "https";
    private static final String host = "www.gutenberg.org";

    //Builds URL
    static URL buildUri(String repo) throws URISyntaxException, MalformedURLException {
    	String path = String.format("/files/%s/%s-0.txt", repo, repo);
        URL url = null;
        URI builtUri = new URI(scheme, host, path, null, null);
        url = builtUri.toURL();

        return url;
    }
    
	static Map<String, Long> getWordsMap(URL myUrl) throws IOException{
		
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
		
		return map;

	}

    

}
