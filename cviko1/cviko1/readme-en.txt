compile by the compile.bat (or use own IDE)
launch by run.bat (or use own IDE)
(adjust path to javac.exe or java.exe in the batch files before their use)
You see two balls flying one through other 

open Scene.java in a suitable editor (e.g. Notepad++) 
Try to understand the code.
- describe the tree structure of the 3D scena
- where the anonymous class is employed?
- how do we call i in getters and setters ? (hint: d........r)
- from where are the textures wood.jpg and stone.jpg loaded? (try to extract them)

when the formatting of .java files is damaged, fix it by format.bat (please, try)

There are comments on the Java language, if you are not so experienced with the language.

Notice that the main in Scene.java employs the for loop.

Modify the main so that the balls collide and bounce. It is enough to change ball direction
in the 45th step of the simulation.

Develop class Ball (see subdirectory serialization for the initial version) that 
exists and act in an autonomous way.
So that balls fly (it is not necessary to implement also collision) and the main changes 
approximatelly to:

public static void main( String[] args ) {
  	initialize();
  	new Ball(1,0.1f);
  	new Ball(2,-0.1f);
  	delay(10000);
	System.exit(0);
}

Employ codes from java/java.pdf. There are more choices:
- Thread + delay (e.g. gulka has own thread with a loop containing activities and delay)
- TimeredTask (e.g. the ball activities are launched from the thread of timer) (str. 26)

advanced task:
How can we manage collision and bouncing now?
(Forget any planned trajectories of blind movement of balls.
Manage that ball can test if it is colliding with another ball.)
Solve it just for two balls, so the ball id checks hit with the ball 3-id.

Homework: 
try serialization in the subdirectory serialization (see readme in that subdirectory)
Make the ball persistent by exchange of the byteArray I/O by the File I/O.
Modify the main so that the state of balls is saved after 10 seconds and it is
renewed by the next run when balls follows their movement from positions they had at the
end of the previous run.
