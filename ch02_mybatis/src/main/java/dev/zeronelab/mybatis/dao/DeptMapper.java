package dev.zeronelab.mybatis.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import dev.zeronelab.mybatis.vo.DeptEntity;

@Mapper
public interface DeptMapper {
	public List<DeptEntity> listDept();

}
