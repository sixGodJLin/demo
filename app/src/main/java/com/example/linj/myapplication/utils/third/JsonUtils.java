package com.example.linj.myapplication.utils.third;

/**
 * @author JLin
 * @date 2019/11/14
 * @describe response 输出
 */
public class JsonUtils {
    private static String getLevelStr(int mLevel) {
        StringBuilder levelStr = new StringBuilder();
        for (int level = 0; level < mLevel; level++) {
            levelStr.append("\t");
        }
        return levelStr.toString();
    }

    public static String jsonHandle(String s) {
        int level = 0;
        StringBuilder json = new StringBuilder();
        for (int index = 0; index < s.length(); index++) {
            char c = s.charAt(index);
            if (level > 0 && '\n' == json.charAt(json.length() - 1)) {
                json.append(getLevelStr(level));
            }
            switch (c) {
                case '{':
                case '[':
                    json.append(c).append("\n");
                    level++;
                    break;
                case ',':
                    json.append(c).append("\n");
                    break;
                case '}':
                case ']':
                    json.append("\n");
                    level--;
                    json.append(getLevelStr(level));
                    json.append(c);
                    break;
                default:
                    json.append(c);
                    break;
            }
        }
        return json.toString();
    }
}