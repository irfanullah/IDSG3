package Services;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

public class EmailAdopter {


    private static EmailAdopter emailAdopter = new EmailAdopter();
    private static AppEnv appEnv = new AppEnv();
    //private static General Utils = null;


    private EmailAdopter() {
    }

    /* Static 'instance' method */
    public static EmailAdopter getInstance(AppEnv appEnv) {
        EmailAdopter.appEnv = appEnv;
        //Utils = General.getInstance(appEnv);
        return emailAdopter;
    }

    public void Email_Report() {
        DateTime dt = new DateTime();
        final String username = "irfanidsqa@gmail.com"; //change to your Gmail username
        final String password = "P@ccw0rdIU"; //change to your Gmail password
        final String from = "irfanisdqa@gmail.com"; //change to from email address
        final String to = appEnv.getToEmailAddress(); //change to to email address
        final String cc1 = appEnv.getCCEmailAddress(); //change to cc email address
      //  final String cc2 = "cristiana.gosman@ids-astra.com";
        // final String bcc = "raiirfan@gmail.ocm"; //change to bcc email address
        final String subject = "G3 Work Orders : Test Cases Execution Report - " + dt.toString(DateTimeFormat.longDate()); //change to your subject
        final String msg = "Hi, Please find attached G3 Work Order on Web Test Cases Execution Report. Happy Testing"; //change to your message

        Properties props = new Properties();
        props.put("mail.smtp.auth", true);
        props.put("mail.smtp.starttls.enable", true);
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator()
        {
            protected PasswordAuthentication getPasswordAuthentication()
            {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));
            String address = appEnv.getCCEmailAddress();
            InternetAddress[] iAdressArray = InternetAddress.parse(address);
            message.setRecipients(Message.RecipientType.CC, iAdressArray);
            //below code only requires if your want cc email address
            //message.setRecipients(Message.RecipientType.CC,InternetAddress.parse(cc1));
            //message.setRecipients(Message.RecipientType.CC,InternetAddress.parse(cc2));
            message.setSubject(subject);
            //message.setText(msg);
            String imagePath = "./src/main/resources/Reports/emailable-extent.png";
            String fileName = imagePath.substring(imagePath.lastIndexOf('/') + 1);
            String htmlText = "<html><body>"+msg+"<img src=\"cid:img1\"></body></html>";
            MimeMultipart multipart = new MimeMultipart("related");
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent(htmlText, "text/html; charset=utf-8");
            multipart.addBodyPart(messageBodyPart);
            messageBodyPart = new MimeBodyPart();
            DataSource fds = new FileDataSource(imagePath);
            messageBodyPart.setDataHandler(new DataHandler(fds));
            messageBodyPart.setHeader("Content-ID", "<img1>");
            messageBodyPart.setDisposition(MimeBodyPart.INLINE);
            messageBodyPart.setFileName(fileName);
            multipart.addBodyPart(messageBodyPart);
            message.setContent(multipart);
            MimeBodyPart messageBodyPart2 = new MimeBodyPart();
            String filename = "./src/main/resources/Reports/" + appEnv.getReportName();
            DataSource source = new FileDataSource(filename);
            messageBodyPart2.setDataHandler(new DataHandler(source));
            messageBodyPart2.setFileName(appEnv.getReportName());
            multipart.addBodyPart(messageBodyPart2);
            Transport.send(message);

            System.out.println("Sent message successfully....");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}




