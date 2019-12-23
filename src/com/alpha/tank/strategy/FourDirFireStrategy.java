package com.alpha.tank.strategy;

import com.alpha.tank.Bullet;
import com.alpha.tank.Dir;
import com.alpha.tank.Tank;

public class FourDirFireStrategy implements FireStrategy {

	@Override
	public void fire(Tank k) {
		
		int bX = k.getX() + Tank.WIDTH/2 - Bullet.WIDTH/2;
		int bY = k.getY() + Tank.HEIGHT/2 - Bullet.HEIGHT/2;
		
		Dir[] dirs = Dir.values();
		for(Dir dir : dirs) {
			new Bullet(bX, bY, dir, k.getGroup());			
		}
		
	}
	
}
