Instruction to run
---

- download [XAMPP](https://www.apachefriends.org/download.html) (7.2.5 / PHP 7.2.5	)

- run : Apache,MySQL,Tomcat

- go to folder where you installed XAMPP then tomcat>conf and edit tomcat-users.xml file in the following matter : 

    add this attribute and save : 

   ```  <tomcat-users>```

   ```<user username="admin" password="admin" roles="manager-gui" />```

- go to [localhost/phpmyadmin](http://localhost/phpmyadmin/)

- create new database call it 'form_builder'

- import 'SQL/form_builder.sql'(that you can find on github repo) into form_builder database

- go to [localhost:8080](http://localhost:8080/)

- go to Manager App (username : admin , password : admin)

- go to 'WAR file to deploy' section and upload 'WAR/ForumBuilder.war' (that you also can find on
github repo)

- go to [http://localhost:8080/FormBuilder/Home](http://localhost:8080/FormBuilder/Home)

- enjoy :)
