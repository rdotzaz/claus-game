import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OptionFrame extends JFrame
{
    public static final int FRAME_WIDTH = 350;
    public static final int FRAME_HEIGTH = 400;

    MenuFrame mF;
    OkButton okButton, cancelButton;
    TextField n,m,count,speed, range, power;

    public OptionFrame(MenuFrame mF)
    {
        this.mF=mF;
        okButton = new OkButton("Ok");
        cancelButton = new OkButton("Powrot");
        setTitle("Opcje");
        setSize(FRAME_WIDTH,FRAME_HEIGTH);
        setVisible(true);
        setLocationRelativeTo(null);
        setFocusable(true);
        setLayoutItems();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                checkEverything();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dispose();
            }
        });
    }

    private void setLayoutItems()
    {
        setLayout(new GridLayout(7,2));
        n = new TextField("15");
        m = new TextField("17");
        count = new TextField("12");
        speed = new TextField("400");
        range = new TextField("4");
        power = new TextField("7");
        add(new Label("N",SwingConstants.CENTER));
        add(n);
        add(new Label("M",SwingConstants.CENTER));
        add(m);
        add(new Label("Dzieciaki",SwingConstants.CENTER));
        add(count);
        add(new Label("Opoznienie dzieciaka",SwingConstants.CENTER));
        add(speed);
        add(new Label("Zakres widzenia dzieciaka",SwingConstants.CENTER));
        add(range);
        add(new Label("Wytrzymalosc",SwingConstants.CENTER));
        add(power);
        add(okButton);
        add(cancelButton);
    }

    private void checkEverything()
    {
        try{
            int numberN = Integer.parseInt(n.getText());
            int numberM = Integer.parseInt(m.getText());
            int numberCount = Integer.parseInt(count.getText());
            int numberSpeed = Integer.parseInt(speed.getText());
            int numberRange = Integer.parseInt(range.getText());
            int numberPower = Integer.parseInt(power.getText());
            if(isDataNotCorrect(numberN,numberM,numberCount,numberSpeed,numberRange,numberPower)) throw new IllegalArgumentException();
            mF.m=numberM;
            mF.n=numberN;
            mF.count=numberCount;
            mF.speed=numberSpeed;
            mF.range=numberRange;
            mF.power=numberPower;
            dispose();

        }
        catch (NumberFormatException e)
        {
            ErrorFrame eF = new ErrorFrame("Prosze wpisac liczbe");
        }
        catch (IllegalArgumentException e)
        {
            ErrorFrame eF = new ErrorFrame("Sprobuj ponownie");
        }
    }

    private boolean isDataNotCorrect(int numN, int numM, int numCou, int numSpeed, int numRange, int numPow)
    {
        return (numM<10 || numM>40 || numN<10 || numN>50 || numSpeed<150 || numRange<2 || numPow<2 || numCou>((numN/2)*numM));
    }
}
