package io.buffered;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class CreateFileV3 {

    public static void main(String[] args) throws IOException {
        FileOutputStream fos = new FileOutputStream(BufferedConst.FILE_NAME);
        BufferedOutputStream bos = new BufferedOutputStream(fos, BufferedConst.BUFFER_SIZE);

        long startTime = System.currentTimeMillis();

        for (int i = 0; i < BufferedConst.FILE_SIZE; i++) {
            bos.write(1);
        }
        // flush는 close에서 자동 호출됨 (bos를 close 하면서 fos도 close) (v2에서 남은 버퍼를 쓰기 위해 호출했던 것과 동일)
        bos.close();

        long endTime = System.currentTimeMillis();
        System.out.println("File created: " + BufferedConst.FILE_NAME);
        System.out.println("File Size: " + BufferedConst.FILE_SIZE/1024/1024 + " MB");
        System.out.println("Time taken: " + (endTime - startTime) + " ms");
    }

}
