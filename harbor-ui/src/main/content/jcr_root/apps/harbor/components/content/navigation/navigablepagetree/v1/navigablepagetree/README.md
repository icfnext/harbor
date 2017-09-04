# Harbor Navigable Page Tree (v1)

Produces an unordered listing of a rooted tree of pages within a `nav` element 
suitable for site and site section navigations.

## Usage

This resource type is intended as an abstract type from which concrete navigation 
components may extend.  It is the basis for the Primary Navigation, Section Navigation, 
Bootstrap Primary Navigation, and Bootstrap Section Navigation.

The rendering respects the following page properties:

* Hide in Navigation - Pages marked as hidden from navigation will not render
  in the tree.
* Redirect To - The HREF of pages with a configured redirect will point to the 
  page or URL being redirected to. 

## General Information

* `resourceType`: `harbor/components/content/navigation/navigablepagetree/v1/navigablepagetree`

## Sling Model

Uses the `com.icfolson.aem.harbor.api.components.content.tree.TreeComponent` Model interface.

## HTL 

* `navigablepagetree.html` - The principal renderer for the page tree. 
  Responsible for instantiating the `TreeComponent` instance, including 
  `empty.html` when appropriate, and calling upon the `navigablepagelist.html`
  template passing the root of the tree component.
* `navigablepagelist.html` - HTL Template recursively rendering the page 
  tree as a `ul`.  This template writes out the following helper properties 
  to rendered elements
  * `data-level` - a numeric value indicating the current depth.  Applied to
    the `ul` element.
  * `class: active` - a class applied to an `li` if the page it represents is 
    along the path of the currently active page.
* `empty.html` - Produces an author helper message to be displayed if no
  items are in the page tree.
 
## Default Implementation

`com.icfolson.aem.harbor.core.components.content.navigation.page.v1.DefaultNavigablePageTree`

Starting from a root node, this implementation builds up a `TreeNode<NavigablePage>` 
tree to an infinite depth.  The implementation exposes the following methods intended 
for override during extension.

* `PageDecorator getRootPage()` - Returns the page to be used as the starting 
  value for the tree.  Defaults to the current page.
* `PageDecorator getCurrentPage()` - Returns the current page if one exists.  
  Defaults to the current page as injected by Sling Models.
* `Optional<Integer> getDepth()` - Returns the intended depth of the tree.  If 
  the optional is empty the tree should be constructed to an infinite depth.
* `Predicate<PageDecorator> getInclusionPredicate()` - Returns a Predicate used 
  to determine whether or not a page should be included in the tree.  Defaults 
  to `PagePredicates.NAVIGABLE_PAGES_PREDICATE` which returns false when a page 
  is marked as hidden from navigation.
* `TreeNode<NavigablePage> transformPageAndChildren(PageDecorator page, List<TreeNode<NavigablePage>> children, int depth)` - 
  Transforms a page, children, and the current depth, into a `TreeNode<NavigablePage>`. 
  Defaults to using the `com.icfolson.aem.harbor.core.components.content.navigation.page.v1.DefaultNavigablePage`
  `NavigablePage` implementation.
  