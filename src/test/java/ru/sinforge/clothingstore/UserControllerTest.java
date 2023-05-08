package ru.sinforge.clothingstore;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import ru.sinforge.clothingstore.DTOs.CreateUserDto;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Sql(value={"/create_users-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value={"/create_users-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class UserControllerTest {
    @Autowired
    private MockMvc _mockMvc;
    @Test
    public void contentLoads() throws Exception {
        this._mockMvc.perform(get("/registration"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(notNullValue()));

    }
    @Test
    public void checkToSameEmails() throws  Exception {
        CreateUserDto createUserDto = new CreateUserDto();
        createUserDto.email = "vlad.vlasov77@mail.ru";
        createUserDto.country = "Russia";
        createUserDto.name = "Vlad";
        createUserDto.password = "12345";
        createUserDto.surname = "Vlasov";
        this._mockMvc.perform(post("/registration")

                        .content(String.valueOf(createUserDto)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Регистрация")));
    }
}
