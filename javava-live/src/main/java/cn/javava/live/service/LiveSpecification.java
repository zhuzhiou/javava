package cn.javava.live.service;

import cn.javava.live.entity.LivePo;
import cn.javava.live.entity.LivePo_;
import cn.javava.live.vo.LiveCriteria;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

public class LiveSpecification implements Specification<LivePo> {

    private LiveCriteria criteria;

    public LiveSpecification(LiveCriteria criteria) {
        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate(Root<LivePo> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();
        if (isNotBlank(criteria.getStreamAliasEq())) {
            Predicate predicate = cb.equal(root.get(LivePo_.streamAlias), criteria.getStreamAliasEq());
            predicates.add(predicate);
        }
        if (isNotBlank(criteria.getPublishNameEq())) {
            Predicate predicate = cb.equal(root.get(LivePo_.publishName), criteria.getPublishNameEq());
            predicates.add(predicate);
        }
        if (isNotBlank(criteria.getCloseReasonEq())) {
            Predicate predicate = cb.equal(root.get(LivePo_.closeReason), criteria.getCloseReasonEq());
            predicates.add(predicate);
        }
        if (criteria.getCreateDateGe() != null) {
            Predicate predicate = cb.greaterThanOrEqualTo(root.get(LivePo_.createDate), criteria.getCreateDateGe());
            predicates.add(predicate);
        }
        CriteriaQuery<?> cq = query.where(predicates.toArray(new Predicate[predicates.size()]));
        return cq.getRestriction();
    }

}
