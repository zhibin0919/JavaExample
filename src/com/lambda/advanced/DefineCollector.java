package com.lambda.advanced;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.lambda.basic.Transcript;
import com.sun.corba.se.impl.encoding.OSFCodeSetRegistry.Entry;

public class DefineCollector {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Transcript> studentList = new ArrayList<Transcript>();
		Transcript t1 = new Transcript(2,"Ben", "Male", 87, 91, 80);
		Transcript t2 = new Transcript(1,"Sjkok", "Female", 94, 99, 90);
		Transcript t3 = new Transcript(3,"Aaron", "Male", 44, 55, 100);
		Transcript t4 = new Transcript(4,"Zhibin", "Male", 77, 88, 66);
		studentList.add(t1);
		studentList.add(t2);
		studentList.add(t3);
		studentList.add(t4);
		
		System.out.println(studentList.stream().collect(new ExcellentMenu(80)));
	}

}

class ExcellentMenu implements Collector<Transcript, Map<Transcript, Integer>, List<String>> {
	int standard;
	
	public ExcellentMenu(int standard){
		this.standard = standard;
	}
	
	@Override
	public Supplier<Map<Transcript, Integer>> supplier() {
		// TODO Auto-generated method stub
		return () -> new HashMap<>();
	}

	@Override
	public BiConsumer<Map<Transcript, Integer>, Transcript> accumulator() {
		// TODO Auto-generated method stub
		return (acc, elem) -> {
			int i = 0;
			if(elem.getChinese() >= standard)i++;
			if(elem.getEnglish() >= standard)i++;
			if(elem.getMath() >= standard)i++;
			acc.put(elem, i);
		};
	}

	@Override
	public BinaryOperator<Map<Transcript, Integer>> combiner() {
		// TODO Auto-generated method stub
		return (acc1, acc2) -> {
			acc1.putAll(acc2);
			return acc1;
		};
	}

	@Override
	public Function<Map<Transcript, Integer>, List<String>> finisher() {
		// TODO Auto-generated method stub
		return (accmulator) -> {
			int max = accmulator.entrySet().stream()
					  .reduce((elem1, elem2) -> elem1.getValue() > elem2.getValue() ? elem1 : elem2)
					  .map(entry -> entry.getValue()).get();
			return accmulator.entrySet().stream()
					.filter(n -> n.getValue() == max)
					.map(entry -> entry.getKey().getName())
					.collect(Collectors.toList());
		};
	}

	@Override
	public Set<java.util.stream.Collector.Characteristics> characteristics() {
		// TODO Auto-generated method stub
		return Collections.emptySet();
	}
}