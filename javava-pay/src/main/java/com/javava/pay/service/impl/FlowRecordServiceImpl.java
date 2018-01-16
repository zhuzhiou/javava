package com.javava.pay.service.impl;

import com.javava.pay.entity.FlowRecord;
import com.javava.pay.repository.FlowRecordRepository;
import com.javava.pay.service.FlowRecordService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by wlrllr on 2018/1/15.
 */
@Service
public class FlowRecordServiceImpl implements FlowRecordService {
    @Autowired
    private FlowRecordRepository flowRecordRepository;

    @Override
    public List<FlowRecord> list(FlowRecord record,Date beginTime,Date endTime) {
        Specification<FlowRecord> specification = (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if(StringUtils.isNotBlank(record.getDeviceInfo())){
                predicates.add(criteriaBuilder.equal(root.get("deviceInfo"),record.getDeviceInfo()));
            }
            if(StringUtils.isNotBlank(record.getType())){
                predicates.add(criteriaBuilder.equal(root.get("type"),record.getType()));
            }
            if(StringUtils.isNotBlank(record.getOpenid())){
                predicates.add(criteriaBuilder.equal(root.get("openid"),record.getOpenid()));
            }
            if(StringUtils.isNotBlank(record.getUserId())){
                predicates.add(criteriaBuilder.equal(root.get("userId"),record.getUserId()));
            }
            if(StringUtils.isNotBlank(record.getWalletId())){
                predicates.add(criteriaBuilder.equal(root.get("walletId"),record.getWalletId()));
            }
            if(beginTime != null){
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("operateTime"),beginTime));
            }
            if(endTime != null){
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("operateTime"),endTime));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        };
        return flowRecordRepository.findAll(specification);
    }
}
