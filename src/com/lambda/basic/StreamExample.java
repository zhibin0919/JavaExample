package com.lambda.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub		
		List<Transcript> studentList = new ArrayList<Transcript>();
		Transcript t1 = new Transcript(2,"Ben", "Male", 87, 91, 80);
		Transcript t2 = new Transcript(1,"Sjkok", "Female", 94, 99, 65);
		Transcript t3 = new Transcript(3,"Aaron", "Male", 44, 55, 100);
		Transcript t4 = new Transcript(4,"Zhibin", "Male", 77, 88, 66);
		studentList.add(t1);
		studentList.add(t2);
		studentList.add(t3);
		studentList.add(t4);
		
		//Use Collections's sort method
		Collections.sort(studentList, (o1, o2) -> o1.getName().compareTo(o2.getName()));	
		studentList.forEach(t -> System.out.println("ID:"+t.getId()+"\tName:"+t.getName()+"\tChinese:"+t.getChinese()+"\tMath:"+t.getMath()+"\tEnglish:"+t.getEnglish()));
		
		System.out.println("=======================================");
		
		System.out.println("filter的使用");
		//Use filter and count method
		long mathPassCount = studentList.stream()
										.filter(trans -> trans.getMath() > 60)
										.count();
		System.out.println(mathPassCount);
		
		System.out.println("=======================================");
		
		System.out.println("collect的使用");
		//Use filter and collect method
		List<Transcript> chineseNoPass = studentList.stream()
													.filter(trans -> trans.getChinese() < 60)
													.collect(Collectors.toList());
		chineseNoPass.forEach(t -> System.out.println(t.getName()));
		
		System.out.println("=======================================");
		
		System.out.println("map的使用");
		List<String> collected = Stream.of("ben", "sjkok", "jack", "aaron")
                                       .map(String::toUpperCase)
                                       .collect(Collectors.toList());
		collected.forEach(string -> System.out.println(string));
		
		System.out.println("=======================================");
		
		System.out.println("混合的使用");
		//average > 80's student
		List<String> excellentName = studentList.stream()
												.filter(t -> {
													int sum = t.getChinese()+t.getEnglish()+t.getMath();
													return sum/3 > 80;
												})
												.map(t -> t.getName())
												.collect(Collectors.toList());
		excellentName.forEach(name -> System.out.println(name));
		
		System.out.println("==============以下為進階操作============");
		
		System.out.println("單一條件群組的使用");
		//區分成績單中的男性與女性人員
		Map<String, List<Transcript>> transBySex = null;
		transBySex = studentList.stream()
								.collect(Collectors.groupingBy(Transcript::getSex));
		transBySex.forEach((sex, trans) -> {
			System.out.println(sex+"=>"+
					trans.stream()
						 .map(t -> t.getName())
						 .collect(Collectors.joining(", ", "[", "]")));
		});
		
		System.out.println("=======================================");
		
		System.out.println("多個條件群組的使用");
		//計算成績單中的男性與女性數學的平均值
		Map<String, Double> avgMathBySex = null;
		avgMathBySex = studentList.stream()
								  .collect(Collectors.groupingBy(Transcript::getSex,
										  Collectors.averagingInt(Transcript::getMath)));
		avgMathBySex.forEach((sex , avgmath) -> System.out.println(sex+"=>"+avgmath));
		
		System.out.println("=======================================");
		Sclass sclass[] = new Sclass[2];
		sclass[0] = new Sclass("ClassA", studentList);
		List<Transcript> studentListB = studentList.stream()
												   .map(t -> {
													   Transcript tc = t.clone();
													   tc.setName(tc.getName()+"*");
													   return tc;
													})
												   .collect(Collectors.toList());
		sclass[1] =  new Sclass("ClassB", studentListB);
		
		Set<String> student = new HashSet<String>();
		System.out.println("使用forEach");
		Stream.of(sclass)
			  .forEach(s -> {
					s.getTranscript().stream()
					 .filter(t -> t.getEnglish() > 70)
					 .map(t -> t.getName())
					 .forEach(name -> student.add(name));
			  });
		System.out.println(student);
		
		student.clear();
		System.out.println("使用flatMap");
		Stream.of(sclass)
			  .flatMap(s -> s.getTranscript().stream())
		      .filter(t -> t.getEnglish() > 70)
		      .map(t -> t.getName())
		      .forEach(name -> student.add(name));
		System.out.println(student);
	}

}

class Sclass{
	private String sname;
	private List<Transcript> transcript;
	public Sclass(String sname, List<Transcript> transcript) {
		super();
		this.sname = sname;
		this.transcript = transcript;
	}
	public String getSname() {
		return sname;
	}
	public List<Transcript> getTranscript() {
		return transcript;
	}
}

class Transcript implements Cloneable{
	private int id;
	private String name;
	private String sex;
	private int chinese;
	private int math;
	private int english;
	
	public Transcript(int id, String name, String sex, int chinese, int math, int english) {
		super();
		this.id = id;
		this.sex = sex;
		this.name = name;
		this.chinese = chinese;
		this.math = math;
		this.english = english;
	}
	public int getId() {
		return id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public int getChinese() {
		return chinese;
	}
	public int getMath() {
		return math;
	}
	public int getEnglish() {
		return english;
	}
	public String getSex() { 
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Transcript clone(){
		Transcript transcript = null;
		try {
			transcript = (Transcript) super.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return transcript;
	}
}
