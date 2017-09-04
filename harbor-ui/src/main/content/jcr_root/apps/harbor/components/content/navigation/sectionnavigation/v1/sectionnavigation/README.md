# Harbor Section Navigation (v1)

An extension of the Navigable Page Tree (v1) resource type intended for usage 
as an intra-section navigation.  

## General Information

* `resourceType`: `harbor/components/content/navigation/sectionnavigation/v1/sectionnavigation`
* `resourceSuperType`: `harbor/components/content/navigation/navigablepagetree/v1/navigablepagetree`

## Sling Model

Uses the `com.icfolson.aem.harbor.api.components.content.tree.TreeComponent` Model interface.

## Default Implementation

`com.icfolson.aem.harbor.core.components.content.navigation.sectionnavigation.v1.DefaultSectionNavigation`
The implementation makes the following overrides to the Navigable Page Tree.

* `getRootPage` - The root of the tree is the parent of the closest SectionLandingPage 
  or the closest HomePage if no section landing page is present.
* `navigablepagelist.html` - The HTL template is updated to only recurse through the 
  branch of the tree which is along the active path.  
  