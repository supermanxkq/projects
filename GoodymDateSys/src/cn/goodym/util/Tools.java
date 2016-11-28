package cn.goodym.util;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.oro.text.regex.PatternCompiler;
import org.apache.oro.text.regex.PatternMatcher;
import org.apache.oro.text.regex.Perl5Compiler;
import org.apache.oro.text.regex.Perl5Matcher;




/** 锟斤拷锟斤拷锟斤拷 */
public class Tools {
	
	//锟斤拷锟斤拷锟斤拷锟节革拷式
	private static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	private static SimpleDateFormat pic_df = new SimpleDateFormat("yyyyMM");//锟斤拷锟节革拷式锟斤拷锟斤拷-锟斤拷
	public static SimpleDateFormat df_ymdhms = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
	//锟斤拷锟斤拷锟叫癸拷幕锟斤拷腋锟绞?
	private static NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.CHINA);	
	
	
	/**锟叫讹拷锟角凤拷锟斤拷锟斤拷效URL锟斤拷锟斤拷**/
	public boolean IsURL(String url){
		
		String strRegex = "^((https|http|ftp|rtsp|mms)?://)"  
				        + "?(([0-9a-zA-Z_!~*'().&=+$%-]+: )?[0-9a-zA-Z_!~*'().&=+$%-]+@)?" //ftp锟斤拷user@  
				        + "(([0-9]{1,3}\\.){3}[0-9]{1,3}" // IP锟斤拷式锟斤拷URL- 199.194.52.184  
				        + "|" // 锟斤拷锟斤拷IP锟斤拷DOMAIN锟斤拷锟斤拷锟斤拷 
				        + "([0-9a-zA-Z_!~*'()-]+\\.)*" // 锟斤拷锟斤拷- www.  
				        + "([0-9a-zA-Z][0-9a-zA-Z-]{0,61})?[0-9a-zA-Z]\\." // 锟斤拷锟斤拷锟斤拷锟斤拷  
				        + "[a-zA-Z]{2,6})" // first level domain- .com or .museum  
				        + "(:[0-9]{1,4})?" // 锟剿匡拷- :80  
				        + "((/?)|" // a slash isn't required if there is no file name  
				        + "(/[0-9a-zA-Z_!~*'().;?:@&=+$,%#-]+)+/?)$"; 
        boolean re=url.matches(strRegex);
        if (re){ 
            return (true);  
        }else{  
            return (false);  
        } 
	}
	/**小锟斤拷锟?锟斤拷锟斤拷:锟斤拷式锟斤拷小锟斤拷取2位小锟斤拷,锟斤拷1锟斤拷头时锟斤拷锟斤拷1.0*/
	public float DfDecimal(float num){
		float result=0;
		java.text.DecimalFormat df=new java.text.DecimalFormat("#.##");   
		result=new Float(df.format(num));
		return result;
	}
	/** 
	* 锟街斤拷转锟戒换锟斤拷锟街凤拷
	* @param b byte[] 
	* @return String 
	*/ 
	public static String Bytes2HexString(byte[] b) { 
		String ret = ""; 
		for (int i = 0; i < b.length; i++) { 
			String hex = Integer.toHexString(b[i] & 0xFF); 
			if (hex.length() == 1) { 
				hex = '0' + hex; 
			} 
			ret += hex.toUpperCase(); 
		} 
		return ret; 
	} 
	/**************************************
	 *  锟斤拷锟斤拷说锟斤拷锟斤拷锟斤拷锟斤拷瞻锟阶拷锟?
	 *  莫锟斤拷
     *  2012-03-02
     ***************************************/ 
	public static String StrBlank(String HtmlStr){
		try {
			byte[] space = new byte[]{(byte) 0xc2,(byte) 0xa0};
			String UTFSpace = Bytes2HexString(space);
	        UTFSpace = new String(UTFSpace.getBytes("iso88591"), "UTF-8");
	        HtmlStr = HtmlStr.replaceAll(UTFSpace,"&nbsp;");
		 } catch (Exception e) {
	         e.printStackTrace();
	     }
		 return HtmlStr;
	}
	/**************************************
	 *  锟斤拷锟斤拷说锟斤拷锟斤拷去锟斤拷锟街凤拷目瞻锟?
	 *  莫锟斤拷
     *  2012-03-02
	 **************************************/
	public static String StrTrim(String content){
		return content.replace("/(^\\s*)|(\\s*$)/g", "");
	}
	/**************************************
	 *  锟斤拷锟斤拷说锟斤拷锟斤拷锟斤拷锟斤拷锟街凤拷s锟斤拷锟斤拷取s锟斤拷锟斤拷某锟斤拷锟斤拷雍锟斤拷锟斤拷锟斤拷锟斤拷锟?
	 *  莫锟斤拷
     *  2012-03-02
	 **************************************/
	public static String ReplaceUrl(String content,String replaceText){
		
		String tempStr="",lastContent="";
		lastContent = content;
        String regex="<a.*?/a>";        
        Pattern pt=Pattern.compile(regex);
        Matcher mt=pt.matcher(content);
        while(mt.find())
        {
        	tempStr=mt.group().trim();
        	if(tempStr.indexOf(replaceText)!=-1){
        		lastContent=lastContent.replaceAll(tempStr, replaceText);
        	}
        }
        return lastContent;
	}
	
	/**匹锟斤拷图片锟斤拷锟斤拷图片锟截碉拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷式 <img.*src=(.*?)[^>]*?>src=\"?(.*?)(\"|>|\\s+) **/
	public static List<String> getImgStr(String htmlStr){     
	     String img="";     
	     Pattern p_image;     
	     Matcher m_image;     
	     List<String> pics = new ArrayList<String>();  
	  
	     String regEx_img = "<img.*src=(.*?)[^>]*?>"; //图片锟斤拷锟接碉拷址     
	     p_image = Pattern.compile(regEx_img,Pattern.CASE_INSENSITIVE);     
	     m_image = p_image.matcher(htmlStr);   
	     while(m_image.find()){     
	         img = img + "," + m_image.group();     
	         Matcher m  = Pattern.compile("src=\"?(.*?)(\"|>|\\s+)").matcher(img); //匹锟斤拷src  
	         while(m.find()){  
	            pics.add(m.group(1));  
	         }  
	     }     
	     return pics;     
	 }    
	/**锟叫讹拷锟侥憋拷锟角凤拷锟斤拷谐锟斤拷锟斤拷锟?*/
	public static boolean isAtag(String str){
		 boolean isA=false;
		 Pattern pattern=Pattern.compile("<a\\s(?:\\s*\\w*?\\s*=\\s*\".+?\")*(?:\\s*href\\s*=\\s*\".+?\")(?:\\s*\\w*?\\s*=\\s*\".+?\")*\\s*>([\\s\\S]*?)<\\/a>");  
		 Matcher matcher=pattern.matcher(str);  
		 if(matcher.find()){  
		    isA=true;
		 }  
		 return isA;
	}	
	
	/** 取锟斤拷指锟斤拷图片锟侥匡拷锟斤拷锟竭讹拷 */
	public static Map getPicWidthHeight(String filename){
		Map map = new HashMap();
		try {
			BufferedImage sourceImg = javax.imageio.ImageIO.read(new FileInputStream(filename));
			map.put("width", sourceImg.getWidth());
			map.put("height", sourceImg.getHeight());
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}	
	}

	/** 锟角凤拷锟斤拷删锟斤拷锟斤拷锟脚碉拷权锟斤拷 */ 
//	public static boolean isHaveDelete(AppAdmin admin) {
//		if(admin==null){
//			return false;
//		}else{
//			if (admin.getHaveDelete()!=null && admin.getHaveDelete().intValue()==1)
//				return true;
//			else {
//				return false;		
//			}
//		}
//	}
//
//	/** 锟角凤拷为锟斤拷站锟斤拷锟斤拷锟斤拷锟斤拷员 */ 
//	public static boolean isSuperAdmin(AppAdmin admin) {
//		if(admin==null){
//			return false;
//		}else{
//			if (admin.getPrivilege().substring(0, 1).equals("1"))
//				return true;
//			else {
//				return false;		
//			}
//		}
//	}
//	
//	/** 锟角凤拷为锟斤拷站锟斤拷锟斤拷锟斤拷锟斤拷员 */ 
//	public static boolean isSiteAdmin(AppAdmin admin) {
//		if(admin==null){
//			return false;
//		}else{
//			if (admin.getPrivilege().substring(1, 2).equals("1"))
//				return true;
//			else {
//				return false;		
//			}
//		}
//	}	
//	/** 锟角凤拷没锟斤拷指锟斤拷锟侥诧拷锟斤拷权锟斤拷 */ 
//	public static boolean isDisable(AppAdmin admin, int option) {
//		if(admin==null){
//			return true;
//		}else{
//			if (admin.getPrivilege().substring(0, 1).equals("1"))
//				return false;
//			else {
//				if (admin.getPrivilege().substring(option - 1, option).equals("1"))
//					return false;
//				else
//					return true;
//			}			
//		}
//	}
//	
//	/** 锟角凤拷拥锟斤拷指锟斤拷锟侥诧拷锟斤拷权锟斤拷 */ 
//	public static boolean isEnable(AppAdmin admin, int option) {
//		if(admin==null){
//			return false;
//		}else{
//			if (admin.getPrivilege().substring(0, 1).equals("1"))
//				return true;
//			else {
//				if (admin.getPrivilege().substring(option - 1, option).equals("1"))
//					return true;
//				else
//					return false;
//			}			
//		}
//	}
	
	/** 取锟斤拷锟斤拷锟斤拷锟斤拷募锟斤拷锟?*/
	public synchronized static String getRndFilename(){
		return String.valueOf(System.currentTimeMillis()+(int)(Math.random()*10000+1));
	}
	
	
	/** 取锟斤拷指锟斤拷锟侥硷拷锟斤拷锟侥硷拷锟斤拷展锟斤拷 */
	public synchronized static String getFileExtName(String filename){
		int p = filename.lastIndexOf(".");
		return filename.substring(p);
	}
	/**锟斤拷锟経RL取锟斤拷锟斤拷展锟斤拷*/
	public synchronized static String getExtendName(String url){
		String result=null;
		String strUrl=url.trim();
		int pointNum=strUrl.lastIndexOf(".");
		String tempstr=strUrl.substring(pointNum+1, strUrl.length());
		result=tempstr;
		return result;
	}
	/** 锟斤拷证锟较达拷锟侥硷拷锟斤拷锟斤拷锟斤拷锟角凤拷戏锟?fileType:1-图片 2-锟斤拷频 3-锟斤拷锟斤拷锟斤拷锟斤拷 4-锟斤拷锟斤拷swf锟斤拷锟斤拷*/
	public synchronized static boolean isEnableUploadType(int fileType,String filename){
		String enableExtNames = null;
		int p = filename.indexOf(".");
		String fileExtName = filename.substring(p).toLowerCase();
		if (fileType==1){//图片锟侥硷拷锟斤拷锟斤拷
			enableExtNames = ".jpg,.gif,.png";
		}else if (fileType==2){//锟斤拷频锟侥硷拷锟斤拷锟斤拷
			enableExtNames = ".flv";
		}else if (fileType==3){//锟斤拷锟斤拷锟斤拷锟斤拷
			enableExtNames = ".rar,.zip,.txt,.doc,.jpg,.gif,.png,.xsl";	
		}else if (fileType==4){//SWF锟斤拷锟斤拷锟斤拷锟斤拷
			enableExtNames = ".swf";			
		}
		if (enableExtNames!=null){
			if (enableExtNames.indexOf(fileExtName)!=-1)return true;
			else return false;			
		}else{
			return true;
		}

	}	
	
	/** HTML锟斤拷锟斤拷锟紼scape锟斤拷锟?锟斤拷--锟斤拷锟斤拷(锟斤拷锟斤拷ckEditor锟斤拷锟脚编辑锟斤拷) */
	public static String  escape(String src){
		return StringEscapeUtils.escapeXml(src);
	}
	
	/** HTML锟斤拷锟斤拷锟経nEscape锟斤拷锟?锟斤拷--锟斤拷锟斤拷(锟斤拷锟斤拷ckEditor锟斤拷锟脚编辑锟斤拷) */
	public static String  unescape(String src){
		return StringEscapeUtils.unescapeHtml(StringEscapeUtils.unescapeXml(src));
	}
	
	/** HTML锟斤拷锟斤拷锟紼scape锟斤拷锟?锟斤拷 --锟斤拷锟斤拷(锟斤拷锟斤拷fckEditor锟斤拷锟脚编辑锟斤拷)*/
	public static String  escape2(String src){
		int i;
		char j;
		StringBuffer tmp = new StringBuffer();
		tmp.ensureCapacity(src.length()*6);
		for (i=0;i<src.length();i++){
			j = src.charAt(i);
			if (Character.isDigit(j) || Character.isLowerCase(j) || Character.isUpperCase(j)) 
				tmp.append(j);
			else if(j<256){
				tmp.append( "%" );
				if (j<16)tmp.append("0");
				tmp.append( Integer.toString(j,16));
			}else{
				tmp.append("%u");
				tmp.append(Integer.toString(j,16));
			}
		}
		return tmp.toString();
	}
	
	/** HTML锟斤拷锟斤拷锟経nEscape锟斤拷锟?锟斤拷--锟斤拷锟斤拷(锟斤拷锟斤拷fckEditor锟斤拷锟脚编辑锟斤拷) */
	public static String  unescape2(String src){
		StringBuffer tmp = new StringBuffer();
		tmp.ensureCapacity(src.length());
		int lastPos=0,pos=0;
		char ch;
		while(lastPos<src.length()){
			pos = src.indexOf("%",lastPos);
			if (pos == lastPos){
				if (src.charAt(pos+1)=='u'){
					ch = (char)Integer.parseInt(src.substring(pos+2,pos+6),16);
					tmp.append(ch);
					lastPos = pos+6;
				}else{
					ch = (char)Integer.parseInt(src.substring(pos+1,pos+3),16);
					tmp.append(ch);
					lastPos = pos+3;
				}
			}else{
				if (pos == -1){
					tmp.append(src.substring(lastPos));
					lastPos=src.length();
				}else{
					tmp.append(src.substring(lastPos,pos));
					lastPos=pos;
				}
			}
		}
		return tmp.toString();
	}
	
	/** 为锟皆讹拷锟脚分革拷锟斤拷锟街凤拷锟矫匡拷锟斤拷锟皆拷锟斤拷锟斤拷锟斤拷,锟斤拷:aa,bb-->'aa','bb' */
	public static String formatString(String src){
		StringBuffer result = new StringBuffer();
		result.append("");
		if (src!=null){
			String[] tmp = src.split(",");
			result.append("'"+tmp[0]+"'");
			for(int i=1;i<tmp.length;i++){
				result.append(",'"+tmp[i]+"'");
			}
		}		
		return result.toString();
	}	
	
	public static String formatStr( String str ){
		String [] temp = null;
		if( str != null ){
			if( str.indexOf(  ",")  != -1 ){
				temp = str.split( "," );
			}else{
				return str;
			}
			return temp[0];
		}else{
			return null;
		}
	}		
    /** 锟斤拷取指锟斤拷锟街斤拷锟斤拷锟斤拷址锟?锟斤拷确锟斤拷锟斤拷锟街诧拷锟斤拷锟斤拷锟?*/
	public static String cutString(String text, int textMaxChar){   
        int size,index;  
        //锟斤拷hamming锟斤拷锟较ｏ拷
        if(text == null || text.equals("")){
        	return "";
        }
        String result = null;  
        if(textMaxChar<=0){   
        	result= text;   
        }else{   
            for(size=0,index=0;index<text.length()&&size<textMaxChar;index++){   
                size += text.substring(index,index+1).getBytes().length;   
            }   
            result = text.substring(0,index);   
        }  
        return result;   
    }
	
    /** 锟斤拷yyyy-MM-dd锟斤拷式锟斤拷式锟斤拷锟斤拷锟斤拷 */
	public static String formatDate(Date date){   
		if (date==null){
			return "";
		}else{
			return df.format(date);
		}
    }
	 /** 锟斤拷yyyy-MM-dd kk:mm:ss锟斤拷式锟斤拷式锟斤拷锟斤拷锟斤拷 */
	public static String formatAllDate(Date date){   
		if (date==null){
			return "";
		}else{
			return df_ymdhms.format(date);
		}
    }
	
    /** 锟斤拷yyyy-MM-dd hh:mm:ss锟斤拷式锟斤拷式锟斤拷锟斤拷锟斤拷 */
	public static String formatDate2(Date date){   
		if (date==null){
			return "";
		}else{
			java.text.DateFormat format1 = new java.text.SimpleDateFormat("yyyy锟斤拷MM锟斤拷dd锟斤拷  HH:mm:ss");
			return format1.format(date);
		}
    }
	
    /** 锟斤拷yyyy-MM-dd hh:mm:ss锟斤拷式锟斤拷式锟斤拷锟斤拷锟斤拷 */
	public static String formatDate4(Date date){   
		if (date==null){
			return "";
		}else{
			java.text.DateFormat format1 = new java.text.SimpleDateFormat("yy-MM-dd HH:mm:ss");
			return format1.format(date);
		}
    }	
	
	 /** 锟斤拷hh:mm锟斤拷式锟斤拷式锟斤拷锟斤拷锟斤拷 */
	public static String formatDate3(Date date){   
		if (date==null){
			return "";
		}else{
			java.text.DateFormat format1 = new java.text.SimpleDateFormat("HH:mm");
			return format1.format(date);
		}
    }	
	 /** 锟斤拷yyyy-MM锟斤拷式锟斤拷式锟斤拷锟斤拷锟斤拷 */
	public static String formatDate6( Date date ){
		if (date==null){
			return "";
		}else{
			java.text.DateFormat format1 = new java.text.SimpleDateFormat("yyyy-MM");
			return format1.format(date);
		}
	}
	 /** 锟斤拷MM锟斤拷dd锟秸革拷式锟斤拷式锟斤拷锟斤拷锟斤拷 */
	public static String formatDate5(Date date){   
		if (date==null){
			return "";
		}else{
			java.text.DateFormat format1 = new java.text.SimpleDateFormat("MM锟斤拷dd锟斤拷");
			return format1.format(date);
		}
    }	
	
	/**锟斤拷锟街凤拷锟斤拷锟斤拷转锟斤拷锟斤拷Date锟斤拷锟斤拷*/
	public static java.util.Date sDateParse(String sDate){
		try{
			SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
			return sd.parse(sDate);
		
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return null;
	}
	/**锟斤拷锟街凤拷锟斤拷锟斤拷转锟斤拷锟斤拷Date锟斤拷锟斤拷*/
	public static java.util.Date sDateParse2(String sDate){
		try{
			SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return sd.parse(sDate);
		
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return null;
	}
	 /*****************************
     * 锟斤拷锟斤拷锟斤拷锟节硷拷
     * @return
     ****************************/
    public static String getDateWeek(Date date){
        
    	String dayNames[] = { "锟斤拷锟斤拷锟斤拷", "锟斤拷锟斤拷一", "锟斤拷锟节讹拷", "锟斤拷锟斤拷锟斤拷", "锟斤拷锟斤拷锟斤拷", "锟斤拷锟斤拷锟斤拷","锟斤拷锟斤拷锟斤拷" }; 
    	Calendar calendar = Calendar.getInstance(); 
    	calendar.setTime(date); 
    	int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)-1; 
    	if(dayOfWeek <0)dayOfWeek=0; 
    	return dayNames[dayOfWeek];
    }
    
    public static String getDateWeek(){
        
    	String dayNames[] = { "锟斤拷锟斤拷锟斤拷", "锟斤拷锟斤拷一", "锟斤拷锟节讹拷", "锟斤拷锟斤拷锟斤拷", "锟斤拷锟斤拷锟斤拷", "锟斤拷锟斤拷锟斤拷","锟斤拷锟斤拷锟斤拷" }; 
    	Calendar calendar = Calendar.getInstance(); 
    	Date date = new Date();
    	calendar.setTime(date); 
    	int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)-1; 
    	if(dayOfWeek <0)dayOfWeek=0; 
    	return dayNames[dayOfWeek];
    }    
    /*****************************
     * 锟斤拷锟截碉拷前锟杰的碉拷一锟斤拷锟斤拷锟斤拷
     * @return
     ****************************/
   public static Date getCurWeekFirstDate(){
        
		SimpleDateFormat   format   =   new   SimpleDateFormat("yyyy-MM-dd");   
		Calendar   calendar   =   Calendar.getInstance();   
		calendar.setTime(new   Date());  
		//锟矫碉拷锟斤拷锟斤拷锟斤拷锟斤拷锟节硷拷锟斤拷锟斤拷锟斤拷锟斤拷为1   
		int   datInWeek   =   calendar.get(Calendar.DAY_OF_WEEK);   
		//锟斤拷锟斤拷锟杰的碉拷一锟斤拷   
		calendar.add(Calendar.DAY_OF_MONTH,   -(datInWeek   -   1));   
		return sDateParse(format.format(calendar.getTime()));
    }
   /*****************************
    * 锟斤拷锟截碉拷前锟杰碉拷锟斤拷锟揭伙拷锟斤拷锟斤拷锟?
    * @return
    ****************************/
    public static Date getCurWeekLastDate(){
       
		SimpleDateFormat   format   =   new   SimpleDateFormat("yyyy-MM-dd");   
		Calendar   calendar   =   Calendar.getInstance();   
		calendar.setTime(new   Date());  
		//锟矫碉拷锟斤拷锟斤拷锟斤拷锟斤拷锟节硷拷锟斤拷锟斤拷锟斤拷锟斤拷为1   
		int   datInWeek   =   calendar.get(Calendar.DAY_OF_WEEK);   
		//锟斤拷锟斤拷锟杰碉拷锟斤拷锟揭伙拷锟?  
		calendar.add(Calendar.DAY_OF_MONTH,   7   -   datInWeek);   
		return sDateParse(format.format(calendar.getTime()));   
    }
    /*****************************
     * 锟斤拷锟截碉拷锟斤拷一锟杰的碉拷一锟斤拷锟斤拷锟斤拷
     * @return
     ****************************/
     public static Date getPastWeekFirstDate(){
        
		  SimpleDateFormat   sdf=new   SimpleDateFormat("yyyy-MM-dd");   
		  Calendar   cal=Calendar.getInstance();   
		  try{   
			  cal.setTime(new   Date()); 
		  }catch(Exception   e){};   
		  if   (cal.get(Calendar.DAY_OF_WEEK)==1){   
			  cal.add(cal.WEEK_OF_MONTH,-1);   
		  }   
		  cal.set(Calendar.DAY_OF_WEEK,Calendar.MONDAY);//锟斤拷一锟斤拷锟斤拷锟斤拷   
		  cal.add(cal.WEEK_OF_MONTH,-1);    
		  return sDateParse(sdf.format(cal.getTime()));   
     }
     /*****************************
      * 锟斤拷锟截碉拷锟斤拷一锟杰碉拷锟斤拷锟揭伙拷锟斤拷锟斤拷锟?
      * @return
      ****************************/
      public static Date getPastWeekLastDate(){
         
    	  SimpleDateFormat   sdf=new   SimpleDateFormat("yyyy-MM-dd");   
    	  Calendar   cal=Calendar.getInstance();   
    	  try{    
    	  cal.setTime(new   Date());   
    	  }catch(Exception   e){};   
    	  if   (cal.get(Calendar.DAY_OF_WEEK)!=1){   
    	  cal.add(cal.WEEK_OF_MONTH,+1);   
    	  }   
    	  cal.set(Calendar.DAY_OF_WEEK,Calendar.SUNDAY);   
    	  cal.add(cal.WEEK_OF_MONTH,-1);   
 		  return sDateParse(sdf.format(cal.getTime()));   
      }
     /**锟斤拷玫锟角帮拷锟斤拷锟?/
  	public static String getToday(){
  		String sToday="";
  		java.util.Date  nowDate = new   java.util.Date();
  		java.text.SimpleDateFormat sdfCurrentDatelog = new java.text.SimpleDateFormat("yyyy-MM-dd");
  		sToday = sdfCurrentDatelog.format(nowDate);
  		return sToday;
  	}
  	/**锟斤拷玫锟角笆憋拷锟?/
  	public static String getDayTime(){
  		String sDayTime="";
  		java.util.Date  nowDate = new   java.util.Date();
  		sDayTime = df_ymdhms.format(nowDate);
  		return sDayTime;
  	}
	/** 锟斤拷未escape锟斤拷HTML锟斤拷锟捷斤拷锟叫凤拷页锟斤拷锟斤拷,锟斤拷锟斤拷String[] */
	public static String[] splitContent(String unEscapedHtml){ 
		if (unEscapedHtml==null){
			return null;
		}else{
			String ck_separator = "(?is)<div style=\"page-break-after: always\">(.*?)<span style=\"display: none\">(.*?)</span></div>";
			String content = unescape(unEscapedHtml);
			//锟斤拷锟叫凤拷页锟斤拷锟斤拷
			return content.split(ck_separator);
		}
	}
	/** 取锟矫革拷式锟斤拷锟斤拷锟斤拷泄锟斤拷锟斤拷锟街凤拷 */
	public static String formatCcurrency(double money){
		return currencyFormat.format(money);   		

		
	}
	
	/**  
     * 锟斤拷锟絜mail锟斤拷锟斤拷锟角凤拷锟斤拷确  
     * 锟斤拷确锟斤拷锟斤拷写锟斤拷式为 username@domain  
     * @param value  
     * @return  
     */  
     public boolean checkEmail(String value, int length) {   
            return value.matches("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*")&&value.length()<=length;   
     }   
    /**  
     * 锟斤拷锟界话锟斤拷锟斤拷锟角凤拷锟斤拷确  
     * 锟斤拷确锟斤拷式 012-87654321锟斤拷0123-87654321锟斤拷0123锟斤拷7654321  
     * @param value  
     * @return  
     */  
     public boolean checkTel(String value) {   
        return value.matches("\\d{4}-\\d{8}|\\d{4}-\\d{7}|\\d(3)-\\d(8)");     
     }   
    /**  
     * 锟斤拷锟斤拷只锟斤拷锟斤拷锟斤拷欠锟斤拷锟饺? 
     * @param value  
     * @return  
     */  
     public boolean checkMobile(String value) {   
        return value.matches("^[1][3,5]+\\d{9}");   
     }   
    /**  
     * 锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷欠锟斤拷锟饺? 
     * @param value  
     * @return  
     */  
     public boolean checkChineseName(String value, int length) {   
        return value.matches("^[\u4e00-\u9fa5]+$")&&value.length()<=length;   
     }   
    /**  
     * 锟斤拷锟紿TML锟斤拷锟斤拷尾锟斤拷锟叫伙拷崭锟? 
     * @param value  
     * @return  
     */  
     public boolean checkBlank(String value){   
        return value.matches("^\\s*|\\s*$");   
    }   
    /**  
     * 锟斤拷锟斤拷址锟斤拷欠锟斤拷锟紿TML锟斤拷签  
     * @param value  
     * @return  
     */  
       
     public boolean checkHtmlTag(String value){   
        return value.matches("<(\\S*?)[^>]*>.*?</\\1>|<.*? />");   
     }   
    /**  
     * 锟斤拷锟経RL锟角凤拷戏锟? 
     * @param value  
     * @return  
     */  
     public boolean checkURL(String value){   
        return value.matches("[a-zA-z]+://[^\\s]*");   
     }   
    /**  
     * 锟斤拷锟絀P锟角凤拷戏锟? 
     * @param value  
     * @return  
     */  
     public boolean checkIP(String value){   
        return value.matches("\\d{1,3}+\\.\\d{1,3}+\\.\\d{1,3}+\\.\\d{1,3}");   
     }   
    /**  
     * 锟斤拷锟絀D锟角凤拷戏锟斤拷锟斤拷锟酵凤拷锟斤拷锟斤拷谴锟叫⌒达拷锟侥革拷锟斤拷锟斤拷锟轿伙拷锟斤拷锟斤拷写锟叫⌒达拷址锟斤拷锟斤拷帧锟斤拷禄锟斤拷锟? 
     * @param value  
     * @return  
     */  
    public boolean checkID(String value){   
        return value.matches("[a-zA-Z][a-zA-Z0-9_]{4,15}$");   
    }   
    /**  
     * 锟斤拷锟絈Q锟角凤拷戏锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷郑锟斤拷锟斤拷锟轿伙拷锟斤拷锟轿?锟斤拷锟筋长15位  
     * @param value  
     * @return  
     */  
       
    public boolean checkQQ(String value){   
        return value.matches("[1-9][0-9]{4,13}");   
    }   
    /**  
     * 锟斤拷锟斤拷时锟斤拷欠锟较凤拷  
     * @param value  
     * @return  
     */  
    public boolean checkPostCode(String value){   
        return value.matches("[1-9]\\d{5}(?!\\d)");   
    }   
    /**  
     * 锟斤拷锟斤拷锟斤拷证锟角凤拷戏锟?15位锟斤拷18位  
     * @param value  
     * @return  
     */  
    public boolean checkIDCard(String value){   
        return value.matches("\\d{15}|\\d{18}");   
    }   
    /**  
     * 锟斤拷锟斤拷锟斤拷锟斤拷欠癯锟斤拷娑拷锟斤拷锟? 
     * @param length  
     * @param value  
     * @return  
     */  
    public boolean checkLength(String value, int length) {   
        return ((value == null || "".equals(value.trim())) ? 0 : value.length()) <= length;   
    }   
  
    /**  
     * 锟斤拷锟斤拷欠锟轿拷锟斤拷址锟?锟秸ｏ拷true,锟斤拷锟斤拷:false  
     * @param value  
     * @return  
     */  
    public boolean checkNull(String value) {   
        return value == null || "".equals(value.trim());   
    }
    
	/** 取锟斤拷a锟斤拷b之锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷b */
	public static int getRandomNumber(int a,int b){
		return (int)(Math.random()*(b-a))+a;
	}
	
	/*******************************************
	 * Desc:锟斤拷锟揭伙拷锟轿э拷锟斤拷锟斤拷锟斤拷
	 * Author: 莫锟斤拷
	 * Time:20080520锟斤拷锟斤拷锟斤拷
	 * fnum 锟斤拷小值,snum 锟斤拷锟街?type 锟角凤拷锟斤拷要小锟斤拷锟?
	 * type=0 锟斤拷锟斤拷要,type=1 锟斤拷要1位小锟斤拷,锟斤拷锟斤拷锟酵拷锟?
	 *******************************************/
	public static String getRand(String fnum,String snum,int type){
		double result=0;
		String returnNum="";
		String tempFnum="";
		String tempSnum="";
		if(fnum!=null&&!fnum.equals("")){
			tempFnum=(fnum.trim()).replaceAll(" ", "");
			if(tempFnum.indexOf(".")!=-1){
				String Ftemp="",Ltemp="";
				int pointLen=tempFnum.indexOf(".");
				int tempFnumLen=tempFnum.length();
				Ftemp=tempFnum.substring(0, pointLen+1);
				Ltemp=tempFnum.substring(pointLen+1, tempFnumLen);
				int tempLen=Ftemp.length();
				if(tempLen>3)Ltemp=Ltemp.substring(0, 1);
				tempFnum=Ftemp+Ltemp;
			}
		}
		if(snum!=null&&!snum.equals("")){
			tempSnum=(snum.trim()).replaceAll(" ", "");
			if(tempSnum.indexOf(".")!=-1){
				String Ftemp="",Ltemp="";
				int pointLen=tempSnum.indexOf(".");
				int tempSnumLen=tempSnum.length();
				Ftemp=tempSnum.substring(0, pointLen+1);
				Ltemp=tempSnum.substring(pointLen+1, tempSnumLen);
				int tempLen=Ftemp.length();
				if(tempLen>3)Ltemp=Ltemp.substring(0, 1);
				tempSnum=Ftemp+Ltemp;
			}
		}
		double endnum=Double.parseDouble(tempSnum)*10;
		double startnum=Double.parseDouble(tempFnum)*10;
		result =((double)(Math.random()*(endnum-startnum))+startnum)/10;
		
		int scale = 0; //锟斤拷锟斤拷位锟斤拷:锟斤拷锟斤拷要小锟斤拷锟?
		scale= type;
		int roundingMode = 4; //锟斤拷示锟斤拷锟斤拷锟斤拷锟诫，锟斤拷锟斤拷选锟斤拷锟斤拷锟斤拷锟斤拷值锟斤拷式锟斤拷锟斤拷锟斤拷去尾锟斤拷锟饺碉拷
		BigDecimal bd = new BigDecimal(result);
		bd = bd.setScale(scale,roundingMode);
		returnNum=String.valueOf(bd);

		return returnNum;
	}
	
	/*************************
	 * 锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷
	 * 锟斤拷页锟斤拷锟斤拷锟斤拷锟斤拷锟?
	 ************************/
	public static String changeWebEncode(String str)
	{
		if(str.equals("")||str==null)
		{
			return "";
		}
		try {
			str=new String(str.getBytes("GBK"),"ISO-8859-1");			
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		return str;
	}
	/**URL锟斤拷锟斤拷URLDecoder**/
	public static String URLEncoder(String str){
		return URLEncoder.encode(str);
	}
	/**URL锟斤拷锟斤拷URLDecoder**/
	public static String URLDecoder(String str){
		return URLDecoder.decode(str);
	}
	
	/*********************************************
	 * 锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷
	 * str---要转锟斤拷锟斤拷址锟?
	 * fromEncode--源锟斤拷锟斤拷
	 * targetEncode--目锟斤拷锟斤拷锟?
	 *********************************************/
	public static String changeEncode(String str ,String fromEncode ,String targetEncode)
	{
		if(str.equals("")||str==null)
		{
			return "";
		}
		try {
			str=new String(str.getBytes(fromEncode),targetEncode);			
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		return str;
	}
	/**锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷**/
	public static String changeEncode(String str)
	{
		if(str.equals("")||str==null)
		{
			return "";
		}
		try {
			str=new String(str.getBytes("ISO-8859-1"),"GBK");			
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		return str;
	}
	/**锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷(JAVA锟斤拷锟叫筹拷锟斤拷锟斤拷锟斤拷)**/
	public String changeEncode2(String str)
	{
		if(str.equals("")||str==null)
		{
			return "";
		}
		try {
			str=new String(str.getBytes("ISO-8859-1"),"utf-8");			
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		return str;
	}
	/**锟叫讹拷锟街凤拷锟角凤拷锟斤拷锟斤拷锟?*/
	public static boolean isChinese(String str){
		boolean bln=false;
		for (int i = 0; i < str.length(); i++){
            if (str.substring(i,i+1).matches("[\u4e00-\u9fa5]+")){
            	bln=true;
            }
        }
		return bln;
	}
	
	
	/** 锟斤拷取HTML锟斤拷锟斤拷锟斤拷械拇锟斤拷谋锟?*/
	public static String getTextOfHTML(String html){
		if (html!=null){
			html=html.replaceAll("</?[^>]+>","");   //去锟斤拷锟斤拷html锟侥憋拷签
			html=html.replaceAll("&nbsp;",""); 	//去锟斤拷崭锟?
			html=html.replaceAll("\\s*|\t|\r|\n",""); //去锟斤拷锟街凤拷锟叫的空革拷,锟截筹拷,锟斤拷锟叫凤拷,锟狡憋拷锟?
		}
		return html;
	}
	
	/** 锟斤拷取HTML锟斤拷锟斤拷锟斤拷械拇锟斤拷谋锟?*/
	public static String getTextOfHTML2(String html){
		if (html!=null){
			html=html.replaceAll("</?[^>]+>","");   //去锟斤拷锟斤拷html锟侥憋拷签
			
		}
		return html;
	}
	
	/** 去锟斤拷HTML锟斤拷锟斤拷锟斤拷械某锟斤拷锟斤拷锟?*/
	public static String clearHrefOfHtml(String html){
		if (html!=null){
			html=html.replaceAll("<a[^>]+>(.*?)</a>", "$1");   //去锟斤拷锟斤拷html锟侥筹拷锟斤拷锟接憋拷签
		}
		return html;
	}
	/********************************************************************  
	     * 删锟斤拷input锟街凤拷锟叫碉拷html锟斤拷式    
	     * @param input    
	     * @param length    
	     * @return    
	*********************************************************************/     
    public static String splitAndFilterString(String input) {      
        if (input == null || input.trim().equals("")) {      
            return "";      
        }      
        // 去锟斤拷锟斤拷锟斤拷html元锟斤拷,      
        String str = input.replaceAll("\\&[a-zA-Z]{1,10};", "").replaceAll(      
                "<[^>]*>", "");      
        str = str.replaceAll("[(/>)<]", "");      
        int len = str.length();
        return str;      
    } 
	/*****************************
     * 锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷某锟斤拷锟斤拷
     * @param day 锟斤拷锟斤拷,锟斤拷锟斤拷锟斤拷前锟斤拷;为锟斤拷锟斤拷,锟斤拷锟斤拷锟斤拷锟斤拷锟?
     * 为0时为锟斤拷锟斤拷
     * System.out.println("dayPush=="+dayPush(-4));
     * @return
     ****************************/
    public static String dayPush(int day)
	{
		Date dt=new Date();
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(dt);
		gc.add(5, day);
		Date d=gc.getTime();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String sdate=df.format(d);
		return sdate;
	}
	/**(莫锟斤拷)锟斤拷锟截第硷拷锟杰ｏ拷锟斤拷取某一锟斤拷锟斤拷锟斤拷全锟斤拷锟叫碉拷为锟节硷拷锟斤拷(Date锟斤拷锟酵诧拷锟斤拷)**/
	public static int WeekOfTheYear(Date sdate)
	{       
		int week=0;
		Calendar cal=Calendar.getInstance();
		cal.setTime(sdate);  
		week=cal.get(Calendar.WEEK_OF_YEAR); 
    	return week;
	}
	/**(莫锟斤拷)锟斤拷锟截第硷拷锟杰ｏ拷锟斤拷取某一锟斤拷锟斤拷锟斤拷全锟斤拷锟叫碉拷为锟节硷拷锟斤拷(String锟斤拷锟酵诧拷锟斤拷)**/
	public static int WeekOfTheYear(String sdate)
	{       
		int week=0;
		Calendar cal=Calendar.getInstance();
		try{
			SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
			cal.setTime(sd.parse(sdate));
		}catch(Exception ex){
			ex.printStackTrace();
		} 
		week=cal.get(Calendar.WEEK_OF_YEAR); 
    	return week;
	}
	/**********************************************************
	 * (莫锟斤拷)锟斤拷锟斤拷小锟斤拷
	 * a     
	 **********************************************************/
	public String getXiaoshu(String a,String b,int c)	
	{
		System.out.println(Long.parseLong(a));
		System.out.println(Long.parseLong(b));		
		double d=Double.parseDouble(a)/Double.parseDouble(b);
		String str=String.valueOf(d)+"0000000000000";		
		int point=str.indexOf(".");
		if(c==0)
		  str=str.substring(0, point);
		else
		  str=str.substring(0, point+c+1);		
		return str;
	}
	
    //锟斤拷锟绞憋拷锟斤拷2010-6-18-2010-6-25锟斤拷锟斤拷为Date(锟斤拷锟斤拷锟斤拷2010-6-25)
    public static String getweekday(Date fd) {
    	String dateString=null;
    	SimpleDateFormat df=new SimpleDateFormat("yyyy锟斤拷MM锟斤拷dd锟斤拷");   	 
    	Calendar cal=Calendar.getInstance();
    	cal.setTime(fd);//锟斤拷Date锟斤拷锟斤拷转锟斤拷锟斤拷cal  
    	cal.add(Calendar.DATE, -7); 
    	Date date=cal.getTime(); //锟斤拷cal转锟斤拷为date锟斤拷锟斤拷
    	String g=df.format(date);
    	cal.add(Calendar.DATE, 7); 
    	date=cal.getTime(); 
    	String b=df.format(date);
    	dateString=g+"-"+b;
    	System.out.println(dateString);
    	return dateString;
    }
    //锟矫碉拷锟斤拷前锟斤拷锟斤拷前XXX锟斤拷锟斤拷锟轿狣ate(锟斤拷锟斤拷锟斤拷2010-6-25)
    public static String getdate(Date date,int hf) throws ParseException{
    	String dateString=null;
    	SimpleDateFormat df=new SimpleDateFormat("yyyy锟斤拷MM锟斤拷dd锟斤拷"); 
    	//Date fgDate=df.parse(date);		 
		Calendar cal=Calendar.getInstance();
		cal.setTime(date);//锟斤拷Date锟斤拷锟斤拷转锟斤拷锟斤拷cal      	
		cal.add(Calendar.DATE, -hf); 
		Date date1=cal.getTime(); //锟斤拷cal转锟斤拷为date锟斤拷锟斤拷      	
		dateString=df.format(date1);
        	System.out.println(dateString); 
        	return dateString;
    	
    }

	/**
	 * @Author锟斤拷莫锟斤拷
	 * @Time锟斤拷2011-11-16
	 * @Desc锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷址锟斤拷锟斤拷锟饺ワ拷锟斤拷械锟斤拷瞻祝锟饺伙拷锟斤拷婊蝗拷堑亩锟斤拷锟轿拷锟角ｏ拷锟劫凤拷锟截凤拷细锟绞斤拷锟斤拷址锟?
	 * 锟斤拷锟街凤拷为String str=",,ssdd锟斤拷,,1,锟斤拷2,3,锟斤拷,,4,锟斤拷,ddd,ggh,11212锟斤拷3334,,锟斤拷,,";
	 * 锟斤拷锟矫猴拷锟斤拷蠓祷锟絪tr="ssdd,1,2,3,4,ddd,ggh,11212,3334";
	 */
	public static String formatIDS(String ids) {

		String str = "", regex = "";

		str = ids;
		str = str.replaceAll(" ", "");// 去锟斤拷锟斤拷锟叫的空革拷
		str = str.replaceAll("锟斤拷", ",");// 去锟斤拷锟斤拷锟叫碉拷全锟角讹拷锟斤拷

		regex = "^,*|,*$";// 锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷式

		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);// 锟斤拷锟斤拷锟街凤拷
		// 只锟斤拷锟斤拷一锟斤拷锟街凤拷

		str = matcher.replaceAll("");

		regex = "\\,*\\,";
		pattern = Pattern.compile(regex);
		matcher = pattern.matcher(str);
		str = matcher.replaceAll(",");

		return str;
	}

	/**
	 * @Author锟斤拷莫锟斤拷
	 * @Time锟斤拷2011-11-16
	 * @Desc锟斤拷统锟斤拷锟街凤拷
	 * @param str
	 *            锟斤拷锟斤拷锟斤拷锟街凤拷
	 * @param c
	 *            锟斤拷统锟狡碉拷锟街凤拷
	 */
	public static int showNum(String str, char c) {
		int i = 0;
		for (char temp : str.toCharArray()) {
			if (temp == c)
				i++;
		}
		return i;
	}
	/**
	 * @Author锟斤拷莫锟斤拷
	 * @Time锟斤拷2011-11-22
	 * @Desc锟斤拷权锟睫继继筹拷--锟较诧拷锟街凤拷
	 * @param String privilege1 权锟斤拷1
	 * @param String privilege2 权锟斤拷2
	 */
	public static String combineStr(String privilege1,String Privilege2) {

	   String str="",s1="",s2="";
	   long num=0,num1=0,num2=0,strLen=0,ssLen=0;
	   //锟斤拷锟斤拷址锟侥空革拷
	   s1=privilege1.replaceAll(" ", "");
	   s2=Privilege2.replaceAll(" ", "");
	   //锟斤拷锟斤拷锟斤拷锟斤拷址锟侥筹拷锟斤拷
	   ssLen=s1.length();
	   //锟斤拷锟街凤拷转锟斤拷long锟斤拷(注锟斤拷:转锟斤拷锟斤拷锟斤拷锟斤拷锟轿晃?0",锟斤拷锟截碉拷锟斤拷锟斤拷锟斤拷要锟节猴拷锟斤拷锟斤拷锟斤拷位锟斤拷锟斤拷)
	   num1=Long.valueOf(s1);
	   num2=Long.valueOf(s2);
	   //锟斤拷锟斤拷锟斤拷锟斤拷锟?
	   num=num1+num2;
	   //锟斤拷锟斤拷锟斤拷转锟斤拷锟斤拷锟街凤拷
	   str=String.valueOf(num);
	   //锟斤拷锟斤拷喜锟斤拷锟斤拷锟街凤拷锟斤拷
	   strLen=str.length();
	   //锟饺斤拷锟斤拷锟竭的筹拷锟斤拷锟角凤拷一锟铰ｏ拷锟斤拷锟斤拷锟斤拷锟斤拷诘锟揭晃伙拷锟?0"
	   if(ssLen!=strLen)
		   str="0"+str;
	   //锟斤拷锟斤拷锟轿?锟斤拷锟斤拷锟斤拷锟芥换锟斤拷1
	   str=str.replaceAll("2", "1");
	   
	   return str;
    }
	/**
	 * @Author锟斤拷莫锟斤拷
	 * @Time锟斤拷2011-11-17
	 * @Desc锟斤拷锟斤拷锟街凤拷锟斤拷锟斤拷转锟斤拷锟斤拷锟街凤拷
	 * @param String[] 锟街凤拷锟斤拷锟斤拷
	 */
	public static String stringIDS(String[] ids) {

        String tempStr="";
        StringBuffer sb = new StringBuffer();
        for(String str:ids)
        {
           sb.append(str + ",");
        }
        sb.delete(sb.length()-1,sb.length());
        tempStr=sb.toString();
		return tempStr;
	}
	

	
	//锟斤拷锟斤拷一锟斤拷SMTP锟斤拷权锟斤拷证锟斤拷
	static class SmtpAuth extends Authenticator{
		String user,password;
		//锟斤拷锟斤拷锟绞猴拷锟斤拷息
		void setAccount(String user,String password){
			this.user = user;
			this.password = password;
		}
		//取锟斤拷PasswordAuthentication锟斤拷锟斤拷
		protected PasswordAuthentication getPasswordAuthentication(){
			return new PasswordAuthentication(user,password);
		}
	}	
	/**锟叫讹拷锟角凤拷为锟斤拷锟斤拷**/
	public static boolean isNumeric(String str)
    {
           Pattern pattern = Pattern.compile("[0-9]*");
           Matcher isNum = pattern.matcher(str);
           if( !isNum.matches() )
           {
                 return false;
           }
           return true;
    }
	
	 /**锟斤拷式锟斤拷锟斤拷锟斤拷锟斤拷锟绞憋拷墓丶锟斤拷锟?/
	 public static String getKeyWordsSplit(String keywords) {  
		  StringBuffer sb = new StringBuffer();
		  keywords = keywords.replaceAll("锟斤拷", ",");
		  keywords = keywords.replaceAll("锟斤拷", ",");
		  keywords = keywords.replaceAll(" ", ",");
		  keywords = keywords.replaceAll("锟斤拷", ",");
		  keywords = keywords.replaceAll("锟斤拷", ",");
		  keywords = keywords.replaceAll(":", ",");
		  keywords = keywords.replaceAll("\\.", ",");
		  keywords = keywords.replaceAll("锟斤拷", ",");
		  keywords = keywords.replaceAll("锟斤拷", ",");
		  keywords = keywords.replaceAll(";", ",");
		  keywords = keywords.replaceAll("\\|", ",");
		  keywords = keywords.replaceAll("/", ",");
		  keywords = keywords.replaceAll("\r\n", ",");
		  String[] tmp = keywords.split(",");
		  for(String m:tmp){
				  sb.append(m.trim()+",");
		  }		 
		 return sb.toString().substring(0, sb.toString().length()-1);
	  }
	 
	 
	 
	 /**锟叫讹拷锟角凤拷锟角合凤拷锟斤拷锟斤拷锟斤拷*/
		public static boolean isMail(String mail){
			try{				
				String regexp ="^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$";
				PatternCompiler compile = new Perl5Compiler();
				org.apache.oro.text.regex.Pattern pattern = compile.compile(regexp);
				PatternMatcher matcher = new Perl5Matcher();
				return matcher.matches(mail,pattern);			
			}catch(Exception ex){
				ex.printStackTrace();
				return false;
			}
		}
		
		 /**锟叫讹拷锟角凤拷锟斤拷锟街伙拷锟斤拷锟斤拷袒锟?/
		public static boolean isMobile(String mobile){
			try{				
				String regexp1 = "^0*(13|15|18)\\d{9}$";   
				String regexp2 = "^((\\(\\d{2,3}\\))|(\\d{3}\\-))?(\\(0\\d{2,3}\\)|0\\d{2,3}-)?[1-9]\\d{6,7}(\\-\\d{1,4})?$"; 
				PatternCompiler compile = new Perl5Compiler();
				org.apache.oro.text.regex.Pattern pattern1 = compile.compile(regexp1);
				org.apache.oro.text.regex.Pattern pattern2 = compile.compile(regexp2);
				PatternMatcher matcher = new Perl5Matcher();
				boolean bool1= matcher.matches(mobile,pattern1);
				boolean bool2= matcher.matches(mobile,pattern2);
				if(!bool1&& !bool2){
					return false;
				}else{
					return true;
				}		
			}catch(Exception ex){
				ex.printStackTrace();
				return false;
			}
		}
		/***************************************************************************************************
		 * 锟斤拷锟斤拷图片锟较达拷锟侥的癸拷锟斤拷
		 * t_pic  目锟斤拷图片锟侥硷拷路锟斤拷
		 * s_pic  源图片
		 * author :锟斤拷志伟
		 * */
		public static  void uploadPic(String t_pic,File s_pic){
			/**锟斤拷锟斤拷图片锟较达拷锟侥硷拷**/
				try{
					FileOutputStream fos = new FileOutputStream(t_pic);
					FileInputStream fis = new FileInputStream(s_pic);
					byte[] buf = new byte[1024];
					int len = 0;
					while((len=fis.read(buf))>0){
						fos.write(buf,0,len);
					}
					if (fis!=null)fis.close();
					if (fos!=null)fos.close();
				}catch(Exception e){
					e.printStackTrace();
				}
		}
		/**锟斤拷锟斤拷图片锟斤拷锟侥硷拷锟斤拷锟斤拷锟斤拷(yyyy-mm)*/
		public static String createFolder(){
			return pic_df.format(new Date());
		}
		/**锟斤拷锟酵计拷募锟斤拷锟铰凤拷锟?/
		public static void createPath(String filename){
			try{
				File file=new File(filename);
				if(!file.exists()){
					file.mkdirs();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}
		/**转锟斤拷锟街凤拷为十锟斤拷锟斤拷票锟斤拷锟?/
		public static String toHexString(String s)   
		{   
		   String str="";   
		   for (int i=0;i<s.length();i++)   
		   {   
		    int ch = (int)s.charAt(i);   
		    String s4 = Integer.toHexString(ch);   
		    str = str + s4; 
		   }   
		   return "0x" + str;//0x锟斤拷示十锟斤拷锟斤拷锟?
		}
		/** 取锟斤拷图片锟斤拷锟侥硷拷锟斤拷 (锟斤拷锟斤拷时锟斤拷锟斤拷锟叫猴拷)*/
		public synchronized static String getFilename(){
			return Integer.toHexString((int)( System.currentTimeMillis()));
		}

		//锟斤拷取锟斤拷锟斤拷锟斤拷锟斤拷
		public static Date getYesterday(){
			Date yd=null;
			try{
				Calendar cal=Calendar.getInstance();
		        cal.add(Calendar.DATE,-1);
		        Date d=cal.getTime();
		        yd=df.parse(df.format(d));//锟斤拷取锟斤拷锟斤拷锟斤拷锟斤拷
			}catch(Exception e){
				e.printStackTrace();
			}
	        return yd;
		}
		//锟斤拷取前锟斤拷锟斤拷锟斤拷
		public static Date getBYesterday(){
			Date yd=null;
			try{
				Calendar cal=Calendar.getInstance();
		        cal.add(Calendar.DATE,-2);
		        Date d=cal.getTime();
		        yd=df.parse(df.format(d));//锟斤拷取锟斤拷锟斤拷锟斤拷锟斤拷
			}catch(Exception e){
				e.printStackTrace();
			}
	        return yd;
		}
		
   public static void main(String[] args) {
	  
    }
}
