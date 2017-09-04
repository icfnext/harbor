# Harbor Bootstrap Primary Navbar (v1)

Produces a Bootstrap Navbar rendering appropriate for use as a sites primary 
navigation.  Aggregates the following components via resource inclusion:

* Bootstrap Brand
* Bootstrap Primary Navigation

## General Information

* `group`: Harbor Navigation
* `resourceType`: `harbor/components/content/navigation/bootstrapnavigation/bootstrapprimarynavbar/v1/bootstrapprimarynavbar`
* `identifiable`

## Sling Model

Uses the `com.icfolson.aem.harbor.api.components.content.navigation.bootstrapnavigation.navbar.BootstrapPrimaryNavbar` Model interface.

## HTL 

* `bootstrapprimarynavbar.html` - Renders the containing `nav` element and an inner 
  `div` styled with either a `container-fluid` or `container` class depending on whether 
  the navbar is full width based on the implementation of the sling model interface. 
 
## Default Implementation

`com.icfolson.aem.harbor.core.components.content.navigation.bootstrapnavigation.navbar.v1.DefaultBootstrapPrimaryNavbar`

* `boolean isSticky()` - Whether the navigation bar should stick to the top of the page 
  during scrolling.  
* `boolean isFullWidth()` - Whether the navigation bar should render full width.
* `String getBrandResourceType()` - Returns the resource type to include for the 
  Brand label of the navigation bar.  Defaults to 
  `harbor/components/content/navigation/brand/bootstrapbrand/v1/bootstrapbrand`
* `String getPrimaryNavigationResourceType()` - Returns the resource type to 
  include as the primary navigation of the navigation bar.  Defaults to 
  `harbor/components/content/navigation/bootstrapnavigation/bootstrapprimarynavigation/v1/bootstrapprimarynavigation`
* `String getNavigationToggleScreenReaderLabel()` - String label to render 
  as part of the toggle button in mobile view.  Defaults to the injected 
  value of `navigationToggleScreenReaderLabel`, returning `null` if unavailable.