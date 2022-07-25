import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {

    public static void main(String[] args) throws Exception {

        // fazer uma conexão HTTP e buscar os top 250 filmes
        String url = "https://mocki.io/v1/9a7c1ca9-29b4-4eb3-8306-1adb9d159060";
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build(); // método para buscar dados em qualquer uri de um servidor HTTP
        HttpResponse<String> response = client.send(request,  BodyHandlers.ofString());
        String body = response.body();

        // extrair só os dados que interessam (título, imagem, classificação)
        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);

        // exibir os dados
        var geradora = new GeradoraDeFigurinhas();
        for (Map<String,String> filme : listaDeFilmes) {
            try{
                String urlImagem = filme.get("image");
                String titulo = filme.get("title");
                String nomeArquivo = titulo + ".png";
                InputStream inputStream = new URL(urlImagem).openStream();
                geradora.cria(inputStream, nomeArquivo);

            }catch(java.io.FileNotFoundException err){
                System.out.println("Imagem não encontrada ou link inválido");
            }

        }
    }
}