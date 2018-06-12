package com.entor.hrm.mapper;

import com.entor.hrm.mapper.provider.NoticeDynaSQLProvider;
import com.entor.hrm.po.Notice;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;
import java.util.Map;

import static com.entor.hrm.util.common.HrmConstants.NOTICE_TABLE;

public interface NoticeMapper {

    /**
     * 动态查询通知
     *
     * @param params
     * @return
     */
    @SelectProvider(type = NoticeDynaSQLProvider.class, method = "selectWithParams")
    @Results({
            @Result(column = "create_date", property = "createDate", javaType = java.util.Date.class),
            @Result(column = "user_id", property = "user", javaType = com.entor.hrm.po.User.class,
                    one = @One(select = "com.entor.hrm.mapper.UserMapper.selectById", fetchType = FetchType.EAGER))
    })
    List<Notice> selectByPage(Map<String, Object> params);

    /**
     * 动态查询通知总记录数
     *
     * @param params
     * @return
     */
    @SelectProvider(type = NoticeDynaSQLProvider.class, method = "count")
    Integer count(Map<String, Object> params);

    /**
     * 查询所有用户
     *
     * @return
     */
    @Select("select * from " + NOTICE_TABLE + " ")
    @Results({
            @Result(column = "create_date", property = "createDate", javaType = java.util.Date.class),
            @Result(column = "user_id", property = "user", javaType = com.entor.hrm.po.User.class,
            one = @One(select = "com.entor.hrm.mapper.UserMapper.selectById", fetchType = FetchType.EAGER))
    })
    List<Notice> getAllNotice();

    /**
     * 根据id查询通知
     *
     * @param id
     * @return
     */
    @Select("select * from notice_inf where id = #{id}")
    @Results({
            @Result(column = "create_date", property = "createDate", javaType = java.util.Date.class),
            @Result(column = "user_id", property = "user", javaType = com.entor.hrm.po.User.class,
                    one = @One(select = "com.entor.hrm.mapper.UserMapper.selectById", fetchType = FetchType.EAGER))
    })
    Notice getById(Integer id);

    /**
     * 获取最新通知
     *
     * @return
     */
    @Results({
            @Result(column = "create_date", property = "createDate", javaType = java.util.Date.class),
            @Result(column = "user_id", property = "user", javaType = com.entor.hrm.po.User.class,
                    one = @One(select = "com.entor.hrm.mapper.UserMapper.selectById", fetchType = FetchType.EAGER))
    })
    @Select("select * from " + NOTICE_TABLE + " order by create_date desc limit 0,1")
    Notice selectNewest();

    /**
     * 获取近期通知(近期最多五条记录)
     *
     * @return
     */
    @Results({
            @Result(column = "create_date", property = "createDate", javaType = java.util.Date.class),
            @Result(column = "user_id", property = "user", javaType = com.entor.hrm.po.User.class,
                    one = @One(select = "com.entor.hrm.mapper.UserMapper.selectById", fetchType = FetchType.EAGER))
    })
    @Select("select * from " + NOTICE_TABLE + " order by create_date desc limit 0,5")
    List<Notice> selectRecent();

    /**
     * 根据id删除通知
     *
     * @param id
     */
    @Delete("delete from notice_inf where id = #{id}")
    void deleteById(Integer id);

    /**
     * 动态修改通知
     *
     * @param notice
     */
    @UpdateProvider(type = NoticeDynaSQLProvider.class, method = "updateNotice")
    void update(Notice notice);

    /**
     * 动态添加通知
     *
     * @param notice
     */
    @InsertProvider(type = NoticeDynaSQLProvider.class, method = "insertNotice")
    void insert(Notice notice);

    /**
     * 根据id批量删除通知
     *
     * @param params
     */
    @DeleteProvider(type = NoticeDynaSQLProvider.class, method = "batchDeleteNotice")
    void batchDelete(Map<String, Object> params);

}
