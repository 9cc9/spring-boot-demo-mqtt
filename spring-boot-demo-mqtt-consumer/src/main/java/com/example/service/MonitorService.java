package com.example.service;


import com.example.entity.MonitorEntity;

import java.util.List;

/**
 * <p>
 * MonitorService
 * </p>
 *
 * @author yingxian.cyx
 * @date Created in 2024-06-15 16:45
 */
public interface MonitorService {
    /**
     * 保存或修改用户
     *
     * @param monitorEntity 用户对象
     * @return 操作结果
     */
    MonitorEntity addOrUpdate(MonitorEntity monitorEntity);

    /**
     * 获取用户
     *
     * @param id key值
     * @return 返回结果
     */
    MonitorEntity queryById(Integer id);

    /**
     * 获取用户
     *
     * @return 返回结果
     */
    List<MonitorEntity> getAll();

    /**
     * 删除
     *
     * @param id key值
     */
    void delete(Integer id);
}
