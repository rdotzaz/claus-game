import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OptionButton extends JButton
{
    public OptionButton(MenuFrame mF)
    {
        super("Opcje");
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                OptionFrame o = new OptionFrame(mF);
            }
        });
    }
}
