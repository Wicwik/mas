At the first, let us remind network programming on the transport layer
(see subdirectory tcp)

(we use port just 7171, because it is the only port allowed by the local firewall.)
if some strange behaviour of network is seen, please, contact lecturer)

1. compile classes (use the provided batch files),
   launch Server (through batch files - see inside the file for arguments)
   and open the scena controlled by the Controller. You should see a moving ball.
   Check that without running Server, the ball does not move.
   
2. Study how the Server.class materialize the ball movement.
   Realize that the Server is running under other JVM than the Controller.
   Notice how the Server works: it is derived from the Thread class and
   calls start() in the constructor. The start() is a method inherited from
   the Thread class and creates a new thread which launches the overriden 
   method run().

3. in couples: launch net.bat to find IP address of one computer 
   and modify the solution in such a way, that the Server is running on
   that computer and the client (i.e. Controller) is running on another computer.

4. modify the codes so that the Server could serve to more clients in the
   same time, so more scenas can operate with a single server.
   (hint: it is not so difficult, the solution is almost ready in the code)
   
5. modify the server operation so that it can distinguish more clients
   and respond to them in such a way the it controlls two scenas 
   displayed on two adjacent displays and a ball in one scena will 
   seemingly jump from one display to another.
   (Employs any rough approach, e.g. we move one ball from some time and
    then we move other ball, so far out of visible part of the other scena)
   
Now let us code on the presentation layer 
We employ middleware Remote Method Invocation (RMI)
Task: (see subdirectory rmi-jrm):
(firewall allows also port 1099 used by the rmi registry -
what is a TSC server listening on port 1099 which materialize the naming service)

compile the interface Space
compile the RemoteSpace - the RMI object
launch the rmi.bat 
launch the Remote Space throgh the runRemoteSpace.bat 
compile the Agent
launch the Agent through the runAgent.bat
(rmiregistry must run only on that node where the RemoteSpace is running)

we have implemented two services of the indirect communication using the RMI:
read and write

- compile the Controler (Scene.java is the same as in the tcp case, but the Controller is different)
  and launch it
  
- modify the code of the Agent so that the cube in the Scena would move from the moment when 
  the Agent is started


advanced task:
- modify the solution for communication between the nodes (use rmiregistry.exe
  and as the address of the node use IP address found via the ipconfig utility, e.g. 158.195.....
  or logic name of the computer found via Control Panel in Windows, e.g. H3-01.dai.fmph.uniba.sk) 
  (rmiregistry must be launched from the same directory where there are skeleton classes)
  (because of virtual IP of the PCs in the terminal rooms on FMFI we have; to define java.rmi.server.hostname)
  (Since our firewall allows only 1099, 1098 and 7171, we have assign the port number for 
  the RemoteSpace manually to 7171)
  move up the cube on one node, by launching the Agent on the other node.
  (work in couples)

Homework:
- add an own method, e.g. blocking read into the interface Space. 
  When a client is calling an object which has not been written yes,
  it is blocked until another client writes the object.
  (you can use any rough method for the blocking, i. e. waiting in loop
  - we have not learn about synchronization mechanisms in Java.) In the
  loop you can call sleeping for a small instant if time
  try {
  	Thread.sleep(ms);
  } catch (InterruptedException ee) {
  }


