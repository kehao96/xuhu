package com.xuhu.onlinechargingsystem.mapper;


import com.xuhu.onlinechargingsystem.domain.PayRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Mapper
@Repository
public interface RecordMapper {
    List<PayRecord>  queryAllRecordsByUsername(String username);
    boolean addRecord(PayRecord record);
    boolean updateRecord(PayRecord record);
    PayRecord queryOneRecord(@Param("username") String username, @Param("date") Date date);
    List<PayRecord> queryAllRecords();
}
