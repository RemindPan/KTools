package com.jiangkang.ktools.audio;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiangkang on 2017/10/17.
 * 处理金额转换
 */

public class VoiceHelper {

    private static String[] units = new String[]{
      "shi","bai","qian","wan","yi"
    };

    private static String[] nums = new String[]{
      "0","1","2","3","4","5","6","7","8","9",
    };

    private static String dot = "dot";


    /**
     * @param money the money string
     *
     * @return true if the money string is valid, otherwise return false
     */
    private static boolean isMoneyNumValue(String money) {
        int length = money.length();
        if (length < 1){
            return false;
        }
        for (int i = 0; i < length; i++) {
            if (!Character.isDigit(money.charAt(i)) && !".".equals(money.charAt(i))) {
                return false;
            }
        }
        if (money.split("\\.")[0].length() > 12) {
            return false;
        }
        if (money.length() > 2 && "0".equals(money.charAt(0)) && !".".equals(money.charAt(1))) {
            return false;
        }

        if (money.startsWith(".") || money.endsWith(".")){
            return false;
        }
        return true;
    }


//    public static List<String> getReadableMoney(String numString){
//        List<String> result = new ArrayList<>();
//        if (isMoneyNumValue(numString)){
//           if (numString.contains(".") && !numString.endsWith(".")){
//               String intNum = numString.split(".")[0];
//               String decimalNum = numString.split(".")[1];
//               List<String>
//           }
//        }
//        return null;
//    }



}
