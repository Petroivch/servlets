package ru.netology.repository;

import ru.netology.model.Post;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

// Stub
public class PostRepository {

  private List<Post> posts = new ArrayList<>();
  private long count = 0;

  public List<Post> all() {
    return posts;
  }


  public Post getById(long id) {
    var res = new Post();
    for (Post pst:posts)
    {
      if (pst.getId() == id)
        res = pst;
    }
    return res;
  }

  public Post save(Post post) {
    if (post.getId() == 0)
    {
      count++;
      post.setId(count);
      posts.add(post);
    }
    else {
      updatePost(post);
    }
    return post;
  }

  public void removeById(long id) {
  }

  public void updatePost(Post post) {
    var res = new Post();
    for (Post pst:posts)
    {
      if (pst.getId() == post.getId())
        pst.setContent(post.getContent());
    }
  }
}
