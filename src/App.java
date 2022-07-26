import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class App {

    public static void main(String[] args) throws Exception {

        // String url = "https://mocki.io/v1/9a7c1ca9-29b4-4eb3-8306-1adb9d159060";
        String url = "https://api.mocki.io/v2/549a5d8b/Top250TVs";
        var http = new ClienteHttp();
        String json = http.buscaDados(url);

        // exibir os dados
        ExtratorDeConteudoDoIMDB extrator = new ExtratorDeConteudoDoIMDB();
        List<Conteudo> conteudos = extrator.extraiConteudos(json);

        var geradora = new GeradoraDeFigurinhas();

        for (Conteudo conteudo : conteudos) {
            try{
                InputStream inputStream = new URL(conteudo.getUrlimagem()).openStream();
                String nomeArquivo = "out/" + conteudo.getTitulo() + ".png";
                geradora.cria(inputStream, nomeArquivo);

            }catch(java.io.FileNotFoundException err){
                System.out.println("Imagem não encontrada ou link inválido");
            }
        }
    }
}