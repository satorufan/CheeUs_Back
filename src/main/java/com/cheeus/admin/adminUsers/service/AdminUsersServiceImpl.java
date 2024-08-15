package com.cheeus.admin.adminUsers.service;

import com.cheeus.admin.adminUsers.dto.AdminUsersDto;
import com.cheeus.admin.adminUsers.mapper.AdminUsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminUsersServiceImpl implements AdminUsersService {

    @Autowired
    private AdminUsersMapper mapper;

    @Override
    public List<AdminUsersDto> findAll() {
        return mapper.findAll();
    }

    @Override
    public AdminUsersDto findByEmail(String id) {
        return (AdminUsersDto) mapper.findByEmail(id);
    }

    @Override
    public void save(AdminUsersDto profileData) {
        if (profileData.getId() == null) {
            mapper.insert(profileData);
        } else {
            mapper.update(profileData);
        }
    }

    @Override
    public void delete(String id) {
        mapper.delete(id);
    }
}
