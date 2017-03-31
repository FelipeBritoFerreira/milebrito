package br.com.milebrito.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.appengine.api.utils.SystemProperty;

import br.com.milebrito.beans.Galeria;
import br.com.milebrito.services.StorageServiceImpl;

/**
 * Spring MVC Controller
 * <br>	
 * Controla as chamadas das telas
 * @author Felipe Brito
 *
 */
@Controller
public class MileBritoController {
	
	private Log log = LogFactory.getLog(MileBritoController.class);
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ssss"); 
	
	/**
	 * Inicia o sistema
	 * @return
	 */
	@RequestMapping("/")
	public String index(Model model) {
		
		String horaInicial = sdf.format(new Date());
		log.info(horaInicial);
		
		String ambiente = SystemProperty.Environment.environment.get();
		log.info("[INDEX] - Ambiente: " + ambiente);
		
		
		try {
			
			StorageServiceImpl storageServiceImpl = new StorageServiceImpl();
			List<Galeria> galerias = storageServiceImpl.listarGalerias();
			List<String> carrossel = storageServiceImpl.listarUrlsImagensCarrossel();
			
			model.addAttribute("galerias", galerias);
			model.addAttribute("carrossel", carrossel);
			
			String horaFinal = sdf.format(new Date());
			log.info(horaFinal);

			return "index";
			
		} catch (Exception e) {
			log.error(e);
			throw new RuntimeException(e);
		} 
		
	}
	
	

	

}





