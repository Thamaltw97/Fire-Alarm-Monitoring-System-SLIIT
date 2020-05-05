
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


/**
 *
 * @author Thamal Wijetunge
 */
public class Email {
    
    public static final String recipient = "stdrecipient2020@gmail.com"; 
    public static final String sender = "stdsender2020@gmail.com"; 
    public static final String host = "smtp.gmail.com"; 
    
    public static void sendMail() {
          Properties properties = System.getProperties(); 

          properties.put("mail.smtp.host", host);
          properties.put("mail.smtp.port", "465");
          properties.put("mail.smtp.ssl.enable", "true");
          properties.put("mail.smtp.auth", "true");
          
          Session session = Session.getDefaultInstance(properties, new Authenticator() {
              protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("stdsender2020@gmail.com", "student321");
            }
          });
          
          //session.setDebug(true);

         try 
          { 
             // MimeMessage object. 
             MimeMessage message = new MimeMessage(session); 
      
             // Set From Field: adding senders email to from field. 
             message.setFrom(new InternetAddress(sender)); 
      
             // Set To Field: adding recipient's email to from field. 
             message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient)); 
      
             // Set Subject: subject of the email 
             message.setSubject("Alert"); 
      
             // set body of the email. 
             message.setText("Fire Alarm Danger Alert !"); 
      
             // Send email. 
             Transport.send(message); 
             System.out.println("Mail successfully sent"); 
          } 
          catch (MessagingException mex)  
          { 
             mex.printStackTrace(); 
          } 
    }
    
}
