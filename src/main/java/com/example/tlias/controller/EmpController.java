package com.example.tlias.controller;

import com.example.tlias.pojo.Emp;
import com.example.tlias.pojo.PageBean;
import com.example.tlias.pojo.Result;
import com.example.tlias.service.EmpService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

/**
 * 员工管理 Controller
 */
@RestController
@RequestMapping("/emps")
public class EmpController {

    @Autowired
    private EmpService empService;

    /** 分页条件查询 */
    @GetMapping
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       String name,
                       Short gender,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end,
                       Integer deptId) {
        PageBean pageBean = empService.page(page, pageSize, name, gender, begin, end, deptId);
        return Result.success(pageBean);
    }

    /** 根据ID查询员工 */
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        Emp emp = empService.findById(id);
        if (emp == null) {
            return Result.error("员工不存在");
        }
        return Result.success(emp);
    }

    /** 新增员工 */
    @PostMapping
    public Result add(@Valid @RequestBody Emp emp) {
        empService.add(emp);
        return Result.success();
    }

    /** 修改员工 */
    @PutMapping
    public Result update(@Valid @RequestBody Emp emp) {
        empService.update(emp);
        return Result.success();
    }

    /** 删除员工 */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        empService.deleteById(id);
        return Result.success();
    }
}
