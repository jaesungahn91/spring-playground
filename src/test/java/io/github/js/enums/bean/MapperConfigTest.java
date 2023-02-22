package io.github.js.enums.bean;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

class MapperConfigTest {

    @Test
    void 카테고리성_데이터_열거형으로_빈등록() {
        // given
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(MapperConfig.class);
        
        // when
        EnumMapper enumMapper = (EnumMapper) ac.getBean("enumMapper");

        // then
        assertThat(enumMapper.get("FeeType")).hasSize(2);
    }

}