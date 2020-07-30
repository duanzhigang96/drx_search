package jp.search.reponsiory;

import jp.search.Constants;
import jp.search.pojo.SearchBean;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.HighlightOptions;
import org.springframework.data.solr.core.query.HighlightQuery;
import org.springframework.data.solr.core.query.SimpleHighlightQuery;
import org.springframework.data.solr.core.query.result.HighlightEntry;
import org.springframework.data.solr.core.query.result.HighlightPage;
import org.springframework.stereotype.Repository;

import java.io.IOException;

@Repository
public class SearchRepositoryImpl implements SearchRepository {

    @Autowired
    private SolrTemplate solrTemplate;

    @Autowired
    private SolrClient solrClient;

    @Override
    public SearchBean findById(String id) {
        SearchBean searchBean = solrTemplate.getById(Constants.SOLR_COLLECTION, id, SearchBean.class).get();
        return searchBean;
    }

    @Override
    public Page<SearchBean> findAllWithPageable(Pageable pageable) {
        return null;
    }

    @Override
    public HighlightPage<SearchBean> findWithHighlight(Pageable pageable, String item) {

        HighlightQuery query = new SimpleHighlightQuery();
        String searchField[] = {"fun_head", "fun_describe"};
        HighlightOptions highlightOptions = new HighlightOptions().addField(searchField);
        highlightOptions.setSimplePrefix("<span style='color:red'>");
        highlightOptions.setSimplePostfix("</span>");
        query.setHighlightOptions(highlightOptions);
        Criteria criteria = new Criteria("fun_describe").is(item.replace(" ", ""));
        criteria.and("fun_head").is(item.replace(" ", ""));
        query.addCriteria(criteria);
        HighlightPage<SearchBean> highlightPage = solrTemplate.queryForHighlightPage(Constants.SOLR_COLLECTION, query, SearchBean.class);
        return highlightPage;
    }
}
