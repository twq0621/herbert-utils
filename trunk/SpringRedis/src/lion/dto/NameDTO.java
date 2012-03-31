package lion.dto;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class NameDTO {

	private String name;

	private String passwprd;

	private int age;

	private float money;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPasswprd() {
		return passwprd;
	}

	public void setPasswprd(String passwprd) {
		this.passwprd = passwprd;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public float getMoney() {
		return money;
	}

	public void setMoney(float money) {
		this.money = money;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

}
