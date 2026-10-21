package com.example.tlias.mapper;

import com.example.tlias.pojo.Emp;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;

/**
 * 员工管理 Mapper 接口
 */
@Mapper
public interface EmpMapper {

    /** 条件查询总记录数 */
    @Select({
        "<script>",
        "select count(*) from emp e",
        "<where>",
        "  <if test='name != null and name != \"\"'> and e.name like concat('%', #{name}, '%') </if>",
        "  <if test='gender != null'> and e.gender = #{gender} </if>",
        "  <if test='begin != null'> and e.entrydate &gt;= #{begin} </if>",
        "  <if test='end != null'> and e.entrydate &lt;= #{end} </if>",
        "  <if test='deptId != null'> and e.dept_id = #{deptId} </if>",
        "</where>",
        "</script>"
    })
    Long count(@Param("name") String name, @Param("gender") Short gender,
               @Param("begin") LocalDate begin, @Param("end") LocalDate end,
               @Param("deptId") Integer deptId);

    /** 分页条件查询（左连接部门表带出部门名称） */
    @Select({
        "<script>",
        "select e.*, d.name as dept_name from emp e left join dept d on e.dept_id = d.id",
        "<where>",
        "  <if test='name != null and name != \"\"'> and e.name like concat('%', #{name}, '%') </if>",
        "  <if test='gender != null'> and e.gender = #{gender} </if>",
        "  <if test='begin != null'> and e.entrydate &gt;= #{begin} </if>",
        "  <if test='end != null'> and e.entrydate &lt;= #{end} </if>",
        "  <if test='deptId != null'> and e.dept_id = #{deptId} </if>",
        "</where>",
        "order by e.update_time desc",
        "limit #{offset}, #{pageSize}",
        "</script>"
    })
    List<Emp> page(@Param("name") String name, @Param("gender") Short gender,
                   @Param("begin") LocalDate begin, @Param("end") LocalDate end,
                   @Param("deptId") Integer deptId,
                   @Param("offset") Integer offset, @Param("pageSize") Integer pageSize);

    /** 根据ID查询员工 */
    @Select("select e.*, d.name as dept_name from emp e left join dept d on e.dept_id = d.id where e.id = #{id}")
    Emp findById(Integer id);

    /** 新增员工 */
    @Insert("insert into emp(username, password, name, gender, image, job, entrydate, dept_id, create_time, update_time) " +
            "values(#{username}, #{password}, #{name}, #{gender}, #{image}, #{job}, #{entrydate}, #{deptId}, #{createTime}, #{updateTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Emp emp);

    /** 修改员工 */
    @Update("update emp set username = #{username}, name = #{name}, gender = #{gender}, image = #{image}, " +
            "job = #{job}, entrydate = #{entrydate}, dept_id = #{deptId}, update_time = #{updateTime} where id = #{id}")
    void update(Emp emp);

    /** 根据ID删除员工 */
    @Delete("delete from emp where id = #{id}")
    void deleteById(Integer id);

    /** 统计某部门下的员工数量（用于删除部门前校验） */
    @Select("select count(*) from emp where dept_id = #{deptId}")
    Long countByDeptId(Integer deptId);
}
