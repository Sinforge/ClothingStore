package ru.sinforge.clothingstore;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.metadata.HsqlTableMetaDataProvider;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import ru.sinforge.clothingstore.Controllers.ClothController;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Sql(value={"/create_users-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value={"/create_users-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class ClothControllerTest {
    @Autowired
    private MockMvc _mockMvc;

    @Test
    public void contentLoads() throws Exception {
        this._mockMvc.perform(get("/cloth/all"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attribute("allSizes", notNullValue()))
                .andExpect(model().attribute("allBrands", notNullValue()))
                .andExpect(model().attribute("allSections", notNullValue()))
                .andExpect(model().attribute("allColors", notNullValue()))
                .andExpect(model().attribute("path", notNullValue()))
                .andExpect(model().attribute("allCloth", notNullValue()))
                .andExpect(content().string(notNullValue()));
        this._mockMvc.perform(get("/cloth/602"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(notNullValue()));

    }
    @Test
    public void checkToUnauthorized() throws Exception {
        this._mockMvc.perform(get("/addToBasket/1"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
        this._mockMvc.perform(get("/basket"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
        this._mockMvc.perform(post("/basket"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
        this._mockMvc.perform(get("/leave"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
        this._mockMvc.perform(get("/deleteFromBasket/1"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
    }
    @Test
    @WithUserDetails("lakij67786@doerma.com")
    public void checkToAuthorize() throws Exception {
        this._mockMvc.perform(get("/addToBasket/1"))
                .andDo(print())
                .andExpect(authenticated());
        this._mockMvc.perform(get("/basket"))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(content().string(notNullValue()));
        this._mockMvc.perform(post("/basket"))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(content().string(notNullValue()));
        this._mockMvc.perform(get("/leave"))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(content().string(notNullValue()));

        this._mockMvc.perform(get("/deleteFromBasket/1"))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(content().string(notNullValue()));

    }

}
