package network.tcp.v6;

import network.tcp.v5.SessionV5;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static util.MyLogger.log;

public class ServerV6 {

    private static final int PORT = 12345;


    public static void main(String[] args) throws IOException {
        log("서버 시작");
        SessionManagerV6 sessionManager = new SessionManagerV6();
        ServerSocket serverSocket = new ServerSocket(PORT);
        log("서버 소켓 시작 - 리스닝 포트: " + PORT);

        // shutdown hook 등록
        ShutdownHook shutdownHook = new ShutdownHook(serverSocket, sessionManager);
        Runtime.getRuntime().addShutdownHook(new Thread(shutdownHook, "ShutdownHook-Thread"));

        try {
            while (true) {
                Socket socket = serverSocket.accept(); // 메인스레드 블로킹 -> 여기서 IOException 발생 (shut down hook 스레드가 serverScoekt.close() 호출 시)
                log("소켓 연결: " + socket);

                SessionV6 session = new SessionV6(socket, sessionManager);
                Thread thread = new Thread(session);
                thread.start();
            }
        } catch (IOException e) {
            log("서버 소켓 종료: " + e);
        }


    }

    static class ShutdownHook implements Runnable {

        private final ServerSocket serverSocket;
        private final SessionManagerV6 sessionManager;

        ShutdownHook(ServerSocket serverSocket, SessionManagerV6 sessionManager) {
            this.serverSocket = serverSocket;
            this.sessionManager = sessionManager;
        }

        @Override
        public void run() {
            log("shutdown Hook 실행");
            try {
                sessionManager.closeAll();
                serverSocket.close();

                Thread.sleep(1000);
            } catch (Exception e) {
                System.out.println("e = " + e);
            }
        }
    }

}
