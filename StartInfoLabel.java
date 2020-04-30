import javax.swing.*;
import java.awt.*;

public class StartInfoLabel extends JLabel
{
    private static String info = "Mikolaj i dzieciaki!";

    public StartInfoLabel()
    {
        super(info, SwingConstants.CENTER);
        setBounds(0,0,MenuFrame.FRAME_WIDTH,50);
        setPreferredSize(new Dimension(MenuFrame.FRAME_WIDTH,50));
    }
}
