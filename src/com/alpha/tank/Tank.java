package com.alpha.tank;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;
import com.alpha.tank.abstractFactory.BaseTank;

public class Tank extends BaseTank{

	private int x, y;
	private Dir dir = Dir.DOWN;
	private static final int SPEED = 2;
	public static int WIDTH = ResourceMgr.goodTankD.getWidth();
	public static int HEIGHT = ResourceMgr.goodTankD.getHeight();
	
//	public GameModel  gm;
	
	private Random random = new Random();
	Rectangle rect;

	private boolean moving = true;
	private boolean living = true;
	private Group group = Group.BAD;
//	FireStrategy fs;
	int oldX;
	int oldY;

	public Tank(int x, int y, Dir dir, Group group) {
		super();
		this.x = x;
		this.y = y;
		this.dir = dir;
//		this.gm = gameModel;
		this.group = group;
		
		String fsName = "";
		if(group == Group.GOOD) {
			moving = false;
//			fs = new FourDirFireStrategy();
			fsName = (String)PropertyMgr.get("goodFS");	
		}else {
			moving = true;
//			fs = new DefaultFireStrategy();
			fsName = (String)PropertyMgr.get("badFS");
		}
		try {
//			fs = (FireStrategy)Class.forName(fsName).newInstance();
//			fs = (FireStrategy)Class.forName(fsName).getDeclaredConstructor().newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		rect = new Rectangle(x, y, WIDTH, HEIGHT);

	}


	public void paint(Graphics g) {
		
		if(!living) {
			GameModel.getInstance().remove(this);
			return;
		}
		
//		Color c = g.getColor();
//		g.setColor(Color.YELLOW);
//		g.fillRect(x, y, 50, 50);
//		g.setColor(c);
		// 由颜色方块转换为图片：
//		g.drawImage(ResourceMgr.tankD, x, y, null);
		
		if(this.group == Group.BAD) {
			switch(dir) {
			case LEFT:
				g.drawImage(ResourceMgr.badTankL, x, y, null);
				break;
			case RIGHT:
				g.drawImage(ResourceMgr.badTankR, x, y, null);
				break;
			case UP:
				g.drawImage(ResourceMgr.badTankU, x, y, null);
				break;
			case DOWN:
				g.drawImage(ResourceMgr.badTankD, x, y, null);
				break;
			}			
		}else {
			switch(dir) {
			case LEFT:
				g.drawImage(ResourceMgr.goodTankL, x, y, null);
				break;
			case RIGHT:
				g.drawImage(ResourceMgr.goodTankR, x, y, null);
				break;
			case UP:
				g.drawImage(ResourceMgr.goodTankU, x, y, null);
				break;
			case DOWN:
				g.drawImage(ResourceMgr.goodTankD, x, y, null);
				break;
			}

		}

		move();
	}

	private void move() {
		
		if(!moving) return;

		oldX = x;
		oldY = y;
		
		switch (dir) {
		case LEFT:
			x -= SPEED;
			break;
		case RIGHT:
			x += SPEED;
			break;
		case UP:
			y -= SPEED;
			break;
		case DOWN:
			y += SPEED;
			break;
		}

		
		// 对类型为敌人的操作
		if(this.group == Group.BAD) {		
			// 随机开火
			if(random.nextInt(1000) > 998) {
				this.fire();
			}
			// 随机改变方向
			if(random.nextInt(100) > 96) {
				dir = Dir.values()[random.nextInt(4)];
			}
		}

		boundsCheck();
		
		// udpate rect
		rect.x = x;
		rect.y = y;
	}
	
	
	public void die() {
		this.living = false;
	}

	
	public void fire() {
		int bX = this.x + Tank.WIDTH/2 - Bullet.WIDTH/2;
		int bY = this.y + Tank.HEIGHT/2 - Bullet.HEIGHT/2;
		

//		GameModel.getInstance().add(new Bullet(bX, bY, dir, this.group));
		
		Dir[] dirs = Dir.values();
		for(Dir d:dirs) GameModel.getInstance().add(new Bullet(bX, bY, d, this.group));

//		fs.fire(this);
		
	}
	
	// 边界检测
	private void boundsCheck() {
		if(this.x < 0) {
			x = 0;
		}
		if(this.y < 30) {
			y = 30;
		}
		if(this.x > TankFrame.GAME_WIDTH - Tank.WIDTH) {
			x = TankFrame.GAME_WIDTH - Tank.WIDTH;
		}
		if(this.y > TankFrame.GAME_HEIGHT - Tank.HEIGHT) {
			y = TankFrame.GAME_HEIGHT - Tank.HEIGHT;
		}
	}

	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Dir getDir() {
		return dir;
	}

	public void setDir(Dir dir) {
		this.dir = dir;
	}

	public static int getSpeed() {
		return SPEED;
	}

	public boolean isMoving() {
		return moving;
	}

	public void setMoving(boolean moving) {
		this.moving = moving;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}


	public void stop() {
		// TODO Auto-generated method stub
		this.moving = false;
	}


	public void back() {
		// TODO Auto-generated method stub
		x = oldX;
		y = oldY;
	}
}
