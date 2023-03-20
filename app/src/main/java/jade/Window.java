package jade;
import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;

import java.nio.*;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;
public class Window {
    private int width , height;
    private String title;
    private long glfwindow;
    private static Window window = null;
    private Window(){
        this.width = 1920 ;
        this.height = 1080 ;
        this.title = "Mario";
    } 
    public static Window get(){
        if (Window.window == null) {
            Window.window = new Window();
        }
        return Window.window;
    }
    public void run() 
    {
        System.out.println("Hello LWJGL " + Version.getVersion() + "!");
        init();
		loop();
		glfwFreeCallbacks(glfwindow);
		glfwDestroyWindow(glfwindow);
		glfwTerminate();
		glfwSetErrorCallback(null).free();
    }
    private void init() {
		// Setup an error callback. The default implementation
		// will print the error message in System.err.
		GLFWErrorCallback.createPrint(System.err).set();

		// Initialize GLFW. Most GLFW functions will not work before doing this.
		if ( !glfwInit() )
			throw new IllegalStateException("Unable to initialize GLFW");

		// Configure GLFW
		glfwDefaultWindowHints(); // optional, the current window hints are already the default
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); // the window will stay hidden after creation
		glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE); // the window will be resizable
		glfwWindowHint(GLFW_MAXIMIZED, GLFW_TRUE); // the window will be resizable

		// Create the window
		glfwindow = glfwCreateWindow(this.width, this.height , this.title , NULL, NULL);
		if ( glfwindow == NULL )
			throw new RuntimeException("Failed to create the GLFW window");
		glfwSetCursorPosCallback(glfwindow, MouseListener::mousePosCallback);
		glfwSetMouseButtonCallback(glfwindow, MouseListener::mouseButtonCallback);
		glfwSetScrollCallback(glfwindow, MouseListener::mouseScrollCallback);
		// Setup a key callback. It will be called every time a key is pressed, repeated or released.
		// glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
		// 	if ( key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE )
		// 		glfwSetWindowShouldClose(window, true); // We will detect this in the rendering loop
		// });

		// Get the thread stack and push a new frame
		// try ( MemoryStack stack = stackPush() ) {
		// 	IntBuffer pWidth = stack.mallocInt(1); // int*
		// 	IntBuffer pHeight = stack.mallocInt(1); // int*

		// 	// Get the window size passed to glfwCreateWindow
		// 	glfwGetWindowSize(window, pWidth, pHeight);

		// 	// Get the resolution of the primary monitor
		// 	GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

		// 	// Center the window
		// 	glfwSetWindowPos(
		// 		window,
		// 		(vidmode.width() - pWidth.get(0)) / 2,
		// 		(vidmode.height() - pHeight.get(0)) / 2
		// 	);
		// } // the stack frame is popped automatically

		// Make the OpenGL context current
		glfwMakeContextCurrent(glfwindow);
		// Enable v-sync
		glfwSwapInterval(1);

		// Make the window visible
		glfwShowWindow(glfwindow);
        // This line is critical for LWJGL's interoperation with GLFW's
		// OpenGL context, or any context that is managed externally.
		// LWJGL detects the context that is current in the current thread,
		// creates the GLCapabilities instance and makes the OpenGL
		// bindings available for use.
		GL.createCapabilities();
	}
    
	private void loop() {
		// // This line is critical for LWJGL's interoperation with GLFW's
		// // OpenGL context, or any context that is managed externally.
		// // LWJGL detects the context that is current in the current thread,
		// // creates the GLCapabilities instance and makes the OpenGL
		// // bindings available for use.
		// GL.createCapabilities();

		// Set the clear color
		// glClearColor(1.0f, 0.0f, 0.0f, 0.0f);

		// Run the rendering loop until the user has attempted to close
		// the window or has pressed the ESCAPE key.
		while ( !glfwWindowShouldClose(glfwindow) ) {
			// glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer

			// glfwSwapBuffers(window); // swap the color buffers

			// Poll for window events. The key callback above will only be
			// invoked during this call.
			glfwPollEvents();
            glClearColor(1.0f, 0.0f, 0.0f, 0.0f);
			glClear(GL_COLOR_BUFFER_BIT); // clear the framebuffer
			if (MouseListener.mouseButtonDown(0) || MouseListener.mouseButtonDown(1) ||MouseListener.mouseButtonDown(2) ) {
				System.out.println("ok");
			}

			glfwSwapBuffers(glfwindow); // swap the color buffers


		}
	}
}
