import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EndFrame extends JFrame
{
    public static final int FRAME_WIDTH = 200;
    public static final int FRAME_HEIGTH = 200;

    JLabel info;
    OkButton okButton, menuButton, reGameButton;
    int n,m,count,speed, range, power;

    public EndFrame(String label, String title, int n, int m, int count, int speed, int range, int power)
    {
        this.n=n;
        this.m=m;
        this.count=count;
        this.speed=speed;
        this.range=range;
        this.power=power;
        info = new JLabel(label,SwingConstants.CENTER);
        okButton = new OkButton("Wyjdz");
        menuButton = new OkButton("Powrot do menu");
        reGameButton = new OkButton("Sprobuj jeszcze raz");
        setTitle(title);
        setSize(FRAME_WIDTH,FRAME_HEIGTH);
        setLocationRelativeTo(null);
        setFocusable(true);
        setLayout(new GridLayout(4,1));
        add(info);
        add(okButton);
        add(menuButton);
        add(reGameButton);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dispose();
                System.exit(0);
            }
        });

        menuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dispose();
                MenuFrame m = new MenuFrame();
            }
        });

        reGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dispose();
                GameFrame newg = new GameFrame(n,m,count,speed,range,power);
            }
        });

    }
}
