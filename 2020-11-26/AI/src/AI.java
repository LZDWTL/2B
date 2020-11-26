import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;
import java.util.*;
import java.io.*;

public class AI extends MIDlet
{
	Display display;
	MainCanvas mc;
	
/* 图片标号
下  00 10 20
左  02 12 22
上  04 14 24
右  06 16 26
*/
	public AI(){
		display=Display.getDisplay(this);
		mc=new MainCanvas();
		display.setCurrent(mc);
	}
	public void startApp(){
	}
	public void destroyApp(boolean unc){
	}
	public void pauseApp(){
	}
}
class MainCanvas extends Canvas implements Runnable
{
	
	int k=0;
	int i,j;
	int flag=1;
	int heroX,heroY,bossX,bossY;

	Image [][]heroImg =new Image[4][3];
	Image currentImg;
	Image bossImg;
	Thread thread;
	Random random=new Random();  //随机数

	public MainCanvas(){
		try{
			
			for(i =0 ;i<4;i++){
				for(j=0;j<3;j++){
					heroImg[i][j]=Image.createImage("/sayo"+j+k+".png");
				}
				k+=2;
			}
			heroX=110;
			heroY=120;
			bossX=110;
			bossY=0;
			currentImg=heroImg[0][0];
			bossImg=Image.createImage("/tekidan01.png");
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	public void paint(Graphics g){
		g.setColor(250,200,180);
		g.fillRect(0,0,getWidth(),getHeight());
		g.drawImage(currentImg,heroX,heroY,0);
		g.drawImage(bossImg,bossX,bossY,0);
	}

	public void keyRepeated(int keyCode){
		int action=getGameAction(keyCode);
		switch(action){
			case KEY_NUM4:
			case LEFT:              //左
				if(heroX<=220 && heroX >2){
					heroX-=2;
				}
				heroMove(1);
				break;

			case KEY_NUM6:
			case RIGHT:				//右
				if(heroX<220 && heroX >=0){
					heroX+=2;
				}
				heroMove(3);
				break;

			case KEY_NUM8:
			case UP:				//上
				if(heroY<=260 && heroY >2){
					heroY-=2;
				}
				heroMove(2);
				break;

			case KEY_NUM2:
			case DOWN:				//下
				if(heroY<260 && heroY >=0){
					heroY+=2;
				}
				heroMove(0);
				break;
		}
	}

	public void keyPressed(int keyCode){
		int action=getGameAction(keyCode);
		switch(action){
			case KEY_NUM4:
			case LEFT:              //左
				if(heroX<=220 && heroX >2){
					heroX-=2;
				}
				heroMove(1);
				break;

			case KEY_NUM6:
			case RIGHT:				//右
				if(heroX<220 && heroX >=0){
					heroX+=2;
				}
				heroMove(3);
				break;

			case KEY_NUM8:
			case UP:				//上
				if(heroY<=260 && heroY >2){
					heroY-=2;
				}
				heroMove(2);
				break;

			case KEY_NUM2:
			case DOWN:				//下
				if(heroY<260 && heroY >=0){
					heroY+=2;
				}
				heroMove(0);
				break;
		}

	}

	public void heroMove(int x){
		if(flag==0){
			currentImg=heroImg[x][1];
			flag+=1;
		}
		else if(flag==1){
			currentImg=heroImg[x][0];
			flag+=1;
		}
		else if(flag==2){
			currentImg=heroImg[x][1];
			flag+=1;
		}
		else{
			currentImg=heroImg[x][2];
			flag=0;
		}
	
		repaint();
	}
	public void run(){
		while(true){
			int number = random.nextInt(10);
			try{
				Thread.sleep(100);
			}
			catch(InterruptedException e){
				e.printStackTrace();			
			}
			if(number%3==0){
				if(bossX < heroX){
					bossX++;
				}
				else{
					bossX--;
				}
				if(bossY < bossY){
					bossY++;
				}
				else{
					bossY--;
				}

			}
			repaint();

		}
	}

	
}
