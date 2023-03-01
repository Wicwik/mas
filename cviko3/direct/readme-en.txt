ObjectWriterAgent and ObjectReaderAgent demonstrates
direct communication among agents: Writer sends a message to Reader.
The transmitted message is text based.
Study the code of the agents and understand it

modity the text of the message (any message just unique one)
and compile the updated classes

see runReader.bat - this time it is important to pay attention
to batch files, read them in details, and realize that they
containers and just that containers running the agents.
Launch the main container and the Reader agent inside it.
The argument -gui provides that container launches also the 
graphic user interface which display all containers and agents
(unfold the tree by clicking to its vertices to see the agent,
AMS, DF and rms - which is the GUI application)

From the GUI, using the right mouse buttom, launch the Writer agent 
in the JADE container and follow the Reader output confirming 
reception of the message. In this way you are running the Writer agent
in the same container where the Reader agent is running.

Study the runWriter.bat which is launching the Writer agent in
the adjacent container. Persuade yourself that it is operational,
i.e. switch off everything and then launch the main container
and the adjacent one with the Writer agent.

Work in couples: try to modify the runWriter.bat to send messege from
one PC to another PC
- for that you have to specify IP addres by -host option 
at the side of the Writer, i.e. at the main container 
(find the IP address via ipconfig utility)
- at the both sides, specify the actual container IP by -local-host 
(so the IP is different at each side)
- when you are launching the writer, reader must not be running yet,
otherwise the writer finds the port 1099 occupied and selecte other port
randomly - however that port is blocked by firewall (just the  port 1099
is allowed)

try another type of content for the message transmission alternative
e.g. StringLanguage (modify the writer, see comments in the writer code)
or XML (this content requires the codec mentioned in the batch files)

Reader and Writer logic is completely implemented in the setup() method.
Modify them to move the login into an Behavior and in the setup() just
start the Behaviour (implemented via anonymous class).
see doc/index.html Behavior
The best choice is the CyclicBehavior at the side od the reader and 
OneShotBehavior at the side of the writer.
(pay attention to doDelete which can be very confusing)

Ball Agent is an agent, which control a virtual scena with a cube which
user can move by mouse. Compile it.  Launch it so that at the first you 
start the main container, than the first agent and then the second one.
in the adjacent containters. You should observe that the movement of 
the cubes are synchronized. (When you like to restart the system, always 
you need to switch off and on also the main container to clean its DF)

Modify this solution to operation over network, i.e. movement of cube
from one computer is transferred to the cube on the other computer.
Concern that no change in code of agents is necessary to complete this
task, you have to modify just batch files.

Notice that also the adjacent container is listening on a particular port.
(which is necessarily different than the port of the main container).
If you would not apply the swither -local-port for the adjacent container,
by which we specify the port 7171 allowed by firewall, all other container
will see the adjacent container, they will send to it  messages, but it 
will receive nothing. So the synchronization between two cubes on the same
computer would be working fine, but would be one-way only when the cubes are
on two computers.

Advanced task:
Follow the attribute last and indetify the algorithmu which is behind the
cubes synchronization 

Homework: Improve the BallAgent code so it is possible to switch it off
and on withou restart of the main container (do not worry of exceptions
thrown on the main container while it is running)

