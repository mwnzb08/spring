package support;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.http.HttpResponse;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;

import java.util.Arrays;
public class CustomConnectionKeepAliveStrategy implements ConnectionKeepAliveStrategy {

    private final long DEFAULT_SECOND=30;

    @Override
    public long getKeepAliveDuration(HttpResponse httpResponse, HttpContext httpContext) {
        return Arrays.asList(httpResponse.getHeaders(HTTP.CONN_KEEP_ALIVE))
                .stream()
                .filter(h-> StringUtils.equalsAnyIgnoreCase(h.getName(),"timeout")
                        && StringUtils.isNumeric(h.getValue()))
                .findFirst()
                .map(h-> NumberUtils.toLong(h.getValue(),DEFAULT_SECOND))
                .orElse(DEFAULT_SECOND)*1000;
    }
}
