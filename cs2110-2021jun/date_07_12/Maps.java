package date_07_12;

import java.util.HashMap;
import java.util.Map;

public class Maps {

	public static void main(String[] args) {
		Map<String, Integer> nameToYear = new HashMap<>();
		nameToYear.put("Einstein", 1879);
		nameToYear.put("Curie", 1867);
		nameToYear.put("Newton", 1643);
		nameToYear.put("Galileo", 1564);
		Map<Integer, String> yearToName = new HashMap<>();
		yearToName.put(1879, "Einstein");
		yearToName.put(1867, "Curie");
		yearToName.put(1643, "Newton");
		yearToName.put(1564, "Galileo");
		
		System.out.println(yearToName.get(1879));

	}

}
