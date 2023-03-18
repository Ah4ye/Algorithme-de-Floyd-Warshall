package Projet_Math ;
import java.io.File;
import java.util.*;  
import java.io.FileNotFoundException;

public class Floyd_Warshall  {

	private int [][]matrice_chemin ;
	private int [][]matrice_adjacence ;

	public Floyd_Warshall (GraphLinearDirected g , int Dpt_debut , int Dpt_arriver ) throws FileNotFoundException{
		
		int [][] cout = Matrice.Set_arete_dpt() ; // init la Matrice des poids
		int max = g.getOrdre(); // init la valeur maximum du graph qui est 95
		int [][] chemin = new int[max][max] ; //init les 2 matrice pour l'algo Floyd_Warshall
		int [][] matrice_operator = new int[max][max] ; // La matrice des distances + La matrices des prédécesseurs
		
		matrice_operator = init_chemin ( chemin , max, cout, g ); // remplit les 2 matrices
		matrice_operator = algo( chemin , matrice_operator ) ; // lance l'algo Floyd_Warshall
		
		set_matrice_chemin( matrice_operator );
		set_matrice_adjacence( chemin  );
		printSolution(this.matrice_chemin, max, Dpt_debut-1 , Dpt_arriver-1 , cout  ) ; // Affiche la reponse
		
	}
	
	public int  getPoid( int source ,int target , int [][] cout){ 
	// Trouve les sommets reliés au vertex source
		int  poid = 0 ;
		int taille = cout.length ;
		int taille2 = cout[0].length ;
		for ( int i = 0 ; i<taille ; i++){
			for ( int j = 0 ; j<taille2 ; j++){
				if ( (cout[i][j] == source) && (cout[i][j+1] == target+1)){
					poid = cout[i][j+2] ;
					break ;
				}
				else break ;
			}
		}
		return poid ;
	}
	
	public int init_target( int Dpt_debut)// Init le selecteur de vertex
	{
		int target = 0 ;
		if ( target == Dpt_debut)
			target += 1;
		return target;
	}
	
	public int next_target( int target ,int Dpt_debut, int max) //
	{
		if ( (target ) == Dpt_debut-1)
			{ return target += 1 ;}
		else if ( (target +1) > max)	
			{ return target = init_target(Dpt_debut) ;}
		else return target += 1 ;
	
	}
	
	
	public int[] remplie_tab_chemin ( int [] chemin , int Dpt_debut ,GraphLinearDirected g, int[][] cout,  List<Integer> check )
	{
		int taille = g.getOrdre() ;
		int n = 0 ;
		int target = init_target(Dpt_debut) ;
		while ( n <taille){			
			if ( g.isEdge(Dpt_debut, target)  ){
				chemin[target] = getPoid(Dpt_debut, target , cout) ;
				target = next_target( target , Dpt_debut,  taille) ;
				n = n +1 ;
			}
			else{	
		 		if ( !already_do(check, target+1)   ){
				 	chemin[target] = Integer.MAX_VALUE ;
				 	target = next_target( target , Dpt_debut,  taille) ;
				 	n = n +1 ;
			 	}
			 	else {	target = next_target( target , Dpt_debut,  taille) ;
			 		n = n +1 ; }
			}
		}
		chemin[Dpt_debut-1] = 0 ;
		return chemin ;
	}
	//-------------------------------- Liste ----------------------------------------
	public void element_traiter ( List <Integer> element_traiter , int element ){
		element_traiter.add(element) ;	
	}
	
	public Boolean already_do ( List <Integer> element_traiter , int element ) {
	//Renvoie vrai si un element a deja ete traiter
		int taille = element_traiter.size() ;
		Boolean reponse = false ;
		
		for ( int x = 0 ; x< taille ;x++ ){
			if ( element_traiter.get(x) == element)
			{reponse = true ; }	
		}
			return reponse ;
	}
	
	public int sum_value_steps( List<Integer> check, int [] chemin_actuel ){ 
	// Renvoie le cout des chemin deja parcouru
		int taille = check.size() ;
		int sum = 0 ;
		
		for ( int x = 0 ; x< taille ;x++ ){
			sum += chemin_actuel[check.get(x)-1] ;
			
		}
		return sum ;
	
	}
	//--------------------------------------------------------------------------------

	public int find_min_way(GraphLinearDirected g, int[] chemin,  List<Integer> check ){
	// trouve le  chemin le plus court
		int size_tab = chemin.length ;
		int min = Integer.MAX_VALUE ;
		int indice = 0 ;
		for ( int i = 0 ; i< size_tab ; i++){
			if ( (!already_do(check, i+1)) && chemin[i]< min)
			{
				min = chemin[i] ;
				indice = i ; 
			}
		}
		return indice ;
	}
	
	public void add_value_steps( int [] ancien_chemin , int [] actuel_chemin , List<Integer> check ){ 
	// Ajoute les valeurs des chemins deja parcourus, des etapes precedentes 
		int size_chemin = ancien_chemin.length ;
		for ( int i = 0 ; i < size_chemin ; i++ ){
			if (already_do(check, i+1) ){
				actuel_chemin[i] = ancien_chemin[i]  ; }
			else if (!already_do(check, i+1) && ancien_chemin[i] < Integer.MAX_VALUE  ){
				 actuel_chemin[i] = ancien_chemin[i] + sum_value_steps(check,ancien_chemin)  ;}
			
		}
	}
	
	
	public int [][] init_chemin ( int [][] chemin , int ordre, int [][] cout, GraphLinearDirected g ){
	// Cree la matrice d'adjacence et la matrice des couts entre vertex
		int n = 0 ;
		List <Integer> no = new ArrayList<>() ;
		int [][] matrice_adj = new int[ordre][ordre] ;
		
		while ( n < ordre){
			chemin[n] = remplie_tab_chemin ( chemin[n] , n+1,g,  cout, no );
			n +=1 ;}
		
		for ( int i = 0 ; i < ordre; i++ ) {
			for ( int j = 0 ; j < ordre; j++ ){
				if (i == j) {
                    			matrice_adj[i][j] = 0;
                		}
				else if (chemin[i][j] != Integer.MAX_VALUE) {
				    matrice_adj[i][j] = i;
				}
				else {
				    matrice_adj[i][j] = -1;
				}
			}
		}
		return matrice_adj ;	
	}
	
	
	public int [][] algo ( int [][] chemin , int [][] matrice_adj  ){
	// Algorithme Floyd_Warshall 
		int ordre = chemin.length ;
		int [][] reponse = new int[ordre][ordre] ;
		for ( int k = 0 ; k < ordre; k++ )
		{
		
			for ( int i = 0 ; i < ordre; i++ )
			{
				for ( int j = 0 ; j < ordre; j++ ) 
				{
					if ( 	chemin[i][k] != Integer.MAX_VALUE  
						&& chemin[k][j] != Integer.MAX_VALUE 
						&& ((chemin[i][k] + chemin[k][j]) < chemin[i][j]))
					{
						chemin[i][j] = chemin[i][k] + chemin[k][j];
                        			matrice_adj[i][j] = matrice_adj[k][j];
					}
				}
			} 
		}
		reponse = matrice_adj ; 
		return reponse ;
	} 
	//---------------------------Print Solution --------------------------------
	private static void printChemin(int[][] path, int v, int u, List<Integer> route)
	{// Fonction recursive, Ajoute le chemin le plus court
		if (path[v][u] == v) {

		    return;
		}
		printChemin(path, v, path[v][u], route);
		route.add(path[v][u]+1);
	}
	
	private void printCout( List<Integer> route, int [][] cout )
	{
		int somme_cout = 0 ;
		int taille = route.size() ;
		
		for ( int i = 0; i< taille ; i ++ ){
			if ( i< taille -1 ){
			somme_cout +=  getPoid( route.get(i) ,route.get(i+1)-1 , cout); } 
		}

		 System.out.println("Et le cout total du chemin est : "+ somme_cout);
	}
	
	
	private  void printSolution(int[][] path, int n , int Dpt_debut , int Dpt_arriver , int [][] cout )
	{
		List<Integer> route = new ArrayList<>();
		for (int v = Dpt_debut; v <  Dpt_debut+1; v++){
		    for (int u = Dpt_arriver; u < Dpt_arriver+1; u++){  
		        if (u != v && path[v][u] != -1 ){
		            
		            route.add(v+1);
		            printChemin(path, v, u, route);
		            route.add(u+1);
		            System.out.printf("Le plus court chemin entre %d —> %d est %s\n",
		                v+1, u+1, route);   
		        }
		    }
		}
		printCout( route , cout) ;
	  }
	//---------------------------Print Solution --------------------------------
	public void set_matrice_chemin( int [][] matrice ){	
		this.matrice_chemin = matrice ;
	}
	
	public void set_matrice_adjacence( int [][] matrice ){	
		this.matrice_adjacence = matrice ;
	}

	public int [][] get_matrice_adjacence(  ){
		return this.matrice_adjacence ;
	}
	public int [][] get_matrice_chemin(  ){
		return this.matrice_chemin ;
	}













	



}
