import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ClienteHttp {

    public String buscaDados(String url){
        try {
            URI endereco = URI.create(url);
            var client = HttpClient.newHttpClient();
            var request = HttpRequest.newBuilder(endereco).GET().build(); // método para buscar dados em qualquer uri de um servidor HTTP
            HttpResponse<String> response = client.send(request,  HttpResponse.BodyHandlers.ofString());
            String body = response.body();
            return body;

        } catch (java.io.IOException | InterruptedException ex){
            throw  new RuntimeException(ex);
        }



    }





}
