package com.alpha.tank;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TankFrame extends Frame{

	static final int GAME_WIDTH = 1080, GAME_HEIGHT = 960;
	
//	Explode e = new Explode(100, 100, this);
	
	public TankFrame() {
		
		setSize(GAME_WIDTH, GAME_HEIGHT);
		setResizable(false);
		setTitle("Tank War");
		setVisible(true);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				// TODO Auto-generated method stub
				super.windowClosing(arg0);
				System.exit(0);
			}
		});
		
		this.addKeyListener(new MyKeyListener());
		
	}
	
	
	class MyKeyListener extends KeyAdapter{
		
		boolean bL = false;
		boolean bR = false;
		boolean bU = false;
		boolean bD = false;

		@Override
		public void keyPressed(KeyEvent e) {

			int keyCode = e.getKeyCode();
			switch(keyCode) {
			case KeyEvent.VK_LEFT:
				bL = true;
				break;
			case KeyEvent.VK_RIGHT:
				bR = true;
				break;
			case KeyEvent.VK_UP:
				bU = true;
				break;
			case KeyEvent.VK_DOWN:
				bD = true;
				break;
				
			default: 
				;
			
			}
			
			setMainTankDir();

		}

		@Override
		public void keyReleased(KeyEvent e) {
			int keyCode = e.getKeyCode();
			switch(keyCode) {
			case KeyEvent.VK_LEFT:
				bL = false;
				break;
			case KeyEvent.VK_RIGHT:
				bR = false;
				break;
			case KeyEvent.VK_UP:
				bU = false;
				break;
			case KeyEvent.VK_DOWN:
				bD = false;
				break;
			case KeyEvent.VK_CONTROL:
				GameModel.getInstance().myTank.fire();
				
			default: break;
			
			}
			setMainTankDir();
		}

		private void setMainTankDir() {
			
			if(!bL && !bR && !bU && !bD) {
				GameModel.getInstance().myTank.setMoving(false);
			}else {
				GameModel.getInstance().myTank.setMoving(true);
				
				if(bL) GameModel.getInstance().myTank.setDir(Dir.LEFT);
				if(bR) GameModel.getInstance().myTank.setDir(Dir.RIGHT);
				if(bU) GameModel.getInstance().myTank.setDir(Dir.UP);
				if(bD) GameModel.getInstance().myTank.setDir(Dir.DOWN);
				
			}
			
		}
		
	}
	
	
	@Override
	public void paint(Graphics g) {
		GameModel.getInstance().paint(g);
	}
	
	// 双缓冲？ 解决屏幕闪烁问题
	Image offScreenImage = null;
	@Override
	public void update(Graphics g) {
		if(offScreenImage == null) {
			offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
		}
		Graphics gOffScreen = offScreenImage.getGraphics();
		Color c = gOffScreen.getColor();
		gOffScreen.setColor(Color.BLACK);
		gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
		gOffScreen.setColor(c);
		paint(gOffScreen);	// 调用 paint() 在内存中进行绘制
		
		g.drawImage(offScreenImage, 0, 0, null);	// 调用画笔将整张图片画上去
		
	}
	

}
