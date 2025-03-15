package org.zerock.club.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class SearchCriteria extends Criteria {

	private String type;
	private String keyword;

}
