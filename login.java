import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.BorderFactory; 
import javax.swing.border.Border;
import java.sql.*;

class login implements FocusListener , ActionListener
{
	JFrame fr;
	JLabel lclose,lkey;
	JLabel l1,l2,l3,l4;
	JTextField t1;
	JPasswordField t2;
	Border border1,border2;
	JButton b1;
	
	public login()
	{	
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		fr = new JFrame();
		fr.setBounds((dim.width-500)/2,(dim.height-300)/2,500,300);
		fr.setLayout(null);
		fr.setContentPane(new JLabel(new ImageIcon("images/login.png")));
		
		lclose = new JLabel(new ImageIcon("images/close1.png"));
		lclose.setBounds(457,8,32,32);
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
				fr.dispose();
			}
		});

		lkey = new JLabel(new ImageIcon("images/key.gif"));
		lkey.setBounds(10,85,120,100);
		fr.add(lkey);

		l1 = new JLabel("UserName");
		l2 = new JLabel("Password");
		l1.setBounds(130,70,100,30);
		l2.setBounds(130,110,100,30);
		l1.setForeground(Color.cyan);
		l2.setForeground(Color.cyan);
		l1.setFont(new Font("verdana",Font.PLAIN,16));
		l2.setFont(new Font("verdana",Font.PLAIN,16));
		fr.add(l1);
		fr.add(l2);

		border1 = BorderFactory.createLineBorder(Color.gray);
		border2 = BorderFactory.createLineBorder(Color.green);
		
		t1 = new JTextField();
		t2 = new JPasswordField();
		t1.setBorder(BorderFactory.createCompoundBorder(border1,BorderFactory.createEmptyBorder(1,8,1,1)));	
		t2.setBorder(BorderFactory.createCompoundBorder(border1,BorderFactory.createEmptyBorder(1,8,1,1)));	
		t1.setBounds(240,70,190,30);		
		t2.setBounds(240,110,190,30);		
		t1.setCaretColor(Color.green);
		t2.setCaretColor(Color.green);
		t1.setFont(new Font("verdana",Font.PLAIN,18));
		t2.setFont(new Font("verdana",Font.PLAIN,18));
		t1.setOpaque(false);
		t2.setOpaque(false);
		t1.setForeground(Color.green);
		t2.setForeground(Color.green);
		fr.add(t1);
		fr.add(t2);
		t1.addFocusListener(this);
		t2.addFocusListener(this);

		l3 = new JLabel("<html><u>New User</u></html>");
		l4 = new JLabel("<html><u>Forget Password</u></html>");
		l3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		l4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		l3.setBounds(80,185,70,25);
		l4.setBounds(80,220,118,25);
		l3.setFont(new Font("verdana",Font.PLAIN,14));
		l4.setFont(new Font("verdana",Font.PLAIN,14));
		l3.setForeground(Color.pink);
		l4.setForeground(Color.pink);
		fr.add(l3);
		fr.add(l4);
		l3.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent me)
			{
				new newuser();
			}
		});
		l4.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent me)
			{
				new forget();
			}
		});


		b1 = new JButton("Login");
		b1.setBounds(330,150,100,30);
		b1.setFont(new Font("verdana",Font.PLAIN,14));
		b1.setForeground(Color.blue);
		b1.addActionListener(this);
		fr.add(b1);

		fr.setUndecorated(true);
		fr.setVisible(true);
	}

	public void focusGained(FocusEvent fe)
	{
		JTextField txt = (JTextField)fe.getComponent();
		if(txt==t1)
		{
			t1.setBorder(BorderFactory.createCompoundBorder(border2,BorderFactory.createEmptyBorder(1,8,1,1)));	
		}
		if(txt==t2)
		{
			t2.setBorder(BorderFactory.createCompoundBorder(border2,BorderFactory.createEmptyBorder(1,8,1,1)));	
		}
	}
	public void focusLost(FocusEvent fe)
	{
		JTextField txt = (JTextField)fe.getComponent();
		if(txt==t1)
		{
			t1.setBorder(BorderFactory.createCompoundBorder(border1,BorderFactory.createEmptyBorder(1,8,1,1)));	
		}
		if(txt==t2)
		{
			t2.setBorder(BorderFactory.createCompoundBorder(border1,BorderFactory.createEmptyBorder(1,8,1,1)));	
		}
	}

	public void actionPerformed(ActionEvent ae)
	{
		if(t1.getText().trim().length()==0)
		{
			JOptionPane.showMessageDialog(fr,"please enter USERNAME");
			t1.requestFocus();
		}
		else if(t2.getText().trim().length()==0)
		{
			JOptionPane.showMessageDialog(fr,"please enter PASSWORD");
			t2.requestFocus();
		}
		else
		{
			try
			{
				//step 1/2
				Connection con = Dao.createconnection();
				//step 3
				PreparedStatement ps = con.prepareStatement("select * from sms_login where username=? and password=?");
				ps.setString(1,t1.getText());
				ps.setString(2,t2.getText());
				ResultSet rs = ps.executeQuery();	
				if(rs.next()==true)
				{
					JOptionPane.showMessageDialog(fr,"Welcome, "+t1.getText().toUpperCase());
					new mainform(t1.getText());
					fr.dispose();
				}
				else
				{
					JOptionPane.showMessageDialog(fr,"sorry! invalid username or password");
				}
				con.close();
			}
			catch(Exception e)
			{
			}
		}	
	}

	public static void main(String args[])
	{
		new login();
	}
}