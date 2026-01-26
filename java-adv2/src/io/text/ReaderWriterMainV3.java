package io.text;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class ReaderWriterMainV3 {

    public static void main(String[] args) throws IOException {

        String writeString = "Hello Reader Writer";
        System.out.println("writeString = " + writeString);


        // 파일에 쓰기
        FileWriter fw = new FileWriter(TextConst.FILE_NAME, StandardCharsets.UTF_8);
        fw.write(writeString);
        fw.close();


        // 파일에서 읽기
        FileReader fr = new FileReader(TextConst.FILE_NAME, StandardCharsets.UTF_8);

        int ch;
        StringBuilder content = new StringBuilder();
        while ((ch = fr.read()) != -1) {
            content.append((char) ch);
        }
        fr.close();

        System.out.println("readString = " + content);
    }
}
