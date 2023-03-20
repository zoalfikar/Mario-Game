package jade;

import javax.swing.Action;
import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;
import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;
public class MouseListener {
    private static MouseListener instance;
    private double scrollx ,scrolly;
    private double posx , posy ,lastX ,lastY;
    private boolean mouseButtonPresed[] = new boolean[3];
    private boolean isDragging;
    private MouseListener(){
        this.scrollx=0.0;
        this.scrolly=0.0;
        this.posx=0.0;
        this.posy=0.0;
        this.lastX=0.0;
        this.lastY=0.0;
    }
    public static MouseListener get() {
        if (MouseListener.instance == null) {
            MouseListener.instance = new MouseListener();
        }
        return MouseListener.instance;
        
    }
    public static void mousePosCallback(long window , double xpos , double ypos) {
        get().lastX = get().posx;
        get().lastY = get().posy;
        get().posx = xpos;
        get().posy = ypos;
        get().isDragging = get().mouseButtonPresed[0]||get().mouseButtonPresed[1] || get().mouseButtonPresed[2];
    }
    public static void mouseButtonCallback(long window , int  button , int  action , int mods) {
        if (action == GLFW_PRESS) {
            if (button < get().mouseButtonPresed.length) {
                
                get().mouseButtonPresed[button]=true;       
            }
        }
        else if (action == GLFW_RELEASE) {
            if (button < get().mouseButtonPresed.length) {

            get().mouseButtonPresed[button]=false;
            get().isDragging = false;
            }
        }
    }
    public static void mouseScrollCallback(long window , Double  xOffset , Double  yOffset) {
        get().scrollx = xOffset;
        get().scrolly = yOffset;
    }
    public static void endFrame() {
        get().scrollx=0;
        get().scrolly=0;
        get().lastX=get().posx;
        get().lastY=get().posx;
    }
    public static float getX() {
        return (float)get().posx;
    }
    public static float getY() {
        return (float)get().posy;
    }
    public static float getDX() {
        return (float)(get().lastX - get().posx);
    }
    public static float getDY() {
        return (float)(get().lastY - get().posy);
    }
    public static float getScrollX() {
        return (float)get().scrollx;
    }
    public static float getScrollY() {
        return (float)get().scrolly;
    }
    public static boolean isDragging() {
        return get().isDragging;
    }
    public static boolean mouseButtonDown(int button) {
        if (button < get().mouseButtonPresed.length) {
            return get().mouseButtonPresed[button];
        }else{
            return false;
        }
    }

}
