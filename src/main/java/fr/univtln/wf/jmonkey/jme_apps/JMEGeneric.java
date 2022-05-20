package fr.univtln.wf.jmonkey.jme_apps;

import com.jme3.app.SimpleApplication;
import com.jme3.system.AppSettings;

/**
 * Common behaviours of all jmonkey app (like settings)
 * @author Wide Factory Team
 */
public abstract class JMEGeneric extends SimpleApplication
{
    /**
     * Constructor with a custom width height and title of window
     * @param width width of window
     * @param height height of window
     * @param title title of window
     */
    protected JMEGeneric(int width, int height, String title)
    {
        AppSettings customSettings = new AppSettings(true);
        customSettings.put("Width", width);
        customSettings.put("Height", height);
        customSettings.put("Title", title);
        customSettings.put("VSync", true);
        customSettings.put("Samples", 4);
        customSettings.setFrameRate(15);

        this.setShowSettings(false);
        this.setSettings(customSettings);
        this.setDisplayFps(false);

        this.setDisplayStatView(false);
    }

    /** Constructor with default jmonkey app */
    protected JMEGeneric()
    {
        this(1280, 720, "Wide Factory");
    }

}
