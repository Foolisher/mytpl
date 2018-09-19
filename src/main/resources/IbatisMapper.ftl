<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE sqlMap PUBLIC "-//iBATIS.com//DTD SQL Map 2.0//EN" "http://ibatis.apache.org/dtd/sql-map-2.dtd">
<sqlMap namespace="${conf.basePackage}.mapper.${conf.bean}DAO">
  <resultMap id="BaseResultMap" type="${conf.basePackage}.bean.${conf.bean}">
    <id column="id" property="id"/>
    <#list columns as col>
      <#if col.fieldName!='id'>
    <result column="${col.columnName}" property="${col.fieldName}"/>
      </#if>
    </#list>
  </resultMap>

  <sql id="Base_Column_List">
    <#list columns as col><#if col.fieldName!="id">${col.columnName}</#if><#if col_has_next && col_index &gt; 0>, </#if></#list>
  </sql>

  <insert id="insert" parameterType="${conf.basePackage}.bean.${conf.bean}">
    INSERT INTO ${conf.table} (<include refid="Base_Column_List"/>)
    VALUES (NOW(), NOW(), <#list columns as col><#if col.fieldName!="id" && col.fieldName!="gmtCreate" && col.fieldName!="gmtModified">${"#"+col.fieldName+"#"}<#if col_has_next>, </#if></#if></#list>)
  </insert>

  <select id="queryById" resultMap="BaseResultMap" parameterType="java.lang.Long">
    SELECT id,<include refid="Base_Column_List"/>
      FROM ${conf.table}
    WHERE id = ${"#id#"}
  </select>

  <sql id="Where_Clause">
    <dynamic>
    <#list columns as col>
    <#if col.fieldName!="features" && col.fieldName!="description">
      <isNotNull property="${col.fieldName}" prepend=" and ">${col.columnName} = ${"#"+col.fieldName+"#"}</isNotNull>
    </#if></#list>
    </>
  </sql>

  <select id="countBy" resultType="int" parameterType="map">
    SELECT COUNT(1)
      FROM ${conf.table}
    WHERE 1=1
    <include refid="Where_Clause"/>
  </select>

  <select id="queryBy" resultMap="BaseResultMap" parameterType="map">
    SELECT id,<include refid="Base_Column_List"/>
    FROM ${conf.table}
    WHERE 1=1
    <include refid="Where_Clause"/>
    <isNotNull property="limit">
        limit <isNotNull property="offset">#offset#,</isNotNull> #limit#
    </isNotNull>
    ORDER BY id DESC
  </select>

  <delete id="deleteById" parameterType="long">
    DELETE FROM ${conf.table}
    WHERE id = #id#
  </delete>

  <update id="updateById" parameterType="${conf.basePackage}.bean.${conf.bean}">
    UPDATE ${conf.table}
    SET gmt_modified = NOW()
    <dynamic>
    <#list columns as col>
      <#if col.fieldName!="id" && col.fieldName!="gmtCreate" && col.fieldName!="gmtModified">
      <isNotNull property="${col.fieldName}" prepend=",">${col.columnName} = ${"#"+col.fieldName+"#"}</isNotNull>
      </#if></#list>
    </>
    WHERE id = #id#
  </update>


</sqlMap>