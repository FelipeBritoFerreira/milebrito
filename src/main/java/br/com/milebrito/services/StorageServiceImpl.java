package br.com.milebrito.services;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.Signature;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.SecurityUtils;
import com.google.api.services.storage.Storage;
import com.google.api.services.storage.StorageScopes;
import com.google.api.services.storage.model.Objects;
import com.google.api.services.storage.model.StorageObject;

import br.com.milebrito.beans.Foto;
import br.com.milebrito.beans.Galeria;
import br.com.milebrito.beans.TipoFoto;
import br.com.milebrito.beans.TipoTituloSubTitulo;





public class StorageServiceImpl {

	private Log log = LogFactory.getLog(StorageServiceImpl.class);
	
	private Storage storageService;
	private final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
	private static final String APPLICATION_NAME = "milebrito";
	private static final String BUCKET_NAME = "milebrito-1362.appspot.com";
	private static final String SERVICE_ACCOUNT_EMAIL = "milebrito-bucket@milebrito-1362.iam.gserviceaccount.com";
	private static List<StorageObject> OBJETOS = new ArrayList<>();
	private static final String FOTO_CAPA = "01capa.jpg";
	private static final String FOTO_CONTRACAPA = "01.jpg";
	
	
	private PrivateKey key;
	
	
	public StorageServiceImpl() {
		try {
			key = loadKeyFromPkcs12("notasecret".toCharArray());
			OBJETOS = listarObjetosStorage();
		} catch (Exception ex) {
			throw new RuntimeException("Erro - ", ex);
		}
	}
	
	
	
	/**
	 * Lista todas as imagens parte do carrossel da home do site
	 * @return
	 * @throws Exception
	 */
	public List<String> listarUrlsImagensCarrossel() throws Exception {
		
		List<String> urls = new ArrayList<>();
		
		List<StorageObject> objetos  = OBJETOS;
		
		for ( StorageObject obj : objetos  ) {
			
			if (  ! obj.getName().equals("img_carrossel/") && obj.getName().startsWith("img_carrossel/") ) {
				
				urls.add( getSigningURL("GET", obj.getName()) );
				
			}
			
		}
		
		return urls;
	}
	
	
	
	
	/**
	 * Lista as galerias com as fotos
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<Galeria> listarGalerias() throws Exception {
		
		List<Galeria> galerias = new ArrayList<>();
		
		List<String> pastasStorage = listarPastas();
		
		for (  String pasta : pastasStorage  ) {
			
			Galeria galeria = new Galeria();
			galeria.setNomeGaleria(pasta);
			
			TipoTituloSubTitulo tituloSubTitulo = TipoTituloSubTitulo.porNomeGaleria(galeria.getNomeGaleria());
			galeria.setTitulo( tituloSubTitulo.getTitulo()  );
			galeria.setSubTitulo( tituloSubTitulo.getSubtitulo()  );
			
			List<Foto> fotos = listarFotosPorNomeGaleria(galeria.getNomeGaleria());
			List<Foto> fotosRemover = new ArrayList<>();
			
			for (Foto foto : fotos) {
				
				switch (foto.getNomeArquivo()) {
				case FOTO_CAPA:
					galeria.setFotoCapa(foto);
					fotosRemover.add(foto);
					break;
				case FOTO_CONTRACAPA:
					galeria.setFotoContracapa(foto);
					fotosRemover.add(foto);
					break;
				default:
					break;
				}
				
			}
			
			fotos.removeAll(fotosRemover);
			
			galeria.setFotos(fotos);
			
			galerias.add(galeria);
			
		}
		
		
		return galerias;
	}
	
	
	
	
	
	
	/**
	 * Lista toda as fotos de uma determinada galeria
	 * 
	 * @param nomeGaleria
	 * @return
	 * @throws Exception
	 */
	private List<Foto> listarFotosPorNomeGaleria(String nomeGaleria) throws Exception {
		
		List<Foto> fotos = new ArrayList<>();
		
		List<StorageObject> objetos  = OBJETOS;
		
		for (  StorageObject obj : objetos  ) {
			
			String pasta = obj.getName();
			
			if (  ! obj.getName().equals("galerias/") && obj.getName().startsWith("galerias/") ) {

				pasta = pasta.substring( pasta.indexOf("/") + 1 ,  pasta.lastIndexOf("/") );
				
				if ( pasta.equals(nomeGaleria) ) {
					
					String nomeFoto = obj.getName().substring( obj.getName().lastIndexOf("/") + 1 , obj.getName().length());
					
					Foto foto = new Foto();
					foto.setNomeArquivo(nomeFoto);
					foto.setUrl(  getSigningURL("GET", obj.getName())  );
					
					switch (nomeFoto) {
					case FOTO_CAPA:
						foto.setTipoFoto(TipoFoto.CAPA);
						break;
					case FOTO_CONTRACAPA:
						foto.setTipoFoto(TipoFoto.CONTRACAPA);
						break;
					default:
						foto.setTipoFoto(TipoFoto.COMUM);
						break;
					}
					
					fotos.add(foto);
					
				}
				
				
			}
			
		}
		
		return fotos;
		
	}
	
	
	
	
	
	/**
	 * Lista todas as pastas da galeria
	 * 
	 * @return
	 * @throws Exception
	 */
	private List<String> listarPastas() throws Exception {
		
		Set<String> pastas = new LinkedHashSet<>();
		
		List<StorageObject> objetos  = OBJETOS;
		
		for (  StorageObject obj : objetos  ) {
			
			String pasta = obj.getName();
			
			if (  ! obj.getName().equals("galerias/") && obj.getName().startsWith("galerias/") ) {
				
				pasta = pasta.substring( pasta.indexOf("/") + 1 ,  pasta.lastIndexOf("/") );
				
				pastas.add(pasta);
				
			}
			
		}

		List<String> retorno = new ArrayList<>();
		
		retorno.addAll(pastas);
		
		return retorno;
	}
	
	
	

	
	
	
	
	/**
	 * Lista todos os objetos do storage
	 * 
	 * @return
	 * @throws Exception
	 */
	private List<StorageObject> listarObjetosStorage() throws Exception {
		
		List<StorageObject> objetos = new ArrayList<>();
		
		Storage.Objects.List listObjects = getStorageService().objects().list(BUCKET_NAME);
		
		Objects objects;
		
		do {
			  objects = listObjects.execute();
			  
			  for (StorageObject object : objects.getItems()) {
				  
				  //urls.add( getSigningURL("GET", object.getName()) );
				  objetos.add(object);
				  
			  }
			  
			  listObjects.setPageToken(objects.getNextPageToken());
			  
			} while (null != objects.getNextPageToken());
		
		
		return objetos;
		

	}
	
	
	
	
	
	/************************
	 * 
	 * 		AUTENTICACAO
	 * 
	 * **********************
	 */
	
	
	/**
	 * Servico de autenticacao para acesso ao bucket, em qualquer ambiente
	 * 
	 * @return
	 * @throws IOException
	 * @throws GeneralSecurityException
	 */
	private Storage getStorageService() throws IOException, GeneralSecurityException {
		
		log.info("[STORAGE] - Autenticacao");

		if (null == storageService) {
			// GoogleCredential credential =
			// GoogleCredential.getApplicationDefault();
			// Depending on the environment that provides the default
			// credentials (e.g. Compute Engine,
			// App Engine), the credentials may require us to specify the scopes
			// we need explicitly.
			// Check for this case, and inject the Cloud Storage scope if
			// required.
			PrivateKey serviceAccountPrivateKey = SecurityUtils.loadPrivateKeyFromKeyStore(SecurityUtils.getPkcs12KeyStore(), this.getClass().getClassLoader().getResourceAsStream("milebrito-0c739b8ff6d5.p12"), "notasecret", "privatekey", "notasecret");

			HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
			// Build service account credential.
			
			GoogleCredential credential = new GoogleCredential.Builder()
					.setTransport(httpTransport)
					.setJsonFactory(JSON_FACTORY)
					.setServiceAccountId(SERVICE_ACCOUNT_EMAIL)
					.setServiceAccountScopes(Collections.singleton(StorageScopes.DEVSTORAGE_FULL_CONTROL))
					.setServiceAccountPrivateKey(serviceAccountPrivateKey).build()
					
					;
			
				if (credential.createScopedRequired()) {
					credential = credential.createScoped(StorageScopes.all());
				}
				

			storageService = new Storage.Builder(httpTransport, JSON_FACTORY, credential).setApplicationName(APPLICATION_NAME).build();
		}
		
		return storageService;
	}

	
	/**
	 * Assinatura digital de URL
	 * 
	 * @param verb
	 * @param objectName
	 * @return
	 * @throws Exception
	 */
	private String getSigningURL(String verb, String objectName) throws Exception {

		String signed_url = "";
		Calendar c = Calendar.getInstance();
		c.add(Calendar.YEAR, 10000);
		final long expiration = c.getTimeInMillis();

		String url_signature = this.signString(verb + "\n\n\n" + expiration + "\n" + "/" + BUCKET_NAME + "/" + objectName);
		signed_url = "https://storage.googleapis.com/" + BUCKET_NAME + "/" + objectName + "?GoogleAccessId="
				+ SERVICE_ACCOUNT_EMAIL + "&Expires=" + expiration + "&Signature="
				+ URLEncoder.encode(url_signature, "UTF-8");

		return signed_url;
	}
	
	
	/**
	 * URL assinada
	 * 
	 * @param stringToSign
	 * @return
	 * @throws Exception
	 */
	private String signString(String stringToSign) throws Exception {
		
		if (key == null) {
			throw new Exception("Private Key not initalized");
		}
		Signature signer = Signature.getInstance("SHA256withRSA");
		signer.initSign(key);
		signer.update(stringToSign.getBytes("UTF-8"));
		byte[] rawSignature = signer.sign();
		return new String(Base64.encodeBase64(rawSignature, false), "UTF-8");
	}	
	
	
	/**
	 * Busca a chave de acesso P12
	 * 
	 * @param password
	 * @return
	 * @throws Exception
	 */
	private PrivateKey loadKeyFromPkcs12(char[] password) throws Exception {
		
		InputStream fis = this.getClass().getClassLoader().getResourceAsStream("milebrito-0c739b8ff6d5.p12");
		log.info(String.format("Carregando p12 %s", fis));
		KeyStore ks = KeyStore.getInstance("PKCS12");
		ks.load(fis, password);
		return (PrivateKey) ks.getKey("privatekey", password);
	}	
	
	
	

}
