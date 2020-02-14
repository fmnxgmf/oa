package cn.fmnx.oa.common.page;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @ClassName PageResult
 * @Description: TODO
 * @Author gmf
 * @Date 2020/2/5
 * @Version V1.0
 **/
@Data
@ApiModel(description = "分页数据模板")
public class PageResult<T> {
    /**
     * //总条数
     */
    @ApiModelProperty(name = "total",value = "总条数")
    private Long totalCount;
    /**
     * //总页数
     */
    @ApiModelProperty(name = "totalPage",value = "总页数")
    private Integer totalPage;
    /**
     * //当前页
     */
    @ApiModelProperty(name = "pageNum",value = "当前页")
    private int pageNum;
    /**
     * //每页的数量
     */
    @ApiModelProperty(name = "pageSize",value = "每页的数量")
    private int pageSize;
    /**
     * //当前页的数量
     */
    @ApiModelProperty(name = "size",value = "当前页的数量")
    private int size;
    /**
     * 包装的数据对象
     */
    @ApiModelProperty(name = "items",value = "包装的数据对象,返回的所需数据")
    private List<T> items;

    public PageResult() {
    }
    public PageResult(PageInfo<T>pageInfo) {
        this.totalCount = pageInfo.getTotal();
        this.items = pageInfo.getList();
        this.totalPage = pageInfo.getPages();
        this.pageNum = pageInfo.getPageNum();
        this.size = pageInfo.getSize();
        this.pageSize = pageInfo.getPageSize();
    }

}
