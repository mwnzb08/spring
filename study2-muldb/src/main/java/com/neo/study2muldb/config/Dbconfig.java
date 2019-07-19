package com.neo.study2muldb.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;


@Configuration
public class Dbconfig {
    @Primary
    @Bean(name = "primarydb")
    @Qualifier("primarydb")
    @ConfigurationProperties(prefix = "spring.datasource.druid.primary")
    public DataSource primarydb(){
        return DataSourceBuilder.create().build();
    }
    @Bean(name = "secondary")
    @Qualifier("secondary")
    @ConfigurationProperties(prefix = "spring.datasource.druid.secondary")
    public DataSource secondary(){
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "primaryTemplate")
    public JdbcTemplate primaryTemplate(@Qualifier("primarydb") DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }
    @Bean(name = "secondaryTemplate")
    public JdbcTemplate secondaryTemplate(@Qualifier("secondary") DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }
}
