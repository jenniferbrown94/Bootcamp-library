package informed.dev.Classes;

public class Book extends Item {
	private int ISBN;
	private String author;
	private String genre;
	
	public Book(String name, int ISBN, String author, String genre, int id) {
		super(name, id, "Book");
		this.ISBN = ISBN;
		this.author = author;
		this.genre = genre;
	}
	
	public String getAuthor() {
		return author;
	}

}
