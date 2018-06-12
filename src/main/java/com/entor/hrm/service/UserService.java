package com.entor.hrm.service;

import com.entor.hrm.po.User;
import com.entor.hrm.service.impl.PageModel;

import java.util.List;

/**
 * 用户服务接口
 */
public interface UserService {

    /**
     * 根据loginName和password查找用户
     *
     * @param loginName
     * @param password
     * @return
     */
    User findByLoginNameAndPassword(String loginName, String password);
    /**
     * 根据id获得用户
     *
     * @param id
     * @return
     */
    User getById(Integer id);

    /**
     * 获得所有用户信息
     *
     * @return {@link List<User>}
     */
    List<User> getAll();

    /**
     * 根据参数获得用户分页信息
     *
     * @param user      关键字
     * @param pageIndex 当前页码
     * @param pageSize  指定分页记录数
     * @return {@link PageModel<User>}
     */
    PageModel<User> getByPage(User user, Integer pageIndex, Integer pageSize);

    /**
     * 动态修改用户
     *
     * @param user
     */
    void modifyUser(User user);

    /**
     * 动态保存用户
     *
     * @param user
     */
    void save(User user);

    /**
     * 根据id删除用户
     *
     * @param id
     */
    void removeById(Integer id);

    /**
     * 根据id批量删除用户
     *
     * @param ids
     */
    void batchRemoveUser(Integer[] ids);

    /**
     * 根据id批量查找记录
     * @param ids
     * @return
     */
    List<User> getByIds(Integer[] ids);
}
