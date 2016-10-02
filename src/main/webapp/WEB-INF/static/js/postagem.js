$(document).ready(function(){
	
	$( "#save-ajax" ).submit(function( event ) {
		  event.preventDefault();
		  
		  $.post('/blog/postagem/ajax/save', (this).serialize())
			  .done(function name(postagem) {
				 $('#info').empty().append(
						 "<p>Postagem salva com sucesso!</p> " +
						 "<p>Abrir Postagem: <a href='/blog/" + postagem.permaLink + "'>" + postagem.titulo + "</a></p>"
				 );
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
	var url = "/blog/postagem/ajax/titulo/" + value + "/page/1";
	
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

	if (titulo.lenght > 0) {
		url = "/blog/postagem/ajax/titulo/" + titulo + "/page/" + page;
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