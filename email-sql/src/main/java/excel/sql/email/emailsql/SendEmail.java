package excel.sql.email.emailsql;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class SendEmail {

    public static void generateEmail(String emailFrom, String subject, String content, String emailTo) throws MessagingException {

    Properties properties = System.getProperties();
    properties.put("mail.smtp.host","smtp.gmail.com");
    properties.put("mail.smtp.port","465");
    properties.put("mail.smtp.ssl.enable","true");
    properties.put("mail.smtp.auth","true");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("smohture01@gmail.com", "mxwsmagjewhivvmx");
            }
        });

        try {

        MimeMessage mimeMessage = new MimeMessage(session);
        mimeMessage.setSubject(subject);
        mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(emailTo));
        mimeMessage.setFrom(emailFrom);

        MimeMultipart multipart = new MimeMultipart();
        MimeBodyPart text = new MimeBodyPart();
        MimeBodyPart attachment = new MimeBodyPart();

        text.setText(content);
        File file = new File("D:\\QR code\\"+emailTo+".png");
        attachment.attachFile(file);

        multipart.addBodyPart(text);
        multipart.addBodyPart(attachment);

        mimeMessage.setContent(multipart);

        Transport.send(mimeMessage);

        System.out.println("Email sent to: "+emailTo);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

}
