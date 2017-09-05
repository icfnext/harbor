# Harbor Content Container

A generic container for content exposing a paragraph system for the creation of content.

## General Information

* `group`: Harbor Scaffolding 
* `resourceType`: `harbor/components/content/contentcontainer/v1/contentcontainer`
* `classifiable`
* `identifiable`
* `inheritable`
* `designed container`

## Sling Model

Uses the `com.icfolson.aem.harbor.components.content.container.Container` Model interface.

## HTL

* `contentcontainer.html` - Principal rendering HTL.  Responsible for choosing 
  whether to render a `section` DOM element around the container based on the 
  `isSection()` method of the container implementation.  Also responsible for 
  calling the `innercontentcontainer.html` HTL template.
* `innercontentcontainer.html` - HTL template rendering an appropriate paragraph 
  system populated with child resource `container-par`.
* `authorhelp.html` - An author help message providing a hook which may be 
  clicked on to open the editbar for the container.

## Default Implementation 

`com.icfolson.aem.harbor.core.components.content.container.v1.DefaultContainer`

Makes the following choices for the Container methods:

* `isFullWidth()` - `false`
* `getClassification()` - uses tag based Classification
* `getContainerElement()` - div
* `getRole()` - `null`
* `isSection()` - `true`
* `isInherits()` - `false`
* `getParagraphSystemType()` - If the site is designed to use the responsive 
  grid, it uses that, otherwise it checks whether the container is to be an 
  inheriting parsys and uses that, otherwise it falls back to a parsys.