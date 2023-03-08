On the server www.agentspace.org we have running tomcat on the port 8080 
Inside the tomcat, there are servlets read and write, which read from and write to Space

you can write by calling web service
http://www.agentspace.org:8080/write?a=1&b=2.3&c=ahoj

and read by calling web service
http://www.agentspace.org:8080/read?a&b&c

try to call them from browser and see html code of the returned page
(via developer plugins installed into browser)

see the supplied code of the Java classes and understand how it works.
Notice that the number of the cube coordinates is shared by all users,
thus you have to select an own unique number - or do not wonder that your
cube is moving by activities of the other users.

compile the code and launch it.

Check that you can controll the cube position by the calling of the write web service from the browser.

It is less clear that the sphere position can be changed by mouse
since the system synchronize its position with value stored in the Space on the web server

Modify the system (work in couples) so that two spheres are synchronized 
so when you move one of them by mouse, the other one is moving too and oppositely.

hint: We have learnt the basic algorithm for the conflict solution int the exercise from the previous lesson.
