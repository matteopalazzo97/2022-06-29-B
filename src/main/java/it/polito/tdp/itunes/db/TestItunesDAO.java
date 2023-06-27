package it.polito.tdp.itunes.db;

import java.util.List;
import java.util.LinkedList;

public class TestItunesDAO {

	public static void main(String[] args) {
		/*ItunesDAO dao = new ItunesDAO();
		System.out.println(dao.getAllAlbums().size());
		System.out.println(dao.getAllArtists().size());
		System.out.println(dao.getAllPlaylists().size());
		System.out.println(dao.getAllTracks().size());
		System.out.println(dao.getAllGenres().size());
		System.out.println(dao.getAllMediaTypes().size());*/
		
		
		List<Integer> lista = new LinkedList();
		
		for(int i=0; i<=5; i++) {
			lista.add(i);
		}
		for(int i=0; i<=5; i++) {
			System.out.println(i);
		}
	}

}
