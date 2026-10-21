package com.example.tlias.service;

import com.example.tlias.pojo.Emp;
import com.example.tlias.pojo.PageBean;

import java.time.LocalDate;

/**
 * 员工管理 Service 接口
 */
public interface EmpService {

    /** 分页条件查询 */
    PageBean page(Integer page, Integer pageSize, String name, Short gender,
                  LocalDate begin, LocalDate end, Integer deptId);

    Emp findById(Integer id);

    void add(Emp emp);

    void update(Emp emp);

    void deleteById(Integer id);
}
