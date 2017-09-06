# Harbor Call To Action Component

A button representing a Call to Action which, when pressed, directs the user to a 
specified page path or external URL.

## General Information

* `group`: Harbor
* `resourceType`: `harbor/components/content/calltoaction/v1/calltoaction`

## Sling Model

Uses the `com.icfolson.aem.harbor.api.components.content.calltoaction.CallToAction` Model interface.

## HTL

* `calltoaction.html` - Principal rendering HTL for the component.  Produces a 
  Call to Action button based on the configuration of the implementation.  Supports 
  links which open in either the current or a new window.
  
## Default Implementation

`com.icfolson.aem.harbor.core.components.content.calltoaction.v1.DefaultCallToAction`

## Dialog Fields

* `text` - Test to render as the label of the call to action
* `action` - Selection of whether the link target should open in a new window/tab
* `linkHref` - The Path or URL to link to.  Defaults to the current page's url