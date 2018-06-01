function addComponents(){
	
	var lname = document.getElementById('label_name').value;
	
	if(lname.trim() == '')
		return;

	var radios = document.getElementsByName('radio');

	var types = ["Text", "Color", "Date","Email","Telephone Number","Number"];
	
	for (var i = 0, length = radios.length; i < length; i++)
	 if (radios[i].checked)
	  break;



	 $.ajax({
         type:'POST',
         data:{action:"add",lname:lname,choise:i},
         url:'WizardServlet',
         success:function(result){
             $('#current_form_display').html("");
             $('#current_form_display').html(result);
         },error:function(request,status,error){
        	 alert("error @ addComp ="+error);
         }
     });
	 
	 document.getElementById('label_name').value = "";
	 document.getElementById('label_name').focus();
}


function FormNameChange(value){
	$('#header_formDisplay').html(value);
}

function Delete(id){
	 $.ajax({
         type:'POST',
         data:{action:"remove",id:id},
         url:'WizardServlet',
         success:function(result){
             $('#current_form_display').html("");
             $('#current_form_display').html(result);
         },error:function(request,status,error){
        	 alert("error @Delete ="+error);
         }
     });
}

function publish(){
	
	var fname = document.getElementById('fname').value;
	
	if(fname.trim() == ''){
		alert("Please enter form name");
		return;
	}
	
	$.ajax({
        type:'POST',
        data:{action:"publish",fname:fname},
        url:'WizardServlet',
        success:function(result){
            if(result == "es"){
            	alert("Empty Form cannot be published");
            	return;
            }else{//legal form
            	document.location = "Home";
            }
        },error:function(request,status,error){
       	 alert("error @Delete ="+error);
        }
    });
}

