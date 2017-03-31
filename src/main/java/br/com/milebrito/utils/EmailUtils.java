package br.com.milebrito.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import br.com.milebrito.beans.Email;


/**
 * 
 * @author FBF0113
 *
 */
public class EmailUtils {
	
	private Log log = LogFactory.getLog(EmailUtils.class);
	
	private static final String REMETENTE = "fel.brito@gmail.com";
	private static final String ASSUNTO = "Contato Website MileBrito";
	

	/**
	 * Envia o email de contato
	 * @param destinatarios
	 * @param assunto
	 * @param mensagem
	 * @throws MessagingException
	 */
    public void envia(Email email) throws MessagingException  {
    	
    	List<String> destinatarios = new ArrayList<String>();
    	destinatarios.add("fel.brito@gmail.com");
    	destinatarios.add("camilepapazian@gmail.com");
    	
    	try {
    		
	        Properties props = new Properties();
	        Session session = Session.getDefaultInstance(props, null);

	        Message msg = new MimeMessage(session);

	        msg.setFrom(new InternetAddress(REMETENTE, "MileBrito Contato"));
	        msg.setReplyTo(new InternetAddress[] { new InternetAddress(REMETENTE) });

	        for (int i = 0; i < destinatarios.size(); i++) {
	            if (i == 0) {
	                msg.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatarios.get(i)));
	            } else {
	                msg.addRecipient(Message.RecipientType.CC, new InternetAddress(destinatarios.get(i)));
	            }
	        }

	        msg.setSubject(ASSUNTO);
	        
	        msg.setContent(prepararMensagemHtml(email), "text/html");
    		
        	Transport.send(msg);
        	
    	} catch (AddressException e) {
    		log.error(e);
    		throw new AddressException(e.getMessage());
    	} catch (MessagingException e) {
    		log.error(e);
    		throw new MessagingException(e.getMessage());
    	} catch (Exception e) {
			log.error(e);
			throw new RuntimeException(e);
		}
        

    }
    
    
    
    
    private String prepararMensagemHtml(Email email) {
    	
    	return "<html><body><p><strong>Nome</strong>: "+  email.getNomeRemetente() + "</p><p><strong>E-mail:</strong>: "+ email.getEnderecoEmailRemetente() +" </p><p><strong>Mensagem:</strong> <br> "+email.getTextoMensagem()+"</p></body></html>";
    	
    }

    
}