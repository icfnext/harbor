# Harbor Global Page Component

Global Page Component from which Harbor based project page components are intended to inherit.

## General Information

* `resourceType`: `harbor/components/page/global/v1/global`
* `resourceSuperType`: `wcm/foundation/components/page`

## Sling Model

Uses the `com.icfolson.aem.harbor.components.page.global.Global` Model interface.

## SEO Metadata

Various aspects of page level metadata may be set under the "SEO METADATA" tab of 
the page properties.  

### Configurable Properties

* `Schema.org Page Metadata`: Controls output of common search engine metadata following schema.org properties.  Includes `name`, `description`, and `image` set based on the page's properties. Inherits to child pages.
* `Twitter Publisher Handle`: Set to the Twitter handler of the publisher including the `@` symbol.  Causes Twitter card metadata to be written to the page based on the page's properties.  Inherits to child pages.
* `Open Graph Type`: Set to the Facebook Open Graph type of the page.  Causes Facebook Open Graph metadata to be written to the page based on the page's properties.  Does NOT inherit to child pages.
* `Canonical URL`: Set to a relative page path or to a full URL if your canonical URL is something other than the current page's URL.
* Robots Metadata
  * `Add NOINDEX Metadata Tag`: Causes the NOINDEX robots metadata tag to be written to the page.  Does NOT inherit to child pages. 
  * `Add NOFOLLOW Metadata Tag`: Causes the NOFOLLOW robots metadata tag to be written to the page.  Does NOT inherit to child pages.

## Page Icon

Allows for the setting of a page's icon.  A pages icon may be used in numerous contexts 
such as breadcrumbs, link lists, page references, etc.  

### Configurable Properties

* `Icon`: Sets the search icon.  Based on the [Font Awesome](http://fontawesome.io/) icon set. 

#### Developer Note on Page Icon

The Page Icon is stored under the `icf:iconicRepresentation` property of a page's 
content resource. 

## Sitemap Configuration

Sitemap configurations, available under the "SITEMAP" tab of the Page Properties, 
affect the generation of the sitemap when using Harbor's sitemap generator. 

### Configurable Properties

* `Disable Indexing`: Indicates that the page should not be included in the sitemap.
* `Change Frequency`: Affects the change frequency in the generated sitemap.
* `Priority`: Affects the page's priority in the generated sitemap.

## Main Paragraph System

The Global Page Component respects the `icf:usesResponsiveGrid` property set on 
a page's design.  When set to true the Responsive Grid will be used in place of the 
default Paragraph System allowing for usage of layout mode.

### Developer Note on `icf:usesResponsiveGrid`

The `content.html` script of the Global Page Component expresses the logic dictating 
whether the Responsive Grid is used.  When overridden this logic must be ported to 
the overriding script.  

The page's content resource may be adapted to the `com.icfolson.aem.harbor.api.components.design.container.ContainerDesign` 
interface.  This interface exposes an `isUsesResponsiveGrid()` method which, when true, 
indicates that the resource type `wcm/foundation/components/responsivegrid` should be 
used for the main paragraph system of a page.  See `content.html` for an example.


