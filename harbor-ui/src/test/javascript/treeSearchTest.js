describe("Design Search Replacement Tests: ", function() {

    describe("Tree Construction ", function(){
        it("skips", function(){
            expect(true).toEqual(true);
        });

    });

    describe("Design Search", function(){
        var content_structure = {"jcr:title":"Default Design","cq:lastModified":"Fri Jan 24 2014 13:06:47 GMT-0600","cq:lastModifiedBy":"admin","offerpage":{"par":{"components":["/libs/foundation/components/flash","/libs/foundation/components/image","/libs/foundation/components/slideshow","/libs/foundation/components/text","/libs/foundation/components/textimage"],"section":{}}},"teaserpage":{"par":{"components":["/libs/foundation/components/flash","/libs/foundation/components/image","/libs/foundation/components/slideshow","/libs/foundation/components/text","/libs/foundation/components/textimage"],"section":{}}},"promotion":{"config":{"pairings":{"components":["/libs/commerce/components/promotion/perfectpartner/pairing"],"section":{}}}},"pack":{"vlt:definition":{"filter":{"components":["/libs/cq/packaging/components/pack/definition/filter"]},"screenshots":{"components":["/libs/cq/packaging/components/pack/definition/screenshot"]}}},"collectionpage":{"vlt:definition":{"filter":{"components":["cq/workflow/components/collection/definition/resource"]}}},"formpage":{"par":{"components":"group:Form","section":{}}},"segmentpage":{"par":{"components":["/libs/cq/personalization/components/traits/logic/or","/libs/cq/personalization/components/traits/logic/and"],"section":{}},"orpar":{"components":["group:Segmentation"],"section":{}},"andpar":{"components":["group:Segmentation"],"section":{}}},"newsletterpage":{"header":{"maxWidth":"530"},"par":{"jcr:lastModifiedBy":"admin","components":["group:Newsletter"],"componentsX":["/libs/mcm/components/newsletter/image","/libs/mcm/components/newsletter/text","/libs/mcm/components/newsletter/textimage"],"jcr:lastModified":"Wed Mar 17 2010 11:14:41 GMT-0500","colctrl":{"layouts":"2;310,28,192\t2 Columns, main column left\n2;192,28,310\t2 Columns, main column right"},"section":{},"image":{"maxWidth":"310"}}},"page_geometrixx_newsletter":{"header":{"maxWidth":"530"},"par":{"jcr:lastModifiedBy":"admin","components":["group:Newsletter","/libs/foundation/components/reference"],"componentsX":["/libs/mcm/components/newsletter/image","/libs/mcm/components/newsletter/text","/libs/mcm/components/newsletter/textimage"],"jcr:lastModified":"Wed Mar 17 2010 11:14:41 GMT-0500","colctrl":{"layouts":"2;310,28,192\t2 Columns, main column left\n2;192,28,310\t2 Columns, main column right"},"section":{},"image":{"maxWidth":"310"}}},"model":{"flow":{"jcr:lastModifiedBy":"admin","components":["group:Collaboration Workflow","group:DAM Workflow","group:WCM Workflow","group:Workflow"],"jcr:lastModified":"Thu Sep 23 2010 05:41:41 GMT-0500","section":{}}},"page":{"par":{"video":{"profiles":["hq","firefoxhq"]}}},"clientcontextdesigner":{"stores":{"jcr:lastModifiedBy":"admin","components":"group:Client Context","jcr:lastModified":"Tue Aug 30 2011 08:28:01 GMT-0500","tagcloud":{"store":"tagcloud"},"resolvedsegments":{"store":"segments"},"activitystream":{"store":"activitystream","sling:resovurceType":"cq/personalization/components/contextstores/activitystream"},"geolocation":{"store":"geolocation","jcr:lastModifiedBy":"admin","serviceURL":"http://api.wipmania.com/jsonp?callback=${callback}","properties":["city","region","country","country_code"],"jcr:lastModified":"Tue Sep 13 2011 08:27:16 GMT-0500"}}},"assetshare":{"lenses":{"jcr:lastModifiedBy":"admin","components":["/libs/cq/search/components/lenses/list","/libs/cq/search/components/lenses/mosaic"],"jcr:lastModified":"Tue Jan 05 2010 05:22:21 GMT-0600","section":{}},"actions":{"jcr:lastModifiedBy":"admin","components":["/libs/dam/components/assetshare/actions/delete","/libs/dam/components/assetshare/actions/download","/libs/dam/components/assetshare/actions/lightbox","/libs/dam/components/assetshare/actions/move","/libs/dam/components/assetshare/actions/tags","/libs/dam/components/assetshare/actions/viewasset"],"jcr:lastModified":"Tue Jan 05 2010 06:25:44 GMT-0600","section":{}},"querybuilder":{"compontents":["group:Predicates"],"left":{"jcr:lastModifiedBy":"admin","components":["/libs/cq/search/components/predicates/date","/libs/dam/components/search/predicates/noexpired","/libs/cq/search/components/predicates/options","/libs/cq/search/components/predicates/path","/libs/cq/search/components/predicates/property"],"jcr:lastModified":"Tue Jan 05 2010 05:20:53 GMT-0600","section":{}},"top":{"components":["/libs/cq/search/components/predicates/date","/libs/dam/components/search/predicates/notexpired","/libs/cq/search/components/predicates/options","/libs/cq/search/components/predicates/path","/libs/cq/search/components/predicates/property"]}}},"asseteditor":{"par":{"jcr:lastModifiedBy":"admin","components":["/libs/dam/components/asseteditor/form/start","/libs/dam/components/asseteditor/form/metadata","/libs/dam/components/asseteditor/subassets","/libs/dam/components/asseteditor/tags","/libs/dam/components/asseteditor/thumbnail","/libs/dam/components/asseteditor/title","/libs/foundation/components/parsys/colctrl"],"jcr:lastModified":"Tue Jan 05 2010 06:19:02 GMT-0600","colctrl":{"layouts":"2;cq-colctrl-lt0\t2 Columns\n"},"actions":{"jcr:lastModifiedBy":"admin","components":["/libs/dam/components/asseteditor/actions/download","/libs/dam/components/asseteditor/actions/editors","/libs/dam/components/asseteditor/actions/lightbox","/libs/dam/components/asseteditor/actions/references","/libs/dam/components/asseteditor/actions/versioning","/libs/dam/components/asseteditor/actions/locking"],"jcr:lastModified":"Tue Jan 05 2010 06:16:31 GMT-0600","section":{}},"section":{}}},"master":{"mainpar":{"jcr:lastModifiedBy":"admin","components":["/libs/foundation/components/image","/libs/foundation/components/text","group:Harbor"],"jcr:lastModified":"Fri Jan 24 2014 13:06:47 GMT-0600","section":{},"columnrow":{"column":{"column-par":{"jcr:lastModifiedBy":"admin","components":"group:General","jcr:lastModified":"Fri Jan 24 2014 11:08:49 GMT-0600","section":{}}}}}}};




        it("finds a design for the mainpar (master|global|page/mainpar|parsys)", function(){
            var search_tree = Harbor.Overrides.SearchTree.buildSearchTree("master|global|page/mainpar|parsys");

            var style =  Harbor.Overrides.SearchTree.DFsearch(search_tree, {
                searchConfiguration:  Harbor.Overrides.SearchTree.defaultSearchConfig(content_structure)
            });
            style = style.pop();
            //I expect, specifically, to know what that thing is also:
            expect(style).toEqual([
                "/libs/foundation/components/image",
                "/libs/foundation/components/text",
                "group:Harbor"
            ]);
        });
    });


    describe("Tree Pruning", function(){
        it("skips", function(){
            expect(true).toEqual(true);
        });
    });

});