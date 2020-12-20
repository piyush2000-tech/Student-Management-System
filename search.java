import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.BorderFactory; 
import javax.swing.border.Border;
import java.sql.*;
import java.util.Date;
import java.text.*;
import java.util.UUID;
import java.io.*;
import java.util.regex.*;

class search implements ActionListener
{
	String month[]={"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
	int fees[] = {5000,5500,15000,7500,8000,4000,15000,25000,20000,10000};
	int duration[]={3,4,6,3,4,2,5,3,3,4};	
	JFrame fr,fn;
	JLabel lclose;
	JLabel l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,l11,l12,l13,l14,l15,l16,lphoto;	
	JTextField t1,t2,t3,t5,t6,t7,t8,t9,t10,c4,c5,c6,c7;
	JTextArea t4;
	
	JRadioButton r1,r2;
	JComboBox c1,c2,c3;
	Border border1;
	ButtonGroup bg;
	JButton b1,b2,b3,b4;
	Image original,scaled;
	String path;
	double am=0,dis=0,tot=0;
	int p=0;
		
	Connection con;
	ResultSet rs;
	PreparedStatement ps;

	public search(JFrame f)
	{	
		fn = f;
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		fr = new JFrame();
		fr.setBounds((dim.width-1000)/2,(dim.height-450)/2,1000,550);
		fr.setLayout(null);
		fr.setContentPane(new JLabel(new ImageIcon("images/searchform.png")));
		
		lclose = new JLabel(new ImageIcon("images/close1.png"));
		lclose.setBounds(943,20,32,32);
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
				fn.setEnabled(true);
				fr.dispose();
			}
		});

		l1 = new JLabel("Student ID");
		l2 = new JLabel("Student's Name");
		l3 = new JLabel("Father's Name");
		l4 = new JLabel("Gender");
		l5 = new JLabel("Date of Birth");
		l6 = new JLabel("Address");
		l7 = new JLabel("Mobile");
		l8 = new JLabel("Email-ID");
		l1.setFont(new Font("verdana",Font.PLAIN,18));
		l2.setFont(new Font("verdana",Font.PLAIN,18));
		l3.setFont(new Font("verdana",Font.PLAIN,18));
		l4.setFont(new Font("verdana",Font.PLAIN,18));
		l5.setFont(new Font("verdana",Font.PLAIN,18));
		l6.setFont(new Font("verdana",Font.PLAIN,18));
		l7.setFont(new Font("verdana",Font.PLAIN,18));
		l8.setFont(new Font("verdana",Font.PLAIN,18));
		l1.setBounds(60,80,120,30);
		l2.setBounds(60,130,150,30);
		l3.setBounds(60,180,150,30);
		l4.setBounds(60,230,150,30);
		l5.setBounds(60,280,150,30);
		l6.setBounds(60,330,150,30);
		l7.setBounds(60,420,150,30);
		l8.setBounds(60,470,150,30);
		l1.setForeground(Color.pink);
		l2.setForeground(Color.pink);
		l3.setForeground(Color.pink);
		l4.setForeground(Color.pink);
		l5.setForeground(Color.pink);
		l6.setForeground(Color.pink);
		l7.setForeground(Color.pink);
		l8.setForeground(Color.pink);
		fr.add(l1);
		fr.add(l2);
		fr.add(l3);
		fr.add(l4);
		fr.add(l5);
		fr.add(l6);
		fr.add(l7);
		fr.add(l8);
		
		border1 = BorderFactory.createLineBorder(Color.gray);
		
		t1 = new JTextField();
		t1.setEditable(false);
		t2 = new JTextField();
		t3 = new JTextField();
		t4 = new JTextArea();
		t5 = new JTextField();
		t6 = new JTextField();
		t1.setFont(new Font("verdana",Font.PLAIN,18));
		t2.setFont(new Font("verdana",Font.PLAIN,18));
		t3.setFont(new Font("verdana",Font.PLAIN,18));
		t4.setFont(new Font("verdana",Font.PLAIN,18));
		t5.setFont(new Font("verdana",Font.PLAIN,18));
		t6.setFont(new Font("verdana",Font.PLAIN,18));
		t1.setBorder(BorderFactory.createCompoundBorder(border1,BorderFactory.createEmptyBorder(1,8,1,1)));	
		t2.setBorder(BorderFactory.createCompoundBorder(border1,BorderFactory.createEmptyBorder(1,8,1,1)));	
		t3.setBorder(BorderFactory.createCompoundBorder(border1,BorderFactory.createEmptyBorder(1,8,1,1)));	
		t4.setBorder(BorderFactory.createCompoundBorder(border1,BorderFactory.createEmptyBorder(1,8,1,1)));	
		t5.setBorder(BorderFactory.createCompoundBorder(border1,BorderFactory.createEmptyBorder(1,8,1,1)));	
		t6.setBorder(BorderFactory.createCompoundBorder(border1,BorderFactory.createEmptyBorder(1,8,1,1)));	
		t1.setBounds(225,80,230,30);
		t2.setBounds(225,130,230,30);
		t3.setBounds(225,180,230,30);
		t4.setBounds(225,330,230,70);
		t5.setBounds(225,420,230,30);
		t6.setBounds(225,470,230,30);
		t1.setForeground(Color.green);
		t2.setForeground(Color.green);
		t3.setForeground(Color.green);
		t4.setForeground(Color.green);
		t5.setForeground(Color.green);
		t6.setForeground(Color.green);
		t1.setCaretColor(Color.green);
		t2.setCaretColor(Color.green);
		t3.setCaretColor(Color.green);
		t4.setCaretColor(Color.green);
		t5.setCaretColor(Color.green);
		t6.setCaretColor(Color.green);
		t1.setOpaque(false);
		t2.setOpaque(false);
		t3.setOpaque(false);
		t4.setOpaque(false);
		t5.setOpaque(false);
		t6.setOpaque(false);
		fr.add(t1);
		fr.add(t2);
		fr.add(t3);
		fr.add(t4);
		fr.add(t5);
		fr.add(t6);
		t5.addKeyListener(new KeyAdapter()
		{
			public void keyTyped(KeyEvent ke)
			{
				char ch = ke.getKeyChar();
				if(ch<'0' || ch>'9')
				{
					ke.consume();
				}
				if(t5.getText().length()==10)
				{
					ke.consume();
				}
			}
		});
		
		bg = new ButtonGroup();
		r1 = new JRadioButton("Male");
		r2 = new JRadioButton("Female");
		r1.setBounds(225,230,70,30);
		r2.setBounds(315,230,95,30);
		r1.setOpaque(false);
		r2.setOpaque(false);
		r1.setFont(new Font("verdana",Font.PLAIN,19));
		r2.setFont(new Font("verdana",Font.PLAIN,19));
		r1.setForeground(Color.green);
		r2.setForeground(Color.green);
		bg.add(r1);
		bg.add(r2);
		fr.add(r1);
		fr.add(r2);

		c1 = new JComboBox();
		c1.addItem(" ");
		for(int i=1 ; i<=31 ; i++)
		{
			c1.addItem(""+i);
		}
		c1.setFont(new Font("verdana",Font.BOLD,16));
		c1.setBounds(225,280,60,30);
		c1.setBackground(Color.black);
		c1.setForeground(Color.green);
		fr.add(c1);

		c2 = new JComboBox();
		c2.addItem(" ");
		for(int i=0 ; i<12 ; i++)
		{
			c2.addItem(month[i]);
		}
		c2.setFont(new Font("verdana",Font.BOLD,16));
		c2.setBounds(295,280,70,30);
		c2.setBackground(Color.black);
		c2.setForeground(Color.green);
		fr.add(c2);

		c3 = new JComboBox();
		c3.addItem(" ");
		for(int i=1980 ; i<=2005 ; i++)
		{
			c3.addItem(""+i);
		}
		c3.setFont(new Font("verdana",Font.BOLD,16));
		c3.setBounds(375,280,80,30);
		c3.setBackground(Color.black);
		c3.setForeground(Color.green);
		fr.add(c3);

		lphoto = new JLabel(new ImageIcon("images/photo.png"));
		lphoto.setBounds(860,80,80,80);
		fr.add(lphoto);	

		l9 = new JLabel("Course");
		l10 = new JLabel("Branch");
		l11 = new JLabel("College Name");
		l12 = new JLabel("Year");
		l13 = new JLabel("Select");
		l14 = new JLabel("Fees");
		l15 = new JLabel("Duration");
		l9.setBounds(525,80,130,30);
		l10.setBounds(525,130,130,30);
		l11.setBounds(525,180,130,30);
		l12.setBounds(525,230,130,30);
		l13.setBounds(525,295,160,30);
		l14.setBounds(525,345,160,30);
		l15.setBounds(525,395,160,30);
		l9.setFont(new Font("verdana",Font.PLAIN,18));
		l10.setFont(new Font("verdana",Font.PLAIN,18));
		l11.setFont(new Font("verdana",Font.PLAIN,18));
		l12.setFont(new Font("verdana",Font.PLAIN,18));
		l13.setFont(new Font("verdana",Font.PLAIN,18));
		l14.setFont(new Font("verdana",Font.PLAIN,18));
		l15.setFont(new Font("verdana",Font.PLAIN,18));
		l9.setForeground(Color.pink);
		l10.setForeground(Color.pink);
		l11.setForeground(Color.pink);
		l12.setForeground(Color.pink);
		l13.setForeground(Color.pink);
		l14.setForeground(Color.pink);
		l15.setForeground(Color.pink);
		fr.add(l9);
		fr.add(l10);
		fr.add(l11);
		fr.add(l12);
		fr.add(l13);
		fr.add(l14);
		fr.add(l15);
		
		c4 = new JTextField();
		c4.setBorder(BorderFactory.createCompoundBorder(border1,BorderFactory.createEmptyBorder(1,8,1,1)));	
		c4.setFont(new Font("verdana",Font.BOLD,16));
		c4.setBounds(665,80,170,30);
		c4.setBackground(Color.black);
		c4.setForeground(Color.green);
		fr.add(c4);

		c5 = new JTextField();
		c5.setBorder(BorderFactory.createCompoundBorder(border1,BorderFactory.createEmptyBorder(1,8,1,1)));	
		c5.setFont(new Font("verdana",Font.BOLD,16));
		c5.setBounds(665,130,170,30);
		c5.setBackground(Color.black);
		c5.setForeground(Color.green);
		fr.add(c5);

		t7 = new JTextField();
		t7.setFont(new Font("verdana",Font.PLAIN,18));
		t7.setBorder(BorderFactory.createCompoundBorder(border1,BorderFactory.createEmptyBorder(1,8,1,1)));	
		t7.setForeground(Color.green);
		t7.setOpaque(false);
		t7.setBounds(665,180,275,30);
		t7.setCaretColor(Color.green);
		fr.add(t7);	

		t8 = new JTextField();
		t8.setEditable(false);
		t8.setFont(new Font("verdana",Font.PLAIN,18));
		t8.setBorder(BorderFactory.createCompoundBorder(border1,BorderFactory.createEmptyBorder(1,8,1,1)));	
		t8.setForeground(Color.green);
		t8.setOpaque(false);
		t8.setBounds(665,345,275,30);
		t8.setCaretColor(Color.green);
		fr.add(t8);

		t9 = new JTextField();
		t9.setEditable(false);
		t9.setFont(new Font("verdana",Font.PLAIN,18));
		t9.setBorder(BorderFactory.createCompoundBorder(border1,BorderFactory.createEmptyBorder(1,8,1,1)));	
		t9.setForeground(Color.green);
		t9.setOpaque(false);
		t9.setBounds(665,395,275,30);
		t9.setCaretColor(Color.green);
		fr.add(t9);
	
		t10 = new JTextField();
		t10.setEditable(false);
		t10.setFont(new Font("verdana",Font.PLAIN,18));
		t10.setBorder(BorderFactory.createCompoundBorder(border1,BorderFactory.createEmptyBorder(1,8,1,1)));	
		t10.setForeground(Color.green);
		t10.setOpaque(false);
		t10.setBounds(665,445,275,30);
		t10.setCaretColor(Color.green);
		fr.add(t10);

		t1.setEditable(false);
		t2.setEditable(false);
		t3.setEditable(false);
		t4.setEditable(false);
		t5.setEditable(false);
		t6.setEditable(false);
		t7.setEditable(false);
		t8.setEditable(false);
		t9.setEditable(false);
		t10.setEditable(false);

		c6 = new JTextField();
		c6.setBorder(BorderFactory.createCompoundBorder(border1,BorderFactory.createEmptyBorder(1,8,1,1)));	
		c6.setFont(new Font("verdana",Font.BOLD,16));
		c6.setBounds(665,230,275,30);
		c6.setBackground(Color.black);
		c6.setForeground(Color.green);
		fr.add(c6);

		c7 = new JTextField();
		c7.setBorder(BorderFactory.createCompoundBorder(border1,BorderFactory.createEmptyBorder(1,8,1,1)));	
		c7.setFont(new Font("verdana",Font.BOLD,16));
		c7.setBounds(665,298,275,30);
		c7.setBackground(Color.black);
		c7.setForeground(Color.green);
		fr.add(c7);

		c4.setEditable(false);
		c5.setEditable(false);
		c6.setEditable(false);
		c7.setEditable(false);
		
		l16 = new JLabel("Fees Received");
		l16.setBounds(525,445,130,30);
		l16.setFont(new Font("verdana",Font.PLAIN,16));
		l16.setForeground(Color.pink);
		l16.setOpaque(false);
		fr.add(l16);
	
		b1 = new JButton("Reset");
		b2 = new JButton("Search");
		b1.setBounds(698,497,100,35);
		b2.setBounds(810,497,100,35);
		b1.setFont(new Font("verdana",Font.BOLD,14));
		b2.setFont(new Font("verdana",Font.BOLD,14));
		b1.setForeground(Color.blue);
		b2.setForeground(Color.blue);
		b1.addActionListener(this);
		b2.addActionListener(this);
		fr.add(b1);
		fr.add(b2);
		
		fr.setUndecorated(true);
		fr.setVisible(true);
		connect();
	}

	public void connect()
	{
		try
		{
			con = Dao.createconnection();	
			ps = con.prepareStatement("select * from sms_record where student_id=?");
			String id = JOptionPane.showInputDialog(fr,"Enter Student ID");
			if(id==null)
			{
				JOptionPane.showMessageDialog(fr,"Cancelled by user");
			}
			else
			{
				ps.setString(1,id);
				rs = ps.executeQuery();
				if(rs.next()==true)
				{
					t1.setText(rs.getString("student_id"));
					t2.setText(rs.getString("student_name"));
					t3.setText(rs.getString("father_name"));
					if(rs.getString("gender").equals("Male"))
					{
						r1.setSelected(true);
					}
					else
					{
						r2.setSelected(true);
					}
					String dob = rs.getString("dob");
					String dt[] = dob.split("/");
					c1.setSelectedItem(dt[0]);
					c2.setSelectedItem(dt[1]);
					c3.setSelectedItem(dt[2]);
					t4.setText(rs.getString("address"));
					t5.setText(rs.getString("mobile"));
					t6.setText(rs.getString("email"));
					c4.setText(rs.getString("course"));
					c5.setText(rs.getString("branch"));
					t7.setText(rs.getString("college_name"));
					c6.setText(rs.getString("year"));
					c7.setText(rs.getString("select_course"));
					t8.setText(rs.getString("fees"));
					t9.setText(rs.getString("duration"));
					t10.setText(rs.getString("total"));
				
					Blob b = rs.getBlob("photo"); 
					byte barr[] = b.getBytes(1,(int)b.length());  
              				FileOutputStream fout = new FileOutputStream("photo/"+rs.getString(1)+".jpg");  
					fout.write(barr);  
              				fout.close();
					original = Toolkit.getDefaultToolkit().getImage("photo/"+rs.getString(1)+".jpg");	
					scaled = original.getScaledInstance(80,80,Image.SCALE_DEFAULT);
					lphoto.setIcon(new ImageIcon(scaled));  
				}
				else
				{
					JOptionPane.showMessageDialog(fr,"Student ID does not exists");
				}
				con.close();
			}
		}
		catch(Exception e)
		{
		}
	}

	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==b1)
		{
			reset();
		}
		if(ae.getSource()==b2)
		{
			connect();
		}
	}

	public void reset()
	{
		p=0;
		t1.setText("");
		t2.setText("");
		t3.setText("");
		t4.setText("");
		t5.setText("");
		t6.setText("");
		t7.setText("");
		t8.setText("");
		t9.setText("");
		t10.setText("");
		bg.clearSelection();
		c1.setSelectedIndex(0);	
		c2.setSelectedIndex(0);	
		c3.setSelectedIndex(0);	
		c4.setText("");	
		c5.setText("");	
		c6.setText("");	
		c7.setText("");	
		lphoto.setIcon(new ImageIcon("images/photo.png"));
	}
}
