package com.cheeus.user.report;

import com.cheeus.user.report.UserReportDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserReportMapper {
    void insert(UserReportDto board);
}
