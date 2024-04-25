package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Calculation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private int number1;
	private int number2;
	
	private String operation;
	
	public Calculation() {
		
	}
	
	public String getOperation() {
		return operation;
	}
	public void setOperation(String newOperation) {
		operation = newOperation;
	}
	
	public int getNumber1() {
		return number1;
	}
	
	public void setNumber1(int value) {
		number1 = value;
	}
	
	public int getNumber2() {
		return number2;
	}
	
	public void setNumber2(int value) {
		number2 = value;
	}
	
	public void setId(Long newId) {
		id = newId;
	}
}