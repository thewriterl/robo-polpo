import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class PolpoClass {
    public static void main(String[] args) throws IOException {

        //entra na home do site
        Document homeDoSite = Jsoup.connect("https://www.nike.com.br/Snkrs/").get();

        //seleciona as tres vitrines, feed, em estoque e calendario
        Elements vitrines = homeDoSite.select(".vitrine-content--feed");

        //seleciona lista de produtos vindos da vitrine
        Elements listaDeProdutos = vitrines.select(".produto");

        // seleciona os links dentro da lista de produtos
        Elements listaDeLinks = listaDeProdutos.select("a").select(".aspect-radio-box");

        // transforma elementos HTML em text (reproduz a lista de links e pega o texto do elemento)
        List<String> links = listaDeLinks.stream()
                .map(element -> element.attr("href"))
                .collect(Collectors.toList());

        //para cada link....
        links.forEach(link -> {
            try {
                //avisa o usuario
                System.out.println("Entrando na página: " + link);

                //entra na pagina do produto passando o link acima
                Document paginaDoProduto = Jsoup.connect(link).get();

                //seleciona o nome do produto
                String nomeDoProduto = paginaDoProduto.select(".nome-preco-produto").text();

                //seleciona o preco
                String preco = paginaDoProduto.select(".js-valor-por").text();

                //avisa o usuario
                System.out.println("nome do tenis: " + nomeDoProduto + " \npreco: " + preco);
            } catch (IOException e) {
                System.out.println("Deu erro na pagina -> " + link);
            }

        });

        System.out.println(" sdfsdf");

    }
}
