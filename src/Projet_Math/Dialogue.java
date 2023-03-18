package Projet_Math ;
import java.io.File;
import java.util.*;  
import fr_departments.* ;
import java.io.FileNotFoundException;

public class Dialogue{



	public static void Presente() {
	
		System.out.println("Bonjour," );
		System.out.println("Le Graph à deja été initialiser.." );
		System.out.println("Souhaitez vous consulter les paramètres d'initialisation suivant ?" );
		System.out.println("" );
		System.out.println(" 'DATA_ARC_POID_DPT_FR' || 'LISTE_ADJACENCE' || 'MATRICE_ADJACENCE' " );
		System.out.println("" );
		System.out.println("Si oui Taper, le mots énoncé précedement, sinon pour passer directement à" );
		System.out.println("l'algorithme taper : 'NON' " );
		System.out.println("" );
	
	
	}
	
	public static void choose(GraphLinearDirected g)throws FileNotFoundException  {
		String reponse = "" ;
		String no = "NON";
		Scanner sc = new Scanner(System.in);
		while ( reponse != no )
    		{
			reponse = sc.next();
			String data = "DATA_ARC_POID_DPT_FR";
			String liste = "LISTE_ADJACENCE";
			String matrix ="MATRICE_ADJACENCE" ;
			switch(reponse) {
				case "DATA_ARC_POID" :
					Matrice.affiiche2(Matrice.Set_arete_dpt());
					break;
				case "LISTE_ADJACENCE" :
					Matrice.affiiche2(g.GetAdjacenceyLists());
					break ;
				case "MATRICE_ADJACENCE" :
					Matrice.affiiche(g.GetAdjacenceyMatrix());
					break ;
				case "NON" :
					reponse = no ;
					break ;		
			}
			if (reponse != no)
			{
				System.out.println("Continuer à consulter ? " );
				System.out.println("" );
				System.out.println(" 'DATA_ARC_POID_DPT_FR' || 'LISTE_ADJACENCE' || 'MATRICE_ADJACENCE' " );
				System.out.println("" );
				System.out.println("Passer directement à l'algorithme taper : 'NON' " );
			}
		}
	}
	
	public static void welcome(){
	
		System.out.println("Vous entrer dans l'algorithme de Floyd_Warshall" );
		System.out.println("Selectionner deux département parmis les suivants :" );
		System.out.println("" );
	}
	
	public static void select_dpt ( int Dpt_debut , int Dpt_arriver ){
			
			
			FR_Department start = FR_AllDepartments.getDepartment(Dpt_debut) ;
			FR_Department end = FR_AllDepartments.getDepartment(Dpt_arriver) ;
			System.out.println("Vous avez selectionner les departements suivant :" );
			System.out.println("Departement de Debut : "+ Dpt_debut +" "+ start.getName() );
			System.out.println("Departement d'Arriver : "+ Dpt_arriver+" "+end.getName() );
			System.out.println(" ");
	}
	
	public static Boolean stop()throws FileNotFoundException{
	
		System.out.println("");
		System.out.println("Souhaitez vous,vous arretez ici ? ");
		System.out.println("Et mettre fin au programme.. ");
		System.out.println("-----Taper-Zero--- '0' ou 'NON' -----------");
		Scanner sc = new Scanner(System.in);
		String reponse = sc.next();
		Boolean result = true ;
		switch(reponse) {
				case "NON" :
					result = true ;
					break;
				case "0" :
					result = false ;
					break ;
				}
		System.out.println("");
		return result ;
	}
	
	
	
	
	
}
