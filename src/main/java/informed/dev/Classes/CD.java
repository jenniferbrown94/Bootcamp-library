package informed.dev.Classes;

public class CD extends Item {
	private String artist;
	private String genre;
	
	public CD(String name, String artist, String genre, int id) {
		super(name, id, "CD");
		this.artist = artist;
		this.genre = genre;
	}

}
