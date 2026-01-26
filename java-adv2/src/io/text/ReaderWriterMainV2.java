package io.text;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class ReaderWriterMainV2 {

    public static void main(String[] args) throws IOException {

        String writeString = "Hello Reader Writer";
        System.out.println("writeString = " + writeString);


        // 파일에 쓰기
        FileOutputStream fos = new FileOutputStream(TextConst.FILE_NAME);
        OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);

        osw.write(writeString);
        osw.close();


        // 파일에서 읽기
        FileInputStream fis = new FileInputStream(TextConst.FILE_NAME);
        InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);

        int ch;
        StringBuilder content = new StringBuilder();
        while ((ch = isr.read()) != -1) {
            content.append((char) ch);
        }
        isr.close();

        System.out.println("readString = " + content);
    }
}
