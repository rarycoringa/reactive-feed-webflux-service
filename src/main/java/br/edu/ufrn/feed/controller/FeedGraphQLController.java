package br.edu.ufrn.feed.controller;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import br.edu.ufrn.feed.client.PostGraphQLClient;
import br.edu.ufrn.feed.record.PostDTO;
import br.edu.ufrn.feed.service.FeedService;
import reactor.core.publisher.Flux;

@Controller
public class FeedGraphQLController {

    private final FeedService feedService;

    public FeedGraphQLController(PostGraphQLClient client) {
        this.feedService = new FeedService(client);
    }

    @QueryMapping
    public Flux<PostDTO> getLatestPosts(@Argument Integer limit){
        return feedService.getLatestPosts(limit);
    }

}
