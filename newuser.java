import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.BorderFactory; 
import javax.swing.border.Border;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.sql.*;

class newuser implements FocusListener , ActionListener
{
	JFrame fr;
	JLabel lclose,lkey;
	JLabel l1,l2,l3,l4;
	JTextField t1,t3;
	JPasswordField t2;
	Border border1,border2;
	JButton b1;
	
	public newuser()
	{	
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		fr = new JFrame();
		fr.setBounds((dim.width-500)/2,(dim.height-300)/2,500,300);
		fr.setLayout(null);
		fr.setContentPane(new JLabel(new ImageIcon("images/newuser.png")));
		
		lclose = new JLabel(new ImageIcon("images/close1.png"));
		lclose.setBounds(457,8,32,32);

		l1 = new JLabel("UserName");
		l2 = new JLabel("Password");
		l3 = new JLabel("Email-ID");
		l1.setBounds(130,70,100,30);
		l2.setBounds(130,110,100,30);
		l3.setBounds(130,150,100,30);
		l1.setForeground(Color.cyan);
		l2.setForeground(Color.cyan);
		l3.setForeground(Color.cyan);
		l1.setFont(new Font("verdana",Font.PLAIN,16));
		l2.setFont(new Font("verdana",Font.PLAIN,16));
		l3.setFont(new Font("verdana",Font.PLAIN,16));
		fr.add(l1);
		fr.add(l2);
		fr.add(l3);

		border1 = BorderFactory.createLineBorder(Color.gray);
		border2 = BorderFactory.createLineBorder(Color.green);
		
		t1 = new JTextField();
		t2 = new JPasswordField();
		t3 = new JTextField();
		t1.setBorder(BorderFactory.createCompoundBorder(border1,BorderFactory.createEmptyBorder(1,8,1,1)));	
		t2.setBorder(BorderFactory.createCompoundBorder(border1,BorderFactory.createEmptyBorder(1,8,1,1)));	
		t3.setBorder(BorderFactory.createCompoundBorder(border1,BorderFactory.createEmptyBorder(1,8,1,1)));	
		t1.setBounds(240,70,190,30);		
		t2.setBounds(240,110,190,30);		
		t3.setBounds(240,150,190,30);		
		t1.setCaretColor(Color.green);
		t2.setCaretColor(Color.green);
		t3.setCaretColor(Color.green);
		t1.setFont(new Font("verdana",Font.PLAIN,18));
		t2.setFont(new Font("verdana",Font.PLAIN,18));
		t3.setFont(new Font("verdana",Font.PLAIN,18));
		t1.setOpaque(false);
		t2.setOpaque(false);
		t3.setOpaque(false);
		t1.setForeground(Color.green);
		t2.setForeground(Color.green);
		t3.setForeground(Color.green);
		t1.addFocusListener(this);
		t2.addFocusListener(this);
		t3.addFocusListener(this);
		fr.add(t1);
		fr.add(t2);
		fr.add(t3);
		
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

		b1 = new JButton("Submit");
		b1.setBounds(330,200,100,30);
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
		if(txt==t3)
		{
			t3.setBorder(BorderFactory.createCompoundBorder(border2,BorderFactory.createEmptyBorder(1,8,1,1)));	
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
		if(txt==t3)
		{
			t3.setBorder(BorderFactory.createCompoundBorder(border1,BorderFactory.createEmptyBorder(1,8,1,1)));	
		}
	}

	public void actionPerformed(ActionEvent ae)
	{
		try
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
			else if(t3.getText().trim().length()==0)
			{
				JOptionPane.showMessageDialog(fr,"please enter EMAIL-ID");
				t3.requestFocus();
			}
			else
			{
				if(t3.getText().length()!=0)
				{
					try
					{
						String ss = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
						Pattern pt = Pattern.compile(ss);
						
						Matcher mt = pt.matcher(t3.getText());
						if(mt.matches())
						{
							try
							{
								//step 1/2
								Connection con = Dao.createconnection();
								//step 3
								PreparedStatement ps = con.prepareStatement("insert into SMS_LOGIN values(?,?,?)");
								ps.setString(1,t1.getText());
								ps.setString(2,t2.getText());
								ps.setString(3,t3.getText());
								int z = ps.executeUpdate();	
								if(z>0)
								{
									JOptionPane.showMessageDialog(fr,"user created successfully");
								}
								con.close();
							}
							catch(SQLException e)
							{
								JOptionPane.showMessageDialog(fr,"duplicate entry found");
							}
						}
						else
						{
							JOptionPane.showMessageDialog(fr,"please enter valid EMAIL-ID");
							t3.requestFocus();
						}
					}
					catch(Exception e)
					{
					}
				}
			}
		}
		catch(Exception e)
		{
		}
	}

	public static void main(String args[])
	{
		new login();
	}
}