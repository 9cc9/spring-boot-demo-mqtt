package com.example.mapper;

import com.example.entity.MonitorEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * Monitor Mapper
 * </p>
 *
 * @author yingxian.cyx
 * @date Created in 2024-06-08 10:54
 */
@Mapper
@Component
public interface MonitorMapper {

    /**
     * 查询所有监控
     *
     * @return 监控列表
     */
    @Select("SELECT * FROM demo_monitor")
    List<MonitorEntity> selectAll();

    /**
     * 根据id查询监控
     *
     * @param id 主键id
     * @return 当前id的监控，不存在则是 {@code null}
     */
    @Select("SELECT * FROM demo_monitor WHERE id = #{id}")
    MonitorEntity selectById(@Param("id") Integer id);

    /**
     * 保存监控
     *
     * @param monitor 监控
     * @return 成功 - {@code 1} 失败 - {@code 0}
     */
    int insert( MonitorEntity monitor);

    /**
     * @param entity
     */
    void updateById(MonitorEntity entity);

    /**
     * 删除监控
     *
     * @param id 主键id
     * @return 成功 - {@code 1} 失败 - {@code 0}
     */
    int deleteById(@Param("id") Integer id);

}
