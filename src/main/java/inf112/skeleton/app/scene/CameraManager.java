package inf112.skeleton.app.scene;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;

public class CameraManager {
    private final Vector3 position; // Using Vector3 for compatibility with OrthographicCamera's position.
    private final float zoom;
    private final OrthographicCamera camera; // Direct reference to the camera being managed.

    public CameraManager(OrthographicCamera camera) {
        this.camera = camera; // Initialize with a specific camera.
        this.position = new Vector3(camera.position); // Start with the current camera position.
        this.zoom = camera.zoom; // Use the camera's current zoom level as a starting point.
    }


    public OrthographicCamera getCamera() {
        return camera;
    }
    public Vector3 getPosition() {
        return new Vector3(position.x, position.y, position.z); // Return a copy to prevent direct manipulation.
    }


    public void resize(int width, int height) {
        camera.viewportWidth = width;
        camera.viewportHeight = height;
        camera.update();
    }

}
