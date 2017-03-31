package br.com.milebrito.controller;

import java.util.List;

import javax.mail.MessagingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.milebrito.beans.Email;
import br.com.milebrito.beans.Foto;
import br.com.milebrito.beans.Galeria;
import br.com.milebrito.controller.dto.EmailDTO;
import br.com.milebrito.controller.dto.EmailResponseDTO;
import br.com.milebrito.controller.dto.converter.EmailDTOConverter;
import br.com.milebrito.services.StorageServiceImpl;
import br.com.milebrito.utils.EmailUtils;

/**
 * Spring MVC Controller
 * <br>	
 * Controla as chamadas das telas
 * @author Felipe Brito
 *
 */
@RestController
public class MileBritoRestController {
	
	private Log log = LogFactory.getLog(MileBritoRestController.class);
	
	
	/**
	 * Envia email de contato 
	 * 
	 * @param emailDto
	 * @return
	 */
	@RequestMapping("/email/enviar")
	public EmailResponseDTO enviarEmail(@RequestBody EmailDTO emailDto) {
		
		Email email = EmailDTOConverter.fromDTO(emailDto);
		EmailResponseDTO response = new EmailResponseDTO();
		EmailUtils utils = new EmailUtils();

		try {
			utils.envia(email);
			response.setErro(false);
		} catch (MessagingException e) {
			response.setErro(true);
			log.error(e);
		}
		
		return response;
	}	
	
	
	/**
	 * Testes com o storage (bucket)
	 * @return
	 */
	@RequestMapping("/galeria/listar")
	public String listarGalerias() {
		
		StorageServiceImpl storageServiceImpl = new StorageServiceImpl();
		
		try {
			
			List<Galeria> galerias = storageServiceImpl.listarGalerias();
			
			StringBuilder html = new StringBuilder();
			
			html.append("<div class=\"highlight-info\">");
			html.append("<div class=\"overlay spacer\">");
			html.append("<h3 class=\"text-center wowload fadeInUp\">Momentos eternizados por minhas lentes</h3>");
			html.append("</div>");
			html.append("</div>");
			
			for (Galeria galeria : galerias) {
				
				html.append("<div id=\"galeria-"+galeria.getNomeGaleria()+"\" onclick=\"abrirGaleria('foto-"+galeria.getNomeGaleria()+"', window.event)\">");
					
					html.append("<figure class=\"effect-oscar  wowload fadeInUp\">");
					html.append("<img src=\""+galeria.getFotoCapa().getUrl()+"\" alt=\"\"/>");
					html.append("<figcaption>");
					html.append("<p>"+galeria.getTitulo()+"</p>");
					html.append("<p>"+galeria.getSubTitulo()+"<br>");
					
					html.append("<a href="+galeria.getFotoContracapa().getUrl()+" class=\"foto-"+galeria.getNomeGaleria()+"\" title=\"\">GALERIA</a>");
					
					html.append("</p>");
					html.append("</figcaption>");
					html.append("</figure>");
					
					for (Foto foto : galeria.getFotos()) {
						
						html.append("<a href="+foto.getUrl()+" title=\"\" class=\"foto-"+galeria.getNomeGaleria()+"\" style=\"display: none\"></a>");
						
					}
					
				html.append("</div>");
				
			}
				
			return html.toString();
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}
	
	

}





