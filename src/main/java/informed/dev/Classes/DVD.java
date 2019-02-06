package informed.dev.Classes;

public class DVD extends Item {
	private String director;
	private String genre;
	private int runTime;
	
	public DVD(String name, String director, String genre, int runTime) {
		super(name);
		this.director = director;
		this.genre = genre;
		this.runTime = runTime;
	}

}
