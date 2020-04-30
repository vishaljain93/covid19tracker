package covid.tracker.covid19tracker.services;

import java.io.IOException;
import java.net.http.HttpResponse;

public interface HttpConnection {

    HttpResponse getResponse(final String url) throws IOException, InterruptedException;
}
