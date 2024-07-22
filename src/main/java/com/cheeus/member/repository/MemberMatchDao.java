package com.cheeus.member.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.cheeus.member.domain.MemberProfile;

@Mapper
@Repository
public interface MemberMatchDao {

	// 매치 카드 불러오기
	List<MemberProfile> findAll ();
}
