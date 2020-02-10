package com.bookstore.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bookstore.model.MediaCoverageResponseTO;

/**
 * Interface for Media Coverage Service.
 * 
 * @author shivanimalhotra
 *
 */
@Component
public interface MediaCoverageService {

	/**
	 * Method to get media coverage from an external api.
	 * 
	 * @return - List<MediaCoverageResponseTO>
	 */
	public List<MediaCoverageResponseTO> getMediaCoverage();

}
