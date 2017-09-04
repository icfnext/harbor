# Harbor Bootstrap Brand (v1)

A rendering of a textual brand logo.

## Usage

Intended for inclusion in a navigation bar.

## General Information

* `resourceType`: `harbor/components/content/navigation/brand/bootstrapbrand/v1/bootstrapbrand`

## Sling Model

Uses the `com.icfolson.aem.harbor.api.components.content.navigation.brand.bootstrapbrand.BootstrapBrand` Model interface.

## HTL 

* `bootstrapbrand.html` - Renders a `navbar-brand` link with a title.
 
## Default Implementation

`com.icfolson.aem.harbor.core.components.content.navigation.brand.bootstrapbrand.v1.DefaultBootstrapBrand`

Provides the title and href for the brand link rendered in the HTL.  Defaults to 
linking to the closest HomePage using the HomePage title as the brand text.  
The following methods are provided for override during extension:

* `String getTitle()` - Defaults to looking up the closest HomePage and returning 
  its title.  Returns "Brand Title" if no HomePage can be found.
* `String getHref()` - Defaults to looking up the closest HomePage and returning 
  its href.  Returns "#" if no HomePage can be found.