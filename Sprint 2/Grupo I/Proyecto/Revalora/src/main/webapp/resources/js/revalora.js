$( document ).ready(function() {
	if(localStorage.mnuActive != undefined){
		$( ".menuContent a.ui-menuitem-link[href='"+localStorage.mnuActive+"']" ).addClass("selectMenu");
	}
	
  $("a.ui-menuitem-link").click(function(){	
	localStorage.setItem("mnuActive", $(this).attr("href"));
  });
});