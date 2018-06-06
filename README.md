Instructions to run
---

- download and install [XAMPP](https://www.apachefriends.org/download.html) (7.2.5 / PHP 7.2.5	)

- open XAMPP control panel and run : Apache,MySQL

- go to the folder where you installed XAMPP then navigate to tomcat>conf and edit tomcat-users.xml file in the following matter : 

    > add this attribute and save : 

   ```  <tomcat-users>```

   ```<user username="admin" password="admin" roles="manager-gui" />```
   
   > run tomcat in XAMPP control pannel

- in your browser go to [localhost/phpmyadmin](http://localhost/phpmyadmin/)

- create new database call it 'form_builder'

- import 'SQL/form_builder.sql'(that you can find on github repo) into form_builder database

- go to [localhost:8080](http://localhost:8080/)

- go to Manager App, it's located in the right side of the screen (username : admin , password : admin)

- scroll down to 'WAR file to deploy' section and upload 'WAR/FormBuilder.war' (that you also can find on
github repo)

- visit [http://localhost:8080/FormBuilder/Home](http://localhost:8080/FormBuilder/Home)

- enjoy :)
