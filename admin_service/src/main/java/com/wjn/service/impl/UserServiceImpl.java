package com.wjn.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.crypto.digest.BCrypt;
import com.wjn.bean.dto.RegisterUserDto;
import com.wjn.constant.NaturalNumber;
import com.wjn.constant.RolePermissionEnum;
import com.wjn.constant.UserEnum;
import com.wjn.mapper.RolePermissionMapper;
import com.wjn.mapper.UserMapper;
import com.wjn.model.admin.RolePermission;
import com.wjn.model.admin.User;
import com.wjn.service.UserService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther WJN
 * @date 2019/9/1 0001 下午 11:26
 * @describe
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Override
    public String getName(Integer id) {
        User user = userMapper.selectByPrimaryKey(id);
        return user.getName();
    }

    @Override
    public void register(RegisterUserDto register) {
        User user = User.of();
        BeanUtil.copyProperties(register,user);
        String password = BCrypt.hashpw(user.getPassword());
                 user
                .setPassword(password)
                .setState(NaturalNumber.one)
                .setEntryTime(DateUtil.date());
        userMapper.insert(user);
    }

    @Override
    public User findByName(String username) {
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo(UserEnum.username.name(),username);
        List<User> users = userMapper.selectByExampleAndRowBounds(example, new RowBounds(NaturalNumber.zero, NaturalNumber.one));
        return users.get(NaturalNumber.one);
    }

    @Override
    public List<Integer> getPermissionIdsByRoleId(Integer roleId) {
        Example example = new Example(RolePermission.class);
        example.selectProperties(RolePermissionEnum.permissionId.name());
        example.createCriteria().andEqualTo(RolePermissionEnum.roleId.name(),roleId);
        List<RolePermission> rolePermissions = rolePermissionMapper.selectByExample(example);
        List<Integer> list = new ArrayList<>();
        for (RolePermission rolePermission : rolePermissions) {
            list.add(rolePermission.getPermissionId());
        }
        return list;
    }
}
