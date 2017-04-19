<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="${conf.basePackage}.mapper.${conf.bean}Mapper">
  <resultMap id="BaseResultMap" type="${conf.basePackage}.bean.${conf.bean}">
    <id column="id" property="id" jdbcType="BIGINT"/>
    <#list columns as col>
      <#if col.fieldName!='id'>
    <result column="${col.columnName}" property="${col.fieldName}" jdbcType="${col.dbType}"/>
      </#if>
    </#list>
  </resultMap>

  <sql id="Base_Column_List">
    <#list columns as col><#if col.fieldName!="id">${col.columnName}</#if><#if col_has_next && col_index &gt; 0>, </#if></#list>
  </sql>

  <insert id="insert" parameterType="${conf.basePackage}.bean.${conf.bean}" keyProperty="id" useGeneratedKeys="true">
    INSERT INTO ${conf.table} (<include refid="Base_Column_List"/>)
    VALUES (NOW(), NOW(), <#list columns as col><#if col.fieldName!="id" && col.fieldName!="gmtCreate" && col.fieldName!="gmtModified">${"#\{"+col.fieldName+"}"}<#if col_has_next>, </#if></#if></#list>)
  </insert>

  <select id="queryById" resultMap="BaseResultMap" parameterType="java.lang.Long">
    SELECT <include refid="Base_Column_List"/>
      FROM ${conf.table}
    WHERE id = ${"#\{id}"}
  </select>

  <select id="queryBy" resultMap="BaseResultMap" parameterType="map">
    SELECT <include refid="Base_Column_List"/>
    FROM ${conf.table}
    <where>
    <#list columns as col>
      <if test="${col.fieldName} != null">and ${col.columnName} = ${"#\{"+col.fieldName+"}"}</if>
    </#list>
    </where>
  </select>

  <delete id="deleteById" parameterType="java.lang.Long">
    DELETE FROM ${conf.table}
    WHERE id = ${"#\{id}"}
  </delete>

  <update id="updateById" parameterType="${conf.basePackage}.bean.${conf.bean}">
    UPDATE ${conf.table}
    <set>
    <#list columns as col>
      <#if col.fieldName!="id" && col.fieldName!="gmtCreate" && col.fieldName!="gmtModified">
      <if test="${col.fieldName} != null">${col.columnName} = ${"#\{"+col.fieldName+"}"},</if>
      </#if></#list>
      gmt_modified = NOW()
    </set>
    WHERE id = ${"#\{id}"}
  </update>


</mapper>