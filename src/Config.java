import java.awt.*;

public class Config {
    public static final int SIZE = 20;
    public static final int WIDTH = 40;
    public static final int HEIGHT = 30;
    public static final int SLEEPMS = 500;

    public static Color getColor (Status status){
        return switch (status) {
            default -> Color.BLACK;
            case BORN -> Color.GRAY;
            case LIVE -> Color.DARK_GRAY;
            case DIED -> Color.BLUE;
        };
    }
}
