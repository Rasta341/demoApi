@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    entityManagerFactoryRef = "userEntityManagerFactory",
    transactionManagerRef = "userTransactionManager",
    basePackages = {"com.example.demoapi.bankinforepositories"}
)
public class bankInfoDataBaseConfig {

    @Primary
    @Bean(name = "bankInfoDataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource bankInfoDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "bankInfoEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean bankInfoEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("bankInfoDataSource") DataSource dataSource
    ) {
        return builder
                .dataSource(dataSource)
                .packages("com.example.demoapi.bankinfoentities")
                .persistenceUnit("bankInfo")
                .build();
    }

    @Primary
    @Bean(name = "bankInfoTransactionManager")
    public PlatformTransactionManager bankInfoTransactionManager(
            @Qualifier("bankInfoEntityManagerFactory") EntityManagerFactory entityManagerFactory
    ) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}