import java.awt.*;

public class Claus
{
    public static final Color COLOR_NICHOLAS = Color.RED;
    public int i, j;
    private Board board;
    private GameFrame g;
    private int gifts;

    public Claus(int i, int j, Board b, GameFrame g)
    {
        this.i=i;
        this.j=j;
        this.board=b;
        this.g=g;
        this.gifts=board.getKIDS_COUNT();
    }

    public void paint(Graphics graphics)
    {
        graphics.setColor(COLOR_NICHOLAS);
        graphics.fillRect(i*g.getSQURE_SIZE()+g.getSQURE_SIZE()/4,j*g.getSQURE_SIZE()+g.getSQURE_SIZE()/4,
                g.getSQURE_SIZE()/2,g.getSQURE_SIZE()/2);
    }

    public void moveRight()
    {
        if(board.boardTable[(i+1)%board.getN()][j]==0)
        {
            board.boardTable[i][j]=0;
            i=(i+1)%board.getN();
            board.boardTable[i][j]=6;
        }
    }

    public void moveLeft()
    {
        if(i==0 && board.boardTable[board.getN()-1][j]==0){// && isDiffrentPosition(N-1,nicholas.j)){
            board.boardTable[i][j]=0;
            i=board.getN()-1;
            board.boardTable[i][j]=6;
        }
        else if(i>0 && board.boardTable[i-1][j]==0){// && nicholas.i>0 && isDiffrentPosition(nicholas.i-1,nicholas.j)) {
            board.boardTable[i][j]=0;
            i--;
            board.boardTable[i][j]=6;
        }
    }

    public void moveUp()
    {
        if(j==0 && board.boardTable[i][board.getM()-1]==0){ //&& isDiffrentPosition(nicholas.i,M-1)) {
            board.boardTable[i][j]=0;
            j=board.getM()-1;
            board.boardTable[i][j]=6;
        }
        else if(j>0 && board.boardTable[i][j-1]==0){//isDiffrentPosition(nicholas.i,nicholas.j-1)) {
            board.boardTable[i][j]=0;
            j--;
            board.boardTable[i][j]=6;
        }
    }

    public void moveDown()
    {
        if(board.boardTable[i][(j+1)%board.getM()]==0){//isDiffrentPosition(nicholas.i,((nicholas.j+1)%M))) {
            board.boardTable[i][j]=0;
            j=(j+1)%board.getM();
            board.boardTable[i][j]=6;
        }
    }


}
