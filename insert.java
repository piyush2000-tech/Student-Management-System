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

class insert implements ItemListener , ActionListener
{
	String month[]={"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
	int fees[] = {5000,5500,15000,7500,8000,4000,15000,25000,20000,10000};
	int duration[]={3,4,6,3,4,2,5,3,3,4};	
	JFrame fr,fn;
	JLabel lclose;
	JLabel l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,l11,l12,l13,l14,l15,lphoto,lbrowse;	
	JTextField t1,t2,t3,t5,t6,t7,t8,t9,t10;
	JTextArea t4;
	
	JRadioButton r1,r2;
	JComboBox c1,c2,c3,c4,c5,c6,c7;
	Border border1;
	ButtonGroup bg;
	JButton b1,b2;
	Image original,scaled;
	String path;
	JCheckBox chk;
	double am=0,dis=0,tot=0;
	int p=0;
		
	public insert(JFrame f)
	{	
		fn = f;
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		fr = new JFrame();
		fr.setBounds((dim.width-1000)/2,(dim.height-450)/2,1000,550);
		fr.setLayout(null);
		fr.setContentPane(new JLabel(new ImageIcon("images/addform.png")));
		
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

		l1 = new JLabel("<html><u>Generate ID</u></html>");
		l1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
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
		l1.setForeground(Color.green);
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
		l1.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent me)
			{			
				String id = UUID.randomUUID().toString();
				id = id.substring(0,6);
				t1.setText("GLA-"+id);
			}
		});

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
		r1 = new JRadioButton("Male",true);
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
		lphoto.setBounds(860,55,80,80);
		fr.add(lphoto);	

		lbrowse = new JLabel("<html><u>Browse</u></html>");
		lbrowse.setBounds(875,140,60,20);
		lbrowse.setFont(new Font("verdana",Font.PLAIN,14));
		lbrowse.setForeground(Color.green);
		lbrowse.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		fr.add(lbrowse);
		lbrowse.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent me)
			{
				try
				{
					FileDialog fd = new FileDialog(fr,"Load Photo",FileDialog.LOAD);
					fd.setVisible(true);
					path = fd.getDirectory() + fd.getFile();
					if(path.equals("nullnull"))
					{
						p=0;
						JOptionPane.showMessageDialog(fr,"Cancled by User");
					}
					else
					{
						p=1;
						original = Toolkit.getDefaultToolkit().getImage(path);
						scaled = original.getScaledInstance(80,80,Image.SCALE_DEFAULT);
						lphoto.setIcon(new ImageIcon(scaled));
					}
				}
				catch(Exception e)
				{
				}
			}
		});
	

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
		
		c4 = new JComboBox();
		c4.addItem("BCA");		
		c4.addItem("BBA");		
		c4.addItem("B.Sc");		
		c4.addItem("B.Com");		
		c4.addItem("B.Tech");		
		c4.addItem("MCA");		
		c4.addItem("MBA");		
		c4.addItem("M.Sc");		
		c4.addItem("M.Com");		
		c4.addItem("M.Tech");		
		c4.setFont(new Font("verdana",Font.BOLD,16));
		c4.setBounds(665,80,170,30);
		c4.setBackground(Color.black);
		c4.setForeground(Color.green);
		fr.add(c4);

		c5 = new JComboBox();
		c5.addItem("None");		
		c5.addItem("CS");		
		c5.addItem("IT");		
		c5.addItem("EC");		
		c5.addItem("ME");		
		c5.addItem("EE");		
		c5.addItem("CE");		
		c5.addItem("Physics");		
		c5.addItem("Math");		
		c5.addItem("Chemistry");		
		c5.addItem("Biology");		
		c5.addItem("English");		
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

		t8 = new JTextField("5000");
		t8.setEditable(false);
		t8.setFont(new Font("verdana",Font.PLAIN,18));
		t8.setBorder(BorderFactory.createCompoundBorder(border1,BorderFactory.createEmptyBorder(1,8,1,1)));	
		t8.setForeground(Color.green);
		t8.setOpaque(false);
		t8.setBounds(665,345,275,30);
		t8.setCaretColor(Color.green);
		fr.add(t8);

		t9 = new JTextField("3 Months");
		t9.setEditable(false);
		t9.setFont(new Font("verdana",Font.PLAIN,18));
		t9.setBorder(BorderFactory.createCompoundBorder(border1,BorderFactory.createEmptyBorder(1,8,1,1)));	
		t9.setForeground(Color.green);
		t9.setOpaque(false);
		t9.setBounds(665,395,275,30);
		t9.setCaretColor(Color.green);
		fr.add(t9);
	
		t10 = new JTextField("5000");
		t10.setEditable(false);
		t10.setFont(new Font("verdana",Font.PLAIN,18));
		t10.setBorder(BorderFactory.createCompoundBorder(border1,BorderFactory.createEmptyBorder(1,8,1,1)));	
		t10.setForeground(Color.green);
		t10.setOpaque(false);
		t10.setBounds(665,445,275,30);
		t10.setCaretColor(Color.green);
		fr.add(t10);

		c6 = new JComboBox();
		c6.addItem("1 Year");		
		c6.addItem("2 Year");		
		c6.addItem("3 Year");		
		c6.addItem("4 Year");		
		c6.setFont(new Font("verdana",Font.BOLD,16));
		c6.setBounds(665,230,275,30);
		c6.setBackground(Color.black);
		c6.setForeground(Color.green);
		fr.add(c6);

		c7 = new JComboBox();
		c7.addItem("C & C++");		
		c7.addItem("PHP");		
		c7.addItem("Java");		
		c7.addItem("Python");		
		c7.addItem("Dot Net");		
		c7.addItem("Oracle");		
		c7.addItem("Hadoop");		
		c7.addItem("Digital Marketing");		
		c7.addItem("Cloud Computing");		
		c7.addItem("Web-Designing");
		c7.setFont(new Font("verdana",Font.BOLD,16));
		c7.setBounds(665,298,275,30);
		c7.setBackground(Color.black);
		c7.setForeground(Color.green);
		c7.addItemListener(this);
		fr.add(c7);

		chk = new JCheckBox("Discount 10%");
		chk.setBounds(525,445,130,30);
		chk.setFont(new Font("verdana",Font.PLAIN,14));
		chk.setForeground(Color.pink);
		chk.setOpaque(false);
		chk.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		fr.add(chk);
		chk.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent me)
			{	
				if(t8.getText().length()!=0)
				{
					if(chk.isSelected()==true)
					{
						am = Double.parseDouble(t8.getText());
						dis = am * 10 / 100;
						tot = am - dis;
						t10.setText(""+tot);	
					}
					if(chk.isSelected()==false)
					{
						am=0;
						dis=0;
						tot=0;
						t10.setText(t8.getText());
					}
				}
				else
				{
					
				}
			}
		});

		b1 = new JButton("Reset");
		b2 = new JButton("Save");
		b1.setBounds(698,500,100,30);
		b2.setBounds(810,500,100,30);
		b1.setFont(new Font("verdana",Font.BOLD,15));
		b2.setFont(new Font("verdana",Font.BOLD,15));
		b1.setForeground(Color.blue);
		b2.setForeground(Color.blue);
		b1.addActionListener(this);
		b2.addActionListener(this);
		fr.add(b1);
		fr.add(b2);
		
		
		
		fr.setUndecorated(true);
		fr.setVisible(true);
		
	}

	public void itemStateChanged(ItemEvent ie)
	{
		int indx = c7.getSelectedIndex();
		t8.setText(""+fees[indx]);			
		t10.setText(""+fees[indx]);			
		t9.setText(""+duration[indx]+" months");
	}

	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==b1)
		{
			reset();
		}
		if(ae.getSource()==b2)
		{
			try
			{
				if(t1.getText().length()==0)
				{
					JOptionPane.showMessageDialog(fr,"Student ID can't leave blank");
				}
				else if(t2.getText().trim().length()==0)
				{
					JOptionPane.showMessageDialog(fr,"Student's Name can't leave blank");
					t2.requestFocus();		
				}
				else if(t3.getText().trim().length()==0)
				{
					JOptionPane.showMessageDialog(fr,"Father's Name can't leave blank");
					t3.requestFocus();		
				}
				else if(t4.getText().trim().length()==0)
				{
					JOptionPane.showMessageDialog(fr,"Address can't leave blank");
					t4.requestFocus();		
				}
				else if(t5.getText().trim().length()==0)
				{
					JOptionPane.showMessageDialog(fr,"Mobile can't leave blank");
					t5.requestFocus();		
				}
				else if(t6.getText().trim().length()==0)
				{
					JOptionPane.showMessageDialog(fr,"Email-ID can't leave blank");
					t6.requestFocus();		
				}
				else if(t7.getText().trim().length()==0)
				{
					JOptionPane.showMessageDialog(fr,"College Name can't leave blank");
					t7.requestFocus();		
				}
				else if(p==0)		
				{
					JOptionPane.showMessageDialog(fr,"Please Browse Photo");
				}
				else
				{
					if(t5.getText().length()!=10)
					{
						JOptionPane.showMessageDialog(fr,"Please Eneter 10 Digit Mobile Number");
						t5.requestFocus();
					}
					else if(t6.getText().length()!=0)
					{
						String s1 = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
						String s2 = t6.getText();	
						Pattern pt = Pattern.compile(s1);
						Matcher mt = pt.matcher(s2);
						if(mt.matches()!=true)
						{
							JOptionPane.showMessageDialog(fr,"Invalid Email-ID");
							t6.requestFocus();
						}
						else
						{
							Connection con = Dao.createconnection();
							PreparedStatement ps = con.prepareStatement("insert into sms_record values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
							ps.setString(1,t1.getText());
							ps.setString(2,t2.getText());
							ps.setString(3,t3.getText());
							String gen = "";
							if(r1.isSelected()==true)	
							{
								gen = "Male";
							}
							else
							{
								gen = "Female";
							}
							ps.setString(4,gen);
							String dob = c1.getSelectedItem().toString()+"/"+c2.getSelectedItem().toString()+"/"+c3.getSelectedItem().toString();
							ps.setString(5,dob);
							ps.setString(6,t4.getText());
							ps.setString(7,t5.getText());
							ps.setString(8,t6.getText());
							ps.setString(9,c4.getSelectedItem().toString());
							ps.setString(10,c5.getSelectedItem().toString());
							ps.setString(11,t7.getText());
							ps.setString(12,c6.getSelectedItem().toString());
							ps.setString(13,c7.getSelectedItem().toString());
							ps.setString(14,t8.getText());
							ps.setString(15,t9.getText());
							ps.setDouble(16,dis);
							ps.setString(17,t10.getText());
							FileInputStream fis = new FileInputStream(path);
							ps.setBinaryStream(18,fis,fis.available());
							int z = ps.executeUpdate();
							if(z>0)
							{
								JOptionPane.showMessageDialog(fr,"Record Saved Successfully......");
							}
							con.close();
						}
					}
				}
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(fr,"Duplicate Record Found");
			}
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
		t8.setText("5000");
		t9.setText("3 Months");
		t10.setText("5000");
		r1.setSelected(true);
		c1.setSelectedIndex(0);	
		c2.setSelectedIndex(0);	
		c3.setSelectedIndex(0);	
		c4.setSelectedIndex(0);	
		c5.setSelectedIndex(0);	
		c6.setSelectedIndex(0);	
		c7.setSelectedIndex(0);	
		chk.setSelected(false);
		lphoto.setIcon(new ImageIcon("images/photo.png"));
	}
}
