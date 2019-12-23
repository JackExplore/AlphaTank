package com.alpha.tank;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.alpha.tank.abstractFactory.BaseBullet;

public class Bullet extends BaseBullet{

	private static final int SPEED = 10;
	public static int WIDTH = ResourceMgr.bulletD.getWidth();
	public static int HEIGHT = ResourceMgr.bulletD.getHeight();
	
	private int x, y;
	
	private Dir dir;
	
	private boolean living = true;
	
	private Group group = Group.BAD;
	
	Rectangle rect;

	public Bullet(int x, int y, Dir dir, Group group) {
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.group = group;
		
		rect = new Rectangle(x, y, WIDTH, HEIGHT);
		
		GameModel.getInstance().add(this);
	}
	
	public void paint(Graphics g) {
		if(!living) {
			GameModel.getInstance().remove(this);
		}
//		Color c = g.getColor();
//		g.setColor(Color.RED);
//		g.fillOval(x, y, WIDTH, HEIGHT);
//		g.setColor(c);

		switch(dir) {
		case LEFT:
			g.drawImage(ResourceMgr.bulletL, x, y, null);
			break;
		case RIGHT:
			g.drawImage(ResourceMgr.bulletR, x, y, null);
			break;
		case UP:
			g.drawImage(ResourceMgr.bulletU, x, y, null);
			break;
		case DOWN:
			g.drawImage(ResourceMgr.bulletD, x, y, null);
			break;
		}
		
		move();
	}
	
	private void move() {

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
		
		rect.x = x;
		rect.y = y;
		
		if(x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT) {
			living = false;
		}

	}

	public boolean collideWith(Tank tank) {

		if(this.group == tank.getGroup()) return false;
		
		// TODO: 用一个 rect 来记录子弹的位置
		// 修改后，使用各自对象所带的 Rectangle 来检测
		// 否则，每次刷新要检测 2 * m * n 个Rectangle 对象，加上刷新频率，新对象的创建很恐怖 !  细节点，注意！@
		if(rect.intersects(tank.rect)) {
			tank.die();
			this.die();
			// 添加爆炸效果
			GameModel.getInstance().add(new Explode(this.x, this.y));
//			tf.exploads.add(tf.gf.createExplode(x, y, tf));
			return true;
		}
		
		return false;
	}

	public void die() {
		this.living = false;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}
	
	
}
