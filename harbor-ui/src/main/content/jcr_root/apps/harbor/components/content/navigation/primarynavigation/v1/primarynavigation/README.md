# Harbor Primary Navigation (v1)

An extension of the Navigable Page Tree (v1) resource type intended for usage 
as a primary site navigation.  

## General Information

* `resourceType`: `harbor/components/content/navigation/sectionnavigation/v1/sectionnavigation`
* `resourceSuperType`: `harbor/components/content/navigation/navigablepagetree/v1/navigablepagetree`

## Sling Model

Uses the `com.icfolson.aem.harbor.api.components.content.tree.TreeComponent` Model interface.

## Default Implementation

`com.icfolson.aem.harbor.core.components.content.navigation.primarynavigation.v1.DefaultPrimaryNavigation`
The implementation makes the following overrides to the Navigable Page Tree.

* `getRootPage` - Selects the closest HomePage as the root page
