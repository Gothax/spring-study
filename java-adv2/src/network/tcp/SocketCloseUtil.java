package network.tcp;

import util.MyLogger;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import static util.MyLogger.*;

public class SocketCloseUtil {

    public static void closeAll(Socket socket, InputStream input, OutputStream output) {
        close(input);
        close(output);
        close(socket);
    }

    private static void close(Socket socket) {
        if (socket != null) {
            try {
                socket.close();
            } catch (Exception e) {
                log(e.getMessage());
            }
        }
    }

    private static void close(OutputStream output) {
        if (output != null) {
            try {
                output.close();
            } catch (Exception e) {
                log(e.getMessage());
            }
        }
    }

    private static void close(InputStream input) {
        if (input != null) {
            try {
                input.close();
            } catch (Exception e) {
                log(e.getMessage());
            }
        }
    }

}
