import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.BorderFactory; 
import javax.swing.border.Border;
import java.sql.*;
import java.util.Date;
import java.text.*;

class mainform implements Runnable , ActionListener
{
	JFrame fr;
	JLabel l1,l2,lclose,ll1,ll2,ll3,ll4,ll5,ll6;
	Thread th = null;
	JPopupMenu pop;
	JMenuItem m1,m2,m3,m4,m5;
	
	public mainform(String un)
	{	
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		fr = new JFrame();
		fr.setSize(dim.width,dim.height);
		fr.setLayout(null);
		fr.setContentPane(new JLabel(new ImageIcon("images/mainform.png")));
		
		lclose = new JLabel(new ImageIcon("images/close1.png"));
		lclose.setBounds(dim.width-90,25,32,32);
		lclose.setToolTipText("Quit Application");
		lclose.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		fr.add(lclose); 
		lclose.addMouseListener(new MouseAdapter()
		{
			public void mouseEntered(MouseEvent me)
			{
				lclose.setIcon(new ImageIcon("images/close2.png"));
			}
			public void mouseExited(MouseEvent me)
			{
				lclose.setIcon(new ImageIcon("images/close1.png"));
			}
			public void mouseClicked(MouseEvent me)
			{
				System.exit(0);
			}
		});

		l1 = new JLabel("Hi, " + un.toUpperCase());		
		l1.setBounds(20,20,200,30);
		l1.setFont(new Font("verdana",Font.BOLD,20));
		l1.setForeground(Color.white);
		fr.add(l1);

		l2 = new JLabel("");		
		l2.setBounds(20,50,300,30);
		l2.setFont(new Font("verdana",Font.BOLD,18));
		l2.setForeground(Color.white);
		fr.add(l2);

		ll1 = new JLabel(new ImageIcon("images/add1.png"));
		ll1.setBounds(20,150,100,100);
		ll1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		ll1.setToolTipText("Add Record");
		fr.add(ll1);
		ll1.addMouseListener(new MouseAdapter()
		{
			public void mouseEntered(MouseEvent me)
			{
				ll1.setIcon(new ImageIcon("images/add2.png"));
			}
			public void mouseExited(MouseEvent me)
			{
				ll1.setIcon(new ImageIcon("images/add1.png"));
			}
			public void mouseClicked(MouseEvent me)
			{
				fr.setEnabled(false);
				new insert(fr);
			}
		});

		ll2 = new JLabel(new ImageIcon("images/search1.png"));
		ll2.setBounds(20,260,100,100);
		ll2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		ll2.setToolTipText("Search Record");fr.add(ll2);
		ll2.addMouseListener(new MouseAdapter()
		{
			public void mouseEntered(MouseEvent me)
			{
				ll2.setIcon(new ImageIcon("images/search2.png"));
			}
			public void mouseExited(MouseEvent me)
			{
				ll2.setIcon(new ImageIcon("images/search1.png"));
			}
			public void mouseClicked(MouseEvent me)
			{
				fr.setEnabled(false);
				new search(fr);
			}
		});

		ll3 = new JLabel(new ImageIcon("images/delete1.png"));
		ll3.setBounds(20,370,100,100);
		ll3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		ll3.setToolTipText("Delete Record");
		fr.add(ll3);
		ll3.addMouseListener(new MouseAdapter()
		{
			public void mouseEntered(MouseEvent me)
			{
				ll3.setIcon(new ImageIcon("images/delete2.png"));
			}
			public void mouseExited(MouseEvent me)
			{
				ll3.setIcon(new ImageIcon("images/delete1.png"));
			}
			public void mouseClicked(MouseEvent me)
			{
				fr.setEnabled(false);
				new delete(fr);
			}
		});

		ll4 = new JLabel(new ImageIcon("images/edit1.png"));
		ll4.setBounds(20,480,100,100);
		ll4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		ll4.setToolTipText("Edit Record");
		fr.add(ll4);
		ll4.addMouseListener(new MouseAdapter()
		{
			public void mouseEntered(MouseEvent me)
			{
				ll4.setIcon(new ImageIcon("images/edit2.png"));
			}
			public void mouseExited(MouseEvent me)
			{
				ll4.setIcon(new ImageIcon("images/edit1.png"));
			}
			public void mouseClicked(MouseEvent me)
			{
				fr.setEnabled(false);
				new modify(fr);
			}
		});

		ll5 = new JLabel(new ImageIcon("images/display1.png"));
		ll5.setBounds(20,590,100,100);
		ll5.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		ll5.setToolTipText("Display Record");
		fr.add(ll5);
		ll5.addMouseListener(new MouseAdapter()
		{
			public void mouseEntered(MouseEvent me)
			{
				ll5.setIcon(new ImageIcon("images/display2.png"));
			}
			public void mouseExited(MouseEvent me)
			{
				ll5.setIcon(new ImageIcon("images/display1.png"));
			}
			public void mouseClicked(MouseEvent me)
			{
				fr.setEnabled(false);
				new display(fr);
			}
		});

		pop = new JPopupMenu();
		m1 = new JMenuItem(" Insert");
		m2 = new JMenuItem(" Search");
		m3 = new JMenuItem(" Remove");
		m4 = new JMenuItem(" Modify");
		m5 = new JMenuItem(" Display");
		m1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I,KeyEvent.CTRL_MASK));
		m2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,KeyEvent.CTRL_MASK));
		m3.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R,KeyEvent.CTRL_MASK));
		m4.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M,KeyEvent.CTRL_MASK));
		m5.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D,KeyEvent.CTRL_MASK));
		m1.setFont(new Font("verdana",Font.PLAIN,16));
		m2.setFont(new Font("verdana",Font.PLAIN,16));
		m3.setFont(new Font("verdana",Font.PLAIN,16));
		m4.setFont(new Font("verdana",Font.PLAIN,16));
		m5.setFont(new Font("verdana",Font.PLAIN,16));
		m1.setIcon(new ImageIcon("images/add.png"));
		m2.setIcon(new ImageIcon("images/search.png"));
		m3.setIcon(new ImageIcon("images/delete.png"));
		m4.setIcon(new ImageIcon("images/edit.png"));
		m5.setIcon(new ImageIcon("images/display.png"));
		m1.addActionListener(this);
		m2.addActionListener(this);
		m3.addActionListener(this);
		m4.addActionListener(this);
		m5.addActionListener(this);
		m1.setForeground(Color.blue);
		m2.setForeground(Color.blue);
		m3.setForeground(Color.blue);
		m4.setForeground(Color.blue);
		m5.setForeground(Color.blue);
		pop.add(m1);
		pop.addSeparator();
		pop.add(m2);
		pop.addSeparator();
		pop.add(m3);
		pop.addSeparator();
		pop.add(m4);
		pop.addSeparator();
		pop.add(m5);
				
		fr.addMouseListener(new MouseAdapter()
		{	
			public void cat(MouseEvent me)
			{
				if(me.isPopupTrigger())
				{
					pop.show(fr,me.getX(),me.getY());
				}
			}

			public void mousePressed(MouseEvent me)
			{
				cat(me);
			}
			public void mouseReleased(MouseEvent me)
			{
				cat(me);
			}
		});

		fr.setUndecorated(true);
		fr.setVisible(true);
		th = new Thread(this);
		th.start();
	}

	public void run()
	{
		while(true)
		{
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy hh:mm:ss");
			Date date = new Date();
			String dt = sdf.format(date).toString();
			l2.setText(dt);
			try
			{
				Thread.sleep(1000);
			}
			catch(InterruptedException e)
			{
			}
		}			
	}

	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==m1)
		{
			fr.setEnabled(false);
			new insert(fr);
		}
		if(ae.getSource()==m2)
		{
			fr.setEnabled(false);
			new search(fr);
		}
		if(ae.getSource()==m3)
		{
			fr.setEnabled(false);
			new delete(fr);
		}
		if(ae.getSource()==m4)
		{
			fr.setEnabled(false);
			new modify(fr);
		}
		if(ae.getSource()==m5)
		{
			fr.setEnabled(false);
			new display(fr);
		}
	}

	public static void main(String args[])
	{
		new mainform("sharad");
	}
}