package charset;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class EncodingMain2 {

    private static final Charset EUC_KR = Charset.forName("EUC-KR");
    private static final Charset MS_949 = Charset.forName("MS949");

    public static void main(String[] args) {

        System.out.println("== 영문 ASCI 인코딩 ==");
        test("A", StandardCharsets.US_ASCII, StandardCharsets.US_ASCII);
        test("A", StandardCharsets.US_ASCII, StandardCharsets.ISO_8859_1);
        test("A", StandardCharsets.US_ASCII, EUC_KR);
        test("A", StandardCharsets.US_ASCII, MS_949);
        test("A", StandardCharsets.US_ASCII, StandardCharsets.UTF_8);
        test("A", StandardCharsets.US_ASCII, StandardCharsets.UTF_16);
        test("A", StandardCharsets.US_ASCII, StandardCharsets.UTF_16BE);


        System.out.println("\n== 한글 인코딩 - 기본 ==");
        test("가", StandardCharsets.US_ASCII, StandardCharsets.US_ASCII);
        test("가", StandardCharsets.ISO_8859_1, StandardCharsets.ISO_8859_1);
        test("가", StandardCharsets.US_ASCII, StandardCharsets.ISO_8859_1);
        test("가", EUC_KR, EUC_KR);
        test("가", MS_949, MS_949);
        test("가", StandardCharsets.UTF_8, StandardCharsets.UTF_8);
        test("가", StandardCharsets.UTF_16, StandardCharsets.UTF_16);
        test("가", StandardCharsets.UTF_16BE, StandardCharsets.UTF_16BE);


        System.out.println("\n== 한글 인코딩 - 복잡한 문자 ==");
        test("뷁", EUC_KR, EUC_KR);
        test("뷁", MS_949, MS_949);
        test("뷁", StandardCharsets.UTF_8, StandardCharsets.UTF_8);
        test("뷁", StandardCharsets.UTF_16, StandardCharsets.UTF_16);
        test("뷁", StandardCharsets.UTF_16BE, StandardCharsets.UTF_16BE);


        System.out.println("\n== 한글 인코딩 - 인코딩 디코딩이 다른 경우 ==");
        test("가", EUC_KR, MS_949);
        test("뷁", MS_949, EUC_KR); // 뷁 문자는 EUC-KR에 없음 (인코딩은 가능, 디코딩 불가)


        test("가", EUC_KR, StandardCharsets.UTF_8);
        test("가", StandardCharsets.UTF_8, EUC_KR);


        test("가", MS_949, StandardCharsets.UTF_8); // 가장 흔히 발생하는 문제 (윈도우 환경 -> UTF-8 환경)
        test("가", StandardCharsets.UTF_8, MS_949); // 가장 흔히 발생하는 문제 (UTF-8 환경 -> 윈도우 환경)


        System.out.println("\n== 영문 인코딩 - 인코딩 디코딩이 다른 경우 ==");
        test("A", EUC_KR, StandardCharsets.UTF_8);
        test("A", MS_949, StandardCharsets.UTF_8);
        test("A", StandardCharsets.UTF_8, MS_949);

        test("A", StandardCharsets.UTF_8, StandardCharsets.UTF_16BE);



    }


    private static void test(String text, Charset encodingCharSet, Charset decodingCharset) {
        byte[] encodedBytes = text.getBytes(encodingCharSet);
        String decodedString = new String(encodedBytes, decodingCharset);

        System.out.printf("원본 문자열: %s -> [%s] 인코딩: %s %sbyte -> [%s] 디코딩: %s\n",
                text,
                encodingCharSet,
                Arrays.toString(encodedBytes),
                encodedBytes.length,
                decodingCharset,
                decodedString
        );
    }

}
