# Harbor Bootstrap Section Navigation (v1)

An extension of the Section Navigation (v1) with bootstrap specific markup.

## General Information

* `resourceType`: `harbor/components/content/navigation/bootstrapnavigation/bootstrapsectionnavigation/v1/bootstrapsectionnavigation`
* `resourceSuperType`: `harbor/components/content/navigation/sectionnavigation/v1/sectionnavigation`

## Sling Model

Uses the `com.icfolson.aem.harbor.api.components.content.tree.TreeComponent` Model interface.

## Default Implementation

`com.icfolson.aem.harbor.core.components.content.navigation.bootstrapnavigation.bootstrapsectionnavigation.v1.DefaultBootstrapSectionNavigation`
The implementation makes the following overrides to the Section Navigation.

* `getDepth` - Defaults to an Optional of `2`. 
* `navigablepagelist.html` - Renders the list using the `nav nav-pills nav-stacked` 
  classes.
  
  