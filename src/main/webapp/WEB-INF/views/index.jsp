<%@include file="/WEB-INF/views/imports.jsp"%>


<html>

<head>

	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
	<meta name="description" content="Galeria de fotos da MileBrito Fotografias" />
	<meta name="googlebot" content="all" />
	<meta name="robots" content="all" />

<title>MileBrito</title>

</head>

<body>

<div class="topbar animated fadeInLeftBig"></div>

<!-- inicio headers -->
	<div class="navbar-wrapper">
      <div class="container">
      
        <div class="navbar navbar-inverse navbar-fixed-top" role="navigation" id="top-nav" style="background-color: white">
          <div class="container">
            <div class="navbar-header">
              <!-- Logo Starts -->
              <a class="navbar-brand" href="#home"><img  src="<c:url value="/resources/images/logo.png" />"  alt="logo"></a>
              <!-- #Logo Ends -->


              <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
              </button>

            </div>


            <!-- Nav Starts -->
            <div class="navbar-collapse  collapse">
              <ul class="nav navbar-nav navbar-right scroll">
                 <li class="active"><a href="#home"><i class="fa fa-home"></i> In&iacute;cio</a></li>
                 <li ><a href="#about" class="a-item-menu" ><i class="fa fa-female"></i> Mile</a></li>
                 <li ><a href="#galeria" class="a-item-menu"><i class="fa fa-camera"></i> Galeria</a></li>
                 <li ><a href="#contato" class="a-item-menu"><i class="fa fa-envelope-o"></i> Contato</a></li>
              </ul>
            </div>
            <!-- #Nav Ends -->

          </div>
        </div>

      </div>
    </div>
<!-- #inicio headers -->




<div id="home">
<!-- Slider Starts -->
<div id="myCarousel" class="carousel slide banner-slider animated bounceInDown" data-ride="carousel">     
      
      <div class="carousel-inner">
        
        <c:forEach items="${carrossel}" var="item" varStatus="cont" >
        
        	<c:choose>
        		<c:when test="${cont.index eq '0'}">
		        	<div class="item active">
        		</c:when>
        		<c:otherwise>
		        	<div class="item">
        		</c:otherwise>
        	</c:choose>
          				<img src="<c:url value="${item}" />" alt="banner">
          		</div>
		</c:forEach>
		</div>

      <a class="left carousel-control" href="#myCarousel" data-slide="prev"><span class="glyphicon-chevron-left"><i class="fa fa-angle-left"></i></span></a>
      <a class="right carousel-control" href="#myCarousel" data-slide="next"><span class="glyphicon-chevron-right"><i class="fa fa-angle-right"></i></span></a>
    </div>






<!-- Sobre mim -->

	<div id="about" class="container spacer about">
		<h3 class="text-center wowload fadeInUp">Um pouco sobre mim</h3><br>
		<div class="row">
			<div class="col-sm-5 wowload fadeInLeft" style="text-align: justify">
				<h4>
					<i class="fa fa-camera-retro"></i> A fot&oacute;grafa
				</h4>
				<p>Meu trabalho nasceu de duas grandes paix&otilde;es: fotografia e
					fam&iacute;lia. Meu objetivo &eacute;, de forma invis&iacute;vel, eternizar, atrav&eacute;s do
					meu olhar e das minhas lentes, os seus momentos mais marcantes.</p>
			</div>
			<div class="col-sm-2 wowload fadeInLeft"></div>
			<div class="col-sm-5 wowload fadeInRight" style="text-align: justify">
				<h4>
					<i class="fa fa-smile-o"></i>Como trabalho
				</h4>
				<p>Sou uma entusiasta! Fa&ccedil;o com que seu evento seja o mais
					importante do dia! Sou bem humorada, pau pra toda obra! E sou
					emp&aacute;tica, talvez a caracter&iacute;stica que considere mais importante no
					meu trabalho, que me ajuda a identificar seus desejos, ouvir suas
					necessidades e sentir suas emo&ccedil;&otilde;es. Enfim, meu profundo
					envolvimento me ajuda nas capturas mais emocionantes do seu evento.
					Conte tamb&eacute;m com minha responsabilidade, pontualidade, cortesia e
					generosidade.
				</p>
			</div>
		</div>

	</div>
	<!-- #Sobre mim -->




<!-- galeria de fotos -->
<div id="galeria"  class=" clearfix grid ensaio-galeria"></div>
<!-- #galeria de fotos -->







<!-- Bootstrap Image Gallery lightbox. Esta div deve ser parent direto do elemnto body -->
<div id="blueimp-gallery" class="blueimp-gallery blueimp-gallery-controls">
    <!-- The container for the modal slides -->
    <div class="slides"></div>
    <!-- Controls for the borderless lightbox -->
    <h3 class="title">Title</h3>
    <a class="prev"><i class="fa fa-angle-left"></i></a>
    <a class="next"><i class="fa fa-angle-right"></i></a>
    <a class="close">x</a>
    <!-- The modal dialog, which will be used to wrap the lightbox content -->    
</div>




<!-- Contato -->
<div id="contato" class="spacer">
<div class="container contactform center">

<h3 class="text-center wowload fadeInUp">
	Solicite seu or&ccedil;amento.<br>
</h3>
<h4 class="text-center wowload fadeInUp"> 
O que você idealizar, compartilhe comigo e faremos acontecer.<br><br> 
Acharemos uma forma que se ajuste &agrave;s suas necessidades e disponibilidades. 
</h4>

  <div class="row wowload fadeInLeftBig">  
  	<div class="col-sm-6 col-sm-offset-3 col-xs-12"> 
  	
  		<form id="email-form">
	  	    
	  	    <input id="nome" type="text" placeholder="Nome" required />
	        <input id="email" type="email" placeholder="E-mail" required />
	        <textarea id="mensagem" rows="5" placeholder="Mensagem" required ></textarea>
	        <div id="div-bt-enviar"></div>
        </form>
        
		<div id="feedback"></div>      
  	
  	</div>
  </div>
</div>
</div>
<!-- #Contato -->






<!-- Footer -->
<div id="footer" class="footer text-center spacer">
<p class="wowload flipInX">
	<a href="http://www.facebook.com/milebritofotografiadefamilia" target="_blank"><i class="fa fa-facebook fa-2x"></i></a> 
	<a href="https://www.instagram.com/milebritofotografia/" target="_blank"><i class="fa fa-instagram fa-2x"></i></a> 
	<!--    <a href="https://mail.google.com/mail/?view=cm&fs=1&to=contato@milebrito.com.br" target="_blank"><i class="fa fa-envelope-o fa-2x"></i></a> --> 
	<!-- a href="#"><i class="fa fa-twitter fa-2x"></i></a --> 
	<!-- a href="#"><i class="fa fa-flickr fa-2x"></i></a --> 
</p>
	<jsp:useBean id="date" class="java.util.Date"></jsp:useBean>
	MileBrito &#169; <fmt:formatDate value="${date}" pattern="yyyy"/>. Todos os direitos reservados.
</div>
<!-- # Footer -->
<a href="#home" class="gototop "><i class="fa fa-angle-up  fa-3x"></i></a>


</body>

</html>




