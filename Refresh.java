import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Refresh extends Thread
{
    public static final int FPS = 50;
    private Board board;
    private Timer t;
    private int minute;
    private boolean isOver;

    public Refresh(Board b)
    {
        super();
        this.board=b;
        this.minute=0;
        this.isOver=false;
        this.t = new Timer(FPS, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(isOver) {

                }
                else if(board.counter==board.getKIDS_COUNT()) {
                    System.out.println("Wygrales");
                    board.win();
                    isOver=true;
                }
                else
                {
                    board.repaint();
                    /*
                    if(minute%30==0){
                        board.printConsole();
                        minute=0;
                    }
                    else if(minute%29==0)
                    {
                        clearScreen();
                        minute++;
                    }
                    else {
                        minute++;
                    }
                    */

                }
            }
        });
    }

    public void run()
    {
        t.start();
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
