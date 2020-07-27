package jp.search.reponsiory;

import jp.search.Constants;
import jp.search.pojo.SearchBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.result.HighlightPage;
import org.springframework.stereotype.Repository;

@Repository
public class SearchRepositoryImpl implements SearchRepository {

    @Autowired
    private SolrTemplate solrTemplate;

    @Override
    public SearchBean findByName(String id) {
        SearchBean searchBean = solrTemplate.getById(Constants.SOLR_COLLECTION, id, SearchBean.class).get();
        return searchBean;
    }

    @Override
    public Page<SearchBean> findAllWithPageable(Pageable pageable) {
        return null;
    }

    @Override
    public HighlightPage<SearchBean> findWithHighlight(Pageable pageable) {
        return null;
    }
}
