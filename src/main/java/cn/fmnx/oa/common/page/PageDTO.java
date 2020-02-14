package cn.fmnx.oa.common.page;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;

/**
 * @ClassName PageDTO
 * @Description: TODO
 * @Author gmf
 * @Date 2020/2/14
 * @Version V1.0
 **/
@Data
@ApiModel(description = "分页数据的数据对象,前端如果不传值则，默认值为第1页，每页大小为10")
public class PageDTO {
    /**
     * //页码
     */
    @ApiModelProperty(name = "pageNum",value = "页码")
    @Min(1)
  private  Integer pageNum ;
    /**
     * //每页显示数量
     */
    @ApiModelProperty(name = "pageSize",value = "每页显示数量")
  private Integer pageSize;
    public PageDTO(){
        if(this.getPageNum() ==null || this.getPageNum()==0){
            this.pageNum = 1;
        }
        if(this.getPageSize() == null|| this.getPageNum()==0){
            this.pageSize =10;
        }
    }

    public PageDTO(@Min(1) Integer pageNum, Integer pageSize) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }
}
