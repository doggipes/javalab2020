package ru.javalab.hateoas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import ru.javalab.hateoas.models.User;
import ru.javalab.hateoas.services.UserService;

import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
@AutoConfigureRestDocs(outputDir = "target/snippets")
public class UserTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @BeforeEach
    public void setUp() {
        when(userService.changeEmail(1L, "123@1234.ru")).thenReturn(publishedUser());
    }

    @Test
    public void coursePublishTest() throws Exception {
        mockMvc.perform(put("/users/1/email").param("email", "123@1234.ru"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(publishedUser().getName()))
                .andExpect(jsonPath("$.email").value(publishedUser().getEmail()))
                .andExpect(jsonPath("$.password").value(publishedUser().getPassword()))
                .andDo(document("change_email", responseFields(
                        fieldWithPath("name").description("Имя пользователя"),
                        fieldWithPath("email").description("Почта пользователя"),
                        fieldWithPath("password").description("Пароль пользователя")
                )));
    }

    private User publishedUser() {
        return User.builder()
                .email("123@1234.ru")
                .password("123")
                .name("Доширак")
                .build();
    }
}
