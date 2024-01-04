package pl.gontarczyk.calculator.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.gontarczyk.calculator.mapper.EquationMapper;
import pl.gontarczyk.calculator.mapper.EquationMapperImpl;

@Configuration
public class MapStructConfiguration {

    @Bean
    public EquationMapper equationMapper() {
        return new EquationMapperImpl();
    }
}