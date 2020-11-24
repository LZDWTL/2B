import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;
import java.util.*;
import java.io.*;
public class AI extends MIDlet
{
	Display display;
	MainCanvas mc;
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
	Image[][] heroImg =new Image[4][3];
	Image currentImg;
	Image bossImg;
	Thread thread;   // 线程？
	
	int heroX,heroY,bossX,bossY;
	int flag=1;     //标志位
	int threadFlag=0;  //boss行走标志位

	Random random=new Random();        //随机数

	public MainCanvas(){
		
	try
	{

		for(int i=0; i<4; i++){       //for循环数组存取图片  4个方向，每个方向3张
			for(int j =0; j<3; j++){
				heroImg[i][j]=Image.createImage("/sayo"+ j + k+".png");
			}
			k+=2;
		}
		heroX = 110;
		heroY = 120;

		bossX = 0;
		bossY = 0;
		currentImg=heroImg[0][1];
		bossImg= Image.createImage("/benzaiten.png");
		thread = new Thread(this);  //线程
		thread.start();

	}
	catch (IOException e)
	{
		e.printStackTrace();
	}
	}
	public void paint(Graphics g){
		g.setColor(	222,184,135);     //背景颜色
		g.fillRect(0,0,getWidth(),getHeight());
		g.drawImage(currentImg,heroX,heroY,0);
		g.drawImage(bossImg,bossX,bossY,0);
	}

	public void run(){ //线程
		
		while(true){                                 //让boss自己在限定范围内行动
			
			try{
				Thread.sleep(100);
			}
			catch(InterruptedException e){
				e.printStackTrace();
			}
			if(bossX < 210 && threadFlag==0){      
				bossX++;
				if(bossX == 210){
					threadFlag=1;
				}
			}
			else if(bossX >10 && threadFlag==1){
				bossX--;
				if(bossX == 10){
					threadFlag=0;
				}
			}
			
			if( (heroX-bossX)<60 && (heroX-bossX)<70 ){    //在hero与boss的距离到达吸引boss之后启动下面的自动追逐hero的程序
				break;
			}
			repaint();
		}

		while(true){
			int number =random.nextInt(10);
			try{
				Thread.sleep(200);   //让线程不要执行得那么快
			}
			catch(InterruptedException e){
				e.printStackTrace();
			}
			if(number%3==0){
				if(bossX < heroX){   //boss在 X走
					bossX+=2;
				}
				else{
					bossX-=2;
				}

				if(bossY < heroY){   //boss在 Y走
					bossY++;
				}
				else{
					bossY--;
				}
			}
			repaint();
		}
	}
	

	public void keyPressed(int keyCode){

		int direction =0;

		int action = getGameAction(keyCode);
		switch(action){
		case DOWN:     //下
			direction=0;
			if(heroY>=0 && heroY<260){ //边界限制
				heroY+=2;
			}
			break;
		case LEFT:     //左
			direction=1;
			if(heroX>2 && heroX<=220){ //边界限制
				heroX-=2;
			}
			break;
		case UP:     //上
			direction=2;
			if(heroY>=2 && heroY<=260){  //边界限制
				heroY-=2;
			}
			break;
		case RIGHT:     //右
			direction=3;
			if(heroX>=0 && heroX<220){  //边界限制
				heroX+=2;
			}
			break;
		}
		
		ImageChange(direction);
	}

/*	
	public void keyRepeated(int keyCode){

		int direction =0;

		int action = getGameAction(keyCode);
		switch(action){
		case DOWN:     //下
			direction=0;
			if(heroY>=0 && heroY<260){ //边界限制
				heroY+=2;
			}
			break;
		case LEFT:     //左
			direction=1;
			if(heroX>2 && heroX<=220){ //边界限制
				heroX-=2;
			}
			break;
		case UP:     //上
			direction=2;
			if(heroY>=2 && heroY<=260){  //边界限制
				heroY-=2;
			}
			break;
		case RIGHT:     //右
			direction=3;
			if(heroX>=0 && heroX<220){  //边界限制
				heroX+=2;
			}
			break;
		}
		
		ImageChange(direction);
	}
*/

	public void ImageChange(int direction){
		if(flag==0){
			currentImg=heroImg[direction][0];
			flag++;
		}
		else if(flag==1){
			currentImg=heroImg[direction][2];
			flag++;
		}
		else if(flag==2){
			currentImg=heroImg[direction][2];
			flag++;
		}
	//	repaint();
	}



}
