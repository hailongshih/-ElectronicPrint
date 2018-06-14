package com.us.example.config;

import com.jolbox.bonecp.BoneCPDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.beans.PropertyVetoException;

@Configuration
public class DBconfig {
    @Autowired
    private Environment env;

    @Bean(name="dataSource")
    public BoneCPDataSource dataSource() throws PropertyVetoException {
        BoneCPDataSource boneCPDataSource = new BoneCPDataSource();

        boneCPDataSource.setDriverClass(env.getProperty("jdbc.db.driverClassName"));

        boneCPDataSource.setJdbcUrl(env.getProperty("jdbc.db.url"));

        boneCPDataSource.setUsername(env.getProperty("jdbc.db.username"));

        boneCPDataSource.setPassword(env.getProperty("jdbc.db.password"));

        boneCPDataSource.setIdleConnectionTestPeriodInMinutes(60);
        boneCPDataSource.setTransactionRecoveryEnabled(true);
        boneCPDataSource.setIdleMaxAgeInMinutes(30);
        boneCPDataSource.setMaxConnectionsPerPartition(100);
        boneCPDataSource.setMinConnectionsPerPartition(5);
        return boneCPDataSource;
    }
}
