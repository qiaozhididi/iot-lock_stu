package iot.cloud.platform.lock.mapper;

import iot.cloud.platform.lock.entity.ConfigEntity;

public interface ConfigMapper {
    ConfigEntity getConfigByK(String k);
}
