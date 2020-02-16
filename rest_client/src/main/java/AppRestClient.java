import java.io.IOException;

import org.springframework.http.ResponseEntity;

import rest_client.Request;
import rest_client.RestClient;

public class AppRestClient extends RestClient{

    public AppRestClient(boolean isSecured) throws Exception {
        super(isSecured);
    }

    public ResponseEntity getSomething(Request request) throws IOException {
        return this.get(request);
    }

}
