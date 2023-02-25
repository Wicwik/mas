import org.jagatoo.input.*;
import org.jagatoo.input.devices.components.*;
import org.jagatoo.input.events.*;
import org.jagatoo.opengl.enums.*;
import org.openmali.vecmath2.*;
import org.xith3d.*;
import org.xith3d.loop.*;
import org.xith3d.loop.opscheduler.*;
import org.xith3d.base.*;
import org.xith3d.render.*;
import org.xith3d.resources.*;
import org.xith3d.scenegraph.*;
import org.xith3d.scenegraph.primitives.*;
import org.xith3d.schedops.movement.*;

import java.io.*;

public class Scene extends InputAdapterRenderLoop {

    public static Scene scene = null;

    public static void setPos ( int i, float x, float y, float z ) {
        scene.setPosInScene(i,x,y,z);
    }

    public static float getPosX ( int i) {
        return scene.getPosXInScene(i);
    }

    public static float getPosY ( int i) {
        return scene.getPosYInScene(i);
    }

    public static float getPosZ ( int i) {
        return scene.getPosZInScene(i);
    }

    private TransformGroup trans1;
    private TransformGroup trans2;

    public void setPosInScene ( int i, float x, float y, float z ) {
        switch (i) {
        case 1:
            trans1.setTransform(new Transform3D(x,y,z));
            break;
        case 2:
            trans2.setTransform(new Transform3D(x,y,z));
            break;
        }
    }

    public float getPosXInScene (int i) {
        switch (i) {
        case 1:
            return trans1.getTransform().getTranslation().getX();
        case 2:
            return trans2.getTransform().getTranslation().getX();
        }
        return 0;
    }

    public float getPosYInScene (int i) {
        switch (i) {
        case 1:
            return trans1.getTransform().getTranslation().getY();
        case 2:
            return trans2.getTransform().getTranslation().getY();
        }
        return 0;
    }

    public float getPosZInScene (int i) {
        switch (i) {
        case 1:
            return trans1.getTransform().getTranslation().getZ();
        case 2:
            return trans2.getTransform().getTranslation().getZ();
        }
        return 0;
    }

    public Scene() throws Exception {
        super( 120f );
        Xith3DEnvironment env = new Xith3DEnvironment( this );
        Canvas3D canvas = Canvas3DFactory.createWindowed( 800, 600, "Animation - Collision" );
        env.addCanvas( canvas );
        InputSystem.getInstance().registerNewKeyboardAndMouse( canvas.getPeer() );
        ResourceLocator resLoc = ResourceLocator.create( "test-resources/" );
        resLoc.createAndAddTSL( "textures" );
        Sphere sphere1 = new Sphere( 0.5f, 10, 10, "stone.jpg" );
        Sphere sphere2 = new Sphere( 0.5f, 10, 10, "wood.jpg" );
        trans1 = new TransformGroup( new Transform3D(-5,0,0) );
        trans1.addChild( sphere1 );
        trans2 = new TransformGroup( new Transform3D(5,0,0) );
        trans2.addChild( sphere2 );
        BranchGroup bg = new BranchGroup();
        bg.addChild(trans1);
        bg.addChild(trans2);
        env.addPerspectiveBranch( bg );
    }

    public static void delay (int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ee) {
        }
    }

    public static void initialize() {
        Thread rendering = new Thread() {
            public void run () {
                try {
                    scene = new Scene();
                    scene.begin();
                } catch (Exception ee) {
                }
            }
        };
        rendering.start();
        for (;;) {
            delay(1000);
            if (scene != null) break;
        }
    }

    public static void main( String[] args ) {
        initialize();

        Gulka g1 = null, g2 = null;

        File f = new File("./gulky.save");
        if(f.exists() && !f.isDirectory()) {
            try {
                FileInputStream inputByteStream = new FileInputStream("./gulky.save");
                ObjectInputStream demarshaller = new ObjectInputStream(inputByteStream);

                g1 = (Gulka) demarshaller.readObject();
                g2 = (Gulka) demarshaller.readObject();

                inputByteStream.close();
            } catch (FileNotFoundException e) {
                System.out.println("File not found");
            } catch (IOException e) {
                System.out.println("Error initializing stream");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            g1.start();
            g2.start();

        }

        if (g1 == null || g2 == null) {
            g1 = new Gulka(1,0.1f);
		    g2 = new Gulka(2,-0.1f);
        }

		delay(2500);

        try {
            FileOutputStream outputByteStream = new FileOutputStream("./gulky.save");
            ObjectOutputStream marshaller = new ObjectOutputStream(outputByteStream);
            marshaller.writeObject(g1);
            marshaller.writeObject(g2);

            outputByteStream.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error initializing stream");
        }

        delay(7500);

        System.exit(0);
    }

}
