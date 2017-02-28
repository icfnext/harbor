package com.icfolson.aem.harbor

import com.icfolson.aem.library.models.specs.AemLibraryModelSpec
import com.icfolson.aem.namespace.api.ontology.Namespace
import com.icfolson.aem.prosper.annotations.ModelSpec
import com.icfolson.aem.prosper.annotations.NodeTypes

import static com.day.cq.tagging.TagConstants.NT_TAG

/**
 * Base class for Harbor test specs.
 */
@NodeTypes("SLING-INF/nodetypes/nodetypes.cnd")
@ModelSpec(additionalPackages = "com.icfolson.aem.harbor.core.components.mixins.classifiable")
abstract class HarborSpec extends AemLibraryModelSpec {

    def setupSpec() {
        // add ICF namespace
        session.workspace.namespaceRegistry.registerNamespace(Namespace.ICF_OLSON, Namespace.ICF_OLSON_URI)

        // create classification tags
        nodeBuilder.etc {
            tags {
                colors(NT_TAG) {
                    red(NT_TAG)
                    green(NT_TAG)
                    blue(NT_TAG)
                }
            }
        }
    }
}
