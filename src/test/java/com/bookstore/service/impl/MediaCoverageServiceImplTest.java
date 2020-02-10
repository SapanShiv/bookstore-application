package com.bookstore.service.impl;

import static org.mockito.Mockito.times;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

import com.bookstore.configuration.CommonConfiguration;
import com.bookstore.enums.ResponseErrorCodeEnum;
import com.bookstore.exception.ServiceException;
import com.bookstore.model.MediaCoverageResponseTO;

import junit.framework.Assert;

/**
 * Unit test for MediaCoverageServiceImpl.
 * 
 * @author shivanimalhotra
 */
@SuppressWarnings("deprecation")
@RunWith(MockitoJUnitRunner.class)
public class MediaCoverageServiceImplTest {

	/**
	 * MediaCoverageServiceImpl instance.
	 */
	@InjectMocks
	private MediaCoverageServiceImpl mediaCoverageServiceImpl;

	/**
	 * CommonConfiguration instance.
	 */
	@Mock
	private CommonConfiguration commonConfiguration;

	/**
	 * RestTemplate instance.
	 */
	@Mock
	RestTemplate restTemplate;

	/**
	 * Test method for getMediaCoverage.
	 */
	@Test
	public void testGetMediaCoverage() {
		final MediaCoverageResponseTO mediaResponse = prepareMediaCoverageResponseTO();
		final List<MediaCoverageResponseTO> mediaResponseList = new ArrayList<>();
		mediaResponseList.add(mediaResponse);
		Mockito.when(commonConfiguration.getMediaCoverageUrl()).thenReturn("https://jsonplaceholder.typicode.com/posts");
		final List<MediaCoverageResponseTO> response = mediaCoverageServiceImpl.getMediaCoverage();
		Mockito.verify(commonConfiguration, times(1)).getMediaCoverageUrl();
		Assert.assertEquals(100, response.size());

	}

	/**
	 * Test method for getMediaCoverage throwing Exception.
	 */
	@Test(expected = ServiceException.class)
	public void testGetMediaCoverageThrowingTechnicalException() {
		Mockito.when(commonConfiguration.getMediaCoverageUrl()).thenThrow(new ServiceException(ResponseErrorCodeEnum.GENERIC_EXCEPTION, "Error occurred"));

		mediaCoverageServiceImpl.getMediaCoverage();

	}

	/**
	 * Method to prepare mediaCoverageResponseTO object.
	 * 
	 * @return - mediaCoverageResponseTO
	 */
	private MediaCoverageResponseTO prepareMediaCoverageResponseTO() {
		final MediaCoverageResponseTO mediaCoverageResponseTO = new MediaCoverageResponseTO();
		mediaCoverageResponseTO.setBody("illum quis cupiditate provident sit magnam\nea sed aut omnis\nveniam maiores ullam consequatur atque\nadipisci quo iste expedita sit quos voluptas");
		mediaCoverageResponseTO.setId(19L);
		mediaCoverageResponseTO.setTitle("adipisci placeat illum aut reiciendis qui");
		mediaCoverageResponseTO.setUserId(2L);
		return mediaCoverageResponseTO;
	}

}
