package chatLogger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ChatLogger {
    private final BufferedWriter bufferedWriterChatLog;
    private final BufferedWriter bufferedWriterServiceLog;

    public ChatLogger(String pathChatLogFile, String pathServiceLogFile) throws IOException {
        bufferedWriterChatLog = new BufferedWriter(new FileWriter(pathChatLogFile, true));
        bufferedWriterServiceLog = new BufferedWriter(new FileWriter(pathServiceLogFile, true));
    }

    public void addToChatLog(String event) {
        try {
            bufferedWriterChatLog.write(event);
            bufferedWriterChatLog.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void addToServiceLog(String event) {
        try {
            bufferedWriterServiceLog.write(event);
            bufferedWriterServiceLog.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
