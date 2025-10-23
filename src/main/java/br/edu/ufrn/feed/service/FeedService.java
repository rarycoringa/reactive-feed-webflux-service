package br.edu.ufrn.feed.service;

import br.edu.ufrn.feed.client.PostClient;
import br.edu.ufrn.feed.record.PostDTO;
import reactor.core.publisher.Flux;

public class FeedService {

    private final PostClient postClient;

    public FeedService(
        PostClient postClient
    ) {
        this.postClient = postClient;
    }

    public Flux<PostDTO> getLatestPosts(Integer limit) {
        return postClient.getAll()
            .take(limit);
    }

}
