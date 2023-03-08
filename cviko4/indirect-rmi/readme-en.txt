study the implementation of the proprietary middleware based on the native
Java marshalling (org/agentspace) which implements a core of indirect 
communication among agents

it is a good opportunity to learn how to use Java packaging (import, package)
on the package org.agentspace

The package contains the main class RemoteSpace which is more-thread server
operating over TCP, which impelements interface Space. The next important class
is the LocalSpace, which represents the RemoteSpace at the client side
(it is so called proxy). Thus the client is calling services of the RemoteSpace
as it would be running on the same JVM, but it is not. No matter, the client 
is accessing the interface Space regardless the called distributed object is located.

Other classes serves to for communication between the client and the server
or represent serializable data which are transferred as mobile structures
between the client and the server. The main class for serialization is the SpaceMsg, 
which serves a root of polymorphism and a mark for all serializable classes derived from it.

Every serializable structure contains the serialVersionID
but just a formal one - this number should be unique.
Please, improve it in one from the derived classes, e.g. SpaceMsgDelete -
comment out the number and let it to generate in a correct form by the serialversion.bat

Launch the Scene (Controler), where there is a plane sensor used for the cube mainipulation.
Check, that you can manipulate the displayed cube via mouse.
Switch off the scena.

Launch the Starter Space, launch the Scena again and after few time, launch the Agent
Notice that the cube is controlled by the agent.

Work in couples: modify the solution so that using Space on one computer you synchronize
two scenas  - when you move by mouse with one cube, the other cube moves too. 

How to solve the conflict when we move two scenas by mouse in the same time?

Advanced task: try to describe - point by point - what happens in the system
when one moves the cube. Do not avoid technical details (as marshalling).
