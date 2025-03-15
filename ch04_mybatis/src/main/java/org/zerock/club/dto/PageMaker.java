package org.zerock.club.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.Data;

@Data
public class PageMaker {

	private int totalCount;
	private int startPage;
	private int endPage;
	private boolean prev;
	private boolean next;
	private int displayPageNum = 10;
	private Criteria cri;
	private List list = new ArrayList();

	public void setCri(Criteria cri) {
		this.cri = cri;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;

		calcData();
	}

	private void calcData() {
		// (int)(Math.ceil(9/10.0)*10)=10
		endPage = (int) (Math.ceil(cri.getPage() / (double) displayPageNum) * displayPageNum);

		startPage = (endPage - displayPageNum) + 1;
		// (int)(Math.ceil(195/10.0))=20
		int tempEndPage = (int) (Math.ceil(totalCount / (double) cri.getPerPageNum()));

		if (endPage > tempEndPage) {
			endPage = tempEndPage;
		}

		prev = startPage == 1 ? false : true;
		// 10 * 10 >= 195 ? false : true = true
		next = endPage * cri.getPerPageNum() >= totalCount ? false : true;

	}

	public String makeQuery(int page) {

		UriComponents uriComponents = UriComponentsBuilder.newInstance()
				.queryParam("page", page)
				.queryParam("perPageNum", cri.getPerPageNum())
				.build();

		return uriComponents.toUriString();
	}

	public String makeSearch(int page) {

		UriComponents uriComponents = UriComponentsBuilder.newInstance()
				.queryParam("page", page)
				.queryParam("perPageNum", cri.getPerPageNum())
				.queryParam("type", ((SearchCriteria) cri).getType())
				.queryParam("keyword", ((SearchCriteria) cri).getKeyword())
				.build();

		return uriComponents.toUriString();
	}
}
