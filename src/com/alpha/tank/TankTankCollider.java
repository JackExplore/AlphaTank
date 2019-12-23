package com.alpha.tank;

public class TankTankCollider implements Collider{

	@Override
	public boolean collider(GameObject o1, GameObject o2) {
//		if(o1 instanceof Tank && o2 instanceof Tank) {
//			Tank t1 = (Tank) o1;
//			Tank t2 = (Tank) o2;
//			t1.setDir(Dir.values()[(t1.getDir().ordinal() + 2) % 4]);
//			t2.setDir(Dir.values()[(t2.getDir().ordinal() + 2) % 4]);	// 这个处理过程会导致坦克自旋转
//			return false;
//		} 
		return true;	// 是否继续往下执行 - 责任链
	}

}
