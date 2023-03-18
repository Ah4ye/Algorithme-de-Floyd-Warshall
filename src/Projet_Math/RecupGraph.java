package Projet_Math;
import java.io.File;
import java.util.*;  

import java.io.FileNotFoundException;
import java.io.IOException ;
import java.io.BufferedWriter;
import java.io.FileWriter;

import java.io.PrintWriter;



public class RecupGraph {
	
	public RecupGraph(int [][] matrice_adjacence, int [][] matrice_chemin) throws java.io.IOException{
	
	
		File file_matrice_adj = new File("graph-001.costs/matrice_adjacence.amatrix");
		file_matrice_adj.createNewFile();
		
		File file_matrice_chem = new File("graph-011.paths/matrice_chemin.amatrix");
		file_matrice_chem.createNewFile();
		
		Retranscrit_info( matrice_adjacence , file_matrice_adj );
		Retranscrit_info( matrice_chemin ,file_matrice_chem );
		
	
	}
	
	public  void Retranscrit_info( int [][] matrice_adjacence , File file_matrice_adj )throws IOException {
	
		int ordre = matrice_adjacence.length ; 
		PrintWriter writer = null;
		try{ 
			writer = new PrintWriter(new BufferedWriter( new FileWriter (file_matrice_adj) ) );
			writer.println(ordre) ;
			for ( int i = 0 ; i< ordre ; i++ )
			{
				for ( int j = 0 ; j< ordre ; j++ )
				{
					if ( matrice_adjacence[i][j] != Integer.MAX_VALUE  && matrice_adjacence[i][j] !=  -1){
						writer.print(matrice_adjacence[i][j] + " ");}
					else if ( matrice_adjacence[i][j] ==  -1){ writer.print("X" + " ");}
					else writer.print("Ꝏ" + " ");
				}
				writer.println();
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
				if (writer != null)
					writer.close();
			}
	}
	
	
	

}
