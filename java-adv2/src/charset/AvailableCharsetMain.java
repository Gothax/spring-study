package charset;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.SortedMap;

public class AvailableCharsetMain {

    public static void main(String[] args) {
        // 이용 가능한 Charset 목록 출력
        SortedMap<String, Charset> charsets = Charset.availableCharsets();
        for (String name : charsets.keySet()) {
            System.out.println(name);
        }


        // MS949
        Charset ms949 = Charset.forName("MS949");
        System.out.println("Charset for MS949: " + ms949);

        // 문자로 조회
        Charset utf8 = StandardCharsets.UTF_8;
        System.out.println("Charset for UTF-8: " + utf8);


        // 시스템 기본 charset
        Charset defaultCharset = Charset.defaultCharset();
        System.out.println("Default Charset: " + defaultCharset);

    }

}
