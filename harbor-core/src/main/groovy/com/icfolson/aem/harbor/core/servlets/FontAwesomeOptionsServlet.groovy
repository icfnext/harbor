package com.icfolson.aem.harbor.core.servlets

import com.day.cq.commons.jcr.JcrConstants
import com.google.common.base.Charsets
import com.google.common.base.Optional
import com.icfolson.aem.harbor.api.constants.components.ComponentConstants
import com.icfolson.aem.library.api.request.ComponentServletRequest
import com.icfolson.aem.library.core.servlets.optionsprovider.AbstractOptionsProviderServlet
import com.icfolson.aem.library.core.servlets.optionsprovider.Option
import groovy.util.logging.Slf4j
import org.apache.felix.scr.annotations.sling.SlingServlet
import org.apache.sling.api.resource.Resource

import javax.jcr.Node

@SlingServlet(paths = ComponentConstants.FONT_AWESOME_SERVLET_PATH)
@Slf4j("LOG")
class FontAwesomeOptionsServlet extends AbstractOptionsProviderServlet {

    private final static String FONT_AWESOME_ICON_CLASS_SEARCH_REGEX = ".+fa.+:before.+"

    private final static String ICON_CLASS_EXTRA_CHARACTER_REMOVAL_REGEX = "\\.|:before.*"

    private final static String HTML_STRING_FOR_OPTIONS_MAP = "%s <i class='fa %s'></i>"

    @Override
    protected List<Option> getOptions(ComponentServletRequest request) {
        // Get resource which contains the Font Awesome CSS
        def resource = request.resourceResolver.getResource(ComponentConstants.FONT_AWESOME_CSS_FILE_PATH)

        def iconOptions = getIconOptions(resource)

        iconOptions.sort { option1, option2 ->
            option1.text <=> option2.text
        }
    }

    @Override
    protected Optional<String> getOptionsRoot(ComponentServletRequest componentServletRequest) {
        Optional.absent()
    }

    private List<Option> getIconOptions(Resource resource) {
        def iconList = getIconsFromResource(resource)

        getOptionsFromIconList(iconList)
    }

    private List<String> getIconsFromResource(Resource resource) {
        def iconList = []

        try {
            def dataProperty = resource.adaptTo(Node).getProperty(
                JcrConstants.JCR_CONTENT + "/" + JcrConstants.JCR_DATA)

            def binary = dataProperty.binary
            def stream = binary.stream

            def builder = new StringBuilder()

            stream.newReader(Charsets.UTF_8.name()).eachLine { line ->
                if (line.matches(FONT_AWESOME_ICON_CLASS_SEARCH_REGEX)) {
                    def lineFormattedIntoIconName = line.replaceAll(ICON_CLASS_EXTRA_CHARACTER_REMOVAL_REGEX, "")

                    builder.append(lineFormattedIntoIconName).append('\n')
                }
            }

            iconList = builder.toString().split("\\r?\\n") as List

            stream.close()
            binary.dispose()
        } catch (Exception e) {
            LOG.error("An error occurred while attempting to parse the Font Awesome file.", e)
        }

        iconList
    }

    private List<Option> getOptionsFromIconList(List<String> iconList) {
        def mapToBeConvertedToOptionsList = iconList.collectEntries { icon ->
            [icon, String.format(HTML_STRING_FOR_OPTIONS_MAP, icon, icon)]
        }

        Option.fromMap(mapToBeConvertedToOptionsList)
    }
}