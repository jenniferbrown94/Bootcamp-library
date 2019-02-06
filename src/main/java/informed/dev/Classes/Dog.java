package informed.dev.Classes;

public class Dog extends Item {
	private String breed;
	private int age;
	private String food;
	private int microchipNumber;
	private String colour;
	
	public Dog(String name, String breed, int age, String food, int microchipNumber, String colour){
		super(name);
		this.breed = breed;
		this.age = age;
		this.food = food;
		this.microchipNumber = microchipNumber;
		this.colour = colour;
	}
	
	//TODO
	public void borrow() {
		
	}

}
