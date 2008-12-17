/*
 * Copyright (c) 2006 Microbrain Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *        http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.microbrain.cosmos.web.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;

import org.apache.commons.logging.Log;

import com.microbrain.cosmos.core.log.CosmosLogFactory;

/**
 * <p>
 * 随机图生成工具。
 * </p>
 * 
 * @author Richard Sun (Richard.SunRui@gmail.com)
 * @version 1.0, 08/12/10
 * @see java.awt.image.BufferedImage
 * @since CFDK 1.0
 */
public class ImageUtils {

	/**
	 * 私有化构造函数。
	 */
	private ImageUtils() {
	}

	/**
	 * 日志记录类。
	 */
	private static final Log log = CosmosLogFactory.getLog();

	/**
	 * 默认宽。
	 */
	private static final int WIDTH = 50;

	/**
	 * 默认高。
	 */
	private static final int HEIGHT = 20;

	/**
	 * 获得随机颜色。
	 * 
	 * @param fc
	 *            颜色范围。
	 * @param bc
	 *            颜色范围。
	 * @return 随机颜色对象。
	 */
	private static Color getRandColor(int fc, int bc) {
		Random random = new Random();
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}

	/**
	 * 产生随机图。
	 * 
	 * @param width
	 *            随机图宽。
	 * @param height
	 *            随机图高。
	 * @param os
	 *            输出流。
	 * @return 随机图上的文字。
	 * @throws IOException
	 *             IO读写异常。
	 */
	public static String random(int width, int height, OutputStream os)
			throws IOException {
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();
		Random random = new Random();
		g.setColor(getRandColor(200, 250));
		g.fillRect(0, 0, width, height);

		g.setFont(new Font("Dialog", Font.BOLD, 16));

		g.setColor(getRandColor(160, 200));
		for (int i = 0; i < 155; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int xl = random.nextInt(12);
			int yl = random.nextInt(12);
			g.drawLine(x, y, x + xl, y + yl);
		}

		StringBuilder sRand = new StringBuilder();
		String rand = null;
		for (int i = 0; i < 4; i++) {
			int x = random.nextInt(2);
			if (x == 0) {
				int j = random.nextInt(26);
				int k = (0 == random.nextInt(2)) ? (j + 65) : (j + 97);
				if (k == 111 || k == 108 || k == 73 || k == 79) {
					i--;
					continue;
				}
				char c = (char) k;
				rand = String.valueOf(c);
			} else {
				rand = String.valueOf(random.nextInt(10));
			}
			sRand.append(rand.toUpperCase());
			g.setColor(new Color(20 + random.nextInt(110), 20 + random
					.nextInt(110), 20 + random.nextInt(110)));
			g.drawString(rand, 10 * i + 6, 16 + random.nextInt(3));
		}

		g.dispose();
		try {
			ImageIO.write(image, "JPEG", os);
		} catch (IOException e) {
			log.error("Exception in generate image.", e);
			throw e;
		}

		return sRand.toString();
	}

	/**
	 * 产生默认大小的随机图。
	 * 
	 * @param os
	 *            输出流。
	 * @return 随机图上的文字。
	 * @throws IOException
	 *             IO异常。
	 */
	public static String random(OutputStream os) throws IOException {
		return random(WIDTH, HEIGHT, os);
	}

}
