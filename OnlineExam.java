import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
public class OnlineExam extends WindowAdapter implements ActionListener, ItemListener
{
	JFrame f,f1;
	JDialog f2;
	JPanel p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11,p12;
	JButton b1,b2,b3,b4,b5,b6;
	JLabel l1,l2,l4,l6,l8,l10,l11,l12,l14,l16,l18,l20,l21;
	CheckboxGroup jcg;
	Checkbox jcb1,jcb2,jcb3,jcb4,jcb5,jcb6,jcb7,jcb8;
	CardLayout cl;
	int count=0,count1,i,temp2;
	String str,str1,temp,temp1,str7;
	Vector v1,v2;
	JProgressBar pb;
	Boolean reset=false;
	ResultSet rs;
	examdatabase ed=new examdatabase();
	public OnlineExam()
	{
		f=new JFrame("Online Exam");
		f.setSize(400,400);
		v1=new Vector();
		v2=new Vector();
		b1=new JButton("Start");
		b2=new JButton("Exit");
		b1.addActionListener(this);
		b2.addActionListener(this);
		p1=new JPanel(new GridBagLayout());
		GridBagConstraints gbc=new GridBagConstraints();
     	gbc.gridx=0; gbc.gridy=0;
     	gbc.gridwidth=1;  gbc.gridheight=1;
     	Insets i=new Insets(2,2,2,10);
     	gbc.insets=i;
     	p1.add(b1,gbc);
     	gbc.gridx=1; gbc.gridy=0; 
     	gbc.gridwidth=1;
     	Insets j=new Insets(2,2,2,10);
     	gbc.insets=j;
		p1.add(b2,gbc);
		f.add(p1);
		f.setVisible(true);
	}
	public void actionPerformed(ActionEvent e)
	{
		String str=e.getActionCommand();
		if(str.equals("Start"))
		{
			startexam();
		}
		if(str.equals("Exit"))
		{
			System.exit(1);
		}
		if(str.equals("Next"))
		{
			System.out.println("next was pressed");
			if(count<=11)
			{
				if(v1.size()>count1)
				{
				System.out.println("Redisplay was clicked");
				redisplay();
				}
				else
			{
				System.out.println("display was clicked");
				display();

			}
			}
			   if(count==10)
			{
				b4.setVisible(false);
				b5=new JButton("Finish");
				b5.addActionListener(this);
				p5.add(b5);
			}
			
	 	}
	 	if(str.equals("Back"))
		{
			System.out.println("back was pressed");
			back();
			if(v1.size()==10)
			{
				b5.setVisible(false);
				b4.setVisible(true);
			}
		}
		
		if(str.equals("Finish"))
		{
			System.out.println("finish was pressed");
			if(v2.size()<v1.size())
	 			{
	 				v2.add(count-1,null);
	 			}
			int temp3=0;
			System.out.println("Result");
			for(int i=0;i<10;i++)
			{
				try{
				int d20=(int)v1.get(i);
				String str8=(String)v2.get(i);
				rs=ed.select("questions",d20);
				rs.next();
				if(rs.getString("CorrectAns").equals(str8))
				{
					temp3++;
				}
			}
				catch(Exception e7)
				{
					System.out.println("ERROR");
				}
				}
				f2=new JDialog(f1);
			f2.setSize(300,300);
			f2.setLayout(new BorderLayout());
			p11=new JPanel(new GridBagLayout());
			GridBagConstraints gbc=new GridBagConstraints();
			pb=new JProgressBar(0,10);
	 gbc.gridx=0; gbc.gridy=0;
     gbc.gridwidth=1;  gbc.gridheight=1;
     Insets i=new Insets(2,2,2,10);
     gbc.insets=i;
  	 p11.add(pb,gbc);
  	 pb.setValue(temp3);
	temp3=temp3*10;
	l21=new JLabel("your progress is "+temp3+"%");
  	 gbc.gridx=0; gbc.gridy=1;
     gbc.gridwidth=1;  gbc.gridheight=1;
     Insets k=new Insets(2,2,2,10);
     gbc.insets=k;
  	 p11.add(l21,gbc);
			f2.add(p11,"Center");
			b6=new JButton("Preview");
			b6.addActionListener(this);
			p12=new JPanel();
			p12.add(b6);
			f2.add(p12,"South");
			f2.addWindowListener(this);
			f2.setModal(true);
			f2.setVisible(true);
		}
		if(str.equals("Preview"))
		{
			System.out.println("preview was pressed");
			f2.dispose();
			preview();
			
		}
	}
	public void windowClosing(WindowEvent w)
	{
		preview();
		f2.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
	}
	public void itemStateChanged(ItemEvent ie)
	{
		int d8=(int)v1.get(count1-1);
		try{
			rs=ed.select("questions",d8);
			rs.next();
	 		if(d8==rs.getInt("QuestionNo"))
	 		{
	 			str=rs.getString("type");
	 			if(str.equals("s"))
	 			{
	 				String str5=(jcg.getSelectedCheckbox()).getLabel();
	 				if(v2.size()>=v1.size())
	 				{
	 					v2.set(count-1,str5);
	 				}
	 				else{
	 					v2.add(count-1,str5);
	 				}
	 				}
	 			else
	 			{
	 				str7="";
	 				if(jcb5.getState()==true)
	 				{
	 					str7=jcb5.getLabel();
	 				}
	 				if(jcb6.getState()==true)
	 				{
	 					str7=str7+jcb6.getLabel();
	 				}
	 				if(jcb7.getState()==true)
	 				{
	 					str7=str7+jcb7.getLabel();
	 				}
	 				if(jcb8.getState()==true)
	 				{
	 					str7=str7+jcb8.getLabel();
	 				}
	 				if(v2.size()>=v1.size())
	 				{
	 					v2.set(count-1,str7);
	 				}
	 				else
	 				{
	 					v2.add(count-1,str7);
	 				}
	 			}
	 	}	
		}
		catch(Exception e4)
		{
			System.out.println("ERROR");
		}
	}
	public void preview()
	{
		reset=true;
						jcb1.setEnabled(false);
	 					jcb2.setEnabled(false);
	 					jcb3.setEnabled(false);
	 					jcb4.setEnabled(false);
	 					jcb5.setEnabled(false);
	 					jcb6.setEnabled(false);
	 					jcb7.setEnabled(false);
	 					jcb8.setEnabled(false);
			f1.toFront();
				int d11=(int)v1.get(count1-1);
			try{
	 	rs=ed.select("questions",d11);
	 	System.out.println("database is called");
	 	rs.next();
	 		if(d11==rs.getInt("QuestionNo"))
	 		{
	 			str=rs.getString("type");
	 			if(str.equals("s"))
	 			{
	 				cl.first(p9);
	 				b5.setVisible(false);
	 				SetSingleQuestion();
	 				SetSingleColouredAnswer();
	 			}
	 			else
	 			{
	 				cl.last(p9);
	 				b5.setVisible(false);
	 				SetMultipleQuestion();
	 				SetMultipleColouredAnswer();
	 			}
	 		}
	 	
		}
		catch(Exception e10)
		{
			System.out.println("ERROR");
		}
			
		}
		public void SetSingleQuestion() throws SQLException,ClassNotFoundException
		{
				l1.setText(""+count);
	 			System.out.println("Question no is"+count);
	 			l2.setText(rs.getString("Question"));
	 			l4.setText(rs.getString("A"));
	 			l6.setText(rs.getString("B"));
	 			l8.setText(rs.getString("C"));
	 			l10.setText(rs.getString("D"));
		}
		public void SetMultipleQuestion() throws SQLException,ClassNotFoundException
		{
			l11.setText(""+count);
	 				System.out.println("Question no is"+count);
	 			l12.setText(rs.getString("Question"));
	 			l14.setText(rs.getString("A"));
	 			l16.setText(rs.getString("B"));
	 			l18.setText(rs.getString("C"));
	 			l20.setText(rs.getString("D"));
		}
		public void SetSingleColouredAnswer() throws SQLException,ClassNotFoundException
		{
			temp=(String)v2.get(count1-1);
	 			temp1=rs.getString("CorrectAns");
	 			if(temp==null)
	 			{
	 				if(temp1.equals("A"))
	 				{
	 					
	 					jcb1.setBackground(Color.green);
	 				}
	 				if(temp1.equals("B"))
	 				{
	 					jcb2.setBackground(Color.green);
	 				}
	 				if(temp1.equals("C"))
	 				{
	 					jcb3.setBackground(Color.green);
	 				}
	 				if(temp1.equals("D"))
	 				{
	 					jcb4.setBackground(Color.green);
	 				}
	 			}
	 				else
	 				{	 					
	 			if(temp.equals(temp1))
	 			{
	 				if(temp.equals("A"))
	 				{
	 					
	 					jcb1.setBackground(Color.green);
	 				}
	 				if(temp.equals("B"))
	 				{
	 					jcb2.setBackground(Color.green);
	 				}
	 				if(temp.equals("C"))
	 				{
	 					jcb3.setBackground(Color.green);
	 				}
	 				if(temp.equals("D"))
	 				{
	 					jcb4.setBackground(Color.green);
	 				}
	 			}
	 			else
	 			{
	 				if(temp.equals("A"))
	 				{
	 					
	 					jcb1.setBackground(Color.red);
	 				}
	 				if(temp.equals("B"))
	 				{
	 					jcb2.setBackground(Color.red);
	 				}
	 				if(temp.equals("C"))
	 				{
	 					jcb3.setBackground(Color.red);
	 				}
	 				if(temp.equals("D"))
	 				{
	 					jcb4.setBackground(Color.red);
	 				}
	 				if(temp1.equals("A"))
	 				{
	 					
	 					jcb1.setBackground(Color.green);
	 				}
	 				if(temp1.equals("B"))
	 				{
	 					jcb2.setBackground(Color.green);
	 				}
	 				if(temp1.equals("C"))
	 				{
	 					jcb3.setBackground(Color.green);
	 				}
	 				if(temp1.equals("D"))
	 				{
	 					jcb4.setBackground(Color.green);
	 				}
	 				
	 			}
	 			}
		}
		public void SetMultipleColouredAnswer() throws SQLException,ClassNotFoundException
		{
			temp=(String)v2.get(count1-1);
	 			temp1=rs.getString("CorrectAns");
	 			if(temp==null)
	 			{
	 				char arr4[];
	 				arr4=temp1.toCharArray();
	 				for(int y=0;y<arr4.length;y++)
	 				{
	 					if(arr4[y]=='A')
	 					{
	 						jcb5.setBackground(Color.green);
	 					}
	 					if(arr4[y]=='B')
	 					{
	 						jcb6.setBackground(Color.green);
	 					}
	 					if(arr4[y]=='C')
	 					{
	 						jcb7.setBackground(Color.green);
	 					}
	 					if(arr4[y]=='D')
	 					{
	 						jcb8.setBackground(Color.green);
	 					}
	 				}
	 			}
	 			else
	 			{
	 			if(temp.equals(temp1))
	 			{
	 				char arr1[];
	 				arr1=temp.toCharArray();
	 				for(int y=0;y<arr1.length;y++)
	 				{
	 					if(arr1[y]=='A')
	 					{
	 						jcb5.setBackground(Color.green);
	 					}
	 					if(arr1[y]=='B')
	 					{
	 						jcb6.setBackground(Color.green);
	 					}
	 					if(arr1[y]=='C')
	 					{
	 						jcb7.setBackground(Color.green);
	 					}
	 					if(arr1[y]=='D')
	 					{
	 						jcb8.setBackground(Color.green);
	 					}
	 				}
	 			}
	 			else
	 			{
	 				char arr2[];
	 				arr2=temp.toCharArray();
	 				for(int y=0;y<arr2.length;y++)
	 				{
	 					if(arr2[y]=='A')
	 					{
	 						jcb5.setBackground(Color.red);
	 					}
	 					if(arr2[y]=='B')
	 					{
	 						jcb6.setBackground(Color.red);
	 					}
	 					if(arr2[y]=='C')
	 					{
	 						jcb7.setBackground(Color.red);
	 					}
	 					if(arr2[y]=='D')
	 					{
	 						jcb8.setBackground(Color.red);
	 					}
	 				}
	 				char arr3[];
	 				arr3=temp1.toCharArray();
	 				for(int y=0;y<arr3.length;y++)
	 				{
	 					if(arr3[y]=='A')
	 					{
	 						jcb5.setBackground(Color.green);
	 					}
	 					if(arr3[y]=='B')
	 					{
	 						jcb6.setBackground(Color.green);
	 					}
	 					if(arr3[y]=='C')
	 					{
	 						jcb7.setBackground(Color.green);
	 					}
	 					if(arr3[y]=='D')
	 					{
	 						jcb8.setBackground(Color.green);
	 					}
	 				}
	 				
	 			}}
		}
public void SetStateMultipleQuestion()
		{
			jcb5.setState(false);
	 			jcb6.setState(false);
	 			jcb7.setState(false);
	 			jcb8.setState(false);
		}
		public void SetSingleBackgroungColor()
		{
			jcb1.setBackground(Color.white);
	 				jcb2.setBackground(Color.white);
	 				jcb3.setBackground(Color.white);
	 				jcb4.setBackground(Color.white);

		}
		public void SetMultipleBackgroungColor()
		{
				 	jcb5.setBackground(Color.white);
	 				jcb6.setBackground(Color.white);
	 				jcb7.setBackground(Color.white);
	 				jcb8.setBackground(Color.white);
		}
		public void SetSingleSelected()
		{
			String str10=(String)v2.get(count1-1);
	 			if(str10==null)
	 			{
	 				jcg.setSelectedCheckbox(null);
	 			}
	 			else
	 			{
	 			if(str10=="A")
	 			{
	 				jcg.setSelectedCheckbox(jcb1);
	 			}
	 			if(str10=="B")
	 			{
	 				jcg.setSelectedCheckbox(jcb2);
	 			}
	 			if(str10=="C")
	 			{
	 				jcg.setSelectedCheckbox(jcb3);
	 			}
	 			if(str10=="D")
	 			{
	 				jcg.setSelectedCheckbox(jcb4);
	 			}}
		}
		public void SetMultipleSelected()
		{
			String str12=(String)v2.get(count1-1);
	 			if(str12==null)
	 			{
	 				SetStateMultipleQuestion();
	 			}
	 			else
	 			{
	 			char arr[];
	 			arr=str12.toCharArray();
	 			for(int k=0;k<arr.length;k++)
	 			{
	 				if(arr[k]=='A')
	 				{
	 					jcb5.setState(true);
	 				}
	 				if(arr[k]=='B')
	 				{
	 					jcb6.setState(true);
	 				}
	 				if(arr[k]=='C')
	 				{
	 					jcb7.setState(true);
	 				}
	 				if(arr[k]=='D')
	 				{
	 					jcb8.setState(true);
	 				}
	 			}
	 			}
		}
	public void startexam()
	{
		f1=new JFrame("Test");
		f1.setSize(300,300);
		cl=new CardLayout();
		f1.setLayout(new BorderLayout());
		p2=new JPanel(new BorderLayout());
		p3=new JPanel(new FlowLayout(FlowLayout.LEFT));
		l1=new JLabel();
     p3.add(l1);
     l2=new JLabel();
     p3.add(l2);
     p2.add(p3,"North"); 
  	 p4=new JPanel(new GridBagLayout());
	 GridBagConstraints gbc=new GridBagConstraints();
  	 jcg=new CheckboxGroup();
  	 jcb1=new Checkbox("A",jcg,false);
  	 jcb1.addItemListener(this);
	 gbc.gridx=0; gbc.gridy=0;
     gbc.gridwidth=1;  gbc.gridheight=1;
     Insets i=new Insets(2,2,2,10);
     gbc.insets=i;
  	 p4.add(jcb1,gbc);
  	 l4=new JLabel();
  	 gbc.gridx=1; gbc.gridy=0;
     gbc.gridwidth=1;  gbc.gridheight=1;
     Insets k=new Insets(2,2,2,10);
     gbc.insets=k;
  	 p4.add(l4,gbc);
  	 jcb2=new Checkbox("B",jcg,false);
	 gbc.gridx=0; gbc.gridy=1;
     gbc.gridwidth=1;  gbc.gridheight=1;
     Insets m=new Insets(2,2,2,10);
     gbc.insets=m;
  	 p4.add(jcb2,gbc);
  	 l6=new JLabel();
  	 gbc.gridx=1; gbc.gridy=1;
     gbc.gridwidth=1;  gbc.gridheight=1;
     Insets o=new Insets(2,2,2,10);
     gbc.insets=o;
  	 p4.add(l6,gbc);
  	 jcb3=new Checkbox("C",jcg,false);
	 gbc.gridx=0; gbc.gridy=2;
     gbc.gridwidth=1;  gbc.gridheight=1;
     Insets p=new Insets(2,2,2,10);
     gbc.insets=p;
  	 p4.add(jcb3,gbc);
  	 l8=new JLabel();
  	 gbc.gridx=1; gbc.gridy=2;
     gbc.gridwidth=1;  gbc.gridheight=1;
     Insets r=new Insets(2,2,2,10);
     gbc.insets=r;
  	 p4.add(l8,gbc);
  	 jcb4=new Checkbox("D",jcg,false);
	 gbc.gridx=0; gbc.gridy=3;
     gbc.gridwidth=1;  gbc.gridheight=1;
     Insets s=new Insets(2,2,2,10);
     gbc.insets=s;
  	 p4.add(jcb4,gbc);
  	 l10=new JLabel();
  	 gbc.gridx=1; gbc.gridy=3;
     gbc.gridwidth=1;  gbc.gridheight=1;
     Insets u=new Insets(2,2,2,10);
     gbc.insets=u;
  	 p4.add(l10,gbc);
  	 p2.add(p4,"West");
     p5=new JPanel();
     b3=new JButton("Back");
     b4=new JButton("Next");
     b3.addActionListener(this);
     b4.addActionListener(this);
     p5.add(b3);
     p5.add(b4);
     f1.add(p5,"South");
     p6=new JPanel(new BorderLayout());
	p7=new JPanel(new FlowLayout(FlowLayout.LEFT));
	l11=new JLabel();
     p7.add(l11);
     l12=new JLabel();
     p7.add(l12);
     p6.add(p7,"North");
     p9=new JPanel(cl);
     p8=new JPanel(new GridBagLayout());
	 GridBagConstraints gbc1=new GridBagConstraints();
  	 jcb5=new Checkbox("A");
	 gbc1.gridx=0; gbc1.gridy=0;
     gbc1.gridwidth=1;  gbc1.gridheight=1;
     Insets i1=new Insets(2,2,2,10);
     gbc1.insets=i1;
  	 p8.add(jcb5,gbc1);
  	 l14=new JLabel();
  	 gbc1.gridx=1; gbc1.gridy=0;
     gbc1.gridwidth=1;  gbc1.gridheight=1;
     Insets k1=new Insets(2,2,2,10);
     gbc1.insets=k1;
  	 p8.add(l14,gbc1);
  	 jcb6=new Checkbox("B");
	 gbc1.gridx=0; gbc1.gridy=1;
    gbc1.gridwidth=1;  gbc1.gridheight=1;
     Insets m1=new Insets(2,2,2,10);
     gbc1.insets=m1;
  	 p8.add(jcb6,gbc1);
  	 l16=new JLabel();
  	 gbc1.gridx=1; gbc1.gridy=1;
     gbc1.gridwidth=1;  gbc1.gridheight=1;
     Insets o1=new Insets(2,2,2,10);
     gbc1.insets=o1;
  	 p8.add(l16,gbc1);
  	 jcb7=new Checkbox("C");
	 gbc1.gridx=0; gbc1.gridy=2;
     gbc1.gridwidth=1;  gbc1.gridheight=1;
     Insets p1=new Insets(2,2,2,10);
     gbc1.insets=p1;
  	 p8.add(jcb7,gbc1);
  	 l18=new JLabel();
  	 gbc1.gridx=1; gbc1.gridy=2;
     gbc1.gridwidth=1;  gbc1.gridheight=1;
     Insets re=new Insets(2,2,2,10);
     gbc1.insets=re;
  	 p8.add(l18,gbc1);
  	 jcb8=new Checkbox("D");
	 gbc1.gridx=0; gbc1.gridy=3;
     gbc1.gridwidth=1;  gbc1.gridheight=1;
     Insets s1=new Insets(2,2,2,10);
     gbc1.insets=s1;
  	 p8.add(jcb8,gbc1);
  	 l20=new JLabel();
  	 gbc1.gridx=1; gbc1.gridy=3;
     gbc1.gridwidth=1;  gbc1.gridheight=1;
     Insets u1=new Insets(2,2,2,10);
     gbc.insets=u1;
  	 p8.add(l20,gbc1);
  	 p6.add(p8,"West");
  	 jcb2.addItemListener(this);
  	 jcb3.addItemListener(this);
  	 jcb4.addItemListener(this);
  	 jcb5.addItemListener(this);
  	 jcb6.addItemListener(this);
  	 jcb7.addItemListener(this);
  	 jcb8.addItemListener(this);
   	 p9.add(p2);
	 p9.add(p6);
	 f1.add(p9,"Center");
  	 double x=Math.random()*20;
  	 int y=(int)(Math.ceil(x));
  	 v1.add(y); 
  	 	count1=v1.size();
  	 	System.out.println(y);
  	 	b3.setVisible(false);
	 				count=count+1;
	 	try{
	 	rs=ed.select("questions",y);  //database is called
	 		rs.next();
	 		if(y==rs.getInt("QuestionNo"))
	 		{
	 			str=rs.getString("type");
	 			if(str.equals("s"))
	 			{
	 				cl.first(p9);
	 				SetSingleQuestion();
	 		 			}
	 			else
	 			{
					cl.last(p9);
					SetMultipleQuestion();
	 			}
	 		}
	 	
	 }
	 catch(Exception e1)
	 {
	 	System.out.println("ERROR");
	 }
		f1.setVisible(true);
	}
	public void display() 
	{
		double x=Math.random()*20;
  	 int y=(int)(Math.ceil(x));
  	 int y2=y;
  	 int e=0;
  		while(e<v1.size())
  		{
  			int g=(int)v1.get(e);
  	 		if(y2==g)
  	 	{
  	 		double x1=Math.random()*20;
  	 		int y1=(int)(Math.ceil(x1));
  	 		y2=y1;
  	 		e=0;
  	 	}
  	 	else
  	 	{
  	 		e++;
  	 	}
  	 }
  	 if(v2.size()<v1.size())
	 			{
	 				v2.add(count-1,null);
	 			}
  	 v1.add(y2);
  	 count1=v1.size();
  	 b3.setVisible(true);
	 count=count+1;
	 	try{
	 	rs=ed.select("questions",y2);
	 	rs.next();
	 	if(y2==rs.getInt("QuestionNo"))
	 		{
	 			if(rs.getString("type").equals("s"))
	 			{
					cl.first(p9);
	 			SetSingleQuestion();
	 			jcg.setSelectedCheckbox(null);
	 			}
	 			else
	 			{
	 				cl.last(p9);
	 				SetMultipleQuestion();
	 				SetStateMultipleQuestion();
	 			}	
	 		}
	 	
	 }
	 catch(Exception e1)
	 {
	 	System.out.println("ERROR");
	 }
	}
	
	public void back()
	{
		int d5=(int)v1.get(count1-2);
			count1=count1-1;
			count=count-1;
	 				if(count==1)
	 				{
	 					b3.setVisible(false);
	 				}
	 				else
	 				{
	 					b3.setVisible(true);
	 				}
	 				if(v2.size()<v1.size())
	 			{
	 				v2.add(count,null);
	 			}
			try{
	 	rs=ed.select("questions",d5);
	 	System.out.println("database is called");
	 	rs.next();
	 		if(d5==rs.getInt("QuestionNo"))
	 		{
	 			str=rs.getString("type");
	 			if(str.equals("s"))
	 			{
	 				cl.first(p9);
	 				SetSingleQuestion();
	 			jcg.setSelectedCheckbox(null);
	 			SetSingleSelected();
	 			if(reset==true)
	 			{
	 				SetSingleBackgroungColor();
	 				SetSingleColouredAnswer();
	 				
	 			}
	 			}
	 			else
	 			{
	 				cl.last(p9);
	 				SetMultipleQuestion();
	 				SetStateMultipleQuestion();	
	 			SetMultipleSelected();
	 			if(reset==true)
	 			{
	 				SetMultipleBackgroungColor();
	 				SetMultipleColouredAnswer();
	 			}
	 			}
	 		}
	 	
	 }
	 catch(Exception e1)
	 {
	 	System.out.println("ERROR");
	 }
	}
	public void redisplay()
	{
		int d7=(int)v1.get(count1);
		count1=count1+1;
		count=count+1;
	 				if(count>=2)
	 				{
	 					b3.setVisible(true);
	 				}
	 				if(v2.size()<v1.size())
	 			{
	 				v2.add(count,null);
	 			}
		try{
	 	rs=ed.select("questions",d7);
	 	System.out.println("database is called");
	 	rs.next();
	 		if(d7==rs.getInt("QuestionNo"))
	 		{
	 			str=rs.getString("type");
	 			if(str.equals("s"))
	 			{
	 				cl.first(p9);
	 				SetSingleQuestion();
	 			jcg.setSelectedCheckbox(null);
	 			SetSingleSelected();
	 			if(reset==true)
	 			{
	 				SetSingleBackgroungColor();
	 				b5.setVisible(false);
	 				SetSingleColouredAnswer();
	 			}
	 			
	 			}
	 			else
	 			{
	 				cl.last(p9);
	 			SetMultipleQuestion();
	 			SetStateMultipleQuestion();
	 			SetMultipleSelected();
	 			if(reset==true)
	 			{
					SetMultipleBackgroungColor();
	 				SetMultipleColouredAnswer();	
	 			}
	 			}
	 		}
	 	
	 }
	 catch(Exception e1)
	 {
	 	System.out.println("ERROR");
	 }
	}	
	public static void main(String args[]) 
	{
		OnlineExam oe=new OnlineExam();
	}
}