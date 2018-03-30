/* Vince Chang
 * REST API Training
 * 3/28/18
 */

package com.macys.rest;

import java.util.List;

public class Boat {

	private String name;
	private int hp;
	private int length;
	private String color;
	private String make;
	
	public Boat() {}

	public Boat(String name, int hp, int length, String color, String make,
	 	List<Employee> employees) {
		super();
		this.name = name;
		this.hp = hp;
		this.length = length;
		this.color = color;
		this.make = make;
		this.employees = employees;
	}

	@Override
	public String toString() {
		return "Boat [name=" + name + ", hp=" + hp + ", length=" + length + ",
		color=" + color + ", make=" + make + ", employees=" + employees + "]";
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getMake() {
		return make;
	}
	public void setMake(String make) {
		this.make = make;
	}
	public List<Employee> getEmployees() {
		return employees;
	}
	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}
	private List<Employee> employees;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Boat other = (Boat) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}