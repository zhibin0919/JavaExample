package com.lambda.basic;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class MapMethod {
	static Map<String, Integer> idMapSalary;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int basic_salary = 130;
		idMapSalary = new HashMap<String, Integer>();
		idMapSalary.put("1", 140);
		idMapSalary.put("2", 150);
		
		System.out.println("#所有id皆呼叫function，但id value不存在才set值");
		IntStream.range(1, 5)
				 .mapToObj(i -> String.valueOf(i))
                 .forEach(i -> { 
                	 idMapSalary.putIfAbsent(i, addHourSalaryForOddId(i, basic_salary));
                	 printResult(i, idMapSalary.get(i));
                 });
		
		System.out.println("\n#id value不存在才呼叫function");
		IntStream.range(1, 7)
		         .mapToObj(i -> String.valueOf(i))
	             .forEach(i -> {
	            	 printResult(i, idMapSalary.computeIfAbsent(i,  key -> 
	            	 	addHourSalaryForOddId(key, basic_salary)));
	             });
		
		System.out.println("\n#id value存在才呼叫function");
		IntStream.range(1, 9)
		         .mapToObj(i -> String.valueOf(i))
	             .forEach(i -> {
	            	 printResult(i, idMapSalary.computeIfPresent(i, (key, val) -> 
	            	 	addHourSalaryForOddId(key, val)));
	             });
		
		System.out.println("\n#所有id皆呼叫function");
		IntStream.range(1, 9)
		         .mapToObj(i -> String.valueOf(i))
		         .forEach(i -> {
		        	 printResult(i, idMapSalary.compute(i, (key, val) -> 
		        	 	(val != null) ? addHourSalaryForOddId(key, val) : 100));
		         });
		
		System.out.println("\n#所有id皆呼叫function");
		IntStream.range(1, 9)
		         .mapToObj(i -> String.valueOf(i))
		         .forEach(i -> {
		        	 printResult(i, idMapSalary.merge(i, basic_salary, (oldValue, Value) -> 
		        	 (oldValue <= Value) ? oldValue + 10 : Value));
		         });
	}
	
	public static void printResult(String id, Integer value){
		System.out.println("No."+id+" hour sallary="+value);
	}
	
	public static Integer addHourSalaryForOddId(String id, int salary){
		System.out.println(id);
		//if(salary != null)
			return (Integer.parseInt(id) % 2 == 1) ? salary + 10 : salary;
		//return salary;
	}
}
