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

public class Scene extends InputAdapterRenderLoop {

    private TransformGroup trans;

    public void setPos ( float x, float y, float z ) {
        trans.setTransform(new Transform3D(x,y,z));
    }

    public float getPosX () {
        return trans.getTransform().getTranslation().getX();
    }

    public float getPosY () {
        return trans.getTransform().getTranslation().getY();
    }

    public float getPosZ () {
        return trans.getTransform().getTranslation().getZ();
    }

    public Scene() throws Exception {
        super( 120f );
        Xith3DEnvironment env = new Xith3DEnvironment( this );
        Canvas3D canvas = Canvas3DFactory.createWindowed( 800, 600, "Animation - Rotation" );
        env.addCanvas( canvas );
        InputSystem.getInstance().registerNewKeyboardAndMouse( canvas.getPeer() );
        ResourceLocator resLoc = ResourceLocator.create( "test-resources/" );
        resLoc.createAndAddTSL( "textures" );
        Sphere sphere = new Sphere( 0.5f, 10, 10, "stone.jpg" );
        Cube cube = new Cube( 1.0f, "stone.jpg" );
        trans = new TransformGroup( new Transform3D(-5,0,0) );
        trans.addChild( cube );
        BranchGroup bg = new BranchGroup();
        bg.addChild(sphere);
        bg.addChild(trans);
        env.addPerspectiveBranch( bg );
    }

    public static void main( String[] args ) throws Exception {
        Scene scene = new Scene();
        new Controller(scene);
        scene.begin();
    }

}
