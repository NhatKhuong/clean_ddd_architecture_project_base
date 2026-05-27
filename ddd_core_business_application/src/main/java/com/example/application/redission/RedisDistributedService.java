package com.example.application.redission;

public interface RedisDistributedService {
    RedisDistributedLocker getDistributedLock(String lockKey);
}
