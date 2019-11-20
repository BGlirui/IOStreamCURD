package action;

import java.io.Serializable;

public class Employee implements Serializable {
	public final static long serialVersionUID = 1L;
	private int id = 0;
	private String name = null;
	private String position = null;
	private double salary = 0.00;
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return this.id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setPosition(String position) {
		this.position = position;
	}
	
	public String getPosition() {
		return this.position;
	}
	
	public void setSalary(double salary) {
		this.salary = salary;
	}
	
	public double getSalary() {
		return this.salary;
	}
}
