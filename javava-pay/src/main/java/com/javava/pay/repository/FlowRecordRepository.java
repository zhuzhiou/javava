package com.javava.pay.repository;

import com.javava.pay.entity.FlowRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


/**
 * Created by wlrllr on 2018/1/12.
 */
public interface FlowRecordRepository extends JpaRepository<FlowRecord,String>,JpaSpecificationExecutor<FlowRecord> {

}
