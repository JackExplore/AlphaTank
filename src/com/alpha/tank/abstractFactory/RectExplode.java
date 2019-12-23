package com.alpha.tank.abstractFactory;

import java.awt.Color;
import java.awt.Graphics;

import com.alpha.tank.Audio;
import com.alpha.tank.ResourceMgr;
import com.alpha.tank.TankFrame;


public class RectExplode extends BaseExplode{

	public static int WIDTH = ResourceMgr.exploding[0].getWidth();
	public static int HEIGHT = ResourceMgr.exploding[0].getHeight();
	
	private int x, y;
	
//	private boolean living = true;
	private int step = 0;
	
	private TankFrame tf;
	
	public RectExplode(int x, int y, TankFrame tf) {
		this.x = x;
		this.y = y;
		this.tf = tf;
		
//		new Audio("audio/explode.wav").start();
	}
	
	public void paint(Graphics g) {

//		Color c = g.getColor();
//		g.setColor(Color.RED);
//		g.fillRect(x, y, 10*step, 10*step);
//		step++;
//		
////		g.drawImage(ResourceMgr.exploding[step++], x, y, null);
////		
//		if(step >= ResourceMgr.exploding.length) {
//			tf.exploads.remove(this);
//		}
//		g.setColor(c);
	}


}
