package com.alpha.tank.strategy;

import com.alpha.tank.Bullet;
import com.alpha.tank.Tank;

public class DefaultFireStrategy implements FireStrategy{

	@Override
	public void fire(Tank k) {
		
		int bX = k.getX() + Tank.WIDTH/2 - Bullet.WIDTH/2;
		int bY = k.getY() + Tank.HEIGHT/2 - Bullet.HEIGHT/2;
		new Bullet(bX, bY, k.getDir(), k.getGroup());		
	}
	

}
