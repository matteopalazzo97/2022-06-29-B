package it.polito.tdp.itunes.model;

import java.util.List;
import java.util.Collections;
import java.util.LinkedList;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import it.polito.tdp.itunes.db.ItunesDAO;

public class Model {
	
	private ItunesDAO dao;
	private Graph<Album, DefaultWeightedEdge> grafo;
	
	public Model() {
		super();
		this.dao = new ItunesDAO();
	}

	public void creaGrafo(int n) {
		
		this.grafo = new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);
		
		Graphs.addAllVertices(grafo, this.dao.getVertici(n));
		
		List<AlbumDurata> verticiDurata = this.dao.getVerticiDurata(n);
		
		for(int i=0; i<=verticiDurata.size(); i++) {
			for(int j=i+1; j<verticiDurata.size(); j++) {
				if(verticiDurata.get(i).getDurata() != verticiDurata.get(j).getDurata()) {
					if(verticiDurata.get(i).getDurata()+verticiDurata.get(j).getDurata() > 4*n) {
						if(verticiDurata.get(i).getDurata() < verticiDurata.get(j).getDurata()) {
							this.grafo.addEdge(verticiDurata.get(i).getA(),
									verticiDurata.get(j).getA());
							this.grafo.setEdgeWeight(this.grafo.getEdge(verticiDurata.get(i).getA(),
									verticiDurata.get(j).getA()), verticiDurata.get(i).getDurata()
									+ verticiDurata.get(j).getDurata());
						}
						if(verticiDurata.get(i).getDurata() > verticiDurata.get(j).getDurata()) {
							this.grafo.addEdge(verticiDurata.get(j).getA(),
									verticiDurata.get(i).getA());
							this.grafo.setEdgeWeight(this.grafo.getEdge(verticiDurata.get(j).getA(),
									verticiDurata.get(i).getA()), verticiDurata.get(i).getDurata()
									+ verticiDurata.get(j).getDurata());
						}
					}
				}
			}
		}
		
	}
	
	public List<AlbumBilanci> adiacenze(Album album){
		return this.calcolaBilanci(Graphs.successorListOf(grafo, album));
	}

	public List<AlbumBilanci> calcolaBilanci(List<Album> listaAlbum) {

		List<AlbumBilanci> res = new LinkedList<>();


		for(Album a : listaAlbum) {

			double in = 0.0;
			double out = 0.0;

			for(DefaultWeightedEdge e: this.grafo.incomingEdgesOf(a)) {
				in += this.grafo.getEdgeWeight(e);
			}

			for(DefaultWeightedEdge e: this.grafo.outgoingEdgesOf(a)) {
				out += this.grafo.getEdgeWeight(e);
			}

			res.add(new AlbumBilanci(a, in-out));

		}

		Collections.sort(res);

		return res;

	}
	
	public int getNumVertici() {
		return this.grafo.vertexSet().size();
	}

	public int getNumArchi() {
		return this.grafo.edgeSet().size();
	}

	public List<Album> getVertici() {

		List<Album> res = new LinkedList<>();

		for(Album a: this.grafo.vertexSet()) {
			res.add(a);
		}

		//Collections.sort(res);

		return res;
	}
	
}
