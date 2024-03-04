package inf112.skeleton.app.ui.components;

public interface Pressable {

        boolean contains(float x, float y);

        void touchDown(float x, float y);

        void touchRelease(float x, float y);

        boolean isPressed();

        void setPressed(boolean pressed);
}
