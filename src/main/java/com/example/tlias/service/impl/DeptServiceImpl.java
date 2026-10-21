package com.example.tlias.service.impl;

import com.example.tlias.mapper.DeptMapper;
import com.example.tlias.mapper.EmpMapper;
import com.example.tlias.pojo.Dept;
import com.example.tlias.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 部门管理 Service 实现类
 */
@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;

    @Autowired
    private EmpMapper empMapper;

    @Override
    public List<Dept> findAll() {
        return deptMapper.findAll();
    }

    @Override
    public Dept findById(Integer id) {
        return deptMapper.findById(id);
    }

    @Override
    public void add(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.insert(dept);
    }

    @Override
    public void update(Dept dept) {
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.update(dept);
    }

    @Override
    public String deleteById(Integer id) {
        Long count = empMapper.countByDeptId(id);
        if (count != null && count > 0) {
            return "该部门下存在 " + count + " 名员工，无法删除";
        }
        deptMapper.deleteById(id);
        return null;
    }
}
