package io.text;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class ReaderWriterMainV4 {

    private static final int BUFFER_SIZE = 8192;

    public static void main(String[] args) throws IOException {

        String writeString = "Hello\nReader\nWriter";
        System.out.println("writeString = " + writeString);


        // 파일에 쓰기
        FileWriter fw = new FileWriter(TextConst.FILE_NAME, StandardCharsets.UTF_8);
        BufferedWriter bw = new BufferedWriter(fw, BUFFER_SIZE);

        bw.write(writeString);
        bw.close();


        // 파일에서 읽기
        FileReader fr = new FileReader(TextConst.FILE_NAME, StandardCharsets.UTF_8);
        BufferedReader br = new BufferedReader(fr, BUFFER_SIZE);

        StringBuilder content = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            content.append(line).append("\n");
        }
        br.close();


        System.out.println("readString = " + content);
    }
}
