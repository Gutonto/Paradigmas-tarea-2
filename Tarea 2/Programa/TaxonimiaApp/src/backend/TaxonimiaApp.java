package backend;


import frontend.SplashScreenForm;
import javax.swing.SwingUtilities;

public class TaxonimiaApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
        SplashScreenForm splash = new SplashScreenForm (null, false);
        splash.setVisible(true);
        });
    }
}
