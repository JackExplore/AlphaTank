package com.alpha.tank;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TankFrame extends Frame{

	Tank myTank = new Tank(200, 200, Dir.STOP);
	
	public TankFrame() {
		setSize(800, 600);
		setResizable(false);
		setTitle("tank war");
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
				
			default: break;
			
			}
			setMainTankDir();
		}

		private void setMainTankDir() {
			if(bL) {
				myTank.setDir(Dir.LEFT);
			}else if(bR) {
				myTank.setDir(Dir.RIGHT);
			}else if(bU) {
				myTank.setDir(Dir.UP);
			}else if(bD) {
				myTank.setDir(Dir.DOWN);
			}else {
				myTank.setDir(Dir.STOP);
			}
		}
		
	}
	
	@Override
	public void paint(Graphics g) {

		myTank.paint(g);
		
	}
	
	

}
