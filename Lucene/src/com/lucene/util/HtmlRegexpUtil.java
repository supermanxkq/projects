package com.lucene.util;
import java.util.regex.Matcher;   
import java.util.regex.Pattern;   
  
/**  
 * <p>  
 * Title: HTML相关的正则表达式工具类  
 * </p>  
 * <p>  
 * Description: 包括过滤HTML标记，转换HTML标记，替换特定HTML标记  
 * </p>  
 * <p>  
 * Copyright: Copyright (c) 2006  
 * </p>  
 *   
 * @author hejian  
 * @version 1.0  
 * @createtime 2006-10-16  
 */  
  
public class HtmlRegexpUtil {   
    private final static String regxpForHtml = "<([^>]*)>"; // 过滤所有以<开头以>结尾的标签   
  
    private final static String regxpForImgTag = "<\\s*img\\s+([^>]*)\\s*>"; // 找出IMG标签   
  
    private final static String regxpForImaTagSrcAttrib = "src=\"([^\"]+)\""; // 找出IMG标签的SRC属性   
  
    /**  
     *   
     */  
    public HtmlRegexpUtil() {   
        // TODO Auto-generated constructor stub   
    }   
  
    /**  
     *   
     * 基本功能：替换标记以正常显示  
     * <p>  
     *   
     * @param input  
     * @return String  
     */  
    public String replaceTag(String input) {   
        if (!hasSpecialChars(input)) {   
            return input;   
        }   
        StringBuffer filtered = new StringBuffer(input.length());   
        char c;   
        for (int i = 0; i <= input.length() - 1; i++) {   
            c = input.charAt(i);   
            switch (c) {   
            case '<':   
                filtered.append("&lt;");   
                break;   
            case '>':   
                filtered.append("&gt;");   
                break;   
            case '"':   
                filtered.append("&quot;");   
                break;   
            case '&':   
                filtered.append("&amp;");   
                break;   
            default:   
                filtered.append(c);   
            }   
  
        }   
        return (filtered.toString());   
    }   
  
    /**  
     *   
     * 基本功能：判断标记是否存在  
     * <p>  
     *   
     * @param input  
     * @return boolean  
     */  
    public boolean hasSpecialChars(String input) {   
        boolean flag = false;   
        if ((input != null) && (input.length() > 0)) {   
            char c;   
            for (int i = 0; i <= input.length() - 1; i++) {   
                c = input.charAt(i);   
                switch (c) {   
                case '>':   
                    flag = true;   
                    break;   
                case '<':   
                    flag = true;   
                    break;   
                case '"':   
                    flag = true;   
                    break;   
                case '&':   
                    flag = true;   
                    break;   
                }   
            }   
        }   
        return flag;   
    }   
  
    /**  
     *   
     * 基本功能：过滤所有以"<"开头以">"结尾的标签  
     * <p>  
     *   
     * @param str  
     * @return String  
     */  
    public static String filterHtml(String str) {   
        Pattern pattern = Pattern.compile(regxpForHtml);   
        Matcher matcher = pattern.matcher(str);   
        StringBuffer sb = new StringBuffer();   
        boolean result1 = matcher.find();   
        while (result1) {   
            matcher.appendReplacement(sb, "");   
            result1 = matcher.find();   
        }   
        matcher.appendTail(sb);   
        return sb.toString();   
    }   
  
    /**  
     *   
     * 基本功能：过滤指定标签  
     * <p>  
     *   
     * @param str  
     * @param tag  
     *            指定标签  
     * @return String  
     */  
    public static String fiterHtmlTag(String str, String tag) {   
        String regxp = "<\\s*" + tag + "\\s+([^>]*)\\s*>";   
        Pattern pattern = Pattern.compile(regxp);   
        Matcher matcher = pattern.matcher(str);   
        StringBuffer sb = new StringBuffer();   
        boolean result1 = matcher.find();   
        while (result1) {   
            matcher.appendReplacement(sb, "");   
            result1 = matcher.find();   
        }   
        matcher.appendTail(sb);   
        return sb.toString();   
    }   
  
    /**  
     *   
     * 基本功能：替换指定的标签  
     * <p>  
     *   
     * @param str  
     * @param beforeTag  
     *            要替换的标签  
     * @param tagAttrib  
     *            要替换的标签属性值  
     * @param startTag  
     *            新标签开始标记  
     * @param endTag  
     *            新标签结束标记  
     * @return String  
     * @如：替换img标签的src属性值为[img]属性值[/img]  
     */  
    public static String replaceHtmlTag(String str, String beforeTag,   
            String tagAttrib, String startTag, String endTag) {   
        String regxpForTag = "<\\s*" + beforeTag + "\\s+([^>]*)\\s*>";   
        String regxpForTagAttrib = tagAttrib + "=\"([^\"]+)\"";   
        Pattern patternForTag = Pattern.compile(regxpForTag);   
        Pattern patternForAttrib = Pattern.compile(regxpForTagAttrib);   
        Matcher matcherForTag = patternForTag.matcher(str);   
        StringBuffer sb = new StringBuffer();   
        boolean result = matcherForTag.find();   
        while (result) {   
            StringBuffer sbreplace = new StringBuffer();   
            Matcher matcherForAttrib = patternForAttrib.matcher(matcherForTag   
                    .group(1));   
            if (matcherForAttrib.find()) {   
                matcherForAttrib.appendReplacement(sbreplace, startTag   
                        + matcherForAttrib.group(1) + endTag);   
            }   
            matcherForTag.appendReplacement(sb, sbreplace.toString());   
            result = matcherForTag.find();   
        }   
        matcherForTag.appendTail(sb);   
        return sb.toString();   
    }   
    
    public static void main(String[] args) {
		System.out.println(new HtmlRegexpUtil().filterHtml("<p>最近老是睡不着，写点东西记录一下此刻的心情，不是抱怨，也不是什么惊天动地豪言壮语。生活中一些事情吧，说了矫情，不说呢心里又觉得憋屈。放下所有思绪，心情，静静的记录一下。</p><p>作为一个刚步入社会的我，这一年让我改变了许多。时间是个很无情的东西，不管你怎么样，它都是那样的残酷。它告诉我，你应该从一个时期过度到另一个时期了！就像久在温暖的鸟巢第一次要飞翔的小鸟，就像刚学走路的孩子失去了父母的双手，就想小老虎要第一次去捕猎了。这种过度是艰难的，因为不知道会发生什么，也许走着摔倒，也许飞的不怎么高。但不管怎么样，这是我们必须要走的路！大学毕业后在北京闯荡，不问家里要钱了，也不好意思要了。社会这堂课学费太贵，这一年学费交了不少。自己租了房子，锅碗瓢盆柴米油盐酱醋茶都得考虑，吃着自己做的黑暗料理，深刻的感觉到了生活不易，也体会到了父母的不容易，辛辛苦苦把我拉扯大，虽然是个男生吧，但也有一点依赖的感觉。虽然做饭不易，但在北京这是其中一个可以觉得幸福的事情了，是的，我认为做饭会给人带来幸福感。搞砸了的不算，哈哈哈哈。可以请朋友一起来吃饭，然后你剥蒜我切葱，那种感觉很不错。虽然现在还经常搞砸，但是那种幸福感一直存在的，脱离了一整天的工作，买点菜，噼里啪啦做些黑暗料理，嗯。。。挺好的。</p><p>孤独是最大的敌人，如果一天什么都没有做，心里总会惴惴不安，孤独瞬间淹没了自己，那种感觉让人无法呼吸，找些视频看吧，郭德纲相声，搞笑电影，综艺节目，鬼片，一般都是看一半就睡着了，不感兴趣，因为一个人吧。笑完了，总是那么不真实，不痛快，不踏实，就像在水蒸气上取暖，热的快，凉的也快。</p><p>先写到这里，手机打字好困。。。。。。</p><p></p>"));
	}
}  
