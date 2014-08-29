import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.net.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.*;

class H_109816029 extends JFrame implements MouseListener,ActionListener,KeyListener
{
	static H_109816029 chat=new H_109816029();
	static TextField txf=new TextField(18);
	static TextArea  txa=new TextArea("",33,19,TextArea.SCROLLBARS_NONE);
	static String CHo="",CHi="";







    static Label lab=new Label();
	static JLabel play[] = new JLabel[32];
    static JLabel image;
    static Container con;
	static ServerSocket S1;
    static Socket Sa;
    static ObjectOutputStream out;
    static ObjectInputStream   in;
    static int I=0;
    static int chessPlayClick=0,User;
    static int X1,Y1,U,P;
    static int A[];
    static int B[];
    static int Z[];
    static int Man;
    static int C=0,C1=0,C2=0,C3=0,C4=0,C5=0;





	static MenuBar mb=new MenuBar();
	static Menu menu1=new Menu("選項");
	static MenuItem mi1=new MenuItem("對話視窗");
	static MenuItem mi2=new MenuItem("清除對話");
	static MenuItem mi3=new MenuItem("查看本機");
	static MenuItem mi4=new MenuItem("關於作者");
	static MenuItem mi5=new MenuItem("結束離開");



	public void keyPressed (KeyEvent e)
		{
			if(e.getKeyCode() == KeyEvent.VK_ENTER)
			{

               if(User==2)
               {


			     CHo=CHo+"Server:"+txf.getText()+"\n";

			     txa.setText(CHo);


                 try
                 {
                 out.writeObject("Server:"+txf.getText()+"\n");
                 System.out.print("成功發送訊息");

                 }
                 catch(Exception e1){}
                 txf.setText("");
               }
               else if(User==1)
               {

			     CHo=CHo+"Client:"+txf.getText()+"\n";

			     txa.setText(CHo);

               	 try
                 {
                 out.writeObject("Client:"+txf.getText()+"\n");
                 System.out.print("成功發送訊息");
                 }
                 catch(Exception e1){}
                 txf.setText("");
               }

            }

		}
	public void keyReleased(KeyEvent e){}
	public void keyTyped   (KeyEvent e){}

	public void mouseClicked (MouseEvent e){}
    public void mouseEntered (MouseEvent e){}
    public void mouseExited  (MouseEvent e){}
    public void mouseReleased(MouseEvent e){}
    public void mouseMoved   (MouseEvent e){}
    public void mousePressed (MouseEvent me)
    	{
           if(me.getSource().equals(image)&&chessPlayClick!=0)
            {

               System.out.println("點擊背景");
               if (chessPlayClick == 2 && play[Man].getName().charAt(1) == '2'&&User==2)//red
               {


                 for(int u=0;u<10;u++)
                 {
       	            if( -14<=(me.getY()+3)-B[u] && (me.getY()+3)-B[u]<=14)
       	            {
       	            	U=u;
       	            	C++;
       	            	System.out.println("點的夠準");
       	            }

                 }
                 for(int p=0;p<9;p++)
    	         {
    		        if( -14<=(me.getX()-28)-A[p] && (me.getX()-28)-A[p]<=14 )
    		        {
    		            P=p;
    		            C++;
    		            System.out.println("點的夠準");
    		        }
    	         }
                 go();
                 if(C==2&&C1==1)
                 {
                 play[Man].setBounds(A[P],B[U],55,55);
                 chessPlayClick=1;
                 try
                 {
                    out.writeObject("-1"+" "+A[P]+" "+B[U]+" "+chessPlayClick+" "+Man);
                    System.out.println("紅色移動");
                    lab.setText("Client黑色出棋");
                 }
                 catch(Exception ex){}
                 }
                 C  = 0;
                 C1 = 0;

               }
               else if (chessPlayClick == 1 && play[Man].getName().charAt(1) == '1'&&User==1)//black
               {
               	 for(int u=0;u<10;u++)
                 {
       	            if( -14<=(me.getY()+3)-B[u] && (me.getY()+3)-B[u]<=14)
       	            {
       	            	U=u;
       	            	C++;
       	            	System.out.println("點的夠準");
       	            }
                 }
                 for(int p=0;p<9;p++)
    	         {
    		        if( -14<=(me.getX()-28)-A[p] && (me.getX()-28)-A[p]<=14 )
    		        {
    		            P=p;
    		            C++;
    		            System.out.println("點的夠準");
    		        }
    	         }


                 go();
                 if(C==2&&C1==1)
                 {
               	 play[Man].setBounds(A[P],B[U],55,55);
                 chessPlayClick=2;
                 try
                 {
                    out.writeObject("-1"+" "+A[P]+" "+B[U]+" "+chessPlayClick+" "+Man);
                    System.out.println("黑色移動");
                    lab.setText("Server紅色出棋");
                 }
                 catch(Exception ex){}
                 }
                 C=0;
                 C1 = 0;
               }
            }
            else if (chessPlayClick!=0)
            {
            	for (int i=0;i<32;i++)
            	{
				    if (me.getSource().equals(play[i]))
				  	{
				  		for(int u=0;u<10;u++)
                        {
       	                  if( B[u]==play[i].getY())
       	                  {
       	            	    U=u;
       	                  }
                        }
                        for(int p=0;p<9;p++)
    	                {
    		              if( A[p]==play[i].getX() )
    		              {
    		                P=p;
    		              }
    	                }

				  		if(play[Man].getName().charAt(1)==play[i].getName().charAt(1))
				  		{
				  		    System.out.println("換取點擊棋子");
					    	Man=i;

				  		}
				  		else if(chessPlayClick==2&& play[Man].getName().charAt(1) == '2'&&play[i].getName().charAt(1)== '1'&&User==2)
				  		{

				  			Eat(i);
				  			if(C4==1)
				  			{
				  			System.out.println("紅吃黑");
				  			play[Man].setBounds(play[i].getX(),play[i].getY(),55,55);


				  			chessPlayClick=1;
				  			play[i].setVisible(false);
                            Z[i]=1;
				  			try
                            {
                               out.writeObject(play[i].getX()+" "+play[i].getY()+" "+chessPlayClick+" "+Man+" "+i);
                               lab.setText("Client黑色出棋");
                            }
                            catch(Exception ex){}
                            if(i==30)
                            {
                            	JOptionPane.showMessageDialog(null,"紅棋勝利","勝負",JOptionPane.PLAIN_MESSAGE);
                            	chessPlayClick=0;
                            }
				  			}
				  			C4=0;


				  		}
				  		else if(chessPlayClick==1&& play[Man].getName().charAt(1) == '1'&&play[i].getName().charAt(1)== '2'&&User==1)
				  		{

				  			Eat(i);
				  			if(C4==1)
				  			{
				  			System.out.println("黑吃紅");
				  			play[Man].setBounds(play[i].getX(),play[i].getY(),55,55);

				  			chessPlayClick=2;
				  			play[i].setVisible(false);
                            Z[i]=1;
				  			try
                            {
                               out.writeObject(play[i].getX()+" "+play[i].getY()+" "+chessPlayClick+" "+Man+" "+i);
                               lab.setText("Server紅色出棋");
                            }
                            catch(Exception ex){}
                            if(i==31)
                            {
                            	JOptionPane.showMessageDialog(null,"黑棋勝利","勝負",JOptionPane.PLAIN_MESSAGE);
                            	chessPlayClick=0;
                            }
				  			}

				  			C4=0;

				  		}
				  		else
				  		{
				  			System.out.println("點擊棋子");
					    	Man=i;




				  		}

					}
				}
            }

    	}

    public void actionPerformed(ActionEvent e)
    {
    	MenuItem mi=(MenuItem) e.getSource();
    	if(mi==mi1)
    	{
    		chat.setVisible(true);
        	chat.setResizable(false);
    	}
    	else if(mi==mi2)
    	{

    	  CHo="";
    	  txa.setText("");

    	}
        else if(mi==mi3)
        {
          try
          {
          Enumeration E=NetworkInterface.getNetworkInterfaces();
          while(E.hasMoreElements())
          {
              NetworkInterface netface=(NetworkInterface)E.nextElement();
              Enumeration e2=netface.getInetAddresses();
              InetAddress ip=null;
              while(e2.hasMoreElements())
              {
                 ip=(InetAddress)e2.nextElement();
              }
              JOptionPane.showMessageDialog(null,"本機實際 IP : "+ip.toString().replace("/",""),"INFO",JOptionPane.INFORMATION_MESSAGE);
          }

          }
          catch(Exception ex){}


        }
    	else if(mi==mi4)
    	{
    	        JOptionPane.showMessageDialog(null,"網路程式期末專題專用"+'\n'+"資二甲109816029林家熯"+'\n'+"資二甲109816022葉舒安","關於作者",JOptionPane.PLAIN_MESSAGE);
        }
    	else if(mi==mi5)
    	{
    			System.exit(0);
    	}
    }

    public static void main(String[] args) throws Exception
    {


    	String string="";
    	String serverIP,NAME;
    	int select;
        Z=new int[32];
    	A=new int[9];
        B=new int[10];
        for(int z=0;z<32;z++)
        {
       	    Z[z]=0;
        }
        for(int u=0;u<10;u++)
        {
       	    B[u]=56+57*u;
        }
        for(int p=0;p<9;p++)
    	{
    	    A[p]=24+57*p;
    	}



        select = JOptionPane.showOptionDialog(null,"選擇模式","請選擇",JOptionPane.YES_OPTION,JOptionPane.QUESTION_MESSAGE,null,new Object[]{"等待挑戰","選擇對手"},"等待接受挑戰");

        if(select==0)
    	{

          User=2;
          NAME = JOptionPane.showInputDialog(null,"請輸入名子","輸入名子",JOptionPane.QUESTION_MESSAGE);
          H_109816029 PaintA=new H_109816029();
          PaintA.setSize(563,700);
    	  PaintA.setTitle("使用者"+NAME+"  Server端 紅色");
    	  PaintA.setVisible(true);
    	  PaintA.setLocation(650,140);
    	  PaintA.setResizable(false);
    	  PaintA.addMouseListener(PaintA);
    	  PaintA.setLayout(null);
    	  mb.add(menu1);
    	  menu1.add(mi1);
    	  menu1.add(mi2);
    	  menu1.add(mi3);
    	  menu1.add(mi4);
    	  menu1.add(mi5);
    	  PaintA.setMenuBar(mb);
    	  mi1.addActionListener(PaintA);
    	  mi2.addActionListener(PaintA);
    	  mi3.addActionListener(PaintA);
    	  mi4.addActionListener(PaintA);
    	  mi5.addActionListener(PaintA);
    	  lab.setForeground(Color.black);
    	  lab.setSize(180,20);
    	  lab.setLocation(200,10);
    	  lab.setFont(new Font("Serief",Font.ITALIC+Font.BOLD,20));
    	  lab.setText("Server紅色出棋");
    	  PaintA.add(lab);

    	  PaintA.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    	  chat.setSize(200,600);
    	  chat.setTitle("Ser聊天視窗");
          chat.setLayout(new FlowLayout(FlowLayout.CENTER));
          txf.addKeyListener(chat);
          txa.setEditable(false);
          chat.add(txf);
          chat.add(txa);

    	  con = PaintA.getContentPane();
	      int i=0,k=0;
		  Icon In;
	      In = new ImageIcon("image\\黑車.GIF");
		  for (i=0,k=24;i<2;i++,k+=456)
		  {
			play[i] = new JLabel(In);
			con.add(play[i]);
			play[i].addMouseListener(PaintA);
			play[i].setBounds(k,56,55,55);
			play[i].setName("車1");
		  }

	      In = new ImageIcon("image\\黑馬.GIF");
		  for (i=4,k=81;i<6;i++,k+=342)
		  {
		    play[i] = new JLabel(In);
			con.add(play[i]);
			play[i].addMouseListener(PaintA);
			play[i].setBounds(k,56,55,55);
			play[i].setName("馬1");
		  }

		  In = new ImageIcon("image\\黑象.GIF");
		  for (i=8,k=138;i<10;i++,k+=228)
		  {
			play[i] = new JLabel(In);
			con.add(play[i]);
			play[i].addMouseListener(PaintA);
			play[i].setBounds(k,56,55,55);
			play[i].setName("象1");
		  }

		  In = new ImageIcon("image\\黑士.GIF");
		  for (i=12,k=195;i<14;i++,k+=114)
		  {
		    play[i] = new JLabel(In);
			con.add(play[i]);
			play[i].addMouseListener(PaintA);
			play[i].setBounds(k,56,55,55);
			play[i].setName("士1");
		  }

		  In = new ImageIcon("image\\黑卒.GIF");
		  for (i=16,k=24;i<21;i++,k+=114)
		  {
			play[i] = new JLabel(In);
			con.add(play[i]);
			play[i].addMouseListener(PaintA);
			play[i].setBounds(k,227,55,55);
			play[i].setName("卒1" + i);
		  }

		  In = new ImageIcon("image\\黑炮.GIF");
		  for (i=26,k=81;i<28;i++,k+=342)
		  {
		    play[i] = new JLabel(In);
			con.add(play[i]);
			play[i].addMouseListener(PaintA);
			play[i].setBounds(k,170,55,55);
			play[i].setName("炮1" + i);
		  }

		  In = new ImageIcon("image\\黑將.GIF");
		    play[30] = new JLabel(In);
		    con.add(play[30]);
			play[30].addMouseListener(PaintA);
		    play[30].setBounds(252,56,55,55);
		    play[30].setName("將1");

		  In = new ImageIcon("image\\紅車.GIF");
		  for (i=2,k=24;i<4;i++,k+=456)
		  {
			play[i] = new JLabel(In);
			con.add(play[i]);
			play[i].addMouseListener(PaintA);
			play[i].setBounds(k,569,55,55);
			play[i].setName("車2");
		  }

		  In = new ImageIcon("image\\紅馬.GIF");
		  for (i=6,k=81;i<8;i++,k+=342)
		  {
			play[i] = new JLabel(In);
			con.add(play[i]);
			play[i].addMouseListener(PaintA);
			play[i].setBounds(k,569,55,55);
			play[i].setName("馬2");
		  }

		  In = new ImageIcon("image\\紅象.GIF");
		  for (i=10,k=138;i<12;i++,k+=228)
		  {
			play[i] = new JLabel(In);
			con.add(play[i]);
			play[i].addMouseListener(PaintA);
			play[i].setBounds(k,569,55,55);
			play[i].setName("象2");
		  }

		  In = new ImageIcon("image\\紅士.GIF");
		  for (i=14,k=195;i<16;i++,k+=114)
		  {
			play[i] = new JLabel(In);
			con.add(play[i]);
			play[i].addMouseListener(PaintA);
			play[i].setBounds(k,569,55,55);
			play[i].setName("士2");
		  }

		  In = new ImageIcon("image\\紅卒.GIF");
		  for (i=21,k=24;i<26;i++,k+=114)
		  {
			play[i] = new JLabel(In);
			con.add(play[i]);
			play[i].addMouseListener(PaintA);
			play[i].setBounds(k,398,55,55);
			play[i].setName("卒2" + i);
		  }

		  In = new ImageIcon("image\\紅炮.GIF");
		  for (i=28,k=81;i<30;i++,k+=342)
		  {
			play[i] = new JLabel(In);
			con.add(play[i]);
			play[i].addMouseListener(PaintA);
			play[i].setBounds(k,455,55,55);
			play[i].setName("炮2" + i);
		  }

		  In = new ImageIcon("image\\紅將.GIF");
			play[31] = new JLabel(In);
			con.add(play[31]);
			play[31].addMouseListener(PaintA);
		    play[31].setBounds(252,569,55,55);
		    play[31].setName("帥2");

    	  con.add(image = new JLabel(new ImageIcon("image\\Main.GIF")));
		  image.setBounds(0,30,558,620);
    	  image.addMouseListener(PaintA);





    	  S1  = new ServerSocket(6060);
    	  Sa  = S1.accept();
    	  out = new ObjectOutputStream (Sa.getOutputStream());
          in  = new ObjectInputStream  (Sa.getInputStream());

          try
          {
              out.writeObject(NAME);
          }
          catch(Exception ex){}

          string=(String)in.readObject();

          JOptionPane.showMessageDialog(null,"您已與"+string+"連線對戰","連線成功",JOptionPane.PLAIN_MESSAGE);
          chessPlayClick=2;
          while(I==0)
          {
          	  if(chessPlayClick==1)
          	  {lab.setText("Client黑色出棋");}
          	  else if(chessPlayClick==2)
          	  {lab.setText("Server紅色出棋");}
          	  string=(String)in.readObject();





              if(string.indexOf("Client:")!=-1)
              {
                   CHi=string;
              	   CHo=CHo+CHi;
              	   chat.setVisible(true);
			       txa.setText(CHo);
              }
              else if (string.indexOf("-1")!=-1)
              {
              	     int sx,sy;
              	     String str[];
              	     str=string.split(" ");
                     sx=Integer.parseInt(str[1]);
                     sy=Integer.parseInt(str[2]);
                     chessPlayClick=Integer.parseInt(str[3]);
                     Man=Integer.parseInt(str[4]);
                     play[Man].setBounds(sx,sy,55,55);

              }
              else
              {
              	     int sx,sy,eat;
              	     String str[];
              	     str=string.split(" ");
                     sx=Integer.parseInt(str[0]);
                     sy=Integer.parseInt(str[1]);
                     chessPlayClick=Integer.parseInt(str[2]);
                     Man=Integer.parseInt(str[3]);
                     play[Man].setBounds(sx,sy,55,55);
                     eat=Integer.parseInt(str[4]);
                     play[eat].setVisible(false);
                     Z[eat]=1;
                     if(eat==31)
                     {
                       JOptionPane.showMessageDialog(null,"黑棋勝利","勝負",JOptionPane.PLAIN_MESSAGE);
                       chessPlayClick=0;
                     }



              }


          }

          out.close();
          in.close();
          Sa.close();
    	  }


    	else
       	{

       	  User=1;
    	  serverIP = JOptionPane.showInputDialog(null,"輸入對方的IP位址","選擇對手",JOptionPane.QUESTION_MESSAGE);
    	  NAME = JOptionPane.showInputDialog(null,"請輸使用者姓名","輸入姓名",JOptionPane.QUESTION_MESSAGE);
    	  H_109816029 PaintB=new H_109816029();
    	  PaintB.setSize(563,700);
    	  PaintB.setTitle("使用者"+NAME+"  Client端 黑色");
    	  PaintB.setVisible(true);
    	  PaintB.setLocation(650,140);
    	  PaintB.setResizable(false);
    	  PaintB.addMouseListener(PaintB);

    	  PaintB.setLayout(null);
    	  mb.add(menu1);
    	  menu1.add(mi1);
    	  menu1.add(mi2);
    	  menu1.add(mi3);
    	  menu1.add(mi4);
    	  menu1.add(mi5);
    	  PaintB.setMenuBar(mb);
    	  mi1.addActionListener(PaintB);
    	  mi2.addActionListener(PaintB);
    	  mi3.addActionListener(PaintB);
    	  mi4.addActionListener(PaintB);
    	  mi5.addActionListener(PaintB);
    	  lab.setForeground(Color.black);
    	  lab.setSize(180,20);
    	  lab.setLocation(200,10);
    	  lab.setFont(new Font("Serief",Font.ITALIC+Font.BOLD,20));
    	  lab.setText("Server紅色出棋");
    	  PaintB.add(lab);
    	  PaintB.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    	  chat.setSize(200,600);
    	  chat.setTitle("Cli聊天視窗");
          chat.setLayout(new FlowLayout(FlowLayout.CENTER));
          txf.addKeyListener(chat);
          txa.setEditable(false);
          chat.add(txf);
          chat.add(txa);


    	  con = PaintB.getContentPane();
	      int i,k;
		  Icon In;

	      In = new ImageIcon("image\\黑車.GIF");
		  for (i=0,k=24;i<2;i++,k+=456)
		  {
			play[i] = new JLabel(In);
			con.add(play[i]);
			play[i].addMouseListener(PaintB);
			play[i].setBounds(k,56,55,55);
			play[i].setName("車1");
		  }

	      In = new ImageIcon("image\\黑馬.GIF");
		  for (i=4,k=81;i<6;i++,k+=342)
		  {
		    play[i] = new JLabel(In);
			con.add(play[i]);
			play[i].addMouseListener(PaintB);
			play[i].setBounds(k,56,55,55);
			play[i].setName("馬1");
		  }

		  In = new ImageIcon("image\\黑象.GIF");
		  for (i=8,k=138;i<10;i++,k+=228)
		  {
			play[i] = new JLabel(In);
			con.add(play[i]);
			play[i].addMouseListener(PaintB);
			play[i].setBounds(k,56,55,55);
			play[i].setName("象1");
		  }

		  In = new ImageIcon("image\\黑士.GIF");
		  for (i=12,k=195;i<14;i++,k+=114)
		  {
		    play[i] = new JLabel(In);
			con.add(play[i]);
			play[i].addMouseListener(PaintB);
			play[i].setBounds(k,56,55,55);
			play[i].setName("士1");
		  }

		  In = new ImageIcon("image\\黑卒.GIF");
		  for (i=16,k=24;i<21;i++,k+=114)
		  {
			play[i] = new JLabel(In);
			con.add(play[i]);
			play[i].addMouseListener(PaintB);
			play[i].setBounds(k,227,55,55);
			play[i].setName("卒1" + i);
		  }

		  In = new ImageIcon("image\\黑炮.GIF");
		  for (i=26,k=81;i<28;i++,k+=342)
		  {
		    play[i] = new JLabel(In);
			con.add(play[i]);
			play[i].addMouseListener(PaintB);
			play[i].setBounds(k,170,55,55);
			play[i].setName("炮1" + i);
		  }

		  In = new ImageIcon("image\\黑將.GIF");
		    play[30] = new JLabel(In);
		    con.add(play[30]);
			play[30].addMouseListener(PaintB);
		    play[30].setBounds(252,56,55,55);
		    play[30].setName("將1");

		  In = new ImageIcon("image\\紅車.GIF");
		  for (i=2,k=24;i<4;i++,k+=456)
		  {
			play[i] = new JLabel(In);
			con.add(play[i]);
			play[i].addMouseListener(PaintB);
			play[i].setBounds(k,569,55,55);
			play[i].setName("車2");
		  }

		  In = new ImageIcon("image\\紅馬.GIF");
		  for (i=6,k=81;i<8;i++,k+=342)
		  {
			play[i] = new JLabel(In);
			con.add(play[i]);
			play[i].addMouseListener(PaintB);
			play[i].setBounds(k,569,55,55);
			play[i].setName("馬2");
		  }

		  In = new ImageIcon("image\\紅象.GIF");
		  for (i=10,k=138;i<12;i++,k+=228)
		  {
			play[i] = new JLabel(In);
			con.add(play[i]);
			play[i].addMouseListener(PaintB);
			play[i].setBounds(k,569,55,55);
			play[i].setName("象2");
		  }

		  In = new ImageIcon("image\\紅士.GIF");
		  for (i=14,k=195;i<16;i++,k+=114)
		  {
			play[i] = new JLabel(In);
			con.add(play[i]);
			play[i].addMouseListener(PaintB);
			play[i].setBounds(k,569,55,55);
			play[i].setName("士2");
		  }

		  In = new ImageIcon("image\\紅卒.GIF");
		  for (i=21,k=24;i<26;i++,k+=114)
		  {
			play[i] = new JLabel(In);
			con.add(play[i]);
			play[i].addMouseListener(PaintB);
			play[i].setBounds(k,398,55,55);
			play[i].setName("卒2" + i);
		  }

		  In = new ImageIcon("image\\紅炮.GIF");
		  for (i=28,k=81;i<30;i++,k+=342)
		  {
			play[i] = new JLabel(In);
			con.add(play[i]);
			play[i].addMouseListener(PaintB);
			play[i].setBounds(k,455,55,55);
			play[i].setName("炮2" + i);
		  }

		  In = new ImageIcon("image\\紅將.GIF");
			play[31] = new JLabel(In);
			con.add(play[31]);
			play[31].addMouseListener(PaintB);
		    play[31].setBounds(252,569,55,55);
		    play[31].setName("帥2");

		  con.add(image = new JLabel(new ImageIcon("image\\Main.GIF")));
		  image.setBounds(0,30,558,620);
    	  image.addMouseListener(PaintB);




          Sa  = new Socket(InetAddress.getByName(serverIP),6060);
          out = new ObjectOutputStream(Sa.getOutputStream());
          in  = new ObjectInputStream(Sa.getInputStream());

          try
          {
              out.writeObject(NAME);
          }
          catch(Exception ex){}

          string=(String)in.readObject();

          JOptionPane.showMessageDialog(null,"您已與"+string+"連線對戰","連線成功",JOptionPane.PLAIN_MESSAGE);

          chessPlayClick=2;
          while(I==0)
          {
          	  if(chessPlayClick==1)
          	  {lab.setText("Client黑色出棋");}
          	  else if(chessPlayClick==2)
          	  {lab.setText("Server紅色出棋");}
          	  string=(String)in.readObject();




              if(string.indexOf("Server:")!=-1)
              {

              	   CHi=string;
              	   CHo=CHo+CHi;
              	   chat.setVisible(true);
              	   chat.setResizable(false);
			       txa.setText(CHo);

              }
             else if (string.indexOf("-1")!=-1)
              {
              	     int sx,sy;
              	     String str[];
              	     str=string.split(" ");
                     sx=Integer.parseInt(str[1]);
                     sy=Integer.parseInt(str[2]);
                     chessPlayClick=Integer.parseInt(str[3]);
                     Man=Integer.parseInt(str[4]);
                     play[Man].setBounds(sx,sy,55,55);

              }
               else
              {
              	     int sx,sy,eat;
              	     String str[];
              	     str=string.split(" ");
                     sx=Integer.parseInt(str[0]);
                     sy=Integer.parseInt(str[1]);
                     chessPlayClick=Integer.parseInt(str[2]);
                     Man=Integer.parseInt(str[3]);
                     play[Man].setBounds(sx,sy,55,55);

                     eat=Integer.parseInt(str[4]);
                     play[eat].setVisible(false);
                     Z[eat]=1;
                     if(eat==30)
                     {
                       JOptionPane.showMessageDialog(null,"紅棋勝利","勝負",JOptionPane.PLAIN_MESSAGE);
                       chessPlayClick=0;
                     }



              }


          }


          out.close();
          in.close();
          Sa.close();











		}



    }


    public static void go()
    {
        if(Man==30||Man==31)//將
    	{
    		if(Man==30)
    		{
    			if(P==3||P==4||P==5)
    			{
    				if(U==0||U==1||U==2)
    				{
    					if(play[Man].getX()==A[P]+57||play[Man].getX()==A[P]-57)
    					{
    						if(play[Man].getY()==B[U])
    						{
    							C1=1;
    						}
    					}
    					else if(play[Man].getY()==B[U]+57||play[Man].getY()==B[U]-57)
    					{
    						if(play[Man].getX()==A[P])
    						{
    							C1=1;
    						}
    					}
    				}
    			}
    		}
    		else if (Man==31)
    		{
    			if(P==3||P==4||P==5)
    			{
    				if(U==7||U==8||U==9)
    				{
    					if(play[Man].getX()==A[P]+57||play[Man].getX()==A[P]-57)
    					{
    						if(play[Man].getY()==B[U])
    						{
    							C1=1;
    						}
    					}
    					else if(play[Man].getY()==B[U]+57||play[Man].getY()==B[U]-57)
    					{
    						if(play[Man].getX()==A[P])
    						{
    							C1=1;
    						}
    					}

    				}
       			}

    		}

    	}

 ////////////////////////////////////////////////////////////////////////////////////////////////
    	else if(Man==12||Man==13||Man==14||Man==15)//士
    	{
    		if(Man==12||Man==13)
    		{
    			if(P==3||P==4||P==5)
    			{
    				if(U==0||U==1||U==2)
    				{
    					if(play[Man].getX()==A[P]+57&&play[Man].getY()==B[U]+57)
    					{
    						C1=1;
    					}
    					else if(play[Man].getX()==A[P]-57&&play[Man].getY()==B[U]-57)
    					{

    						C1=1;

    					}
    					else if(play[Man].getX()==A[P]+57&&play[Man].getY()==B[U]-57)
    					{

    						C1=1;

    					}
    					else if(play[Man].getX()==A[P]-57&&play[Man].getY()==B[U]+57)
    					{

    						C1=1;

    					}
    				}
    		    }
    		}
    	    else if(Man==14||Man==15)
    	    {
    	    	if(P==3||P==4||P==5)
    			{
    				if(U==7||U==8||U==9)
    				{
    					if(play[Man].getX()==A[P]+57&&play[Man].getY()==B[U]+57)
    					{
    						C1=1;
    					}
    					else if(play[Man].getX()==A[P]-57&&play[Man].getY()==B[U]-57)
    					{

    						C1=1;

    					}
    					else if(play[Man].getX()==A[P]+57&&play[Man].getY()==B[U]-57)
    					{

    						C1=1;

    					}
    					else if(play[Man].getX()==A[P]-57&&play[Man].getY()==B[U]+57)
    					{

    						C1=1;

    					}

    				}
       			}
    	    }
    	}
////////////////////////////////////////////////////////////////////////////////////////////////////
    	else if(Man==8||Man==9||Man==10||Man==11)//象for
    	{
    		if(Man==10||Man==11)
    		{
    			if(U==5||U==6||U==7||U==8||U==9)
    			{

    			        if(play[Man].getX()+114==A[P]&&play[Man].getY()+114==B[U])
    					{
    						for(int i=0;i<32;i++)
    						{
    							if(play[i].getX()==play[Man].getX()+57&&play[i].getY()==play[Man].getY()+57&&Z[i]!=1){C2=1;}

    						}
    						if(C2==0)
    						{
    							C1=1;

    						}
    						else if(C2==1)
    						{
    						    C2=0;
    						}

    					}
    					else if(play[Man].getX()-114==A[P]&&play[Man].getY()-114==B[U])
    					{

    						for(int i=0;i<32;i++)
    						{
    							if(play[i].getX()==play[Man].getX()-57&&play[i].getY()==play[Man].getY()-57&&Z[i]!=1){C2=1;}

    						}
    						if(C2==0)
    						{
    							C1=1;

    						}
    						else if(C2==1)
    						{
    						    C2=0;
    						}
    					}
    					else if(play[Man].getX()+114==A[P]&&play[Man].getY()-114==B[U])
    					{

    						for(int i=0;i<32;i++)
    						{
    							if(play[i].getX()==play[Man].getX()+57&&play[i].getY()==play[Man].getY()-57&&Z[i]!=1){C2=1;}

    						}
    						if(C2==0)
    						{
    							C1=1;

    						}
    						else if(C2==1)
    						{
    						    C2=0;
    						}

    					}
    					else if(play[Man].getX()-114==A[P]&&play[Man].getY()+114==B[U])
    					{

    						for(int i=0;i<32;i++)
    						{
    							if(play[i].getX()==play[Man].getX()-57&&play[i].getY()==play[Man].getY()+57&&Z[i]!=1){C2=1;}

    						}
    						if(C2==0)
    						{
    							C1=1;

    						}
    						else if(C2==1)
    						{
    						    C2=0;
    						}

    					}

    			}
    		}
    		else if(Man==8||Man==9)
    		{
    			if(U==0||U==1||U==2||U==3||U==4)
    			{

    			        if(play[Man].getX()+114==A[P]&&play[Man].getY()+114==B[U])
    					{
    						for(int i=0;i<32;i++)
    						{
    							if(play[i].getX()==play[Man].getX()+57&&play[i].getY()==play[Man].getY()+57&&Z[i]!=1){C2=1;}

    						}
    						if(C2==0)
    						{
    							C1=1;

    						}
    						else if(C2==1)
    						{
    						    C2=0;
    						}


    					}
    					else if(play[Man].getX()-114==A[P]&&play[Man].getY()-114==B[U])
    					{

    						for(int i=0;i<32;i++)
    						{
    							if(play[i].getX()==play[Man].getX()-57&&play[i].getY()==play[Man].getY()-57&&Z[i]!=1){C2=1;}

    						}
    						if(C2==0)
    						{
    							C1=1;

    						}
    						else if(C2==1)
    						{
    						    C2=0;
    						}

    					}
    					else if(play[Man].getX()+114==A[P]&&play[Man].getY()-114==B[U])
    					{

    						for(int i=0;i<32;i++)
    						{
    							if(play[i].getX()==play[Man].getX()+57&&play[i].getY()==play[Man].getY()-57&&Z[i]!=1){C2=1;}

    						}
    						if(C2==0)
    						{
    							C1=1;

    						}
    						else if(C2==1)
    						{
    						    C2=0;
    						}

    					}
    					else if(play[Man].getX()-114==A[P]&&play[Man].getY()+114==B[U])
    					{

    						for(int i=0;i<32;i++)
    						{
    							if(play[i].getX()==play[Man].getX()-57&&play[i].getY()==play[Man].getY()+57&&Z[i]!=1){C2=1;}

    						}
    						if(C2==0)
    						{
    							C1=1;

    						}
    						else if(C2==1)
    						{
    						    C2=0;
    						}

    					}
    		    }
    	   }
    	}
///////////////////////////////////////////////////////////////////////////////////////////////
        else if(Man==16||Man==17||Man==18||Man==19||Man==20||Man==21||Man==22||Man==23||Man==24||Man==25)//卒
    	{
    		if(Man==16||Man==17||Man==18||Man==19||Man==20)
    		{
    			if(U==0||U==1||U==2||U==3||U==4)
    		    {
    		    	if(play[Man].getY()==B[U]-57)
    					{
    						if(play[Man].getX()==A[P])
    						{
    							C1=1;
    						}
    					}

    		    }
    		    else if(U==5||U==6||U==7||U==8||U==9)
    		    {
    		    	if(play[Man].getY()==B[U]-57)
    				{
    					if(play[Man].getX()==A[P])
    					{
    							C1=1;
    					}
    				}
    				else if(play[Man].getX()==A[P]+57||play[Man].getX()==A[P]-57)
    				{
    					if(play[Man].getY()==B[U])
    					{
    							C1=1;
    					}
    				}

    		    }

    		}
            else if(Man==21||Man==22||Man==23||Man==24||Man==25)
            {
            	if(U==5||U==6||U==7||U==8||U==9)
    		    {
    		    	if(play[Man].getY()==B[U]+57)
    					{
    						if(play[Man].getX()==A[P])
    						{
    							C1=1;
    						}
    					}

    		    }
    		    else if(U==0||U==1||U==2||U==3||U==4)
    		    {
    		    	if(play[Man].getY()==B[U]+57)
    				{
    					if(play[Man].getX()==A[P])
    					{
    							C1=1;
    					}
    				}
    				else if(play[Man].getX()==A[P]+57||play[Man].getX()==A[P]-57)
    				{
    					if(play[Man].getY()==B[U])
    					{
    							C1=1;
    					}
    				}

    		    }

            }
    	}
////////////////////////////////////////////////////////////////////////////////////
        else if(Man==29||Man==28||Man==27||Man==26||Man==0||Man==1||Man==2||Man==3)//車for
    	{
    		int r1=57,r2=57,r3=57,r4=57;
    		int t1=24,t2=480,t3=56,t4=569;
    		while(24<=play[Man].getX()-r1)
    		{

    			for(int i=0;i<32;i++)
    			{

    			  if(play[Man].getX()-r1==play[i].getX()&&play[Man].getY()==play[i].getY()&&Z[i]!=1)
    			  {
                    C3=1;
    			  	t1=play[i].getX();

    			  }
    			}
    			r1=r1+57;
    			if(C3==1)
    			{
    				C3=0;
    				break;
    			}
    		}
    		while(play[Man].getX()+r2<=480)
    		{
    			for(int i=0;i<32;i++)
    			{
    			  if(play[Man].getX()+r2==play[i].getX()&&play[Man].getY()==play[i].getY()&&Z[i]!=1)
    			  {
    			  	C3=1;
    			  	t2=play[i].getX();

    			  }
    			}
    			r2=r2+57;
    			if(C3==1)
    			{
    				C3=0;
    				break;
    			}
    		}
    		while(56<=play[Man].getY()-r3)
    		{
    			for(int i=0;i<32;i++)
    			{
    			  if(play[Man].getY()-r3==play[i].getY()&&play[Man].getX()==play[i].getX()&&Z[i]!=1)
    			  {
    			  	C3=1;
    			  	t3=play[i].getY();

    			  }
    			}
    			r3=r3+57;
    			if(C3==1)
    			{
    				C3=0;
    				break;
    			}
    		}
            while(play[Man].getY()+r4<=569)
            {
            	for(int i=0;i<32;i++)
    			{
    			  if(play[Man].getY()+r4==play[i].getY()&&play[Man].getX()==play[i].getX()&&Z[i]!=1)
    			  {
    			  	C3=1;
    			  	t4=play[i].getY();

    			  }
    			}
    			r4=r4+57;
    			if(C3==1)
    			{
    				C3=0;
    				break;
    			}
            }


            if(A[P]<=t2&&t1<=A[P]&&play[Man].getY()==B[U])
            {

            	C1=1;
            }
            else if(B[U]<=t4&&t3<=B[U]&&play[Man].getX()==A[P])
            {

            	C1=1;
            }


    	}

/////////////////////////////////////////////////////////////////////////////////////////////
        else if(Man==4||Man==5||Man==6||Man==7)//馬for
    	{
    		int z1=0,z2=0,z3=0,z4=0;
    		for(int i=0;i<32;i++)
    	    {
    		if     (play[i].getX()==play[Man].getX()+57  &&  play[i].getY()==play[Man].getY()   &&Z[i]!=1)       {z2=1;}
    		else if(play[i].getX()==play[Man].getX()-57  &&  play[i].getY()==play[Man].getY()   &&Z[i]!=1)       {z4=1;}
    		else if(play[i].getX()==play[Man].getX()     &&  play[i].getY()==play[Man].getY()-57&&Z[i]!=1)       {z1=1;}
    		else if(play[i].getX()==play[Man].getX()     &&  play[i].getY()==play[Man].getY()+57&&Z[i]!=1)       {z3=1;}
            }
    		if(play[Man].getX()+114==A[P]&&play[Man].getY()+57==B[U])
    		{
    			if(z2!=1)
    			{
    				C1=1;
    			}
    		}
    		else if(play[Man].getX()+114==A[P]&&play[Man].getY()-57==B[U])
    		{
    			if(z2!=1)
    			{
    				C1=1;
    			}
    		}
    		else if(play[Man].getX()-114==A[P]&&play[Man].getY()+57==B[U])
    		{
    			if(z4!=1)
    			{
    				C1=1;
    			}
    		}
    		else if(play[Man].getX()-114==A[P]&&play[Man].getY()-57==B[U])
    		{
    			if(z4!=1)
    			{
    				C1=1;
    			}
    		}
    		else if(play[Man].getX()+57==A[P]&&play[Man].getY()+114==B[U])
    		{
    			if(z3!=1)
    			{
    				C1=1;
    			}
    		}
    		else if(play[Man].getX()+57==A[P]&&play[Man].getY()-114==B[U])
    		{
    			if(z1!=1)
    			{
    				C1=1;
    			}
    		}
    		else if(play[Man].getX()-57==A[P]&&play[Man].getY()+114==B[U])
    		{
    			if(z3!=1)
    			{
    				C1=1;
    			}
    		}
    		else if(play[Man].getX()-57==A[P]&&play[Man].getY()-114==B[U])
    		{
    			if(z1!=1)
    			{
    				C1=1;
    			}
    		}


    	}

















  }
    public static void Eat(int i)
    {
    	if(Man==30||Man==31)//將
    	{
    		if(Man==30)
    		{
    			if(P==3||P==4||P==5)
    			{
    				if(U==0||U==1||U==2)
    				{
    					if(play[Man].getX()==play[i].getX()+57||play[Man].getX()==play[i].getX()-57)
    					{
    						if(play[Man].getY()==play[i].getY())
    						{
    							C4=1;
    						}
    					}
    					else if(play[Man].getY()==play[i].getY()+57||play[Man].getY()==play[i].getY()-57)
    					{
    						if(play[Man].getX()==play[i].getX())
    						{
    							C4=1;
    						}
    					}
    				}
    			}
    		}
    		else if (Man==31)
    		{
    			if(P==3||P==4||P==5)
    			{
    				if(U==7||U==8||U==9)
    				{
    					if(play[Man].getX()==play[i].getX()+57||play[Man].getX()==play[i].getX()-57)
    					{
    						if(play[Man].getY()==play[i].getY())
    						{
    							C4=1;
    						}
    					}
    					else if(play[Man].getY()==play[i].getY()+57||play[Man].getY()==play[i].getY()-57)
    					{
    						if(play[Man].getX()==play[i].getX())
    						{
    							C4=1;
    						}
    					}

    				}
       			}

    		}

    	}
    	////////////////////////////////////////////
    	else if(Man==12||Man==13||Man==14||Man==15)//士
    	{
    		if(Man==12||Man==13)
    		{
    			if(P==3||P==4||P==5)
    			{
    				if(U==0||U==1||U==2)
    				{
    					if(play[Man].getX()+57==play[i].getX()&&play[Man].getY()+57==play[i].getY())
    					{
    						C4=1;
    					}
    					else if(play[Man].getX()-57==play[i].getX()&&play[Man].getY()-57==play[i].getY())
    					{

    						C4=1;

    					}
    					else if(play[Man].getX()+57==play[i].getX()&&play[Man].getY()-57==play[i].getY())
    					{

    						C4=1;

    					}
    					else if(play[Man].getX()-57==play[i].getX()&&play[Man].getY()+57==play[i].getY())
    					{

    						C4=1;

    					}
    				}
    		    }
    		}
    	    else if(Man==14||Man==15)
    	    {
    	    	if(P==3||P==4||P==5)
    			{
    				if(U==7||U==8||U==9)
    				{
    					if(play[Man].getX()+57==play[i].getX()&&play[Man].getY()+57==play[i].getY())
    					{
    						C4=1;
    					}
    					else if(play[Man].getX()-57==play[i].getX()&&play[Man].getY()-57==play[i].getY())
    					{

    						C4=1;

    					}
    					else if((play[Man].getX()+57)==play[i].getX()&&(play[Man].getY()-57)==play[i].getY())
    					{

    						C4=1;System.out.println(4);

    					}
    					else if(play[Man].getX()-57==play[i].getX()&&play[Man].getY()+57==play[i].getY())
    					{

    						C4=1;

    					}

    				}
       			}
    	    }
    	}
    	//////////////////////////////////////
    	else if(Man==8||Man==9||Man==10||Man==11)//象
    	{
    		if(Man==10||Man==11)
    		{
    			if(U==5||U==6||U==7||U==8||U==9)
    			{

    			        if(play[Man].getX()+114==play[i].getX()&&play[Man].getY()+114==play[i].getY())
    					{
    						for(int j=0;j<32;j++)
    						{
    							if(play[j].getX()==play[Man].getX()+57&&play[j].getY()==play[Man].getY()+57&&Z[j]!=1){C2=1;}

    						}
    						if(C2==0)
    						{
    							C4=1;

    						}
    						else if(C2==1)
    						{
    						    C2=0;
    						}

    					}
    					else if(play[Man].getX()+114==play[i].getX()&&play[Man].getY()-114==play[i].getY())
    					{
    						for(int j=0;j<32;j++)
    						{
    							if(play[j].getX()==play[Man].getX()+57&&play[j].getY()==play[Man].getY()-57&&Z[j]!=1){C2=1;}

    						}
    						if(C2==0)
    						{
    							C4=1;

    						}
    						else if(C2==1)
    						{
    						    C2=0;
    						}

    					}
    					else if(play[Man].getX()-114==play[i].getX()&&play[Man].getY()-114==play[i].getY())
    					{
    						for(int j=0;j<32;j++)
    						{
    							if(play[j].getX()==play[Man].getX()-57&&play[j].getY()==play[Man].getY()-57&&Z[j]!=1){C2=1;}

    						}
    						if(C2==0)
    						{
    							C4=1;

    						}
    						else if(C2==1)
    						{
    						    C2=0;
    						}

    					}
    					else if(play[Man].getX()-114==play[i].getX()&&play[Man].getY()+114==play[i].getY())
    					{
    						for(int j=0;j<32;j++)
    						{
    							if(play[j].getX()==play[Man].getX()-57&&play[j].getY()==play[Man].getY()+57&&Z[j]!=1){C2=1;}

    						}
    						if(C2==0)
    						{
    							C4=1;

    						}
    						else if(C2==1)
    						{
    						    C2=0;
    						}

    					}

    			}
    		}
    		else if(Man==8||Man==9)
    		{
    			if(U==0||U==1||U==2||U==3||U==4)
    			{

    			        if(play[Man].getX()+114==play[i].getX()&&play[Man].getY()+114==play[i].getY())
    					{
    						for(int j=0;j<32;j++)
    						{
    							if(play[j].getX()==play[Man].getX()+57&&play[j].getY()==play[Man].getY()+57&&Z[j]!=1){C2=1;}

    						}
    						if(C2==0)
    						{
    							C4=1;

    						}
    						else if(C2==1)
    						{
    						    C2=0;
    						}

    					}
    					else if(play[Man].getX()+114==play[i].getX()&&play[Man].getY()-114==play[i].getY())
    					{
    						for(int j=0;j<32;j++)
    						{
    							if(play[j].getX()==play[Man].getX()+57&&play[j].getY()==play[Man].getY()-57&&Z[j]!=1){C2=1;}

    						}
    						if(C2==0)
    						{
    							C4=1;

    						}
    						else if(C2==1)
    						{
    						    C2=0;
    						}

    					}
    					else if(play[Man].getX()-114==play[i].getX()&&play[Man].getY()-114==play[i].getY())
    					{
    						for(int j=0;j<32;j++)
    						{
    							if(play[j].getX()==play[Man].getX()-57&&play[j].getY()==play[Man].getY()-57&&Z[j]!=1){C2=1;}

    						}
    						if(C2==0)
    						{
    							C4=1;

    						}
    						else if(C2==1)
    						{
    						    C2=0;
    						}

    					}
    					else if(play[Man].getX()-114==play[i].getX()&&play[Man].getY()+114==play[i].getY())
    					{
    						for(int j=0;j<32;j++)
    						{
    							if(play[j].getX()==play[Man].getX()-57&&play[j].getY()==play[Man].getY()+57&&Z[j]!=1){C2=1;}

    						}
    						if(C2==0)
    						{
    							C4=1;

    						}
    						else if(C2==1)
    						{
    						    C2=0;
    						}

    					}
    		    }
    	   }
    	}
///////////////////////////////////////////////////////////////////////////////////////////////
        else if(Man==16||Man==17||Man==18||Man==19||Man==20||Man==21||Man==22||Man==23||Man==24||Man==25)//卒
    	{
    		if(Man==16||Man==17||Man==18||Man==19||Man==20)
    		{
    			if(U==0||U==1||U==2||U==3||U==4)
    		    {
    		    	if(play[Man].getY()==play[i].getY()-57)
    					{
    						if(play[Man].getX()==play[i].getX())
    						{
    							C4=1;
    						}
    					}

    		    }
    		    else if(U==5||U==6||U==7||U==8||U==9)
    		    {
    		    	if(play[Man].getY()==play[i].getY()-57)
    				{
    					if(play[Man].getX()==play[i].getX())
    					{
    							C4=1;
    					}
    				}
    				else if(play[Man].getX()==play[i].getX()+57||play[Man].getX()==play[i].getX()-57)
    				{
    					if(play[Man].getY()==play[i].getY())
    					{
    							C4=1;
    					}
    				}

    		    }

    		}
            else if(Man==21||Man==22||Man==23||Man==24||Man==25)
            {
            	if(U==5||U==6||U==7||U==8||U==9)
    		    {
    		    	if(play[Man].getY()==play[i].getY()+57)
    					{
    						if(play[Man].getX()==play[i].getX())
    						{
    							C4=1;
    						}
    					}

    		    }
    		    else if(U==0||U==1||U==2||U==3||U==4)
    		    {
    		    	if(play[Man].getY()==play[i].getY()+57)
    				{
    					if(play[Man].getX()==play[i].getX())
    					{
    							C4=1;
    					}
    				}
    				else if(play[Man].getX()==play[i].getX()+57||play[Man].getX()==play[i].getX()-57)
    				{
    					if(play[Man].getY()==play[i].getY())
    					{
    							C4=1;
    					}
    				}

    		    }

            }
    	}
/////////////////////////////////////////////////////////////////////////////////////////////////////
        else if(Man==4||Man==5||Man==6||Man==7)//馬for
    	{
    		int z1=0,z2=0,z3=0,z4=0;
    		for(int j=0;j<32;j++)
    	    {
    		if     (play[j].getX()==play[Man].getX()+57  &&  play[j].getY()==play[Man].getY()   &&Z[j]!=1)       {z2=1;}
    		else if(play[j].getX()==play[Man].getX()-57  &&  play[j].getY()==play[Man].getY()   &&Z[j]!=1)       {z4=1;}
    		else if(play[j].getX()==play[Man].getX()     &&  play[j].getY()==play[Man].getY()-57&&Z[j]!=1)       {z1=1;}
    		else if(play[j].getX()==play[Man].getX()     &&  play[j].getY()==play[Man].getY()+57&&Z[j]!=1)       {z3=1;}
            }
    		if(play[Man].getX()+114==play[i].getX()&&play[Man].getY()+57==play[i].getY())
    		{
    			if(z2!=1)
    			{
    				C4=1;
    			}
    		}
    		else if(play[Man].getX()+114==play[i].getX()&&play[Man].getY()-57==play[i].getY())
    		{
    			if(z2!=1)
    			{
    				C4=1;
    			}
    		}
    		else if(play[Man].getX()-114==play[i].getX()&&play[Man].getY()+57==play[i].getY())
    		{
    			if(z4!=1)
    			{
    				C4=1;
    			}
    		}
    		else if(play[Man].getX()-114==play[i].getX()&&play[Man].getY()-57==play[i].getY())
    		{
    			if(z4!=1)
    			{
    				C4=1;
    			}
    		}
    		else if(play[Man].getX()+57==play[i].getX()&&play[Man].getY()+114==play[i].getY())
    		{
    			if(z3!=1)
    			{
    				C4=1;
    			}
    		}
    		else if(play[Man].getX()+57==play[i].getX()&&play[Man].getY()-114==play[i].getY())
    		{
    			if(z1!=1)
    			{
    				C4=1;
    			}
    		}
    		else if(play[Man].getX()-57==play[i].getX()&&play[Man].getY()+114==play[i].getY())
    		{
    			if(z3!=1)
    			{
    				C4=1;
    			}
    		}
    		else if(play[Man].getX()-57==play[i].getX()&&play[Man].getY()-114==play[i].getY())
    		{
    			if(z1!=1)
    			{
    				C4=1;
    			}
    		}


    	}

  ///////////////////////////////////////////////////
        else if(/*Man==29||Man==28||Man==27||Man==26||*/Man==0||Man==1||Man==2||Man==3)//車for
    	{
    		int r1=57,r2=57,r3=57,r4=57;
    		int t1=24,t2=480,t3=56,t4=569;
    		while(24<=play[Man].getX()-r1)
    		{

    			for(int j=0;j<32;j++)
    			{

    			  if(play[Man].getX()-r1==play[j].getX()&&play[Man].getY()==play[j].getY()&&Z[j]!=1)
    			  {
                    C3=1;
    			  	t1=play[j].getX();

    			  }
    			}
    			r1=r1+57;
    			if(C3==1)
    			{
    				C3=0;
    				break;
    			}
    		}
    		while(play[Man].getX()+r2<=480)
    		{
    			for(int j=0;j<32;j++)
    			{
    			  if(play[Man].getX()+r2==play[j].getX()&&play[Man].getY()==play[j].getY()&&Z[j]!=1)
    			  {
    			  	C3=1;
    			  	t2=play[j].getX();

    			  }
    			}
    			r2=r2+57;
    			if(C3==1)
    			{
    				C3=0;
    				break;
    			}
    		}
    		while(56<=play[Man].getY()-r3)
    		{
    			for(int j=0;j<32;j++)
    			{
    			  if(play[Man].getY()-r3==play[j].getY()&&play[Man].getX()==play[j].getX()&&Z[j]!=1)
    			  {
    			  	C3=1;
    			  	t3=play[j].getY();

    			  }
    			}
    			r3=r3+57;
    			if(C3==1)
    			{
    				C3=0;
    				break;
    			}
    		}
            while(play[Man].getY()+r4<=569)
            {
            	for(int j=0;j<32;j++)
    			{
    			  if(play[Man].getY()+r4==play[j].getY()&&play[Man].getX()==play[j].getX()&&Z[j]!=1)
    			  {
    			  	C3=1;
    			  	t4=play[j].getY();

    			  }
    			}
    			r4=r4+57;
    			if(C3==1)
    			{
    				C3=0;
    				break;
    			}
            }


            if(play[i].getX()<=t2&&t1<=play[i].getX()&&play[Man].getY()==play[i].getY())
            {

            	C4=1;
            }
            else if(play[i].getY()<=t4&&t3<=play[i].getY()&&play[Man].getX()==play[i].getX())
            {

            	C4=1;
            }


    	}
    	/////////////////////////////////////
        else if(Man==29||Man==28||Man==27||Man==26)//炮
        {
        	int r1=57,r2=57,r3=57,r4=57;
    		int t1=24,t2=480,t3=56,t4=569;
    		int p1=-1,p2=-1,p3=-1,p4=-1;
    		while(24<=play[Man].getX()-r1)
    		{

    			for(int j=0;j<32;j++)
    			{

    			  if(play[Man].getX()-r1==play[j].getX()&&play[Man].getY()==play[j].getY()&&Z[j]!=1)
    			  {
                    if(C3==0)
                    {
                    C3=1;
                    }
    			  	else if(C3==1)
    			    {
    			    C5=1;
    				p1=j;
    				System.out.println("p1="+play[j].getName());
    			    }


    			  }
    			}
    			r1=r1+57;
    			if(C5==1)
    			{
    				break;
    			}
    		}
    		C3=0;
    		C5=0;
    		while(play[Man].getX()+r2<=480)
    		{
    			for(int j=0;j<32;j++)
    			{
    			  if(play[Man].getX()+r2==play[j].getX()&&play[Man].getY()==play[j].getY()&&Z[j]!=1)
    			  {
    			  	if(C3==0)
                    {
                    C3=1;
                    }
    			  	else if(C3==1)
    			    {
    			    C5=1;
    				p2=j;
    				System.out.println("p1="+play[j].getName());
    			    }

    			  }
    			}
    			r2=r2+57;
    			if(C5==1)
    			{

    				break;
    			}
    		}
    		C3=0;
    		C5=0;
    		while(56<=play[Man].getY()-r3)
    		{
    			for(int j=0;j<32;j++)
    			{
    			  if(play[Man].getY()-r3==play[j].getY()&&play[Man].getX()==play[j].getX()&&Z[j]!=1)
    			  {
    			  	if(C3==0)
                    {
                    C3=1;
                    }
    			  	else if(C3==1)
    			    {
    			    C5=1;
    				p3=j;
    				System.out.println("p1="+play[j].getName());
    			    }

    			  }
    			}
    			r3=r3+57;
    			if(C5==1)
    			{

    				break;
    			}
    		}
    		C3=0;
    		C5=0;
            while(play[Man].getY()+r4<=569)
            {
            	for(int j=0;j<32;j++)
    			{
    			  if(play[Man].getY()+r4==play[j].getY()&&play[Man].getX()==play[j].getX()&&Z[j]!=1)
    			  {
    			  	if(C3==0)
                    {
                    C3=1;
                    }
    			  	else if(C3==1)
    			    {
    			    C5=1;
    				p4=j;
    				System.out.println("p1="+play[j].getName());
    			    }
    			  }
    			}
    			r4=r4+57;
    			if(C5==1)
    			{

    				break;
    			}
            }
            C3=0;
    		C5=0;
        	if(p1!=-1||p2!=-1||p3!=-1||p4!=-1)
            {
            	if(i==p1||i==p2||i==p3||i==p4)
            	{
            	C4=1;
            	}
            }





        }



    }

}