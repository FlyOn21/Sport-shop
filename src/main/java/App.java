import controllers.EshopMainController;

public class App {
    private final EshopMainController startApp = new EshopMainController();

    public void run() {
        startApp.eshopProcessing();
    }

    public static void main(String[] args) {
        new App().run();
    }
}
