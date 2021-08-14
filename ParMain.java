
package com.mycompany.minimax;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alfonso Ponce Navarro
 */
public class ParMain 
        implements Runnable
{
    private static int N = 4;
    private  ThreeInRow g;
    private static Minimax c;
    private static int Nhilos;

    private static Movement[] results;
    private int id;
    public ParMain(int id, ThreeInRow g, Minimax c)
    {this.id = id; this.g = g; this.c=c; }
    
    public void run()
    {
        
        c.minimaxAlgorithm(g);

        
        results[id] = c.getMove();
        System.out.println(id);
    }
    
    /**
     * 
     * @param board board of the game
     * @return Number of possible movements
     */
    public static int nSonsBoard(int[] board)
    {
        int n = 0;
        
        for(int play = 0; play < N*N; play++)
            if(board[play] == 0)
                n++;
        
        
        return n;
    }
    
    /**
     * Selects the best movement
     * @return Index of the best movement
     */
    public static int getMaxInd()
    {
        int ind = -1;
        int max = -9999;
        for(int i = 0; i < results.length; i++)
            if(results[i].score > max)
            {
               max = results[i].score;
               ind = results[i].move;
            }
        
        return ind;
    }
    
    
    public static void main(String[] args) throws InterruptedException
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
            Nhilos = nSonsBoard(game.getBoard());
            int j = 0;
            
            ArrayList<Thread> tareas  = new ArrayList(Nhilos);
            results = new Movement[Nhilos];
            System.out.println("My turn");
            if(!game.endGame())
            {
                for(int i = 0; i < N*N; i++)
                    if(game.isValid(i))
                    {
                        game.applyPlay(1, i);
                        tareas.add(new Thread(new ParMain(j, new ThreeInRow(game.getBoard()), computador)));
                        game.retractMove(i);
                        j++;
                    }
                tIni = System.nanoTime();
                for(int i = 0; i < Nhilos; i++)
                    tareas.get(i).start();
                
                for(int i = 0; i < Nhilos; i++)
                    tareas.get(i).join();
                System.out.println("Execution Time = "+((System.nanoTime()-tIni)/(long)1.0e6)+"msegs");
                game.applyPlay(1, ParMain.getMaxInd());
                game.showBoard();
            }
        }
        
        
        
    }
}
