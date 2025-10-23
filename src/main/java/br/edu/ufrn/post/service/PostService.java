package br.edu.ufrn.post.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufrn.post.model.Post;
import br.edu.ufrn.post.record.CreatePostDTO;
import br.edu.ufrn.post.record.PostDTO;
import br.edu.ufrn.post.record.UserDTO;
import br.edu.ufrn.post.repository.PostRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public Flux<PostDTO> getAll() {
        return postRepository.findAll()
            .map(
                post -> new PostDTO(
                    post.getId(),
                    post.getContent(),
                    new UserDTO(
                        post.getUserId(),
                        null,
                        null
                    ),
                    post.getCreatedAt()
                )
            );
    }

    public Flux<PostDTO> getAllByUserId(String userId) {
        return postRepository.findAllByUserId(userId)
            .map(
                post -> new PostDTO(
                    post.getId(),
                    post.getContent(),
                    new UserDTO(
                        post.getUserId(),
                        null,
                        null
                    ),
                    post.getCreatedAt()
                )
            );
    }
    
    public Mono<PostDTO> getById(String id) {
        return postRepository.findById(id)
            .map(
                post -> new PostDTO(
                    post.getId(),
                    post.getContent(),
                    new UserDTO(
                        post.getUserId(),
                        null,
                        null
                    ),
                    post.getCreatedAt()
                )
            );
    }
    
    public Mono<PostDTO> save(CreatePostDTO createPostDTO) {
        Post postModel = new Post(
            createPostDTO.content(),
            createPostDTO.userId()
        );

        return postRepository.save(postModel)
            .map(
                post -> new PostDTO(
                    post.getId(),
                    post.getContent(),
                    new UserDTO(
                        post.getUserId(),
                        null,
                        null
                    ),
                    post.getCreatedAt()
                )
            );
    }
    
    public Mono<Void> delete(String id) {
        return postRepository.deleteById(id);
    }

}
