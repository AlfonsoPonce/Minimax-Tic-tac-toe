
package com.mycompany.minimax;

/**
 * 3-In-Row abstraction class.
 * @author Alfonso Ponce Navarro
 */
public class ThreeInRow 
{

    private int[] board;
    private int winnerPos = 1000000;
    private final int N = 4;
    private int free = N*N;
    
 
    /**
     * Default Constructor
     */
    public ThreeInRow()
    {
        board = new int[N*N];
        
    }
    /**
     * 
     * @param b Deafult board
     */
    public ThreeInRow(int[] b)
    {
        board = new int[N*N];
        for(int i = 0; i < b.length; i++)
            board[i] = b[i];
    }
    
    /**
     * 
     * @param play cell where token is put
     * @return true if movement is valid, false if not
     */
    public boolean isValid(int play)
    {
        boolean res = false;
        
        if(play >= 0 && play <= N*N-1) //Out of bounds
            if(board[play] != 1 && board[play] != -1) //Can't replace tokens
                res = true;
        
        return res;
    }
    
    /**
     * Puts a token in the board
     * @param player -1 if is human, 1 if is computer
     * @param x row number
     * @param y col number
     */
    public void applyPlay(int player, int play)
    {
        board[play] = player;
        free--;
    } 
    
    /**
     * Erases the last move done
     * @param last_move Last token put 
     */
    public void retractMove(int last_move)
    {
        board[last_move] = 0; free++;
    }
    
    
    /**
     * 
     * 
     * @return 100 if AI wins, -100 if Human Wins, 0 if its a draw
     */
    public int Terminal()
    {
        int res = 0;
        /** boolean top_row = board[0] == board[1] && board[0] == board[2] && board[0] != 0,
                mid_row = board[3] == board[4] && board[3] == board[5] && board[3] != 0,
                bot_row = board[6] == board[7] && board[6] == board[8] && board[6] != 0,
                left_col = board[0] == board[3] && board[0] == board[6] && board[0] != 0,
                mid_col = board[1] == board[4] && board[1] == board[7] && board[1] != 0,
                right_col = board[2] == board[5] && board[2] == board[8] && board[2] != 0,
                first_diag = board[0] == board[4] && board[0] == board[8] && board[0] != 0,
                second_diag = board[2] == board[4] && board[2] == board[8] && board[2] != 0;// Winner combinations**/
         
       boolean top_row = board[0] == board[1] && board[0] == board[2] && board [0] == board[3] && board[0] != 0,
                mid_row1 = board[4] == board[5] && board[4] == board[6] && board[4] == board[7] && board[4] != 0,
                mid_row2 = board[8] == board[9] && board[8] == board[10] && board[8] == board[11] && board[8] != 0,
                bot_row = board[12] == board[13] && board[12] == board[14] && board[12] == board[15] && board[12] != 0,
                left_col = board[0] == board[4] && board[0] == board[8] && board[0] == board[12] && board[0] != 0,
                mid_col1 = board[1] == board[5] && board[1] == board[9] && board[1] == board[13] && board[1] != 0,
                mid_col2 = board[2] == board[6] && board[2] == board[10] && board[2] == board[14] && board[2] != 0,
                right_col = board[3] == board[7] && board[3] == board[11] && board[3] == board[15] && board[3] != 0,
                first_diag = board[0] == board[5] && board[0] == board[10] && board[0] == board[15] && board[0] != 0,
                second_diag = board[3] == board[6] && board[3] == board[9] && board[3] == board[12] && board[3] != 0;// Winner combinations
 
    /**    if(top_row) 
            {res += 100*board[0];}
        else if(mid_row) 
            {res += 100*board[3];}
        else if(bot_row) 
            {res += 100* board[6];}
        else if(left_col) 
            {res += 100*board[0];}
        else if(mid_col) 
            {res += 100*board[1];}
        else if(right_col) 
            {res += 100*board[2];}
        else if(first_diag) 
            {res += 100*board[0];}
        else if(second_diag) 
            {res += 100*board[2];} **/
       
       if(top_row) 
            {res += 100*board[0];}
        else if(mid_row1) 
            {res += 100*board[4];}
        else if(mid_row2)
            {res += 100*board[8];}
        else if(bot_row) 
            {res += 100* board[12];}
        else if(left_col) 
            {res += 100*board[0];}
        else if(mid_col1) 
            {res += 100*board[1];}
        else if(mid_col2)
            {res += 100*board[2];}
        else if(right_col) 
            {res += 100*board[3];}
        else if(first_diag) 
            {res += 100*board[0];}
        else if(second_diag) 
            {res += 100*board[3];}

        
        
        return res;
    }
    /**
     * 
     * @return true if game has ended, false if not 
     */
    public boolean endGame()
    {
        if(free == 0 || evalBoard())
            return true;
        else 
            return false;
    }
    
    
    
    /**
     * 
     * @return internal representation of the game board 
     */
    public int[] getBoard()
    {
        return board;
    }
    
    
    
    public void showBoard()
    {
        int col_count = 0;
        for(int i = 0; i < N*N; i ++)
        {           
            System.out.print("|"+board[i]);
            if(i==((N-1)+(N*col_count)))
                {System.out.print("|"); System.out.println("");}
            if(i%N == 0 && i != 0)
                {col_count++;}
            
        }
    }
    
    private boolean evalBoard()
    {
        /**boolean top_row = board[0] == board[1] && board[0] == board[2] && board[0] != 0,
                mid_row = board[3] == board[4] && board[3] == board[5] && board[3] != 0,
                bot_row = board[6] == board[7] && board[6] == board[8] && board[6] != 0,
                left_col = board[0] == board[3] && board[0] == board[6] && board[0] != 0,
                mid_col = board[1] == board[4] && board[1] == board[7] && board[1] != 0,
                right_col = board[2] == board[5] && board[2] == board[8] && board[2] != 0,
                first_diag = board[0] == board[4] && board[0] == board[8] && board[0] != 0,
                second_diag = board[2] == board[4] && board[2] == board[8] && board[2] != 0;// Winner combinations**/
        
        boolean top_row = board[0] == board[1] && board[0] == board[2] && board [0] == board[3] && board[0] != 0,
                mid_row1 = board[4] == board[5] && board[4] == board[6] && board[4] == board[7] && board[4] != 0,
                mid_row2 = board[8] == board[9] && board[8] == board[10] && board[8] == board[11] && board[8] != 0,
                bot_row = board[12] == board[13] && board[12] == board[14] && board[12] == board[15] && board[12] != 0,
                left_col = board[0] == board[4] && board[0] == board[8] && board[0] == board[12] && board[0] != 0,
                mid_col1 = board[1] == board[5] && board[1] == board[9] && board[1] == board[13] && board[1] != 0,
                mid_col2 = board[2] == board[6] && board[2] == board[10] && board[2] == board[14] && board[2] != 0,
                right_col = board[3] == board[7] && board[3] == board[11] && board[3] == board[15] && board[3] != 0,
                first_diag = board[0] == board[5] && board[0] == board[10] && board[0] == board[15] && board[0] != 0,
                second_diag = board[3] == board[6] && board[3] == board[9] && board[3] == board[12] && board[3] != 0;// Winner combinations **/
        
        /**return top_row || mid_row || bot_row || 
                        left_col || mid_col || right_col || 
                        first_diag || second_diag;**/
        
        return top_row || mid_row1 || mid_row2 || bot_row || 
                        left_col || mid_col1 || mid_col2 || right_col || 
                        first_diag || second_diag; 
        
        //return isLine() || isColumn() || isDiagonal(); 
    }
    
    
/**    private boolean isLine()
    {
        boolean res = true;
        
        for(int i = 0; i < N*N; i+=N)
        {
            res = true;
            for(int j = i; j < (i+N)-1; j++)
            {
                if(board[j] != 0)
                {
                    res = res && board[j] == board[j+1];
                    
                }
                else 
                    res = false;
            }
            
            if(res)
                winnerPos = i;
        }
          
        
                
        return res;
    }
    
    private boolean isColumn()
    {
        boolean res = true;
        
        for(int i = 0; i < N; i++)
        {
            res = true;
            for(int j = i; j < (i+N)-1; j+=N)
            {
                if(board[j] != 0)
                    res = res && board[j] == board[j+1];
                else 
                    res = false;
            }
            
            if(res)
                winnerPos = i;
        }
          
                
        return res;
    }
    
    private boolean isDiagonal()
    {
        boolean d1 = true, d2 = true;
        
        for(int i = 0; i < (N*N)-1; i+=(N+1))
        {
                if(board[i] != 0)
                    d1 = d1 && board[i] == board[i+1];
                else 
                    d1 = false;            
        }
        
        for(int i = N-1; i < N*(N-1); i+=(N-1))
        {
            if(board[i] != 0)
                    d2 = d2 && board[i] == board[i+1];
                else 
                    d2 = false; 
        }
        
        if(d1)
            winnerPos = 0;
        if(d2)
            winnerPos = N-1;
                
        return d1 || d2;
    }**/
}
