package iot.cloud.platform.lock.mapper;

import iot.cloud.platform.lock.entity.ConfigEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ConfigMapper {
    ConfigEntity getConfigByK(String k);
}
