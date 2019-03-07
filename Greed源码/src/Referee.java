import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

class UI extends JFrame
{
	private int state=0,mouseX,mouseY,isWaiting,humanNum,AINum,playerNow;
	MyCanvas canvas;
	Image background=new ImageIcon("./image/background.png").getImage();
	Image background1=new ImageIcon("./image/background1.png").getImage();
	Image background2=new ImageIcon("./image/background2.png").getImage();
	Image flag=new ImageIcon("./image/flag.png").getImage();
	Image win=new ImageIcon("./image/win.png").getImage();
	Image turn=new ImageIcon("./image/turn.png").getImage();
	Image[]a=new Image[11];
	Image[]h=new Image[11];
	Image[]t=new Image[7];
	Image[]n=new Image[11];
	Image[]s=new Image[11];
	int[]lx=new int[] {0,500,209,30,30,192,501,848,1152,1152,828};
	int[]ly=new int[] {0,549,549,351,100,16,16,16,104,359,549};
	int[]dx=new int[] {0,295,295,445,445,595,595};
	int[]dy=new int[] {0,209,359,209,359,209,359};
	int[]fx=new int[] {0,624,314,9,10,310,613,964,1128,1128,922};
	int[]fy=new int[] {0,560,560,464,200,49,49,49,206,455,560};
	int[]bx=new int[] {0};
	int[]by=new int[] {0};
	class MyCanvas extends Canvas
	{
		public void paint(Graphics g)
		{
			super.paint(g);
			Graphics2D g2=(Graphics2D)g;
			int i,score;
			if(state==0)
			{
				g2.drawImage(background,0,0,this);
			}
			else if(state==1)
			{
				g2.drawImage(background1,0,0,this);
			}
			else if(state==2)
			{
				g2.drawImage(background2,0,0,this);
			}
			g2.drawImage(turn,lx[playerNow]+80,ly[playerNow]+50,this);
			for(i=1;i<=humanNum;i++)
			{
				g2.drawImage(h[i%3+1],lx[i],ly[i],this);
			}
			for(i=humanNum+1;i<=humanNum+AINum;i++)
			{
				g2.drawImage(a[i],lx[i],ly[i],this);
			}
			for(i=1;i<=Referee.dice.getLastDiceNum();i++)
			{
				g2.drawImage(t[Referee.dice.getPoint(i)],dx[i],dy[i],this);
			}
			for(i=1;i<=humanNum;i++)
			{
				score=Referee.scoreboard.getPlayerScore(i);
				g2.drawImage(n[score/1000],fx[i],fy[i],this);
				g2.drawImage(n[(score/100%10)],fx[i]+30,fy[i],this);
				g2.drawImage(n[score/10%10],fx[i]+60,fy[i],this);
				g2.drawImage(n[score%10],fx[i]+90,fy[i],this);
				if(Referee.humans[i].getState()==0)
				{
					g2.drawImage(flag,lx[i]+80,ly[i]+70,this);
				}
				if(score>=3000)
				{
					if(i==1||i==2||i==10)
					{
						g2.drawImage(win,lx[i],ly[i]-20,this);
					}
					else
					{
						g2.drawImage(win,lx[i],ly[i],this);
					}
				}
			}
			
			for(i=humanNum+1;i<=humanNum+AINum;i++)
			{
				score=Referee.scoreboard.getPlayerScore(i);
				g2.drawImage(n[score/1000],fx[i],fy[i],this);
				g2.drawImage(n[(score/100%10)],fx[i]+30,fy[i],this);
				g2.drawImage(n[score/10%10],fx[i]+60,fy[i],this);
				g2.drawImage(n[score%10],fx[i]+90,fy[i],this);
				if(Referee.AIs[i-humanNum].getState()==0)
				{
					g2.drawImage(flag,lx[i]+80,ly[i]+70,this);
				}
				if(score>=3000)
				{
					if(i==1||i==2||i==10)
					{
						g2.drawImage(win,lx[i],ly[i]-20,this);
					}
					else
					{
						g2.drawImage(win,lx[i],ly[i],this);
					}
				}
			}
			score=Referee.dice.getScore();
			g2.drawImage(s[score/1000],721,205,this);
			g2.drawImage(s[(score/100%10)],791,205,this);
			g2.drawImage(s[score/10%10],861,205,this);
			g2.drawImage(s[score%10],931,205,this);
			score=Referee.getTurnScore();
			g2.drawImage(n[score/1000],800,285,this);
			g2.drawImage(n[(score/100%10)],830,285,this);
			g2.drawImage(n[score/10%10],860,285,this);
			g2.drawImage(n[score%10],890,285,this);
		}
		public MyCanvas()
		{
			this.addMouseListener(new MouseListener()
			{
				@Override
				public void mouseClicked(MouseEvent e)
				{
					mouseX=e.getX();
					mouseY=e.getY();
					isWaiting=0;
					System.out.println(e.getX()+" "+e.getY());
			    }
				@Override
				public void mousePressed(MouseEvent e) {}
				@Override
				public void mouseReleased(MouseEvent e) {}
				@Override
				public void mouseEntered(MouseEvent e) {}
				@Override
				public void mouseExited(MouseEvent e) {}
			});
		}
	}
	void startGUI() 
	{
		a[1]=new ImageIcon("./image/a1.png").getImage();
		a[2]=new ImageIcon("./image/a2.png").getImage();
		a[3]=new ImageIcon("./image/a3.png").getImage();
		a[4]=new ImageIcon("./image/a4.png").getImage();
		a[5]=new ImageIcon("./image/a5.png").getImage();
		a[6]=new ImageIcon("./image/a6.png").getImage();
		a[7]=new ImageIcon("./image/a7.png").getImage();
		a[8]=new ImageIcon("./image/a8.png").getImage();
		a[9]=new ImageIcon("./image/a9.png").getImage();
		a[10]=new ImageIcon("./image/a10.png").getImage();
		t[1]=new ImageIcon("./image/t1.png").getImage();
		t[2]=new ImageIcon("./image/t2.png").getImage();
		t[3]=new ImageIcon("./image/t3.png").getImage();
		t[4]=new ImageIcon("./image/t4.png").getImage();
		t[5]=new ImageIcon("./image/t5.png").getImage();
		t[6]=new ImageIcon("./image/t6.png").getImage();
		n[0]=new ImageIcon("./image/n0.png").getImage();
		n[1]=new ImageIcon("./image/n1.png").getImage();
		n[2]=new ImageIcon("./image/n2.png").getImage();
		n[3]=new ImageIcon("./image/n3.png").getImage();
		n[4]=new ImageIcon("./image/n4.png").getImage();
		n[5]=new ImageIcon("./image/n5.png").getImage();
		n[6]=new ImageIcon("./image/n6.png").getImage();
		n[7]=new ImageIcon("./image/n7.png").getImage();
		n[8]=new ImageIcon("./image/n8.png").getImage();
		n[9]=new ImageIcon("./image/n9.png").getImage();
		s[0]=new ImageIcon("./image/s0.png").getImage();
		s[1]=new ImageIcon("./image/s1.png").getImage();
		s[2]=new ImageIcon("./image/s2.png").getImage();
		s[3]=new ImageIcon("./image/s3.png").getImage();
		s[4]=new ImageIcon("./image/s4.png").getImage();
		s[5]=new ImageIcon("./image/s5.png").getImage();
		s[6]=new ImageIcon("./image/s6.png").getImage();
		s[7]=new ImageIcon("./image/s7.png").getImage();
		s[8]=new ImageIcon("./image/s8.png").getImage();
		s[9]=new ImageIcon("./image/s9.png").getImage();
		h[1]=new ImageIcon("./image/h1.png").getImage();
		h[2]=new ImageIcon("./image/h2.png").getImage();
		h[3]=new ImageIcon("./image/h3.png").getImage();
		this.setSize(1280,688);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Greed");
        this.setResizable(false);
        this.getFocusOwner();
		this.setVisible(true);
		canvas=new MyCanvas();
		add(canvas);
		humanNum=Referee.getHumanNum();
		AINum=Referee.getAINum();
		
    }
	int waitInput()
	{
		while(1==1)
		{
			isWaiting=1;
			while(isWaiting==1)
			{
				try   
				{   
					Thread.currentThread().sleep(17);
				}   
				catch(Exception e){}
			}
			if(state==1)
			{
				if(872<=mouseX&&mouseX<=1020&&400<=mouseY&&mouseY<=448)
				{
					return 3;
				}
			}
			else if(state==2)
			{
				if(709<=mouseX&&mouseX<=856&&334<=mouseY&&mouseY<=383)
				{
					return 1;
				}
				if(872<=mouseX&&mouseX<=1018&&334<=mouseY&&mouseY<=383)
				{
					return 0;
				}
				if(709<=mouseX&&mouseX<=856&&400<=mouseY&&mouseY<=448)
				{
					return -1;
				}
			}
		}
	}
	void refresh()
	{
		canvas.repaint();
		canvas.revalidate();
	}
	public void setState(int newState)
	{
		state=newState;
	}
	static int inputHumanNum()
	{
		return Referee.in.nextInt();
	}
	static int inputAINum()
	{
		return Referee.in.nextInt();
	}
	static int inputStraPref()
	{
		return Referee.in.nextInt();
	}
	void setPlayerNow(int newPlayerNow)
	{
		playerNow=newPlayerNow;
	}
}

class Referee
{
    private static int humanNum,AINum,score,i,x;
    static UI ui=new UI();
    static Scanner in=new Scanner(System.in);
    static Player[] humans=new Player[20];
    static AI[] AIs=new AI[20];
    static ScoreBoard scoreboard=new ScoreBoard();
    static Dice dice=new Dice();
    public static void main(String[]args)
    {	
        initPlayers();
        ui.startGUI();
    	startGame();
    }
    static void initPlayers()
    {
    	int i,x;
        for(i=1;i<20;i++)
        {
        	humans[i]=new Player();
            AIs[i]=new AI();
        }
        System.out.println("请在第一行输入玩家个数、AI个数");
        System.out.println("请在第二行输入每个AI的策略偏好，1为智慧型，2为保守型，3为激进型");
        humanNum=ui.inputHumanNum();
        AINum=ui.inputAINum();
        for(i=1;i<=humanNum;i++)
        {
            humans[i].setID(i);
        }
		for(i=1;i<=AINum;i++)
        {
            AIs[i].setID(humanNum+i);
            x=ui.inputStraPref();
            if(x==1)
            {
                AIs[i].setStraPref(1.0);
            }
            else if(x==2)
            {
                AIs[i].setStraPref(0.7);
            }
            else
            {
                AIs[i].setStraPref(1.3);
            }
        }
    }
    static void startGame()
    {
        int i;
        try   
		{   
			Thread.currentThread().sleep(3000);
		}   
		catch(Exception e){}
        while(1==1)
        {
            for(i=1;i<=humanNum+AINum;i++)
            {
                System.out.print(ScoreBoard.getPlayerScore(i)+"  ");
            }
            System.out.println(" ");
            for(i=1;i<=humanNum;i++)
        	{
            	if(scoreboard.updateScore(i,playOnce(i))==1)
            	{
                	System.out.println("Human "+i+" win!");
                    return;
            	}
        	}
        	for(i=1;i<=AINum;i++)
        	{
            	if(scoreboard.updateScore(i+humanNum,playOnce(i+humanNum))==1)
            	{
                	System.out.println("AI "+(i+humanNum)+" win!");
                    return;
            	}
        	}
        }
    }
    static int playOnce(int num)
    {
    	ui.setPlayerNow(num);
        dice.reset();
        if(num<=humanNum)
        {
        	score=0;
        	Player player=humans[num];
            if(player.getState()==-1)
        	{
            	return 0;
        	}
        	int result;
        	score+=player.castDices(dice);
        	if(score==0)
        	{
        		Referee.ui.setState(1);
        		Referee.ui.refresh();
            	Referee.ui.waitInput();
        		return 0;
        	}
        	if(player.getState()==0)
        	{
            	if(score>=300)
            	{
                	player.setState(1);
            	}
            	else
            	{
            		score=0;
            		Referee.ui.setState(1);
            		Referee.ui.refresh();
                	Referee.ui.waitInput();
                	return 0;
            	}
        	}
        	Referee.ui.refresh();
        	while(player.getDecision(dice,in)==1)
        	{
            	result=player.castDices(dice);
            	score+=result;
            	if(result==0)
            	{
            		score=0;
            		Referee.ui.setState(1);
            		Referee.ui.refresh();
                	Referee.ui.waitInput();
                	return 0;
            	}
        	}
        	return score;
        }
        else
        {
        	score=0;
            AI player=AIs[num-humanNum];
            if(player.getState()==-1)
        	{
            	return 0;
        	}
        	int result;
        	score+=player.castDices(dice);
        	if(score==0)
        	{
        		Referee.ui.setState(1);
        		Referee.ui.refresh();
            	Referee.ui.waitInput();
        		return 0;
        	}
        	if(player.getState()==0)
        	{
            	if(score>=300)
            	{
                	player.setState(1);
            	}
            	else
            	{
            		score=0;
            		Referee.ui.setState(1);
            		Referee.ui.refresh();
                	Referee.ui.waitInput();
                	return 0;
            	}
        	}
        	Referee.ui.setState(1);
        	Referee.ui.refresh();
        	Referee.ui.waitInput();
        	while(player.getAIDecision(dice,score)==1)
        	{
            	result=player.castDices(dice);
            	score+=result;
            	if(result==0)
            	{
            		score=0;
            		Referee.ui.setState(1);
            		Referee.ui.refresh();
                	Referee.ui.waitInput();
                	return 0;
            	}
            	Referee.ui.setState(1);
            	Referee.ui.refresh();
            	Referee.ui.waitInput();
        	}
        	return score;
        }
    }
    static int getHumanNum()
    {
    	return humanNum;
    }
    static int getAINum()
    {
    	return AINum;
    }
    static int getTurnScore()
    {
    	return score;
    }
}

class Player
{
    private int id,state;
    Player()
    {
        id=0;
        state=0;
    }
    int castDices(Dice dice)
    {
        dice.cast();
        return dice.getScore();
    }
    int getDecision(Dice dice,Scanner in)
    {
        int x;
        if(dice.getDiceNum()==0)
        {
        	Referee.ui.setState(1);
    		Referee.ui.refresh();
        	Referee.ui.waitInput();
            return 0;
        }
        Referee.ui.setState(2);
        Referee.ui.refresh();
        x=Referee.ui.waitInput();
        if(x==-1)
        {
            state=-1;
            x=0;
        }
        return x;
    }
    void setID(int newid)
    {
        id=newid;
    }
    int getState()
    {
        return state;
    }
    void setState(int newState)
    {
        state=newState;
    }
}

class ScoreBoard
{
    private static int[]score=new int[100];
    int updateScore(int playerID,int newScore)
    {
        score[playerID]+=newScore;
        Referee.ui.refresh();
        if(score[playerID]>=3000)
        {
            return 1;
        }
        else
        {
            return 0;
        }
    }
    static int getPlayerScore(int playerID)
    {
        return score[playerID];
    }
}

class AI extends Player
{
    private int[]critical=new int[7];
    private double strategyPreference;
    AI()
    {
        critical[0]=4000;
        critical[1]=38;
        critical[2]=113;
        critical[3]=313;
        critical[4]=4000;
        critical[5]=4000;
        critical[6]=4000;
    }
    int getAIDecision(Dice dice,int gain)
    {
    	if(dice.getDiceNum()==0)
        {
            return 0;
        }
        if(gain<critical[dice.getDiceNum()]*strategyPreference)
        {
            return 1;
        }
        else
        {
            return 0;
        }
    }
    void setStraPref(double newPref)
    {
    	strategyPreference=newPref;
    }
}

class Dice
{
	private int point[]=new int[7];
    private int diceNum,lastDiceNum;
    private int diceScore;
    void cast()
    {
        double ran;
        int i,flag;
        int[]r=new int[7];
        diceScore=0;
        lastDiceNum=diceNum;
        for(i=1;i<=6;i++)
        {
            r[i]=0;
        }
        for(i=1;i<=diceNum;i++)
        {
            ran=Math.random();
            point[i]=((int)(ran*10000))%6+1;
            r[point[i]]++;
        }
        while(1==1)
        {
            flag=0;
            if(r[1]==6)
            {
                flag=1;
                r[1]-=6;
                diceNum=0;
                diceScore=3000;
            }
            if(r[1]>=3)
            {
                flag=1;
                r[1]-=3;
                diceNum-=3;
                diceScore=1000;
            }
            for(i=2;i<=6;i++)
            {
                if(r[i]>=3)
                {
                    flag=1;
                    r[i]-=3;
                    diceNum-=3;
                    diceScore+=i*100;
                }
            }
            if(r[1]>0||r[5]>0)
            {
                flag=1;
                diceNum-=r[1]+r[5];
                diceScore+=100*r[1]+50*r[5];
                r[1]=0;
                r[5]=0;
            }
            if(flag==0)
            {
                break;
            }
        }
        Referee.ui.refresh();
    }
    void reset()
    {
        diceNum=6;
    }
    int getDiceNum()
    {
        return diceNum;
    }
    int getScore()
    {
        return diceScore;
    }
    int getPoint(int num)
    {
    	return point[num];
    }
    int getLastDiceNum()
    {
    	return lastDiceNum;
    }
}