package hello.itemservice.message;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

import java.util.Locale;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class MessageSourceTest {

    @Autowired
    MessageSource ms;

    @Test
    public void helloMessage() {
        // given
        String result = ms.getMessage("hello", null, Locale.ENGLISH);
        System.out.println("result = " + result);
        String result2 = ms.getMessage("hello", null, Locale.KOREA);
        System.out.println("result2 = " + result2);
        // when
        // then
        assertThat(result).isEqualTo("hello");

    }

    @Test
    public void notFoundMessageCode() {
        // given
        assertThatThrownBy(() -> ms.getMessage("no_code", null, Locale.KOREA))
                .isInstanceOf(NoSuchMessageException.class);
        // when
        // then
    }

    @Test
    public void defaultLang() {
        // given

        // when

        // then
//        assertThat(ms.getMessage())

    }
}
