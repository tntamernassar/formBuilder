function answer(id){
	
	var val = $("input[name="+id+"]").val();
	
	 $.ajax({
         type:'POST',
         data:{action:"update",id:id,val:val},
         url:'SubmitForm',
         success:function(result){
             
         },error:function(request,status,error){
        	 alert("error @ addComp ="+error);
         }
     });
}

function submit(){
	 $.ajax({
         type:'POST',
         data:{action:"submit"},
         url:'SubmitForm',
         success:function(result){
             if(result == "es"){
            	 alert('please fill the form');
             }else{
            	 document.location = "Home";

             }
         },error:function(request,status,error){
        	 alert("error @ addComp ="+error);
         }
     });
}