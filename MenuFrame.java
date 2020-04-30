import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuFrame extends JFrame
{
    public static final int FRAME_WIDTH = 400;
    public static final int FRAME_HEIGTH = 200;

    StartInfoLabel startInfoLabel;
    OkButton okButton, exitButton;
    OptionButton optionButton;

    public int n=16,m=17,count=12, speed = 400, range = 4, power = 7;

    public MenuFrame()
    {
        startInfoLabel = new StartInfoLabel();
        okButton = new OkButton("Zaczynajmy");
        optionButton = new OptionButton(this);
        exitButton = new OkButton("Wyjscie");
        setTitle("Mikolaj i dzieciaki");
        setSize(FRAME_WIDTH,FRAME_HEIGTH);
        setVisible(true);
        setLocationRelativeTo(null);
        setFocusable(true);
        setLayout(new GridLayout(4,1));
        add(startInfoLabel);
        add(okButton);
        add(optionButton);
        add(exitButton);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dispose();
                GameFrame g = new GameFrame(n,m,count,speed,range,power);
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });
    }
}
