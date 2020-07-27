package jp.search.reponsiory;

import jp.search.pojo.SearchBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.core.query.result.HighlightPage;
import org.springframework.data.solr.repository.Highlight;
import org.springframework.data.solr.repository.Query;

public interface SearchRepository {

    SearchBean findByName(String id);

    @Query("*:*")
    Page<SearchBean> findAllWithPageable(Pageable pageable);

    @Highlight(prefix = "</highlight>",postfix = "</highlight>")
    @Query("*:*")
    HighlightPage<SearchBean> findWithHighlight(Pageable pageable);

}
