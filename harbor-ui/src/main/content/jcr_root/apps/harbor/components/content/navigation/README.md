# Navigations in Harbor

The Navigation Components are arguably the most complex of the components in 
Harbor.  A navigation, at its core, is a tree of pages starting from a single 
root and branching to a certain depth.  Concrete navigational components 
vary on this theme along the axes of:

* How deep to traverse the tree
* What manner of pages to include
* What information to expose about each included page
* How to render the navigation

Each navigation component in Harbor is built on a hierarchy of concrete and 
abstract navigational classes and resource types allowing for projects to 
"jump on" as it were at an appropriate point.  For example, if your project 
does not plan on using the Bootstrap Navbar to present a navigation, there would 
likely be little value in proxying and then completely overriding the Bootstrap 
Navbar and Bootstrap Primary Navigation components - it may instead make more 
sense to proxy the un-skinned Primary Navigation component and add your project 
specific markup, style, and behavior at that level.

## Investigation of the Navigation Hierarchy

As an example let's consider the Bootstrap Section Navigation component and how 
it builds upon its parent classes and resource types to arrive at a concrete 
implementation. 

When considering the inheritance of a concrete navigation component we must consider 
the inheritance of the Java Classes, the inheritance of the Java Interfaces, 
and the Resource Type inheritance.

### Java Class Inheritance for Bootstrap Section Navigation

_Note: See below for fully qualified classes and interfaces_

* `DefaultBootstrapSectionNavigation` extends `DefaultSectionNavigation` 
   implements `BootstrapSectionNavigation` - 
   Resource Type `harbor/components/content/navigation/bootstrapnavigation/bootstrapsectionnavigation/v1/bootstrapsectionnavigation`
* `DefaultSectionNavigation` extends `DefaultSiteNavigation` 
   implements `SectionNavigation` - 
   Resource Type `harbor/components/content/navigation/sitenavigation/v1/sitenavigation`
* `DefaultSiteNavigation` extends `AbstractPageTree<PageTreeNode>` 
   implements `SiteNavigation`
   Resource Type `harbor/components/content/navigation/sitenavigation/v1/sitenavigation`
* `AbstractPageTree<PageTreeNode>` implements `Tree<PageTreeNode>`
   
The `AbstractPageTree` builds out an implementation of `getRoot` and an 
overrideable `buildTreeNodeForPage` method which recursively produces 
a `Tree` of `PageTreeNode`s.  What is in the tree is influenced by 
extending implementations overriding the `getInclusionPredicate` method 
which returns a `Predicate<PageDecorator>`.  Construction of individual 
nodes in the tree is taken care of by `transformPageAndChildren` which 
consumes a value (a PageDecorator) and a list of child nodes and returns 
a tree node.  

`DefaultSiteNavigation` makes the following decisions on the abstract methods 
in `AbstractPageTree`: Its inclusion predicate returns true for pages which are 
not marked as hidden in navigation and it produces nodes of type `DefaultSiteNavigationNode`.
The resource type associated introduces HTL for the rendering of a `nav` element. 
The contents of the `nav` is a `ul` nested to the depth of the tree.  Each list 
item is written as a link.  `sitenavigation.html` ensures there is something to 
render while `sitenavigationlist.html` is an HTL template producing the actual 
`ul` and `li` elements to the appropriate depth.  

The `DefaultSiteNavigation` also defaults the starting root of the tree to the current
page and defaults the depth to "unlimited".  This is rarely an appropriate 
combination and as such this class is intended for extension. 

`DefaultSectionNavigation` extends `DefaultSiteNavigation` making different 
choices on the starting root of the tree.  Instead of starting from the 
current page, the `DefaultSectionNavigation` finds the closest Section Landing Page 
(see Section Landing Page for information about this page type) and roots 
the tree there.  This component does not override the HTL inherited from 
the `DefaultSiteNavigation`.  

Finally, `DefaultBootstrapSectionNavigation` extends the `DefaultSectionNavigation` 
and constrains the depth of the tree to 2.  It also overrides the rendering in 
the `sitenavigationlist.html` HTL template, producing stacked `nav-pills` elements 
creating a navigation appropriate perhaps for a sidebar or navigation subsection 
of a page.

### Java Interface Inheritance for Bootstrap Section Navigation

In order to support Sling Models resource type based implementation selection, 
each Java Class supporting the Bootstrap Section Navigation has an accompanying 
Interface which it implements.  This allows the developer to extend from any 
point in the class hierarchy supported by the aforementioned resource type 
implementation selection.  

* `BootstrapSectionNavigation` extends `SectionNavigation`
* `SectionNavigation` extends `SiteNavigation`
* `SiteNavigation` extends `PageTree<PageTreeNode>`
* `PageTree<T extends PageTreeNode>` extends `Tree<T>`

### Fully Qualified Class and Interface Names

* API
  * `com.icfolson.aem.harbor.api.trees.Tree`
  * `com.icfolson.aem.harbor.api.trees.TreeNode`
  * `com.icfolson.aem.harbor.api.model.page.pagetree.PageTree`
  * `com.icfolson.aem.harbor.api.model.page.pagetree.PageTreeNode`
  * `com.icfolson.aem.harbor.api.components.content.navigation.sitenavigation.SiteNavigation`
  * `com.icfolson.aem.harbor.api.components.content.navigation.sectionnavigation.SectionNavigation`
  * `com.icfolson.aem.harbor.api.components.content.navigation.bootstrapnavigation.bootstrapsectionnavigation.BootstrapSectionNavigation`
* Core
  * `com.icfolson.aem.harbor.core.model.page.pagetree.v1.AbstractPageTree`
  * `com.icfolson.aem.harbor.core.components.content.navigation.sitenavigation.v1.DefaultSiteNavigation`
  * `com.icfolson.aem.harbor.core.components.content.navigation.sitenavigation.v1.DefaultSiteNavigationNode`
  * `com.icfolson.aem.harbor.core.components.content.navigation.sectionnavigation.v1.DefaultSectionNavigation`
  * `com.icfolson.aem.harbor.core.components.content.navigation.bootstrapnavigation.bootstrapsectionnavigation.v1.DefaultBootstrapSectionNavigation`


