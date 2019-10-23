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

    /**
     * @param totalCount  从数据库中查询出来的总条数
     * @param currentPage 当前页
     * @param pageSize    当前页数据的大小
     */
    public void setPagination(Integer totalCount, Integer currentPage, Integer pageSize) {

        this.currentPage = currentPage;
        Integer totalPage = 0;  //总页数
        if (totalCount % pageSize == 0) {
            totalPage = totalCount / pageSize;
        } else {
            totalPage = totalCount / pageSize + 1;
        }

        pages.add(currentPage);
        for (int i = 1; i <= 3; i++) {
            if (currentPage - i > 0) {
                pages.add(0, currentPage - i);
            }
            if (currentPage + i <= totalPage) {
                pages.add(currentPage + i);
            }
        }

        //是否展示前一页
        if (currentPage == 1) {
            showPreviousPage = false;
        } else {
            showPreviousPage = true;
        }
        //是否展示下一页
        if (currentPage == totalPage) {
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
