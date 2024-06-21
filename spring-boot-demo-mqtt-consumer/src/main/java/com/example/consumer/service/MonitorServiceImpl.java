package com.example.consumer.service;

import com.example.base.entity.MonitorEntity;
import com.example.consumer.mapper.MonitorMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * monitorService
 * </p>
 *
 * @author yingxian.cyx
 * @date Created in 2024-06-17 16:45
 */
@Service
@Slf4j
public class MonitorServiceImpl implements MonitorService {

    @Autowired
    private MonitorMapper monitorMapper;

    /**
     * 保存或修改监控
     *
     * @param monitor 监控对象
     * @return 操作结果
     */
    @CachePut(value = "monitorEntity", key = "#monitor.id")
    @Override
    public MonitorEntity addOrUpdate(MonitorEntity monitor) {
        // TODO parameter check

        if (monitor.getId() != null && monitor.getId() > 0) {
            MonitorEntity monitorFromDB = monitorMapper.selectById(monitor.getId());

            if (monitorFromDB != null) {
                monitorFromDB.setName(monitor.getName());
                monitorFromDB.setStatus(monitor.getStatus());
                monitorFromDB.setGmtModified(new Date());
                monitorMapper.updateById(monitorFromDB);
                return monitorFromDB;
            }
        }

        monitor.setGmtCreate(new Date());
        monitor.setGmtModified(new Date());
        monitorMapper.insert(monitor);
        return monitor;
    }

    /**
     * 获取监控
     *
     * @param id key值
     * @return 返回结果
     */
    @Cacheable(value = "monitorEntity", key = "#id")
    @Override
    public MonitorEntity queryById(Integer id) {
        // 我们假设从数据库读取
        log.info("查询监控【id】= {}", id);
        return monitorMapper.selectById(id);
    }

    @Override
    public List<MonitorEntity> getAll() {
        return monitorMapper.selectAll();
    }

    /**
     * 删除
     *
     * @param id key值
     */
    @CacheEvict(value = "monitorEntity", key = "#id")
    @Override
    public void delete(Integer id) {
        int returnId = monitorMapper.deleteById(id);
        log.info("删除监控【id】= {}", returnId);
    }
}
