package wordcounts;

import static java.util.Collections.reverseOrder;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ParseUtils {




	static Map<String, Long> TopWords(Map<String, Long> map, int limit){

		return map.entrySet().stream()
				.sorted(reverseOrder(Map.Entry.comparingByValue())).limit(limit)
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
						(oldValue, newValue) -> oldValue, LinkedHashMap::new));

	}




}



