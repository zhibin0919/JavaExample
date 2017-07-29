package com.lambda.basic;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StringProcess {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("IntStream range 應用1");
		String title = "Java Basic Lessons";
		String intro1 = title+" --> variables";
		String emptyStr = IntStream.range(0, title.length())
							  .mapToObj(i -> " ")
							  .collect(Collectors.joining(""));
		String intro2 = emptyStr+" --> condition control, flow control";
		
		System.out.println(intro1+"\n"+intro2);
		
		System.out.println();
		
		System.out.println("IntStream range 應用2");
		String sports[] = {"jogging", "badminton", "both"};
		String bodys[]  = {"feet", "hand", "feet and hand"};
		IntStream.range(0, sports.length).forEach(index -> {
			System.out.println(sports[index]+" can train "+bodys[index]);
		});
		
	}

}
