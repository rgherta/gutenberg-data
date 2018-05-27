package wordcounts;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

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
    

}
