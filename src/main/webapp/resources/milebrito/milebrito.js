var btEnviar = '<button type="submit" class="btn btn-primary" ><i class="fa fa-paper-plane"></i> Enviar</button>';
var btEnviando = '<button type="submit" class="btn btn-primary" >Enviando mensagem...</button>';

var erroEnvioMensagem = '<div class="alert alert-danger alert-dismissible" role="alert"><button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button><strong>Desculpe o transtorno.</strong> Entre em contato pelo Facebook ou pelo Instagram. Os links estão no final da página. <i class="fa fa-smile-o"></i> </div>';
var msgEnviada = '<div class="alert alert-success alert-dismissible" role="alert"><button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>Obrigada pelo contato. Responderei em breve. <i class="fa fa-smile-o"></i> </div>';

var progressBarGaleria ='<div class="col-sm-4"></div><div class="alert alert-success col-sm-4" role="alert">BUSCANDO <img  src="/resources/images/sunny.gif"  > GALERIA</div><div class="col-sm-4"></div>';





$(document).ready(function($) {


	//popular galeria
	$.ajax({
		type : "GET",
		url : "/galeria/listar",
		beforeSend : function() {
			$("#galeria").html(progressBarGaleria);		
		},
		success : function(data) {
			$("#galeria").html(data);		
		},
		error : function(e) {
			$("#galeria").html("Erro ao buscar a galeria");		
			console.log(e);
		}
	});
	//#popular galeria


	

	

	//envio email
	$('#div-bt-enviar').html(btEnviar);
	$("#email-form").submit(function(event) {
		$('#div-bt-enviar').html(btEnviando);
		//Previne o envio do formulario via browser
		event.preventDefault();
		enviarEmail();
	});
	//#envio email

	
});






/**
 * Funcionalidades da galeria de fotos
 * Usada quando na montagem do HTML da galeria dinamicamente, pelo Java na classe MileBritoRestController, método listarGalerias()
 * 
 * @param foto_classe
 * @param event
 */
function abrirGaleria(foto_classe, event) {
    event = event || window.event;
    var target = event.target || event.srcElement,
        link = target.src ? target.parentNode : target,
        options = {index: link, event: event},
        links = document.getElementsByClassName(foto_classe);
    blueimp.Gallery(links, options);
}





/**
 * Envia email
 * 
 */
function enviarEmail() {

	var objeto = {}
	objeto["nome"] = $("#nome").val();
	objeto["email"] = $("#email").val();
	objeto["mensagem"] = $("#mensagem").val();

	$.ajax({
		type : "POST",
		contentType : "application/json",
		url : "/email/enviar",
		data : JSON.stringify(objeto),
		dataType : 'json',
		timeout : 100000,
		success : function(data) {
			respostaEnvioEmail(data);
		},
		error : function(e) {
			respostaEnvioEmail(e);
		}
	});

}



/**
 * Obtém a resposta do envio do email
 * 
 * @param data
 */
function respostaEnvioEmail(data) {
	var json = JSON.parse(  JSON.stringify(data, null, 4)  );

	if (!json.erro) {//caso sucesso no envio
		$('#div-bt-enviar').html(btEnviar);
		$('#feedback').html(msgEnviada);
		$("#email-form")[0].reset();
	} else {
		$('#div-bt-enviar').html(btEnviar);
		$('#feedback').html(erroEnvioMensagem);
	}
	
	
}




