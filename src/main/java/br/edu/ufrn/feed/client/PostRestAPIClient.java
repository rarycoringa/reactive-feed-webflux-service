package br.edu.ufrn.feed.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import br.edu.ufrn.feed.record.PostDTO;
import reactor.core.publisher.Flux;

@Component
public class PostRestAPIClient implements PostClient {

    private final WebClient client;
    
    public PostRestAPIClient(
        WebClient.Builder builder,
        @Value("${post.restapi.base-url}") String baseUrl
    ) {
        this.client = builder.baseUrl(baseUrl).build();
    }

    @Override
    public Flux<PostDTO> getAll() {
        return client.get()
            .uri("/posts")
            .retrieve()
            .bodyToFlux(PostDTO.class);
    }

}
