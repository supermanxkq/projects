package com.blog.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;


/***
 * 邮件发送工具类
 * 
 * @ClassName:MailUtils
 * @date: 2014-3-17上午10:17:36
 * @author: 孟凡岭
 * @version: V1.0
 */
public class MailUtils {
    /** 邮件发送类 **/
    private static JavaMailSenderImpl senderimpl = null;

    /** 输入流 **/
    private static InputStream is = null;

    /** 用来读取配置文件 **/
    private static Properties p = null;

    /** 邮件服务器 **/
    private static String mailSmtp = null;

    /** 邮件帐号 **/
    private static String userName = null;

    /** 邮件密码 **/
    private static String password = null;

    /** 超时时间 **/
    private static String timeout = null;

    /** 邮件编码 **/
    private static String encoding = null;
    /** 使用static静态块进行初始化 **/
    static {
        senderimpl = new JavaMailSenderImpl();
        /** 读取配置文件 **/
        is = MailUtils.class.getClassLoader().getResourceAsStream("mail.properties");
        p = new Properties();
        try {
            /** 使用Properties加载配置文件 **/
            p.load(is);
        }
        catch (IOException e) {
            // TODO Auto-generated catch block

        }
        /** 从配置文件获取邮件服务器 **/
        mailSmtp = p.getProperty("smtp");
        /** 从配置文件获取邮箱帐号 **/
        userName = p.getProperty("userName");
        /** 从配置文件获取邮箱密码 **/
        password = p.getProperty("password");
        /** 从配置文件获取超时时间 **/
        timeout = p.getProperty("timeout");
        /** 从配置文件获取邮件内容编码 **/
        encoding = p.getProperty("encoding");
    }

    /**
     * 邮件发个单个人
     * 
     * @Title:sendTextMail
     *@param:@param subject邮件主题
     *@param:@param content邮件内容
     *@param:@param toMail要发送到的Email
     *@return:void
     *@author:孟凡岭
     *@thorws:
     */
    public static void sendMail(String subject, String content, String toMail) throws Exception {

        /** 设定邮件的字符编码 **/
        senderimpl.setDefaultEncoding(encoding);

        /** 设定mail server **/
        senderimpl.setHost(mailSmtp);

        /** 设置邮箱的登录帐号及密码 **/
        senderimpl.setUsername(userName);
        senderimpl.setPassword(password);

        /** 这部分是必须的 **/
        Properties properties = new Properties();
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.timeout", timeout);
        senderimpl.setJavaMailProperties(properties);

        Session session = Session.getInstance(properties, null);

        MimeMessage mimeMessage = new MimeMessage(session);
        mimeMessage.setSubject(subject);
        Multipart mp = new MimeMultipart();
        BodyPart bp1 = new MimeBodyPart();
        bp1.setContent(content, "text/html;charset=UTF-8");
        mp.addBodyPart(bp1);
        mimeMessage.setContent(mp);
        mimeMessage.setFrom(new InternetAddress(userName));
        mimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toMail));
        mimeMessage.saveChanges();
        try {
            Transport transport = session.getTransport("smtp");
            transport.connect(mailSmtp, userName, password);
            transport.sendMessage(mimeMessage, mimeMessage.getRecipients(Message.RecipientType.TO));
            transport.close();
        }
        catch (Exception e) {
            // TODO: handle exception
        }

        //		/** 建立邮件消息 **/
        //		SimpleMailMessage mailmessage = new SimpleMailMessage();
        //		
        //		/** 设置收件人及发件人 **/
        //		mailmessage.setTo(toMail);
        //		/** 发件人必须与邮箱的登录帐号一致 **/
        //		mailmessage.setFrom(userName);
        //
        //		/** 设置邮件主题和内容 **/
        //		mailmessage.setSubject(subject);
        //		mailmessage.setText(content);
        //		/** 发送邮件 **/
        //		try {
        //			senderimpl.send(mailmessage);
        //		} catch (Exception e) {
        //			// TODO: handle exception
        //		}

    }

    public static void sendMail(String subject, String content, String[] toMail) throws Exception {
        /** 设定邮件的字符编码 **/
        senderimpl.setDefaultEncoding(encoding);

        /** 设定mail server **/
        senderimpl.setHost(mailSmtp);

        /** 设置邮箱的登录帐号及密码 **/
        senderimpl.setUsername(userName);
        senderimpl.setPassword(password);

        /** 这部分是必须的 **/
        Properties properties = new Properties();
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.timeout", timeout);
        senderimpl.setJavaMailProperties(properties);

        Session session = Session.getInstance(properties, null);

        /** 建立邮件消息 **/
        SimpleMailMessage mailmessage = new SimpleMailMessage();
        /** 群发时对数组进行分割,每组20个邮箱 **/
        Object[] subArr = splitAry(toMail, 20);
        if (subArr != null) {
            for (int i = 0; i < subArr.length; i++) {
                String[] newToMail = (String[]) subArr[i];
                Address[] addresses = new Address[newToMail.length];
                for (int j = 0; j < newToMail.length; j++) {
                    addresses[j] = new InternetAddress(newToMail[j]);
                }
                MimeMessage mimeMessage = new MimeMessage(session);
                mimeMessage.setSubject(subject);
                Multipart mp = new MimeMultipart();
                BodyPart bp1 = new MimeBodyPart();
                bp1.setContent(content, "text/html;charset=UTF-8");
                mp.addBodyPart(bp1);
                mimeMessage.setContent(mp);
                mimeMessage.setFrom(new InternetAddress(userName));
                mimeMessage.setRecipients(Message.RecipientType.TO, addresses);
                mimeMessage.saveChanges();
                try {
                    Transport transport = session.getTransport("smtp");
                    transport.connect(mailSmtp, userName, password);
                    transport.sendMessage(mimeMessage, mimeMessage.getRecipients(Message.RecipientType.TO));
                    transport.close();
                }
                catch (Exception e) {
                    // TODO: handle exception
                }

                //				/** 设置收件人及发件人 **/
                //				mailmessage.setTo(newToMail);
                //				/** 发件人必须与邮箱的登录帐号一致 **/
                //				mailmessage.setFrom(userName);
                //
                //				/** 设置邮件主题和内容 **/
                //				mailmessage.setSubject(subject);
                //				mailmessage.setText(content);
                //			    
                //				/** 发送邮件 **/
                //				try {
                //					senderimpl.send(mailmessage);
                //				} catch (Exception e) {
                //					// TODO: handle exception
                //				}
            }
        }

    }


    /***
     * 
     *@Title:splitAry
     *@TODO:对数组进行分割
     *@data:2014-3-25
     *@param:@param ary
     *@param:@param subSize
     *@param:@return
     *@return:Object[]
     *@author:孟凡岭
     *@thorws:
     */
    private static Object[] splitAry(String[] ary, int subSize) {
        if (ary != null) {
            int count = ary.length % subSize == 0 ? ary.length / subSize : ary.length / subSize + 1;

            List<List<String>> subAryList = new ArrayList<List<String>>();

            for (int i = 0; i < count; i++) {
                int index = i * subSize;

                List<String> list = new ArrayList<String>();
                int j = 0;
                while (j < subSize && index < ary.length) {
                    list.add(ary[index++]);
                    j++;
                }

                subAryList.add(list);
            }

            Object[] subAry = new Object[subAryList.size()];

            for (int i = 0; i < subAryList.size(); i++) {
                List<String> subList = subAryList.get(i);

                String[] subAryItem = new String[subList.size()];
                for (int j = 0; j < subList.size(); j++) {
                    subAryItem[j] = subList.get(j);
                }

                subAry[i] = subAryItem;
            }
            return subAry;
        }
        return null;
    }

}
