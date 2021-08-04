package date_07_19;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class MapsV2 {
	public static void demoNameToYear(Map<String, Integer> nameToYear ) {
		nameToYear.put("Einstein", 1879);
		nameToYear.put("Curie", 1867);
		nameToYear.put("Newton", 1643);
		nameToYear.put("Galileo", 1564);
		System.out.println(nameToYear.get("Curie"));
	}

	public static void demoYearToName(Map<Integer, String> yearToName) {
		yearToName.put(1879, "Einstein");
		yearToName.put(1867, "Curie");
		yearToName.put(1643, "Newton");
		yearToName.put(1564, "Galileo");
		System.out.println(yearToName.get(1867));
	}

	public static void main(String[] args) {
//		Map<String, Integer> nameToYear = new HashMap<>();
		Map<String, Integer> nameToYear = new TreeMap<>();
		demoNameToYear(nameToYear);
		Map<Integer, String> yearToName = new HashMap<>();
		demoYearToName(yearToName);
	}
}
