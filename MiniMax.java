/*
 * This class implements depth search with Alpha-Beta pruning on the minimax algorithm
 * 
 */

/**
 * All lines beginning with // are testing lines
 * @author Tyler
 */
public class MiniMax {
    final int NUM_COLS = 7;
    boolean col_full = true;
    int[][] board;
    private final int MAX_POSSIBLE_VALUE;
    private final int MIN_POSSIBLE_VALUE;
    private int currentPlayer;

    public MiniMax(int[][] b, int player)
    {
        board = b;
        MAX_POSSIBLE_VALUE = Integer.MAX_VALUE;
        MIN_POSSIBLE_VALUE = Integer.MIN_VALUE;
        currentPlayer=player;
    }

  public int minMax (int depth)
  {
      if(depth == 0)
      {
          System.out.println("error 0 depth.");
          return -1;
      }
      int Alpha = MIN_POSSIBLE_VALUE;
      int colIndex = 0;
      int Beta = MAX_POSSIBLE_VALUE;
      for(int j = 0; j < NUM_COLS; j++)
      {
          if(!isFull(j))
          {
                if(currentPlayer==1){
                    move(j, true);
                    int strength = expandMaxNode(depth -1, Alpha);
                    if(strength > Alpha)
                    {
                        Alpha = strength;
                        colIndex = j;
                    }
                    unmove(j);
                }
                else{
                    move(j, false);
                    int strength = expandMinNode(depth -1, Beta);
                    if(strength < Beta)
                    {
                        Beta = strength;
                        colIndex = j;
                    }
                    unmove(j);
                }
          }
      }
    return colIndex;
  }

  public int expandMaxNode (int depth, int Beta)
  {
        if (depth == 0)
        {
            if(currentPlayer==1){
                return my_heuristic(true);
            }
            else{
                return my_heuristic(false);
            }
        }
        int maxStrength = MIN_POSSIBLE_VALUE;

        for(int j = 0; j < NUM_COLS; j++)
        {
          if(!isFull(j))
          {
                if(currentPlayer==1){
                    move(j, false);
                }
                else{
                    move(j, true);
                }
                int strength = expandMinNode(depth -1, maxStrength);
                if(strength > Beta)
                {
                    unmove(j);
                    return strength;
                }
                if(strength > maxStrength)
                {
                    maxStrength = strength;
                }
                unmove(j);
          }
        }
        return maxStrength;
}


  private int expandMinNode(int depth, int Alpha)
  {
      if(depth == 0)
      {
        if(currentPlayer==1){
            return my_heuristic(true);
        }
        else{
            return my_heuristic(false);
        }
      }
      int minStrength = MAX_POSSIBLE_VALUE;
        for(int j = 0; j < NUM_COLS; j++)
        {
          if(!isFull(j))
          {
                if(currentPlayer==1)
                    move(j, true);
                else
                    move(j, false);
                int strength = expandMaxNode(depth -1, minStrength);
                if(strength < Alpha)
                {
                    unmove(j);
                    return strength;
                }
                if(strength < minStrength)
                {
                    minStrength = strength;
                }
                unmove(j);
          }
        }
        return minStrength;
}

   //removes the last move from the specified column.
   private void unmove(int j)
   {
        for(int i = 0; i <= 5; i++){
            if(board[i][j] != 0){
                board[i][j] = 0;
                break;
            }
        }
   }
   //places a piece in the specified column for the player.
    private void move(int j, boolean player)
    {
        for(int i = 5; i >= 0; i--){
            if(board[i][j] == 0){
                if(player)
                    board[i][j] = 1;
                else 
                    board[i][j] = 2;
                break;
            }
        }
    }
    //returns true if a column is full, false otherwise.
    private boolean isFull(int j)
    {
        if(board[0][j] != 0)
            return true;
        else
            return false;
    }

    private int my_heuristic(boolean playersturn)
    {
        Heuristic test = new Heuristic(this.board);
        int strength = test.getStrength(playersturn);
        return strength;
    }
}
