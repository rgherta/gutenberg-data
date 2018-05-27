package wordcounts;

import java.util.Collections;

import com.google.devtools.common.options.Option;
import com.google.devtools.common.options.OptionsBase;
import com.google.devtools.common.options.OptionsParser;

public class ConsoleArguments extends OptionsBase  {


    @Option(
            name = "bookId",
            abbrev = 'b',
            help = "The book id on ProjectGutenberg",
            category = "Documentation",
            defaultValue = ""
    )
    public String bookId;
    
    
    static void printUsage(OptionsParser parser) {
        System.out.println("Usage: java -jar wordcounts.jar OPTIONS");
        System.out.println(parser.describeOptions(Collections.<String, String>emptyMap(),
                OptionsParser.HelpVerbosity.LONG));
    }

}


