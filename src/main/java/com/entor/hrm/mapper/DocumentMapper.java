package com.entor.hrm.mapper;

import com.entor.hrm.mapper.provider.DocumentDynaSQLProvider;
import com.entor.hrm.po.Document;
import org.apache.ibatis.annotations.*;

import javax.print.Doc;
import java.util.List;
import java.util.Map;

import static com.entor.hrm.util.common.HrmConstants.DOCUMENT_TABLE;
import static org.apache.ibatis.mapping.FetchType.EAGER;

public interface DocumentMapper {

    /**
     * 动态查询文档
     * @param params
     * @return
     */
    @SelectProvider(type = DocumentDynaSQLProvider.class, method = "selectWithParams")
    @Results({
            @Result(column = "create_date", property = "createDate", javaType = java.util.Date.class),
            @Result(column = "user_id", property = "user", javaType = com.entor.hrm.po.User.class,
            one = @One(select = "com.entor.hrm.mapper.UserMapper.selectById", fetchType = EAGER))
    })
    List<Document> selectByPage(Map<String, Object> params);

    /**
     * 动态查询文档总记录数
     * @param params
     * @return
     */
    @SelectProvider(type = DocumentDynaSQLProvider.class, method = "count")
    Integer count(Map<String, Object> params);

    /**
     * 查询所有文档
     * @return
     */
    @Select("select * from " + DOCUMENT_TABLE)
    @Results({
            @Result(column = "create_date", property = "createDate", javaType = java.util.Date.class),
            @Result(column = "user_id", property = "user", javaType = com.entor.hrm.po.User.class,
                    one = @One(select = "com.entor.hrm.mapper.UserMapper.selectById", fetchType = EAGER))
    })
    List<Document> selectAll();

    /**
     * 根据id查询文档
     * @param id
     * @return
     */
    @Select("select * from " + DOCUMENT_TABLE + " where id = #{id} ")
    @Results({
            @Result(column = "create_date", property = "createDate", javaType = java.util.Date.class),
            @Result(column = "user_id", property = "user", javaType = com.entor.hrm.po.User.class,
                    one = @One(select = "com.entor.hrm.mapper.UserMapper.selectById", fetchType = EAGER))
    })
    Document selectById(Integer id);

    /**
     * 根据id集合查询多条记录
     * @param params
     * @return
     */
    @SelectProvider(type = DocumentDynaSQLProvider.class, method = "selectByIds")
    List<Document> selectByIds(Map<String, Object> params);

    /**
     * 根据id删除文档
     * @param id
     */
    @Delete("delete from " + DOCUMENT_TABLE + " where id = #{id}")
    void delete(Integer id);

    /**
     * 添加文档
     * @param document
     */
    @InsertProvider(type = DocumentDynaSQLProvider.class, method = "insertDocument")
    void insert(Document document);

    /**
     * 修改文档
     * @param document
     */
    @UpdateProvider(type = DocumentDynaSQLProvider.class, method = "updateDocument")
    void update(Document document);

    /**
     * 根据id批量删除文档
     * @param params
     */
    @DeleteProvider(type = DocumentDynaSQLProvider.class, method = "batchDeleteDocument")
    void batchDelete(Map<String, Object> params);
}
