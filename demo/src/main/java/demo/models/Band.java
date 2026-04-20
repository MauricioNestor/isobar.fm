package demo.models;

import java.util.List;

public class Band {
	
	private String id;
	private String name;
	private String genre;
	private String biography;
	private Long numPlays;
	
	private List<String> albums;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getBiography() {
		return biography;
	}

	public void setBiography(String biography) {
		this.biography = biography;
	}

	public Long getNumPlays() {
		return numPlays;
	}

	public void setNumPlays(Long numPlays) {
		this.numPlays = numPlays;
	}

	public List<String> getAlbums() {
		return albums;
	}

	public void setAlbums(List<String> albums) {
		this.albums = albums;
	}
	
}