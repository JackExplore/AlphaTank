package com.alpha.test;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.junit.jupiter.api.Test;

class ImageTest {

	@Test
	void test() throws IOException {
		
		/**
		 * 这种方式的局限性，文件路径固定 ?
		 */
//		BufferedImage image = ImageIO.read(new File("D:\\IdeaProjects\\tank.jpg"));
//		assertNotNull(image);

		/**
		 * 获取类加载器路径，然后获取资源文件方式，可移植          OK  !
		 */
		BufferedImage image = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("images/tankD.gif"));
		assertNotNull(image);
		
		
		//		fail("Not yet implemented");
	}

}
