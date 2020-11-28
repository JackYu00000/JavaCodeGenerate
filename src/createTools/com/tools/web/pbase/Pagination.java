package com.tools.web.pbase;


import java.io.Serializable;
import java.util.List;

import com.tools.entity.CreateUtils;

public class Pagination implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 默认页面显示条数
    private final static int DEFAULTPAGESIZE= 10;
    private final static int DEFAILTPAGENO= 1;

    // 每页规定显示记录数
    private int pageSize;
    // 当前页数
    private int pageNo=DEFAILTPAGENO;
    // 页面显示的数据
    private List<?> data;
    // 共多少条记录
    private int totalCount;
    // 共多少页
    private int totalPage;

    public Pagination() {
         this.pageNo=DEFAILTPAGENO;
         this.pageSize=DEFAULTPAGESIZE;
    }

    /**
     *@parampageNo
     *@parampageSize
     */
    public Pagination(int pageNo,int pageSize) {
         if(pageSize <= 0) {
             this.pageSize=DEFAULTPAGESIZE;
         }else{
             this.pageSize= pageSize;
         }
         this.pageNo= pageNo;
    }

    /**
     *@returnthe totalCount
     */
    public int getTotalCount() {
         return this.totalCount;
    }

    /**
     *@paramtotalCount
     */
    public void setTotalCount(int totalCount) {
         this.totalCount= totalCount;
    }

    /**
     *@returnthe totalPages
     */
    public int getTotalPages() {
         if(this.totalCount%this.pageSize== 0) {
             return(this.totalCount/this.pageSize);
         }
         return(this.totalCount/this.pageSize+ 1);
    }

    /**
     *@returnthe pageSize
     */
    public int getPageSize() {
         return this.pageSize;
    }

    /**
     *@return
     */
    public boolean hasNextPage() {
         return (getPageNo() >= getTotalPages() - 1);
    }

    /**
     *@return
     */
    public boolean hasPreviousPage() {
         return(getPageNo() <= 1);
    }

    /**
     *@return
     */
    public int getStartRow() {
         return((this.pageNo- 1) *this.pageSize);
    }

    /**
     *@return
     */
    public int getEndRow() {
         return(this.pageNo*this.pageSize);
    }

    /**
     *@parampageSize
     */
    public void setPageSize(int pageSize) {
         this.pageSize= pageSize;
    }

    /**
     *@returndata
     */
    public List<?> getData() {
         return this.data;
    }

    /**
     *@paramdata
     */
    public void setData(List<?> data) {
         this.data= data;
    }

    /**
     *@returnpageNo
     */
    public int getPageNo() {
         return this.pageNo;
    }

    /**
     *@parampageNo
     */
    public void setPageNo(int pageNo) {
         this.pageNo= pageNo;
    }

    /**
     *@returnthe totalPage
     */
    public int getTotalPage() {
    	totalPage = getTotalCount() / getPageSize();
         if(getTotalCount() % getPageSize() > 0) {
             return totalPage + 1;
         }
         return totalPage;
    }

    /**
     *@paramtotalPage
     */
    public void setTotalPage(int totalPage) {
         this.totalPage= totalPage;
    }
    
    public String toJsonString(){
    	StringBuffer tabSB = new StringBuffer();
		tabSB.append("{").append("\n");
		tabSB.append("\t\"pageSize\":").append(this.pageSize).append(",\n");
		tabSB.append("\t\"pageNo\":").append(this.pageNo).append(",\n");
		tabSB.append("\t\"totalCount\":").append(this.totalCount).append(",\n");
		tabSB.append("\t\"totalPage\":").append(this.totalPage).append("\n");
		tabSB.append("\t").append("}\n");
		return tabSB.toString();
    }
}
