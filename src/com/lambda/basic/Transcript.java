package com.lambda.basic;

public class Transcript implements Cloneable{
	private int id;
	private String name;
	private String sex;
	private int chinese;
	private int math;
	private int english;
	private int sum;
	
	public Transcript(int id, String name, String sex, int chinese, int math, int english) {
		super();
		this.id = id;
		this.sex = sex;
		this.name = name;
		this.chinese = chinese;
		this.math = math;
		this.english = english;
		this.sum = chinese + math + english;
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
	public int getSum() {
		return sum;
	}
	public void setSum(int sum) {
		this.sum = sum;
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