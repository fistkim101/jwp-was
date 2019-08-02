package webserver.http;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class RequestURLTest {

    @DisplayName("RequestURL을 생성한다.")
    @ParameterizedTest
    @ValueSource(strings = {
            "/",
            "/users",
            "/users/10",
            "/users?hello=world",
            "/users/10?hello=world&a=a",
            "/users?hello=world&a=a&b=b",
    })
    void create(final String rawRequestURL) {
        // when
        final RequestURL requestURL = RequestURL.of(rawRequestURL);

        // then
        assertThat(requestURL).isNotNull();
    }

    @DisplayName("RequestURL이 잘못됐을 경우 예외처리 한다.")
    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {
            " ",
            "users/10",
            " users ",
            "/users/10?h",
    })
    void invalid(final String rawRequestURL) {
        // when / then
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> RequestURL.of(rawRequestURL));
    }
}
