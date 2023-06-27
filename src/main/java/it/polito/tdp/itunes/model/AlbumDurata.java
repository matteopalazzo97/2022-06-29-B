package it.polito.tdp.itunes.model;

public class AlbumDurata {
	
	private Album a;
	private double durata;
	
	public AlbumDurata(Album a, double durata) {
		super();
		this.a = a;
		this.durata = durata;
	}
	public Album getA() {
		return a;
	}
	public void setA(Album a) {
		this.a = a;
	}
	public double getDurata() {
		return durata;
	}
	public void setDurata(double durata) {
		this.durata = durata;
	}
	
	
	
	

}
