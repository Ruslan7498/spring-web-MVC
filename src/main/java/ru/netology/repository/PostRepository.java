package ru.netology.repository;

import ru.netology.exception.NotFoundException;
import ru.netology.model.Post;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.*;

@Repository
public class PostRepository {
    private final Map<Long, Post> mapPosts = new HashMap<>();
    private long id = 1;

    public List<Post> all() {
        return new ArrayList<>(mapPosts.values());
    }

    public Post getById(long id) {
        if (mapPosts.containsKey(id)) {
            return mapPosts.get(id);
        } else {
            throw new NotFoundException("Error id");
        }
    }

    public synchronized Post save(Post post) {
        if (post.getId() == 0) {
            Post newPost = new Post(id, post.getContent());
            mapPosts.put(id, newPost);
            id++;
            return newPost;
        }
        if (mapPosts.containsKey(post.getId())) {
            Post replacePost = new Post(post.getId(), post.getContent());
            mapPosts.put(post.getId(), replacePost);
            return replacePost;
        } else {
            throw new NotFoundException("Error id");
        }
    }

    public synchronized void removeById(long id) {
        if (mapPosts.containsKey(id)) {
            mapPosts.remove(id);
        } else {
            throw new NotFoundException("Error id");
        }
    }
}