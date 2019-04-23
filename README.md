# Harbor 

Harbor is a sites development SDK for AEM Sites built on the key tenants of 

* Cohesion
* Semantics
* Extensibility

## Compatibility

Harbor Version(s) | AEM Version
------------ | -------------
5.x.x | 6.3, 6.4, 6.5 
4.x.x | 6.4
3.3.x | 6.3 SP2
3.0.x | 6.3 

## Dependencies and Versions

* [AEM Library](https://github.com/icfnext/aem-library) `12.0.0`
* [Component Plugin](https://github.com/icfnext/cq-component-maven-plugin) `5.1.0`
* [AEM Namespace Extension](https://github.com/icfnext/aem-namespace-extension) `0.4.0`

Built for use in AEM 6.3+ 

## Modules

Each module in Harbor expresses an aspect of Harbor and, to some extent, may 
be used in isolation from the other modules where useful.

### Harbor API

The API layer of Harbor expresses a vocabulary of component types, component 
interactions, and data structures.  

### Harbor Core

The Core, or implementation, layer of Harbor proposes a concrete content structure 
for component instances.  The implementations of the components presented in the 
vocabulary expressed by the API presume certain property names and give certain meaning 
to relations like the parent - child Resource relationship.  

When this content structure is useful to you and in line with the needs of your project, 
extend it either as a pure proxy or with overrides.  If you are going to structure your 
content in a different way, then write your own implementation, our feelings won't be hurt - 
you can still use the vocabulary expressed by the API with your own content structure 
thus affording the benefits of the remainder of Harbor. 

### Harbor UI

The UI layer of Harbor exposes the default rendering of componentry focusing on the production 
of clean and semantic HTML and on reusability.  Throughout the HTL implementations you will 
find many of the renderings split into various HTL includes and template calls making it 
easier to selectively override during extension.  

Used classes in the HTL adapt from the API layer with models exposed using the [Sling Models 
resource type based instance lookup](https://sling.apache.org/documentation/bundles/models.html#associating-a-model-class-with-a-resource-type-since-130).  
This means that your project's implementations will be picked for usage without having to 
update the HTL in any way.

Some of the classes and structures in the HTL are somewhat Bootstrap specific and as such 
if using another framework (or no framework) you may need to override more than you would if 
using Bootstrap, but many of the components just produce simple semantic HTML that should 
be usable in pretty much any context.

## Components

The following is a list of the componentry in Harbor along with links to the relevant 
documentation for each.  Keep in mind the word "Component" is used loosely here as, 
while the Harbor UI module defines Resource Types, Harbor does not define any 
components as AEM would define the term.  

The componentry is broken into groups from an organizational perspective.  This 
grouping does not dictate or affect how usages of the componentry at a project 
level group concrete component implementations.  

* Group: Harbor
  * [Heading (v1)](harbor-ui/src/main/content/jcr_root/apps/harbor/components/content/heading/v1/heading)
  * [Title (v1)](harbor-ui/src/main/content/jcr_root/apps/harbor/components/content/title/v1/title)
  * [Subtitle (v1)](harbor-ui/src/main/content/jcr_root/apps/harbor/components/content/subtitle/v1/subtitle)
  * [Text (v1)](harbor-ui/src/main/content/jcr_root/apps/harbor/components/content/text/v1/text)
  * [Call to Action (v1)](harbor-ui/src/main/content/jcr_root/apps/harbor/components/content/calltoaction/v1/calltoaction)
  * [HTML (v1)](harbor-ui/src/main/content/jcr_root/apps/harbor/components/content/html/v1/html)
* Group: Harbor Scaffolding
  * [Content Container (v1)](harbor-ui/src/main/content/jcr_root/apps/harbor/components/content/contentcontainer/v1/contentcontainer)
  * [Page Header (v1)](harbor-ui/src/main/content/jcr_root/apps/harbor/components/content/pageheader/v1/pageheader)
  * [Page Footer (v1)](harbor-ui/src/main/content/jcr_root/apps/harbor/components/content/pagefooter/v1/pagefooter)
  * [Column Row (v1)](harbor-ui/src/main/content/jcr_root/apps/harbor/components/content/columnrow/v1/columnrow)
    * [Column (v1)](harbor-ui/src/main/content/jcr_root/apps/harbor/components/content/columnrow/v1/column)
  * [Accordion (v1)](harbor-ui/src/main/content/jcr_root/apps/harbor/components/content/accordion/v1/accordion)
    * [Accordion Item (v1)](harbor-ui/src/main/content/jcr_root/apps/harbor/components/content/accordion/v1/accordionitem)
  * [Dynamic Accordion (v1)](harbor-ui/src/main/content/jcr_root/apps/harbor/components/content/dynamicaccordion/v1/dynamicaccordion)
    * [Parsys Accordion Item (v1)](harbor-ui/src/main/content/jcr_root/apps/harbor/components/content/dynamicaccordion/items/parsysaccordionitem/v1/parsysaccordionitem)
  * [Tabs (v1)](harbor-ui/src/main/content/jcr_root/apps/harbor/components/content/tabs/v1/tabs)
    * [Tab (v1)](harbor-ui/src/main/content/jcr_root/apps/harbor/components/content/tabs/v1/tab)
  * [Dynamic Tabs (v1)](harbor-ui/src/main/content/jcr_root/apps/harbor/components/content/dynamictabs/v1/dynamictabs)
    * [Parsys Tab (v1)](harbor-ui/src/main/content/jcr_root/apps/harbor/components/content/dynamictabs/tabs/parsystab/v1/parsystab)
  * [Carousel (v1)](harbor-ui/src/main/content/jcr_root/apps/harbor/components/content/carousel/v1/carousel)
  * [Dynamic Carousel (v1)](harbor-ui/src/main/content/jcr_root/apps/harbor/components/content/dynamiccarousel/v1/dynamiccarousel)
    * [Parsys Slide (v1)](harbor-ui/src/main/content/jcr_root/apps/harbor/components/content/dynamiccarousel/slides/parsysslide/v1/parsysslide)
* Group: Harbor Lists
  * [Link List (v1)](harbor-ui/src/main/content/jcr_root/apps/harbor/components/content/lists/linklist/v1/linklist)
  * [Dynamic List (v1)](harbor-ui/src/main/content/jcr_root/apps/harbor/components/content/lists/dynamiclist/v1/dynamiclist)
  * [Abstract Automated List (v1)](harbor-ui/src/main/content/jcr_root/apps/harbor/components/content/lists/automatedlist/v1/automatedlist)
  * [Child Page List (v1)](harbor-ui/src/main/content/jcr_root/apps/harbor/components/content/lists/childpagelist/v1/childpagelist)
* Group: Harbor Navigation
  * [Navigable Page Tree (v1)](harbor-ui/src/main/content/jcr_root/apps/harbor/components/content/navigation/navigablepagetree/v1/navigablepagetree)
  * [Primary Navigation (v1)](harbor-ui/src/main/content/jcr_root/apps/harbor/components/content/navigation/primarynavigation/v1/primarynavigation)
  * [Section Navigation (v1)](harbor-ui/src/main/content/jcr_root/apps/harbor/components/content/navigation/sectionnavigation/v1/sectionnavigation)
  * [Bootstrap Primary Navigation (v1)](harbor-ui/src/main/content/jcr_root/apps/harbor/components/content/navigation/bootstrapnavigation/bootstrapprimarynavigation/v1/bootstrapprimarynavigation)
  * [Bootstrap Primary Navbar (v1)](harbor-ui/src/main/content/jcr_root/apps/harbor/components/content/navigation/bootstrapnavigation/bootstrapprimarynavbar/v1/bootstrapprimarynavbar)
  * [Bootstrap Section Navigation (v1)](harbor-ui/src/main/content/jcr_root/apps/harbor/components/content/navigation/bootstrapnavigation/bootstrapsectionnavigation/v1/bootstrapsectionnavigation)
  * [Bootstrap Brand (v1)](harbor-ui/src/main/content/jcr_root/apps/harbor/components/content/navigation/brand/bootstrapbrand/v1/bootstrapbrand)
  * [Breadcrumb Navigation (v1)](harbor-ui/src/main/content/jcr_root/apps/harbor/components/content/breadcrumbnavigation/v1/breadcrumbnavigation)
* Page
  * [Global (v1)](harbor-ui/src/main/content/jcr_root/apps/harbor/components/page/global/v1/global)

## Models, Services, and Servlets

### Sitemap

The Sitemap servlet, `com.icfolson.aem.harbor.core.servlets.sitemap.SiteMapServlet`, listens 
for `cq:Page` requests with a selector of `sitemap` and an extension of `xml`.  This 
servlet adapts the content resource of the page to the `com.icfolson.aem.harbor.api.domain.sitemap.SiteMap` 
interface and uses that interface's `marshall(OutputStream)` method to marshall the 
xml of the sitemap.  

The default implementation, `com.icfolson.aem.harbor.core.domain.sitemap.v1.DefaultSiteMap` 
is a Sling Model leveraging JAXB to create the XML representation of the sitemap.  
Projects wishing to override the behavior of the sitemap generation will most often 
be able to do so by creating a new SiteMap implementation, either by extending the default 
or by creating from scratch, and registering the implementation as a Sling Model specific 
to your page's resource type.

## Core Concepts

### Page Types

Harbor provides the following Page interfaces each representing a type of page generally 
common to a site structure:

* `com.icfolson.aem.harbor.api.content.page.HierarchicalPage`
* `com.icfolson.aem.harbor.api.content.page.HomePage`
* `com.icfolson.aem.harbor.api.content.page.SectionLandingPage`

Each of these types has default implementations which are available for extension. 

* `com.icfolson.aem.harbor.core.content.page.v1.DefaultHierarchicalPage`
* `com.icfolson.aem.harbor.core.content.page.v1.DefaultHomePage`
* `com.icfolson.aem.harbor.core.content.page.v1.DefaultSectionLandingPage`

#### Hierarchical Page

Represents any page within a page tree.  Exposes methods for finding both the nearest 
SectionLandingPage and the nearest HomePage.  Also exposes two methods to help determine 
the current page type, `isHomePage` and `isSectionLandingPage`.  

At a project level, Hierarchical Page, Home Page, and Section Landing Page implementations 
should all adapt to `HierarchicalPage`.  Given a page you should be able to determine the 
type of the page by adapting the page's content resource to `HierarchicalPage` and calling 
upon `isHomePage` and `isSectionLandingPage`.  Below is an example home page implementation 
at a project level.

```java
@Model(adaptables = Resource.class,
        adapters = {HomePage.class, HierarchicalPage.class},
        resourceType = MyProjectHomePage.RESOURCE_TYPE)
public class MyProjectHomePage extends DefaultHomePage {

    public static final String RESOURCE_TYPE = "my-project/components/page/homepage";

}
```

#### Home Page

Represents the root of a site.  Hierarchical Pages expose the `getHomePage()` method 
which attempts to find the closest home page, resulting in an `Optional<HomePage>`. 

#### Section Landing Page

Represents the root of a section of a site.  Section Landing Pages may be nested to 
represent sub sections under sections.  Hierarchical Pages expose the `getSectionLandingPage()`
method which attempts to find the closest section landing page, resulting in an 
`Optional<SectionLandingPage>`.

### Classifiability

Many components in Harbor are "Classifiable".  This allows for the application of semantics to an 
otherwise general component.  For example, if you are producing a recipe, you might classify your 
Container component using a `Recipe` tag.  Systematically, for most components, this classification 
simply applies an additional class attribute to the rendered HTML for the component which allows 
for extended usage such as targeting of styles or unique indexability.

The `com.icfolson.aem.harbor.api.components.mixins.classifiable.Classification` interface 
exposes a `List<String> getClassificationNames()` method returning the list of classifications 
for the classified component instance as a list of strings.  Harbor also provides an 
implementation of `Classification`, `com.icfolson.aem.harbor.core.components.mixins.classifiable.TagBasedClassification` 
which expects the classifications to be stored as Tags under the property `icf:classification`.  
Default implementations within Harbor make use of the `TagBasedClassification`, however 
you are free to provide your own implementation as your needs dictate. 

### Paragraph System Containers

The `com.icfolson.aem.harbor.api.components.mixins.paragraphsystem.ParagraphSystemContainer` 
API is intended to be implemented by components which present a paragraph system to 
manage child components.  It offers a single `String getParagraphSystemType()` method 
which indicates the type of paragraph system to be exposed.  This method may be overridden 
at an extending implementation level to do things like force the usage of an inheriting 
paragraph system or a layout container. 

Within Harbor, the default resource type for paragraph systems is `wcm/foundation/components/parsys`.

### Containers

Many of the elements of Bootstrap require placement within a Container.  In Harbor there are numerous Container Components which produce such a Container suitable for placing other components within.  These are identified by the `Container` demarcation in the documentation.  

For example, the Content Container Component is a basic Container.

General information about Containers in Bootstrap can be found [in the Bootstrap Container documentation](http://getbootstrap.com/css/#overview-container).

### Dynamics

A common authoring pattern is the creation of lists or sets of things which may 
have disparate natures.  Take for example a list.  An author may want to create 
a list which contains

* Simple text items
* Some links to external sites
* Some links to internal pages
* Some more complex links which show both a title and a thumbnail of the item linked to

Components in the Harbor Dynamics family enable this pattern by exposing a means 
to select the type of item you are going to add to a set or list.  

Returning to the example of a list, the Dynamic List component OOB gives authors 
the ability to choose whether they want to create an external link item, an 
internal link item, or a text item, each time they add a new item to the list. 
This mechanism is also extensible at a project level giving your developers the 
ability to add new types of list items. 

The Harbor Dynamics component family currently consists of 

* Dynamic List
* Dynamic Tabs 
* Dynamic Carousel 
* Dynamic Accordion

### Inheriting Variants

By default component instances receive their configuration from the concrete Resource 
representing the instance.  In support of configuration inheritance, many components 
expose an `inheriting` variant.  In all cases where inheriting variants exists, their 
resource type is that of the extended component with `/inheriting` added to the path. 
For example, the Text resource type is `harbor/components/content/text/v1/text` while 
its inheriting variant is `harbor/components/content/text/v1/text/inheriting`.

#### On Inheritance for Child Resources

Many of the component backing classes presume the existence and meaning of child 
resources of a container resource.  For example, `DefaultLinkList` treats its direct 
children as `ListableLink`s.  When inheriting children, if inheritance is overridden, 
the new children are not combined with inherited children - they override them.  

For example, if a link list exists on a Home Page with 3 items, and these are being 
inherited on a child page, if an author adds a listable link to a child page, they 
will not have 4 links on the child page, they will have 1, the new one they added.

## Usage as an SDK

### Proxying Harbor Implementations

Harbor implementation usage is similar to the Proxy Component Pattern advocated by 
the Core Component development model.  Due to the nature of the Harbor implementations 
however it differs in a few important ways.

First, Harbor does not expose components in the classical sense.  That is to say, Harbor 
contains no `cq:Component` Nodes, no dialogs, and no edit configurations.  These are all 
to be produced by the projects using Harbor.  

Documentation herein presumes the use of the [Component Plugin](https://github.com/OlsonDigital/cq-component-maven-plugin) 
in the implementing project. While this is not a technical requirement for using Harbor
it makes things a lot easier in our opinion.  

To proxy an entire Harbor implementation at a Project level we create a class which 
extends from the Harbor implementation and is annotated to extend from the resource 
type as well.  Here is an example of a title proxy.

```java
package com.harbor.my.awesome.project;

import com.citytechinc.cq.component.annotations.Component;

@Component(
        value = "Title",
        resourceSuperType = HarborTitle.RESOURCE_TYPE
)
class Title extends com.icfolson.aem.harbor.core.components.content.title.v1.Title {

    public static final String RESOURCE_TYPE = "archetype-test/components/content/title";

}
```

## FAQ

### Where are the Dialogs?

Didn't you read the documentation?  There aren't any.  As indicated in the 
"Proxying Harbor Implementations", Harbor does not expose components in the 
traditional sense.  It exposes a rendering and authoring libraries through the UI 
module, a programatic vocabulary through the API module, and a suggested content 
structure through the CORE module, but leaves the actual component definitions including 
dialog definitions to the consuming project.

### How do I Bake in Components?

The main concern when baking in components is ensuring the authorability is appropriate 
for the baked in components.  Most notably, they shouldn't expose delete or copy/move 
operations.  A common pattern we advocate is creation of a `bakedin` variant for the 
component.  The structure we usually use for this is the creation of a `bakedin` resource 
type which extends a non baked in type.  For example, if creating a baked in title, 
you might have a `yourapp/components/content/title/bakedin` component which extends 
from `yourapp/components/content/title` component and define the baked in variant to 
not support deletion, copying, or moving via the edit config. 

### Are These Duplicates of Core Components?

Some of them are close - for example, title - but they're not the same.  Harbor I would 
say is actually more opinionated.  Consider the title example - in Harbor, you can't 
pick the size of the title, it is always an `h1`, because we believe your title 
should be your `h1`.  

It's also worth noting that there is nothing in Harbor that precludes the use of 
Core Components.  Mix and match as you see fit. 

### Why not Just Contribute to Core Components?

Lay off man.  What is it with you and these Core Components? 

We do have hopes of contributing some of our implementations to Core Components 
in the future where it makes sense.  As mentioned above, some of what is in Harbor is 
more opinionated than what is in Core Components.  In our opinion some things are 
opinionated to a degree that they are not even appropriate in Core Components.

Plus, it is ostensibly a longer time to "market" cycle to get things into Core 
Components than it would be to make a change here. 
