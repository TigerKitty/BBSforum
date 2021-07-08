package cn.edu.guet.util;

import com.github.binarywang.java.emoji.EmojiConverter;

public class EmojiChange {
    private static EmojiConverter emojiConverter = EmojiConverter.getInstance();

    // 将表情编码转换成可读的表情字符
    public static String emojiConverterUnicodeStr(String emojiStr){
//        System.out.println("到了EmojiChange的emojiConverterUnicodeStr中！");
        String result = emojiConverter.toUnicode(emojiStr);
        return result;
    }

    // 将带有表情的字符转换为表情编码（存入数据库）
    public static String emojiConverterToAlias(String str){
//        System.out.println("到了EmojiChange的emojiConverterToAlias中！");
        String result=emojiConverter.toAlias(str);
        return result;

    }
}
