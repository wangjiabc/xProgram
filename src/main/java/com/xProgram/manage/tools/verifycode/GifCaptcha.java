package com.xProgram.manage.tools.verifycode;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
/**
 * <p>Gif楠岃瘉鐮佺被</p>
 *
 * @author: wuhongjun
 * @version:1.0
 */
public class GifCaptcha extends Captcha
{
    public GifCaptcha()
    {
    }

    public GifCaptcha(int width,int height){
        this.width = width;
        this.height = height;
    }

    public GifCaptcha(int width,int height,int len){
        this(width,height);
        this.len = len;
    }

    public GifCaptcha(int width,int height,int len,Font font)
    {
        this(width,height,len);
        this.font = font;
    }

    @Override
    public void out(OutputStream os)
    {
        try
        {
            GifEncoder gifEncoder = new GifEncoder();   // gif缂栫爜绫伙紝杩欎釜鍒╃敤浜嗘磱浜哄啓鐨勭紪鐮佺被锛屾墍鏈夌被閮藉湪闄勪欢涓�
            //鐢熸垚�?�楃��?
            gifEncoder.start(os);
            gifEncoder.setQuality(180);
            gifEncoder.setDelay(100);
            gifEncoder.setRepeat(0);
            BufferedImage frame;
            char[] rands =alphas();
            Color fontcolor[]=new Color[len];
            for(int i=0;i<len;i++)
            {
                fontcolor[i]=new Color(20 + num(110), 20 + num(110), 20 + num(110));
            }
            for(int i=0;i<len;i++)
            {
                frame=graphicsImage(fontcolor, rands, i);
                gifEncoder.addFrame(frame);
                frame.flush();
            }
            gifEncoder.finish();
        }finally
        {
        	try {
				os.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }

    }

    /**
     * 鐢婚殢鏈虹爜鍥�
     * @param fontcolor 闅忔��?瀛椾綋棰滆壊
     * @param strs 瀛楃鏁扮粍
     * @param flag 閫忔槑搴︿娇鐢�
     * @return BufferedImage
     */
    private BufferedImage graphicsImage(Color[] fontcolor,char[] strs,int flag)
    {
        BufferedImage image = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);
        //鎴栧緱鍥惧舰涓婁笅鏂��?
        //Graphics2D g2d=image.createGraphics();
        Graphics2D g2d = (Graphics2D)image.getGraphics();
        //鍒╃敤鎸囧畾棰滆壊濉厖鑳屾��?
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, width, height);
        AlphaComposite ac3;
        int h  = height - ((height - font.getSize()) >>1) ;
        int w = width/len;
        g2d.setFont(font);
        for(int i=0;i<len;i++)
        {
            ac3 = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, getAlpha(flag, i));
            g2d.setComposite(ac3);
            g2d.setColor(fontcolor[i]);
            g2d.drawOval(num(width), num(height), 5+num(10), 5+num(10));
            g2d.drawString(strs[i]+"", (width-(len-i)*w)+(w-font.getSize())+1, h-4);
        }
        g2d.dispose();
        return image;
    }

    /**
     * 鑾峰彇閫忔槑搴�,浠�0鍒�1,鑷姩璁＄畻姝ラ��?
     * @return float 閫忔槑搴��?
     */
    private float getAlpha(int i,int j)
    {
        int num = i+j;
        float r = (float)1/len,s = (len+1) * r;
        return num > len ? (num *r - s) : num * r;
    }

}
