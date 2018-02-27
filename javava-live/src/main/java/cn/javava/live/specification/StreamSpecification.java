package cn.javava.live.specification;

import cn.javava.live.dto.StreamCriteria;
import cn.javava.live.entity.Stream;
import cn.javava.live.entity.Stream_;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

public class StreamSpecification implements Specification<Stream> {

    private StreamCriteria criteria;

    public StreamSpecification(StreamCriteria criteria) {
        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate(Root<Stream> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();
        if (isNotBlank(criteria.getChannelIdEq())) {
            Predicate predicate = cb.equal(root.get(Stream_.channelId), criteria.getChannelIdEq());
            predicates.add(predicate);
        }
        if (isNotBlank(criteria.getStreamAliasEq())) {
            Predicate predicate = cb.equal(root.get(Stream_.streamAlias), criteria.getStreamAliasEq());
            predicates.add(predicate);
        }
        if (isNotBlank(criteria.getPublishNameEq())) {
            Predicate predicate = cb.equal(root.get(Stream_.publishName), criteria.getPublishNameEq());
            predicates.add(predicate);
        }
        if (isNotBlank(criteria.getCloseReasonEq())) {
            Predicate predicate = cb.equal(root.get(Stream_.closeReason), criteria.getCloseReasonEq());
            predicates.add(predicate);
        }
        if (criteria.getCreateDateGe() != null) {
            Predicate predicate = cb.greaterThanOrEqualTo(root.get(Stream_.createDate), criteria.getCreateDateGe());
            predicates.add(predicate);
        }
        CriteriaQuery<?> cq = query.where(predicates.toArray(new Predicate[predicates.size()]));
        return cq.getRestriction();
    }

}
