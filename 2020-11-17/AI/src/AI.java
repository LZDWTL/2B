import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;
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
class MainCanvas extends Canvas
{
	Image img1,img2,img3,img4,img1_1,img2_1,img3_1,img4_1,currentImg;
	//int x,y;
	int x=110;
	int y=120;
	int flag=1;
	public MainCanvas(){
		
	try
	{
		img1=Image.createImage("/sayo00.png");   //下
		img2=Image.createImage("/sayo02.png");    //左
		img3=Image.createImage("/sayo06.png");  //右
		img4=Image.createImage("/sayo04.png");   //上

		img1_1=Image.createImage("/sayo20.png");   //下
		img2_1=Image.createImage("/sayo22.png");    //左
		img3_1=Image.createImage("/sayo26.png");  //右
		img4_1=Image.createImage("/sayo24.png");   //上
		currentImg=img1;
	}
	catch (IOException e)
	{
		e.printStackTrace();
	}
	}
	public void paint(Graphics g){
		g.setColor(200,155,200);
		g.fillRect(0,0,getWidth(),getHeight());
		g.drawImage(currentImg,x,y,0);
	}

	public void keyPressed(int keyCode){
		int action = getGameAction(keyCode);  
		if(action==LEFT){                  //左
			if(flag==1){
				currentImg=img2;
				flag=2;
			}
			else{
				currentImg=img2_1;
				flag=1;
			}
			if(x>2 && x<220){ //边界限制
				x-=2;
			}
			repaint();
		}  
		else if(action==RIGHT){              //右
			
			if(flag==1){
				currentImg=img3;
				flag=2;
			}
			else{
				currentImg=img3_1;
				flag=1;
			}
			if(x>=0 && x<220){  //边界限制
				x+=2;
			}
			repaint();
		}
		else if(action==UP){                    //上
//			currentImg=img4;
			if(flag==1){
				currentImg=img4;
				flag=2;
			}
			else{
				currentImg=img4_1;
				flag=1;
			}
			if(y>=2 && y<260){  //边界限制
				y-=2;
			}
			repaint();
		}
		else {                                //下
			if(flag==1){
				currentImg=img1;
				flag=2;
			}
			else{
				currentImg=img1_1;
				flag=1;
			}
		//	currentImg=img1;
			if(y>=0 && y<260){ //边界限制
				y+=2;
			}
			repaint();
		}
	}
}