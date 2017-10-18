package com.company;

        import java.io.IOException;
        import java.io.PrintStream;
        import java.net.ServerSocket;
        import java.net.Socket;
        import java.util.Scanner;

public class SocketsOne extends Thread{
    private static final int PORT = 31111;

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT);
        Socket clientSocket = serverSocket.accept();
        read(clientSocket);
        write(clientSocket);

    }

    public static void read(Socket socket) {
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try{
                    Scanner in = new Scanner(socket.getInputStream());
                    String inputLine;
                    while ((inputLine = in.nextLine()) != null) {
                        System.out.println(inputLine);
                    }
                }catch (IOException e) {e.printStackTrace();}
            }
        });thread.start();


    }

    public static void write(Socket socket){
        try {
            PrintStream out = new PrintStream(socket.getOutputStream());
            Scanner stdIn = new Scanner(System.in);
            String userInput;

            while ((userInput = stdIn.nextLine()) != null) {
                out.println(userInput);
            }
        }catch (IOException e) {e.printStackTrace();}

    }

}






