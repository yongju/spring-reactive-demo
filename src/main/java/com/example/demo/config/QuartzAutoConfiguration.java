package com.example.demo.config;

import com.example.demo.schedule.SimpleJob;
import com.example.demo.util.YamlPropertySourceFactory;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.quartz.QuartzDataSource;
import org.springframework.boot.autoconfigure.quartz.QuartzTransactionManager;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Configuration
@PropertySource(value = {"classpath:quartz.yml"}, factory = YamlPropertySourceFactory.class)
public class QuartzAutoConfiguration {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private Scheduler scheduler;

    @Bean
    @Qualifier("quartzDataSourceProperties")
    @ConfigurationProperties(prefix = "spring.quartz.properties.org.quartz.datasource")
    public DataSourceProperties quartzDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @QuartzDataSource
    public DataSource quartzDataSource() {
        return quartzDataSourceProperties().initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }

    @Bean
    @QuartzTransactionManager
    public PlatformTransactionManager quartzTransactionManager() {
        final DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(quartzDataSource());

        return transactionManager;
    }

    @PostConstruct
    public void start() {
        log.info("JobController start invoked");
        try {
            JobDetail jobDetail = buildJobDetail(
                    SimpleJob.class,
                    "gradeRatingJob", //name
                    "상점에 대한 리뷰 별점 적용", //desc
                    new HashMap()); //param

            //이미 스케쥴이 DB에 등록되어 있다면 삭제
            if (scheduler.checkExists(jobDetail.getKey())) {
                scheduler.deleteJob(jobDetail.getKey());
            }

            //Job과 트리거를 설정,
            scheduler.scheduleJob(
                    jobDetail,
                    buildSimpleJobTrigger(10));
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    // *  *   *   *   *   *     *
    //초  분  시  일  월  요일  년도(생략가능)
    public Trigger buildCronJobTrigger(String scheduleExp) {
        return TriggerBuilder.newTrigger()
                .withSchedule(CronScheduleBuilder.cronSchedule(scheduleExp))
                .build();
    }

    // 매개변수로 입력받은 시간단위로 실행
    public Trigger buildSimpleJobTrigger(Integer hour) {
        return TriggerBuilder.newTrigger()
                .withSchedule(SimpleScheduleBuilder
                        .simpleSchedule()
                        .repeatForever()
                        .withIntervalInSeconds(hour))
                .build();
    }

    public JobDetail buildJobDetail(Class job, String name, String desc, Map params) {
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.putAll(params);
        return JobBuilder
                .newJob(job)
                .withIdentity(name)
                .withDescription(desc)
                .usingJobData(jobDataMap)
                .build();
    }

}
