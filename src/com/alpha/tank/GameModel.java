package com.alpha.tank;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameModel {
	
	private static final GameModel INSTANCE = new GameModel();

	Tank myTank = new Tank(500, 200, Dir.DOWN, Group.GOOD);
//	GameFactory gf = new DefaultFactory();

//	List<BaseBullet> bullets = new ArrayList<>();
//	List<Tank> tanks = new ArrayList<>();
//	public List<BaseExplode> exploads = new ArrayList<>();

	private List<GameObject> objects = new ArrayList<GameObject>();

	ColliderChain collider = new ColliderChain();

	public static GameModel getInstance() {
		return INSTANCE;
	}
	private GameModel() {
		int initTankCount = Integer.parseInt((String) PropertyMgr.get("initTankCount"));
		Random ran  = new Random();
		// 初始化敌方坦克
		for (int i = 0; i < initTankCount; i++) {
			int x = ran.nextInt(TankFrame.GAME_WIDTH);
			int y = ran.nextInt(TankFrame.GAME_HEIGHT);
			Dir dir = Dir.values()[ran.nextInt(4)];
			objects.add(new Tank(x, y, dir, Group.BAD));
		}
		
		// 初始化墙
		add(new Wall(150, 150, 200, 150));
		add(new Wall(550, 150, 200, 50));
		add(new Wall(300, 300, 50, 200));
		add(new Wall(550, 300, 50, 200));
	}

	public void add(GameObject go) {
		this.objects.add(go);
	}

	public void remove(GameObject go) {
		this.objects.remove(go);
	}

	public void paint(Graphics g) {
		// 画文字
		Color c = g.getColor();
		g.setColor(Color.WHITE);
		g.drawString("Objects Num : " + objects.size(), 10, 60);
//		g.drawString("  Tanks Num : " + tanks.size(), 10, 80);
//		g.drawString("  Bloom Num : " + exploads.size(), 10, 100);
		g.setColor(c);

		// 画 myTank
		myTank.paint(g);

		// 画 子弹
		// 这种方式会报错，迭代器方式 Iterator
//		for(Bullet b : bullets) {
//			b.paint(g);
//		} 
		// ---> 解释：
		/**
		 * 这种方式不会发生同步越界问题吗？ 不会 当进行 remove() 操作的时候，会同步的调整 size() 每次会重新计算 消除了子弹列表的内存泄漏问题
		 */
		for (int i = 0; i < objects.size(); i++) {
			objects.get(i).paint(g);
		}
//		// 另一种方式，使用同一种方式进行遍历和删除，需要将删除操作也放在这里
////		for(Iterator<Bullet> it = bullets.iterator(); it.hasNext(); ) {
////			Bullet b = it.next();
////			if(!b.live()) it.remove();
////			b.paint(g);
////		}
//		
//		// 画 敌方坦克
//		for(int i=0; i<tanks.size(); i++) {
//			tanks.get(i).paint(g);
//		}
//		
//		
		// 碰撞检测
		for (int i = 0; i < objects.size(); i++) {
			for (int j = i + 1; j < objects.size(); j++) {
//				objects.get(i).collideWith(tanks.get(j));
				GameObject o1 = objects.get(i);
				GameObject o2 = objects.get(j);
				if(!collider.collider(o1, o2)) {
					break;
				}
			}
		}
//		
//		// 爆炸效果
//		for(int i=0; i<exploads.size(); i++) {
//			exploads.get(i).paint(g);
//		}

	}
}
