/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Tyler
 */
public class Heuristic {
    int[][] board;
    int P1score = 0;
    int P2score = 0;
    int total=0;
    int player=0;
    public Heuristic(int[][] b){
        this.board = b;
    }
    public int getStrength(boolean playersturn){
        if(playersturn){
           this.P1score=CurrentStrength(1,playersturn );
           this.P2score=CurrentStrength(2, playersturn);
           this.total=(P1score-P2score);
           this.total=Math.abs(total);
        }
        else{ 
           this.P2score=CurrentStrength(2, playersturn);
           this.P1score=CurrentStrength(1, playersturn);
           this.total=(P1score-P2score);
           if(P2score>P1score)
                this.total=total;
           else
                this.total=Math.abs(total);
        }
        return this.total;
    }
    private int CurrentStrength(int piece, boolean playersturn){
        player=piece;
        int strength=0;
        for(int row = 0; row < 6; row ++){
            for(int col = 0; col < 7; col ++)
            {
                int[] Horz={0,0,0,0};
                int[] Vert={0,0,0,0};
                int[] Side={0,0,0,0};
                int j = 0;
                double countH=0.0;
                double countV=0.0;
                double countS=0.0;
                if(col<=3){                                  //horizontal chceks storage
                    Horz[0]=board[row][col];
                    Horz[1]=board[row][col+1];
                    Horz[2]=board[row][col+2];
                    Horz[3]=board[row][col+3];
                    countH++;
                }
                if(row>=3){                                  //vertical chceks storage
                    Vert[0]=board[row][col];
                    Vert[1]=board[row-1][col];
                    Vert[2]=board[row-2][col];
                    Vert[3]=board[row-3][col];
                    countV++;
                }
                if((row>=3)&&(col<=3)){                      //Slant chceks storage
                    Side[0]=board[row][col];
                    Side[1]=board[row-1][col+1];
                    Side[2]=board[row-2][col+2];
                    Side[3]=board[row-3][col+3];
                    countS++;
                }
                if((row<=2)&&(col<=3)){                      //Slant chceks storage
                    Side[0]=board[row][col];
                    Side[1]=board[row+1][col+1];
                    Side[2]=board[row+2][col+2];
                    Side[3]=board[row+3][col+3];
                    countS++;
                }
                if(countH==1){
                    countH=0;
                    for(int x=0;x<4;x++){
                        if(Horz[x]==0)
                            countH=(countH+.25);
                        else if(Horz[x]==player)
                            countH=(countH+1);
                        else
                            countH=(countH-4);
                    }
                }
                if(countV==1){
                    countV=0;
                    for(int x=0;x<4;x++){
                        if(Vert[x]==0)
                            countV=(countV+.25);
                        else if(Vert[x]==player)
                            countV=(countV+1);
                        else
                            countV=(countV-4);
                    }
                }
                if(countS==1){
                    countS=0;
                    for(int x=0;x<4;x++){
                        if(Side[x]==0)
                            countS=(countS+.25);
                        else if(Side[x]==player)
                            countS=(countS+1);
                        else
                            countS=(countS-4);
                    }
                }
                if(countH==4){
                    strength = strength+64;
                }
                if(countH==3.25){
                    if (playersturn && row % 2 == 0)
                    {
                        strength = strength + 32;
                    }
                    else if (!playersturn && row % 2 != 0)
                    {
                        strength = strength+32;
                    }
                    else
                    {
                        strength = strength+16;
                    }
                }
                if(countH==2.5){
                    strength = strength+4;
                }
                if(countH==1.75){
                    strength = strength+1;
                }
                if(countV==4){
                    strength = strength+64;
                }
                if(countV==3.25){
                    if (playersturn && row % 2 == 0)
                    {
                        strength = strength + 32;
                    }
                    else if (!playersturn && row % 2 != 0)
                    {
                        strength = strength+32;
                    }
                    else
                    {
                        strength = strength+16;
                    }
                }
                if(countV==2.5){
                    strength = strength+4;
                }
                if(countV==1.75){
                    strength = strength+1;
                }
                if(countS==4){
                    strength = strength+64;
                }
                if(countS==3.25){
                    if (playersturn && row % 2 == 0)
                    {
                        strength = strength + 32;
                    }
                    else if (!playersturn && row % 2 != 0)
                    {
                        strength = strength+32;
                    }
                    else
                    {
                        strength = strength+16;
                    }
                }
                if(countS==2.5){
                    strength = strength+4;
                }
                if(countS==1.75){
                    strength = strength+1;
                }
            }
        }
        return strength;
    }
}
