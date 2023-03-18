package src ;
import  Projet_Math.* ;
import java.io.File;
import java.util.*;  
import fr_departments.* ;
import java.io.FileNotFoundException;
import java.io.IOException ;

public class Main {

	public static void main(String[] args)throws FileNotFoundException  {
	
		String fichier = "Projet_Math/Data/graph-001.alists" ;
		Scanner file = new Scanner(new File(fichier));
		int n = file.nextInt();
		GraphLinearDirected g = new GraphLinearDirected(n); // Graph initialisé
		int [][] t = Matrice.getAdjacencyList(file,n) ; //Recupération des données 
		System.out.println(" ");
		g.SetAdjacenceyLists(t) ; // Init de la Liste d'adjacence
		byte [][] matrice = Matrice.computeAdjacencyMatrice(g.GetAdjacenceyLists()) ; // Transforme la Liste en Matrice d'adjacence
		g.SetAdjacenceyMatrix( matrice  ); // Init de la Matrice d'adjacence
		
		// ----get ligne listAdjacente---
		int [] tab = g.getVertexSet() ;// Liste departement pour faire le choix de depart
		
		//----------Dialogue-------------------
		Dialogue.Presente() ;
		//Dialogue.choose(g) ;
		Dialogue.welcome();
		Matrice.tab(tab) ;
		System.out.println();
		//---------------------------
		int Dpt_debut = 0 ;
		int Dpt_arriver =0  ;
		Boolean marche = true ;
		
		
		while ( marche )
		{
			System.out.println("Departement de Depart :" );
			Scanner dpt = new Scanner(System.in);
			Dpt_debut = dpt.nextInt() ; 
			System.out.println("Departement d'Arriver :" );
			Dpt_arriver = dpt.nextInt() ; 
			//-----Floyd_Warshall --------
			Dialogue.select_dpt ( Dpt_debut,Dpt_arriver );
			Floyd_Warshall  f = new Floyd_Warshall(g,Dpt_debut,Dpt_arriver) ;
			//----------------------------
			
			
			
			marche = Dialogue.stop() ;
			if ( marche == false){
				try { RecupGraph recup = new RecupGraph( f.get_matrice_adjacence(),f.get_matrice_chemin()) ; } 
					catch ( java.io.IOException e2) { System.out.println("Error ! Fichier Graph non cree");}
			}
			
		}
		
		
		
		
		
		

	}
	
	
	
	
	
	
	

}
