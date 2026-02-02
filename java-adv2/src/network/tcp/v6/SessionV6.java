package network.tcp.v6;

import network.tcp.SocketCloseUtil;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import static util.MyLogger.log;

public class SessionV6 implements Runnable{

    private final Socket socket;
    private final DataInputStream input;
    private final DataOutputStream output;
    private final SessionManagerV6 sessionManager;
    private boolean closed = false;


    public SessionV6(Socket socket, SessionManagerV6 sessionManager) throws IOException {
        this.socket = socket;
        this.sessionManager = sessionManager;

        this.input = new DataInputStream(socket.getInputStream());
        this.output = new DataOutputStream(socket.getOutputStream());
        this.sessionManager.add(this);
    }

    @Override
    public void run() {

        try {

            while (true) {

                // 클라이언트에게 문자 받기
                String received = input.readUTF();
                log("client -> server: " + received);

                if (received.equals("exit")) {
                    break;
                }

                // 클라이언트에게 문자 보내기
                String toSend = received + " World!";
                output.writeUTF(toSend);
                log("client <- server: " + toSend);
            }

        } catch (IOException e) {
            log(e);
        } finally {
            close();
            sessionManager.remove(this);
        }

    }


    // 세션 종료시, 서버 종료시 2번 호출될 수 있다 (synchronized + closed 플래그로 방지)
    public synchronized void close() {
        if (closed) {
            return;
        }
        SocketCloseUtil.closeAll(socket, input, output);
        closed = true;
        log("연결 종료: " + socket + " isClosed: " + socket.isClosed());
    }

}
