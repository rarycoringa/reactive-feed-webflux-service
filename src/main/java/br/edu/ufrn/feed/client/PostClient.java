package br.edu.ufrn.feed.client;

import br.edu.ufrn.feed.record.PostDTO;
import reactor.core.publisher.Flux;

public interface PostClient {
    public Flux<PostDTO> getAll();
}
