package com.example.tlias.service.impl;

import com.example.tlias.mapper.EmpMapper;
import com.example.tlias.pojo.Emp;
import com.example.tlias.pojo.PageBean;
import com.example.tlias.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 员工管理 Service 实现类
 */
@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;

    @Override
    public PageBean page(Integer page, Integer pageSize, String name, Short gender,
                         LocalDate begin, LocalDate end, Integer deptId) {
        if (page == null || page < 1) {
            page = 1;
        }
        if (pageSize == null || pageSize < 1) {
            pageSize = 10;
        }
        Long total = empMapper.count(name, gender, begin, end, deptId);
        Integer offset = (page - 1) * pageSize;
        List<Emp> rows = empMapper.page(name, gender, begin, end, deptId, offset, pageSize);
        return new PageBean(total, rows);
    }

    @Override
    public Emp findById(Integer id) {
        return empMapper.findById(id);
    }

    @Override
    public void add(Emp emp) {
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        if (emp.getPassword() == null || emp.getPassword().isEmpty()) {
            emp.setPassword("123456"); // 默认密码
        }
        empMapper.insert(emp);
    }

    @Override
    public void update(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.update(emp);
    }

    @Override
    public void deleteById(Integer id) {
        empMapper.deleteById(id);
    }
}
