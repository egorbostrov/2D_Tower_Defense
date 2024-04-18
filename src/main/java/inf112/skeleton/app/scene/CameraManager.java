package inf112.skeleton.app.scene;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;

public class CameraManager {
    private static final String TAG = CameraManager.class.getSimpleName();

    private final float MIN_ZOOM = 0.25f;
    private final float MAX_ZOOM = 10.0f;

    private Vector3 position; // Using Vector3 for compatibility with OrthographicCamera's position.
    private float zoom;
    private OrthographicCamera camera; // Direct reference to the camera being managed.

    public CameraManager(OrthographicCamera camera) {
        this.camera = camera; // Initialize with a specific camera.
        this.position = new Vector3(camera.position); // Start with the current camera position.
        this.zoom = camera.zoom; // Use the camera's current zoom level as a starting point.
    }

    public void updatePosition(float deltaTime) {
        // This method could include automatic camera adjustments or smoothing.
    }

    public void setPosition(float x, float y) {
        position.set(x, y, 0);
        applySettings(); // Apply settings immediately when changed.
    }

    public Vector3 getPosition() {
        return new Vector3(position.x, position.y, position.z); // Return a copy to prevent direct manipulation.
    }

    public void adjustZoom(float amount) {
        setZoom(zoom + amount);
    }

    public void setZoom(float zoom) {
        this.zoom = MathUtils.clamp(zoom, MIN_ZOOM, MAX_ZOOM);
        applySettings(); // Apply settings immediately when changed.
    }

    public float getZoom() {
        return zoom;
    }

    public void applyToCamera() {
        camera.position.set(position);
        camera.zoom = zoom;
        camera.update();
    }

    public void moveCamera(float dx, float dy) {
        position.add(dx, dy, 0);
        applySettings();
    }

    private void applySettings() {
        camera.position.set(position);
        camera.zoom = zoom;
        camera.update();
    }
}
