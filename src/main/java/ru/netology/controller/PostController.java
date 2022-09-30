package ru.netology.controller;

import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import ru.netology.model.Post;
import ru.netology.service.PostService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Reader;

@Controller
public class PostController {
  public static final String APPLICATION_JSON = "application/json";
  private final PostService service;

  public PostController(PostService service) {
    //сделал тестовые методы для добавления записей в репозиторий: saveTest и saveTest100
    service.saveTest(0, "bla");
    service.saveTest(0, "bla-bla");
    service.saveTest(0, "bla-bla-bla");
    service.saveTest20();
    service.saveTest(5, "bla-bla-5");
    service.saveTest(10, "bla-bla-10");
    service.saveTest(15, "bla-bla-bla-15");
    this.service = service;
  }

  public void all(HttpServletResponse response) throws IOException {
    response.setContentType(APPLICATION_JSON);
    final var data = service.all();
    final var gson = new Gson();
    response.getWriter().print(gson.toJson(data));
  }

  public void getById(long id, HttpServletResponse response) throws IOException {
    // TODO: deserialize request & serialize response
    response.setContentType(APPLICATION_JSON);
    final var data = service.getById(id);
    final var gson = new Gson();
    response.getWriter().print(gson.toJson(data));
  }

  public void save(Reader body, HttpServletResponse response) throws IOException {
    response.setContentType(APPLICATION_JSON);
    final var gson = new Gson();
    final var post = gson.fromJson(body, Post.class);
    final var data = service.save(post);
    response.getWriter().print(gson.toJson(data));
  }

  public void removeById(long id, HttpServletResponse response) {
    // TODO: deserialize request & serialize response
  }
}
