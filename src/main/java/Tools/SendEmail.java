package Tools;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SendEmail {
    private final static String fromEmail = "iamdevgenz@gmail.com";
    private final static String fromPassword = "#DoDucKhai@BN";

    public static boolean sendEmail(String toEmail, String subject, String text) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
        props.put("mail.smtp.port", "587"); //TLS Port
        props.put("mail.smtp.auth", "true"); //enable authentication
        props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS

        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, fromPassword);
            }
        };

        Session session = Session.getInstance(props, auth);

        try {
            MimeMessage msg = new MimeMessage(session);
            //Headers
            msg.addHeader("Content-Type", "text/plain; charset=UTF-8");
            msg.setFrom(new InternetAddress("DongPhong"));
            msg.setSubject(subject);
            msg.setText(text);
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));

            Transport.send(msg);
        } catch (MessagingException e) {
            return false;
        }

        return true;
    }
}
