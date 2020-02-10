package com.bookstore.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bookstore.configuration.CommonConfiguration;
import com.bookstore.enums.ResponseErrorCodeEnum;
import com.bookstore.exception.ServiceException;
import com.bookstore.model.MediaCoverageResponseTO;
import com.bookstore.service.MediaCoverageService;

/**
 * Implementation class for media coverage service.
 * 
 * @author shivanimalhotra
 *
 */
@Service
public class MediaCoverageServiceImpl implements MediaCoverageService {

	/**
	 * Logger instance.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(MediaCoverageServiceImpl.class);

	/**
	 * CommonConfiguration instance.
	 */
	@Autowired
	private CommonConfiguration commonConfiguration;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bookstore.service.MediaCoverageService#getMediaCoverage()
	 */
	@Override
	public List<MediaCoverageResponseTO> getMediaCoverage() {
		LOGGER.debug("Sending request for getting the response from media coverage api");
		List<MediaCoverageResponseTO> mediaCoverageReponseList = new ArrayList<>();
		try {
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<List<MediaCoverageResponseTO>> response =
					restTemplate.exchange(commonConfiguration.getMediaCoverageUrl(), HttpMethod.GET, null, new ParameterizedTypeReference<List<MediaCoverageResponseTO>>() {
					});
			mediaCoverageReponseList = response.getBody();
		} catch (final ServiceException exception) {
			throw new ServiceException(ResponseErrorCodeEnum.FETCHING_MEDIA_COVERAGE_ERROR);
		}
		return mediaCoverageReponseList;
	}

}
