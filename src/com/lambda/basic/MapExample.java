package com.lambda.basic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MapExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Map<String, List<String>> relation = new HashMap<String, List<String>>();
		List<String> child = new ArrayList<String>();
		child.add("Tom");
		child.add("Ben");
		relation.put("class A", child);
		
		List<String> child2 = new ArrayList<String>();
		child2.add("Mary");
		child2.add("Jacky");
		relation.put("class B", child2);
		
		String search = "Tom";
		
		String getClass = relation.entrySet().stream()
				.filter(c -> c.getValue().contains(search))
				.map(entry -> entry.getKey())
				.collect(Collectors.joining());
		System.out.println(getClass);
	}

}
