package cn.javava.live.service;

import cn.javava.live.entity.LiveRoomPo;
import cn.javava.live.entity.LiveRoomPo_;
import cn.javava.live.vo.LiveRoomCriteria;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

public class LiveRoomSpecification implements Specification<LiveRoomPo> {

    private LiveRoomCriteria criteria;

    public LiveRoomSpecification(LiveRoomCriteria criteria) {
        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate(Root<LiveRoomPo> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();
        if (isNotBlank(criteria.getNameEq())) {
            Predicate predicate = cb.equal(root.get(LiveRoomPo_.name), criteria.getNameEq());
            predicates.add(predicate);
        }
        if (isNotBlank(criteria.getNameLike())) {
            Predicate predicate = cb.like(root.get(LiveRoomPo_.name), criteria.getNameLike());
            predicates.add(predicate);
        }
        if (isNotBlank(criteria.getStateEq())) {
            Predicate predicate = cb.equal(root.get(LiveRoomPo_.state), criteria.getStateEq());
            predicates.add(predicate);
        }
        CriteriaQuery<?> cq = query.where(predicates.toArray(new Predicate[predicates.size()]));
        return cq.getRestriction();
    }
}
