package com.example.q.quickindextest;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * Created by YQ on 2016/7/30.
 */
public class PinYinUtils {
    public static String getPinyin(String word) {
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.UPPERCASE);//大写
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);//没有音调
        StringBuilder sb = new StringBuilder();
        char[] charArray = word.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            char c = charArray[i];
            if (Character.isWhitespace(c)) {
                continue;//如果是空格，跳过
            }
            if (c < 128) {
                //肯定不是汉字,直接添加到后面
                sb.append(c);
            } else {
                String s;
                try {
                    s = PinyinHelper.toHanyuPinyinStringArray(c, format)[0];
                    sb.append(s);//拼接起来
                } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
                    badHanyuPinyinOutputFormatCombination.printStackTrace();
                }
            }

        }
        //通过char得到String【】,多音字

        //15310492603

        return sb.toString();
    }
}
