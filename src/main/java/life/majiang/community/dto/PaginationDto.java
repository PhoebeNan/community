package life.majiang.community.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 包括发布问题列表和页码信息
 *
 * @author zhaoyanan
 * @create 2019-10-23-16:24
 */
@Data
public class PaginationDto {

    private List<QuestionDto> questionDtos; //发布问题和用户信息
    private boolean showPreviousPage;
    private boolean showFirstPage;
    private boolean showNextPage;
    private boolean showEndPage;
    private Integer currentPage;
    private List<Integer> pages = new ArrayList<>(); //存储显示在前端页面的页码
    private Integer totalPage; //总页数

    /**
     * @param totalCount  从数据库中查询出来的总条数
     * @param currentPage 当前页
     * @param pageSize    当前页数据的大小
     */
    public void setPagination(Integer totalCount, Integer currentPage, Integer pageSize) {

        //把参数赋值给成员变量
        this.currentPage = currentPage;

        //总条数/每页数据的条数=总页数
        if (totalCount % pageSize == 0) {
            this.totalPage = totalCount / pageSize;
        } else {
            this.totalPage = totalCount / pageSize + 1;
        }

        //若传参后currentPage是不符合页面的页码时，则定位到第一页或最后一页，这俩语句必须放到“偏移量”之前
        if (this.currentPage < 1) {
            this.currentPage = 1;
        }
        if (this.currentPage > this.totalPage) {
            this.currentPage = this.totalPage;
        }

        //把当前页添加到pages集合中
        pages.add(this.currentPage);

        //循环的把当前页的前三页和后三页添加到pages集合中，如果前面或后面不够三页，那么只添加够的
        for (int i = 1; i <= 3; i++) {
            if (this.currentPage - i > 0) {
                pages.add(0, this.currentPage - i);
            }
            if (this.currentPage + i <= this.totalPage) {
                pages.add(this.currentPage + i);
            }
        }

        //是否展示前一页
        if (this.currentPage == 1) {
            showPreviousPage = false;
        } else {
            showPreviousPage = true;
        }
        //是否展示下一页
        if (this.currentPage == totalPage) {
            showNextPage = false;
        } else {
            showNextPage = true;
        }
        //是否展示第一页
        if (pages.contains(1)) {
            showFirstPage = false;
        } else {
            showFirstPage = true;
        }
        //是否展示最后一页
        if (pages.contains(totalPage)) {
            showEndPage = false;
        } else {
            showEndPage = true;
        }
    }

}
