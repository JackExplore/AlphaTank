package com.alpha.tank;

public class T {

	public static void main(String[] args) throws InterruptedException {
	
		TankFrame tf = new TankFrame();
		
		while(true) {
			Thread.sleep(50);
			tf.repaint();			// 每隔 50ms 刷新窗口
		}
		
	}

}
