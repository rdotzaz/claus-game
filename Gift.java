import java.awt.*;

public class Gift
{
    public static final Color COLOR_GIFT = Color.GRAY;
    public int i, j;
    private GameFrame g;

    public Gift(int i, int j, GameFrame g)
    {
        this.i=i;
        this.j=j;
        this.g=g;
    }

    public void paint(Graphics graphics)
    {
        graphics.setColor(COLOR_GIFT);
        graphics.fillRect(i*g.getSQURE_SIZE()+g.getSQURE_SIZE()/4,j*g.getSQURE_SIZE()+g.getSQURE_SIZE()/4,
                g.getSQURE_SIZE()/3,g.getSQURE_SIZE()/3);
    }


}
