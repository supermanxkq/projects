package com.softkey;

/**
 * This class can take a variable number of parameters on the command
 * line. Program execution begins with the main() method. The class
 * constructor is not invoked unless an object of type 'Class1'
 * created in the main() method.
 */
public class test
{
	/**
	 * The main entry point for the application. 
	 *
	 * @param args Array of parameters passed to the application
	 * via the command line.
	 */
	public static void main1 (String[] args)
	{
		// TODO: Add initialization code here
		com.softkey.jsyunew6 j9=new  com.softkey.jsyunew6();
		String DevicePath;
		 //这个用于判断系统中是否存在着加密锁。不需要是指定的加密锁,
		DevicePath=jsyunew6.FindPort(0);
		if(jsyunew6.get_LastError()!=0)
		{
		System.out.println("未找到加密锁,请插入加密锁后，再进行操作。");
		return ;
		}
		
		/*查找是否存在指定的加密狗,如果找到，则返回0,DevicePath为锁所在的返回设备所在的路径。
        注意！！！！！！！！！这里的参数“1”及参数“134226688”，随每个软件开发商的不同而不同，因为每个开发商的加密锁的加密算法都不一样，
        1、运行我们的开发工具，
        2、在让加密锁进行加密运算那里随意输入一个数
        3、然后读出对应的检验码(即加密后的数据)，
        然后将输入的数和返回的数替换这里的参数“1”及参数“134226688”*/
		DevicePath = jsyunew6.FindPort_2(0, 1, 134226688) ;
		if( jsyunew6.get_LastError() != 0 )System.out.println("未找到指定的加密锁");else System.out.println("找到指定的加密锁");

		/*'用于返回加密狗的ID号，加密狗的ID号由两个长整型组成。
        '提示1：锁ID可以是开发商唯一或每一把都是唯一的，开发商唯一是指同一开发商相同，不同的开发商不相同，每一把是唯一的，是指每一把锁的ID都不相同
        '提示2、如果是每一把都是唯一的，需要在订货时告知我们)
        '提示3: ID唯一是指两个ID转化为16进制字符串后并连接起来后是唯一的*/
		int ID1, ID2;
		ID1 = jsyunew6.GetID_1(DevicePath);
		if(jsyunew6.get_LastError() !=0){System.out.println("返回ID1错误");return ;}
		ID2 =  jsyunew6.GetID_2(DevicePath);
		if(jsyunew6.get_LastError() !=0){System.out.println("返回ID1错误");return ;}
		System.out.println("已成功返回锁的ID号:"+Integer.toHexString(ID1)+Integer.toHexString(ID2));
		
		{
			//用于返回加密狗的版本号
			int version ;
			version = jsyunew6.GetVersion(DevicePath);
			if(jsyunew6.get_LastError() !=0){ System.out.println("返回版本号错误");return ;}
			System.out.println("已成功返回锁的版本号:"+Integer.toString(version));
		}
		
		{
			 //对输入的数进行加密运算，然后读出加密运算后的结果
			int result;
			result=jsyunew6.sWriteEx(1, DevicePath);
			if( jsyunew6.get_LastError()!=0){ System.out.println("加密错误");return ;}
			System.out.println("已成功进行加密运算，加密后的数据是："+Integer.toString(result));
		}
		
		{
			 //对输入的数进行解密运算，然后读出解密运算后的结果
			int result;
			result=jsyunew6.sWrite_2Ex(1342266881, DevicePath);
			if( jsyunew6.get_LastError()!=0){ System.out.println("解密错误");return ;}
			System.out.println("已成功进行解密运算，解密后的数据是："+Integer.toString(result));
		}
		{
			///向加密锁的指定的地址中写入指定的数据，使用默认的写密码
			short address=0;//要写入的地址为0
			short len=20;//要写入的数据为20;
			short [] buf=new short[20];short Data=0;
			for(int i=0;i<20;i++)
			{
				Data++;
				jsyunew6.SetBuf(i,Data);
			}

			if(jsyunew6.YWrite( address,len, "FFFFFFFF","FFFFFFFF",  DevicePath) !=0){ System.out.println("写储存器数据失败");return ;}
			System.out.println("写入批量数据成功。");
			
		}
		{
			//从加密锁的指定的地址中读取一批数据,使用默认的读密码
			short address=0;//要读取的数据在加密锁中储存的起始地址
			short len=20;//要读取20个字节的数据
			if(jsyunew6.YRead(address,len, "FFFFFFFF","FFFFFFFF", DevicePath) !=0){ System.out.println("读取储存器失败");return ;}
			short [] buf=new short[20];
			for(int i=0;i<10;i++)
			{
				buf[i]=jsyunew6.GetBuf(i);
			}
			System.out.println("读取批量数据成功。");
		}
		
		{
			//写入字符串到加密锁中,使用默认的写密码
			short address=0;//要写入的地址为0
			int WriteLen=jsyunew6.YWriteString("加密锁", address, "FFFFFFFF","FFFFFFFF",  DevicePath);//WriteLen返回写入的字符串的长度，以字节来计算
			if(jsyunew6.get_LastError() !=0){ System.out.println("写字符串失败");return ;}
			System.out.println("写入成功。写入的字符串的长度是："+Integer.toString(WriteLen));
			
		}
		{
			//读取字符串，,使用默认的读密码
			String outString;
			short address=0;//要读取的字符串在加密锁中储存的起始地址
			short len=6;//注意这里的6是长度，要与写入的字符串的长度相同
			outString = jsyunew6.YReadString(address,len, "FFFFFFFF","FFFFFFFF", DevicePath);
			if(jsyunew6.get_LastError() !=0){ 
				System.out.println("读字符串失败");return ;
			}
			System.out.println("读字符串成功："+outString);

		}
		
		{
			//写入字符串带长度，,使用默认的读密码
			int ret;
            int nlen;
            String InString;
            byte [] buf = new byte[1];
            InString = "加密锁";
           

            //写入字符串到地址1
            nlen = jsyunew6.YWriteString( InString,(short)1, "ffffffff", "ffffffff", DevicePath);
            int temp=jsyunew6.get_LastError();
            if(  temp!=0 )
            {
                System.out.println("写入字符串(带长度)错误。"+Long.toString(temp)) ;return  ;
            }
           //写入字符串的长度到地址0
            buf[0] = (byte)nlen;
            jsyunew6.SetBuf(0,buf[0]);
            ret = jsyunew6.YWrite( (short)0, (short)1, "ffffffff", "ffffffff", DevicePath);
            if( ret != 0 )
                System.out.println("写入字符串长度错误。错误码：" );
            else
                System.out.println("写入字符串(带长度)成功");
		}
		
		{
			int ret;
            short nlen;
            byte [] buf = new byte[1];
            String outString;
            //先从地址0读到以前写入的字符串的长度
            ret = jsyunew6.YRead((short)0, (short)1, "ffffffff", "ffffffff", DevicePath);
            nlen =jsyunew6.GetBuf(0);
            if( ret != 0 )
            {
                System.out.println("读取字符串长度错误。错误码：" );return ;
            }
          //再读取相应长度的字符串
            outString = jsyunew6.YReadString((short)1, nlen, "ffffffff", "ffffffff", DevicePath);
            if( jsyunew6.get_LastError() != 0 )
                System.out.println("读取字符串(带长度)错误。错误码：" );
            else
                System.out.println("已成功读取字符串(带长度)：" + outString);
		}
		
        {
            //设置增强算法密钥一
            //注意：密钥为不超过32个的0-F字符，例如：1234567890ABCDEF1234567890ABCDEF,不足32个字符的，系统会自动在后面补0
             int ret;
             String Key;
             Key = "1234567890ABCDEF1234567890ABCDEF";
             ret = jsyunew6.SetCal_2(Key, DevicePath);
             if (ret != 0) { System.out.println("设置增强算法密钥错误"); return; }
             System.out.println("已成功设置了增强算法密钥");
         }
        
        {
            //'使用增强算法一对字符串进行加密
            int ret,nlen;
            String InString;
            String outString;
            InString = "加密锁";
            outString = jsyunew6.EncString(InString, DevicePath);
            if (jsyunew6.get_LastError() != 0) { System.out.println("加密字符串出现错误"); return; }
            System.out.println("已成功对字符串进行加密，加密后的字符串为：" + outString);
	        //推荐加密方案：生成随机数，让锁做加密运算，同时在程序中端使用代码做同样的加密运算，然后进行比较判断。
			//'以下是对应的加密代码，可以参考使用
			/*  SoftKey mysoftkey = new SoftKey();
			String Outstring_2;
			InString="加密锁";
			Outstring=mysoftkey.StrEnc(InString,"1234567890ABCDEF1234567890ABCDEF");
			Outstring_2=mysoftkey.StrDec(Outstring,"1234567890ABCDEF1234567890ABCDEF");
			System.out.println(Outstring);
			System.out.println(Outstring_2);*/
        }
        
        {
            //设置增强算法密钥二
            //注意：密钥为不超过32个的0-F字符，例如：1234567890ABCDEF1234567890ABCDEF,不足32个字符的，系统会自动在后面补0
             int ret;
             String Key;
             Key = "1234567890ABCDEF1234567890ABCDEF";
             ret = jsyunew6.SetCal_New(Key, DevicePath);
             if (ret != 0) { System.out.println("设置增强算法密钥错误"); return; }
             System.out.println("已成功设置了增强算法密钥");
         }
        
        {
            //'使用增强算法二对字符串进行加密
            int ret,nlen;
            String InString;
            String outString;
            InString = "加密锁";
            outString = jsyunew6.EncString_New(InString, DevicePath);
            if (jsyunew6.get_LastError() != 0) { System.out.println("加密字符串出现错误"); return; }
            System.out.println("已成功对字符串进行加密，加密后的字符串为：" + outString);
	        //推荐加密方案：生成随机数，让锁做加密运算，同时在程序中端使用代码做同样的加密运算，然后进行比较判断。
			//'以下是对应的加密代码，可以参考使用
			/*  SoftKey mysoftkey = new SoftKey();
			String Outstring_2;
			InString="加密锁";
			Outstring=mysoftkey.StrEnc(InString,"1234567890ABCDEF1234567890ABCDEF");
			Outstring_2=mysoftkey.StrDec(Outstring,"1234567890ABCDEF1234567890ABCDEF");
			System.out.println(Outstring);
			System.out.println(Outstring_2);*/
        }
        
        {
            //使用增强算法对二进制数据进行加密
            int ret,n;
            byte [] OutBuf=new byte[8];
            short Data=0;
            for(n=0;n<8;n++)
            {
            	jsyunew6.SetEncBuf(n, Data);
            	Data++;
            }
            ret = j9.Cal(DevicePath);
            if (ret != 0) { System.out.println("加密数据时失败"); return; }
            for(n=0;n<8;n++)
            {
            	OutBuf[n]=(byte)j9.GetEncBuf(n);

            }
            System.out.println("已成功对二进制数据进行了加密");
            //推荐加密方案：生成随机数，让锁做加密运算，同时在程序中端使用代码做同样的加密运算，然后进行比较判断。
            //以下是对应的加密代码，可以参考使用
            /*SoftKey mysoftkey = new SoftKey();
			byte[] indata = new byte[]{ 0,1,2,3,4,5,6,7};
			byte [] outdata=new byte[8];byte [] outdata_2=new byte[8];
			mysoftkey.EnCode(indata,outdata,"1234567890ABCDEF1234567890ABCDEF");
			mysoftkey.DeCode(outdata,outdata_2,"1234567890ABCDEF1234567890ABCDEF");
			System.out.println("加密成功");*/
        }

		
	}
	public static void main(String[] args) {
		String strValue="ABCDEFG";
		strValue.substring(3);
		strValue.concat("123");
		System.out.println("result=" + strValue);
		String value= new String("ABCDEFG");
		System.out.println(strValue==value);
	}
}
