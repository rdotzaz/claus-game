import javax.swing.*;
import java.awt.*;


public class OkButton extends JButton
{
    public OkButton(String text)
    {
        super(text);
        setPreferredSize(new Dimension(MenuFrame.FRAME_WIDTH,50));
    }
}
