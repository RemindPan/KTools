package com.jiangkang.ktools.audio;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiangkang on 2017/10/17.
 */

public class VoiceTemplate {

    private String voiceType;

    private String numString;

    private String prefix;

    private String suffix;

    private List<String> voiceList;

    public static VoiceTemplate defalut = new VoiceTemplate()
            .setPrefix("success");


    public VoiceTemplate() {

    }


    public String getPrefix() {
        return prefix;
    }

    public VoiceTemplate setPrefix(String prefix) {
        this.prefix = prefix;
        return this;
    }

    public String getSuffix() {
        return suffix;
    }

    public VoiceTemplate setSuffix(String suffix) {
        this.suffix = suffix;
        return this;
    }

    public String getVoiceType() {
        return voiceType;
    }

    public VoiceTemplate setVoiceType(String voiceType) {
        this.voiceType = voiceType;
        return this;
    }

    public String getNumString() {
        return numString;
    }

    public VoiceTemplate setNumString(String numString) {
        this.numString = numString;
        return this;
    }


    public List<String> gen() {
        return genVoiceList();
    }

    private List<String> createReadableNumList(String numString) {
        List<String> result = new ArrayList<>();
        if (!TextUtils.isEmpty(numString)) {
            int len = numString.length();
            for (int i = 0; i < len; i++) {
                if ('.' == numString.charAt(i)) {
                    result.add("dot");
                } else {
                    result.add(String.valueOf(numString.charAt(i)));
                }
            }
        }
        return result;
    }


    private List<String> genVoiceList() {
        List<String> result = new ArrayList<>();
        if (!TextUtils.isEmpty(prefix)) {
            result.add(prefix);
        }
        if (!TextUtils.isEmpty(numString)) {
            result.addAll(genReadableMoney(numString));
        }

        if (!TextUtils.isEmpty(suffix)) {
            result.add(suffix);
        }
        return result;
    }


    private List<String> genReadableMoney(String numString) {
        List<String> result = new ArrayList<>();
        if (!TextUtils.isEmpty(numString)) {
            if (numString.contains(".")) {
                String integerPart = numString.split("\\.")[0];
                String decimalPart = numString.split("\\.")[1];
                List<String> intList = readIntPart(integerPart);
                List<String> decimalList = readDecimalPart(decimalPart);
                result.addAll(intList);
                result.add("dot");
                result.addAll(decimalList);
            }else {
                //int
                result.addAll(genReadableMoney(numString));
            }
        }
        return result;
    }

    private List<String> readDecimalPart(String decimalPart) {
        List<String> result = new ArrayList<>();
        char[] chars = decimalPart.toCharArray();
        for (char ch : chars) {
            result.add(String.valueOf(ch));
        }
        return result;
    }


    private List<String> readIntPart(String integerPart) {
        List<String> result = new ArrayList<>();
        String intString = readIntPart(Integer.parseInt(integerPart));
        int len = intString.length();
        for (int i =0; i < len;i++){
            char current = intString.charAt(i);
            if (current == '拾'){
                result.add("ten");
            }else if (current == '佰'){
                result.add("hundred");
            }else if (current == '仟'){
                result.add("thousand");
            }else if (current == '万'){
                result.add("ten_thousand");
            }else if (current == '亿'){
                result.add("ten_million");
            }else {
                result.add(String.valueOf(current));
            }
        }
        return result;
    }



    private static final char [] ChineseNum ={'0','1','2','3','4','5','6','7','8','9'};
    private static final char [] ChineseUnit={'元','拾','佰','仟','万','拾','佰','仟','亿','拾','佰','仟'};

    /**
     * 返回关于钱的中文式大写数字,支仅持到亿
     * */
    public static String readIntPart(int moneyNum){
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
