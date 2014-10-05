package com.citytechinc.aem.harbor.core.servlets

import com.citytechinc.aem.bedrock.core.servlets.optionsprovider.Option

import javax.jcr.Node
import javax.jcr.Property

import org.apache.felix.scr.annotations.sling.SlingServlet
import org.apache.sling.api.resource.Resource
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import com.citytechinc.aem.bedrock.api.request.ComponentServletRequest
import com.citytechinc.aem.bedrock.core.servlets.optionsprovider.AbstractOptionsProviderServlet
import com.citytechinc.aem.harbor.api.constants.components.ComponentConstants
import com.day.cq.commons.jcr.JcrConstants
import com.google.common.base.Charsets
import com.google.common.base.Optional

@SlingServlet(paths = ComponentConstants.FONT_AWESOME_SERVLET_PATH)
public class FontAwesomeOptionsServlet extends AbstractOptionsProviderServlet {
	private final static String FONT_AWESOME_ICON_CLASS_SEARCH_REGEX = ".+fa.+:before.+";

	private final static String ICON_CLASS_EXTRA_CHARACTER_REMOVAL_REGEX = "\\.|:before.*"

	private final static String HTML_STRING_FOR_OPTIONS_MAP = "%s <i class='fa %s'></i>";

	private static final Logger LOG = LoggerFactory.getLogger(
	FontAwesomeOptionsServlet.class);

	@Override
	protected List<Option> getOptions(final ComponentServletRequest request) {
		//Get resource which contains the Font Awesome CSS
		def resource = request.getResourceResolver().getResource(ComponentConstants.FONT_AWESOME_CSS_FILE_PATH);
		List<Option> iconOptions = parseResourceForIconOptions(resource);
		//We need to sort this list of maps, so here I pass a comparator to the groovy sort function.
		return iconOptions.sort { map1, map2 ->
			def textFromMap1 = map1.getText();
			def textFromMap2 = map2.getText();
			//Groovy will compare strings alphabetically, so here I just see which one comes first, and return the correct integer.
			if (textFromMap1 < textFromMap2) {
				return -1;
			} else if (textFromMap1 > textFromMap2) {
				return 1;
			} else {
				return 0;
			}
		};
	}

	private List<Option> parseResourceForIconOptions(Resource resource) {
		List<String> iconList = getIconListFromResource(resource);
		List<Option> iconOptionsList = getOptionsFromIconList(iconList);
		return iconOptionsList;
	}

	private List<String> getIconListFromResource(Resource resource) {
		List<String> iconList;
		Reader fileReader;
		try {
			fileReader = getReaderFromResource(resource);
			//This method closes fileReader
			iconList = getIconListFromReader(fileReader);
		} catch (Exception e) {
			LOG.error("An error occured while attempting to parse the Font Awesome file.", e);
			if (fileReader) {
				fileReader.close();
			}
		}

		return iconList;
	}

	private Reader getReaderFromResource(Resource resource) {
		InputStreamReader inputStreamReaderFromResource;
		def node = resource.adaptTo(Node.class);
		Property dataProperty = node.getProperty(JcrConstants.JCR_CONTENT + "/" + JcrConstants.JCR_DATA);
		inputStreamReaderFromResource = new InputStreamReader(dataProperty.getBinary().getStream(), Charsets.UTF_8);
		return inputStreamReaderFromResource;
	}

	private List<String> getIconListFromReader(Reader reader) {
		StringBuffer currentFileStringBuffer = new StringBuffer();
		//This method closes the reader on return.
		reader.eachLine({ line ->
			if (line.matches(FONT_AWESOME_ICON_CLASS_SEARCH_REGEX)) {
				def lineFormattedIntoIconName = line.replaceAll(ICON_CLASS_EXTRA_CHARACTER_REMOVAL_REGEX, "");
				currentFileStringBuffer.append(lineFormattedIntoIconName + "\n");
			}
		})
		String fileDataString = currentFileStringBuffer.toString();
		String[] fileDataStringArray = fileDataString.split("\\r?\\n");
		List<String> fileDataStringList = Arrays.asList(fileDataStringArray);
		return fileDataStringList;
	}

	private List<Option> getOptionsFromIconList(List<String> iconList) {
		Map<String, String> mapToBeConvertedToOptionsList = new HashMap<String, String>();
		iconList.each { icon ->
			mapToBeConvertedToOptionsList.put(icon, String.format(HTML_STRING_FOR_OPTIONS_MAP, icon, icon));
		}
		return Option.fromMap(mapToBeConvertedToOptionsList);
	}

	@Override
	protected Optional<String> getOptionsRoot(ComponentServletRequest componentServletRequest) {
		return Optional.absent();
	}
}