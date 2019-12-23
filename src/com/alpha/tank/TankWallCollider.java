package com.alpha.tank;

public class TankWallCollider implements Collider{

	@Override
	public boolean collider(GameObject o1, GameObject o2) {
		if(o1 instanceof Tank && o2 instanceof Wall) {
			Tank t = (Tank) o1;
			Wall w = (Wall) o2;
			
			if(t.rect.intersects(w.rect)) {
//				t.back();
			}
//			return false;
		} else if(o1 instanceof Wall && o2 instanceof Tank) {
			return collider(o2, o1);
		}
		return true;
	}

}
