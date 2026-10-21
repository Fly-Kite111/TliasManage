package com.example.tlias.service;

import com.example.tlias.pojo.Dept;

import java.util.List;

/**
 * 部门管理 Service 接口
 */
public interface DeptService {

    List<Dept> findAll();

    Dept findById(Integer id);

    void add(Dept dept);

    void update(Dept dept);

    /** 删除部门，返回错误信息；null 表示删除成功 */
    String deleteById(Integer id);
}
