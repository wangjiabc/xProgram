package com.xProgram.manage.tools.verifycode;

import java.util.Random;

/**
 * <p>闅忔��?宸ュ叿绫��?</p>
 *
 * @author: wuhongjun
 * @version:1.0
 */
public class Randoms
{
    private static final Random RANDOM = new Random();
    //瀹氫箟楠岃瘉鐮佸瓧绗��?.鍘婚櫎浜哋鍜孖绛夊鏄撴贩娣嗙殑瀛楁��?
    public static final char ALPHA[]={'A','B','C','D','E','F','G','H','G','K','M','N','P','Q','R','S','T','U','V','W','X','Y','Z'
            ,'a','b','c','d','e','f','g','h','i','j','k','m','n','p','q','r','s','t','u','v','w','x','y','z','2','3','4','5','6','7','8','9'};

    /**
     * 浜х敓涓や釜鏁颁箣闂寸殑闅忔満鏁�
     * @param min 灏忔��?
     * @param max 姣攎in澶х殑鏁�
     * @return int 闅忔��?鏁板��?
     */
    public static int num(int min, int max)
    {
        return min + RANDOM.nextInt(max - min);
    }

    /**
     * 浜х敓0--num鐨勯殢鏈烘暟,涓嶅寘鎷琻um
     * @param num 鏁板��?
     * @return int 闅忔��?鏁板��?
     */
    public static int num(int num)
    {
        return RANDOM.nextInt(num);
    }

    public static char alpha()
    {
        return ALPHA[num(0, ALPHA.length)];
    }
}