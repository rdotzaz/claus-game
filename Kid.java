import java.awt.*;

public class Kid extends Thread implements RandomInterface
{
    public int i,j;
    public final int VIEW_RANGE;// = 4;
    public final long KID_SPEED;// = 400;
    public static final long TIME_NOT_SLEEP_MIN = 1000, TIME_NOT_SLEEP_MAX = 3000;
    public final int START_POWER;// = 7;

    private boolean hasGift, gotGift;//, canMove;
    private Color color;
    private int power;
    private Board board;
    private GameFrame g;
    private int giftX, giftY;

    public Kid(int i, int j, int speed, int range, int power ,Board b, GameFrame g)
    {
        super();
        this.i=i;
        this.j=j;
        this.board=b;
        this.g=g;
        this.KID_SPEED=speed;
        this.VIEW_RANGE=range;
        this.START_POWER=power;
        this.hasGift=false;
        this.gotGift=false;
        //this.canMove=true;
        this.color=Kid_State.KID_SLEEP;
        this.power = START_POWER;
    }

    public void paint(Graphics graphics)
    {
        graphics.setColor(color);
        graphics.fillOval(i*g.getSQURE_SIZE()+g.getSQURE_SIZE()/3,j*g.getSQURE_SIZE()+g.getSQURE_SIZE()/3,
                g.getSQURE_SIZE()/2,g.getSQURE_SIZE()/2);
    }

    public synchronized void run()
    {
        while (!hasGift)
        {
            rest();
            if(isGiftHere())
            {
                endWithGift();
                break;
            }

            if(findNicholas())
            {
                goToNicholas();
                if(isHere()) gameOver();
            }
            else
            {
                goRandomPosition();
            }
        }
    }

    private void gameOver()
    {
        System.out.println("Przegrales");
        //System.exit(0);

        board.defeat();
        //board.over=true;

         //*/
    }

    private synchronized void endWithGift()
    {
        hasGift=true;
        this.color=Kid_State.KID_PLAY;
        goToGift();
    }

    public void setOwnGift(int indexX, int indexY, int gX, int gY)
    {
        setGotGift(true);
        setGiftX(gX);
        setGiftY(gY);
        board.boardTable[indexX][indexY]=4;
    }


    private synchronized void goToGift()
    {
        board.boardTable[i][j]=0;
        board.boardTable[giftX][giftY]=3;
        i=giftX;
        j=giftY;
        board.counter++;
    }


    private synchronized void goRandomPosition()
    {
        int indexX = matchIndex(random(i-1,i+1), board.getN());
        int indexY = matchIndex(random(j-1,j+1), board.getM());
        if(board.boardTable[indexX][indexY]!=0) return;
        switchPosition(i,j,indexX,indexY);
        i=indexX;
        j=indexY;
        sleepWhile(randomLong(TIME_NOT_SLEEP_MIN,TIME_NOT_SLEEP_MAX));
        this.color=Kid_State.KID_SLEEP;
    }

    private synchronized void rest()
    {
        this.color=Kid_State.KID_SLEEP;
        try{
            sleep(random(2000,6000));
            power=START_POWER;
        }catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    private synchronized boolean findNicholas()
    {
        this.color=Kid_State.KID_LOOK_FOR;
        sleepWhile(500);
        for(int x = i-VIEW_RANGE;x<=i+VIEW_RANGE;x++)
        {
            int indexX = matchIndex(x,board.getN());
            for(int y = j-VIEW_RANGE;y<=j+VIEW_RANGE;y++)
            {
                int indexY = matchIndex(y,board.getM());
                if(board.boardTable[indexX][indexY]==6) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isGiftHere()
    {
        return this.gotGift;
    }

    private synchronized void goToNicholas()
    {
            this.color=Kid_State.KID_SEE_YOU;
            while (!isHere() && hasPower())
            {
                sleepWhile(KID_SPEED);
                if(i>board.claus.i && board.boardTable[i-1][j]==0)
                {
                    switchPosition(i,j,matchIndex(i-1,board.getN()),j);
                    i=matchIndex(i-1,board.getN());
                }
                else if(i<board.claus.i && board.boardTable[i+1][j]==0)
                {
                    switchPosition(i,j,matchIndex(i+1,board.getN()),j);
                    i=matchIndex(i+1,board.getN());
                }
                sleepWhile(KID_SPEED);
                power--;

                if(j>board.claus.j && board.boardTable[i][j-1]==0)
                {
                    switchPosition(i,j,i,matchIndex(j-1,board.getM()));
                    j=matchIndex(j-1,board.getM());
                }
                else if(j<board.claus.j && board.boardTable[i][j+1]==0)
                {
                    switchPosition(i,j,i,matchIndex(j+1,board.getM()));
                    j=matchIndex(j+1,board.getM());
                }
                power--;
                sleepWhile(100);
                if(isHere()) break;
            }
    }


    private synchronized void sleepWhile(long sec)
    {
        try {
            sleep(sec);
        }catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    private synchronized boolean isHere()
    {
        for(int x = i-1;x<=i+1;x++)
        {
            int goodX = matchIndex(x,board.getN());
            for(int y = j-1;y<=j+1;y++)
            {
                int goodY = matchIndex(y,board.getM());
                if(board.boardTable[goodX][goodY]==6) return true;
            }
        }
        return false;
    }

    private boolean hasPower()
    {
        return this.power>0;
    }


    private synchronized void switchPosition(int oldX, int oldY, int newX, int newY)
    {
        int tmp = board.boardTable[oldX][oldY];
        board.boardTable[oldX][oldY]=board.boardTable[newX][newY];
        board.boardTable[newX][newY]=tmp;
    }

    /////////////////////////////////////////////////////////

    public Color getColor() {
        return color;
    }

    public void setGotGift(boolean gotGift) {
        this.gotGift = gotGift;
    }

    public boolean isGotGift() {
        return gotGift;
    }

    public int getGiftX() {
        return giftX;
    }

    public int getGiftY() {
        return giftY;
    }

    public void setGiftX(int giftX) {
        this.giftX = giftX;
    }

    public void setGiftY(int giftY) {
        this.giftY = giftY;
    }

    @Override
    public int random(int min, int max) {
        return min + (int)(Math.random() * ((max - min) + 1));
    }

    @Override
    public long randomLong(long min, long max) {
        return min + (long)(Math.random() * ((max - min) +1));
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
