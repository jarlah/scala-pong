package gdx.scala.demo.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import gdx.scala.demo.GdxScalaDemoGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Pong";
		config.width = 800;
		config.height = 480;
		new LwjglApplication(new GdxScalaDemoGame(), config);
	}
}
