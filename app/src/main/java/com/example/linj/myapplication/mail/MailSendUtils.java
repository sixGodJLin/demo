package com.example.linj.myapplication.mail;

import android.util.Log;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * @author JLin
 * @date 2019/12/9
 * @describe 邮箱发送工具类
 */
public class MailSendUtils {
    /**
     * 以文本格式发送邮件
     *
     * @param mail 待发送的邮件的信息
     */
    public void sendTextMail(Mail mail, sendCallback sendCallback) {
        // 判断是否需要身份认证
        Authenticator authenticator = null;
        Properties pro = mail.getProperties();
        if (mail.isValidate()) {
            // 如果需要身份认证，则创建一个密码验证器
            authenticator = createAuthenticator(mail);
        }
        // 根据邮件会话属性和密码验证器构造一个发送邮件的session
        Session sendMailSession = Session.getDefaultInstance(pro, authenticator);

        try {
            // 根据session创建一个邮件消息
            Message mailMessage = new MimeMessage(sendMailSession);
            // 创建邮件发送者地址
            Address from = new InternetAddress(mail.getFromAddress());
            // 设置邮件消息的发送者
            mailMessage.setFrom(from);
            // 设置三种地址：收件人、抄送人、加密
            setAddress(mailMessage, mail);
            // 设置邮件消息的主题
            mailMessage.setSubject(mail.getSubject());
            // 设置邮件消息发送的时间
            mailMessage.setSentDate(new Date());
            // 设置邮件消息的主要内容
            String mailContent = mail.getContent();
            mailMessage.setText(mailContent);
            // 发送邮件
            Transport.send(mailMessage);
            sendCallback.success();
            Log.e("发送成功", "--------");
        } catch (MessagingException ex) {
            ex.printStackTrace();
            Log.e("发送失败", ex.getMessage() + "");
            sendCallback.fail(ex.getMessage());
        }
    }

    /**
     * 发送带附件的邮件
     *
     * @param mail
     * @return
     */
    public void sendFileMail(Mail mail, String filePath) {
        // 判断是否需要身份认证
        Authenticator authenticator = null;
        Properties pro = mail.getProperties();
        if (mail.isValidate()) {
            // 如果需要身份认证，则创建一个密码验证器
            authenticator = createAuthenticator(mail);
        }
        // 根据邮件会话属性和密码验证器构造一个发送邮件的session
        Session sendMailSession = Session.getDefaultInstance(pro, authenticator);

        try {
            // 根据session创建一个邮件消息
            Message mailMessage = new MimeMessage(sendMailSession);
            // 创建邮件发送者地址
            Address from = new InternetAddress(mail.getFromAddress());
            // 设置邮件消息的发送者
            mailMessage.setFrom(from);
            // 设置三种地址：收件人、抄送人、加密
            setAddress(mailMessage, mail);
            // 设置邮件消息的主题
            mailMessage.setSubject(mail.getSubject());
            // 设置邮件消息发送的时间
            mailMessage.setSentDate(new Date());

            // 添加附件体
            BodyPart messageBodyPart = new MimeBodyPart();
            // 设置邮件消息的主要内容
            messageBodyPart.setContent(mail.getContent(), "text/html;charset=utf-8");
            MimeMultipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            MimeBodyPart bodyPart = new MimeBodyPart();
            try {
                //绑定附件路径
                bodyPart.attachFile(filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
            multipart.addBodyPart(bodyPart);
            mailMessage.setContent(multipart);
            // 发送邮件
            Log.e("发送", "--------");
            Transport.send(mailMessage);
            Log.e("发送成功", "--------");
        } catch (MessagingException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 设置 收件人地址、抄送人、加密发送人地址
     *
     * @param mailMessage
     */
    private void setAddress(Message mailMessage, Mail mail) throws MessagingException {
        String[] toAddress = mail.getToAddress();
        if (toAddress != null) {
            for (String address : toAddress) {
                // 创建邮件的接收者地址，并设置到邮件消息中
                InternetAddress to = new InternetAddress(address);
                mailMessage.addRecipient(Message.RecipientType.TO, to);
            }
        }
        String[] ccAddress = mail.getCcAddress();

        if (ccAddress != null) {
            for (String address : ccAddress) {
                // 创建抄送邮件的接收者地址，并设置到邮件消息中
                InternetAddress cc = new InternetAddress(address);
                mailMessage.addRecipient(Message.RecipientType.CC, cc);
            }
        }
        String[] bccAddress = mail.getBccAddress();

        if (bccAddress != null) {
            for (String address : bccAddress) {
                // 创建秘密抄送邮件的接收者地址，并设置到邮件消息中
                InternetAddress bcc = new InternetAddress(address);
                mailMessage.addRecipient(Message.RecipientType.BCC, bcc);
            }
        }
    }

    /**
     * 可以重写此方法，如果有必要
     *
     * @param info
     * @return Authenticator
     */
    private Authenticator createAuthenticator(final Mail info) {
        return new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(info.getUserName(), info.getPassword());
            }
        };
    }

    public interface sendCallback {
        void success();

        void fail(String message);
    }
}
