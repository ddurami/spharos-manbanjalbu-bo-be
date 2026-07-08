package com.spharos.manbanjalbu_bo_be.domain.product.dto;

import com.spharos.manbanjalbu_bo_be.domain.product.entity.Season;

public record SeasonResponse(
		Long seasonId,
		String name
) {
	public static SeasonResponse from(Season season) {
		return new SeasonResponse(season.getId(), season.getName());
	}
}
