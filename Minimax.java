
package com.mycompany.minimax;

/**
 *
 * @author Alfonso Ponce Navarro
 */
public class Minimax 
{
    private int N = 4;
    private ThreeInRow game;
    private Movement b = new Movement();
    
    public Minimax(ThreeInRow tir)
    {game = tir;}
    
    public void minimaxAlgorithm(ThreeInRow state)
    {
        Movement best = new Movement();
        Movement m = new Movement();
        m.move = 0;
        best.score = -9999;
        for(m.move = 0; m.move < N*N; ++m.move)
        {
            if(game.isValid(m.move))
            {
                game.applyPlay(1, m.move);
                m.score = MIN();
                if(m.score > best.score){best.move = m.move; best.score = m.score;}
                game.retractMove(m.move);
            } 

        }
        //For parallel version
        b.move = best.move; b.score = best.score;
        
        //For parallel version comment the line below
        //game.applyPlay(1, best.move);
        
                
    }
    /**
     * 
     * @return The best move 
     */
    public Movement getMove()
    {
        return b;
    }
    
    private int MIN()
    {
        Movement best = new Movement();
        Movement m = new Movement();
        if(game.endGame()) return game.Terminal();
        else
        {
            best.score = 1000;
            for(m.move = 0; m.move < N*N; m.move++)
            {
                if(game.isValid(m.move))
                {
                    game.applyPlay(-1, m.move);
                    m.score = MAX();
                    if(m.score < best.score) {best.move = m.move; best.score = m.score;}
                    game.retractMove(m.move);
                }
               
            }
            
            return best.score;
        }    
    }
    
    private int MAX()
    {
        Movement best = new Movement();
        Movement m = new Movement();
        if(game.endGame()) return game.Terminal();
        else
        {
            best.score = -1000;
            for(m.move = 0; m.move < N*N; m.move++)
            {
                if(game.isValid(m.move))
                {
                    game.applyPlay(1, m.move);
                    m.score = MIN();
                    if(m.score > best.score) {best.move = m.move; best.score = m.score;}
                    game.retractMove(m.move);
                }
               
            }
            
            return best.score;
        }  
    }
}
