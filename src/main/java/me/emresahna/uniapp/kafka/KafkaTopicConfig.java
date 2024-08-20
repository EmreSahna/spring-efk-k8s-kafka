package me.emresahna.uniapp.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {
    @Bean
    public NewTopic newStudentGetTopic() {
        return TopicBuilder.name("student-get-topic")
                .partitions(3)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic newStudentAssignCourseTopic() {
        return TopicBuilder.name("student-assign-course-topic")
                .partitions(3)
                .replicas(1)
                .build();
    }
}
