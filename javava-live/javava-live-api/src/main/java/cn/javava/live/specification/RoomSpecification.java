package cn.javava.live.specification;

import cn.javava.live.dto.RoomCriteria;
import cn.javava.live.entity.Room;
import cn.javava.live.entity.Room_;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

public class RoomSpecification implements Specification<Room> {

    private RoomCriteria criteria;

    public RoomSpecification(RoomCriteria criteria) {
        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate(Root<Room> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();
        if (isNotBlank(criteria.getName())) {
            Predicate predicate = cb.equal(root.get(Room_.name), criteria.getName());
            predicates.add(predicate);
        }
        if (isNotBlank(criteria.getState())) {
            Predicate predicate = cb.equal(root.get(Room_.state), criteria.getState());
            predicates.add(predicate);
        }
        CriteriaQuery<?> cq = query.where(predicates.toArray(new Predicate[predicates.size()]));
        return cq.getRestriction();
    }
}
