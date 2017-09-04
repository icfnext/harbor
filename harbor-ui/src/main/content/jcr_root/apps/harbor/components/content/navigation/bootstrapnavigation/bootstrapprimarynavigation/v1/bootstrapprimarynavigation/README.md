# Harbor Bootstrap Primary Navigation (v1)

An extension of the Primary Navigation (v1) with bootstrap specific markup.

## General Information

* `resourceType`: `harbor/components/content/navigation/bootstrapnavigation/bootstrapprimarynavigation/v1/bootstrapprimarynavigation`
* `resourceSuperType`: `harbor/components/content/navigation/primarynavigation/v1/primarynavigation`

## Sling Model

Uses the `com.icfolson.aem.harbor.api.components.content.tree.TreeComponent` Model interface.

## Default Implementation

`com.icfolson.aem.harbor.core.components.content.navigation.bootstrapnavigation.bootstrapsectionnavigation.v1.DefaultBootstrapSectionNavigation`
The implementation makes the following overrides to the Section Navigation.

* `getDepth` - Defaults to an Optional of `2`.
* `bootstrapprimarynavigation.html` - Overrides the primary rendering behavior to 
  not render a containing `nav` element as this is to be handled by a containing 
  navigation bar.
* `navigablepagelist.html` - Produces a level specific rendering containing any 
  items in levels 1 or greater within a dropdown menu.  When depth is greater 
  than 1 the entire element rendered for level 0 is a clickable object and the 
  level 0 link is included as the first item in the dropdown menu. 