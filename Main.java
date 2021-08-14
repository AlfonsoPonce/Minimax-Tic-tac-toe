
package com.mycompany.minimax;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Alfonso Ponce Navarro
 */
public class Main 
{
    
    public static void main(String[] args)
    {
        int[] tablero = {1,1,-1,0,0,0,0,0,-1,0,0,0,0,0,0,0};
        
        Scanner S = new Scanner(System.in);
        ThreeInRow game = new ThreeInRow(tablero);
        Minimax computador = new Minimax(game);
        int jugada;
        long tIni; 

        while(!game.endGame())
        {
            System.out.println("Your turn.");
            jugada = S.nextInt();
            game.applyPlay(-1, jugada);
            game.showBoard();
            if(!game.endGame())
            {
                System.out.println("My turn");
                tIni = System.nanoTime();
                computador.minimaxAlgorithm(game);
                System.out.println("Tiempo de ejecución = "+((System.nanoTime()-tIni)/(long)1.0e6)+"msegs");
                game.showBoard();
            }
        }
        
        
    }
}
