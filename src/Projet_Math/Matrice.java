package Projet_Math ;
import java.io.File;
import java.util.*;  
import java.io.FileNotFoundException;

public class Matrice{


    public static byte[][] getAdjacencyMatrix(Scanner input , int n)
    {
    	byte[][] m = new byte[n][n];
    			
		for(int i=0; i<n; i++)
		{
			for(int j=0; j<n; j++)
			{
				m[i][j] = input.nextByte();
			}
		}
		return m;
    	
    }
    
    public static int[][] getAdjacencyList(Scanner input , int n)
    {
    	
    	int [][] m = new int[n][n];
    	
    			
	for(int i=0; i<n; i++)
	{		
		for( int j=0; j<n; j++)
		{
			m[i][j] = input.nextInt();
			if ( m[i][j] == 0 )
			{
				int [] copy = Arrays.copyOf(m[i], m.length - ( n-j-1));	
				m[i] = Arrays.copyOf(copy, copy.length);	 ; 	
				break ;		
			}	
		}
	}
	return m ;
    	
    }
    
    
    public static void affiiche(byte[][] m)
    {
    	
    	int n = m.length ;		
	for(int i=0; i<n; i++)
	{
		for(int j=0; j<n; j++)
		{	
			System.out.print(m[i][j]+" ");
			
		}
		System.out.println("");
	
	}
	System.out.println("");
    	
    }
    
    public static void tab(int [] m)
    {
    	int n = m.length ;		
	for(int j=0; j<n; j++)
	{	
		if ( m[j] ==Integer.MAX_VALUE ){
		System.out.print("-"+" "); }
		else{
		System.out.print(m[j]+" "); }
		if ( (j+1) % 10 == 0  )
		System.out.println(" ");
	}
	System.out.println("");
    	
    }
    
    public static void affiiche2(int[][] m)
    {
    	int n = m.length ;
		for(int i=0; i<n; i++)
			{
				int taille = m[i].length ;
				for(int j=0; j<taille; j++)
				{	

					if ( m[i][j] ==Integer.MAX_VALUE ){ 
					System.out.print("-"+" ");}
					else
					System.out.print(m[i][j]+" ");
					
				}
				System.out.println("");
			}
			System.out.println("");
    	
    }
    
    public static int[] computeAdjacencyList( byte[][] m , int x)
    {
			
			int n = m.length;
			int tab[] = new int[n] ; 
			for(int i=0; i<n; i++)
			{
				System.out.print((i+1)+": ");
				for(int j=0; j<n; j++)
				{	
					if ( (m[i][j] == 1) && ((i+1)==x) ) 
					{ 
						System.out.print( (j+1)+" ");
						tab[j] = j+1 ;
						
					}

				}
				System.out.println("");
			}
			return tab ;
    }
    
    public static byte[][] computeAdjacencyMatrice( int[][] list )
    {
			
	int n = list.length;
	byte [][]matrice = new byte[n][n] ;
	
	for ( int i = 0 ; i< n ; i++ )
	{
		for ( int j = 1 ; j< n ; j++ )
		{
			if ( list[i][j] == 0 )
			{
				break ;
			}
			else
			{
				matrice[i][ (list[i][j])-1 ] = 1 ;
			}		
		}
	}		
	return matrice ;
    }
   
  public static int [][] Set_arete_dpt() throws FileNotFoundException
  {
  	int n = 488 ;
  	int [][] arete = new int[n][3];
  	String fichier = "Projet_Math/Data/data.data" ;
  	Scanner fil = new Scanner(new File(fichier));
  	
	for( int i = 0 ; i < n ; i++)
	{
		for ( int j = 0 ; j < 3 ; j++) 
		{
			arete[i][j] = fil.nextInt();
		} 
	}
  	
  	return arete ;
  }
  
  
    





}
