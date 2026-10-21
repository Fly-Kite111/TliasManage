package com.example.tlias.controller;

import com.example.tlias.pojo.Dept;
import com.example.tlias.pojo.Result;
import com.example.tlias.service.DeptService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 部门管理 Controller
 */
@RestController
@RequestMapping("/depts")
public class DeptController {

    @Autowired
    private DeptService deptService;

    /** 查询全部部门 */
    @GetMapping
    public Result list() {
        List<Dept> list = deptService.findAll();
        return Result.success(list);
    }

    /** 根据ID查询部门 */
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        Dept dept = deptService.findById(id);
        if (dept == null) {
            return Result.error("部门不存在");
        }
        return Result.success(dept);
    }

    /** 新增部门 */
    @PostMapping
    public Result add(@Valid @RequestBody Dept dept) {
        deptService.add(dept);
        return Result.success();
    }

    /** 修改部门 */
    @PutMapping
    public Result update(@Valid @RequestBody Dept dept) {
        deptService.update(dept);
        return Result.success();
    }

    /** 删除部门 */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        String error = deptService.deleteById(id);
        if (error != null) {
            return Result.error(error);
        }
        return Result.success();
    }
}
