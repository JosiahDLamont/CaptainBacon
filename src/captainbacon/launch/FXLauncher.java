package captainbacon.launch;

import captainbacon.gui.fx.FXGUIImpl;
import captainbacon.gui.render.Renderer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class FXLauncher extends Application {

    /**
     * This function is automatically called when the application is launched. Do Not Call.
     * (likely unnecessary)
     * @pre [Do not call. Called by JavaFX (in main thread).]
     * @post [None.]
     */
    @Override
    public void init() {

    }

    /**
     * This function sets up the view (GUI) and controller of Captain Bacon. It is
     * automatically called when the application is launched. Do Not Call.
     *
     * @pre [Do not call. Called by JavaFX.]
     * @post [The view and controller have been created.]
     *
     * @param stage The primary window of the application.
     */
    @Override
    public void start(Stage stage) {

        Renderer renderer = new Renderer(60, new FXGUIImpl(stage));
        renderer.start();
        stage.show();

        //TODO: remove this test code
        stage.setOnCloseRequest(event -> renderer.endLoop());
    }

    /**
     * This function is automatically called when the GUI ends. Do Not Call.
     * (possibly unnecessary)
     * @pre [Do not call. Called by JavaFX.]
     * @post [None.]
     */
    @Override
    public void stop() {

    }
}
