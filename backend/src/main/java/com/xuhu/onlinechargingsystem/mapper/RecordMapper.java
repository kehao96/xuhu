package com.xuhu.onlinechargingsystem.mapper;

import com.xuhu.onlinechargingsystem.domain.Customer;
import com.xuhu.onlinechargingsystem.domain.PayRecord;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RecordMapper {
    List<PayRecord>  queryAllRecords();
    boolean addRecord(PayRecord record);
}
