package Projet_Math ; 



public class GraphLinearDirected {


	private byte [][] matrice;
	private int [][] listeAdjacence;
	private int[] vertexTab;
	private final int ordre;
	
	
	public GraphLinearDirected (int n) {
		this.matrice = new byte[n][n];
		this.listeAdjacence = new int[n][];
		this.vertexTab = this.getVertexSet();
		this.ordre = n; 
	}
	
	
	public boolean isVertex(int vertex) {
		return (1 <= vertex && vertex <= this.getOrdre());	
	}
	
	
	public boolean isEdge(int source, int target) { // Verifie si il existe un chemin entre 2 vertex
		if( !isVertex(source) && !isVertex(target) )
			throw new myException();
		return (this.matrice[source-1][target] == 1);
	}
	
	public int[] GetAdjacenceyList(int vertex){// Mettre le sommet correspondant -1 pour avoir le bon
		if( !isVertex(vertex+1)) 
			throw new myException();
		return listeAdjacence[vertex-1];
	}
	
	
	public int getOutDegree(int vertex){
		if( !isVertex(vertex)) 
			throw new myException();
		return this.listeAdjacence[vertex].length -2; // -2 pour enlever la valeur du vertex et le 0 de fin
	}
	
	
	public int getInDegree(int vertex) {
		if( !isVertex(vertex)) 
			throw new myException();
		return 0;
		
	}
	
	public int[] getVertexSet() {
		int ordrematrice = this.getOrdre();
		int [] tab = new int[ordrematrice];
		for(int i = 1 ; i <= ordrematrice ; i++)
			tab[i-1] = i;
		return tab;
	}
	
	public void SetAdjacenceyMatrix(byte[][] matrix) {
		this.matrice = matrix;
	}
	
	
	public byte[][] GetAdjacenceyMatrix() {
		return this.matrice;
	}
	
	
	public void SetAdjacenceyLists(int[][] lists) {
		this.listeAdjacence = lists;
	}
	
	public int[][] GetAdjacenceyLists() {
		return this.listeAdjacence;
	}

	public int getOrdre() {
		return ordre;
	}
		



}










































