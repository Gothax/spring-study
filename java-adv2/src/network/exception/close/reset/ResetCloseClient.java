package network.exception.close.reset;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;

import static util.MyLogger.log;

public class ResetCloseClient {


    public static void main(String[] args) throws IOException, InterruptedException {
        Socket socket = new Socket("localhost", 12345);
        log("소켓 연결: " + socket);

        InputStream input = socket.getInputStream();
        OutputStream output = socket.getOutputStream();

        // client <- server: FIN 옴
        Thread.sleep(1000);

        // client -> server: PUSH - 원래 FIN 해야하지만 데이터 보냄 (TCP 규약 위반)
        output.write(100);

        // client <- server: RST 옴
        Thread.sleep(1000);

        try {
            // java.net.SocketException: Connection reset
            int read = input.read();
            log("read = " + read);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
//            java.net.SocketException: Broken pipe
            output.write(1);
        } catch (SocketException e) {
            e.printStackTrace();
        }

    }


}
