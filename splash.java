import javax.swing.*;
import java.awt.*;

class splash implements Runnable
{
	JFrame fr;
	JLabel l1;
	int x=1;
	Thread th;
	
	public splash()
	{	
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		fr = new JFrame();
		fr.setBounds((dim.width-400)/2,(dim.height-250)/2,400,250);
		fr.setLayout(null);

		fr.setContentPane(new JLabel(new ImageIcon("images/splash.png")));

		l1 = new JLabel(new ImageIcon("images/line.png"));
		l1.setBounds(1,192,x,5);
		fr.add(l1);

		fr.setUndecorated(true);
		fr.setVisible(true);

		th = new Thread(this);
		th.start();
	}

	public void run()
	{
		while(x<400)
		{
			l1.setSize(x,5);
			x++;
			try
			{
				Thread.sleep(5);
			}
			catch(Exception e)
			{
			}	
		}
		fr.dispose();
		new login();
	}

	public static void main(String args[])
	{
		new splash();
	}
}