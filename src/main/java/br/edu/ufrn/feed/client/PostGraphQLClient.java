package br.edu.ufrn.feed.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.graphql.client.HttpGraphQlClient;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import br.edu.ufrn.feed.record.PostDTO;
import reactor.core.publisher.Flux;

@Component
public class PostGraphQLClient implements PostClient {

    private final HttpGraphQlClient client;

    public PostGraphQLClient(
        WebClient.Builder builder,
        @Value("${post.graphql.base-url}") String baseUrl
    ) {
        this.client = HttpGraphQlClient.builder(builder.baseUrl(baseUrl)).build();
    }

    @Override
    public Flux<PostDTO> getAll() {
        String query = """
            query GetAll {
                getAll {
                    content
                    user {
                        name
                    }
                    createdAt
                    comments {
                        content
                        user {
                            name
                        }
                    }
                }
            }
            """;

        return client.document(query)
            .retrieve("getAll")
            .toEntityList(PostDTO.class)
            .flatMapMany(Flux::fromIterable);
    }

}
