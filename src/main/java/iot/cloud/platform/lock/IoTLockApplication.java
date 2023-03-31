package iot.cloud.platform.lock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("iot.cloud.platform.lock.mapper")
public class IoTLockApplication {
    public static void main(String[] args) {
        SpringApplication.run(IoTLockApplication.class, args);
    }
}
