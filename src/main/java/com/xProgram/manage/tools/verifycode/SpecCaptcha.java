package com.xProgram.manage.tools.verifycode;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;
/**
 * <p>png鏍煎紡楠岃瘉鐮�</p>
 *
 * @author: wuhongjun
 * @version:1.0
 */
public class SpecCaptcha extends Captcha
{
    public SpecCaptcha()
    {
    }
    public SpecCaptcha(int width, int height)
    {
        this.width = width;
        this.height = height;
    }
    public SpecCaptcha(int width, int height, int len){
        this(width,height);
        this.len = len;
    }
    public SpecCaptcha(int width, int height, int len, Font font){
        this(width,height,len);
        this.font = font;
    }
    /**
     * 鐢熸垚楠岃瘉鐮�
     * @throws java.io.IOException IO寮傚��?
     */
    @Override
    public void out(OutputStream out){
        graphicsImage(alphas(), out);
    }

    /**
     * 鐢婚殢鏈虹爜鍥�
     * @param strs 鏂囨��?
     * @param out 杈撳嚭娴��?
     */
    private boolean graphicsImage(char[] strs, OutputStream out){
        boolean ok = false;
        try
        {
            BufferedImage bi = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
            Graphics2D g = (Graphics2D)bi.getGraphics();
            AlphaComposite ac3;
            Color color ;
            int len = strs.length;
            g.setColor(Color.WHITE);
            g.fillRect(0,0,width,height);
            // 闅忔��?鐢诲共鎵扮殑铔嬭��?
            for(int i=0;i<15;i++){
                color = color(150, 250);
                g.setColor(color);
                g.drawOval(num(width), num(height), 5+num(10), 5+num(10));// 鐢昏泲铔嬶紝鏈夎泲鐨勭敓娲绘墠绮惧僵
                color = null;
            }
            g.setFont(font);
            int h  = height - ((height - font.getSize()) >>1),
                w = width/len,
                size = w-font.getSize()+1;
            /* 鐢诲瓧绗︿覆 */
            for(int i=0;i<len;i++)
            {
                ac3 = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f);// 鎸囧畾閫忔槑搴�
                g.setComposite(ac3);
                color = new Color(20 + num(110), 20 + num(110), 20 + num(110));// 瀵规瘡涓瓧绗﹂兘鐢ㄩ殢鏈洪鑹��?
                g.setColor(color);
                g.drawString(strs[i]+"",(width-(len-i)*w)+size, h-4);
                color = null;
                ac3 = null;
            }
            ImageIO.write(bi, "png", out);
            out.flush();
            ok = true;
        }catch (IOException e){
            ok = false;
        }finally
        {
        	try {
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        return ok;
    }
}