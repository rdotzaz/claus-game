import javax.swing.*;


public class InfoLabel extends JLabel
{
    private static String info = "Sterowanie: strzałki, w,a,s,d - podłóż prezent";

    public InfoLabel(GameFrame g)
    {
        super(info);
        setBounds(0,0,g.FRAME_WIDTH,50);
    }
}
