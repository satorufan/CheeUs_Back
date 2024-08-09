package com.cheeus.user.magazine;

import com.cheeus.admin.adminMagazines.dto.AdminMagazineDto;
import com.cheeus.admin.adminMagazines.mapper.AdminMagazineMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserMagazineServiceImpl implements UserMagazineService {

	@Autowired
	private AdminMagazineMapper boardMapper;

	@Override
	public List<AdminMagazineDto> findAll() {
		return boardMapper.findAll();
	}

	@Override
	public Optional<AdminMagazineDto> findById(int id) {
		return Optional.ofNullable(boardMapper.findById(id));
	}

}
