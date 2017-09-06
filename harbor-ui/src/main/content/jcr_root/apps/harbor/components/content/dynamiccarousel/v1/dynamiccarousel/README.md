# Harbor Dynamic Carousel Component (v1)

A container used to hold any number of Dynamic Carousel Slides. 
The Dynamic Carousel component differs from the Carousel component in that each 
slide can have varying types of content that are individually authored per slide. 

## General Information

* `group`: Harbor Scaffolding
* `resourceType`: `harbor/components/content/dynamiccarousel/v1/dynamiccarousel`

## Sling Model

Uses the `com.icfolson.aem.harbor.api.components.content.dynamiccarousel.DynamicCarousel` Model interface.

Each slide is an instance of `com.icfolson.aem.harbor.api.components.content.dynamiccarousel.DynamicCarouselSlide`.

## Authoring API

`Harbor.Components.DynamicCarousel.v1.DynamicCarousel.addSlide( carouselEditable, dialogPath )`

Opens the dialog indicated by the `dialogPath` parameter and gets it ready to produce 
a new child resource upon submission.  

`Harbor.Components.DynamicCarousel.v1.DynamicCarousel.moveUp( slideEditable )`

Called by passing an editable representing a single Slide.  Causes the 
item to swap places in order with its prior sibling.  

`Harbor.Components.DynamicCarousel.v1.DynamicCarousel.moveDown( slideEditable )`

Called by passing an editable representing a single Slide.  Causes the 
item to swap places in order with its next sibling.  

`Harbor.Components.DynamicCarousel.v1.DynamicCarousel.nextSlide( carouselEditable )`

Called by passing an editable representing the containing Carousel component instance. 
Sends a `harbor-DynamicCarousel-v1-nextSlide` command into the content frame.  A listener 
setup in the content frame is subscribed to these commands and is responsible for 
finding the relevant carousel container (based on the `path` data element passed 
with the command) and changing the currently visible slide to the next in the order. 
This allows each slide to be authored individually while still maintaining the 
WYSIWYG feel of the authoring interface.

`Harbor.Components.DynamicCarousel.v1.DynamicCarousel.previousSlide( carouselEditable )`

Performs the same task as `nextSlide` but opens the previous slide instead of the 
next. 

## HTL

* `dynamiccarousel.html` - The principal rendering HTL for the carousel.  Responsible for instantiating 
  a `DynamicCarousel` instance, including `authorhelp.html` if we are in edit mode, and calling upon the 
  `slides.html`, `previousandnextcontrols.html` and `slideselectorcontrols.html` templates.  
  Establishes the initial configuration for the carousel.
* `slides.html` - HTL template rendering the individual slides.  Each slide's contents is 
  included using `data-sly-resource` with a path and resource type determined by the 
  slide's implementation.
* `previousandnextcontrols.html` - HTL template for rendering the previous and next controls 
  over the carousel. 
* `slideselectorcontrols.html` - HTL template for rendering the slide selection controls over 
  the carousel.
* `authorhelp.html` - Help message rendered when in edit mode to provide a clickable hook 
  to bring up the carousel's edit bar.

## Default Implementation

`com.icfolson.aem.harbor.core.components.content.carousel.v1.DefaultCarousel`

Defaults all configuration for the carousel.  Concrete project implementations may 
override these defaults or chose to make them configurable.

Treats each child resource of the Carousel resource as a `DynamicCarouselSlide`.  Any child 
which can not be adapted to a `DynamicCarouselSlide` is ignored.

