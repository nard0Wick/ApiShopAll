package com.ShopAll.apiShopAll.repositoryTest;

import com.ShopAll.apiShopAll.entity.User;
import com.ShopAll.apiShopAll.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.extractProperty;
import static org.junit.jupiter.api.Assertions.assertEquals;

//@WebMvcTest(UserRepository.class)
@DataJpaTest
public class UserRepositoryTest {
    //@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")

    @Autowired
    private UserRepository userRepository;
    private final Operations op = new Operations();

    User user;
    @BeforeEach
    public void setUp(){
        user = new User("admin@doamin.com",
                "string",
                "string",
                true);
    }
    @Test
    public void findByEmailReturnsUser(){
        //given
        userRepository.save(user);

        //when
        User expected = userRepository.findByEmail(user.getEmail()).orElse(null);

        //then
        assertThat(expected).isEqualTo(user);
    }
    @Test
    public void findByEmailGetsNull(){
        //given
        //userRepository.save(user);

        //when
        User expected = userRepository.findByEmail(user.getEmail()).orElse(null);

        //then
        assertThat(expected).isEqualTo(null);
    }

    @Test
    public void existsByEmailReturnsTrue(){
        //given
        userRepository.save(user);

        //when
        Boolean result = userRepository.existsByEmail(user.getEmail());

        //then
        assertThat(result).isTrue();
    }

    /*@Test
    public void existsByEmailReturnsFalseBecauseIsntSaved(){
        //given
        //userRepository.save(user);

        //when
        Boolean result = userRepository.existsByEmail(user.getEmail());

        //then
        assertThat(result).isFalse();
    }*/

    @Test
    public void existsByEmailReturnsFalse(){
        //given
        String email = "hello@yahoo.com";

        //when
        Boolean result = userRepository.existsByEmail(email);

        //then
        assertThat(result).isFalse();
    }

    @Test
    public void deleteByEmailRetrievesTrue(){
        //given
        userRepository.save(user);
        String email = user.getEmail();

        //when
        userRepository.deleteByEmail(email);
        boolean result = userRepository.findByEmail(email).orElse(null) == null;

        //then
        assertThat(result).isTrue();
    }

    @Test
    public void deleteByEmailRetrievesFalse(){
        //given
        //userRepository.save(user);
        String email = "draxs@pamplona.com";
        if(userRepository.existsByEmail(email)){
            userRepository.deleteByEmail(email);
            boolean result = userRepository.findByEmail(email).orElse(null) == null;
            assertThat(result).isFalse();
        }
    }


    @Test
    @Disabled
    public void testingAssertionsMustWork(){
        //given
        int val1 = 10;
        int val2 = 5;

        //when
        int result = op.adding(val1, val2);

        //then
        assertThat(result).isEqualTo(result);
    }

    @Test
    @Disabled
    public void testingAssertionsMustNotWork(){
        //given
        int val1 = 136;
        int val2 = 4;

        //when
        int result = op.adding(val1, val2);

        //then
        assertThat(result).isEqualTo(-1);//correct result: 140
    }

}
class Operations{
    public int adding(int val1, int val2){
        return val1 + val2;
    }
}

