package ru.netology.service;

import ru.netology.exception.NotFoundException;
import ru.netology.model.Post;
import ru.netology.repository.PostRepository;

import java.util.List;

public class PostService {

  private long count = 0;
  private final PostRepository repository;

  public PostService(PostRepository repository) {
    this.repository = repository;
  }

  public List<Post> all() {
    return repository.all();
  }

  public Post getById(long id) {
    return repository.getById(id);
  }

  public Post save(Post post) {return repository.save(post);}

  public Post saveTest(long id, String content) {
    Post pst = new Post();
    pst.setId(id);
    pst.setContent(content);
    return repository.save(pst);
  }

  public void saveTest20() {
    Post pst;
    for (int i = 0; i<=20; i++) {
      pst = new Post();
      pst.setId(0);
      pst.setContent("post #" + i);
      repository.save(pst);
    }
  }

  public void removeById(long id) {
    repository.removeById(id);
  }
}

