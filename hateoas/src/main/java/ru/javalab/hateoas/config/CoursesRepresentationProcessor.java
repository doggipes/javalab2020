package ru.javalab.hateoas.config;

import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import ru.javalab.hateoas.controllers.UserController;
import ru.javalab.hateoas.models.User;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CoursesRepresentationProcessor implements RepresentationModelProcessor<EntityModel<User>> {

    private final RepositoryEntityLinks links;

    public CoursesRepresentationProcessor(RepositoryEntityLinks links) {
        this.links = links;
    }

    @Override
    public EntityModel<User> process(EntityModel<User> model) {
        User user = model.getContent();
        if (user != null && user.getEmail().equals("123@1234.ru")) {
            model.add(linkTo(methodOn(UserController.class)
                    .change_email(user.getId(), user.getEmail())).withRel("email"));
        }
        return model;
    }
}
