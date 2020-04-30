import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;


// 0 - puste pole
// 1 - dziecko
// 2 - prezent
// 3 - dziecko z prezentem
// 4 - spiace dziecko z prezentem obok
// 6 - mikolaj


public class Board extends JPanel implements KeyListener, RandomInterface
{
    private final int N;// = 16;
    private final int M;// = 17;
    private final int KIDS_COUNT;// = 12;
    private final int KIDS_SPEED;
    private final int KIDS_RANGE;
    private final int KIDS_POWER;
    public boolean over = false;

    public static final Color COLOR_SQUARE = Color.BLACK;
    public static final Color COLOR_BACKGROUND = Color.WHITE;

    public int[][] boardTable;
    public int counter = 0;

    GameFrame g;

    Claus claus;
    ArrayList<Kid> kids;
    ArrayList<Gift> gifts;
    Refresh refresh;

    public Board(int n, int m, int count, int speed, int range, int power, GameFrame g)
    {
        this.N=n;
        this.M=m;
        this.KIDS_COUNT=count;
        this.KIDS_SPEED=speed;
        this.KIDS_RANGE=range;
        this.KIDS_POWER=power;
        this.g=g;
        setParams();
        refresh = new Refresh(this);
        refresh.start();
        addKeyListener(this);
        startGame();
    }

    private void startGame()
    {
        for(Kid k: kids)
        {
            k.start();
        }
    }


    public synchronized void win()
    {
        if(!over)
        {
            g.dispose();
            EndFrame e = new EndFrame("Wygrales","Zwyciestwo",N,M,KIDS_COUNT,KIDS_SPEED,KIDS_RANGE,KIDS_POWER);
            try{
                for(Kid k: kids) k.join();
                refresh.join();
            }catch (InterruptedException exp)
            {
                exp.printStackTrace();
            }
        }
        over=true;
    }

    public synchronized void defeat()
    {
        if(!over){
            g.dispose();
            EndFrame e = new EndFrame("Przegrales","Porazka",N,M,KIDS_COUNT,KIDS_SPEED,KIDS_RANGE,KIDS_POWER);
            try{
                for(Kid k: kids) k.join();
                refresh.join();
            }catch (InterruptedException exp)
            {
                exp.printStackTrace();
            }
        }
        over=true;
    }


    private void setParams()
    {
        claus = new Claus(N-3,M*2/3,this, g);
        boardTable = new int[N][M];
        assignZero(boardTable);
        boardTable[claus.i][claus.j]=6;
        kids = new ArrayList<>();
        gifts = new ArrayList<>();
        while (kids.size()<KIDS_COUNT)
        {
            int x = random(0,N-6);
            int y = random(0,M-1);
            if(isDiffrentPosition(x,y))
            {
                Kid k = new Kid(x,y,KIDS_SPEED,KIDS_RANGE,KIDS_POWER,this,g);
                boardTable[k.i][k.j]=1;
                kids.add(k);
            }
        }
    }

    private void drawBoard(Graphics graphics)
    {
        for(int i = 0; i< N;i++)
        {
            for(int j = 0;j<M;j++)
            {
                graphics.setColor(COLOR_SQUARE);
                graphics.drawRect(i*g.getSQURE_SIZE(),j*g.getSQURE_SIZE(),g.getSQURE_SIZE(),g.getSQURE_SIZE());
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(COLOR_BACKGROUND);
        drawBoard(g);
        requestFocus(true);
        claus.paint(g);
        for(Kid k: kids) k.paint(g);
        for(Gift a: gifts) a.paint(g);
    }

    private void assignZero(int[][] t)
    {
        for(int i =0;i<N;i++)
        {
            for(int j = 0;j<M;j++)
            {
                t[i][j]=0;
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode())
        {
            case KeyEvent.VK_RIGHT:
                claus.moveRight();
                /*
                if(boardTable[(claus.i+1)%N][claus.j]==0)// && isDiffrentPosition(((nicholas.i+1)%N),nicholas.j))
                {
                    boardTable[claus.i][claus.j]=0;
                    claus.i=(claus.i+1)%N;
                    boardTable[claus.i][claus.j]=6;
                }

                 */
                break;

            case KeyEvent.VK_LEFT:
                claus.moveLeft();
                /*
                if(claus.i==0 && boardTable[N-1][claus.j]==0){// && isDiffrentPosition(N-1,nicholas.j)){
                    boardTable[claus.i][claus.j]=0;
                    claus.i=N-1;
                    boardTable[claus.i][claus.j]=6;
                }
                else if(claus.i>0 && boardTable[claus.i-1][claus.j]==0){// && nicholas.i>0 && isDiffrentPosition(nicholas.i-1,nicholas.j)) {
                    boardTable[claus.i][claus.j]=0;
                    claus.i--;
                    boardTable[claus.i][claus.j]=6;
                }

                 */
                break;

            case KeyEvent.VK_UP:
                claus.moveUp();
                /*
                if(claus.j==0 && boardTable[claus.i][M-1]==0){ //&& isDiffrentPosition(nicholas.i,M-1)) {
                    boardTable[claus.i][claus.j]=0;
                    claus.j=M-1;
                    boardTable[claus.i][claus.j]=6;
                }
                else if(claus.j>0 && boardTable[claus.i][claus.j-1]==0){//isDiffrentPosition(nicholas.i,nicholas.j-1)) {
                    boardTable[claus.i][claus.j]=0;
                    claus.j--;
                    boardTable[claus.i][claus.j]=6;
                }

                 */
                break;

            case KeyEvent.VK_DOWN:
                claus.moveDown();
                /*
                if(boardTable[claus.i][(claus.j+1)%M]==0){//isDiffrentPosition(nicholas.i,((nicholas.j+1)%M))) {
                    boardTable[claus.i][claus.j]=0;
                    claus.j=(claus.j+1)%M;
                    boardTable[claus.i][claus.j]=6;
                }

                 */
                break;

            case KeyEvent.VK_X:
                if(boardTable[matchIndex(claus.i+1,N)][claus.j]==0 && isNearly(matchIndex(claus.i+1,N), claus.j)){
                    boardTable[matchIndex(claus.i+1,N)][claus.j]=2;
                    gifts.add(new Gift(matchIndex(claus.i+1,N), claus.j,g));
                }
                else if(boardTable[matchIndex(claus.i-1,N)][claus.j]==0 && isNearly(matchIndex(claus.i-1,N), claus.j)){
                    boardTable[matchIndex(claus.i-1,N)][claus.j]=2;
                    gifts.add(new Gift(matchIndex(claus.i-1,N), claus.j,g));
                }
                else if(boardTable[claus.i][matchIndex(claus.j-1,M)]==0 && isNearly(claus.i, matchIndex(claus.j-1,M))){
                    boardTable[claus.i][matchIndex(claus.j-1,M)]=2;
                    gifts.add(new Gift(claus.i,matchIndex(claus.j-1,M),g));
                }
                else if(boardTable[claus.i][matchIndex(claus.j+1,M)]==0 && isNearly(claus.i, matchIndex(claus.j+1,M))){
                    boardTable[claus.i][matchIndex(claus.j+1,M)]=2;
                    gifts.add(new Gift(claus.i,matchIndex(claus.j+1,M),g));
                }

                break;
            /*
            case KeyEvent.VK_D:
                if(isNearly(nicholas.i+1,nicholas.j)){
                    boardTable[matchIndex(nicholas.i+1,N)][nicholas.j]=2;
                    gifts.add(new Gift(matchIndex(nicholas.i+1,N),nicholas.j));
                }
                break;

            case KeyEvent.VK_A:
                if(isNearly(nicholas.i-1,nicholas.j)){
                    boardTable[matchIndex(nicholas.i-1,N)][nicholas.j]=2;
                    gifts.add(new Gift(matchIndex(nicholas.i-1,N),nicholas.j));
                }
                break;

            case KeyEvent.VK_W:
                if(isNearly(nicholas.i,nicholas.j-1)){
                    boardTable[nicholas.i][matchIndex(nicholas.j-1,M)]=2;
                    gifts.add(new Gift(nicholas.i,matchIndex(nicholas.j-1,M)));
                }
                break;

            case KeyEvent.VK_S:
                if(isNearly(nicholas.i,nicholas.j+1)){
                    boardTable[nicholas.i][matchIndex(nicholas.j+1,M)]=2;
                    gifts.add(new Gift(nicholas.i,matchIndex(nicholas.j+1,M)));
                }
                break;

             */
        }
    }

    private synchronized boolean isNearly(int giftX, int giftY) //giftY=14
    {
        for(int x = giftX-1;x<=giftX+1;x++)
        {
            int indexX = matchIndex(x,N);
            for(int y = giftY-1;y<=giftY+1;y++)
            {
                int indexY = matchIndex(y,M);
                if(boardTable[indexX][indexY]==1){

                    for(Kid k: kids)
                    {
                        if(k.i==indexX && k.j==indexY && k.getColor()==Kid_State.KID_SLEEP){
                            //k.setOwnGift(indexX,indexY,giftX,giftY);
                            k.setGotGift(true);
                            k.setGiftX(giftX);
                            k.setGiftY(giftY);
                            boardTable[indexX][indexY]=4;
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }

    private boolean isDiffrentPosition(int i, int j)
    {
        for(Kid k: kids)
        {
            if(k.i==i && k.j==j) return false;
        }
        return true;
    }

    public int getN() {
        return N;
    }

    public int getKIDS_COUNT() {
        return KIDS_COUNT;
    }

    public int getM() {
        return M;
    }

    public void printConsole()
    {
        for(int i = 0; i<N;i++)
        {
            for(int j = 0;j<M;j++)
            {
                System.out.print(boardTable[i][j] + " ");
            }
            System.out.println();
        }
    }

    @Override
    public int random(int min, int max) {
        return min + (int)(Math.random() * ((max - min) + 1));
    }

    @Override
    public long randomLong(long min, long max) {
        return min + (long)(Math.random() * ((max-min) + 1));
    }


    @Override
    public int matchIndex(int index, int nm) {
        if(index>=nm) return index%nm;
        else if(index<0)
        {
            return nm+index;
        }
        else
        {
            return index;
        }
    }
}
