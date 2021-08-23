package com.icia.kream.common;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class KreamMail {

    private static final String user = "kmahn0719@gmail.com";
    private static final String password = "K1ream1234";

    public static void send(String title, String contents, String recipient){
        Session session = Session.getDefaultInstance(getGmailProperties(), getGmailAuthenticator());

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient)); //수신자메일주소
            message.setSubject(title); // 메일 제목
            message.setText(contents);    // 메일 내용

            Transport.send(message); // 전송
            System.out.println("메일 내용 :" + contents);
            System.out.println("message sent successfully...");
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

    private static Properties getGmailProperties(){
        // SMTP 서버 정보를 설정한다.
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", 465);
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.ssl.enable", "true");
        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        return prop;
    }

    private static Authenticator getGmailAuthenticator(){
        return new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password);
            }
        };
    }

    public static String getMailContents(String token) {
		StringBuilder builder = new StringBuilder();
		builder.append("안녕하세요.\n");
		builder.append("임시 비밀번호를 전달해드립니다.\n\n");
		builder.append(token + "\n\n");
		builder.append("비밀번호 수정을 위해 하단 링크에서 임시 비밀번호를 입력해주시기 바랍니다.\n");
		builder.append("http://localhost:8081/kream/memberlogin");
		return builder.toString();
	}
		
}
