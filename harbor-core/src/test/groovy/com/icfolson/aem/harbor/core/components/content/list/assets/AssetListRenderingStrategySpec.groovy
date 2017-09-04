package com.icfolson.aem.harbor.core.components.content.list.assets

import com.icfolson.aem.harbor.HarborSpec

class AssetListRenderingStrategySpec extends HarborSpec {

    def setupSpec() {
        pageBuilder.content {
            harbor {
                "jcr:content" {
                    assetlist()
                }
            }
        }

        nodeBuilder.content {
            dam("sling:Folder") {
                images("sling:Folder") {
                    "img1.png"()
                    "img2.png"()
                }
            }
        }
    }

    def "to renderable list"() {
        setup:
        def assets = (1..2).collect { i ->
            def assetResource = getResource("/content/dam/images/img${i}.png")

            new MockAsset(assetResource)
        }

        def strategy = getResource("/content/harbor/jcr:content/assetlist").adaptTo(AssetListRenderingStrategy)
        def assetList = strategy.toRenderableList(assets)

        expect:
        assetList.size() == 2
    }

    def "get image source renditions"() {
        setup:
        def assets = (1..2).collect { i ->
            def assetResource = getResource("/content/dam/images/img${i}.png")

            new MockAsset(assetResource)
        }

        def strategy = getResource("/content/harbor/jcr:content/assetlist").adaptTo(AssetListRenderingStrategy)
        def assetList = strategy.toRenderableList(assets)

        expect:
        assetList*.imageSourceRendition == ["/content/dam/images/img1.png", "/content/dam/images/img2.png"]
    }
}
