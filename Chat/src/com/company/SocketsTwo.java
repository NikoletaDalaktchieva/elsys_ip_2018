package com.company;

        import java.io.IOException;
        import java.net.Socket;

public class SocketsTwo extends SocketsOne{
    private static final String HOST_NAME = "localhost";
    private static final int PORT = 31111;

    public static void main(String[] args) throws IOException {
        Socket echoSocket = new Socket(HOST_NAME, PORT);
        read(echoSocket);
        write(echoSocket);
    }
}
