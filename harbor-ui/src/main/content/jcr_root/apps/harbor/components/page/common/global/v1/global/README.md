# Harbor Global Page Component

Global Page Component from which Harbor based project page components are 
intended to inherit.

## General Information

* `resourceType`: `harbor/components/page/global/v1/global`
* `resourceSuperType`: `wcm/foundation/components/page`

## Sling Model

Uses the `com.icfolson.aem.harbor.components.page.global.Global` Model interface.

## SEO Metadata Aspect

Expresses the Metadata about the page and what metadata is to be maintained 
in a page rendering.

### Sling Model

Uses the `com.icfolson.aem.harbor.api.components.page.metapage.MetaPage` Model interface.

### Default Implementation

`com.icfolson.aem.harbor.core.components.page.metapage.v1.DefaultMetaPage`

#### Content Structure 

The following properties are advocated by the default implementation:

* `disableSchemaOrg` - By default, Schema.org metadata should be written to 
  the page head.  If set to true this metadata is to be suppressed.
* `twitterPublisherHandle` - Publisher handle for a Twitter card.  If left blank 
  no Twitter card should be written to the page head.
* `ogType` - Open Graph Type.  One of an enumeration of site or page types 
  appropriate to Facebook Open Graph.  If set Facebook Open Graph metadata should 
  be written to the page head
* `canonicalUrl` - A canonical URL for the current page.  If left empty 
  canonical metadata should not be produced. 
* `noIndex` - Indicates that a `NOINDEX` meta tag should be written to the page
* `noFollow` - Indicates that a `NOFOLLOW` meta tag should be written to the page

## Sitemap Aspect

How a page is included in a sitemap is based on its sitemap aspect.  

### Sling Model

Uses the `com.icfolson.aem.harbor.api.components.page.sitemappedpage.SitemappedPage` Model interface.

### Default Implementation

`com.icfolson.aem.harbor.core.components.page.sitemappedpage.v1.DefaultSitemappedPage`

#### Content Structure

The following properties are advocated by the default implementation:

* `icf:hiddenFromRobots` - Indicates that the Page should be considered as 
  being hidden from robots.
* `icf:changeFrequency` - Indicates the change frequency to be written to a 
  generated sitemap.
* `icf:priority` - Indicates the priority to be written to a sitemap.

## Container Aspect

Each page exposes itself as a Container of content.

### Sling Model

Uses the `com.icfolson.aem.harbor.api.components.design.container.ContainerDesign` Model interface.

### Default Implementation

`com.icfolson.aem.harbor.core.components.design.container.PageContainerDesign`

* `boolean isUsesResponsiveGrid()` - Respects and returns the `icf:usesResponsiveGrid` 
  property of a Page's design.

## HTL

The HTL overrides pointed aspects of the foundation Page type. 

* `head.html` - Overrides the foundation `head.html` in the following ways
  * Includes `pagemeta.html` - which renders the SEO Metadata Aspect of the page
  * Includes `preservicelibs.html` - a place to put code to be included prior 
    to cloud service inclusion.
* `body.html` - Overrides the foundation `body.html` in the following ways
  * Includes `content.html` if the page is not created by an authored template
* `content.html` - Leverages the Container Aspect of the page to determine whether 
  to write out a standard paragraph system or a responsive grid. 
