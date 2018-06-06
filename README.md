#download XAMPP : https://www.apachefriends.org/download.html
#run : Apache,MySQL,Tomcat
#go to folder where you installed XAMPP then tomcat>conf and edit tomcat-users.xml file in the following matter : 
#add this attribute and save : 
   <tomcat-users>
		<user username="admin" password="admin" roles="manager-gui" />
		
#go to http://localhost/phpmyadmin/
#create new database call it 'form_builder'
#import 'SQL/form_builder.sql'(that you can find on github repo)i nto form_builder database
#go to http://localhost:8080/
#go to Manager App (username : admin , password : admin)
#go to 'WAR file to deploy' section and upload 'WAR/ForumBuilder.war'(that you also can find on github repo)
#go to http://localhost:8080/FormBuilder/Home 
#enjoy :)
