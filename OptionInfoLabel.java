import javax.swing.*;
import java.awt.*;

public class OptionInfoLabel extends JLabel
{
    private static String info = "Ustaw parametry gry:";

    public OptionInfoLabel()
    {
        super(info, SwingConstants.CENTER);
        setBounds(0,0,OptionFrame.FRAME_WIDTH,50);
        setPreferredSize(new Dimension(OptionFrame.FRAME_WIDTH,50));
    }
}
