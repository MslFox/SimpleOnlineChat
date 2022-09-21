package client;

import chatLogger.ChatLogger;
import connections.*;

import java.io.IOException;

import static constants.ChatConstants.*;

public class Client implements TCPConnectionHandler {
    private final ClientJFrame clientJFrame;
    private final String nickname;
    private final ChatLogger clientLogger;

    private Client() throws IOException {
        this.clientLogger = new ChatLogger(
                PATH_CLIENT_LOGS + this.hashCode() + "ChatLog.txt",
                PATH_CLIENT_LOGS + this.hashCode() + "ServiceLog.txt");
        this.clientJFrame = new ClientJFrame(this);
        this.nickname = clientJFrame.getAuthorizationNickname();
        clientLogger.addToServiceLog(messageToLogString("Успешная авторизция <nickname = " + nickname + ">"));
        TCPConnection tcpConnection = null;
        try {
            tcpConnection = new TCPConnection(Client.this, HOST, PORT);
        } catch (IOException e) {
            clientJFrame.showServerResetMessage();
            System.exit(0);
        }
        tcpConnection.runConnection();
    }

    public static void main(String[] args) throws IOException {
        new Client();
    }

    public String getNickname() {
        return nickname;
    }

    public ChatLogger getClientLogger() {
        return clientLogger;
    }

    public void sendOutputMessage(TCPConnection tcpConnection, String message) {
        tcpConnection.sendMessage(message);
    }

    @Override
    public String onGetAuthorizationString(TCPConnection tcpConnection) {
        return nickname;
    }

    @Override
    public synchronized void onConnection(TCPConnection tcpConnection) {
        clientJFrame.showChatJFrame(this, tcpConnection);
        tcpConnection.sendMessage(tcpConnection.getAuthorizationString());
        clientJFrame.addToChatLog("Вы присоединились к чату!");
        clientLogger.addToServiceLog(messageToLogString("Клиент установил соединение"));
    }

    @Override
    public void onInputMessage(TCPConnection tcpConnection, String message) {
        if (message == null || message.equals(EXIT_STRING)) onDisconnect(tcpConnection);
        else clientJFrame.addToChatLog(message);
    }

    @Override
    public void onDisconnect(TCPConnection tcpConnection) {
        tcpConnection.disconnect();
        clientLogger.addToServiceLog(messageToLogString("Клиент разорвал соединение"));
    }

    @Override
    public synchronized void onException(TCPConnection tcpConnection, Exception e) {
        onDisconnect(tcpConnection);
        clientLogger.addToServiceLog(messageToLogString(e.getMessage()));
        clientJFrame.getInfoMessagePane(
                "В работе приложения произошла ошибка!\n" +
                        e.getMessage() +
                        "\nПриложение будет закрыто!");
        System.exit(0);
    }
}