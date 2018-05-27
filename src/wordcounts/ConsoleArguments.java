package wordcounts;

import com.google.devtools.common.options.Option;
import com.google.devtools.common.options.OptionsBase;

public class ConsoleArguments extends OptionsBase  {


    @Option(
            name = "bookId",
            abbrev = 'b',
            help = "The book id on ProjectGutenberg",
            category = "Documentation",
            defaultValue = ""
    )
    public String bookId;

}


