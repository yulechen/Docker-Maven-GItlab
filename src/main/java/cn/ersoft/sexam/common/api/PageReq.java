package cn.ersoft.sexam.common.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Sort;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageReq {

    public static final Sort.Direction DEFAULT_DIRECTION = Sort.Direction.DESC;
    public static final String DEFAULT_PROPERTY = "createTime";

    private int size;

    private int pageNo;

    private Sort.Direction direction;

    /**
     * 用entity的属性，而并非数据库中的字段名
     */
    private String property;

}
