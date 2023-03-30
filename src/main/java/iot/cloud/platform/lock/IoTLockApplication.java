package iot.cloud.platform.lock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class IoTLockApplication {
    public static void main(String[] args) {
        SpringApplication.run(IoTLockApplication.class, args);
    }
}
