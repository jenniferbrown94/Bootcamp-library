package informed.dev.Classes;

public class CD extends Item {
	private String artist;
	private String genre;
	
	public CD(String name, String artist, String genre, int id) {
		super(name, id);
		this.artist = artist;
		this.genre = genre;
	}

}
