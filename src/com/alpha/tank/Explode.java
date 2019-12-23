package com.alpha.tank;

import java.awt.Graphics;

import com.alpha.tank.abstractFactory.BaseExplode;

public class Explode extends BaseExplode{

	public static int WIDTH = ResourceMgr.exploding[0].getWidth();
	public static int HEIGHT = ResourceMgr.exploding[0].getHeight();
	
	private int x, y;
	
	private int step = 0;
	
	public Explode(int x, int y) {
		this.x = x;
		this.y = y;
		
		new Audio("audio/explode.wav").start();
	}
	
	public void paint(Graphics g) {

		g.drawImage(ResourceMgr.exploding[step++], x, y, null);
		
		if(step >= ResourceMgr.exploding.length) {
			GameModel.getInstance().remove(this);
		}
	}

}
