package com.entor.hrm.service.impl;

import com.entor.hrm.mapper.UserMapper;
import com.entor.hrm.po.User;
import com.entor.hrm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.transaction.annotation.Isolation.DEFAULT;
import static org.springframework.transaction.annotation.Propagation.REQUIRED;

@Service("userService")
@Transactional(propagation = REQUIRED, isolation = DEFAULT, readOnly = false)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Transactional(readOnly = true)
    @Override
    public User findByLoginNameAndPassword(String loginName, String password) {
        return userMapper.selectByLoginNameAndPassword(loginName, password);
    }

    @Transactional(readOnly = true)
    @Override
    public User getById(Integer id) {
        return userMapper.selectById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> getAll() {
        return userMapper.selectAll();
    }

    @Transactional(readOnly = true)
    @Override
    public PageModel<User> getByPage(User user, Integer pageIndex, Integer pageSize) {
        // 1.整理参数
        Map<String, Object> params = new HashMap<>();
        params.put("user", user);
        // 根据检索条件查询记录总数
        int recordCount = userMapper.count(params);
        // 整理分页参数
        PageModel<User> pageModel = new PageModel<>();
        pageModel.setPageIndex(pageIndex);
        pageModel.setPageSize(pageSize);
        pageModel.setRecordCount(recordCount);
        if (recordCount > 0) {
            // 根据检索和分页条件查询用户记录，保存到分页对象中
            params.put("pageModel",pageModel);
            pageModel.setPageList(userMapper.selectByPage(params));
        }
        return pageModel;
    }

    @Override
    public void modifyUser(User user) {
        userMapper.update(user);
    }

    @Override
    public void save(User user) {
        userMapper.insert(user);
    }

    @Override
    public void removeById(Integer id) {
        userMapper.deleteById(id);
    }

    @Override
    public void batchRemoveUser(Integer[] ids) {
        Map<String, Object> params = new HashMap<>();
        params.put("ids", ids);
        userMapper.batchDelete(params);
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> getByIds(Integer[] ids) {
        // 准备参数
        Map<String, Object> params = new HashMap<>();
        params.put("ids", ids);
        // 根据集合查询记录
        return userMapper.selectByIds(params);
    }
}
