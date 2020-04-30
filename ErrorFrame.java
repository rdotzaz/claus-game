import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ErrorFrame extends JFrame
{
    public static final int FRAME_WIDTH = 200;
    public static final int FRAME_HEIGTH = 100;

    JLabel info;
    OkButton okButton;

    public ErrorFrame(String e)
    {
        info = new JLabel(e);
        okButton = new OkButton("Ok");
        setTitle("Blad");
        setSize(FRAME_WIDTH,FRAME_HEIGTH);
        setVisible(true);
        setLocationRelativeTo(null);
        setFocusable(true);
        setLayout(new BorderLayout());
        add("North",info);
        add("South",okButton);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dispose();
            }
        });

    }
}
