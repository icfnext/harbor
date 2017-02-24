package com.icfolson.aem.harbor

import com.icfolson.aem.harbor.core.components.mixins.classifiable.Classification
import com.icfolson.aem.library.models.specs.AemLibraryModelSpec
import com.icfolson.aem.namespace.api.ontology.Namespace

import static com.day.cq.tagging.TagConstants.NT_TAG

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

        // add package for classification
        slingContext.addModelsForPackage(Classification.class.package.name)
    }
}
