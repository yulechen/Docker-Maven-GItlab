package cn.ersoft.sexam.common.api;

import lombok.Getter;
import lombok.ToString;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * @author Wangkun
 * @since 2018/7/22 11:34
 */
@Getter
@ToString
public class PageVO<VO> {

    private Long totalElements;

    private Integer totalPages;

    private Integer pageSize;

    private Integer pageNo;

    private List<VO> content;

    public PageVO(Page page, List<VO> content) {
        this.totalElements = page.getTotalElements();
        this.totalPages = page.getTotalPages();
        this.pageSize = page.getPageable().getPageSize();
        this.pageNo = page.getPageable().getPageNumber();
        this.content = content;
    }

    public PageVO(PageRequest pageRequest) {
        this.pageNo = pageRequest.getPageNumber();
        this.pageSize = pageRequest.getPageSize();
        this.totalPages = 0;
        this.totalElements = 0L;
    }

}
