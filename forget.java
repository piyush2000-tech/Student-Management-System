import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.BorderFactory; 
import javax.swing.border.Border;
import java.sql.*;
import java.util.regex.*;

class forget implements FocusListener , ActionListener
{
	JFrame fr;
	JLabel lclose,lkey;
	JLabel l1,l2,l3,l4;
	JTextField t1,t2;
	Border border1,border2;
	JButton b1;
	
	public forget()
	{	
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		fr = new JFrame();
		fr.setBounds((dim.width-500)/2,(dim.height-300)/2,500,300);
		fr.setLayout(null);
		fr.setContentPane(new JLabel(new ImageIcon("images/forget.png")));
		
		lclose = new JLabel(new ImageIcon("images/close1.png"));
		lclose.setBounds(457,8,32,32);

		l1 = new JLabel("UserName");
		l2 = new JLabel("Email-ID");
		l1.setBounds(130,90,100,30);
		l2.setBounds(130,130,100,30);
		l1.setForeground(Color.cyan);
		l2.setForeground(Color.cyan);
		l1.setFont(new Font("verdana",Font.PLAIN,16));
		l2.setFont(new Font("verdana",Font.PLAIN,16));
		fr.add(l1);
		fr.add(l2);

		border1 = BorderFactory.createLineBorder(Color.gray);
		border2 = BorderFactory.createLineBorder(Color.green);
		
		t1 = new JTextField();
		t2 = new JTextField();
		t1.setBorder(BorderFactory.createCompoundBorder(border1,BorderFactory.createEmptyBorder(1,8,1,1)));	
		t2.setBorder(BorderFactory.createCompoundBorder(border1,BorderFactory.createEmptyBorder(1,8,1,1)));	
		t1.setBounds(240,90,190,30);		
		t2.setBounds(240,130,190,30);		
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

		b1 = new JButton("Recover");
		b1.setBounds(330,180,100,30);
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
			JOptionPane.showMessageDialog(fr,"please enter EMAIL-ID");
			t2.requestFocus();
		}
		else
		{
			try
			{
				String ss = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
				Pattern pt = Pattern.compile(ss);
						
				Matcher mt = pt.matcher(t2.getText());
				if(mt.matches())
				{
					//step 1/2
					Connection con = Dao.createconnection();
					//step 3
					PreparedStatement ps = con.prepareStatement("select * from sms_login where username=? and email=?");
					ps.setString(1,t1.getText());
					ps.setString(2,t2.getText());
					ResultSet rs = ps.executeQuery();	
					if(rs.next()==true)
					{
						JOptionPane.showMessageDialog(fr,"Your Password : " + rs.getString("password"));
					}
					else
					{
						JOptionPane.showMessageDialog(fr,"sorry! invalid username or email-id");
					}
					con.close();
				}
				else
				{
					JOptionPane.showMessageDialog(fr,"please enter valid EMAIL-ID");
					t2.requestFocus();
				}
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