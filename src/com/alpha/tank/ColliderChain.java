package com.alpha.tank;

import java.util.LinkedList;
import java.util.List;

/**
 *  责任链模式
 * @author Administrator
 *
 */
public class ColliderChain implements Collider{

	private List<Collider> colliders = new LinkedList<>();
	
	public ColliderChain(){
		
//		add(new BulletTankCollider());
//		add(new TankTankCollider());
		
		String[] colliders = ((String)PropertyMgr.get("colliders")).split(",");
		for(String name : colliders) {
			try {
				add((Collider)Class.forName(name).newInstance());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public void add(Collider c) {
		colliders.add(c);
	}

	public boolean collider(GameObject o1, GameObject o2) {
		
		for(int i=0; i<colliders.size(); i++) {
			if(!colliders.get(i).collider(o1, o2)) {
				return false;
			}
		}
		return true;
		
	}
}
