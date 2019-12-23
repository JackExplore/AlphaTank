package com.alpha.tank.abstractFactory;

import com.alpha.tank.Dir;
import com.alpha.tank.Group;
import com.alpha.tank.TankFrame;

public class RectFactory extends GameFactory{

	@Override
	public BaseTank createTank(int x, int y, Dir dir, Group group, TankFrame tf) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BaseExplode createExplode(int x, int y, TankFrame tf) {
		// TODO Auto-generated method stub
		return new RectExplode(x, y, tf);
	}

	@Override
	public BaseBullet createBullet(int x, int y, Dir dir, Group group, TankFrame tf) {
		// TODO Auto-generated method stub
		return null;
	}

}
