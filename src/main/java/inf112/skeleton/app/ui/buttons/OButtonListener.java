package inf112.skeleton.app.ui.buttons;

public interface OButtonListener {
    void touchEvent(TouchEvent event,float x,float y);

    enum TouchEvent{
        DOWN,RELEASE,DRAGGED
    }
}
