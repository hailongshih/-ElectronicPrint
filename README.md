1.Technique:Springboot+Spring Security+JPA+Mysql+Thymeleaf+BootStrap

This site is developed for online form submit/maintenance, ACL(access control list), online word generate/preview/print etc

Deployment steps:

1. access to http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html,  download&install jdk according to your machine
2. Setup java environment.
3. Sign up oracle account then access to https://dev.mysql.com/downloads/mysql/  then download mysql, install mysql and start mysql.
4. create embassyforms/Embassyforms!123 user and create db embassyforms then execute embassyforms.sql in mysql
5. execute "mvn clean install -X" then run "mvn spring-boot:run" command.
6. access to http://localhost:8080 and you'll see the index page.
