PROJECT SETUP
=============

1. Create a Spring project (e.g. named WebServices) as usual using one of the Spring project templates.

2. Copy the src folder of the supplied project into the project you've created. The easiest way to do this 
   is to drag the src folder onto the root project node (e.g. the node named WebServices) in the Package 
   Explorer view. 
   
3. Similarly, replace the new project's POM file with the one supplied.

4. Right-click on the project (i.e. the root node of the project in Eclipse's Package Explorer) and select 

   Properties->Web Project Settings
   
   and enter the value "Contact" for Context root value.
   
5. Within the Spring perspective of Eclipse, if you can see a Servers pane at the bottom left of the Eclipse 
   GUI that includes an entry named "VMware vFabric tc Server", drag the root project node onto the 
   "VMware vFabric tc Server" item. This will install the Web application in the Web container. 
   
   If you don't have a server setup, you will first need to create one. Section 4 of the following tutorial
   describes how to do this (the tutorial more generally covers creating a general Spring MVC project in STS,
   and will be useful later in the course):
   
   http://www.codejava.net/frameworks/spring/spring-mvc-beginner-tutorial-with-spring-tool-suite-ide  
   
6. Start the server by selecting it and pressing the Play (start the Server) button.

7. Run the Web Service client: com.aucklanduni.spring.webservices.restful.RestfulClientMain


PROJECT INFORMATION
===================

The src/main directory contains a directory named "webapp", structured as follows:

webapp
  |----- resources
  |----- WEB-INF
           |----- app
           |       |----- restful-context.xml
           |       |----- root-context.xml
           |
           |----- classes
           |
           |----- web.xml   
           
This is the standard structure for a Web application that is hosted by a Web container. It contains
all code resources (configuration scripts and compiled classes) required to run the application.

The web.xml file is used to initialise the Web container as follows. Points 1 and 2 are necessary, but can
be skimmed for the time being. They'll be discussed at a later stage in the course when covering Spring MVC
in more detail.

1. The contextConfigLocation parameter is set to the Web application's root-context.xml file. This file is
   basically empty for this simple application. For larger projects, it is used to define shared beans and 
   resources.
   
2. A listener is set up to create a top-level Spring container.

3. A servlet - the Spring-provided DispatcherServlet - is declared. This servlet is used as the entry-point 
   for a Spring Web application. The servlet is configured with the restful-context.xml file, which is 
   specific to the Contacts application. This file declares beans that are required for the Contacts 
   application. The servlet has a name (contactsService) that is referred to by the servlet-mapping tag 
   (in 4. below).
   
4. A servlet-mapping tag. This specifies what kinds of URLs the servlet will process when running in 
   the Web container. The "/*" is given. This, in conjunction with other configuration information, means 
   that the servlet will process requests of the form:
   
   http://<domain>/<context-root>/*
   
   The context-root value has been set as a project property (step 4 of PROJECT SETUP above). Assuming the
   server is running on localhost, and is bound to port 8080, the Web application's URL is thus:
   
   http://localhost:8080/Contact 
   
   and the application will process any requests that are prefixed by this URL followed by a "/", e.g.
   
   http://localhost:8080/Contact/contacts
   
   