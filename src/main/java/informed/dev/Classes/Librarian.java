package informed.dev.Classes;

public class Librarian extends Person {
	private int employeeId;
	
	public Librarian (String name, int age, int idNumber, int employeeId){
		super(name, age, idNumber);
		this.employeeId = employeeId;
	}
	
}
