package com.jiangkang.ktools.audio;

/**
 * Created by jiangkang on 2017/10/17.
 */

public class MoneyConvert {


    private static final char [] ChineseNum ={'0','1','2','3','4','5','6','7','8','9'};
    private static final char [] ChineseUnit={'元','拾','佰','仟','万','拾','佰','仟','亿','拾','佰','仟'};

    /**
     * 返回关于钱的中文式大写数字,支仅持到亿
     * */
    public static String arabNumToChineseRMB(int moneyNum){
        String res="";
        int i=0;
        if(moneyNum==0)
            return "0元";
        while(moneyNum>0){
            res=ChineseUnit[i++]+res;
            res=ChineseNum[moneyNum%10]+res;
            moneyNum/=10;
        }
        return res.replaceAll("0[拾佰仟]", "0")
                .replaceAll("0+亿", "亿").replaceAll("0+万", "万")
                .replaceAll("0+元", "元").replaceAll("0+", "0")
                .replace("元","");
    }





}
