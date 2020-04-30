import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame
{
    private int n,m,count,speed,range,power;

    public final int SQURE_SIZE;// = 50;
    public final int INFO_SIZE = 50;
    public final int FRAME_WIDTH;
    public final int FRAME_HEIGTH;

    private InfoLabel infoLabel;
    private Board board;


    public GameFrame(int n, int m, int count, int speed, int range, int power)
    {
        this.n=n;
        this.m=m;
        this.count=count;
        this.speed=speed;
        this.range=range;
        this.power=power;
        this.SQURE_SIZE=countGoodSize();
        infoLabel= new InfoLabel(this);
        board = new Board(n,m,count,speed,range,power,this);
        FRAME_WIDTH=SQURE_SIZE*n;
        FRAME_HEIGTH=SQURE_SIZE*m+INFO_SIZE;
        setTitle("Mikolaj i dzieciaki");
        setSize(FRAME_WIDTH,FRAME_HEIGTH);
        setLocationRelativeTo(null);
        setFocusable(true);
        setResizable(false);
        setLayout(new BorderLayout());
        add("North",infoLabel);
        add("Center", board);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }

    public int getM() {
        return m;
    }

    public int getN() {
        return n;
    }

    public int getCount() {
        return count;
    }

    public int getFRAME_WIDTH() {
        return FRAME_WIDTH;
    }

    public int getFRAME_HEIGTH() {
        return FRAME_HEIGTH;
    }

    public int getSQURE_SIZE() {
        return SQURE_SIZE;
    }

    private int countGoodSize()
    {
        if(n>35 ||( m>20 && m<30))
        {
            if(m>=30) return 25;
            else return 40;
        }
        else if(m>=30)
        {
            return 25;
        }
        else return 50;
    }
}
