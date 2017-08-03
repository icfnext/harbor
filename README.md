# Harbor 

Harbor is a collection of content and page components, services, and patterns constituting a common starting point for projects embodying a consistent philosophy of development.  From both an author and developer enablement perspective Harbor follows these tenets: 

* Cohesion
* Semantics
* Extensibility

## Components

* Group: Harbor
  * [Heading (v1)](harbor-ui/src/main/content/jcr_root/apps/harbor/components/content/heading/v1/heading)
  * [Title (v1)](harbor-ui/src/main/content/jcr_root/apps/harbor/components/content/title/v1/title)
  * [Subtitle (v1)](harbor-ui/src/main/content/jcr_root/apps/harbor/components/content/subtitle/v1/subtitle)
  * [Text (v1)](harbor-ui/src/main/content/jcr_root/apps/harbor/components/content/text/v1/text)
* Group: Harbor Scaffolding
  * [Content Container (v1)](harbor-ui/src/main/content/jcr_root/apps/harbor/components/content/contentcontainer/v1/contentcontainer)
  * [Page Header (v1)](harbor-ui/src/main/content/jcr_root/apps/harbor/components/content/pageheader/v1/pageheader)
  * [Page Footer (v1)](harbor-ui/src/main/content/jcr_root/apps/harbor/components/content/pagefooter/v1/pagefooter)
* Page
  * [Global (v1)](harbor-ui/src/main/content/jcr_root/apps/harbor/components/page/global/v1/global)

## Internal Promotional Videos

The following videos were developed (poorly) to promote Harbor internally.

* [General Information](https://drive.google.com/open?id=0B2FwTcEm5yJ6dWh4LUZJS2pYcnM)
* [Enabling Content Reusability](https://drive.google.com/open?id=0B2FwTcEm5yJ6SzRTTUhUdlhkUXM)
* [Enabling Experience Reusability](https://drive.google.com/open?id=0B2FwTcEm5yJ6VjdZOHJrMU5zcUk)

### Title Component

* Group: Harbor

Presents the Page Title of the current page in an H1 DOM element.  The output H1 element will have an id attribute set to a sanitized version of the title text allowing for fragment linking.  Both the title text and element id may be overridden by authors.

#### Authorability

| Dialog Field | Description |
| ------------ | ----------- |
| Text         | Sets the rendered title text.  When left empty, the title text will default to the page's Page Title. |
| ID           | Sets the string ID to be attributed to the H1 element rendered for the title.  When left empty, the ID will be produced by sanitizing the title text. Indended to allow anchor linking to title elements. |

### Subtitle Component

* Group: Harbor

Presents the Page Subtitle of the current page in an H2 DOM element.  The output H2 element will have an id attribute set to a sanitized version of the subtitle text allowing for fragment linking.  Both the subtitle text and element id may be overridden by authors.

#### Authorability

| Dialog Field | Description |
| ------------ | ----------- |
| Text         | Sets the rendered subtitle text.  When left empty, the subtitle text will default to the page's Page Subtitle. |
| ID           | Sets the string ID to be attributed to the H2 element rendered for the subtitle.  When left empty, the ID will be produced by sanitizing the subtitle text. |

### Heading Component

* Group: Harbor

Presents a heading element appropriate for titling sections of content.  This component is NOT meant to be used as the principal title for content and as such only exposes heading options H2-H6.  If an H1 element is desired, the Title component should be used.

#### Authorability

| Dialog Field | Description |
| ------------ | ----------- |
| Size         | Establishes the size or level of the Heading.  Available sizes include H2 - H6.  If an H1 element is desired the Title component should be used instead. |
| Text         | Sets the rendered heading text. |
| ID           | Sets the string ID to be attributed to the rendered heading element.  When left empty, the ID will be produced by sanitizing the heading text. |

### Text Component

* Group: Harbor
* Classifiable

The Harbor Text component will allow the author to compose text within a Rich Text Editor configured to enable commonly used plugins.

#### Authorability

Text is edited via the text in-place editor.

| Dialog Field | Description |
| ------------ | ----------- |
| Classification | Input support for classifiability of the text component instance. |

### HTML Component

* Group: Harbor

This component is designed to accept any sort of HTML snippet such as an image or anchor tag.

#### Authorability

| Dialog Field | Description |
| ------------ | ----------- |
| Source        | It allows to add HTML snippet |

### Call to Action Component

* Group: Harbor

Presents a Call to Action button which, when pressed, directs the user to a specified page path or external URL.

#### Authorability 

| Dialog Field | Description |
| ------------ | ----------- |
| Text         | Text to present as the button label |
| Link Target  | The page path or external URL the user should be directed to upon clicking the button |
| Size         | The button size |
| Style        | The button style - see [Button Options](http://getbootstrap.com/css/#buttons-options) for more information | 
| Action       | Selection of whether to present the new page in the same window or a new window then the button is clicked |

### Modal Call to Action Component

* Group: Harbor

### Accordion Group Component

* Group: Harbor

Presents a group of Accordion Items allowing for the ad-hoc addition of new items.  Each Accordion Item exposes a Paragraph System into which any number of components may be placed.  When in edit mode an author help message will appear indicating this and making it easy to select the Accordion Group in Touch UI.

#### Authorability

Accordion items are added using the "Add Experience" button in the toolbar of the Accordion Group Component.
  
When viewed in a non Preview / Publish mode, all Accordion Items will be expanded to expose their content to authors.

##### Accordion Group Authoring

| Dialog Field | Description | 
| ------------ | ----------- |
| Open First Item | Indicates whether the first Accordion Item in the Accordion Group whould be opened by default. |

##### Accordion Item Authoring

Each Accordion Item in an Accordion Group exposes its own Paragraph System to which any number of components may be added using normal authoring practices.  While authoring all Accordion Items will be opened to allow for modification.  When in Preview or Publish mode the Accordion Group will operate as a collection of collapsible elements.

The order of established Accordion Items may be modified using the up and down arrows presented in the Toolbar of a given Accordion Item.  Pressing the up button for the first Accordion Item in an Accordion Group or the down button for the last Accordion Item in an Accordion Group will have no effect.

Accordion Items may be removed completely by deleting them using the Delete option presented in the Toolbar for the individual Accordion Item.

| Dialog Field | Description | 
| ------------ | ----------- |
| Title | Text title to present for the Accordion Item. |

### Asset List Component

* Group: Harbor Lists

Render a list of assets (with optional metadata) constructed from a search query.

#### Authorability

Assets are assembled by configuring the **List Construction** properties in the component dialog.  These properties are used to build a search query and resulting hits are transformed into a renderable list based on the configuration of the **List Rendering** dialog properties.

##### List Construction

| Dialog Field | Description |
| ------------ | ----------- |
| Path | Path to search for assets |
| Tags | Tags to filter assets |
| Use AND Logic | If checked, tags will use AND logic rather than OR logic to filter assets |
| Property Relative Path | Relative path from asset node to tags property |
| Limit | Number of assets to return.  Defaults to 10. |
| Order By | Property name to order asset results |
| Sort | Direction to sort by (ascending/descending) |

##### List Rendering

| Dialog Field | Description |
| ------------ | ----------- |
| Suppress Images? | If checked, images will not be rendered |
| Image Size | Render image as original size or thumbnail rendition |
| Render Asset Titles? | If checked, render asset titles |
| Render Asset Creators? | If checked, render asset creator metadata |
| Creator Label | Label to render as prefix for creator value |
| Render Asset Descriptions? | If checked, render asset description |
| Render Asset Formats? | If checked, render asset formats |
| Format Label | Label to render as prefix for format value |
| Render as Links? | If checked, asset title/image will be wrapped in a link to the image |
| Title Heading Type | Heading type to render for asset title (when Render Asset Titles? is checked) |

### Link List Component

* Group: Harbor Lists
* Classifiable

Presents an author composed lists of links.  Links may be references to internal AEM pages or to external URLs.

#### Authorability

The addition of new list items is enabled by the "+" button in the toolbar of the Link List component.

When adding new items to an already populated list you will commonly need to tap on a list item, choose the "select parent" option from the toolbar, and then choose the Link List component from the dropdown list in order to be presented with the edit bar for the list itself.

##### List

| Dialog Field | Description |
| ------------ | ----------- |
| Classification | Input support for classifiability of the list component instance.  Classifies the entire list |

##### List Item

Each item in the list is rendered and authored as its own component.

Items in the list can be moved up or down in the list via the up and down buttons in the List Item's Toolbar.

| Dialog Field | Description |
| ------------ | ----------- |
| Title        | Presentable title to associated with the item in the list. |
| Path         | The path to an internal AEM page or the URL to an external page which the item in the list should link to. |

#### Developer Guide

The Link List component maintains a collection of List Items as direct child Resources.  Author-only Javascript under the namespace `Harbor.Components.LinkList` facilitates the addition and movement of List Items.

| Aspect | Value |
| ------------ | ----------- |
| Resource Type | harbor/components/content/lists/linklist |
| Component Group | Harbor Lists |
| Backing Class | com.icfolson.aem.harbor.core.components.content.list.link.LinkList |
| List Item Resource Type | harbor/components/content/lists/linklist/listablelink |
| List Item Backing Class | com.icfolson.aem.harbor.core.components.content.list.link.ListableLink |

### Dynamic List Component

* Group: Harbor Lists
* Classifiable
* Part of Dynamics

A Dynamic list implementation which allows for the authoring of any number 
of list items of various types. 
 
#### Design

Additional list item types may be made available by updating a site's design.  This can be done at a 
component instance level by switching to design mode and adding the component types to the Dynamic 
Carousel component instance's design, or at a design level by updating the design for primary paragraph 
systems through the site's design page.

#### Dynamic List Item Types

The following list item types are made available by Harbor.  See developer documentation below concerning 
the creation of new project specific types.

##### External Link Item

A list item which links to an external URL.

| Dialog Field | Description |
| ------------ | ----------- |
| URL | External URL to link to |
| Label | Text lable for the presented link |

##### Internal Link Item

A list item which links to an internal page.

| Dialog Field | Description |
| ------------ | ----------- |
| Linked Page | Path to page which will be linked to |
| Label | Text lable for the presented link |

##### Text Item

A list item which simply presents text.

| Dialog Field | Description |
| ------------ | ----------- |
| Text Content | Text to be presented as the content of the list item |

### Manual Page List Component

* Group: Harbor Lists

Render a list of pages constructed from a list of individual page paths.

#### Authorability

##### List Construction

| Dialog Field | Description |
| ------------ | ----------- |
| Paths | List of page paths to include in the list |

##### List Rendering

| Dialog Field | Description |
| ------------ | ----------- |
| Suppress Page Titles? | If checked, page titles will not be rendered |
| Render Page Subtitles? | If checked, page subtitles will be rendered beneath the heading and/or link |
| Render Page Images? | If checked, the page thumbnail image will be rendered beneath the header |
| Render Page Descriptions? | If checked, the page description will be rendered beneath the header |
| Render As Link? | If checked, the page title will be wrapped in a link to the page |
| Title Heading Type | Heading type to render for page title |

### Page List Component

* Group: Harbor Lists

Render a list of pages constructed from a search query.

#### Authorability

Pages are assembled by configuring the **List Construction** properties in the component dialog.  These properties are used to build a search query and resulting hits are transformed into a renderable list based on the configuration of the **List Rendering** dialog properties.

##### List Construction

| Dialog Field | Description |
| ------------ | ----------- |
| Path | Path to search for pages |
| Tags | Tags to filter pages |
| Use AND Logic | If checked, tags will use AND logic rather than OR logic to filter pages |
| Property Relative Path | Relative path from page node to tags property |
| Limit | Number of pages to return.  Defaults to 10. |
| Order By | Property name to order page results |
| Sort | Direction to sort by (ascending/descending) |

##### List Rendering

| Dialog Field | Description |
| ------------ | ----------- |
| Suppress Page Titles? | If checked, page titles will not be rendered |
| Render Page Subtitles? | If checked, page subtitles will be rendered beneath the heading and/or link |
| Render Page Images? | If checked, the page thumbnail image will be rendered beneath the header |
| Render Page Descriptions? | If checked, the page description will be rendered beneath the header |
| Render As Link? | If checked, the page title will be wrapped in a link to the page |
| Title Heading Type | Heading type to render for page title |

### Classified Content

Classified Content is a "classifiable" container with a paragraph system.  The paragraph system is rendered inside of a `<div>` element with a class attribute containing the authored classification tags.

| Dialog Field | Description |
| ------------ | ----------- |
| Classification | Set of classification tags for this content container |

### Container Component

* Group: Harbor Scaffolding
* Classifiable

A container wraps a paragraph system in a Bootstrap "container" `<div>` or `<section>` element.  All components on a page should be placed in a container for proper rendering.
 
#### Baked-In Container

The Baked-In Container variant allows for a container to be statically included in a page component.

### Responsive Container Component

* Group: Harbor Scaffolding

A content container which can be shown or hidden at various device size breakpoints.

#### Authorability

| Dialog Field | Description | 
| ------------ | ----------- |
| Hide In | Selection of one or more device types where the container content should be hidden |

### Column Row Component

* Group: Harbor Scaffolding
* Classifiable

The Harbor Column Row component lets authors flexibly create and layout any number of columns using the Bootstrap grid system.

Columns MUST be placed in a Container component.  See Containers under Core Concepts for more information.

#### Authorability

New columns are added using the "Add Experience" button in the toolbar of the Column Row component.  When in edit mode an author help message will appear indicating this and making it easy to select the Column Row in Touch UI.

Responsiveness of the columns as defined by the Bootstrap grid system can be tested either using the AEM Device emulation mode, the AEM Layout mode, or by resizing your browser window.

##### Row Authoring

| Dialog Field | Description | 
| ------------ | ----------- |
| ID           | A unique identifier for the Row.  If no ID is set no id attribute will be rendered |
| Classification | Input support for classifiability of the row component instance.  Classifies the entire row |

##### Column Authoring

Each Column in a Row is authored independently.  

Many of the inputs control the width and positioning of  the column.  See the Bootstrap documentation concerning the [Grid System](http://getbootstrap.com/css/#grid) for more information on these inputs.

The rendered content of a column is a Paragraph System into which arbitrary components may be placed.  

While it is not recommended due to the various complexities it introduces in authorability, Column Row components may be nested inside Columns allowing extremely complex layouts.

| Dialog Field | Description | 
| ------------ | ----------- |
| Extra Small Device Width | Width of column when presented on extra small devices.  Defaults to none. |
| Small Device Width | Width of column when presented on a small device.  Defaults to none. |
| Medium Device Width | Width of column when presented on a medium device. Defaults to none. |
| Large Device Width | Width of column when presented on a large device.  Defaults to none. |
| Ordering | Indicates whether to push or pull the column to force a certain ordering.  Defaults to none. |
| Classification | Input support for classifiability of the column component instance.  Classifies the single column within the row. |
| Inherit Content | When set to Yes the Paragraph System for the column will be rendered as an Inheriting Paragraph System | 
| ID | A unique identifier for the Column.  If no ID is set no id attribute will be rendered |

### Tabs Component

* Group: Harbor Scaffolding
* Classifiable

The Tabs component creates a set of paragraph system containers in Bootstrap tabs or pills.

#### Authorability

Individual tabs can be added by clicking the "Add Tab" button in the component's edit toolbar.  Each tab contains a title (i.e. the tab/pill label) and a paragraph system for adding nested components into the tab container.  Tabs can also be rearranged using the up/down arrows in the edit toolbar for each individual tab.

##### Tabs

| Dialog Field | Description | 
| ------------ | ----------- |
| Tab Type | Selection to render as Bootstrap "tabs" or "pills"  |

##### Tab

| Dialog Field | Description | 
| ------------ | ----------- |
| Title | Title to display atop the tab or pill 

### Dynamic Tabs Component

* Group: Harbor Scaffolding
* Classifiable
* Part of Dynamics

The Dynamic Tabs component allows for the creation of any number of typed tabs.  
Which tab types are supported by a particular instance of Dynamic Tabs is based on 
the established site design.  

#### Authorability

Individual tabs can be added by clicking the "Add Tab" button in the component's edit toolbar 
and selecting a tab type.  If no tab types are available this means that the site design must 
be modified to allow the desired types.  

The content and configuration of each tab is authored within the tab itself.

#### Design

Additional tab types may be made available by updating a site's design.  This can be done at a 
component instance level by switching to design mode and adding the component types to the Dynamic 
Tabs component instance's design, or at a design level by updating the design for primary paragraph 
systems through the site's design page.

#### Dynamic Tab Types

The following tab types are made available by Harbor.  See developer documentation below concerning 
the creation of new project specific types.

##### Parsys Tab

* Classifiable

A tab which exposes a single paragraph system for editing.

### Header Component

* Group: Harbor Scaffolding

This component is designed to effectively introduce the brand to the primary audience

#### Authorability

| Dialog Field | Description |
| ------------ | ----------- |
| Full Width        | When set to true, will render across the full width of the browser window |
| Container Inheritance       | When enabled, an inherting paragraph system is produced for this container instance |
| Classification        | Input support for classifiability of the header component instance. Classifies the header |
| ID     | A unique identifier for the Header. If no ID is set no id attribute will be rendered |

### Footer Component

* Group: Harbor Scaffolding

The footer, as its name suggests, is the final piece of content located at the bottom of each page of the web site. The purpose of the footer is to signal to the user that they are at the bottom of the web page and to provide links or shortcuts to other areas of the site. The footer is the area that many users expect to find the ‘fine print’ - legal information such as ‘terms and conditions’, ‘privacy policies’, etc. Typical short cuts links include: Contact Us, Sitemap, Privacy Policy, Terms &amp; Conditions.

#### Authorability

| Dialog Field | Description |
| ------------ | ----------- |
| Full Width        | When set to true, will render across the full width of the browser window |
| Container Inheritance       | When enabled, an inheriting paragraph system is produced for this container instance |
| Classification        | Input support for classifiability of the footer component instance. Classifies the footer |
| ID     | A unique identifier for the footer If no ID is set no id attribute will be rendered |

### Dynamic Carousel Component

* Group: Harbor
* Classifiable
* Part of Dynamics

A Dynamic Carousel implementation allowing for the creation of any number of 
slides of varying types.  The content and configuration of each slide is authored 
within the slide itself.  Authors may switch the current slide being authored 
using the previous and next slide buttons presented in the Dynamic Carousel's 
edit toolbar. 

#### Design

Additional slide types may be made available by updating a site's design.  This can be done at a 
component instance level by switching to design mode and adding the component types to the Dynamic 
Carousel component instance's design, or at a design level by updating the design for primary paragraph 
systems through the site's design page.

#### Dynamic Slide Types

The following carousel slide types are made available by Harbor.  See developer documentation below concerning 
the creation of new project specific types.

##### Parsys Slide

* Classifiable 

A slide which exposes a single paragraph system for editing.

##### Image and Caption Slide

The Image and Caption Slide type is still under evaluation and development. 
Use at your own risk.

### Main Auto Navigation

A navigation header constructed from a page hierarchy (or "tree").  Beginning with the current page, the navigation elements are assembled by traversing the page hierarchy to an optionally specified depth.

#### Authorability

Authoring of the Main Auto Navigation component is divided into two sections: "construction" and "rendering".  The former is concerned with how pages are included in the list of navigation elements, while the latter determines how these elements are rendered on the page.

##### Page Navigation Construction

| Dialog Field | Description |
| ------------ | ----------- |
| Navigation Depth | Limits how deep the page hierarchy is traversed from the root page.  A value of 1 would limit to only immediate children of the current page, a value of 2 would include grandchildren, and so on. |

##### Page Navigation Rendering

| Dialog Field | Description |
| ------------ | ----------- |
| Enable Sticky Navigation? | If selected, the navbar position will be fixed to the top of the page |
| Brand Link | If enabled, display a link to the root page as the first navigation element |
| Brand Link Text | Text to display inside of the brank link (will not render if Brand Link Image is selected) |
| Brand Link Image | Image to render inside of the brand link |
| Full Width | Enable the full width container rather than the default responsive fixed width container |
| Present Main Navigation Item in Drop Down | Render the parent navigation element in the context of the drop down and enable expansion of the dropdown via clicking on the entire navigation element.  If unchecked, a separate dropdown icon will render which must be clicked in order to expand the dropdown. |

### Main Manual Navigation

A navigation header constructed from a manually-selected list of pages.

#### Authorability

| Dialog Field | Description |
| ------------ | ----------- |
| Enable Sticky Navigation? | If selected, the navbar position will be fixed to the top of the page |
| Show Brand Link | If enabled, display a link to the root page as the first navigation element |
| Brand Link Text | Text to display inside of the brank link (will not render if Brand Link Image is selected) |
| Brand Link Image | Image to render inside of the brand link |
| Brand Link | Link target for navbar brand element at upper left corner of navbar |
| Full Width | Enable the full width container rather than the default responsive fixed width container |
| Present Main Navigation Item in Drop Down | Render the parent navigation element in the context of the drop down and enable expansion of the dropdown via clicking on the entire navigation element.  If unchecked, a separate dropdown icon will render which must be clicked in order to expand the dropdown. |

##### Adding Navigation Items

To add an item to the manual navigation list, click the 'Add Navigation Item' button in the component's edit toolbar.  This will render a new element which can be authored individually. 

| Dialog Field | Description |
| ------------ | ----------- |
| Element Title | Title to render in the navbar.  If left blank, the page title for the Element Link will be used. |
| Element Link | Page or external link to render for this element |
| Has Dropdown? | If enabled, a caret will be added to this element to produce a dropdown/flyout |

### Secondary Navigation

Secondary navigation is a vertically-oriented (i.e. "pills") navigation list that can be added or baked into a page.

#### Authorability

| Dialog Field | Description |
| ------------ | ----------- |
| Navigation Depth | Limits how deep the page hierarchy is traversed from the root page.  A value of 1 would limit to only immediate children of the current page, a value of 2 would include grandchildren, and so on. |
| Root Page Type | Select 'Home Page' if this navigation should be rooted at the current site's Home Page or 'Section Landing Page' if this navigation should be rooted at the nearest parent Section Landing Page |

### Breadcrumb Component

* Group: Harbor Navigation

#### Authorability

Page icons are authored in the "Icon" tab of page properties for a given page.  By default, the page icon is rendered alongside the page title/link if an icon has been authored for a page in the current breadcrumb trail.

| Dialog Field | Description |
| ------------ | ----------- |
| Breadcrumb Root | Indicates the starting point of the trail. Breadcrumb trails may be rooted at either the containing Home Page or the nearest containing Section Landing Page. |
| Include Current Page in Trail | Indicates whether the current page should be included as the terminal item in the Breadcrumb trail. |
| Include Root Page in Trail | Indicates whether the root page should be included as the first item in the Breadcrumb trail. |
| Render as Link | Indicates whether the items in the Breadcrumb trail should be live links to the pages they represent. |
| Root Item Configuration - Hide Icon | Indicates whether the page icon should be hidden for the root page when presented in the Breadcrumb trail. |
| Root Item Configuration - Hide Title | Indicates whether the page title should be hidden for the root page when presented in the Breadcrumb trail. |
| Intermediate Item Configuration - Hide Icon | Indicates whether the page icon should be hidden for all intermediate pages in the Breadcrumb trail. |
| Intermediate Item Configuration - Hide Title | Indicates whether the page title should be hidden for all intermediate pages in the Breadcrumb trail. |
| Current Item Configuration - Hide Icon | Indicates whether the page icon should be hidden for the current page when presented in the Breadcrumb trail. |
| Current Item Configuration - Hide Title | Indicates whether the page title should be hidden for the current page when presented in the Breadcrumb trail. |

##### Inheriting Breadcrumb

The Inheriting Breadcrumb component is an extension of the base Breadcrumb component that provides inheritance of configured properties from ancestor pages.

Inheritance capabilities are dependent on instances of this component having an identical relative path on descendant pages.

##### Baked-In Breadcrumb

The Baked-In Breadcrumb component further extends the Inheriting Breadcrumb for static inclusion in a page component.

`<sly data-sly-resource="${'breadcrumb' @ resourceType = 'harbor/components/content/bakedinbreadcrumb'}"></sly>`

### Main Auto Navigation Component

* Group: Harbor Navigation

This component is designed as a a site wide nested navigation

#### Authorability

| Dialog Field | Description |
| ------------ | ----------- |
| Navigation Depth      | Indicates how many levels of the page tree under the root page should be presented in the navigation. |
| Enable Sticky Navigation        | When enabled the site navigation will stick to the top of the browser viewport during page scrolling. Note, navigation stickyness will only be enabled in preview and publish mode. Further in preview mode, while stickyness will be enabled the results may not be visible in the Touch UI due to how the interface is rendered. To test the results fully remove editor.html from the browser's address bar. Also, when enabling stickyness ensure your design accounts for this and adds margin to the elements below the navigation otherwise these will render behind the navigation. |
| Brand Link       | Indicates whether a Brand Link should be presented. A Brand Link appears as a link to the Home Page of the site with text defaulted to the site's title.  |
| Brand Link Text        | Text to present in the Brand Link when shown. |
| Brand Link Image        | Image Asset to present in the Brand Link when shown. |
| Full Width       | Indicates whether the navigation should span the full width of the browser or operate at a fixed width. |
| Present Main Navigation Item in Drop Down        | When more than a single level of navigation is presented, nested items appear in drop downs under each top level item. In this case a decision must be made concerning whether clicking on the top level navigation item should open the drop down or whether it should take the user to the associated page. Enabling this option will cause the click to open the dropdown and the link to the associated page will be re-presented within the dropdown. When disabled clicking a top level navigation item will route the user to the associated page and users will have to click a toggle icon next to the menu item text to open the drop down. |

### Secondary Navigation Component

* Group: Harbor Navigation

This component is designed as a rooted tree navigation of arbitrary depth

#### Authorability

| Dialog Field | Description |
| ------------ | ----------- |
| Root Page Type       | Allows navigation to be rooted at the containing Home Page or the closest containing Section Landing Page. |
| Navigation Depth        | Indicates how many levels of the page tree under the root page should be presented in the navigation. |

## Core Concepts

### End User Core Concepts

#### Containers

Many of the elements of Bootstrap require placement within a Container.  In Harbor there are numerous Container Components which produce such a Container suitable for placing other components within.  These are identified by the `Container` demarcation in the documentation.  

For example, the Content Container Component is a basic Container.

General information about Containers in Bootstrap can be found [in the Bootstrap Container documentation](http://getbootstrap.com/css/#overview-container).

#### Classification

Many components in Harbor are "Classifiable".  This allows for the application of semantics to an 
otherwise general component.  For example, if you are producing a recipe, you might classify your 
Container component using a `Recipe` tag.  Systematically, for most components, this classification 
simply applies an additional class attribute to the rendered HTML for the component which allows 
for extended usage such as targeting of styles or unique indexability.

Classifications are always authored as Tags.

#### Dynamics

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
* Dynamic Accordion (still under development)

### Developer Core Concepts

#### Namespace

Numerous properties with consistent semantics unique to Harbor are namespaced under the `icf` namespace deployed by 
the included `com.icfolson.aem.namespace:aem-namespace-extension` module.  This module also exposes String constants 
for notable properties, namespace prefixes, and URIs.  A key element of cohesion across Harbor is the use of consistent 
semantics in the creation of a data space defining the content presentation Harbor affords within AEM.  

##### Page Types

The ICF namespace defines four types of pages:

* Home Page `icf:HomePage`
* Section Landing Page `icf:SectionLandingPage`
* Structural Page `icf:StructuralPage`
* Content Page (any page which is not one of the above)

A Page is defined to be of a type if it has a jcr:mixinType value of one of the above.  For example, a 
page with jcr:mixinType `icf:HomePage` is a home page.  

Any Page may be adapted to a `HierarchicalPage` which exposes methods for finding the containing 
Home Page and Section Landing Page.  Similarly any page may be adapted to a `HomePage` or `SectionLandingPage`.  
These later adaptations will only succeed if the page under adaptation is in fact a Home Page or 
Section Landing Page respectively.

Numerous components leverage these Page Type semantics.  Most notably are the navigational components which 
know how to construct a relevant navigation starting from either a containing Home Page or Section Landing Page. 

###### Home Page

A Home Page is intended to be the root page for a site.  Home Pages may be nested under other Home Pages 
supporting use cases such as one main site housing numerous micro sites.

###### Section Landing Page

Sites are often broken into large sections with unique purpose.  The Section Landing Page represents the 
root of a major section of a site.  For example, a site may be broken up into "Products", "Services", and "About Us". 
Each of these sections would be rooted by a Section Landing Page which would inform the behavior of the 
components used within the sections.  For instance, a secondary navigation specific to Products can be 
automatically generated specifically for the site section based on the semantics of the Section Landing Page. 

Section Landing Pages may be nested arbitrarily. 

###### Structural Page

A Structural Page is one which is not intended to be navigated to by an end user.  Such pages may serve a 
number of purposes such as serving as path place holders, country/language pages, etc.  Structural pages 
to not show up in navigations or other page listings of that type.  

###### Content Page

A Content Page is any page which is not a Home Page, a Section Landing Page, or a Structural Page.  There is 
no separate mixin type for Content Page. 

###### Page Types in Template Definitions

Page templates must define their respective page types on the `jcr:content` template node.  The following is 
an example template definition for a Home Page

```xml
<?xml version="1.0" encoding="UTF-8"?>
<jcr:root xmlns:sling="http://sling.apache.org/jcr/sling/1.0"
          xmlns:cq="http://www.day.com/jcr/cq/1.0"
          xmlns:jcr="http://www.jcp.org/jcr/1.0"
          xmlns:icf="http://www.icfolson.com/ns/aem/"
          jcr:primaryType="cq:Template"
          jcr:title="Project Harbor Home Page"
          allowedPaths="/content(/.*)?"
          ranking="0">
    <jcr:content
            jcr:primaryType="cq:PageContent"
            cq:designPath="/etc/designs/project-name"
            jcr:mixinTypes="[icf:HomePage]"
            sling:resourceType="project-name/components/page/homepage"/>
</jcr:root>
```

#### Lists

The functionality of many of the Components created for a particular project can be distilled down to the presentation of a List of items.  Lists therefore are central to Harbor and enabled by Interfaces and Abstract Classes suggesting a pattern to follow in List Component development.

List Components are a composition of a List Construction Strategy and a List Rendering Strategy.  The List Construction Strategy is responsible for the creation of a list of items based on the logic of the strategy.  These items are passed to the List Rendering Strategy which has an opportunity to add additional properties germane to the rendering of a particular component.  Construction and Rendering Strategies may be mixed and matched within the context of concrete List Component implementations allowing reuse of both across multiple component types.

TODO: Insert example of reuse from the various page listing components

TODO: Link to Javadocs where appropriate

#### Trees

Similar to Lists, Trees pair a Construction Strategy with a Rendering Strategy allowing for reusability and extention via composition.

A Tree has a single TreeNode instance as its root.  A TreeNode may in turn have any number of child TreeNodes of the same type.

Tree Components are in turn components which intend to manage a Tree data structure's construction and presentation.

TODO: Link to Javadocs where appropriate

#### Classification

Components are Classifiable if they contain an `icf:classification` input which is of type Tag Field. 
What a component does with the classification is up to the needs and requirements of the component. 
For all current Harbor components, classification causes additional class attributes to be written 
to the rendered HTML of the component.  This allows for the application of style and functionality 
to sections of a site without the need to built numerous custom components.  For example, a Recipe 
component could be made up of a Content Container with an appropriate classification.  Anything 
under that container then is semantically considered to be part of the recipe.  It may be appropriate 
to further classify child elements such as Recipe Step or Ingredient depending on the granular 
needs of the site and system.

Classifiability is implemented via the `com.icfolson.aem.harbor.core.components.mixins.classifiable.Classification` 
Component class and it's inheriting counterpart `com.icfolson.aem.harbor.core.components.mixins.classifiable.InheritedClassification`.
These are intended to be added as sub-components of classifiable components.  An example of such usage is the 
`com.icfolson.aem.harbor.core.components.content.columns` class. It includes Classification as a Dialog Field Set and an 
injected component

```
    @DialogField(tab = 1, ranking = 40) @DialogFieldSet
    @Self @Optional
    private Classification classification;
```

It then uses Classification's `classNames()` method to output a space delimited set of class names in the accompanying Sightly. 



