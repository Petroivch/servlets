package ru.netology.servlet;

import ru.netology.config.JavaConfig;
import ru.netology.controller.PostController;
import ru.netology.repository.PostRepository;
import ru.netology.service.PostService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainServlet extends HttpServlet {
  private PostController controller;

  public final String GET = "GET";
  public final String POST = "POST";
  public final String DELETE = "DELETE";

  @Override
  public void init() {
    /*final var context = new AnnotationConfigApplicationContext("ru.netology");
    final var repository = context.getBean("postRepository");
    final var service = context.getBean(PostService.class);
    controller = context.getBean(PostController.class);*/
    final var context = new AnnotationConfigApplicationContext(JavaConfig.class);
    final var repository = context.getBean("postRepository");
    final var service = context.getBean(PostService.class);
    controller = context.getBean(PostController.class);
  }

  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp) {
    // если деплоились в root context, то достаточно этого
    try {
      final var path = req.getRequestURI();
      final var method = req.getMethod();
      // primitive routing
      if (method.equals(GET) && path.equals("/api/posts")) {
        controller.all(resp);
        return;
      }
      if (method.equals(GET) && path.matches("/api/posts/\\d+")) {
        // easy way
        final var id = Long.parseLong(path.substring(path.lastIndexOf("/")+1));
        controller.getById(id, resp);
        return;
      }
      if (method.equals(POST) && path.equals("/api/posts")) {
        controller.save(req.getReader(), resp);
        return;
      }
      if (method.equals(DELETE) && path.matches("/api/posts/\\d+")) {
        // easy way
        final var id = Long.parseLong(path.substring(path.lastIndexOf("/")));
        controller.removeById(id, resp);
        return;
      }
      resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
    } catch (Exception e) {
      e.printStackTrace();
      resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
  }
}

