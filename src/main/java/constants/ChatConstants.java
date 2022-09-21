package constants;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ChatConstants {

    public final static String EXIT_STRING = "/exit";
    public final static DateTimeFormatter CHAT_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("d/MM/yy HH:mm:ss");
    public final static int FRAME_X_POS = 700;
    public final static int FRAME_Y_POS = 300;
    public final static int FRAME_WIGHT = 600;
    public final static int FRAME_HEIGHT = 500;
    public final static String PATH_INITIAL_FILE ="src/main/resources/initial.ini";
    public final static String PATH_FILE_IMAGE = "src/main/resources/Chat_Image.png";
    public final static String PATH_SERVER_LOGS ="infoLogs/server/";
    public final static String PATH_CLIENT_LOGS ="infoLogs/client/";
    public final static Image ICON;

    static {
        try {
            ICON = ImageIO.read(new File(PATH_FILE_IMAGE));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private static final BufferedReader initReader;

    static {
        try {
            initReader = new BufferedReader(new FileReader(PATH_INITIAL_FILE));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public final static String HOST;
    public final static int PORT;
    static {
        try {
            HOST = initReader.readLine();
            PORT = Integer.parseInt(initReader.readLine());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public synchronized static String messageToLogString(String message){
        return String.format("[%s] %s%s",
                LocalDateTime.now().format(CHAT_DATE_TIME_FORMATTER),
                message,
                System.lineSeparator());
    }
}
