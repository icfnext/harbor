# Harbor Call To Action Component

A button representing a Call to Action which, when pressed, directs the user to a specified page path or external URL.

## General Information

* `group`: Harbor
* `resourceType`: `harbor/components/content/calltoaction/v1/calltoaction`
* `resourceSuperType`: none

## Sling Model

Uses the `com.icfolson.aem.harbor.api.components.content.calltoaction.CallToAction` Model interface.

## Authorable Properties

### Dialog Fields
* `text`: Text to present as the button label.
* `linkTarget`: The page path or external URL the user should be directed to upon clicking the button.
* `size`: The button size.
* `style`: The button style - see [Button Options](http://getbootstrap.com/css/#buttons-options) for more information.
* `action`: Selection of whether to present the new page in the same window or a new window then the button is clicked.

### Dialog Field Types
* `text`: Text Field
* `linkTarget`: Path Field
* `size`: Selection
* `style`: Selection
* `action`: Selection
