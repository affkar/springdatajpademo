package com.example.springbatchdemo.config;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class DatasourceConfig {

    @Bean
    @ConfigurationProperties("spring.datasource.hikari")
    HikariConfig hikariConfig() {
        return new HikariConfig();
    }

    @Bean
    DataSource dataSource(final HikariConfig hikariConfig) {
        return new HikariDataSource(hikariConfig);
    }

    // @Bean
    // public Job cricketJob(JobRepository jobRepository, Step playerLoad, Step
    // gameLoad, Step playerSummarization) {
    // return new JobBuilder("cricketJob", jobRepository)
    // // .validator(jobParametersValidator())
    // .start(playerLoad)
    // .next(gameLoad)
    // .next(playerSummarization)
    // .build();
    // }

    // private JobParametersValidator jobParametersValidator() {
    // return new DefaultJobParametersValidator(new String[] { "team1Name",
    // "team2Name", "gameFormat" },
    // new String[0]);
    // }

    // @Bean
    // public Step playerSummarization() {
    // // TODO Auto-generated method stub
    // throw new UnsupportedOperationException("Unimplemented method
    // 'playerSummarization'");
    // }

    // @Bean
    // public Step gameLoad() {
    // // TODO Auto-generated method stub
    // throw new UnsupportedOperationException("Unimplemented method 'gameLoad'");
    // }

    // @Bean
    // public Step playerLoad(JobRepository jobRepository,
    // PlatformTransactionManager platformTransactionManager,
    // ItemReader<Player> jsonPlayerReader, ItemProcessor<Object, PlayerE>
    // playerTransformer,
    // ItemWriter<Object> dbPlayerWriter) {
    // return new StepBuilder("playerLoad", jobRepository)
    // .chunk(10, platformTransactionManager)
    // .reader(jsonPlayerReader)
    // .processor(playerTransformer)
    // .writer(dbPlayerWriter)
    // .build();
    // }

}
