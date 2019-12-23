package com.alpha.tank;

public class BulletTankCollider implements Collider{
	
	@Override
	public boolean collider(GameObject o1, GameObject o2) {
		if(o1 instanceof Bullet && o2 instanceof Tank) {
			Bullet b = (Bullet) o1;
			Tank t = (Tank) o2;
			if(b.collideWith(t)) {
				return false;
			}
		} else if(o1 instanceof Tank && o2 instanceof Bullet) {
			return collider(o2, o1);
		}
		return true;
	}

}
