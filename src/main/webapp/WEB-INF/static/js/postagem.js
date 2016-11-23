$(document).ready(function(){
	
	$( "#save-ajax" ).submit(function( event ) {
		  event.preventDefault();
		  
		  var token = $("input[name='_csrf']").attr('value');
		  
		  $.post('/blog/postagem/ajax/save?_csrf' + token, $(this).serialize())
			  .done(function name(result) {
				  
				  if (result.status != 'FAIL') {
					 $('#info').empty().append(
							 "<p>Postagem salva com sucesso!</p> " +
							 "<p>Abrir Postagem: <a href='/blog/" + result.postagem.permaLink + "'>" + result.postagem.titulo + "</a></p>"
					 );
					 
					 $('#save-ajax').each(function() {
						this.reset();
					 });
					 $('#titulo-error').empty();
					 $('#texto-error').empty()
				  } else {
					  $('#titulo-error').empty().append(result.tituloError);
					  $('#texto-error').empty().append(result.textoError);
				  }
				 
			   })
			   .fail(function(error) {
				  $('#info').empty().append("<p>Error: status" + error.status + "," + errorstatusText+ "</p>");
			});
		  
		});
	
	
	
	
	$(document).on('click', 'button[id*="button_"]', function(){
		var pageNumber = $(this).val();
		tbody(pageNumber);
	});
	
	$('#search').keyup(function (){
		var value = $(this).val();	
		
		var exp = new RegExp('[a-zA-Z0-9]');
		
		if (exp.test(value)) {
			search(value);			
		} else {
			tbody(1);
		}
		
	});
	
});

function search(value) {
	var url;
	var autorId = $('#table-ajax').attr('title');
	
	if (autorId > 0) {
		url = "/blog/postagem/ajax/autor/" + autorId +  "/titulo/" + value + "/page/1";
	} else {
		url = "/blog/postagem/ajax/titulo/" + value + "/page/1";
	}
		
	$('#table-ajax').load(url, function(response, status, xhr) {
		if ( status == "error" ) {
			var msg = "Sorry but there was an error: ";
			$( "#info" ).html( msg + xhr.status + " " + xhr.statusText );
		}
	});		
}


function tbody(page) {
	var url = "";
	
	var titulo = $('#search').val();
	
	var autorId = $('#table-ajax').attr('title');
	
	if(autorId > 0 && titulo.length > 0){
		url = "/blog/postagem/ajax/autor/" + autorId +  "/titulo/" + titulo + "/page/" + page;
	} else if (titulo.lenght > 0) {
		url = "/blog/postagem/ajax/titulo/" + titulo + "/page/" + page;
	} else if (autorId > 0) {
		url = "/blog/postagem/ajax/autor/" + autorId + "/page/" + page;
	} else {
		url = "/blog/postagem/ajax/page/" + page;
	}
	
	$( "#table-ajax" ).load(url, function( response, status, xhr ) {
	  if ( status == "error" ) {
	    var msg = "Sorry but there was an error: ";
	    $( "#info" ).html( msg + xhr.status + " " + xhr.statusText );
	  }
	  
	  /*if (status = "success") {
		$('button').each(function(){
			var id ='#' + $(this).attr('id');
			
			if ($(id).attr('disabled') == 'disabled') {
				$(id).removeAttr('disabled');
			}
		});
		
		$('#button_' + page).attr('disabled', 'disabled');		
	}*/
	  
	});
}