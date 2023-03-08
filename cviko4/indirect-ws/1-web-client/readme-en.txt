Find IP of a web page which employs pure http (not https), e.g.
ping dai.fmph.uniba.sk

prepare for copy & past the following request (including 2 empty lines at the end)
GET / HTTP/1.1
User-Agent: me
Host: 192.168.1.15
Accept: */*



Then launch from cmd
telnet-client ip port
where the port is 80
and when connection is created,
copy that above simple HTTP request
and paste it into the window by the right mouse button
the client should react by HTML code of the webpage

in top of the window click the right mouse button, Edit / Select All and click to the selected text
(if it is not working, set the Quick Edit Mode in Properties)
to copy the response, then paste it into editor and save as pom.html

Use the text editor to remove HTTP header from the pom.html
and open pom.html in browser, for instance as
file:///E:/users/cviko4/indirect-ws/1-web-client/pom.html

