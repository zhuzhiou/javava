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
        if (isNotBlank(criteria.getChannelId())) {
            Predicate predicate = cb.equal(root.get(Stream_.channelId), criteria.getChannelId());
            predicates.add(predicate);
        }
        if (isNotBlank(criteria.getStreamAlias())) {
            Predicate predicate = cb.equal(root.get(Stream_.streamAlias), criteria.getStreamAlias());
            predicates.add(predicate);
        }
        if (isNotBlank(criteria.getPublishName())) {
            Predicate predicate = cb.equal(root.get(Stream_.publishName), criteria.getPublishName());
            predicates.add(predicate);
        }
        CriteriaQuery<?> cq = query.where(predicates.toArray(new Predicate[predicates.size()]));
        return cq.getRestriction();
    }

}
